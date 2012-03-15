package accounts.internal;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import accounts.ReportManager;

@Repository
public class JdbcReportManager implements ReportManager{

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcReportManager(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Map<String, Object>> getAllAccounts(){
		
		StringBuilder query = new StringBuilder();
		query.append("	select a.number as numeroCuenta,");
		query.append("		a.name as titular,");
		query.append("		b.name as beneficiario,");
		query.append("		b.allocation_percentage as beneficio,");
		query.append("		b.savings as acumulado");
		query.append("	from t_account a join t_account_beneficiary b ");
		query.append("		on b.account_id = a.id");
		
		List<Map<String, Object>> accounts = jdbcTemplate.queryForList(query.toString());
		return accounts;
	}
	
}
