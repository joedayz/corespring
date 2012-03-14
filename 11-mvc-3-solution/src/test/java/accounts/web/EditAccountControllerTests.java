package accounts.web;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.bind.support.SimpleSessionStatus;

import accounts.Account;

@RunWith(JUnit4.class)
public class EditAccountControllerTests{

	private StubAccountManager accountManager;

	private EditAccountController controller;

	@Before
	public void setUp() {
		accountManager = new StubAccountManager();
		controller = new EditAccountController(accountManager);
	}

	@Test
	public void testGet() throws Exception {
		Account account = controller.setupForm(new Long(0));
		assertNotNull("Should have an account", account);
		assertEquals("Should be an entity with id of 0", Long.valueOf(0), account.getEntityId());
	}

	@Test
	public void testPost() throws Exception {
		Account account = new Account("1", "Ben");
		account.setEntityId(new Long(0));
		BindingResult result = new BeanPropertyBindingResult(account, "account");
		SessionStatus sessionStatus = new SimpleSessionStatus();
		String view = controller.onSubmit(account , result, sessionStatus);
		assertEquals("redirect:/accounts/accountDetails.htm?entityId=0", view);
		assertTrue(sessionStatus.isComplete());
		account = accountManager.getAccount(Long.valueOf(0));
		assertEquals("1", account.getNumber());
		assertEquals("Ben", account.getName());
	}

}
