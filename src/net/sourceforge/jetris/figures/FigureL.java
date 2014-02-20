package net.sourceforge.jetris.figures;

import java.awt.Color;

import net.sourceforge.jetris.Figure;

public class FigureL extends Figure
{

	private int[][] rotations;

	private int curRotation;

	public FigureL()
	{
		super(new int[]
		{ 0, 0, 0, 1 }, new int[]
		{ 0, 1, 2, 2 });

		rotations = new int[8][4];
		rotations[0] = new int[]
		{ 0, 0, 0, 1 };
		rotations[1] = new int[]
		{ 0, 1, 2, 2 };

		rotations[2] = new int[]
		{ 0, 0, 1, 2 };
		rotations[3] = new int[]
		{ 0, 1, 0, 0 };

		rotations[4] = new int[]
		{ 0, 1, 1, 1 };
		rotations[5] = new int[]
		{ 0, 0, 1, 2 };

		rotations[6] = new int[]
		{ 0, 1, 2, 2 };
		rotations[7] = new int[]
		{ 1, 1, 0, 1 };
		curRotation = 0;
	}

	public void rotationRight()
	{
		if (curRotation == 0)
		{
			arrX = rotations[2];
			arrY = rotations[3];
			curRotation = 1;
		} else if (curRotation == 1)
		{
			arrX = rotations[4];
			arrY = rotations[5];
			curRotation = 2;
		} else if (curRotation == 2)
		{
			arrX = rotations[6];
			arrY = rotations[7];
			curRotation = 3;
		} else if (curRotation == 3)
		{
			arrX = rotations[0];
			arrY = rotations[1];
			curRotation = 0;
		}
	}

	public void rotationLeft()
	{
		if (curRotation == 0)
		{
			arrX = rotations[7];
			arrY = rotations[6];
			curRotation = 3;
		} else if (curRotation == 3)
		{
			arrX = rotations[4];
			arrY = rotations[5];
			curRotation = 2;
		} else if (curRotation == 2)
		{
			arrX = rotations[2];
			arrY = rotations[3];
			curRotation = 1;
		} else if (curRotation == 1)
		{
			arrX = rotations[0];
			arrY = rotations[1];
			curRotation = 0;
		}
	}

	public int getGridVal()
	{
		return L;
	}

	public Color getGolor()
	{
		return COL_L;
	}
}