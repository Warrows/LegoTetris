package ai.model;

import java.util.HashSet;
import java.util.Set;

public abstract class Tetromino
{
	protected TetrominoType type;
	protected int rowOffset;
	protected int colOffset;
	protected boolean[][] representation;
	protected int rotation;

	public Tetromino()
	{
		rowOffset = 0;
		colOffset = 4;
		rotation = 0;
	}

	public Tetromino(Tetromino tetromino)
	{
		type = tetromino.type;
		rowOffset = tetromino.rowOffset;
		colOffset = tetromino.colOffset;
		representation = tetromino.representation.clone();
	}

	public boolean turnClockwise(Grid grid)
	{
		rotation ++;
		if (rotate(grid))
			return true;
		turnCounterClockwise(grid);
		return false;
	}
	/*{
		
		final int M = representation.length;
		final int N = representation[0].length;
		boolean[][] ret = new boolean[N][M];
		for (int r = 0; r < M; r++)
		{
			for (int c = 0; c < N; c++)
			{
				if (grid.isOccupied(M - 1 - r + colOffset, c + rowOffset))
					return false;
				ret[c][M - 1 - r] = representation[r][c];
			}
		}
		representation = ret;
		return true;
	}*/
	
	protected abstract boolean rotate(Grid grid);

	protected void turnCounterClockwise(Grid grid)
	{
		rotation --;
	}
	/*
	{
		final int M = representation.length;
		final int N = representation[0].length;
		boolean[][] ret = new boolean[N][M];
		for (int r = 0; r < M; r++)
		{
			for (int c = 0; c < N; c++)
			{
				if (grid.isOccupied(r + colOffset, M - 1 - c + rowOffset))
					return false;
				ret[M - 1 - c][r] = representation[r][c];
			}
		}
		representation = ret;
		return true;
	}*/

	/**
	 * This does not check anything, only change data
	 */
	public void fall()
	{
		rowOffset += 1;
	}

	/**
	 * This does not check anything, only change data
	 */
	public void moveRight()
	{
		colOffset += 1;
	}

	/**
	 * This does not check anything, only change data
	 */
	public void moveLeft()
	{
		colOffset -= 1;
	}

	protected boolean wellPlaced(Grid grid)
	{
		for (int r = 0; r < representation.length; r++)
		{
			for (int c = 0; c < representation[0].length; c++)
			{
				if (grid.isOccupied(c + colOffset,r + rowOffset))
				{
					return false;
				}
			}
		}
		return true;
	}

	public Set<Cell> getCells(Grid grid)
	{
		HashSet<Cell> ret = new HashSet<Cell>();
		for (int row = 0; row < representation[0].length; row++)
		{
			for (int col = 0; col < representation.length; col++)
				if (representation[col][row]
						&& isAcceptable(col + colOffset, row + rowOffset))
				{
					ret.add(grid.getCell(rowOffset + row, colOffset + col));
				}
		}
		return ret;
	}

	private boolean isAcceptable(int col, int row)
	{
		if (col < 0)
			return false;
		if (col > Grid.WIDTH)
			return false;
		if (row < 0)
			return false;
		if (row > Grid.HEIGHT)
			return false;
		return true;
	}

	public boolean isOut()
	{
		for (int row = 0; row < representation[0].length; row++)
			for (int col = 0; col < representation.length; col++)
			{
				if (!isAcceptable(col + colOffset, row + rowOffset))
					if (representation[col][row])
						return true;
			}
		return false;
	}

	public String toString()
	{
		String s = "";
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)

				if (representation[i][j])
					s += "X";
				else
					s += ".";
			s += "\n";
		}
		return s;
	}

	public int getHeightPosition()
	{
		return Grid.HEIGHT - rowOffset - getHeigt();
	}

	public TetrominoType getType()
	{
		return type;
	}

	public abstract int getHeigt();
}
