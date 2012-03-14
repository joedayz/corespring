package batch.internal;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import rewards.Dining;
import rewards.RewardNetwork;
import batch.InvalidBatchException;
import batch.RewardBatch;
import batch.internal.support.ResourceFieldSetReader;

import common.datetime.SimpleDate;
import common.money.MonetaryAmount;

public class RewardBatchImpl implements RewardBatch {

	private RewardNetwork rewardNetwork;

	protected Log logger = LogFactory.getLog(getClass());

	/**
	 * Default constructor is for {@link QuartzJobBean}.
	 */
	public RewardBatchImpl() {
	}

	public void setRewardNetwork(RewardNetwork rewardNetwork) {
		this.rewardNetwork = rewardNetwork;
	}

	/**
	 * Main interface method. Processes rewards from a set of dining events.
	 * 
	 * @see batch.RewardBatch#processResource(org.springframework.core.io.Resource)
	 */
	public int processResource(Resource input) throws InvalidBatchException {

		int count = 0;

		// TODO: create a DiningSource and iterate through it processing each
		// record with the rewardNetwork.

		return count;
	}

	public class DiningSource implements InputSource<Dining> {

		private ResourceFieldSetReader reader;

		public DiningSource(Resource input) {
			try {
				reader = new ResourceFieldSetReader(input);
			} catch (IOException e) {
				throw new InvalidBatchException("Cannot open resource: [" + input + "]", e);
			}
		}

		public Dining read() {
			FieldSet fields = reader.read();
			if (fields == null) {
				return null;
			}

			MonetaryAmount amount;
			String creditCardNumber;
			String merchantNumber;
			SimpleDate date;
			try {
				amount = MonetaryAmount.valueOf(fields.readString(0));
				creditCardNumber = fields.readString(1);
				merchantNumber = fields.readString(2);
				String[] dates = StringUtils.delimitedListToStringArray(fields.readString(3), "/");
				date = new SimpleDate(new Integer(dates[0]), new Integer(dates[1]), new Integer(dates[2]));
			} catch (Exception e) {
				throw new InvalidBatchException("Cannot parse dining from fields: [" + fields + "]", e);
			}

			Dining dining = new Dining(amount, creditCardNumber, merchantNumber, date);
			return dining;
		}

	}

}
