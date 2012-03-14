package accounts.web;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.mock.web.MockHttpServletRequest;
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
	public void testHandleDetailsRequest() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("entityId", "0");
		ModelAndView mav = controller.accountDetails(request);
		assertNotNull(mav);
		assertEquals(1, mav.getModel().size());
		assertTrue(mav.getModel().containsKey("account"));
		Account acc = (Account)mav.getModel().get("account");
		assertEquals(Long.valueOf(0), acc.getEntityId());
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
}
