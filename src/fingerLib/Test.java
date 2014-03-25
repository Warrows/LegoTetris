package fingerLib;

public class Test {
	public static void main(String[] args) {
		try {
			EV3Communicator eve = new EV3Communicator("./config.ini");
			
			eve.stopEV3Program();
			
		} catch (Exception e) {
			System.out.println("Création du communicateur impossible :");
			e.printStackTrace();
		}
	}
}
