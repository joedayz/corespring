package rewards.internal.account;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Finds account objects using the Hibernate API.
 */
@Repository
public class HibernateAccountRepository implements AccountRepository {

	private SessionFactory sessionFactory;

	/**
	 * Creates an new hibernate-based account repository.
	 * @param sessionFactory the Hibernate session factory required to obtain sessions
	 */
	@Autowired
	public HibernateAccountRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Account findByCreditCard(String creditCardNumber) {
		Integer accountId = (Integer) getCurrentSession().createSQLQuery(
				"select ACCOUNT_ID from T_ACCOUNT_CREDIT_CARD where NUMBER = ?").setString(0, creditCardNumber)
				.uniqueResult();
		return (Account) getCurrentSession().load(Account.class, accountId.longValue());
	}

	/**
	 * Returns the session associated with the ongoing reward transaction.
	 * @return the transactional session
	 */
	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}