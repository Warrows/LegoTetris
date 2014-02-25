package ai.model;

import java.util.HashSet;
import java.util.Set;

import ai.model.tetrominos.*;
import net.sourceforge.jetris.figures.*;

public abstract class Tetromino
{
	protected TetrominoType type;
	protected int rowOffset;
	protected int colOffset;
	private int height;
	protected boolean[][] representation;

	public Tetromino()
	{
		rowOffset = 0;
		colOffset = 4;
		height = Grid.HEIGHT;
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
		/*if (type == TetrominoType.Z)
			colOffset++;
		if (type == TetrominoType.T)
			return turnCounterClockwise(grid);*/
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
	}

	public boolean turnCounterClockwise(Grid grid)
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
	}

	/**
	 * This does not check anything, only change data
	 */
	public void fall()
	{
		rowOffset += 1;
		height--;
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
		return rowOffset;
		/*if (height > 0)
			return height;
		return 0;*/
	}

	public int getHeight()
	{
		int h = 0;
		for (int i = 0; i < representation[0].length; i++)
			for (int j = 0; j < representation.length; j++)
				if (representation[j][i])
				{
					h++;
					j = representation.length;
				}
		return h;
	}

	public TetrominoType getType()
	{
		return type;
	}
}
