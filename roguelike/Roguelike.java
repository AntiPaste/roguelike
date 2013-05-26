/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

import java.util.Scanner;

public class Roguelike {

	private Screen screen;
	private Map map;
	private Keyboard keyboard;
	private Client client;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String input;
		
		System.out.print("(S)erver or (C)lient: ");
		input = scanner.nextLine().toLowerCase();
		
		if (input.equals("s")) {
			new Server().start();
		} else if (input.equals("c")) {
			new Roguelike().run();
		} else {
			System.out.println("[!] Invalid choice");
		}
	}

	public void run() {
		this.screen = new Screen();
		this.map = new Map(this);
		this.keyboard = new Keyboard(this);
		this.screen.getWindow().addKeyListener(this.keyboard);

		this.client = new Client(this);
		this.client.run();
	}

	public Client getClient() {
		return this.client;
	}

	public Screen getScreen() {
		return this.screen;
	}

	public Map getMap() {
		return this.map;
	}
}