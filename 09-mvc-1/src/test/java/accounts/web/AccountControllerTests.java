package accounts.web;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.web.servlet.ModelAndView;

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
	@SuppressWarnings("unchecked")
	public void testHandleSummaryRequest() throws Exception {
		ModelAndView mav = controller.accountSummary();
		assertNotNull(mav);
		assertEquals(1, mav.getModel().size());
		assertTrue(mav.getModel().containsKey("accounts"));
		List<Account> accounts = (List<Account>)mav.getModel().get("accounts");
		assertEquals(1, accounts.size());
	}
	
	// TODO 2: Add a test for the accountDetails() controller method
}
