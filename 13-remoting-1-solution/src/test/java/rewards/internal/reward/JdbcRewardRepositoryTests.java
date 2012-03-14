package rewards.internal.reward;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import junit.framework.TestCase;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import rewards.AccountContribution;
import rewards.Dining;
import rewards.RewardConfirmation;
import rewards.internal.account.Account;
import rewards.testdb.TestDataSourceFactory;

import common.money.MonetaryAmount;
import common.money.Percentage;

/**
 * Tests the JDBC reward repository with a test data source to verify data access and relational-to-object mapping
 * behavior works as expected.
 */
public class JdbcRewardRepositoryTests extends TestCase {

	private JdbcRewardRepository repository;

	private DataSource dataSource;

	@Override
	protected void setUp() throws Exception {
		
		dataSource = createTestDataSource();
		repository = new JdbcRewardRepository(dataSource);
		repository.setDataSource(dataSource);
	}

	public void testCreateReward() throws SQLException {
		Dining dining = Dining.createDining("100.00", "1234123412341234", "0123456789");

		Account account = new Account("1", "Keith and Keri Donald");
		account.setEntityId(0L);
		account.addBeneficiary("Annabelle", Percentage.valueOf("50%"));
		account.addBeneficiary("Corgan", Percentage.valueOf("50%"));

		AccountContribution contribution = account.makeContribution(MonetaryAmount.valueOf("8.00"));
		RewardConfirmation confirmation = repository.confirmReward(contribution, dining);
		assertNotNull("confirmation should not be null", confirmation);
		assertNotNull("confirmation number should not be null", confirmation.getConfirmationNumber());
		assertEquals("wrong contribution object", contribution, confirmation.getAccountContribution());
		verifyRewardInserted(confirmation, dining);
	}

	private void verifyRewardInserted(RewardConfirmation confirmation, Dining dining) throws SQLException {
		assertEquals(1, getRewardCount());
		Statement stmt = dataSource.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select REWARD_AMOUNT from T_REWARD where CONFIRMATION_NUMBER = '"
				+ confirmation.getConfirmationNumber() + "'");
		rs.next();
		assertEquals(confirmation.getAccountContribution().getAmount(), MonetaryAmount.valueOf(rs.getString(1)));
	}

	private int getRewardCount() throws SQLException {
		Statement stmt = dataSource.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("select count(*) from T_REWARD");
		rs.next();
		return rs.getInt(1);
	}

	private DataSource createTestDataSource() {
		Resource schemaLocation = new ClassPathResource("/rewards/testdb/schema.sql");
		Resource testDataLocation = new ClassPathResource("/rewards/testdb/test-data.sql");
		return new TestDataSourceFactory("rewards", schemaLocation, testDataLocation).getDataSource();
	}
}