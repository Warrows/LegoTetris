package net.sourceforge.jetris.figures;

import java.awt.Color;

import net.sourceforge.jetris.Figure;

public class FigureI extends Figure
{

	public FigureI()
	{
		super(new int[]
		{ 0, 0, 0, 0 }, new int[]
		{ 0, 1, 2, 3 });
	}

	public void rotationRight()
	{
		int[] tmp = arrX;
		arrX = arrY;
		arrY = tmp;
	}

	public void rotationLeft()
	{
		rotationRight();
	}

	public int getGridVal()
	{
		return I;
	}

	public Color getGolor()
	{
		return COL_I;
	}
}