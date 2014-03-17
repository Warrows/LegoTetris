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
		switch (rotation % 2)
		{
		case 0:
			representation = new boolean[][]
			{
			{ true, false },
			{ true, true },
			{ false, true } };
			break;
		case 1:
			representation = new boolean[][]
			{
			{ false, true, true },
			{ true, true, false } };
			break;
		}
		return wellPlaced(grid);
	}

	@Override
	public int getHeigt()
	{
		if (rotation % 2 == 0)
			return 2;
		return 3;
	}
}
