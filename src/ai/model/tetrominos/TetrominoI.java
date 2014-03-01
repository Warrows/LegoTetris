package ai.model.tetrominos;

import ai.model.Grid;
import ai.model.Tetromino;
import ai.model.TetrominoType;

public class TetrominoI extends Tetromino
{
	public TetrominoI()
	{
		super();
		type = TetrominoType.I;
		representation = new boolean[][]
		{
		{ true, true, true, true } };
	}

	public TetrominoI(Tetromino tetromino)
	{
		super(tetromino);
	}

	@Override
	public boolean rotate(Grid grid)
	{
		if (rotation % 2 == 1)
		{
			representation = new boolean[][]
			{
			{ true },
			{ true },
			{ true },
			{ true } };
		} else
		{
			representation = new boolean[][]
			{
			{ true },
			{ true },
			{ true },
			{ true } };
		}
		return wellPlaced(grid);
	}
}
