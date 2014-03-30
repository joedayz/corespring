package rewards.internal.account;

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
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import rewards.testdb.TestDataSourceFactory;

import common.money.MonetaryAmount;
import common.money.Percentage;

import static org.junit.Assert.*;

/**
 * Unit test for the Hibernate-based account repository implementation. Tests application data access behavior to verify
 * the Account Hibernate mapping is correct.
 */
@RunWith(JUnit4.class)
public class HibernateAccountRepositoryTest {

	private HibernateAccountRepository repository;

	private PlatformTransactionManager transactionManager;

	private TransactionStatus transactionStatus;

	@Before
	public void setUp() throws Exception  {
		// setup the repository to test
		SessionFactory sessionFactory = createTestSessionFactory();
		repository = new HibernateAccountRepository(sessionFactory);
		// begin a transaction
		transactionManager = new HibernateTransactionManager(sessionFactory);
		transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
	}

	@Test
	public void testFindByCreditCard() {
		Account account = repository.findByCreditCard("1234123412341234");
		// assert the returned account contains what you expect given the state of the database
		// and the Account Hibernate mapping configuration
		assertNotNull("account should never be null", account);
		assertEquals("wrong entity id", Long.valueOf(0), account.getEntityId());
		assertEquals("wrong account number", "123456789", account.getNumber());
		assertEquals("wrong name", "Keith and Keri Donald", account.getName());
		assertEquals("wrong beneficiary collection size", 2, account.getBeneficiaries().size());

		Beneficiary b1 = account.getBeneficiary("Annabelle");
		assertNotNull("Annabelle should be a beneficiary", b1);
		assertEquals("wrong savings", MonetaryAmount.valueOf("0.00"), b1.getSavings());
		assertEquals("wrong allocation percentage", Percentage.valueOf("50%"), b1.getAllocationPercentage());

		Beneficiary b2 = account.getBeneficiary("Corgan");
		assertNotNull("Corgan should be a beneficiary", b2);
		assertEquals("wrong savings", MonetaryAmount.valueOf("0.00"), b2.getSavings());
		assertEquals("wrong allocation percentage", Percentage.valueOf("50%"), b2.getAllocationPercentage());
	}

	@After
	public void tearDown() {
		// rollback the transaction to avoid corrupting other tests
		transactionManager.rollback(transactionStatus);
	}

	private SessionFactory createTestSessionFactory() throws Exception {
		// create a FactoryBean to help create a Hibernate SessionFactory
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(createTestDataSource());
		Resource[] mappingLocations = new ClassPathResource[] {
				new ClassPathResource("Account.hbm.xml", Account.class),
				new ClassPathResource("Beneficiary.hbm.xml", Beneficiary.class) };
		factoryBean.setMappingLocations(mappingLocations);
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