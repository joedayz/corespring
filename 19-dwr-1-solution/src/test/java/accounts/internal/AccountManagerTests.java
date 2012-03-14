package accounts.internal;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import accounts.Account;
import accounts.AccountManager;
import accounts.Beneficiary;

import common.money.MonetaryAmount;
import common.money.Percentage;

import static org.junit.Assert.*;

/**
 * Unit test for the Hibernate-based account manager implementation. Tests application behavior to verify the Account
 * Hibernate mapping is correct.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:accounts/system-test-config.xml"})
public class AccountManagerTests extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private AccountManager accountManager;

	@Test
	public void testGetAllAccounts() {
		List<Account> accounts = accountManager.getAllAccounts();
		assertNotNull(accounts);
	}

	@Test
	public void testGetAccount() {
		Account account = accountManager.getAccount(Long.valueOf(1));
		// assert the returned account contains what you expect given the state
		// of the database
		assertNotNull("account should never be null", account);
		assertEquals("wrong entity id", Long.valueOf(1), account.getEntityId());
		assertEquals("wrong account number", "123456789", account.getNumber());
		assertEquals("wrong name", "Keith and Keri Donald", account.getName());
		assertEquals("wrong beneficiary collection size", 2, account.getBeneficiaries().size());

		Beneficiary b1 = account.getBeneficiary("Annabelle");
		assertNotNull("Annabelle should be a beneficiary", b1);
		assertEquals("wrong savings", MonetaryAmount.valueOf("0.00"), b1.getSavings());
		assertEquals("wrong allocation percentage", Percentage.valueOf("50%"), b1.getAllocationPercentage());

		Beneficiary b2 = account.getBeneficiary("Corgan");
		assertNotNull("Corgan should be a beneficiary", b2);
		assertEquals("wrong savings", MonetaryAmount.valueOf("0.00"), b2.getSavings());
		assertEquals("wrong allocation percentage", Percentage.valueOf("50%"), b2.getAllocationPercentage());
	}

	@Test
	public void testUpdateAccount() {
		Account oldAccount = accountManager.getAccount(Long.valueOf(1));
		oldAccount.setName("Ben Hale");
		accountManager.update(oldAccount);
		Account newAccount = accountManager.getAccount(Long.valueOf(1));
		assertEquals("Did not persist the name change", "Ben Hale", newAccount.getName());
	}

	@Test
	public void testUpdateAccountBeneficiaries() {
		Map<String, Percentage> allocationPercentages = new HashMap<String, Percentage>();
		allocationPercentages.put("Annabelle", Percentage.valueOf("25%"));
		allocationPercentages.put("Corgan", Percentage.valueOf("75%"));
		accountManager.updateBeneficiaryAllocationPercentages(Long.valueOf(1), allocationPercentages);
		Account account = accountManager.getAccount(Long.valueOf(1));
		assertEquals("Invalid adjusted percentage", Percentage.valueOf("25%"), account.getBeneficiary("Annabelle")
				.getAllocationPercentage());
		assertEquals("Invalid adjusted percentage", Percentage.valueOf("75%"), account.getBeneficiary("Corgan")
				.getAllocationPercentage());
	}
	
	@Test
	public void testAddBeneficiary() {
		accountManager.addBeneficiary(Long.valueOf(1), "Ben");
		Account account = accountManager.getAccount(Long.valueOf(1));
		assertEquals("Should only have three beneficiaries", 3, account.getBeneficiaries().size());
	}

	@Test
	public void testRemoveBeneficiary() {
		Map<String, Percentage> allocationPercentages = new HashMap<String, Percentage>();
		allocationPercentages.put("Corgan", Percentage.oneHundred());
		accountManager.removeBeneficiary(Long.valueOf(1), "Annabelle", allocationPercentages);
		Account account = accountManager.getAccount(Long.valueOf(1));
		assertEquals("Should only have one beneficiary", 1, account.getBeneficiaries().size());
		assertEquals("Corgan should now have 100% allocation", Percentage.oneHundred(), account
				.getBeneficiary("Corgan").getAllocationPercentage());
	}

}