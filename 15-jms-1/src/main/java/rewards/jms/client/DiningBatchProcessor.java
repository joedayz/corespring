package rewards.jms.client;

import java.util.List;

import rewards.Dining;

/**
 * A batch processor for dining events.
 * 
 * Typical implementations would send notifications to the reward network in order to generate reward confirmations.
 */
public interface DiningBatchProcessor {

	/**
	 * Processes a batch of dining events.
	 * 
	 * @param batch a list of dining events
	 */
	void processBatch(List<Dining> batch);

}
