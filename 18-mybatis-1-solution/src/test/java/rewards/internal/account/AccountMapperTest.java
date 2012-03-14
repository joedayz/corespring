package rewards.internal.account;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:rewards/system-test-config.xml"})
public class AccountMapperTest {

	@Autowired
	private AccountMapper accountMapper;
	
	@Test
	public void findByCreditCard(){
		
		Account account = accountMapper.findByCreditCard("1234123412341234");
		assertNotNull(account);
		
	}
}
