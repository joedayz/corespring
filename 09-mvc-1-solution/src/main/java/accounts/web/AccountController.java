package accounts.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
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
	@Autowired 
	public AccountController(AccountManager accountManager) {
		this.accountManager = accountManager;
	}
	
	
	/**
	 * The @RequestMapping annotation takes care of setting the URL
	 * this controller will react this.
	 */
	@RequestMapping("/accountDetails.htm")
	public ModelAndView accountDetails(HttpServletRequest request) 
	throws ServletRequestBindingException {
		long id = ServletRequestUtils.getRequiredLongParameter(request, "entityId");
		ModelAndView mav = new ModelAndView("accountDetails");
		mav.addObject("account", accountManager.getAccount(id));
		return mav;
	}
	
	@RequestMapping("/accountSummary.htm")
	public ModelAndView accountSummary() {
		List<Account> accounts = accountManager.getAllAccounts();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("accountSummary");
		mav.addObject("accounts", accounts);
		return mav;
	}
}
