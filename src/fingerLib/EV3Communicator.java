package fingerLib;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

/**
 * Version sport de EV3Communicator de la BasicFingerLib.
 * Utilise un fichier de configuration et une communication de données plus complexe.
 * Envoi de paquets de configuration de la vitesse du robot initiale et de l'amplitude de rotation
 * Pilotage par d'un paquet représentant le moteur et son sens de rotation
 * 
 * Attendre que le robot soit prêt (message sur son LCD)
 * Lancer initEV3()
 * Utilisation des fonctions de rotation du moteur une fois que le LCD affiche "Ready to work !"
 * 
 * Configuration dans un fichier de configuration dont l'adresse sera fournie en argument
 * du constructeur.
 * Contenu par défaut du fichier :
 * 
 * "vitesse=720
 * amplitude=30
 * haut=Bf
 * bas=Bb
 * gauche=Af
 * droite=Ab"
 * 
 * 
 * Dans le cas où le fichier ne pourraît être ouvert, utilisation de la configuration citée ci-dessus.
 */

public class EV3Communicator {
	InetAddress adresse;
	int port;
	byte[] tampon;
	
	int amplitude;
	int vitesse;
	String haut;
	String bas;
	String gauche;
	String droite;
	
	final int damp = 30;
	final int dvit = 720;
	final String dh = "Bf";
	final String dbas = "Bb";
	final String dgau = "Af";
	final String ddro = "Ab";
	
	//DatagramSocket socketReception;
	
	public EV3Communicator(String config){
	
		try {
			adresse = InetAddress.getByName("10.0.1.1");
		} catch (UnknownHostException e1) {
			// Impossible de trouver le robot
			e1.printStackTrace();
			System.exit(1);
		}
		port = 22;

		//socketReception = new DatagramSocket(55556);


		
		
		vitesse=-1;
		amplitude=-1;
		haut="0";
		bas="0";
		gauche="0";
		droite="0";
		
		/* Tentative de configuration par fichier */
		try{
			InputStream is = new FileInputStream(config);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String texte = "";
			String ligne;
			while((ligne=br.readLine())!=null) {
				texte+=ligne+"\n";
			}
			br.close();
			
			StringTokenizer st = new StringTokenizer(texte, "\n");
			while (st.hasMoreTokens()) {
				ligne = st.nextToken();
				if(ligne.startsWith("vitesse="))
					vitesse=Integer.parseInt(ligne.substring(8, ligne.length()));
				else if(ligne.startsWith("amplitude="))
					amplitude=Integer.parseInt(ligne.substring(10, ligne.length()));
				else if(ligne.startsWith("haut="))
					haut=ligne.substring(5,7);
				else if(ligne.startsWith("bas="))
					bas=ligne.substring(4,6);
				else if(ligne.startsWith("gauche="))
					gauche=ligne.substring(7,9);
				else if(ligne.startsWith("droite="))
					droite=ligne.substring(7,9);
				else {
					//Fichier avec une entrée invalide
					//Retour à la configuration par défaut
					System.out.println("Entrée invalide dans le fichier; config par défaut !");
					vitesse=dvit;
					amplitude=damp;
					haut=dh;
					bas=dbas;
					gauche=dgau;
					droite=ddro;
					break;
				}
			}
			
			//Vérification que tous les éléments ont été configurés correctement
			if(vitesse > 720 || vitesse <=0
					|| amplitude <= 0 || amplitude >= 45 //On aura certainement pas besoin de plus pour appuyer sur un 
														//clavier, on évite d'abimer de trop les touches ou le robot...
					|| (!haut.equals("Af") && !haut.equals("Ab") && !haut.equals("Bf") && !haut.equals("Bb"))
					|| (!bas.equals("Af") && !bas.equals("Ab") && !bas.equals("Bf") && !bas.equals("Bb"))
					|| (!gauche.equals("Af") && !gauche.equals("Ab") && !gauche.equals("Bf") && !gauche.equals("Bb"))
					|| (!droite.equals("Af") && !droite.equals("Ab") && !droite.equals("Bf") && !droite.equals("Bb"))) {
				/*Configuration invalide */
				System.out.println("Configuration invalide : configuration par défaut rétablie");
				vitesse=dvit;
				amplitude=damp;
				haut=dh;
				bas=dbas;
				gauche=dgau;
				droite=ddro;
			}
					
		}
		catch(IOException e) {
			System.out.println("Fichier de configuration non trouvé ou illisible");
			
			/* Configuration par défaut */
			vitesse=dvit;
			amplitude=damp;
			haut=dh;
			bas=dbas;
			gauche=dgau;
			droite=ddro;
		}
	}
	
