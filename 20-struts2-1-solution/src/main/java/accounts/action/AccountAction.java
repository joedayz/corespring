package accounts.action;

import java.util.List;


import accounts.model.Account;
import accounts.service.AccountManager;

import com.opensymphony.xwork2.ActionSupport;

/**
 * A Struts 2 action that handles a user request to view & edit accounts. Delegates
 * to the application layer to return all accounts.
 */
public class AccountAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3109084436619951621L;
	protected AccountManager accountManager;

	/**
	 * Creates a new account action
	 * 
	 * @param accountManager
	 *            the account manager service needed to load account summaries
	 *            from the database
	 */
	public AccountAction(AccountManager accountManager) {
		this.accountManager = accountManager;
	}

	protected List<Account> accounts;
	
	public List<Account> getAccounts() {
		return accounts;
	}

	public String list() {
		accounts = accountManager.getAllAccounts();
		return SUCCESS;
	}

	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	protected Account account;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String edit() {
		if (id != null) {
			account = accountManager.getAccount(id);
		}
		
		return INPUT;
	}

	public static final String REDIRECT = "redirect";

	public String save() {
		accountManager.update(account);
		return SUCCESS;
	}
}
