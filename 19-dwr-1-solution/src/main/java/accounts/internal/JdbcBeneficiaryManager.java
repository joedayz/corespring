package accounts.internal;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import common.money.MonetaryAmount;
import common.money.Percentage;

import accounts.Account;
import accounts.Beneficiary;
import accounts.BeneficiaryManager;

@Repository("beneficiaryManager")
public class JdbcBeneficiaryManager implements BeneficiaryManager {
	
	private HibernateTemplate hibernateTemplate;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcBeneficiaryManager(SessionFactory sessionFactory){
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		this.jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(sessionFactory));
	}

	@Transactional(readOnly = true)
	public Account findAccountById(Long accountId) {
		
		StringBuilder query = new StringBuilder("select id, number, name ");
		query.append(" from t_account where id = ? ");

		Account account = this.jdbcTemplate.queryForObject(query.toString(),
		        new Object[]{accountId}, new AccountMapper());
		return account;
	}
	
	private static final class AccountMapper implements RowMapper<Account> {

	    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Account account = new Account();
	    	account.setEntityId(rs.getLong("id"));
	    	account.setName(rs.getString("name"));
	    	account.setNumber(rs.getString("number"));
	        return account;
	    }        
	}		
	
	public void saveBeneficiary(Long accountId, Long id, String name,
			String allocationPercentage) {
		
		Beneficiary beneficiary = new Beneficiary(name, Percentage.valueOf(allocationPercentage));
		if(id.longValue() != -1){
			beneficiary.setEntityId(id);			
		}

		Account account = hibernateTemplate.get(Account.class, accountId);
		beneficiary.setAccount(account);
		hibernateTemplate.saveOrUpdate(beneficiary);	
		
	}
	
	public void deleteBeneficiary(Long id){
		jdbcTemplate.update("delete from t_account_beneficiary where ID = ".concat(id.toString()));
	}
	
	@Transactional(readOnly = true)
	public boolean isValidAllocationPercentage(Long accountId,  Long id, 
					BigDecimal allocationPercentage){
		
		StringBuilder query = new StringBuilder("select sum(allocation_percentage) ");
		query.append("from t_account_beneficiary where account_id = ").append(accountId);
		if(id.longValue() > 0){
			query.append(" and id != ").append(id);
		}
			
		BigDecimal percentage = jdbcTemplate.queryForObject(query.toString(), BigDecimal.class);
		if(percentage == null){
			percentage = BigDecimal.ZERO;
		}
		
		percentage = percentage.add(allocationPercentage.divide(new BigDecimal(100)));
		double percentageDouble = percentage.doubleValue();
		if(percentageDouble > 1){
			return false;
		}
		return true;		
	}

	@Transactional(readOnly = true)
	public List<Beneficiary> findBeneficiariesByAccountId(Long accountId) {
		
		StringBuilder query = new StringBuilder("select id, account_id, ");
		query.append(" name, allocation_percentage, savings");
		query.append(" from t_account_beneficiary ");
		query.append(" where account_id = ").append(accountId);
		
		List<Beneficiary> beneficiaries = jdbcTemplate.query( query.toString(), new BeneficiaryMapper());
		return beneficiaries;
	}
	
	private static final class BeneficiaryMapper implements RowMapper<Beneficiary> {

	    public Beneficiary mapRow(ResultSet rs, int rowNum) throws SQLException {
	    	Beneficiary beneficiary = new Beneficiary();
	    	
	    	beneficiary.setEntityId(rs.getLong("id"));
	    	beneficiary.setName(rs.getString("name"));
	    	
	    	Percentage percentage = new Percentage(rs.getDouble("allocation_percentage"));
	    	beneficiary.setAllocationPercentage(percentage);
	    	
	    	MonetaryAmount amount = new MonetaryAmount(rs.getDouble("savings"));
	    	beneficiary.setSavings(amount);
	    	
	    	Account account = new Account();
	    	account.setEntityId(rs.getLong("account_id"));
	    	beneficiary.setAccount(account);
	    	
	        return beneficiary;
	    }        
	}	
}