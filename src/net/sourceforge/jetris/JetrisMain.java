package net.sourceforge.jetris;

import ai.IAMain;
import ai.input.GameInput;

public class JetrisMain
{
	public static void main(String[] args)
	{
		JetrisMainFrame mf = new JetrisMainFrame();
		// TODO
		(new Thread(new IAMain(new GameInput(mf)))).start();
	}

}
