package com.tairovich;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Created by tairovich_jr on Sep 15, 2020
 */
//@Disabled
class StringTest {
	
	@BeforeAll
	static void beforeAll() {
		System.out.println("Initialize connection to database");
	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("Close all db connections");
	}
	
	@BeforeEach
	void setUp(TestInfo info) {
		System.out.println("Initialize test data for each test for " + info.getDisplayName());
	}
	
	@AfterEach
	void cleanUp(TestInfo info) {
		System.out.println("Clean up test data after each test for " + info.getDisplayName());
	}
	
	@Test
	void lengthBasic() {
		int actualLength = "ABCD".length();
		int expectedLength = 4;
		assertEquals(expectedLength, actualLength);	
	}
	
	@Test
	//@Display name is used to give a custom name for the test case
	@DisplayName("When length is null, throw an exception")
	void lengthException() {
		//If str has some value instead of null, this test would fail, because asserThrows expects some exceptions
		String str = null;
		
		assertThrows(NullPointerException.class, 
				() -> {
					int actualLength = str.length();	
				}
				);
	}
	
	@Test
	void toUpperCase_basic() {
		String str = "abcd";
		String result = str.toUpperCase();
		assertNotNull(result);
		assertEquals("ABCD", result);
	}
	
	@Test
	@RepeatedTest(value = 10)
	void contains_basic() {
		String str = "abcdedf";
		boolean result  = str.contains("fd");
	//	assertEquals(false, result);
		assertFalse(result);
	}
	
	@Test
	void mathMin() {
		int actualMin = Math.min(8, 10);
		assertEquals(8, actualMin);
	}
	
	@Test
	@Disabled
	void mathMax() {
		int actualMax = Math.max(8, 10);
		assertEquals(10, actualMax);
	}
	
	@Test
	void split_basic() {
		String str = "abc def ghi";
		String[] actualResult = str.split(" ");
		String[] expectedResult = new String[] {"abc","def","ghi"};
		assertArrayEquals(expectedResult, actualResult);;
	}
	
	
	@Test
	void length_greater_than_zero() {
		assertTrue("ABCD".length()>0);
		assertTrue("ABC".length()>0);
		assertTrue("AB".length()>0);
		assertTrue("A".length()>0);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"ABCD","DSG","dsgasdf","hello","A","AA"})
	void length_greater_than_zero_parameterized_test(String str) {
		assertTrue(str.length()>0);
	}
	
	
	@ParameterizedTest(name = "{0} toUpperCased is {1}")
	@CsvSource(value = {"abcd, ABCD","abc, ABC","'',''","abcdefg,ABCDEFG"})
	void upperCase(String word, String capitalized) {
		
		assertEquals(capitalized, word.toUpperCase());
	}
	
	@ParameterizedTest(name = "{0} length is {1}")
	@CsvSource(value = {"abcd, 4","abc, 3","'',0","abcdefg,7"})
	void length(String word, int expectedLength) {
		
		assertEquals(expectedLength, word.length());
	}
	
	
	@Test
	void performanceTest() {
		assertTimeout(Duration.ofSeconds(5),
				
				()->{
					for (int i = 0; i < 1000000; i++) {
						int j = i;
						System.out.println(j);
					}
				}
				
				);
	}
	
	
	String str ;
	
	@Nested
	@DisplayName("For an empty String")
	class EmptyStringTests{
		
		@BeforeEach
		void setToEmpty() {
			str = "";
		}
		
		@Test
		@DisplayName("Length should be zero")
		public void legnthIsZero() {
			assertEquals(0, str.length());
		}
		
		@Test
		@DisplayName("upper case is empty")
		public void upperCaseIsEmpty() {
			assertEquals("", str.toUpperCase());
		}
	}
	
	
	
	
}









