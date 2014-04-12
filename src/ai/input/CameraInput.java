package ai.input;

import java.util.List;

import vision.Processing;
import net.sourceforge.jetris.Figure;
import ai.model.Grid;

public class CameraInput implements TetrisDataInput
{
	Grid g;

	public CameraInput()
	{
	}

	@Override
	public Grid getTetrisData() throws NoInputException
	{
		g = new Grid();
		List<List<Integer>> rawInput = Processing.getJeu();
		// Figure currentTetromino = new ;
		// g.setFigure(currentTetromino);
		for (int row = 0; row < 20; row++)
			for (int col = 0; col < 10; col++)
				g.setCell(row, col, rawInput.get(row).get(col)>0);
		System.out.println(g);
		return g;
	}

	@Override
	public boolean isGameOver()
	{
		return false;
	}

}
