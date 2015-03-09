package com.saurabh.main;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SumCheckerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDisplayIndices() {
		int[] numberList=new int[]{3,2,1,4};
		SumChecker sumChecker=new SumChecker();
		String indexList=sumChecker.displayIndices(numberList);
		String expected="(0,1,2,3)";
		assertEquals("The results are equal", expected, indexList);
	}
	
	@Test
	public void testDisplayIndicesWithSevenNumbers() {
		int[] numberList=new int[]{3,4,7,1,2,9,8};
		SumChecker sumChecker=new SumChecker();
		String indexList=sumChecker.displayIndices(numberList);
		String expected="(0,4,1,3)(2,4,3,6)(0,2,3,5)(0,2,4,6)(3,5,4,6)(0,6,1,2)(0,6,4,5)(1,2,4,5)(0,5,1,6)";
		assertEquals("The results are equal", expected, indexList);
	}

}
