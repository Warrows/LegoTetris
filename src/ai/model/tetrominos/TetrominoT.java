package ai.model.tetrominos;

import ai.model.Grid;
import ai.model.Tetromino;
import ai.model.TetrominoType;

public class TetrominoT extends Tetromino
{
	public TetrominoT()
	{
		super();
		type = TetrominoType.T;
		representation = new boolean[][]
		{
		{ true, false },
		{ true, true },
		{ true, false } };
	}

	public TetrominoT(Tetromino tetromino)
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
			{ true, false },
			{ true, true },
			{ true, false } };
			break;
		case 1:
			representation = new boolean[][]
			{
			{ false, true, false },
			{ true, true, true } };
			break;
		case 2:
			representation = new boolean[][]
			{
			{ false, true },
			{ true, true },
			{ false, true } };
			break;
		case 3:
			representation = new boolean[][]
			{
			{ true, true, true },
			{ false, true, false } };
			break;
		}
		return wellPlaced(grid);
	}
}
