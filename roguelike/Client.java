/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client implements Runnable {

	private Roguelike roguelike;
	private Map map;
	private Socket socket;
	private DataInputStream dataIn;
	private DataOutputStream dataOut;

	public Client(Roguelike roguelike) {
		this.roguelike = roguelike;
		this.map = this.roguelike.getMap();
	}

	public void run() {
		try {
			this.socket = new Socket("127.0.0.1", Settings.networkPort);
			this.dataIn = new DataInputStream(new BufferedInputStream(this.socket.getInputStream()));
			this.dataOut = new DataOutputStream(this.socket.getOutputStream());
		} catch (UnknownHostException e) {
			System.out.println("[!] Invalid hostname");
			System.exit(-1);
		} catch (IOException e) {
			System.out.println("[!] Unable to connect to server");
			System.exit(-1);
		}

		System.out.println("[x] Connected");
		this.send("move=" + this.map.getPlayer().getPosX() + ":" + this.map.getPlayer().getPosY());

		Pattern parseInput = Pattern.compile("^([a-z]+)=(.+)$");
		
		try {
			String dataInput;
			while ((dataInput = this.dataIn.readUTF()) != null) {
				Matcher input = parseInput.matcher(dataInput);

				if (!input.find()) {
					continue;
				}
				
				String command = input.group(1);
				String parameters = input.group(2);

				if (command.equals("playerid")) {
					this.map.setPlayerID(Integer.parseInt(parameters));
					System.out.println("[x] Received playerID of " + Integer.parseInt(parameters));
				} else if (command.equals("newplayer")) {
					this.map.addPlayer(Integer.parseInt(parameters));
				} else if (command.equals("move")) {
					Pattern parsePosition = Pattern.compile("^([0-9]+):([0-9]+):([0-9]+)$");
					Matcher position = parsePosition.matcher(parameters);

					if (position.find()) {
						int playerID = Integer.parseInt(position.group(1));
						int x = Integer.parseInt(position.group(2));
						int y = Integer.parseInt(position.group(3));

						this.map.moveAbsolute(playerID, x, y);
						System.out.println("[x] Received player " + playerID + " coordinates, x = " + x + ", y = " + y);
					}
				}
			}
		} catch (IOException e) {
			System.out.println("[!] Failed to read data");
			System.exit(-1);
		}
	}

	public void send(String data) {
		try {
			this.dataOut.writeUTF(data);
			this.dataOut.flush();
		} catch (IOException e) {
			System.out.println("[!] Failed to send data");
			System.exit(-1);
		}
	}
}