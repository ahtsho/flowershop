package test.com.icare.flowershop.model.order;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.icare.flowershop.model.order.Item;
import com.icare.flowershop.model.order.Order;
import com.icare.flowershop.model.order.SubOrder;

class OrderTest {
	private Order order;

	@BeforeEach
	void setUp() throws Exception {
		List<SubOrder> suborders = new ArrayList<SubOrder>();
		List<Item> itemsA = new ArrayList<Item>();
		itemsA.add(new Item(1, "A", 10, 15.50));
		itemsA.add(new Item(2, "A", 7, 12.55));
		suborders.add(new SubOrder(itemsA));

		List<Item> itemsB = new ArrayList<Item>();
		itemsB.add(new Item(3, "B", 12, 15.50));
		itemsB.add(new Item(4, "B", 4, 12.55));
		itemsB.add(new Item(6, "B", 2, 8.30));
		suborders.add(new SubOrder(itemsB));

		this.order = new Order(suborders);

	}

	@AfterEach
	void tearDown() throws Exception {
		this.order = null;
	}

	@Test
	void testgetSuborderByItemCode() {
		SubOrder aSubOrder = this.order.getSuborderByItemCode("A");
		assertSame(aSubOrder.getCode(), "A");
		assertEquals(aSubOrder.getSubtotal(), 40.60, 0);
	}

	@Test
	void testComputeTotal() {
		assertEquals(this.order.getTotal(), 0, 0);
		double total = this.order.computeTotal();
		assertEquals(total, 187.10, 0);
	}

}
