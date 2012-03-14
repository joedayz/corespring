package accounts.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import accounts.Account;
import accounts.AccountManager;

/**
 * A Spring MVC @Controller controller handling requests for both the
 * account summary and the account details pages. The accountDetails()
 * method return an account, corresponding to a given entity id. The
 * accountSummary() method returns a list with all accounts.  
 */
@Controller
public class AccountController {

	private AccountManager accountManager;

	@Autowired 
	public AccountController(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	/** The @RequestMapping annotation takes care of setting the URL
	 * this controller will react to.
	 * 
	 * The @ModelAttribute annotation will instruct Spring to put the return value
	 * of this method in the model, using the key 'accounts'.
	 */
	@RequestMapping("/accountSummary.htm")
	public @ModelAttribute("accounts") List<Account> accountSummary() {
		return accountManager.getAllAccounts();
	}
	
	/**
	 * The @RequestMapping annotation takes care of setting the URL
	 * this controller will react to.
	 * 
	 * The @RequestParam annotation will instruct Spring to pull the entityId
	 * parameter from the request and pass it to the method when calling it.
	 * 
	 * The @ModelAttribute annotation will instruct Spring to put the return value
	 * of this method in the model, using the key 'account'.
	 */
	@RequestMapping("/accountDetails.htm")
	public @ModelAttribute("account") Account accountDetails(@RequestParam("entityId") long id){
		return accountManager.getAccount(id);
	}
}
