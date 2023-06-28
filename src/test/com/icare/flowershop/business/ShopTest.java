package test.com.icare.flowershop.business;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.icare.flowershop.business.ProductSupplier;
import com.icare.flowershop.business.Shop;
import com.icare.flowershop.model.product.Flower;
import com.icare.flowershop.model.product.PricedBundle;

class ShopTest {

	private Shop shop;
	@BeforeEach
	void setUp() throws Exception {
		this.shop = new Shop(ProductSupplier.generateDemoProducts());
	}

	@AfterEach
	void tearDown() throws Exception {
		this.shop = null;
	}


	@Test
	void testGetProductByCode() {
		Flower rose = (Flower) this.shop.getProductByCode("R12");
		assertSame(rose.getName(), "Roses");
	}

	@Test
	void testGetReverseSortedBundles() {

		List<PricedBundle> lillyBundles = this.shop.getProductByCode("L09").getBundels();
		// should be 9, 6, 3
		List<PricedBundle> revOrderedBundles = this.shop.getReverseSortedBundles(lillyBundles);
		assertSame(revOrderedBundles.get(0).getBundle(),9);
		assertSame(revOrderedBundles.get(1).getBundle(),6);
		assertSame(revOrderedBundles.get(2).getBundle(),3);
	}

	


}
