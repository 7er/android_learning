package com.androidgames.jvmtest;

import com.androidgames.mrnom.ISnakeSegment;
import com.androidgames.mrnom.NullSnakeSegment;
import com.androidgames.mrnom.Snake;
import com.androidgames.mrnom.SnakeSegment;
import com.androidgames.mrnom.World;

import junit.framework.TestCase;

public class SnakeTest extends TestCase {
	Snake snake;
	public SnakeTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		snake = Snake.createDefault();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testExistence() {		
		assertNotNull(snake);
	}

	public final void testSnakeSegmentEquality() {
		ISnakeSegment expected = new SnakeSegment(2,4);
		ISnakeSegment actual = new SnakeSegment(2,4);
		assertEquals(expected, actual);
	}
	public final void testSnakeSegmentsOfDifferentLenghts() {
		ISnakeSegment expected = new SnakeSegment(2,4);
		expected.setNext(new SnakeSegment(2,5));
		ISnakeSegment actual = new SnakeSegment(2,4);
		assertFalse(expected.equals(actual));
	}
	
	public final void testMultipleSegments() {
		ISnakeSegment expected = new SnakeSegment(2,4);
		expected.setNext(new SnakeSegment(2,5));
		ISnakeSegment actual = new SnakeSegment(2,4);
		actual.setNext(new SnakeSegment(2, 5));
		assertEquals(expected, actual);
		
	}
	
	public final void testAllISnakeSegmentImplementationsCanBeCompared() {
		ISnakeSegment first = new SnakeSegment(0, 0);
		ISnakeSegment second = new NullSnakeSegment();
		assertFalse(second.equals(first));
		assertFalse(first.equals(second));
	}

	public final void testEquality() {
		Snake expected = Snake.createDefault();
		ISnakeSegment newTail = new SnakeSegment(2, 2);
		ISnakeSegment newTail1 = new SnakeSegment(1, 2);
		newTail.setNext(newTail1);
		newTail1.setNext(new SnakeSegment(1, 1));
		Snake actual = new Snake(Snake.Direction.UP, newTail);
		assertEquals(expected, actual);
	}
	
	public final void testMovement() {
		snake.move(null);
		SnakeSegment expectedTail = new SnakeSegment(1, 2);
		SnakeSegment tail1 = new SnakeSegment(1, 1);
		expectedTail.setNext(tail1);
		tail1.setNext(new SnakeSegment(1, 0));
		Snake expected = new Snake(Snake.Direction.UP, expectedTail);
		assertEquals(expected, snake);
	}
	
	public final void testMoveWithWrapAround() {
		World world = new World();
		snake = new Snake(Snake.Direction.LEFT, new SnakeSegment(World.maxX, 0));
		snake.turnRight();
		snake.move(world);
		assertEquals(new Snake(Snake.Direction.UP, new SnakeSegment(World.maxX, World.maxY)), snake);
		snake.turnRight();
		snake.move(world);
		assertEquals(new Snake(Snake.Direction.RIGHT, new SnakeSegment(0, World.maxY)), snake);
		snake.turnRight();
		snake.move(world);
		assertEquals(new Snake(Snake.Direction.DOWN, new SnakeSegment(0, 0)), snake);
		snake.turnRight();
		snake.move(world);
		assertEquals(new Snake(Snake.Direction.LEFT, new SnakeSegment(World.maxX, 0)), snake);
	}
	
	public final void testRoundAndRound() {
		World world = new World();
		snake = new Snake(Snake.Direction.UP, new SnakeSegment(1, 1));
		snake.turnLeft();
		snake.move(world);
		assertEquals(new Snake(Snake.Direction.LEFT, new SnakeSegment(0, 1)), snake);
		snake.turnLeft();
		snake.move(world);
		assertEquals(new Snake(Snake.Direction.DOWN, new SnakeSegment(0, 2)), snake);
		snake.turnLeft();
		snake.move(world);
		assertEquals(new Snake(Snake.Direction.RIGHT, new SnakeSegment(1, 2)), snake);
		snake.turnLeft();
		snake.move(world);
		assertEquals(new Snake(Snake.Direction.UP, new SnakeSegment(1, 1)), snake);	
		
	}
}
