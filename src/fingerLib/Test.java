package fingerLib;

public class Test {
	public static void main(String[] args) {
		try {
			EV3Communicator eve = new EV3Communicator("./config.ini");
			eve.initEV3();
			
			eve.pressButtonRight();
			eve.pressButtonRight();
			eve.pressButtonRight();
			eve.pressButtonRight();
			eve.pressButtonRight();
			eve.pressButtonRight();
			eve.pressButtonLeft();
			eve.pressButtonLeft();
			eve.pressButtonLeft();
			eve.pressButtonLeft();
			eve.pressButtonLeft();
			eve.pressButtonLeft();
			
			eve.stopEV3Program();
			
		} catch (Exception e) {
			System.out.println("Création du communicateur impossible :");
			e.printStackTrace();
		}
	}
}
