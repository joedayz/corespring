package batch;

import org.springframework.core.io.Resource;

import rewards.Dining;
import rewards.RewardNetwork;

/**
 * An interface for a batch processor on the {@link RewardNetwork}.
 * 
 */
public interface RewardBatch {

	/**
	 * Accepts an input resource representing a set of {@link Dining} events, and processes them to reward the diner as
	 * appropriate using the {@link RewardNetwork}.
	 * 
	 * @param input a file or input stream resource.
	 * @return TODO
	 * @throws InvalidBatchException if the data are invalid.
	 */
	int processResource(Resource input) throws InvalidBatchException;

}
