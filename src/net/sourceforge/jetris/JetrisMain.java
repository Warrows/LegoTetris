package net.sourceforge.jetris;

import ai.IAMain;
import ai.input.GameInput;

public class JetrisMain
{
	private static JetrisMainFrame mf;
	
	public static void main(String[] args)
	{
		mf = new JetrisMainFrame();
		solve();
	}
	
	public static void solve()
	{
		(new Thread(new IAMain(new GameInput(mf)))).start();
	}

}
