package Enums;

import Utils.GamePosition;

public enum Direction {
	Up(0, -8), Down(0, 8), Right(8, 0), Left(-8, 0);

	int x, y;

	private Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public GamePosition asVector() {
		return new GamePosition(x, y);
	}

}
