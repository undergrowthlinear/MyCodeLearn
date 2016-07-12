/*   
* Copyright (c) 2012 by XUANWU INFORMATION TECHNOLOGY CO. 
*             All rights reserved                         
*/
package mycodelearn.undergrowth.book.mw.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mycodelearn.undergrowth.book.mw.JunitSuite;

/**
* Description: TODO(这里用一句话描述这个类的作用)
* @author <a href="undergrowth@126.com">undergrowth</a>
* @date 2016年7月9日
* @version 1.0.0
*/
public class JunitSuiteTest {

	private JunitSuite suite;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		suite=new JunitSuite();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link mycodelearn.undergrowth.book.mw.JunitSuite#sayJunitSuite()}.
	 */
	@Test
	public void testSayJunitSuite() {
		suite.setName("under");
		assertEquals("sayJunitSuite under", suite.sayJunitSuite());
	}

	/**
	 * Test method for {@link mycodelearn.undergrowth.book.mw.JunitSuite#sayJunitSuiteWhat()}.
	 */
	@Test
	public void testSayJunitSuiteWhat() {
		suite.setName("under");
		assertEquals("sayJunitSuiteWhat under", suite.sayJunitSuiteWhat());
	}

	/**
	 * Test method for {@link mycodelearn.undergrowth.book.mw.JunitSuite#getName()}.
	 */
	@Test
	public void testGetName() {
		suite.setName("under");
		assertEquals("under", suite.getName());
	}

	/**
	 * Test method for {@link mycodelearn.undergrowth.book.mw.JunitSuite#setName(java.lang.String)}.
	 */
	@Test
	public void testSetName() {
		suite.setName("under");
		assertEquals("under", suite.getName());
	}

}
