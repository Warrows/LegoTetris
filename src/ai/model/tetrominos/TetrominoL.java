package ai.model.tetrominos;

import ai.model.Tetromino;
import ai.model.TetrominoType;

public class TetrominoL extends Tetromino
{
	public TetrominoL()
	{
		super();
		type = TetrominoType.L;
		representation = new boolean[][]{{true,true,true},{false,false,true}};
	}

	public TetrominoL(Tetromino tetromino)
	{
		super(tetromino);
	}
}
