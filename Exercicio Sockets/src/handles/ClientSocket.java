
package handles;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientSocket extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JTextArea texto;
	private JTextField message;
	private JButton botaoEnviar;
	private JButton botaoSair;
	private JLabel historico;
	private JLabel labelMessage;
	private JPanel content;
	private Socket socket;
	private OutputStream ou;
	private Writer ouw;
	private BufferedWriter bufferW;
	private JTextField textIP;
	private JTextField textPorta;
	private JTextField textNome;

	public ClientSocket() throws IOException {
		JLabel lblMessage = new JLabel("Acessar Servidor");
		textIP = new JTextField("127.0.0.1");
		textPorta = new JTextField("12345");
		textNome = new JTextField("Cliente");
		Object[] texts = { lblMessage, textIP, textPorta, textNome };
		JOptionPane.showMessageDialog(null, texts);
		content = new JPanel();
		texto = new JTextArea(10, 20);
		texto.setEditable(false);
		texto.setBackground(new Color(240, 240, 240));
		message = new JTextField(20);
		historico = new JLabel("Histórico");
		labelMessage = new JLabel("Mensagem");
		botaoEnviar = new JButton("Enviar");
		botaoEnviar.setToolTipText("Enviar Mensagem");
		botaoSair = new JButton("Sair");
		botaoSair.setToolTipText("Sair do Chat");
		botaoEnviar.addActionListener(this);
		botaoSair.addActionListener(this);
		botaoEnviar.addKeyListener(this);
		message.addKeyListener(this);
		JScrollPane scroll = new JScrollPane(texto);
		texto.setLineWrap(true);
		content.add(historico);
		content.add(scroll);
		content.add(labelMessage);
		content.add(message);
		content.add(botaoSair);
		content.add(botaoEnviar);
		setTitle(textNome.getText());
		setContentPane(content);
		setLocationRelativeTo(null);
		setResizable(false);
		setSize(250, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void conectar() throws IOException {

		socket = new Socket(textIP.getText(), Integer.parseInt(textPorta.getText()));
		ou = socket.getOutputStream();
		ouw = new OutputStreamWriter(ou);
		bufferW = new BufferedWriter(ouw);
		bufferW.write(textNome.getText() + "\r\n");
		bufferW.flush();
	}

	public void enviarMensagem(String msg) throws IOException {

		if (msg.equals("Sair")) {
			bufferW.write("Desconectado \r\n");
			texto.append("Desconectado \r\n");
		} else {
			bufferW.write(msg + "\r\n");
			texto.append(textNome.getText() + " diz: " + message.getText() + "\r\n");
		}
		bufferW.flush();
		message.setText("");
	}

	public void escutar() throws IOException {

		InputStream in = socket.getInputStream();
		InputStreamReader inr = new InputStreamReader(in);
		BufferedReader bufferR = new BufferedReader(inr);
		String msg = "";

		while (!"Sair".equalsIgnoreCase(msg))

			if (bufferR.ready()) {
				msg = bufferR.readLine();
				if (msg.equals("Sair"))
					texto.append("Servidor caiu! \r\n");
				else
					texto.append(msg + "\r\n");
			}
	}

	public void sair() throws IOException {

		enviarMensagem("Sair");
		bufferW.close();
		ouw.close();
		ou.close();
		socket.close();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			if (e.getActionCommand().equals(botaoEnviar.getActionCommand()))
				enviarMensagem(message.getText());
			else if (e.getActionCommand().equals(botaoSair.getActionCommand()))
				sair();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			try {
				enviarMensagem(message.getText());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	public static void main(String[] args) throws IOException {

		ClientSocket app = new ClientSocket();
		app.conectar();
		app.escutar();
	}
}
