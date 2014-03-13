package fingerDriver;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
//import java.net.InetAddress;


import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;


public class EV3SuperFingerPilot {
	public static void main(String[] args) {
		int amplitude=20;
		
		LCD.clear();
		LCD.drawString("Please send Config", 0,0);
		LCD.refresh();
		Sound.buzz();

		try {
			/* ma procedure à l'initialisation ne marche pas, forcer une adresse IP à la machine host peut être une solution*/
			//InetAddress adresse = InetAddress.getByName("10.0.0.2");
			
			DatagramSocket socketReception  = new DatagramSocket(22);
			String message = null;
			byte[] tampon;
			DatagramPacket paquet;
			
			tampon = new byte[1000];
			paquet = new DatagramPacket(tampon, tampon.length);
			socketReception.receive(paquet);
			message = new String(tampon);
			message = message.substring(0, message.indexOf('\0'));

			
			if(message.startsWith("0")) {
				LCD.clear();
				LCD.drawString("Config problem!", 0,0);
				LCD.drawString("[OK] to quit program", 0,2);
				LCD.refresh();
			    Button.waitForAnyPress();
			    System.exit(1);
			}
			else {
					Motor.A.setSpeed(Integer.parseInt(message));
					Motor.B.setSpeed(Integer.parseInt(message));
			}
			
			tampon = new byte[1000];
			paquet = new DatagramPacket(tampon, tampon.length);
			socketReception.receive(paquet);
			message = new String(tampon);
			message = message.substring(0, message.indexOf('\0'));
			
			if(message.startsWith("0")) {
				LCD.clear();
				LCD.drawString("Config problem!", 0,0);
				LCD.drawString("[OK] to quit program", 0,2);
				LCD.refresh();
			    Button.waitForAnyPress();
			    System.exit(1);
			}
			else {
				amplitude = Integer.parseInt(message);
			}
	/*
			tampon = new byte[100];
			paquet = new DatagramPacket(tampon, tampon.length);
			socketReception.receive(paquet);
			message = new String(tampon);
			message = message.substring(0, message.indexOf('\0'));
			
			if(message.startsWith("0")) {
				LCD.clear();
				LCD.drawString("Config problem!", 0,0);
				LCD.drawString("[OK] to quit program", 0,2);
				LCD.refresh();
			    Button.waitForAnyPress();
			    System.exit(1);
			}
			else {
				adresse = InetAddress.getByName(message);
			};*/
			Motor.B.rotate(0,false);
			Motor.A.rotate(0,false);
			
			LCD.clear();
			LCD.drawString("Ready to work!", 0,0);
			LCD.refresh();
			
			do {
				tampon = new byte[2000];
				paquet = new DatagramPacket(tampon, tampon.length);
				socketReception.receive(paquet);
				message = new String(tampon);
				
				if(message.startsWith("Af")) {
					Motor.A.rotate(amplitude,false);
					while(Motor.A.isMoving())
						Thread.yield();
					Motor.A.rotate(-amplitude,false);
					while(Motor.A.isMoving())
						Thread.yield();
					Motor.A.stop();
				}
				else if(message.startsWith("Ab")) {
					Motor.A.rotate(-amplitude,false);
					while(Motor.A.isMoving())
						Thread.yield();
					Motor.A.rotate(amplitude,false);
					while(Motor.A.isMoving())
						Thread.yield();
					Motor.A.stop();
				}
				else if(message.startsWith("Bf")) {
					Motor.B.rotate(amplitude,false);
					while(Motor.B.isMoving())
						Thread.yield();
					Motor.B.rotate(-amplitude,false);
					while(Motor.B.isMoving())
						Thread.yield();
					Motor.B.stop();
				}
				else if(message.startsWith("Bb")) {
					Motor.B.rotate(-amplitude,false);
					while(Motor.B.isMoving())
						Thread.yield();
					Motor.B.rotate(amplitude,false);
					while(Motor.B.isMoving())
						Thread.yield();
					Motor.B.stop();
				}
				else if (!message.startsWith("0")){
					LCD.clear();
					LCD.drawString("Unknown command", 0,0);
					LCD.drawString(message, 0, 1);
					LCD.drawString("[OK] to resume", 0,2);
					LCD.refresh();
				    Button.waitForAnyPress();
				    LCD.clear();
					LCD.drawString("Ready to work!", 0,0);
					LCD.refresh();
				}
				//DatagramSocket socketEnvoi = new DatagramSocket();
				//String toSend;
				
				//toSend = "FinC";
				//tampon = toSend.getBytes();
				//paquet = new DatagramPacket(tampon, tampon.length, adresse, 55556);
				//socketEnvoi.send(paquet);
				
				//socketEnvoi.close();
			} while(!message.startsWith("0"));
			socketReception.close();
			LCD.clear();
			LCD.drawString("Fin ! [OK]", 0,0);
			LCD.refresh();
		    Button.waitForAnyPress();
		    LCD.clear();
		    LCD.refresh();
		}
		catch(IOException exc) {
			LCD.clear();
			LCD.drawString("Error : IOException", 0,0);
			LCD.drawString("[OK] to quit program", 0,2);
			LCD.refresh();
		    Button.waitForAnyPress();
		    LCD.clear();
		    LCD.refresh();
		}
	}
}
