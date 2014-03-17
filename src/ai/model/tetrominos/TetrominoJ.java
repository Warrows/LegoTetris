package ai.model.tetrominos;

import ai.model.Grid;
import ai.model.Tetromino;
import ai.model.TetrominoType;

public class TetrominoJ extends Tetromino
{
	public TetrominoJ()
	{
		super();
		type = TetrominoType.J;
		representation = new boolean[][]
		{
		{ false, false, true },
		{ true, true, true } };
	}

	public TetrominoJ(Tetromino tetromino)
	{
		super(tetromino);
	}

	@Override
	public boolean rotate(Grid grid)
	{
		switch (rotation % 4)
		{
		case 0:
			representation = new boolean[][]
			{
			{ false, false, true },
			{ true, true, true } };
			break;
		case 1:
			representation = new boolean[][]
			{
			{ true, true },
			{ false, true },
			{ false, true } };
			break;
		case 2:
			representation = new boolean[][]
			{
			{ true, true, true },
			{ true, false, false } };
			break;
		case 3:
			representation = new boolean[][]
			{
			{ true, false },
			{ true, false },
			{ true, true } };
			break;
		}
		return wellPlaced(grid);
	}

	@Override
	public int getHeigt()
	{
		if (rotation % 2 == 0)
			return 3;
		return 2;
	}
}
