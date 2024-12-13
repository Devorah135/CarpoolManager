package carpoolTestCode;
import code.Cost2;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestCost2 {
	private Cost2 cost;
	@BeforeEach
	void setUp() throws Exception {
		Cost2.resetCostCounter();
		cost = new Cost2("Toll", 3.01);
	}

	@Test
	void TestCannotSetCostLessThan0Dollars() {
		assertThrows(IllegalArgumentException.class, () -> cost.setCost(-3.01));
	}
	
	@Test
	void TestCostIDIncrementsBy1EachObjectCreated() {
		assertEquals(1, cost.getCostID());
	}
}
