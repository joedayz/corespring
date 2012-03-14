package rewards.internal.reward;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import rewards.AccountContribution;
import rewards.Dining;
import rewards.RewardConfirmation;

import common.datetime.SimpleDate;

/**
 * JDBC implementation of a reward repository that records the result of a reward transaction by inserting a reward
 * confirmation record.
 */
@Repository
public class JdbcRewardRepository extends SimpleJdbcDaoSupport implements RewardRepository {

	@Autowired
	public JdbcRewardRepository(DataSource dataSource){
		setDataSource(dataSource);
	}

	public RewardConfirmation confirmReward(AccountContribution contribution, Dining dining) {
		
		String sql = "insert into T_REWARD (CONFIRMATION_NUMBER, REWARD_AMOUNT, REWARD_DATE, ACCOUNT_NUMBER, DINING_MERCHANT_NUMBER, DINING_DATE, DINING_AMOUNT) values (?, ?, ?, ?, ?, ?, ?)";
		String confirmationNumber = nextConfirmationNumber();
		getSimpleJdbcTemplate().update(sql, confirmationNumber, contribution.getAmount().asBigDecimal(),
				SimpleDate.today().asDate(), contribution.getAccountNumber(), dining.getMerchantNumber(),
				dining.getDate().asDate(), dining.getAmount().asBigDecimal());
		return new RewardConfirmation(confirmationNumber, contribution);
	}

	private String nextConfirmationNumber() {
		String sql = "select next value for S_REWARD_CONFIRMATION_NUMBER from DUAL_REWARD_CONFIRMATION_NUMBER";
		return getSimpleJdbcTemplate().queryForObject(sql, String.class);
	}
}