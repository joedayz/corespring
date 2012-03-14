package accounts.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import accounts.Account;
import accounts.AccountManager;

/**
 * A Spring MVC form controller that handles the updates of account details. 
 * Delegates to the application layer to update the account details.
 */
@Controller
@SessionAttributes("account")
public class EditAccountController {

	private AccountManager accountManager;
	
	// TODO 04: Create a validator field and initialize with an instance of AccountValidator in the constructor
	
	@Autowired 
	public EditAccountController(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	// TODO 01: Create a method to handle the initial "GET" request 

	// TODO 03: Create a method to handle the form "POST"

	// TODO 08: Create a method to add required fields to the DataBinder
}
