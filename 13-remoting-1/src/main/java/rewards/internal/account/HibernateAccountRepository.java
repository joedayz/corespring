package rewards.internal.account;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Loads accounts from a data source using the Hibernate API.
 */
@Repository
public class HibernateAccountRepository implements AccountRepository {

	private SessionFactory sessionFactory;

	@Autowired
	public HibernateAccountRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Account findByCreditCard(String creditCardNumber) {
		Integer accountId = (Integer) getCurrentSession().createSQLQuery(
				"select ACCOUNT_ID from T_ACCOUNT_CREDIT_CARD where NUMBER = ?").setString(0, creditCardNumber)
				.uniqueResult();
		return (Account) getCurrentSession().load(Account.class, accountId.longValue(), LockMode.UPGRADE);
	}
}
