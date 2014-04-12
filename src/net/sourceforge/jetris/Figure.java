package net.sourceforge.jetris;

import java.awt.Color;

public abstract class Figure
{

	protected final static int I = 1;
	protected final static int T = 2;
	protected final static int O = 3;
	protected final static int L = 4;
	protected final static int J = 5;
	protected final static int S = 6;
	protected final static int Z = 7;

	protected final static Color COL_I = new Color(254, 210, 153);
	protected final static Color COL_T = new Color(254, 254, 153);
	protected final static Color COL_O = new Color(254, 153, 153);
	protected final static Color COL_L = new Color(251, 153, 254);
	protected final static Color COL_J = new Color(153, 176, 254);
	protected final static Color COL_S = new Color(153, 254, 237);
	protected final static Color COL_Z = new Color(154, 253, 154);

	protected int[] arrX;
	protected int[] arrY;

	protected int offsetX;
	protected int offsetY;

	protected int offsetXLast;
	protected int offsetYLast;

	protected Figure(int[] arrX, int[] arrY)
	{
		this.arrX = arrX;
		this.arrY = arrY;
		offsetYLast = offsetY = 0;
		offsetXLast = offsetX = 4;
	}

	protected int getMaxRightOffset()
	{
		int r = Integer.MIN_VALUE;
		for (int i = 0; i < arrX.length; i++)
		{
			if (r < arrX[i])
				r = arrX[i];
		}
		return r + offsetX;
	}

	protected void setOffset(int x, int y)
	{
		offsetXLast = offsetX;
		offsetYLast = offsetY;
		offsetX = x;
		offsetY = y;
	}

	protected void resetOffsets()
	{
		offsetX = offsetY = offsetXLast = offsetYLast = 0;
	}

	protected abstract void rotationRight();

	protected abstract void rotationLeft();

	protected abstract int getGridVal();

	protected abstract Color getGolor();

}
