package accounts.web;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import accounts.Account;
import static org.junit.Assert.*;
/**
 * A JUnit test case testing the AccountController. The AccountController
 * has two handler methods, therefore, two tests.
 */
@RunWith(JUnit4.class)
public class AccountControllerTests {
	
	private AccountController controller;

	@Before
	public void setUp() throws Exception {
		controller = new AccountController(new  StubAccountManager());
	}

	@Test
	public void testHandleDetailsRequest() throws Exception {
		Account acc = controller.accountDetails(0l);
		assertNotNull(acc);
		assertEquals(Long.valueOf(0), acc.getEntityId());
	}

	@Test
	public void testHandleSummaryRequest() throws Exception {
		List<Account> accounts = controller.accountSummary();
		assertEquals(1, accounts.size());
	}
}
