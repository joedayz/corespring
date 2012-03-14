package batch.internal;

import java.io.ByteArrayInputStream;

import junit.framework.TestCase;

import org.springframework.core.io.InputStreamResource;

import rewards.AccountContribution;
import rewards.Dining;
import rewards.RewardConfirmation;
import rewards.internal.RewardNetworkImpl;
import rewards.internal.StubAccountRepository;
import rewards.internal.StubRestaurantRepository;
import rewards.internal.StubRewardRepository;

public class RewardBatchImplTests extends TestCase {

	private RewardBatchImpl batch = new RewardBatchImpl();

	private int confirmations = 0;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		batch.setRewardNetwork(new RewardNetworkImpl(new StubAccountRepository(), new StubRestaurantRepository(),
				new StubRewardRepository() {
					@Override
					public RewardConfirmation confirmReward(AccountContribution contribution, Dining dining) {
						confirmations++;
						return super.confirmReward(contribution, dining);
					}
				}));
	}

	public void testNoRecords() throws Exception {
		int count = batch.processResource(new InputStreamResource(new ByteArrayInputStream("".getBytes())));
		assertEquals(0, count);
		assertEquals(confirmations, 0);
	}

	public void testSunnyDay() throws Exception {
		int count = batch.processResource(new InputStreamResource(new ByteArrayInputStream(
				("100,1234123412341234,1234567890,2007/02/12\n" + "200,1234123412341234,1234567890,2007/02/26\n")
						.getBytes())));
		assertEquals(2, count);
		assertEquals(confirmations, 2);
	}
}
