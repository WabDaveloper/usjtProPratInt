package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modelo.Usuario;
import Service.PaisService;
import criptografia.CryptoMethod;

/**
 * Servlet implementation class LoginPaisController
 */
@WebServlet("/login_pais.do")
public class LoginPaisController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String pUsuario = request.getParameter("usuario");
		String pSenha =request.getParameter("senha");
		String pAcao = request.getParameter("acao");
		
		
		PaisService ps = new PaisService();
		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession();
		Usuario user = new Usuario();
		user = ps.logar(pUsuario);
		CryptoMethod c = new CryptoMethod();
		user = ps.logar(pUsuario);
		
		try {
			String criptografa = c.criptografa(pSenha);
			System.out.println("criptografada: "+ criptografa);
			System.out.println(user.getSenha());
			
			
			if(criptografa.equals(user.getSenha())) {
				request.setAttribute("user", user);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			else {
				
				response.sendRedirect("login.jsp");  

				
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
			
		
	}
	private String getDateTime(){ 
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
		Date date = new Date(); 
		return dateFormat.format(date); 
	}
	
}
