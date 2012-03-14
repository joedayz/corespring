package rewardsadmin.app.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import rewardsadmin.app.Reward;
import rewardsadmin.app.RewardLookupService;

import common.datetime.SimpleDate;
import common.money.MonetaryAmount;

/**
 * A JDBC-based implementation of the reward lookup service. Uses Spring JDBC to query rewards against a data source.
 */
public class JdbcRewardLookupService extends SimpleJdbcDaoSupport implements RewardLookupService {

	/**
	 * Maps rows in returned JDBC result sets to Reward objects.
	 */
	private ParameterizedRowMapper<Reward> rewardMapper = new RewardMapper();

	@SuppressWarnings("deprecation")
	@Transactional
	public Reward lookupReward(String confirmationNumber) {
		return getSimpleJdbcTemplate()
				.queryForObject(
						"select CONFIRMATION_NUMBER, ACCOUNT_NUMBER, DINING_MERCHANT_NUMBER, REWARD_DATE, REWARD_AMOUNT from T_REWARD where CONFIRMATION_NUMBER = ?",
						rewardMapper, confirmationNumber);
	}

	/**
	 * Enapsulates the lgoic to map a row in a ResultSet to a Reward object.
	 */
	private static class RewardMapper implements ParameterizedRowMapper<Reward> {
		public Reward mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Reward(rs.getString(1), rs.getString(2), rs.getString(3), SimpleDate.valueOf(rs.getDate(4)),
					new MonetaryAmount(rs.getDouble(5)));
		}
	}
}