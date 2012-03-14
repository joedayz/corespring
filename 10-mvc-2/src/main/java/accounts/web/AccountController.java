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
	 * this controller will react to.
	 * 
	 * The @RequestParam annotation will instruct Spring to pull the entityId
	 * parameter from the request and pass it to the method when calling it.
	 * 
	 * The @ModelAttribute annotation will instruct Spring to put the return value
	 * of this method in the model, using the key 'account'.
	 */
	// TODO 3: Refactor this method to return an Account object instead of a ModelAndView, and use a @RequestParam instead of the HttpServletRequest
	@RequestMapping("/accountDetails.htm")
	public ModelAndView accountDetails(HttpServletRequest request) 
	throws ServletRequestBindingException {
		long id = ServletRequestUtils.getRequiredLongParameter(request, "entityId");
		ModelAndView mav = new ModelAndView("accountDetails");
		mav.addObject("account", accountManager.getAccount(id));
		return mav;
	}
	
	/** The @RequestMapping annotation takes care of setting the URL
	 * this controller will react to.
	 * 
	 * The @ModelAttribut annotation will instruct Spring to put the return value
	 * of this method in the model, using the key 'accounts'.
	 */
	// TODO 1: Refactor this method to return a List of Account objects instead of a ModelAndView	
	@RequestMapping("/accountSummary.htm")
	public ModelAndView accountSummary() {
		List<Account> accounts = accountManager.getAllAccounts();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("accountSummary");
		mav.addObject("accounts", accounts);
		return mav;
	}
}
