package rewards;

import rewards.internal.account.AccountDao;

/**
 * Created by josediaz on 4/14/14.
 */
public interface RewardNetwork {


    public RewardConfirmation rewardAccountFor(AccountDao accountDao);

    public AccountDao getAccountDao();

}
