package ai.model.tetrominos;

import ai.model.Tetromino;
import ai.model.TetrominoType;

public class TetrominoS extends Tetromino
{
	public TetrominoS()
	{
		super();
		type = TetrominoType.S;
		representation = new boolean[][]{{false,true,true},{true,true,false}};
	}

	public TetrominoS(Tetromino tetromino)
	{
		super(tetromino);
	}
}
