package ai.executor;

import fingerLib.EV3Communicator;
import ai.Command;

public class PhysicalExecutor implements Executor
{
	private static EV3Communicator com;
	
	public PhysicalExecutor()
	{
		if (com == null)
		{
			com = new EV3Communicator("config.ini");
			com.initEV3();
		}
	}
	
	@Override
	public void execute(Command c)
	{
		switch (c)
		{
		case MOVE_DOWN:
			com.pressButtonDown();
			break;
		case MOVE_LEFT:
			com.pressButtonLeft();
			break;
		case MOVE_RIGHT:
			com.pressButtonRight();
			break;
		case ROTATE:
			com.pressButtonUp();
			break;
		}
	}
}
