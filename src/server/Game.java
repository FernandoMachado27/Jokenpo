package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import util.ValueReq;
import util.ValueResp;

public class Game extends Thread {

	Socket clientSocket;

	public Game(Socket socket) {
		clientSocket = socket;
	}

	@Override
	public void run() {

		ObjectOutputStream out;
		ObjectInputStream in;

		try {
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());

			ValueReq valueReq = (ValueReq) in.readObject();

			String p1 = valueReq.getP1();
			String p2 = valueReq.getP2();

			ValueResp valueResp;

			switch (p1) {
			case "Pedra":
				switch (p2) {
				case "Pedra": 
					valueResp = new ValueResp("Empate, não houve vencedor");
					break;

				case "Papel": 
					valueResp = new ValueResp("Player 2 ganhou");
					break;

				case "Tesoura": 
					valueResp = new ValueResp("Player 1 ganhou");
					break;

				default:
					valueResp = new ValueResp("Comando inválido, não houve vencedor");
					break;
				}

				break;

			case "Papel":
				switch (p2) {
				case "Pedra": 
					valueResp = new ValueResp("Player 1 ganhou");
					break;
				case "Papel": 
					valueResp = new ValueResp("Empate, não houve vencedor");
					break;
				case "Tesoura":
					valueResp = new ValueResp("Player 2 ganhou");
					break;
				default:
					valueResp = new ValueResp("Comando inválido, não houve vencedor");
					break;
				}

				break;

			case "Tesoura":
				switch (p2) {
				case "Pedra": 
					valueResp = new ValueResp("Player 2 ganhou");
					break;
				case "Papel":
					valueResp = new ValueResp("Player 1 ganhou");
					break;
				case "Tesoura": 
					valueResp = new ValueResp("Empate, não houve vencedor");
					break;

				default:
					valueResp = new ValueResp("Comando inválido, não houve vencedor");
					break;
				}

				break;

			default:
				valueResp = new ValueResp("Comando inválido, não houve vencedor");
				break;
			}

			out.writeObject(valueResp);

			clientSocket.close();

		} catch (Exception e) {
			System.out.println("Erro (thread): " + e.getMessage());
		}
	}

}
