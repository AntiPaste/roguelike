/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Frozen
 */
public class Roguelike {

	private Screen screen;
	private Map map;
	private Keyboard keyboard;

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		new Roguelike().run();
	}

	public void run() {
		this.screen = new Screen();
		this.map = new Map(this);
		this.keyboard = new Keyboard(this);

		this.screen.getWindow().addKeyListener(this.keyboard);

		try {
			this.startClient();
		} catch (IOException e) {
			System.out.println("[!] Client failure");
			System.exit(-1);
		}
	}

	public Screen getScreen() {
		return this.screen;
	}

	public Map getMap() {
		return this.map;
	}

	public void startClient() throws IOException {
		Socket socket = null;

		try {
			socket = new Socket("127.0.0.1", Settings.networkPort);
		} catch (UnknownHostException e) {
			System.out.println("[!] Invalid hostname");
			return;
		} catch (IOException e) {
			System.out.println("[!] IO failed");
			return;
		}

		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		out.println("player=" + this.map.getPlayer().getPosX() + ":" + this.map.getPlayer().getPosY());

		socket.close();
	}

	public void startServer() throws IOException {
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(Settings.networkPort);
		} catch (IOException e) {
			System.out.println("[!] Could not listen on port " + Settings.networkPort);
			System.exit(-1);
		}

		System.out.println("[x] Waiting for a client...");

		Socket clientSocket = null;

		try {
			clientSocket = serverSocket.accept();
		} catch (IOException e) {
			System.out.println("[!] Accept failed on port " + Settings.networkPort);
			System.exit(-1);
		}

		System.out.println("[x] Knock knock!");

		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		String inputLine;

		Pattern playerPos = Pattern.compile("^player=([0-9]+):([0-9]+)$");

		while ((inputLine = in.readLine()) != null) {
			Matcher m = playerPos.matcher(inputLine);

			if (!m.find()) {
				continue;
			}

			int x = Integer.parseInt(m.group(1));
			int y = Integer.parseInt(m.group(2));
			List<Block> blocks = this.map.getBlocks();

			blocks.remove(this.map.getBlockAt(x, y));
			blocks.add(new Player(x, y));
			this.map.setBlocks(blocks);
			
			System.out.println("[x] Received player coordinates, x = " + x + ", y = " + y);
		}

		clientSocket.close();
		serverSocket.close();
	}

}