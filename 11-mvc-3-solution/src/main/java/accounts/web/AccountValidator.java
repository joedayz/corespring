package accounts.web;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import accounts.Account;
import accounts.AccountManager;

/**
 * Instance validator for the {@link Account} class. 
 */
public class AccountValidator implements Validator {
	
	private AccountManager accountManager;

	public AccountValidator(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	@SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
		return Account.class.isAssignableFrom(clazz);
	}

	/**
	 * Performs validations for the given account. The main validation
	 * is against the account number, which can be changed but not to
	 * a number that's already in use by another account.
	 */
	public void validate(Object target, Errors errors) {
		Account account = (Account) target;
		if (StringUtils.isNotEmpty(account.getNumber())) {
			Account existingAccount = accountManager.findAccount(account.getNumber());
			if (existingAccount != null) {
				if (! account.getEntityId().equals(existingAccount.getEntityId())) {
					errors.rejectValue("number", "account.number.inuse");
				}
			}
		}
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "errors.required", new String[]{"name"});
	}

}
