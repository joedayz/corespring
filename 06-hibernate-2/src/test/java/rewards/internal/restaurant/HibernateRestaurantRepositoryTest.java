package rewards.internal.restaurant;

import static org.junit.Assert.*;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import rewards.testdb.TestDataSourceFactory;

import common.money.Percentage;

/**
 * Unit test for the Hibernate-based restaurant repository implementation. Tests application data access behavior to
 * verify the Restaurant Hibernate mapping is correct.
 */
@RunWith(JUnit4.class)
public class HibernateRestaurantRepositoryTest {

	private HibernateRestaurantRepository repository;

	private PlatformTransactionManager transactionManager;

	private TransactionStatus transactionStatus;

	@Before
	public void setUp() throws Exception {
		// setup the repository to test
		SessionFactory sessionFactory = createTestSessionFactory();
		repository = new HibernateRestaurantRepository(sessionFactory);
		// begin a transaction
		transactionManager = new HibernateTransactionManager(sessionFactory);
		transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
	}

	@Test
	public void testFindRestaurantByMerchantNumber() {
		Restaurant restaurant = repository.findByMerchantNumber("1234567890");
		assertNotNull("the restaurant should never be null", restaurant);
		assertEquals("the merchant number is wrong", "1234567890", restaurant.getNumber());
		assertEquals("the name is wrong", "AppleBees", restaurant.getName());
		assertEquals("the benefitPercentage is wrong", Percentage.valueOf("8%"), restaurant.getBenefitPercentage());
		assertEquals("the benefit availability policy is wrong", AlwaysAvailable.INSTANCE, restaurant
				.getBenefitAvailabilityPolicy());
	}

	@After
	public void tearDown() throws Exception {
		// rollback the transaction to avoid corrupting other tests
		transactionManager.rollback(transactionStatus);
	}

	private SessionFactory createTestSessionFactory() throws Exception {
		// create a FactoryBean to help create a Hibernate SessionFactory
		AnnotationSessionFactoryBean factoryBean = new AnnotationSessionFactoryBean();
		factoryBean.setDataSource(createTestDataSource());
		
		//TODO 35: Add annotated classes

		factoryBean.setHibernateProperties(createHibernateProperties());
		// initialize according to the Spring InitializingBean contract
		factoryBean.afterPropertiesSet();
		// get the created session factory
		return (SessionFactory) factoryBean.getObject();
	}

	private DataSource createTestDataSource() {
		Resource schemaLocation = new ClassPathResource("/rewards/testdb/schema.sql");
		Resource testDataLocation = new ClassPathResource("/rewards/testdb/test-data.sql");
		return new TestDataSourceFactory("rewards", schemaLocation, testDataLocation).getDataSource();
	}

	private Properties createHibernateProperties() {
		Properties properties = new Properties();
		// turn on formatted SQL logging (useful to verify Hibernate is issuing proper SQL)
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.format_sql", "true");
		return properties;
	}
}