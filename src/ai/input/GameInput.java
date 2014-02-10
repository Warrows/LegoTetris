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
		return g;
	}

	@Override
	public boolean isGameOver()
	{
		return jmf.isGameOver();
	}

}
