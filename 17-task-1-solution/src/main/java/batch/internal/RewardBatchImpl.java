package batch.internal;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import rewards.Dining;
import rewards.RewardNetwork;
import batch.InvalidBatchException;
import batch.RewardBatch;
import batch.internal.support.ResourceFieldSetReader;

import common.datetime.SimpleDate;
import common.money.MonetaryAmount;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class RewardBatchImpl extends QuartzJobBean implements RewardBatch {

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

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		Resource input = (Resource) context.getJobDetail().getJobDataMap().get("input");
		int count = processResource(input);
		logger.info("Processed " + count + " Dining events.");
	}

	public int processResource(Resource input) throws InvalidBatchException {

		InputSource<Dining> source = new DiningSource(input);
		Dining dining = source.read();
		int count = 0;

		while (dining != null) {
			try {
				rewardNetwork.rewardAccountFor(dining);
			} catch (Exception e) {
				throw new InvalidBatchException("Cannot reward dining: [" + dining + "]", e);
			}
			count++;
			dining = source.read();
		}

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
