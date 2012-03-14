package rewards.internal.account;

import javax.persistence.Transient;

import common.money.MonetaryAmount;
import common.money.Percentage;

/**
 * A single beneficiary allocated to an account. Each beneficiary has a name (e.g. Annabelle) and a savings balance
 * tracking how much money has been saved for he or she to date (e.g. $1000).
 */
//TODO 10: Add persistence annotation of beneficiary table for mapping with beneficiary class
public class Beneficiary {

	private Long entityId;
	
	private String name;

	private Percentage allocationPercentage;

	private MonetaryAmount savings = MonetaryAmount.valueOf("0.00");
	
	@SuppressWarnings("unused")
	private Beneficiary() {
	}

	/**
	 * Creates a new account beneficiary.
	 * @param name the name of the beneficiary
	 * @param allocationPercentage the beneficiary's allocation percentage within its account
	 */
	public Beneficiary(String name, Percentage allocationPercentage) {
		this.name = name;
		this.allocationPercentage = allocationPercentage;
	}

	/**
	 * Creates a new account beneficiary. This constructor should be called by privileged objects responsible for
	 * reconstituting an existing Account object from some external form such as a collection of database records.
	 * Marked package-private to indicate this constructor should never be called by general application code.
	 * @param name the name of the beneficiary
	 * @param allocationPercentage the beneficiary's allocation percentage within its account
	 * @param savings the total amount saved to-date for this beneficiary
	 */
	public Beneficiary(String name, Percentage allocationPercentage, MonetaryAmount savings) {
		this.name = name;
		this.allocationPercentage = allocationPercentage;
		this.savings = savings;
	}
	
	//TODO 11: Add id annotation 
    //TODO 12: Add id generation strategy. Use GenerationType.AUTO   
    //TODO 13: Add the mapping between the column and the attribute	
	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}	

	/**
	 * Returns the beneficiary name.
	 */
	//TODO 14: Add the mapping between the column and the attribute	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the beneficiary's allocation percentage in this account.
	 */
	//TODO 15: Add the mapping between the column and the attribute
	//TODO 16: Add annotation custom datatype
	public Percentage getAllocationPercentage() {
		return allocationPercentage;
	}
	
	public void setAllocationPercentage(Percentage allocationPercentage) {
		this.allocationPercentage = allocationPercentage;
	}

	/**
	 * Returns the amount of savings this beneficiary has accrued.
	 */
	//TODO 17: Add the mapping between the column and the attribute
	//TODO 18: Add annotation custom datatype
	public MonetaryAmount getSavings() {
		return savings;
	}
	
	public void setSavings(MonetaryAmount savings) {
		this.savings = savings;
	}

	/**
	 * Credit the amount to this beneficiary's saving balance.
	 * @param amount the amount to credit
	 */
	@Transient
	public void credit(MonetaryAmount amount) {
		savings = savings.add(amount);
	}

	public String toString() {
		return "name = '" + name + "', allocationPercentage = " + allocationPercentage + ", savings = " + savings + ")";
	}
	
	private Account account;
	
	//TODO 19: Add the annotation of many-to-one with the parent class. 	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}