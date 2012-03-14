package accounts.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	
	/**
	 * Creates a new AccountController with a given account manager.
	 */
	// TODO 6: Enable autowiring of the AccountManager dependency
	public AccountController(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
	
	@RequestMapping("/accountSummary.htm")
	public ModelAndView accountSummary() {
		List<Account> accounts = accountManager.getAllAccounts();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/WEB-INF/views/accountSummary.jsp");
		mav.addObject("accounts", accounts);
		return mav;
	}
	
	// TODO 1: Add an accountDetails method and associated @RequestMapping
}
