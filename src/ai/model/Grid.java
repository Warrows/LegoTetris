package ai.model;

import java.util.SortedSet;
import java.util.TreeSet;

import net.sourceforge.jetris.Figure;

public class Grid implements Comparable<Grid>
{
	private boolean[][] grid;
	private Figure figure;

	public Grid()
	{
		grid = new boolean[20][10];
	}
	
	public void setFigure(Figure f)
	{
		figure = f;
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

	private double rowsTransitions()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	private double rowsEliminated()
	{
		double d = 0;
		for (int i = 0; i<20; i++)
			d+=testRow(i);
		return d;
	}

	private double testRow(int row)
	{
		for (int i = 0; i<10; i++)
			if (! grid[row][i])
				return 0;
		return 1;
	}

	private double landingHeight()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compareTo(Grid o)
	{
		return (int) (this.compute() - o.compute());
	}

	public SortedSet<Grid> children()
	{
		SortedSet<Grid> children = new TreeSet<Grid>();
		Grid gridA, gridB;
		for(int i = 0; i<4; i++)
		{
			gridA = rotate();
			if (gridA.overstep())
				continue;
			for (int j=0; j<10;j++)
			{
				gridB=gridA.placeTetromino(j);
				if (gridB.overstep())
					continue;
				children.add(gridB);
			}
		}
		//TODO remove following line
		children.add(this);
		return children;
	}

	private Grid placeTetromino(int j)
	{
		// TODO Auto-generated method stub
		return this;
	}

	private boolean overstep()
	{
		// TODO Auto-generated method stub
		return false;
	}

	private Grid rotate()
	{
		// TODO Auto-generated method stub
		return this;
	}
}
