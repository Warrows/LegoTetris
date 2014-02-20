package net.sourceforge.jetris.figures;

import java.awt.Color;

import net.sourceforge.jetris.Figure;

public class FigureO extends Figure
{

	public FigureO()
	{
		super(new int[]
		{ 0, 0, 1, 1 }, new int[]
		{ 0, 1, 0, 1 });
	}

	public void rotationRight()
	{
	}

	public void rotationLeft()
	{
	}

	public int getGridVal()
	{
		return O;
	}

	public Color getGolor()
	{
		return COL_O;
	}
}