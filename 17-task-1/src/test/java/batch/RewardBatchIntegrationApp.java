package batch;
 
import org.springframework.context.support.ClassPathXmlApplicationContext;
 
public class RewardBatchIntegrationApp 
{
    public static void main( String[] args ) throws Exception
    {
    	new ClassPathXmlApplicationContext(new String[]{"batch/batch-config.xml", "rewards/system-test-config.xml"});
    	
    }
}