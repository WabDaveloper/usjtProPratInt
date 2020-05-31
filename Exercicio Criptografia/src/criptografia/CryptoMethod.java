package criptografia;
import java.io.File;

public class CryptoMethod {
	public static String criptografa(String senha) throws Exception {

		File arquivo = new File("C:\Users\letic\Desktop\Anderson\USJT\Keys","key.txt");
		String sMsgClara = senha;
		String sMsgCifrada = null;
		String sMsgDecifrada = null;
		byte[] bMsgClara = null;
		byte[] bMsgCifrada = null;
		byte[] bMsgDecifrada = null;
		Impressora prn = new Impressora();

		System.out.println("-------------------------------------------------------------- -");

		System.out.println(">>> Imprimindo mensagem original...");
		System.out.println("");

		bMsgClara = sMsgClara.getBytes("ISO-8859-1"); 
		System.out.println("Mensagem Clara (Hexadecimal):");

		System.out.print(prn.hexBytesToString(bMsgClara));
		System.out.println("");

		System.out.println("Mensagem Clara (String):");

		System.out.println(sMsgClara);
		System.out.println("");

		System.out.println(">>> Cifrando com o algoritmo AES...");
		System.out.println("");
		CryptoAES caes = new CryptoAES();
		caes.geraCifra(bMsgClara, arquivo); 										
		bMsgCifrada = caes.getTextoCifrado();
		sMsgCifrada = (new String(bMsgCifrada, "ISO-8859-1")); 
		System.out.println("Mensagem Cifrada (Hexadecimal):");
		System.out.print(prn.hexBytesToString(bMsgCifrada));
		System.out.println("");
		System.out.println("Mensagem Cifrada (String):");
		System.out.println(sMsgCifrada);
		System.out.println("");


		return sMsgCifrada;
	}
}