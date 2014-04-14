package rewards.internal;

import rewards.RewardConfirmation;
import rewards.RewardNetwork;
import rewards.internal.account.AccountDao;

/**
 * Created by josediaz on 4/14/14.
 */
public class UPromiseImpl implements RewardNetwork{



    private AccountDao accountDao;


    public UPromiseImpl(AccountDao accountDao){
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
