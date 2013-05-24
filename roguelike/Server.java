/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server {

	private Map<Integer, ServerThread> clients;
	private ServerSocket serverSocket;
	private int playerID = 1;

	public Server() {
		this.clients = new HashMap<Integer, ServerThread>();
	}

	public void start() {
		System.out.println("[x] Starting server...");

		try {
			this.serverSocket = new ServerSocket(Settings.networkPort);
		} catch (IOException e) {
			System.out.println("[!]Â Could not listen on port " + Settings.networkPort);
			System.exit(-1);
		}

		this.acceptConnections();
	}

	public void removeClient(int playerID) {
		ServerThread client = this.clients.get(playerID);
		this.clients.remove(playerID);
		client.kill();
	}

	public void broadcast(String data) {
		this.broadcast(data, null);
	}

	public void broadcast(String data, ServerThread skip) {
		for (ServerThread client : this.clients.values()) {
			if (client != skip) {
				client.send(data);
			}
		}
	}

	private void acceptConnections() {
		System.out.println("[x] Accepting connections.");

		while (true) {
			try {
				Socket clientSocket = this.serverSocket.accept();
				ServerThread client = new ServerThread(this, clientSocket, this.playerID);

				System.out.println("[x] New player ID: " + this.playerID);
				this.clients.put(this.playerID, client);
				client.send("playerid=" + this.playerID);

				for (Map.Entry<Integer, ServerThread> entry : this.clients.entrySet()) {
					int playerID = entry.getKey();
					ServerThread player = (ServerThread) entry.getValue();

					if (playerID != this.playerID) {
						client.send(String.format("newplayer=%d", playerID));
						client.send(String.format("move=%d:%d:%d", playerID, player.getX(), player.getY()));
					}
				}

				this.playerID++;;

				Thread t = new Thread(client);
				t.start();
			} catch (IOException e) {
				System.out.println("[!] IOException in acceptConnections.");
				System.exit(-1);
			}
		}
	}

	public class ServerThread implements Runnable {

		private Server server;
		private Socket clientSocket;
		private DataInputStream dataIn;
		private DataOutputStream dataOut;
		private int playerID;
		private int x;
		private int y;

		public ServerThread(Server server, Socket clientSocket, int playerID) {
			this.server = server;
			this.clientSocket = clientSocket;
			this.playerID = playerID;

			try {
				this.dataIn = new DataInputStream(new BufferedInputStream(this.clientSocket.getInputStream()));
				this.dataOut = new DataOutputStream(this.clientSocket.getOutputStream());
			} catch (IOException e) {
				System.out.println("[!] Failed to create data streams for client");
				System.exit(-1);
			}

			this.server.broadcast(String.format("newplayer=%d", this.playerID), this);
		}

		public void send(String data) {
			try {
				this.dataOut.writeUTF(data);
				this.dataOut.flush();
			} catch (IOException e) {
				System.out.println("[!] Failed to send data");
				this.server.removeClient(this.playerID);
			}
		}

		public void kill() {
			Thread.currentThread().interrupt();
		}

		public int getX() {
			return this.x;
		}

		public int getY() {
			return this.y;
		}

		@Override
		public void run() {
			Pattern parseInput = Pattern.compile("^([a-z]+)=(.+)$");

			try {
				String dataInput;
				while (!Thread.currentThread().isInterrupted() && (dataInput = this.dataIn.readUTF()) != null) {
					Matcher input = parseInput.matcher(dataInput);

					if (!input.find()) {
						continue;
					}

					String command = input.group(1);
					String parameters = input.group(2);

					if (command.equals("move")) {
						Pattern parsePosition = Pattern.compile("^([0-9]+):([0-9]+)$");
						Matcher position = parsePosition.matcher(parameters);

						if (!position.find()) {
							continue;
						}
						
						this.x = Integer.parseInt(position.group(1));
						this.y = Integer.parseInt(position.group(2));

						System.out.println("[x] Received player coordinates, x = " + this.x + ", y = " + this.y);

						this.server.broadcast(String.format("move=%d:%d:%d", this.playerID, this.x, this.y));
					}
				}
			} catch (IOException e) {
				System.out.println("[!] Failed to read data");
				this.server.removeClient(this.playerID);
			}
		}
	}
}