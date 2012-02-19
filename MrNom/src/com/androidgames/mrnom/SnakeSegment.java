package com.androidgames.mrnom;


public class SnakeSegment implements ISnakeSegment {
	private int x;
	private int y;
	private ISnakeSegment next = null;

	public SnakeSegment(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Object otherObject) {
		SnakeSegment other = (SnakeSegment) otherObject;
		boolean temp = this.x == other.x && this.y == other.y; 
		if (this.next == null) {
			return temp && this.next == other.next;
		}
		return temp && other.next != null && this.next.equals(other.next);
	}
	
	/* (non-Javadoc)
	 * @see com.androidgames.mrnom.ISnakeSegment#setNext(com.androidgames.mrnom.ISnakeSegment)
	 */
	@Override
	public void setNext(ISnakeSegment segment) {
		next = segment;
	}

}
