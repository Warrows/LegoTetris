package ai.model;

import java.util.SortedSet;
import java.util.TreeSet;

import net.sourceforge.jetris.Figure;
import net.sourceforge.jetris.figures.*;

public class Grid implements Comparable<Grid>
{
	public static final int WIDTH = 10;
	public static final int HEIGHT = 20;
	private Cell[][] board;
	private Tetromino tetromino;

	private int rotations, translations, down;

	public Grid()
	{
		board = new Cell[WIDTH][HEIGHT];
		for (int col = 0; col < WIDTH; col++)
			for (int row = 0; row < HEIGHT; row++)
				board[col][row] = new Cell(col, row);
		rotations = 0;
		translations = 0;
		down = 0;
		// TODO KEEP ? updateTetromino();
	}

	public Grid(Grid grid)
	{
		this.board = new Cell[WIDTH][HEIGHT];
		for (int col = 0; col < WIDTH; col++)
			for (int row = 0; row < HEIGHT; row++)
			{
				this.board[col][row] = new Cell(col, row);
				if (grid.board[col][row].isOccupied())
					this.board[col][row].occupy();
			}
		this.tetromino = new Tetromino(grid.tetromino);
		rotations = 0;
		translations = 0;
		down = 0;
	}

	public Cell getCell(int row, int col)
	{
		if (row < 0 || row >= HEIGHT || col < 0 || col >= WIDTH)
		{
			Cell a = new Cell(row, col);
			a.occupy();
			return a;
		}
		return board[col][row];
	}

	public boolean moveDown()
	{
		for (Cell cell : tetromino.getCells(this))
		{
			if (isOccupied(cell.getCol(), cell.getRow() + 1))
				return false;
		}
		tetromino.fall();
		return true;
	}

	/**
	 * Déplace le tetromino vers la gauche
	 * 
	 * @return
	 */
	public boolean moveLeft()
	{
		for (Cell cell : tetromino.getCells(this))
			if (isOccupied(cell.getCol() - 1, cell.getRow()))
				return false;
		tetromino.moveLeft();
		return true;
	}

	/**
	 * Déplace le tetromino vers la droite
	 */
	public boolean moveRight()
	{
		for (Cell cell : tetromino.getCells(this))
			if (isOccupied(cell.getCol() + 1, cell.getRow()))
				return false;
		tetromino.moveRight();
		return true;
	}

	/**
	 * Tourne le tetromino dans le sens horaire
	 */
	public void turnClockwise()
	{
		tetromino.turnClockwise(this);
	}

	/**
	 * Tourne le tetromino dans le sens hiraire inverse
	 */
	public void turnCounterClockwise()
	{
		tetromino.turnCounterClockwise(this);
	}

