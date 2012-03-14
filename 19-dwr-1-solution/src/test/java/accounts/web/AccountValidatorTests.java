package accounts.web;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import accounts.Account;
import accounts.AccountManager;

@RunWith(JUnit4.class)
public class AccountValidatorTests extends TestCase {

	private AccountValidator validator;

	@Before
	public void setUp() throws Exception {
		AccountManager accountManager = new StubAccountManager();
		validator = new AccountValidator(accountManager);
	}

	@Test
	public void testSupportsTrue() {
		assertTrue("Should support an Account class", validator.supports(Account.class));
	}

	@Test
	public void testSupportsFalse() {
		assertFalse("Should not support and AccountValidator class", validator.supports(AccountValidator.class));
	}

	@Test
	public void testNumberInUseBySameAccount() {
		Account account = new Account("123456789", "Keith and Keri Donald");
		account.setEntityId(0L);
		Errors errors = new BeanPropertyBindingResult(account, "account");
		validator.validate(account, errors);
		assertEquals("No errors should be registered", 0, errors.getErrorCount());
	}
	
	@Test
	public void testNumberInUseByAnotherAccount() {
		Account account = new Account("123456001", "Keith and Keri Donald");
		account.setEntityId(0L);
		Errors errors = new BeanPropertyBindingResult(account, "account");
		validator.validate(account, errors);
		assertEquals("One error should be registered", 1, errors.getErrorCount());
		FieldError error = errors.getFieldError("number");
		assertNotNull(error);
		assertEquals("account.number.inuse", error.getCode());
	}
	
	@Test
	public void testNumberDoesNotExist() {
		Account account = new Account("100", "Anything");
		account.setEntityId(0L);
		Errors errors = new BeanPropertyBindingResult(account, "account");
		validator.validate(account, errors);
		assertEquals("No errors should be registered", 0, errors.getErrorCount());
	}

	@Test
	public void testNoNumberProvided() {
		Account account = new Account("", "");
		account.setEntityId(0L);
		Errors errors = new BeanPropertyBindingResult(account, "account");
		validator.validate(account, errors);
		assertEquals("No errors should be registered", 0, errors.getErrorCount());
	}
	
}