package rewards;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

/**
 * A system test that demonstrates how the effects of a given test can affect all tests that follow. JUnit makes no
 * guarantee about the order that tests run in, so each test must be independent of all others.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:rewards/system-test-config.xml" })
// TODO 4: Update test to extend AbstractTransactionalJUnit4SpringContextTests
public class RewardNetworkSideEffectTest {

	/**
	 * The object being tested.
	 */
	@Autowired
	private RewardNetwork rewardNetwork;

	/**
	 * A template to use for test verification
	 */
	private SimpleJdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	@Test
	public void testCollision1stTime() {
		Dining dining = Dining.createDining("100.00", "1234123412341234", "1234567890");
		rewardNetwork.rewardAccountFor(dining);
		String sql = "select SAVINGS from T_ACCOUNT_BENEFICIARY where NAME = ?";
		assertEquals(Double.valueOf(4.00), jdbcTemplate.queryForObject(sql, Double.class, "Annabelle"));
		assertEquals(Double.valueOf(4.00), jdbcTemplate.queryForObject(sql, Double.class, "Corgan"));
	}

	@Test
	public void testCollision2ndTime() {
		Dining dining = Dining.createDining("100.00", "1234123412341234", "1234567890");
		rewardNetwork.rewardAccountFor(dining);
		String sql = "select SAVINGS from T_ACCOUNT_BENEFICIARY where NAME = ?";
		assertEquals(Double.valueOf(4.00), jdbcTemplate.queryForObject(sql, Double.class, "Annabelle"));
		assertEquals(Double.valueOf(4.00), jdbcTemplate.queryForObject(sql, Double.class, "Corgan"));
	}
}