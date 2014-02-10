package ai.input;

import net.sourceforge.jetris.JetrisMainFrame;
import ai.model.Grid;

public class GameInput implements TetrisDataInput
{
	private JetrisMainFrame jmf;

	public GameInput(JetrisMainFrame jmf)
	{
		this.jmf = jmf;
	}

	@Override
	public Grid getTetrisData()
	{
		return new Grid();
	}

}
