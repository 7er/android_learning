package com.androidgames.mrnom.test;

import com.androidgames.mrnom.Snake;

import junit.framework.TestCase;

public class SnakeTest extends TestCase {
	Snake snake;
	public SnakeTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		snake = new Snake();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testExistence() {
		
		assertNotNull(snake);
		
	}

}
