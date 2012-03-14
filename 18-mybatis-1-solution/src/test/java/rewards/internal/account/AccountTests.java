package rewards.internal.account;

import junit.framework.TestCase;
import rewards.AccountContribution;

import common.money.MonetaryAmount;
import common.money.Percentage;

/**
 * Unit tests for the Account class that verify Account behavior works in isolation.
 */
public class AccountTests extends TestCase {

	private Account account = new Account("1", "Keith and Keri Donald");

	public void testAccountIsValid() {
		// setup account with a valid set of beneficiaries to prepare for testing
		account.addBeneficiary("Annabelle", Percentage.valueOf("50%"));
		account.addBeneficiary("Corgan", Percentage.valueOf("50%"));
		assertTrue(account.isValid());
	}

	public void testAccountIsInvalidWithNoBeneficiaries() {
		assertFalse(account.isValid());
	}

	public void testAccountIsInvalidWhenBeneficiaryAllocationsAreOver100() {
		account.addBeneficiary("Annabelle", Percentage.valueOf("50%"));
		account.addBeneficiary("Corgan", Percentage.valueOf("100%"));
		assertFalse(account.isValid());
	}

	public void testAccountIsInvalidWhenBeneficiaryAllocationsAreUnder100() {
		account.addBeneficiary("Annabelle", Percentage.valueOf("50%"));
		account.addBeneficiary("Corgan", Percentage.valueOf("25%"));
		assertFalse(account.isValid());
	}

	public void testMakeContribution() {
		account.addBeneficiary("Annabelle", Percentage.valueOf("50%"));
		account.addBeneficiary("Corgan", Percentage.valueOf("50%"));
		AccountContribution contribution = account.makeContribution(MonetaryAmount.valueOf("100.00"));
		assertEquals(contribution.getAmount(), MonetaryAmount.valueOf("100.00"));
		assertEquals(MonetaryAmount.valueOf("50.00"), contribution.getDistribution("Annabelle").getAmount());
		assertEquals(MonetaryAmount.valueOf("50.00"), contribution.getDistribution("Corgan").getAmount());
	}
}