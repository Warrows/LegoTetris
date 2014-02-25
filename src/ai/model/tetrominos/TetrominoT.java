package ai.model.tetrominos;

import ai.model.Tetromino;
import ai.model.TetrominoType;

public class TetrominoT extends Tetromino
{
	public TetrominoT()
	{
		super();
		type = TetrominoType.T;
		representation = new boolean[][]{{false,true},{true,true},{false,true}};
		colOffset = 3;
	}

	public TetrominoT(Tetromino tetromino)
	{
		super(tetromino);
	}
}
