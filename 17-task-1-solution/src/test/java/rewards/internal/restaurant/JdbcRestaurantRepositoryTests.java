package rewards.internal.restaurant;

import javax.sql.DataSource;

import junit.framework.TestCase;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;

import rewards.testdb.TestDataSourceFactory;

import common.money.Percentage;

/**
 * Tests the JDBC restaurant repository with a test data source to verify data access and relational-to-object mapping
 * behavior works as expected.
 */
public class JdbcRestaurantRepositoryTests extends TestCase {

	private JdbcRestaurantRepository repository;

	@Override
	protected void setUp() throws Exception {
		
		repository = new JdbcRestaurantRepository(createTestDataSource());
	}

	public void testFindRestaurantByMerchantNumber() {
		Restaurant restaurant = repository.findByMerchantNumber("1234567890");
		assertNotNull("the restaurant should never be null", restaurant);
		assertEquals("the merchant number is wrong", "1234567890", restaurant.getNumber());
		assertEquals("the name is wrong", "AppleBees", restaurant.getName());
		assertEquals("the benefitPercentage is wrong", Percentage.valueOf("8%"), restaurant.getBenefitPercentage());
		assertEquals("the benefit availability policy is wrong", JdbcRestaurantRepository.AlwaysAvailable.INSTANCE,
				restaurant.getBenefitAvailabilityPolicy());
	}

	public void testFindRestaurantByBogusMerchantNumber() {
		try {
			repository.findByMerchantNumber("bogus");
			fail("Should have thrown EmptyResultDataAccessException for a 'bogus' merchant number");
		} catch (EmptyResultDataAccessException e) {
			// expected
		}
	}

	private DataSource createTestDataSource() {
		Resource schemaLocation = new ClassPathResource("/rewards/testdb/schema.sql");
		Resource testDataLocation = new ClassPathResource("/rewards/testdb/test-data.sql");
		return new TestDataSourceFactory("rewards", schemaLocation, testDataLocation).getDataSource();
	}
}