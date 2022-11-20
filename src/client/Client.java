package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

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
			
			JOptionPane.showMessageDialog(null, "Você deverá seguir o padrão de primeira letra maiúscula e as demais minúsculas. Ex: Papel", "Aviso",
					JOptionPane.INFORMATION_MESSAGE);

			if (op == 0) {
				JPasswordField password = new JPasswordField(10);
				password.getEchoChar(); 
				JLabel rotulo = new JLabel("Player 1, digite ao lado a sua escolha:");
				JPanel entUsuario = new JPanel();
				entUsuario.add(rotulo);
				entUsuario.add(password);
				JOptionPane.showMessageDialog(null, entUsuario, "Player 1", JOptionPane.PLAIN_MESSAGE);
				
				p1 = password.getText();
				
				JPasswordField password2 = new JPasswordField(10);
				password2.getEchoChar(); 
				JLabel rotulo2 = new JLabel("Player 2, digite ao lado a sua escolha:");
				JPanel entUsuario2 = new JPanel();
				entUsuario2.add(rotulo2);
				entUsuario2.add(password2);
				JOptionPane.showMessageDialog(null, entUsuario2, "Player 2", JOptionPane.PLAIN_MESSAGE);
				
				p2 = password2.getText();

				ValueReq valueReq = new ValueReq(p1, p2);
				out.writeObject(valueReq);
				ValueResp valueResp = (ValueResp) in.readObject();
				
				JOptionPane.showMessageDialog(null, "O Player 1 escolheu: " + valueReq.getP1(), "Aviso", JOptionPane.INFORMATION_MESSAGE);
				JOptionPane.showMessageDialog(null, "O Player 2 escolheu: " + valueReq.getP2(), "Aviso", JOptionPane.INFORMATION_MESSAGE);

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
				
				JOptionPane.showMessageDialog(null, "O Computador (player 2) escolheu: " + valueReq.getP2(), "Aviso", JOptionPane.INFORMATION_MESSAGE);
				
				JOptionPane.showMessageDialog(null, valueResp.getVencedor(), "Aviso", JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Vencedor: " + valueResp.getVencedor());
			}

			socket.close();

		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
}
