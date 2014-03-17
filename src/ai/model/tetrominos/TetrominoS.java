package ai.model.tetrominos;

import ai.model.Grid;
import ai.model.Tetromino;
import ai.model.TetrominoType;

public class TetrominoS extends Tetromino
{
	public TetrominoS()
	{
		super();
		type = TetrominoType.S;
		representation = new boolean[][]
		{
		{ false, true },
		{ true, true },
		{ true, false } };
	}

	public TetrominoS(Tetromino tetromino)
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
			{ false, true },
			{ true, true },
			{ true, false } };
			break;
		case 1:
			representation = new boolean[][]
			{
			{ true, true ,false},
			{ false, true, true}};
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
