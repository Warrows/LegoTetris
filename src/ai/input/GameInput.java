package ai.input;

import net.sourceforge.jetris.JetrisMainFrame;
import ai.model.Grid;

public class GameInput implements TetrisDataInput
{
	private JetrisMainFrame jmf;
	Grid g;

	public GameInput(JetrisMainFrame jmf)
	{
		this.jmf = jmf;
	}

	@Override
	public Grid getTetrisData()
	{
		g = new Grid();
		g.setFigure(jmf.getFigure());
		for (int row = 0; row < 20; row++)
			for (int col = 0; col < 10; col++)
					g.setCell(row, col, jmf.isOccupied(row, col));
		return g;
	}

	@Override
	public boolean isGameOver()
	{
		return jmf.isGameOver();
	}

}
