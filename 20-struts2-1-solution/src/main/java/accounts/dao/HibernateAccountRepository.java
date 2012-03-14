package accounts.dao;

import java.util.List;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import accounts.model.Account;

@Repository("accountRepository")
public class HibernateAccountRepository implements AccountRepository {
	
	private HibernateTemplate hibernateTemplate;

	/**
	 * Creates an new hibernate-based account repository.
	 * @param sessionFactory the Hibernate session factory required to obtain sessions
	 */
	@Autowired
	public HibernateAccountRepository(SessionFactory sessionFactory) {
		this.hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public Account findByCreditCard(String creditCardNumber) {
		
		List<Account> accounts = hibernateTemplate.find("from Account a  where a.creditCardNumber = ?", creditCardNumber);
		if (accounts.isEmpty()) return null;
		return accounts.get(0);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> getAllAccounts() {

		List<Account> accounts = hibernateTemplate.find("from Account a ");
		return accounts;
	}

	@Override
	public Account getAccount(Long id) {
		return (Account) hibernateTemplate.get(Account.class, id);
	}

	@Override
	public void update(Account account) {
		hibernateTemplate.saveOrUpdate(account);
	}

}
