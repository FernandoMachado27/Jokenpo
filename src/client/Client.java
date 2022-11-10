package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

import javax.swing.JOptionPane;

import server.Computer;
import util.ValueReq;
import util.ValueResp;

public class Client{

	public static void main(String[] args) {
		Socket socket;
		final int PORT = 0005;
		final String IP = "127.0.0.1";
		ObjectOutputStream out;
		ObjectInputStream in;
		String p1, p2;
		Object[] options = { "PxP", "PxC" };
		int op;

		try {
			socket = new Socket(IP, PORT);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());

			System.out.println("==============================");
			System.out.println("Seja Bem-Vindo ao game Jokenpô");
			System.out.println("==============================");
			System.out.println("Os players devem escolher entre Pedra, Papel e Tesoura");

			JOptionPane.showMessageDialog(null, "Seja Bem-Vindo ao game Jokenpô", "Aviso", JOptionPane.WARNING_MESSAGE);
			JOptionPane.showMessageDialog(null, "Os players devem escolher entre Pedra, Papel e Tesoura", "Aviso",
					JOptionPane.INFORMATION_MESSAGE);

			op = JOptionPane.showOptionDialog(null, "Deseja jogar Player x Player ou Player x Computador?",
					"Clique no botão", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

			if (op == 0) {
				p1 = JOptionPane.showInputDialog(null, "Player 1, digite abaixo sua escolha: ", "Player 1", 1);
				p2 = JOptionPane.showInputDialog(null, "Player 2, digite abaixo sua escolha: ", "Player 2", 1);

				ValueReq valueReq = new ValueReq(p1, p2);
				out.writeObject(valueReq);
				ValueResp valueResp = (ValueResp) in.readObject();

				JOptionPane.showMessageDialog(null, valueResp.getVencedor(), "Aviso", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Vencedor: " + valueResp.getVencedor());
			} else {
				p1 = JOptionPane.showInputDialog(null, "Player 1, digite abaixo sua escolha: ", "Player 1", 1);
				
				Computer computer = new Computer();
				p2 = computer.computer();
				
				ValueReq valueReq = new ValueReq(p1, p2);
				out.writeObject(valueReq);
				
				JOptionPane.showMessageDialog(null, "O sistema está validando as jogadas Player x Computador", "Aviso",
						JOptionPane.INFORMATION_MESSAGE);
				
				ValueResp valueResp = (ValueResp) in.readObject();
				
				JOptionPane.showMessageDialog(null, "O Computador escolheu: " + valueReq.getP2(), "Aviso", JOptionPane.INFORMATION_MESSAGE);
				
				JOptionPane.showMessageDialog(null, valueResp.getVencedor(), "Aviso", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Vencedor: " + valueResp.getVencedor());
			}

			socket.close();

		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
}
