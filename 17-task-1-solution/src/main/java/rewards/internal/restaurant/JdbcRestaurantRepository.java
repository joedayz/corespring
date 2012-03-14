package rewards.internal.restaurant;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import rewards.Dining;
import rewards.internal.account.Account;

import common.money.Percentage;

/**
 * Loads restaurants from a data source using the JDBC API.
 */
@Repository
public class JdbcRestaurantRepository extends JdbcDaoSupport implements RestaurantRepository {

	@Autowired
	public JdbcRestaurantRepository(DataSource dataSource){
		setDataSource(dataSource);
	}
	
	/**
	 * Maps a row returned from a query of T_RESTAURANT to a Restaurant object.
	 */
	private ParameterizedRowMapper<Restaurant> rowMapper = new RestaurantRowMapper();

	public Restaurant findByMerchantNumber(String merchantNumber) {
		String sql = "select MERCHANT_NUMBER, NAME, BENEFIT_PERCENTAGE, BENEFIT_AVAILABILITY_POLICY from T_RESTAURANT where MERCHANT_NUMBER = ?";
		return getJdbcTemplate().queryForObject(sql, new Object[]{merchantNumber}, rowMapper);
	}

	/**
	 * Maps a row returned from a query of T_RESTAURANT to a Restaurant object.
	 * 
	 * @param rs the result set with its cursor positioned at the current row
	 */
	private Restaurant mapRestaurant(ResultSet rs) throws SQLException {
		// get the row column data
		String name = rs.getString("NAME");
		String number = rs.getString("MERCHANT_NUMBER");
		Percentage benefitPercentage = Percentage.valueOf(rs.getString("BENEFIT_PERCENTAGE"));
		// map to the object
		Restaurant restaurant = new Restaurant(number, name);
		restaurant.setBenefitPercentage(benefitPercentage);
		restaurant.setBenefitAvailabilityPolicy(mapBenefitAvailabilityPolicy(rs));
		return restaurant;
	}

	/**
	 * Helper method that maps benefit availability policy data in the ResultSet to a fully-configured
	 * {@link BenefitAvailabilityPolicy} object. The key column is 'BENEFIT_AVAILABILITY_POLICY', which is a
	 * discriminator column containing a string code that identifies the type of policy. Currently supported types are:
	 * 'A' for 'always available' and 'N' for 'never available'.
	 * 
	 * More types could easily be added by enhancing this method. For example, 'W' for 'Weekdays only' or 'M' for 'Max
	 * Rewards per Month'. Some of these types might require additional database column values to be configured, for
	 * example a 'MAX_REWARDS_PER_MONTH' data column.
	 * 
	 * @param rs the result set used to map the policy object from database column values
	 * @return the matching benefit availability policy
	 * @throws IllegalArgumentException if the mapping could not be performed
	 */
	private BenefitAvailabilityPolicy mapBenefitAvailabilityPolicy(ResultSet rs) throws SQLException {
		String policyCode = rs.getString("BENEFIT_AVAILABILITY_POLICY");
		if ("A".equals(policyCode)) {
			return AlwaysAvailable.INSTANCE;
		} else if ("N".equals(policyCode)) {
			return NeverAvailable.INSTANCE;
		} else {
			throw new IllegalArgumentException("Not a supported policy code " + policyCode);
		}
	}

	/**
	 * Returns true indicating benefit is always available.
	 */
	static class AlwaysAvailable implements BenefitAvailabilityPolicy {
		static final BenefitAvailabilityPolicy INSTANCE = new AlwaysAvailable();

		public boolean isBenefitAvailableFor(Account account, Dining dining) {
			return true;
		}

		public String toString() {
			return "alwaysAvailable";
		}
	}

	/**
	 * Returns false indicating benefit is never available.
	 */
	static class NeverAvailable implements BenefitAvailabilityPolicy {
		static final BenefitAvailabilityPolicy INSTANCE = new NeverAvailable();

		public boolean isBenefitAvailableFor(Account account, Dining dining) {
			return false;
		}

		public String toString() {
			return "neverAvailable";
		}
	}

	private class RestaurantRowMapper implements ParameterizedRowMapper<Restaurant> {

		public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
			return mapRestaurant(rs);
		}

	}
}