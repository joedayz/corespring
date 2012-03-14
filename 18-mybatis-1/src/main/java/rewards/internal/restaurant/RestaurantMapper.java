package rewards.internal.restaurant;


/**
 * Loads restaurant aggregates. Called by the reward network to find and reconstitute Restaurant entities from an
 * external form such as a set of RDMS rows.
 * 
 * Objects returned by this repository are guaranteed to be fully-initialized and ready to use.
 */
public interface RestaurantMapper {

	/**
	 * Load a Restaurant entity by its merchant number.
	 * @param merchantNumber the merchant number
	 * @return the restaurant
	 */
	//TODO 10: Use @Select annotation and describe the select query for search a Restaurant
	public Restaurant findByMerchantNumber(String merchantNumber);
}
