package handles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class ServerSocket extends Thread {
	private static ArrayList<BufferedWriter>clientes;           
	private static ServerSocket server; 
	private String nome;
	private Socket con;
	private InputStream inStr;  
	private InputStreamReader inr;  
	private BufferedReader bufferRe;
	
	public ServerSocket(Socket con){
		   this.con = con;
		   try {
			   inStr = con.getInputStream();
		         inr = new InputStreamReader(inStr);
		         bufferRe = new BufferedReader(inr);
		   } catch (IOException e) {
		          e.printStackTrace();
		   }                          
		}

	public void run(){
        
		  try{
		                                      
		    String msg;
		    OutputStream ou =  this.con.getOutputStream();
		    Writer w = new OutputStreamWriter(ou);
		    BufferedWriter bufferW = new BufferedWriter(w); 
		    clientes.add(bufferW);
		    nome = msg = bufferRe.readLine();
		               
		    while(!"Sair".equalsIgnoreCase(msg) && msg != null)
		      {           
		       msg = bufferRe.readLine();
		       sendToAll(bufferW, msg);
		       System.out.println(msg);                                              
		       }
		                                      
		   }catch (Exception e) {
		     e.printStackTrace();
		    
		   }                       
		}
	
	public void sendToAll(BufferedWriter bwSaida, String msg) throws  IOException 
	{
	  BufferedWriter bufferW;
	    
	  for(BufferedWriter bw : clientes){
		  bufferW = (BufferedWriter)bw;
	   if(!(bwSaida == bufferW)){
	     bw.write(nome + ": " + msg+"\r\n");
	     bw.flush(); 
	   }
	  }
	}
	
	public static void main(String []args) {
	    
		  try{
		    JLabel lblMessage = new JLabel("Porta do Servidor:");
		    JTextField txtPorta = new JTextField("12345");
		    Object[] texts = {lblMessage, txtPorta };  
		    JOptionPane.showMessageDialog(null, texts);
		    server = new ServerSocket(Integer.parseInt(txtPorta.getText()));
		    clientes = new ArrayList<BufferedWriter>();
		    JOptionPane.showMessageDialog(null,"Servidor iniciado porta: "+         
		    txtPorta.getText());
		    
		     while(true){
		       System.out.println("Aguardando conexão...");
		       Socket con = server.accept();
		       System.out.println("Usuário conectado...");
		       Thread t = new ServidorSocket(con);
		        t.start();   
		    }
		                              
		  }catch (Exception e) {
		    
		    e.printStackTrace();
		  }                       
		 }                   
}