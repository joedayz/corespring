package accounts.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import accounts.Account;
import accounts.AccountManager;

/**
 * A Spring MVC controller that handles a user request to view the details of an account. Delegates to the application
 * layer to return account details.
 */
@Controller
public class AccountDetailsController {

	private AccountManager accountManager;

	@Autowired
	public AccountDetailsController(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	@RequestMapping("/accountDetails.htm")
	public @ModelAttribute("account") Account accountDetails(@RequestParam("entityId") long id){
		return accountManager.getAccount(id);
	}
}
