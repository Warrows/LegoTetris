package ai.model;

public class Cell
{
	private boolean occupied;
	private int row;
	private int col;

	public Cell(int col, int row)
	{
		this.occupied = false;
		this.row = row;
		this.col = col;
	}

	public boolean occupy()
	{
		if (isOccupied())
			return false;
		occupied = true;
		return true;
	}

	public boolean free()
	{
		if (!isOccupied())
			return false;
		occupied = false;
		return true;
	}

	public boolean isOccupied()
	{
		return occupied;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
	
	public String toString()
	{
		return "Row: "+row+" ,Col: "+col;
	}

}