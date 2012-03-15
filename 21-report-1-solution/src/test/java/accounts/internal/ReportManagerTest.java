package accounts.internal;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import accounts.ReportManager;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:accounts/system-test-config.xml"})
public class ReportManagerTest {

	@Autowired
	private ReportManager reportManager;
	
	@Test
	public void getAllAccounts(){
		
		List<Map<String, Object>> accounts = reportManager.getAllAccounts();
		assertNotNull(accounts);
		
		System.out.println(accounts);
	}
	
}
