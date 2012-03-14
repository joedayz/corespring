package rewardsadmin.web.springmvc20;

import rewards.Dining;
import rewards.RewardNetwork;

import common.datetime.SimpleDate;
import common.money.MonetaryAmount;

/**
 * A form-backing object holding data for fields of the "new reward" form. Just a plain-old-java-object (POJO). Strongly
 * typed--Spring's data binder is capable of performing type conversion and validation.
 */
public class RewardForm {

	private String creditCardNumber = "1234123412341234";

	private MonetaryAmount amount = MonetaryAmount.valueOf("100.00");

	private String merchantNumber = "1234567890";

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public MonetaryAmount getAmount() {
		return amount;
	}

	public void setAmount(MonetaryAmount amount) {
		this.amount = amount;
	}

	public String getMerchantNumber() {
		return merchantNumber;
	}

	public void setMerchantNumber(String merchantNumber) {
		this.merchantNumber = merchantNumber;
	}

	/**
	 * Factory method that creates a Dining object from this RewardForm. A Dining is needed as input into the
	 * {@link RewardNetwork} application to create new rewards.
	 * @return the dining populated from this form
	 */
	public Dining createDining() {
		return new Dining(amount, creditCardNumber, merchantNumber, SimpleDate.today());
	}
}