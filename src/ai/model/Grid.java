package ai.model;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Iterator;

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
		this.tetromino = grid.tetromino;
		rotations = 0;
		translations = 0;
		down = 0;
	}

	/**
	 * 
	 * @param newTetromino
	 */
	public void updateTetromino()
	{
		tetromino = new Tetromino();
		for (Cell c : tetromino.getCells(this))
			if (c.isOccupied())
			{
				gameOver();
				return;
			}
	}

	public Cell getCell(int row, int col)
	{
		if (row >= HEIGHT || col < 0 || col >= WIDTH)
			return null;
		return board[col][row];
	}

	public boolean moveDown()
	{
		for (Cell cell : tetromino.getCells(this))
		{
			if (cell == null)
				return true;
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
			if (cell == null || isOccupied(cell.getCol() - 1, cell.getRow()))
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
			if (cell == null || isOccupied(cell.getCol() + 1, cell.getRow()))
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
				if (getCell(row, col).isOccupied()
						|| tetromino.getCells(this).contains(getCell(row, col)))
					str += " X ";
				else
					str += " . ";
			}
			str += "|\n";
			if (row == HEIGHT - 1)
				str += "\\______________________________/";
		}

		return str;
	}

	public void lockTetromino()
	{
		TreeSet<Integer> rows = new TreeSet<Integer>();
		if (tetromino.isOut())
		{
			gameOver();
			return;
		}
		for (Cell c : tetromino.getCells(this))
		{
			c.occupy();
			rows.add(c.getRow());
		}
		breakLines(rows);
		updateTetromino();
	}

	private void gameOver()
	{
		// TODO Auto-generated method stub

	}

	private void breakLines(TreeSet<Integer> rows)
	{
		Iterator<Integer> it = rows.iterator();

		while (it.hasNext())
		{
			int line = it.next();
			for (int i = 0; i < WIDTH; i++)
			{
				if (!getCell(line, i).isOccupied())
				{
					it.remove();
					break;
				}
			}
		}
		for (int row : rows)
			breakLine(row);
	}

	private boolean breakLine(int rowToClear)
	{
		for (int i = 0; i < WIDTH; i++)
		{
			getCell(rowToClear, i).free();
		}
		for (int row = rowToClear; row >= 0; row--)
			for (int col = 0; col < WIDTH; col++)
				if (getCell(row, col).isOccupied())
				{
					getCell(row + 1, col).occupy();
					getCell(row, col).free();
				}
		return true;
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
		for (int i = 0; i < 4; i++)
		{
			gridA = new Grid(this);
			gridA.turnClockwise();
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
		}
		if (children.isEmpty())
			System.out.println(children);
		System.out.println("->Rot:" + children.first().getRotation()
				+ ", Trans:" + children.first().getTranslation() + ", Down:a"
				+ children.first().getDown());

		return children;
	}

	@Override
	public int compareTo(Grid o)
	{
		return (int) (this.compute() - o.compute());
	}

	private double compute()
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
		// TODO Auto-generated method stub
		return 0;
	}

	private double holesNumber()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	private double columnsTransitions()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * The total number of row transitions. A row transition occurs when an
	 * empty cell is adjacent to a filled cell on the same row and vice versa.
	 */
	private double rowsTransitions()
	{
		int transitions = 0;
		boolean last_bit = true;
		boolean bit = true;

		for (int i = 0; i < board.length; ++i)
		{
			Cell[] row = board[i];

			for (int j = 0; j < board[0].length; ++j)
			{
				bit = row[j].isOccupied();

				if (bit != last_bit)
				{
					++transitions;
				}

				last_bit = bit;
			}
			if (!bit)
			{
				++transitions;
			}
			last_bit = true;
		}
		return transitions;
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
		return 20 - down;
	}
}
