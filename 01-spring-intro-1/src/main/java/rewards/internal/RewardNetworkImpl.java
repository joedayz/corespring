/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rewards.internal;

import common.money.MonetaryAmount;
import rewards.AccountContribution;
import rewards.Dining;
import rewards.RewardConfirmation;
import rewards.RewardNetwork;
import rewards.internal.account.Account;
import rewards.internal.account.AccountRepository;
import rewards.internal.restaurant.Restaurant;
import rewards.internal.restaurant.RestaurantRepository;
import rewards.internal.reward.RewardRepository;

/**
 *
 * @author josediaz
 */
public class RewardNetworkImpl implements RewardNetwork {

    private AccountRepository accountRepository;
    private RestaurantRepository restaurantRepository;
    private RewardRepository rewardRepository;

    public RewardNetworkImpl(AccountRepository accountRepository,
            RestaurantRepository restaurantRepository,
            RewardRepository rewardRepository) {
        this.accountRepository = accountRepository;
        this.restaurantRepository = restaurantRepository;
        this.rewardRepository = rewardRepository;
    }

    @Override
    public RewardConfirmation rewardAccountFor(Dining dining) {
        Account account = accountRepository.findByCreditCard(dining.getCreditCardNumber());
        Restaurant restaurant = restaurantRepository.findByMerchantNumber(
                    dining.getMerchantNumber());
        MonetaryAmount amount = restaurant.calculateBenefitFor(account, dining);
        AccountContribution contribution = account.makeContribution(amount);
        accountRepository.updateBeneficiaries(account);
        return rewardRepository.confirmReward(contribution, dining);
    }

}