	public void initEV3() {
		try {
			
			DatagramSocket socketEnvoi = new DatagramSocket();
			DatagramPacket paquet;
			String message;
			
			message = ""+vitesse;
			tampon = message.getBytes();
			paquet = new DatagramPacket(tampon, tampon.length, adresse, port);
			socketEnvoi.send(paquet);
			
			message = ""+amplitude;
			tampon = message.getBytes();
			paquet = new DatagramPacket(tampon, tampon.length, adresse, port);
			socketEnvoi.send(paquet);
			/*
			message = InetAddress.getLocalHost().getHostAddress().toString();
			tampon = message.getBytes();
			paquet = new DatagramPacket(tampon, tampon.length, adresse, port);
			socketEnvoi.send(paquet);
			*/
			socketEnvoi.close();
		}
		catch(IOException exc) {
			exc.printStackTrace();
			stopEV3Program();
		}
	}
	
	public void pressButtonLeft() {
		try {

			DatagramSocket socketEnvoi = new DatagramSocket();
//			DatagramSocket socketReception  = new DatagramSocket(55555);
			DatagramPacket paquet;
			String message;
			
			message = gauche;
			tampon = message.getBytes();
			paquet = new DatagramPacket(tampon, tampon.length, adresse, port);
			socketEnvoi.send(paquet);
			
			socketEnvoi.close();
/*			message = "nope";
			
				tampon = new byte[20];
				paquet = new DatagramPacket(tampon, tampon.length);
				socketReception.receive(paquet);
				message = new String(tampon);*/

		}
		catch(IOException exc) {
			exc.printStackTrace();
			stopEV3Program();
		}		
	}
	
	public void pressButtonRight() {
		try {

			DatagramSocket socketEnvoi = new DatagramSocket();
			
			DatagramPacket paquet;
			String message;
			
			message = droite;
			tampon = message.getBytes();
			paquet = new DatagramPacket(tampon, tampon.length, adresse, port);
			socketEnvoi.send(paquet);
			
			socketEnvoi.close();
			/*message = "nope";
			
				tampon = new byte[20];
				paquet = new DatagramPacket(tampon, tampon.length);
				socketReception.receive(paquet);
				message = new String(tampon);*/

		}
		catch(IOException exc) {
			exc.printStackTrace();
			stopEV3Program();
		}		
	}
	
	public void pressButtonUp() {
		try {

			DatagramSocket socketEnvoi = new DatagramSocket();
			//DatagramSocket socketReception  = new DatagramSocket(55555);
			DatagramPacket paquet;
			String message;
			
			message = haut;
			tampon = message.getBytes();
			paquet = new DatagramPacket(tampon, tampon.length, adresse, port);
			socketEnvoi.send(paquet);
			
			socketEnvoi.close();/*
			message = "nope";
			
				tampon = new byte[20];
				paquet = new DatagramPacket(tampon, tampon.length);
				socketReception.receive(paquet);
				message = new String(tampon);*/

		}
		catch(IOException exc) {
			exc.printStackTrace();
			stopEV3Program();
		}		
	}
	
	public void pressButtonDown() {
		try {

			DatagramSocket socketEnvoi = new DatagramSocket();
			//DatagramSocket socketReception  = new DatagramSocket(55555);
			DatagramPacket paquet;
			String message;
			
			message = bas;
			tampon = message.getBytes();
			paquet = new DatagramPacket(tampon, tampon.length, adresse, port);
			socketEnvoi.send(paquet);
			
			socketEnvoi.close();/*
			message = "nope";
			
				tampon = new byte[20];
				paquet = new DatagramPacket(tampon, tampon.length);
				socketReception.receive(paquet);
				message = new String(tampon);*/

		}
		catch(IOException exc) {
			exc.printStackTrace();
			stopEV3Program();
		}		
	}
	
	public void stopEV3Program() {
		try {

			DatagramSocket socketEnvoi = new DatagramSocket();
			DatagramPacket paquet;
			String message;
			
			message = "0";
			tampon = message.getBytes();
			paquet = new DatagramPacket(tampon, tampon.length, adresse, port);
			socketEnvoi.send(paquet);
			socketEnvoi.close();			
			
			//socketReception.close();
		}
		catch(IOException exc) {
			exc.printStackTrace();
			/* Si le robot EV3 n'a pas lui même planté pour les mêmes raisons, il faudra agir à la main
			sur lui pour lui faire stopper son programme ou l'éteindre. */
		}	
	}
}
