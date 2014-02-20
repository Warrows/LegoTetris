package ai;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import ai.input.TetrisDataInput;
import ai.model.Grid;

public class IAMain implements Runnable
{
	private TetrisDataInput tdi;
	private Grid currentState;
	private SortedSet<Grid> possibleStates;

	public IAMain(TetrisDataInput tdi)
	{
		this.tdi = tdi;
	}

	@Override
	public void run()
	{
		while (!tdi.isGameOver())
		{
			// Acquisition de la grille
			currentState = tdi.getTetrisData();
			// création des sous grilles
			possibleStates = currentState.children();
			// calcul du chemin entre la grille actuelle et le sous grille
			// envoi du chemin
			play(getCommands());
		}
	}

	private List<Command> getCommands()
	{
		List<Command> list = new LinkedList<Command>();
		Grid objective = possibleStates.first();

		list.add(Command.MOVE_DOWN);
		return list;
	}

	private void play(List<Command> commands)
	{
		for (Command c : commands)
			execute(c);
	}

	private void execute(Command c)
	{
		try
		{
			switch (c)
			{
			case MOVE_DOWN:
				new Robot().keyPress(KeyEvent.VK_S);
				break;
			case MOVE_LEFT:
				new Robot().keyPress(KeyEvent.VK_A);
				break;
			case MOVE_RIGHT:
				new Robot().keyPress(KeyEvent.VK_D);
				break;
			case ROTATE:
				new Robot().keyPress(KeyEvent.VK_W);
				break;
			}
		} catch (AWTException e)
		{
			e.printStackTrace();
		}
	}
}
