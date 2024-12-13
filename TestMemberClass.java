package carpoolTestCode;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import code.Cost2;
import code.Member;

class TestMemberClass {
	private Member member;

	@BeforeEach
	void setUp() throws Exception {
		member = new Member();
	}

	@Test
	void TestWouldNotTakeLessThan0Children() {
		assertThrows(IllegalArgumentException.class, () -> member.addChildToNumChildren(-2));
	}
	
	@Test
	void TestCostTotalCountsCorrectNoDeletions() {
		member.addCost(new Cost2("Toll",2.01));
		member.addCost(new Cost2("Toll",3.01));
		member.addCost(new Cost2("Toll",4.01));
		assertEquals(9.03, member.getCostTotal());
	}
	
	@Test
	void TestCostTotalIs0AfterReseting() {
		member.addCost(new Cost2("Toll",2.01));
		member.addCost(new Cost2("Toll",3.01));
		member.addCost(new Cost2("Toll",4.01));
		member.resetCost();
		assertEquals(0, member.getCostTotal());
	}
	
	@Test
	void TestGetsCorrectCostByID() {
		member.addCost(new Cost2("Toll",2.01));
		member.addCost(new Cost2("Toll",4.01));
		Cost2 cost3 = new Cost2("Toll",3.01);
		member.addCost(cost3);
		assertSame(cost3, member.getCostByID(3));
	}
	
	@Test
	void TestMethodReturnsNullIfThereIsNoSuchCostByID() {
		member.addCost(new Cost2("Toll",2.01));
		member.addCost(new Cost2("Toll",4.01));
		Cost2 cost2 = new Cost2("Toll",3.01);
		member.addCost(cost2);
		assertSame(null, member.getCostByID(7));
	}
	
	@Test
	void TestNumOfChildrenWhenAdding() {
		member.addChildToNumChildren(4);
		assertEquals(4, member.getNumChildren());
	}
	
	@Test
	void TestNumOfChildrenWhenRemoving() {
		member.addChildToNumChildren(5);
		member.removeChildrenFromNumChildren(3);;
		assertEquals(2, member.getNumChildren());
	}
	
	@Test
	void TestCannotRemoveMoreChildrenThanAlreadyHave() {
		member.addChildToNumChildren(3);
		assertThrows(IllegalArgumentException.class, () -> member.removeChildrenFromNumChildren(5));
		
	}

}
