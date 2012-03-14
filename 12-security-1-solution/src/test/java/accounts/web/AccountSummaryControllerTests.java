package accounts.web;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import accounts.Account;

@RunWith(JUnit4.class)
public class AccountSummaryControllerTests {

	private AccountSummaryController controller;

	@Before
	public void setUp() {
		controller = new AccountSummaryController(new StubAccountManager());
	}

	// Unit tests for the accountSummary method....
	@Test
	public void testAccountSummary() throws Exception {
		List<Account> accounts = controller.accountSummary();
		assertEquals(2, accounts.size());
	}
}
