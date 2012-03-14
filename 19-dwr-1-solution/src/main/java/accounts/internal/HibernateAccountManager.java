package accounts.internal;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import accounts.Account;
import accounts.AccountManager;
import common.money.Percentage;

/**
 * An account manager that uses Hibernate to find accounts.
 */
@Repository("accountManager")
public class HibernateAccountManager implements AccountManager {

	private SessionFactory sessionFactory;
	private HibernateTemplate hibernateTemplate;

	/**
	 * Creates a new Hibernate account manager.
	 * @param sessionFactory the Hibernate session factory
	 */
	@Autowired
	public HibernateAccountManager(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	@Transactional(readOnly = true)
	public Account findAccount(String number) {
		
		return (Account) getCurrentSession().
			createQuery("from Account a where a.number = ?").
			setString(0, number).uniqueResult();
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Account> getAllAccounts() {
		return hibernateTemplate.find("from Account order by entityId desc");
	}

	@Transactional(readOnly = true)
	public Account getAccount(Long id) {
		Account account = hibernateTemplate.get(Account.class, id);
		return account;
	}

	@Transactional
	public void update(Account account) {
		hibernateTemplate.saveOrUpdate(account);		
	}

	@Transactional
	public void updateBeneficiaryAllocationPercentages(Long accountId, Map<String, Percentage> allocationPercentages) {
		Account account = getAccount(accountId);
		for (Entry<String, Percentage> entry : allocationPercentages.entrySet()) {
			account.getBeneficiary(entry.getKey()).setAllocationPercentage(entry.getValue());
		}
	}

	@Transactional
	public void addBeneficiary(Long accountId, String beneficiaryName) {
		getAccount(accountId).addBeneficiary(beneficiaryName, Percentage.zero());
	}

	@Transactional
	public void removeBeneficiary(Long accountId, String beneficiaryName, Map<String, Percentage> allocationPercentages) {
		getAccount(accountId).removeBeneficiary(beneficiaryName);
		updateBeneficiaryAllocationPercentages(accountId, allocationPercentages);
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Account> searchAccounts(String name) {
		
		StringBuilder query = new StringBuilder("from Account ");
		if(StringUtils.isNotBlank(name)){
			query.append(" where upper(name) like '%").append(name.trim().toUpperCase()).append("%' ");
		}
		
		List<Account> accounts = hibernateTemplate.find(query.toString());
		return accounts;
	}	
	
	/**
	 * Returns the session associated with the ongoing reward transaction.
	 * @return the transactional session
	 */
	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

}