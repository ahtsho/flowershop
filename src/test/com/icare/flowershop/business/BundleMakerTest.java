package test.com.icare.flowershop.business;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.icare.flowershop.business.BundleMaker;
import com.icare.flowershop.business.Shop;
import com.icare.flowershop.error.NoSuchBundleException;
import com.icare.flowershop.model.product.Flower;
import com.icare.flowershop.model.product.PricedBundle;
import com.icare.flowershop.model.product.Product;

class BundleMakerTest {
	
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCalculateMinBundlesConfig15Roses() throws NoSuchBundleException {
		BundleMaker bundleMaker = new BundleMaker();
		Product flower = new Flower("Roses", "R12", Arrays.asList(new PricedBundle(5, 6.99), new PricedBundle(10, 12.99)));
		Shop shop = new Shop(Arrays.asList(flower));
		List<PricedBundle> revOrderbundles = shop.getReverseSortedBundles(flower.getBundels());
		int bestRow = bundleMaker.calculateMinBundlesConfig(15,flower,revOrderbundles);
		
		assertEquals(bestRow, 0);
	}
	
	@Test
	void testCalculateMinBundlesConfig13Tulips() throws NoSuchBundleException {
		BundleMaker bundleMaker = new BundleMaker();
		Product flower = new Flower("Tulips", "T58", Arrays.asList(new PricedBundle(3, 5.95), new PricedBundle(5, 9.95), new PricedBundle(9, 16.99)));
		Shop shop = new Shop(Arrays.asList(flower));
		List<PricedBundle> revOrderbundles = shop.getReverseSortedBundles(flower.getBundels());
		int bestRow = bundleMaker.calculateMinBundlesConfig(13,flower,revOrderbundles);
		
		assertEquals(bestRow, 1);
	}

	
}
