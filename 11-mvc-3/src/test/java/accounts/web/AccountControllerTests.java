package accounts.web;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.orm.ObjectRetrievalFailureException;

import accounts.Account;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class AccountControllerTests {
	
	private AccountController controller;

	@Before
	public void setUp() throws Exception {
		controller = new AccountController(new  StubAccountManager());
	}

	// Unit tests for the accountSummary method....
	@Test
	public void testAccountSummary() throws Exception {
		List<Account> accounts = controller.accountSummary();
		assertEquals(2, accounts.size());
	}

	// Unit tests for the accountDetails method....
	@Test
	public void testAccountDetails() throws Exception {
		Account account = controller.accountDetails(new Long(0));
		assertEquals(Long.valueOf(0), account.getEntityId());
	}
	
	@Test
	public void testAccountDetailsInvalidId() throws Exception {
		try {
			controller.accountDetails(new Long(99));
			fail("Should have thrown an ObjectRetrievalFailureException with invalid id");
		} catch (ObjectRetrievalFailureException e) {
			// expected exception
		}
	}

}
