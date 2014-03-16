package ai;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;

import ai.executor.Executor;
import ai.executor.InternExecutor;
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
		// Acquisition de la grille
		currentState = tdi.getTetrisData();
		// création des sous grilles
		possibleStates = currentState.children();
		System.out.println(possibleStates);
		// calcul du chemin entre la grille actuelle et le sous grille
		// envoi du chemin
		play(getCommands());
		try
		{
			Thread.sleep(100);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	private List<Command> getCommands()
	{
		List<Command> list = new LinkedList<Command>();
		if (possibleStates.isEmpty())
			return list;
		Grid objective = possibleStates.first();
		System.out.println(objective);
		for (int i = 0; i < objective.getRotation(); i++)
			list.add(Command.ROTATE);
		for (int i = 0; i < objective.getTranslation(); i++)
			list.add(Command.MOVE_RIGHT);
		for (int i = 0; i > objective.getTranslation(); i--)
			list.add(Command.MOVE_LEFT);
		list.add(Command.MOVE_DOWN);
		System.out.println(list);
		return list;
	}

	private void play(List<Command> commands)
	{
		Executor ex = new InternExecutor();
		try
		{
			Thread.sleep(50);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		for (Command c : commands)
		{
			ex.execute(c);
			try
			{
				Thread.sleep(50);
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
