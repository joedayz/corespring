package accounts.internal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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

import accounts.Account;
import accounts.Beneficiary;
import accounts.testdb.TestDataSourceFactory;

import common.money.MonetaryAmount;
import common.money.Percentage;

/**
 * Unit test for the Hibernate-based account manager implementation. Tests application behavior to verify the Account
 * Hibernate mapping is correct.
 */
public class HibernateAccountManagerTests extends TestCase {

	private HibernateAccountManager accountManager;

	private PlatformTransactionManager transactionManager;

	private TransactionStatus transactionStatus;

	@Override
	protected void setUp() throws Exception {
		// setup the repository to test
		SessionFactory sessionFactory = createTestSessionFactory();
		accountManager = new HibernateAccountManager(sessionFactory);
		// begin a transaction
		transactionManager = new HibernateTransactionManager(sessionFactory);
		transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
	}

	public void testGetAllAccounts() {
		List<Account> accounts = accountManager.getAllAccounts();
		assertEquals("Wrong number of accounts", 21, accounts.size());
	}

	public void testGetAccount() {
		Account account = accountManager.getAccount(Long.valueOf(0));
		// assert the returned account contains what you expect given the state
		// of the database
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

	public void testUpdateAccount() {
		Account oldAccount = accountManager.getAccount(Long.valueOf(0));
		oldAccount.setName("Ben Hale");
		accountManager.update(oldAccount);
		Account newAccount = accountManager.getAccount(Long.valueOf(0));
		assertEquals("Did not persist the name change", "Ben Hale", newAccount.getName());
	}

	public void testUpdateAccountBeneficiaries() {
		Map<String, Percentage> allocationPercentages = new HashMap<String, Percentage>();
		allocationPercentages.put("Annabelle", Percentage.valueOf("25%"));
		allocationPercentages.put("Corgan", Percentage.valueOf("75%"));
		accountManager.updateBeneficiaryAllocationPercentages(Long.valueOf(0), allocationPercentages);
		Account account = accountManager.getAccount(Long.valueOf(0));
		assertEquals("Invalid adjusted percentage", Percentage.valueOf("25%"), account.getBeneficiary("Annabelle")
				.getAllocationPercentage());
		assertEquals("Invalid adjusted percentage", Percentage.valueOf("75%"), account.getBeneficiary("Corgan")
				.getAllocationPercentage());
	}

	public void testAddBeneficiary() {
		accountManager.addBeneficiary(Long.valueOf(0), "Ben");
		Account account = accountManager.getAccount(Long.valueOf(0));
		assertEquals("Should only have three beneficiaries", 3, account.getBeneficiaries().size());
	}

	public void testRemoveBeneficiary() {
		Map<String, Percentage> allocationPercentages = new HashMap<String, Percentage>();
		allocationPercentages.put("Corgan", Percentage.oneHundred());
		accountManager.removeBeneficiary(Long.valueOf(0), "Annabelle", allocationPercentages);
		Account account = accountManager.getAccount(Long.valueOf(0));
		assertEquals("Should only have one beneficiary", 1, account.getBeneficiaries().size());
		assertEquals("Corgan should now have 100% allocation", Percentage.oneHundred(), account
				.getBeneficiary("Corgan").getAllocationPercentage());
	}

	@Override
	protected void tearDown() throws Exception {
		// rollback the transaction to avoid corrupting other tests
		transactionManager.rollback(transactionStatus);
	}

	private SessionFactory createTestSessionFactory() throws Exception {
		// create a FactoryBean to help create a Hibernate SessionFactory
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(createTestDataSource());
		Resource[] mappingLocations = new ClassPathResource[] {
				new ClassPathResource("Account.hbm.xml", HibernateAccountManager.class),
				new ClassPathResource("Beneficiary.hbm.xml", HibernateAccountManager.class) };
		factoryBean.setMappingLocations(mappingLocations);
		factoryBean.setHibernateProperties(createHibernateProperties());
		// initialize according to the Spring InitializingBean contract
		factoryBean.afterPropertiesSet();
		// get the created session factory
		return (SessionFactory) factoryBean.getObject();
	}

	private DataSource createTestDataSource() {
		Resource schemaLocation = new ClassPathResource("/accounts/testdb/schema.sql");
		Resource testDataLocation = new ClassPathResource("/accounts/testdb/test-data.sql");
		return new TestDataSourceFactory("rewards", schemaLocation, testDataLocation).getDataSource();
	}

	private Properties createHibernateProperties() {
		Properties properties = new Properties();
		// turn on formatted SQL logging (very useful to verify Hibernate is
		// issuing proper SQL)
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.format_sql", "true");
		return properties;
	}
}