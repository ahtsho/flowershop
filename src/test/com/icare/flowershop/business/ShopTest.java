package test.com.icare.flowershop.business;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.icare.flowershop.business.ProductSupplier;
import com.icare.flowershop.business.Shop;
import com.icare.flowershop.model.order.Order;
import com.icare.flowershop.model.order.PurchaseRequest;
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
	
	@Test
	void testbundleSellSameProduct() {
		List<PurchaseRequest> orders = new ArrayList<PurchaseRequest>();
		orders.add(new PurchaseRequest(10,"R12"));
		orders.add(new PurchaseRequest(9,"R12"));
		orders.add(new PurchaseRequest(8,"R12"));
		orders.add(new PurchaseRequest(7,"R12"));
		orders.add(new PurchaseRequest(6,"R12"));
		orders.add(new PurchaseRequest(5,"R12"));
		Order placedOrder = shop.bundleSell(orders);
		assertEquals(placedOrder.getSuborders().get(0).computeTotal(),12.99,0);
		assertEquals(placedOrder.getSuborders().get(1).computeTotal(),-1,0);
		assertEquals(placedOrder.getSuborders().get(2).computeTotal(),-1,0);
		assertEquals(placedOrder.getSuborders().get(3).computeTotal(),-1,0);
		assertEquals(placedOrder.getSuborders().get(4).computeTotal(),-1,0);
		assertEquals(placedOrder.getSuborders().get(5).computeTotal(),6.99,0);
		
	}

	


}
