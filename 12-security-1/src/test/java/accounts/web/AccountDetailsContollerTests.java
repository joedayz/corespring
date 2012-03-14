package accounts.web;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.orm.ObjectRetrievalFailureException;

import accounts.Account;

@RunWith(JUnit4.class)
public class AccountDetailsContollerTests {

	private AccountDetailsController controller;

	@Before
	public void setUp() throws Exception {
		controller = new AccountDetailsController(new StubAccountManager());
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
