package rewards.internal.account;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * Finds account objects using the Hibernate API.
 */
@Repository
public class HibernateAccountRepository implements AccountRepository {

	private HibernateTemplate hibernateTemplate;

	/**
	 * Creates an new hibernate-based account repository.
	 * @param sessionFactory the Hibernate session factory required to obtain sessions
	 */
	@Autowired
	public HibernateAccountRepository(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public Account findByCreditCard(String creditCardNumber) {
		
		List<Account> accounts = hibernateTemplate.find("from Account a  where a.creditCardNumber = ?", creditCardNumber);
		if (accounts.isEmpty()) return null;
		return accounts.get(0);		
	}
}