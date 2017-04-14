package ecx.mpopijac.restaurants;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EcxRestaurantsApplicationTests {

	@Autowired
	private InitialData initialData;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@Transactional
	public void contextLoads() {
		initialData.loadInitialData();
	}

}
