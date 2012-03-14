package rewards.internal.account;

import org.springframework.stereotype.Repository;

/**
 * Finds account objects using the Hibernate API.
 */
@Repository
public class HibernateAccountRepository implements AccountRepository {

	//TODO 3: Use HibernateTemplate
	
	/**
	 * Creates an new hibernate-based account repository.
	 * @param sessionFactory the Hibernate session factory required to obtain sessions
	 */
	//TODO 4: Instantiate HibernateTemplate 

	public Account findByCreditCard(String creditCardNumber) {
		// TODO 5: Use an HQL query to select the account entity for a given credit card number
		throw new UnsupportedOperationException("Not yet implemented");
	}
}