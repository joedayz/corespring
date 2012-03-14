package rewards.internal.restaurant;

import org.apache.ibatis.annotations.Select;

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
	@Select(" SELECT " + 
			" 	ID as entityId,  " + 
			" 	MERCHANT_NUMBER as number,  " + 
			" 	NAME as name,  " + 
			" 	BENEFIT_PERCENTAGE as benefitPercentage,  " + 
			" 	BENEFIT_AVAILABILITY_POLICY as benefitAvailabilityPolicy " + 
			" FROM T_RESTAURANT " + 
			" 	WHERE MERCHANT_NUMBER = #{merchantNumber}")
	public Restaurant findByMerchantNumber(String merchantNumber);
}
