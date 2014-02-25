package ai.model.tetrominos;

import ai.model.Tetromino;
import ai.model.TetrominoType;

public class TetrominoT extends Tetromino
{
	public TetrominoT()
	{
		super();
		type = TetrominoType.T;
		representation = new boolean[][]{{false,true,false},{true,true,true}};
	}

	public TetrominoT(Tetromino tetromino)
	{
		super(tetromino);
	}
}
