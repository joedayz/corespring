package rewards;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * A system test that demonstrates how the effects of a given test can affect
 * all tests that follow. JUnit makes no guarantee about the order that tests
 * run in, so each test must be independent of all others.
 */
@ContextConfiguration(locations = { "classpath:rewards/system-test-config.xml" })
public class RewardNetworkSideEffectTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	/**
	 * The object being tested.
	 */
	@Autowired
	private RewardNetwork rewardNetwork;

	@Test
	public void collision1stTime() {
		Dining dining = Dining.createDining("100.00", "1234123412341234", "1234567890");
		rewardNetwork.rewardAccountFor(dining);
		String sql = "select SAVINGS from T_ACCOUNT_BENEFICIARY where NAME = ?";
		
		assertEquals(Double.valueOf(4.00), super.simpleJdbcTemplate.queryForObject(sql,
				Double.class, "Annabelle"));
		
		assertEquals(Double.valueOf(4.00), super.simpleJdbcTemplate.queryForObject(sql,
				Double.class, "Corgan"));
	}

	@Test
	public void collision2ndTime() {
		Dining dining = Dining.createDining("100.00", "1234123412341234",
				"1234567890");
		rewardNetwork.rewardAccountFor(dining);
		String sql = "select SAVINGS from T_ACCOUNT_BENEFICIARY where NAME = ?";
		assertEquals(Double.valueOf(4.00), super.simpleJdbcTemplate.queryForObject(sql,
				Double.class, "Annabelle"));
		assertEquals(Double.valueOf(4.00), super.simpleJdbcTemplate.queryForObject(sql,
				Double.class, "Corgan"));
	}
}