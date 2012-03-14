package rewards.internal.restaurant;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:rewards/system-test-config.xml"})
public class RestaurantMapperTest {
	
	@Autowired
	private RestaurantMapper restaurantRepository;
	
	@Test
	public void findByMerchantNumber(){
		
		Restaurant restaurant = restaurantRepository.findByMerchantNumber("1234567890");
		assertNotNull(restaurant);
	}

}
