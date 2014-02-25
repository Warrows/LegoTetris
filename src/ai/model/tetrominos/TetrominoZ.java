package ai.model.tetrominos;

import ai.model.Tetromino;
import ai.model.TetrominoType;

public class TetrominoZ extends Tetromino
{
	public TetrominoZ()
	{
		super();
		type = TetrominoType.Z;
		representation = new boolean[][]{{true,true,false},{false,true,true}};
	}

	public TetrominoZ(Tetromino tetromino)
	{
		super(tetromino);
	}
}
