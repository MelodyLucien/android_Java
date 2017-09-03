package com.my.test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;
import com.my.test.TestTwo;
public class TestTwoTest {

	@Test
	public void testdivide() {
		assertThat(new TestTwo().divide(8, 4),is(2));
	}

}
