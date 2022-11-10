package server;

import java.util.ArrayList;
import java.util.Random;

public class Computer {

	Random randomValue = new Random();
	String p2;
	int valorAleatorio;

	public String computer() {
		int valorAleatorio = randomValue.nextInt(3);
		
		this.valorAleatorio = valorAleatorio;
		
		switch (valorAleatorio) {
		case 0: 
			p2 = "Pedra";
			break;
		case 1:
			p2 = "Papel";
			break;
		case 2:
			p2 = "Tesoura";
			break;
			
		default:
			break;
		}
		return p2;
	}
}
