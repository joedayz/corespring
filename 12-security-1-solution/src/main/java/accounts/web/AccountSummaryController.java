package accounts.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import accounts.Account;
import accounts.AccountManager;

/**
 * A Spring MVC controller that handles a user request to view all accounts. Delegates to the application layer to
 * return all accounts.
 */
@Controller
public class AccountSummaryController {

	private AccountManager accountManager;

	@Autowired
	public AccountSummaryController(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	@RequestMapping("/accountSummary.htm")
	public @ModelAttribute("accounts") List<Account> accountSummary() {
		return accountManager.getAllAccounts();
	}

}
