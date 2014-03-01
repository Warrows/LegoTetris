package ai.model.tetrominos;

import ai.model.Grid;
import ai.model.Tetromino;
import ai.model.TetrominoType;

public class TetrominoZ extends Tetromino
{
	public TetrominoZ()
	{
		super();
		type = TetrominoType.Z;
		representation = new boolean[][]
		{
		{ true, false },
		{ true, true },
		{ false, true } };
	}

	public TetrominoZ(Tetromino tetromino)
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
					{ true, false },
					{ true, true },
					{ false, true } };
		} else
		{
			representation = new boolean[][]
					{
					{ false, true, true },
					{ true, true,false } };
		}
		return wellPlaced(grid);
	}
}
