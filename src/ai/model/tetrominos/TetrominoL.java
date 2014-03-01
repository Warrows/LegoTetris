package ai.model.tetrominos;

import ai.model.Grid;
import ai.model.Tetromino;
import ai.model.TetrominoType;

public class TetrominoL extends Tetromino
{
	public TetrominoL()
	{
		super();
		type = TetrominoType.L;
		representation = new boolean[][]{{true,true,true},{false,false,true}};
	}
	
	public TetrominoL(Tetromino tetromino)
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
			{ true, true, true },
			{ false, false, true } };
			break;
		case 1:
			representation = new boolean[][]
			{
			{ true, true },
			{ true, false },
			{ true, false } };
			break;
		case 2:
			representation = new boolean[][]
			{
			{ true, false, false },
			{ true, true, true } };
			break;
		case 3:
			representation = new boolean[][]
			{
			{ false, true },
			{ false, true },
			{ true, true } };
			break;
		}
		return wellPlaced(grid);
	}
}
