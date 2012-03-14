package rewardsadmin.app;

/**
 * A service for locating rewards. Used to present reward details to users.
 */
public interface RewardLookupService {

	/**
	 * Locates the reward with the confirmation number.
	 * @param confirmationNumber the confirmation number, a unique record locator
	 * @return the reward
	 */
	public Reward lookupReward(String confirmationNumber);
}
