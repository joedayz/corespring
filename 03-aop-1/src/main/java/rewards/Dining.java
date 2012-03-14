package rewards;

import common.datetime.SimpleDate;
import common.money.MonetaryAmount;

/**
 * A dining event that occurred, representing a charge made to an credit card by a merchant on a specific date.
 * 
 * For a dining to be eligible for reward, the credit card number should map to an account in the reward network. In
 * addition, the merchant number should map to a restaurant in the network.
 * 
 * A value object. Immutable.
 */
public class Dining {

	private MonetaryAmount amount;

	private String creditCardNumber;

	private String merchantNumber;

	private SimpleDate date;

	/**
	 * Creates a new dining, reflecting an amount that was charged to a card by a merchant on the date specified.
	 * @param amount the total amount of the dining bill
	 * @param creditCardNumber the number of the credit card used to pay for the dining bill
	 * @param merchantNumber the merchant number of the restaurant where the dining occurred
	 * @param date the date of the dining event
	 */
	public Dining(MonetaryAmount amount, String creditCardNumber, String merchantNumber, SimpleDate date) {
		this.amount = amount;
		this.creditCardNumber = creditCardNumber;
		this.merchantNumber = merchantNumber;
		this.date = date;
	}

	/**
	 * Creates a new dining, reflecting an amount that was charged to a credit card by a merchant on today's date. A
	 * convenient static factory method.
	 * @param amount the total amount of the dining bill as a string
	 * @param creditCardNumber the number of the credit card used to pay for the dining bill
	 * @param merchantNumber the merchant number of the restaurant where the dining occurred
	 * @return the dining event
	 */
	public static Dining createDining(String amount, String creditCardNumber, String merchantNumber) {
		return new Dining(MonetaryAmount.valueOf(amount), creditCardNumber, merchantNumber, SimpleDate.today());
	}

	/**
	 * Creates a new dining, reflecting an amount that was charged to a credit card by a merchant on the date specified.
	 * A convenient static factory method.
	 * @param amount the total amount of the dining bill as a string
	 * @param creditCardNumber the number of the credit card used to pay for the dining bill
	 * @param merchantNumber the merchant number of the restaurant where the dining occurred
	 * @param month the month of the dining event
	 * @param day the day of the dining event
	 * @param year the year of the dining event
	 * @return the dining event
	 */
	public static Dining createDining(String amount, String creditCardNumber, String merchantNumber, int month,
			int day, int year) {
		return new Dining(MonetaryAmount.valueOf(amount), creditCardNumber, merchantNumber, new SimpleDate(month, day,
				year));
	}

	/**
	 * Returns the amount of this dining--the total amount of the bill that was charged to the credit card.
	 */
	public MonetaryAmount getAmount() {
		return amount;
	}

	/**
	 * Returns the number of the credit card used to pay for this dining. For this dining to be eligible for reward,
	 * this credit card number should be associated with a valid account in the reward network.
	 */
	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	/**
	 * Returns the merchant number of the restaurant where this dining occurred. For this dining to be eligible for
	 * reward, this merchant number should be associated with a valid restaurant in the reward network.
	 */
	public String getMerchantNumber() {
		return merchantNumber;
	}

	/**
	 * Returns the date this dining occurred on.
	 */
	public SimpleDate getDate() {
		return date;
	}

	public boolean equals(Object o) {
		if (!(o instanceof Dining)) {
			return false;
		}
		Dining other = (Dining) o;
		// value objects are equal if their attributes are equal
		return amount.equals(other.amount) && creditCardNumber.equals(other.creditCardNumber)
				&& merchantNumber.equals(other.merchantNumber) && date.equals(other.date);
	}

	public int hashCode() {
		return amount.hashCode() + creditCardNumber.hashCode() + merchantNumber.hashCode() + date.hashCode();
	}

	public String toString() {
		return "Dining of " + amount + " charged to '" + creditCardNumber + "' by '" + merchantNumber + "' on " + date;
	}
}