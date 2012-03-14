package accounts.web;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import accounts.Account;
import accounts.AccountManager;

/**
 * Instance validator for the {@link Account} class. 
 */
public class AccountValidator implements Validator {
	
	// TODO 09: add an AccountManager field and initialize it through a constructor argument.
	
	public AccountValidator(AccountManager accountManager) {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return Account.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		// TODO 10: Cast the target to an Account and verify the account number 
		//         is not already in use by another account.
	}

}
