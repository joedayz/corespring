package rewards.internal;

import rewards.AccountContribution;
import rewards.Dining;
import rewards.RewardConfirmation;
import rewards.RewardNetwork;
import rewards.internal.account.Account;
import rewards.internal.account.AccountMapper;
import rewards.internal.account.Beneficiary;
import rewards.internal.restaurant.Restaurant;
import rewards.internal.restaurant.RestaurantMapper;
import rewards.internal.reward.RewardRepository;

import common.money.MonetaryAmount;

/**
 * Rewards an Account for Dining at a Restaurant.
 * 
 * The sole Reward Network implementation. This object is an application-layer service responsible for coordinating with
 * the domain-layer to carry out the process of rewarding benefits to accounts for dining.
 * 
 * Said in other words, this class implements the "reward account for dining" use case.
 */
public class RewardNetworkImpl implements RewardNetwork {

	private AccountMapper accountMapper;

	private RestaurantMapper restaurantMapper;

	private RewardRepository rewardRepository;

	/**
	 * Creates a new reward network.
	 * @param accountMapper the repository for loading accounts to reward
	 * @param restaurantMapper the repository for loading restaurants that determine how much to reward
	 * @param rewardRepository the repository for recording a record of successful reward transactions
	 */
	public RewardNetworkImpl(AccountMapper accountMapper,
			RestaurantMapper restaurantMapper, RewardRepository rewardRepository) {
		this.accountMapper = accountMapper;
		this.restaurantMapper = restaurantMapper;
		this.rewardRepository = rewardRepository;
	}

	public RewardConfirmation rewardAccountFor(Dining dining) {

		Account account = accountMapper.findByCreditCard(dining.getCreditCardNumber());
		Restaurant restaurant = restaurantMapper.findByMerchantNumber(dining.getMerchantNumber());
		MonetaryAmount amount = restaurant.calculateBenefitFor(account, dining);
		AccountContribution contribution = account.makeContribution(amount);
		updateBeneficiaries(account);
		return rewardRepository.confirmReward(contribution, dining);
	}
	
	public void updateBeneficiaries(Account account){
		
		for (Beneficiary beneficiary : account.getBeneficiaries()) {
			accountMapper.updateBeneficiary(beneficiary);
		}
	}
}