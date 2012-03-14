package rewards.internal.restaurant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import rewards.Dining;
import rewards.internal.account.Account;

import common.money.MonetaryAmount;
import common.money.Percentage;

/**
 * A restaurant establishment in the network. Like AppleBee's.
 * 
 * Restaurants calculate how much benefit may be awarded to an account for dining based on a availability policy and a
 * benefit percentage.
 */
@Entity
@Table(name="T_RESTAURANT")
public class Restaurant {
	
	private Long entityId;

	private String number;

	private String name;

	private Percentage benefitPercentage;

	private BenefitAvailabilityPolicy benefitAvailabilityPolicy;

	@SuppressWarnings("unused")
	private Restaurant() {
	}

	/**
	 * Creates a new restaurant.
	 * @param number the restaurant's merchant number
	 * @param name the name of the restaurant
	 */
	public Restaurant(String number, String name) {
		this.number = number;
		this.name = name;
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
	 * Sets the percentage benefit to be awarded for eligible dining transactions.
	 * @param benefitPercentage the benefit percentage
	 */
	public void setBenefitPercentage(Percentage benefitPercentage) {
		this.benefitPercentage = benefitPercentage;
	}

	/**
	 * Sets the policy that determines if a dining by an account at this restaurant is eligible for benefit.
	 * @param benefitAvailabilityPolicy the benefit availability policy
	 */
	public void setBenefitAvailabilityPolicy(BenefitAvailabilityPolicy benefitAvailabilityPolicy) {
		this.benefitAvailabilityPolicy = benefitAvailabilityPolicy;
	}

	/**
	 * Returns the name of this restaurant.
	 */
	@Column(name="NAME", nullable=false, length=80)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the merchant number of this restaurant.
	 */
	@Column(name="MERCHANT_NUMBER", nullable=false, length=10)
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * Returns this restaurant's benefit percentage.
	 */
	@Column(name="BENEFIT_PERCENTAGE", nullable=false)
	@Type(type="common.money.PercentageUserType")
	public Percentage getBenefitPercentage() {
		return benefitPercentage;
	}

	/**
	 * Returns this restaurant's benefit availability policy.
	 */
	@Column(name="BENEFIT_AVAILABILITY_POLICY", nullable=false)
	@Type(type="rewards.internal.restaurant.BenefitAvailabilityPolicyUserType")
	public BenefitAvailabilityPolicy getBenefitAvailabilityPolicy() {
		return benefitAvailabilityPolicy;
	}

	/**
	 * Calculate the benefit eligible to this account for dining at this restaurant.
	 * @param account the account that dined at this restaurant
	 * @param dining a dining event that occurred
	 * @return the benefit amount eligible for reward
	 */
	public MonetaryAmount calculateBenefitFor(Account account, Dining dining) {
		if (benefitAvailabilityPolicy.isBenefitAvailableFor(account, dining)) {
			return dining.getAmount().multiplyBy(benefitPercentage);
		} else {
			return MonetaryAmount.zero();
		}
	}

	public String toString() {
		return "Number = '" + number + "', name = '" + name + "', benefitPercentage = " + benefitPercentage
				+ ", benefitAvailabilityPolicy = " + benefitAvailabilityPolicy;
	}
}