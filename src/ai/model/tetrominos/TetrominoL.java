package ai.model.tetrominos;

import ai.model.Tetromino;
import ai.model.TetrominoType;

public class TetrominoL extends Tetromino
{
	public TetrominoL()
	{
		super();
		type = TetrominoType.L;
		representation = new boolean[][]{{true,false},{true,false},{true,true}};
	}

	public TetrominoL(Tetromino tetromino)
	{
		super(tetromino);
	}
}
