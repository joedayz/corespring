package rewards.internal.restaurant;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * Finds restaurants using the Hibernate API.
 */
@Repository
public class HibernateRestaurantRepository implements RestaurantRepository {

	private HibernateTemplate hibernateTemplate;

	/**
	 * Creates an new hibernate-based restaurant repository.
	 * @param sessionFactory the Hibernate session factory required to obtain sessions
	 */
	@Autowired
	public HibernateRestaurantRepository(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public Restaurant findByMerchantNumber(String merchantNumber) {

		List<Restaurant> restaurants = hibernateTemplate.find("from Restaurant r where r.number = ?", merchantNumber);
		
		if(restaurants.isEmpty()) return null;
		return restaurants.get(0);
	}

}