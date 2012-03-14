package rewards.internal.account;

import javax.sql.DataSource;

import junit.framework.TestCase;

import org.hibernate.SessionFactory;
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

public class HibernateAccountRepositoryTests extends TestCase {

	private HibernateAccountRepository repository;

	private PlatformTransactionManager transactionManager;

	private TransactionStatus status;

	@Override
	protected void setUp() throws Exception {
		repository = new HibernateAccountRepository();
		SessionFactory sessionFactory = createTestSessionFactory();
		repository.setSessionFactory(sessionFactory);
		transactionManager = new HibernateTransactionManager(sessionFactory);
		status = transactionManager.getTransaction(new DefaultTransactionDefinition());
	}

	@Override
	protected void tearDown() throws Exception {
		transactionManager.rollback(status);
	}

	public void testFindByCreditCard() {
		Account account = repository.findByCreditCard("1234123412341234");
		// assert the returned account contains what you expect given the state of the database
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

	private SessionFactory createTestSessionFactory() throws Exception {
		// simulate the Spring bean initialization lifecycle
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(createTestDataSource());
		Resource[] mappingLocations = new ClassPathResource[] {
				new ClassPathResource("Account.hbm.xml", Account.class),
				new ClassPathResource("Beneficiary.hbm.xml", Beneficiary.class) };
		factoryBean.setMappingLocations(mappingLocations);
		factoryBean.afterPropertiesSet();
		return (SessionFactory) factoryBean.getObject();
	}

	private DataSource createTestDataSource() {
		Resource schemaLocation = new ClassPathResource("/rewards/testdb/schema.sql");
		Resource testDataLocation = new ClassPathResource("/rewards/testdb/test-data.sql");
		return new TestDataSourceFactory("rewards", schemaLocation, testDataLocation).getDataSource();
	}
}