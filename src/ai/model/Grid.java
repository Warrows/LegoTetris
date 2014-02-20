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
		return 0; // TODO
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
		for (int k = 0; k < HEIGHT; k++)
		{
			boolean b = false;
			for (Cell[] row : board)
				if ((row[k].isOccupied() || tetromino.getCells(this).contains(
						row[k])) != b)
				{
					System.out.println(row[k]);
					i++;
					b = (row[k].isOccupied() || tetromino.getCells(this)
							.contains(row[k]));
				}
		}
		System.out.println("->"+i);
		return i;
	}

	/**
	 * The total number of row transitions. A row transition occurs when an
	 * empty cell is adjacent to a filled cell on the same row and vice versa.
	 */
	private double rowsTransitions()
	{
		double i = 0;
		for (Cell[] col : board)
		{
			boolean b = false;
			for (Cell c : col)
				if ((c.isOccupied() || tetromino.getCells(this).contains(c)) != b)
				{
					i++;
					b = (c.isOccupied() || tetromino.getCells(this).contains(c));
				}
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

	private boolean occupied(int row, int col)
	{
		return board[row][col].isOccupied()
				|| tetromino.getCells(this).contains(getCell(col, row));
	}

	private double testRow(int row)
	{
		for (int i = 0; i < 10; i++)
			if (!occupied(i, row))
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
		return (tetromino.getHeightPosition() + tetromino.getHeight()) / 2;
	}
}
