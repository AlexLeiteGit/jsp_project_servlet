package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

import java.io.IOException;

@WebServlet("/ServletLoginController")/*Mapeamento da URL que vem da tela*/
public class ServletLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletLoginController() {
        
    }

    /**
	 * goGet = RECEBE OS DADOS PELA URL EM PARÂMETROS
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * doPost = RECEBE OS DADOS ENVIADOS POR UM FORMULÁRIO
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		if(login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
		
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			
			System.out.println(modelLogin.getLogin());
			System.out.println(modelLogin.getSenha());
			
			if(modelLogin.getLogin().equalsIgnoreCase("admin") && modelLogin.getSenha().equalsIgnoreCase("admin")){
			
				request.getSession().setAttribute("usuario", modelLogin.getLogin());
				
				RequestDispatcher redirecionar = request.getRequestDispatcher("main/main.jsp");
				redirecionar.forward(request, response);
				
			} else {
				
				RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msg", "Informa o login e  senha novamente");
				redirecionar.forward(request, response);
				
			}
			
		
		} else {
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg", "Informe o login e senha novamente!");
			redirecionar.forward(request, response);
			
		}
		
	}

}