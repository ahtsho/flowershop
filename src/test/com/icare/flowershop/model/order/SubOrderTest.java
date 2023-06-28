package test.com.icare.flowershop.model.order;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.icare.flowershop.model.order.Item;
import com.icare.flowershop.model.order.SubOrder;

class SubOrderTest {
	private SubOrder suborder;
	
	@BeforeEach
	void setUp() throws Exception {
		List<Item> items = new ArrayList<Item>();
		items.add(new Item(3, "A", 10, 15.50));
		items.add(new Item(3, "A", 7, 12.55));
		items.add(new Item(3, "A", 4, 11.00));
		items.add(new Item(3, "A", 8, 17.75));
		this.suborder = new SubOrder(items);
	}

	@AfterEach
	void tearDown() throws Exception {
		this.suborder = null;
	}

	@Test
	void testComputeTotal() {
		assertEquals(this.suborder.computeTotal(),170.4,0);
	}

}
