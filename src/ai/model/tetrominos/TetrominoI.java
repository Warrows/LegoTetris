package ai.model.tetrominos;

import ai.model.Tetromino;
import ai.model.TetrominoType;

public class TetrominoI extends Tetromino
{
	public TetrominoI()
	{
		super();
		type = TetrominoType.I;
		representation = new boolean[][]{{true,true,true,true}};
		colOffset = 3;
	}

	public TetrominoI(Tetromino tetromino)
	{
		super(tetromino);
	}
}
