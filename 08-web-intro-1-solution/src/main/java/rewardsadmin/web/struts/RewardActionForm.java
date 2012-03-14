package rewardsadmin.web.struts;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import rewards.Dining;
import rewards.RewardNetwork;

import common.money.MonetaryAmount;

/**
 * A Struts Action form holding data needed to execute the {@link RewardAction}.
 * 
 * The form data in this class is generically typed -- every property is a String. This is because the Struts data
 * binding system (bean utils) is unable to perform data binding in a manner that allows binding errors related to
 * type-conversion to be collected and displayed to the user automatically. It is the responsibility of this form class
 * to manually perform type conversion and validation.
 */
public class RewardActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10855963858378990L;

	private String creditCardNumber = "1234123412341234";

	private String amount = "100.00";

	private String merchantNumber = "1234567890";

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getMerchantNumber() {
		return merchantNumber;
	}

	public void setMerchantNumber(String merchantNumber) {
		this.merchantNumber = merchantNumber;
	}

	/**
	 * Factory method that creates a Dining object from this RewardActionForm. A Dining is needed as input into the
	 * {@link RewardNetwork} application to create new rewards.
	 * 
	 * This method will only be called after successful ActionForm validation.
	 * @return the dining populated from this form
	 * @see #validate(ActionMapping, javax.servlet.ServletRequest)
	 */
	public Dining createDining() {
		return Dining.createDining(amount, creditCardNumber, merchantNumber);
	}

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		if (creditCardNumber == null || creditCardNumber.length() == 0) {
			errors.add("creditCardNumber", new ActionMessage("required", new Object[] { "Credit Card Number" }));
		}
		if (amount == null || amount.length() == 0) {
			errors.add("amount", new ActionMessage("required", new Object[] { "Amount" }));
		} else {
			try {
				MonetaryAmount.valueOf(amount);
			} catch (IllegalArgumentException e) {
				errors.add("amount", new ActionMessage("money", new Object[] { "Amount" }));
			}
		}
		if (merchantNumber == null || merchantNumber.length() == 0) {
			errors.add("merchantNumber", new ActionMessage("required", new Object[] { "Merchant Number" }));
		}
		return errors;
	}
}