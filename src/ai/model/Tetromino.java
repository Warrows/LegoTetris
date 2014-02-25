package ai.model;

import java.util.HashSet;
import java.util.Set;

import net.sourceforge.jetris.figures.*;

public class Tetromino
{
	private TetrominoType type;
	private int rowOffset;
	private int colOffset;
	private int height;
	private boolean[][] representation;

	public Tetromino()
	{
		this(TetrominoType.getRandomTetrominoType());
	}

	public Tetromino(TetrominoType type)
	{
		this.type = type;
		rowOffset = 0;
		colOffset = 4;
		height = Grid.HEIGHT;
		switch (type)
		{
		case O:
			representation = new boolean[2][2];
			representation[0][0] = true;
			representation[0][1] = true;
			representation[1][0] = true;
			representation[1][1] = true;
			break;
		case I:
			representation = new boolean[3][4];
			representation[1][0] = true;
			representation[1][1] = true;
			representation[1][2] = true;
			representation[1][3] = true;
			colOffset = 3;
			break;
		case J:
			representation = new boolean[3][3];
			representation[0][2] = true;
			representation[1][0] = true;
			representation[1][1] = true;
			representation[1][2] = true;
			break;
		case L:
			representation = new boolean[3][3];
			representation[0][0] = true;
			representation[0][1] = true;
			representation[0][2] = true;
			representation[1][2] = true;
			break;
		case S:
			representation = new boolean[4][2];
			representation[1][0] = true;
			representation[2][0] = true;
			representation[0][1] = true;
			representation[1][1] = true;
			colOffset = 4;
			break;
		case T:
			representation = new boolean[3][3];
			representation[2][0] = true;
			representation[0][0] = true;
			representation[1][0] = true;
			representation[1][1] = true;
			break;
		case Z:
			representation = new boolean[3][2];
			representation[0][0] = true;
			representation[1][0] = true;
			representation[1][1] = true;
			representation[2][1] = true;
			break;
		}
	}

	public Tetromino(FigureO figure)
	{
		this(TetrominoType.O);
	}

	public Tetromino(FigureI figure)
	{
		this(TetrominoType.I);
	}

	public Tetromino(FigureJ figure)
	{
		this(TetrominoType.J);
	}

	public Tetromino(FigureL figure)
	{
		this(TetrominoType.L);
	}

	public Tetromino(FigureS figure)
	{
		this(TetrominoType.S);
	}

	public Tetromino(FigureT figure)
	{
		this(TetrominoType.T);
	}

	public Tetromino(FigureZ figure)
	{
		this(TetrominoType.Z);
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
		if (type == TetrominoType.Z)
			colOffset++;
		if (type == TetrominoType.T)
			return turnCounterClockwise(grid);
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
		if (height > 0)
			return height;
		return 0;
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
		System.out.println("---" +  h);
		return h;
	}
}
