package ai.input;

import ai.model.Grid;

public interface TetrisDataInput
{
	public Grid getTetrisData() throws NoInputException;
	public boolean isGameOver();
}
