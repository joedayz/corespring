package rewards.jms.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import rewards.RewardConfirmation;

/**
 * A simple logger for reward confirmations.
 */
public class RewardConfirmationLogger {

	private static final Log logger = LogFactory.getLog(RewardConfirmationLogger.class);

	private List<RewardConfirmation> confirmations = new ArrayList<RewardConfirmation>();

	public void log(RewardConfirmation rewardConfirmation) {
		this.confirmations.add(rewardConfirmation);
		if (logger.isInfoEnabled()) {
			logger.info("received confirmation: " + rewardConfirmation);
		}
	}

	public List<RewardConfirmation> getConfirmations() {
		return Collections.unmodifiableList(confirmations);
	}
}
