package ai.input;

import ai.model.Grid;

public interface TetrisDataInput
{
	public Grid getTetrisData();
	public boolean isGameOver();
}
