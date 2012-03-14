package rewards.internal.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import common.money.MonetaryAmount;
import common.money.Percentage;

/**
 * A single beneficiary allocated to an account. Each beneficiary has a name (e.g. Annabelle) and a savings balance
 * tracking how much money has been saved for he or she to date (e.g. $1000).
 */
@Entity
@Table(name="T_ACCOUNT_BENEFICIARY")
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
	
	@Id 
    @GeneratedValue(strategy = GenerationType.AUTO)    
    @Column(name="ID", unique=true, nullable=false)	
	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}	

	/**
	 * Returns the beneficiary name.
	 */
	@Column(name="NAME", nullable=false, length=50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the beneficiary's allocation percentage in this account.
	 */
	@Column(name="ALLOCATION_PERCENTAGE", nullable=false, length=50)
	@Type(type="common.money.PercentageUserType")
	public Percentage getAllocationPercentage() {
		return allocationPercentage;
	}
	
	public void setAllocationPercentage(Percentage allocationPercentage) {
		this.allocationPercentage = allocationPercentage;
	}

	/**
	 * Returns the amount of savings this beneficiary has accrued.
	 */
	@Column(name="SAVINGS", nullable=false, length=50)
	@Type(type="common.money.MonetaryAmountUserType")
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_ID")
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}