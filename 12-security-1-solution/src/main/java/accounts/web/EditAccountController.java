package accounts.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import accounts.Account;
import accounts.AccountManager;

/**
 * A Spring MVC form controller that handles the updates of account details. 
 * Delegates to the application layer to update the account details.
 */
@Controller
@RequestMapping("/editAccount.htm")
@SessionAttributes("account")
public class EditAccountController {

	private AccountManager accountManager;
	
	private AccountValidator accountValidator;
	
	@Autowired 
	public EditAccountController(AccountManager accountManager) {
		this.accountManager = accountManager;
		accountValidator = new AccountValidator(accountManager);
	}

	/**
	 * A method annotated with @InitBinder is used to customize the DataBinder 
	 * mechanism used behind the scenes to bind instance of Account to and from
	 * HTTP request values. In this case add "number" and "name" as required
	 * fields. We could have registered PropertyEditors to convert special
	 * String values such as currency amounts, dates, etc.    
	 * 
	 * @param binder the binder instance to make customizations to.
	 */
	@InitBinder 
	public void initBinder(WebDataBinder binder) {
		binder.setRequiredFields(new String[]{"number", "name"});
	}
	
	/** 
	 * editAccountForm is invoked on the initial HTTP GET request to set up 
	 * the form for editing. This is indicated by the @RequestMapping
	 * annotations on the method and on the class.
	 * 
	 * @param id the id of the account to be edited. Spring MVC extracts the
	 * 		parameter from the request given the @RequestParam annotation.  
	 * @return the Account to be edited annotated. Given the @ModelAttribute 
	 * 		annotation Spring MVC will place the returned value in the model.  
	 */
	@RequestMapping(method=RequestMethod.GET)
	public @ModelAttribute("account") Account setupForm(@RequestParam("entityId") Long id) {
		return accountManager.getAccount(id);
	}

	/**
	 * onSubmit is invoked when the user presses the Submit button to
	 * send the edited Account data with an HTTP POST.  This is indicated 
	 * by the @RequestMapping annotations on the method and on the class.
	 * 
	 * @param account the pre-populated account. The @ModelAttribute allows 
	 * 		the method to get a hold of the object holding the data entered 
	 * 		into the form.
	 * @param result holds the results from binding the HTTP request to the 
	 * 		preceding model (form) object. 
	 * @param status allows the method to request session attributes to be cleaned.  
	 * @return The logical view name to use to display result from the submit.
	 */
	@RequestMapping(method=RequestMethod.POST)
	public String onSubmit(@ModelAttribute("account") Account account, BindingResult result, SessionStatus status) {
		accountValidator.validate(account, result);
		if (result.hasErrors()) {
			return "editAccount";
		}
		accountManager.update(account);
		status.setComplete();
		return "redirect:/accounts/accountDetails.htm?entityId=" + account.getEntityId();
	}
}
