package test.com.icare.flowershop.view;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.icare.flowershop.business.ProductSupplier;
import com.icare.flowershop.business.Shop;
import com.icare.flowershop.model.order.Order;
import com.icare.flowershop.model.order.PurchaseRequest;
import com.icare.flowershop.view.Console;

class ConsoleTest {

	private Shop shop;
	private List<PurchaseRequest> orderRequest; 
	private Order bundledOrder;
	@BeforeEach
	void setUp() throws Exception {
		this.shop = new Shop(ProductSupplier.generateDemoProducts());
		orderRequest = new ArrayList<PurchaseRequest>();
		orderRequest.add(new PurchaseRequest(10,"R12"));
		orderRequest.add(new PurchaseRequest(9,"R12"));
		orderRequest.add(new PurchaseRequest(8,"R12"));
		orderRequest.add(new PurchaseRequest(7,"R12"));
		orderRequest.add(new PurchaseRequest(6,"R12"));
		orderRequest.add(new PurchaseRequest(5,"R12"));
		bundledOrder = shop.bundleSell(orderRequest);
	}

	@AfterEach
	void tearDown() throws Exception {
		this.shop = null;
	}
	@Test
	void testDisplayBundleBreakdownForEachProduct() {
		Console.displayBundleBreakdownForEachProduct(orderRequest, bundledOrder);
	}

}
