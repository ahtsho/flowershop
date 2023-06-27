package flowershop;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ShopTest {
	Shop shop;
	@Before
	public void setUp() throws Exception {
		shop = new Shop();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBundleSell() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetReverseSortedBundlesByCode() {
		List<Bundle> sortedBundles = shop.getReverseSortedBundlesByCode("R12");
		List<Integer> bundles = sortedBundles.stream().map(b->b.)
		//Arrays.asList(new Bundle(5, 6.99), new Bundle(10, 12.99)))
		
		assertEquals(,)
	}

}
