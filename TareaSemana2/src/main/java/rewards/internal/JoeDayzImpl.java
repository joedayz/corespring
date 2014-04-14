package rewards.internal;

import rewards.RewardConfirmation;
import rewards.RewardNetwork;
import rewards.internal.account.AccountDao;

/**
 * Created by josediaz on 4/14/14.
 */
public class JoeDayzImpl implements RewardNetwork {

    private AccountDao accountDao;

    public JoeDayzImpl(AccountDao accountDao){
        this.accountDao = accountDao;

    }

    @Override
    public RewardConfirmation rewardAccountFor(AccountDao accountDao) {
        return new RewardConfirmation();
    }

    @Override
    public AccountDao getAccountDao() {
        return accountDao;
    }
}
