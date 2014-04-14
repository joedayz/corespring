package rewards;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rewards.internal.account.AccountDao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by josediaz on 4/14/14.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-config.xml"})
public class RewardNetworkTest {

    @Autowired @Qualifier("rewardNetwork")
    private RewardNetwork rewardNetwork;

    @Autowired @Qualifier("joedayz")
    private RewardNetwork joedayz;

    @Autowired @Qualifier("uPromise")
    private RewardNetwork upromise;

    @Autowired @Qualifier("uPromiseSucursal")
    private RewardNetwork upromiseSucursal;

    @Test
    public void deberiaGenerarSingletonYPrototype(){
        AccountDao rewardNetworkAccountDao = rewardNetwork.getAccountDao();
        AccountDao joedayzAccountDao = joedayz.getAccountDao();
        AccountDao upromiseAccountDao = upromise.getAccountDao();
        AccountDao upromiseSucursalAccountDao = upromiseSucursal.getAccountDao();

        assertNotNull(rewardNetworkAccountDao);
        assertNotNull(joedayzAccountDao);
        assertNotNull(upromiseAccountDao);
        assertNotNull(upromiseSucursalAccountDao);
        assertEquals(rewardNetworkAccountDao.toString(), joedayzAccountDao.toString());
        assertNotEquals(upromiseAccountDao.toString(), upromiseSucursalAccountDao.toString());

    }

}
