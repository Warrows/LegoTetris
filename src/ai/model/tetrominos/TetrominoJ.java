package ai.model.tetrominos;

import ai.model.Tetromino;
import ai.model.TetrominoType;

public class TetrominoJ extends Tetromino
{
	public TetrominoJ()
	{
		super();
		type = TetrominoType.J;
		representation = new boolean[][]{{false,true},{false,true},{true,true}};
	}

	public TetrominoJ(Tetromino tetromino)
	{
		super(tetromino);
	}
}