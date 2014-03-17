package ai.model.tetrominos;

import ai.model.Grid;
import ai.model.Tetromino;
import ai.model.TetrominoType;

public class TetrominoO extends Tetromino
{
	public TetrominoO()
	{
		super();
		type = TetrominoType.O;
		representation = new boolean[][]{{true,true},{true,true}};
	}

	public TetrominoO(Tetromino tetromino)
	{
		super(tetromino);
	}
	
	@Override
	public boolean rotate(Grid grid)
	{
		return true;
	}

	@Override
	public int getHeigt()
	{
		return 2;
	}
}
