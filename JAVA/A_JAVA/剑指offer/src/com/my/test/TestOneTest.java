package com.my.test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class TestOneTest {


	@Test
	public void testadd() {
		
		int a=new TestOne().add(3, 5);
 		assertEquals(8, a);
	    assertThat(a, is(8));
	    assertThat("not greater than 8",a, allOf(greaterThan(8)));
	    
	}

}
