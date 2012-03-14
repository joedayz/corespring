package rewards.internal.restaurant;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Loads restaurants from a data source using the Hibernate API.
 */
@Repository
public class HibernateRestaurantRepository implements RestaurantRepository {

	private SessionFactory sessionFactory;

	@Autowired
	public HibernateRestaurantRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Restaurant findByMerchantNumber(String merchantNumber) {
		return (Restaurant) getCurrentSession().createQuery("from Restaurant r where r.number = :merchantNumber")
				.setString("merchantNumber", merchantNumber).uniqueResult();
	}

}