	/**
	 * Vérifie si le tetromino peut se déplacer suivant les coordonnées x, y
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isOccupied(int col, int row)
	{
		if (col < 0)
			return true;
		if (row < 0)
			return true;
		if (row >= HEIGHT)
			return true;
		if (col >= WIDTH)
			return true;

		return board[col][row].isOccupied();
	}

	public String toString()
	{

		String str = "\n";
		for (int row = 0; row < HEIGHT; row++)
		{
			str += "|";
			for (int col = 0; col < WIDTH; col++)
			{
				if (getCell(row, col).isOccupied())
					str += " X ";
				else if (tetromino.getCells(this).contains(getCell(row, col)))
					str += " O ";
				else
					str += " . ";
			}
			str += "|\n";
			if (row == HEIGHT - 1)
				str += "\\______________________________/";
		}

		return str;
	}

	public void setCell(int row, int col, boolean occupied)
	{
		if (occupied)
			board[col][row].occupy();
		else
			board[col][row].free();
	}

	public void setFigure(Figure figure)
	{
		if (figure instanceof FigureI)
			tetromino = new Tetromino((FigureI) figure);
		if (figure instanceof FigureO)
			tetromino = new Tetromino((FigureO) figure);
		if (figure instanceof FigureL)
			tetromino = new Tetromino((FigureL) figure);
		if (figure instanceof FigureS)
			tetromino = new Tetromino((FigureS) figure);
		if (figure instanceof FigureZ)
			tetromino = new Tetromino((FigureZ) figure);
		if (figure instanceof FigureT)
			tetromino = new Tetromino((FigureT) figure);
		if (figure instanceof FigureJ)
			tetromino = new Tetromino((FigureJ) figure);
	}

	public SortedSet<Grid> children()
	{
		SortedSet<Grid> children = new TreeSet<Grid>();
		Grid gridA, gridB;
		int t = 0;
		gridA = new Grid(this);
		for (int i = 0; i < 4; i++)
		{
			while (gridA.moveLeft())
				t--;
			do
			{
				gridB = new Grid(gridA);
				while (gridB.moveDown())
					gridB.down++;
				gridB.rotations = i;
				gridB.translations = t;
				children.add(gridB);
				t++;
			} while (gridA.moveRight());
			gridA.turnClockwise();
		}
			System.out.println(children);
		// System.out.println("->Rot:" + children.first().getRotation()
		// + ", Trans:" + children.first().getTranslation() + ", Down:"
		// + children.first().getDown());

		return children;
	}

	@Override
	public int compareTo(Grid o)
	{
		return (int) (o.compute() - this.compute());
	}

	public double compute()
	{
		double val = 0;
		val += -4.500158825082766 * landingHeight();
		val += 3.4181268101392694 * rowsEliminated();
		val += -3.2178882868487753 * rowsTransitions();
		val += -9.348695305445199 * columnsTransitions();
		val += -7.899265427351652 * holesNumber();
		val += -3.3855972247263626 * wellSums();
		return val;
	}

	private double wellSums()
	{
		int well_sums = 0;

		// Check for well cells in the "inner columns" of the board.
		// "Inner columns" are the columns that aren't touching the edge of the
		// board.
		for (int i = 1; i < WIDTH - 1; ++i)
		{
			for (int j = board.length - 1; j >= 0; --j)
			{
				if ((!board[i][j].isOccupied())
						&& (board[i - 1][j].isOccupied())
						&& (board[i + 1][j].isOccupied()))
				{

					// Found well cell, count it + the number of empty cells
					// below it.
					++well_sums;

					for (int k = j - 1; k >= 0; --k)
					{
						if (!(board[i][k].isOccupied()))
						{
							++well_sums;
						} else
						{
							break;
						}
					}
				}
			}
		}
		/*
		 * TODO // Check for well cells in the leftmost column of the board. for
		 * (int j = board.length - 1; j >= 0; --j) { if ((((board[j] >> 0) & 1)
		 * == 0) && (((board[j] >> (0 + 1)) & 1) == 1)) {
		 * 
		 * // Found well cell, count it + the number of empty cells below // it.
		 * ++well_sums;
		 * 
		 * for (int k = j - 1; k >= 0; --k) { if (((board[k] >> 0) & 1) == 0) {
		 * ++well_sums; } else { break; } } } }
		 * 
		 * // Check for well cells in the rightmost column of the board. for
		 * (int j = board.length - 1; j >= 0; --j) { if ((((board[j] >>
		 * (num_columns - 1)) & 1) == 0) && (((board[j] >> (num_columns - 2)) &
		 * 1) == 1)) { // Found well cell, count it + the number of empty cells
		 * below // it.
		 * 
		 * ++well_sums; for (int k = j - 1; k >= 0; --k) { if (((board[k] >>
		 * (num_columns - 1)) & 1) == 0) { ++well_sums; } else { break; } } } }
		 */
		return well_sums;
	}

	private double holesNumber()
	{
		double i = 0;
		for (Cell[] row : board)
			for (Cell c : row)
				if (!c.isOccupied()
						&& getCell(c.getRow() + 1, c.getCol()).isOccupied())
					i++;
		return i;
	}

	private double columnsTransitions()
	{
		double i = 0;
		boolean b = true;
		for (int k = 0; k< WIDTH; k++)
			for (Cell[] row : board)	
				if (row[k].isOccupied() != b)
				{
					i++;
					b = row[k].isOccupied();
				}
		return i;
	}

	/**
	 * The total number of row transitions. A row transition occurs when an
	 * empty cell is adjacent to a filled cell on the same row and vice versa.
	 */
	private double rowsTransitions()
	{
		double i = 0;
		boolean b = true;
		for (Cell[] row : board)
			for (Cell c : row)
				if (c.isOccupied() != b)
				{
					i++;
					b = c.isOccupied();
				}
		return i;
	}

	private double rowsEliminated()
	{
		double d = 0;
		for (int i = 0; i < 20; i++)
			d += testRow(i);
		return d;
	}

	private double testRow(int row)
	{
		for (int i = 0; i < 10; i++)
			if (!board[i][row].isOccupied())
				return 0;
		return 1;
	}

	public int getRotation()
	{
		return rotations;
	}

	public int getTranslation()
	{
		return translations;
	}

	public int getDown()
	{
		return down;
	}

	private double landingHeight()
	{
		return ( tetromino.getHeightPosition() + tetromino.getHeight() )/2;
	}
}
