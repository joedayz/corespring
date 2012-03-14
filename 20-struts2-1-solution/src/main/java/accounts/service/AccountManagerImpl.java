package accounts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import accounts.dao.AccountRepository;
import accounts.model.Account;


@Service("accountManager")
public class AccountManagerImpl implements AccountManager {

	@Autowired
	private AccountRepository accountRepository;
	
	public Account findByCreditCard(String creditCardNumber) {
		return accountRepository.findByCreditCard(creditCardNumber);
	}

	@Override
	public List<Account> getAllAccounts() {
		return accountRepository.getAllAccounts();
	}

	@Override
	public Account getAccount(Long id) {
		return accountRepository.getAccount(id);
	}

	@Override
	public void update(Account account) {
		
		accountRepository.update(account);
	}

}
