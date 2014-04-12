package net.sourceforge.jetris;

import vision.Vision;
import ai.IAMain;
import ai.input.CameraInput;
import ai.input.GameInput;

public class JetrisMain
{
	private static JetrisMainFrame mf;
	
	public static void main(String[] args)
	{
		mf = new JetrisMainFrame();
		//Vision.show();
		solve();
	}
	
	public static void solve()
	{
		(new Thread(new IAMain(new GameInput(mf)))).start();
		//(new Thread(new IAMain(new CameraInput()))).start();
	}

}
