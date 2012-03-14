package rewards.internal.restaurant;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * Finds restaurants using the Hibernate API.
 */
@Repository
public class HibernateRestaurantRepository implements RestaurantRepository  {

	private HibernateTemplate hibernateTemplate;
	
	/**
	 * Creates an new hibernate-based restaurant repository.
	 * @param sessionFactory the Hibernate session factory required to obtain sessions
	 */
	@Autowired
	public HibernateRestaurantRepository(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	public Restaurant findByMerchantNumber(String merchantNumber) {
		// TODO 7: Use named bind parameters to find and return a Restaurant based on its merchant number
		throw new UnsupportedOperationException("Not yet implemented");
	}

}