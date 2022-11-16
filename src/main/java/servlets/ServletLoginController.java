package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

import java.io.IOException;

import dao.DAOLoginRepository;

@WebServlet(urlPatterns={"/ServletLoginController", "/main/ServletLoginController"})/*Mapeamento da URL que vem da tela*/
public class ServletLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();    

    public ServletLoginController() {
        
    }

    /**
	 * goGet = RECEBE OS DADOS PELA URL EM PARÂMETROS
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");//parâmetro que será analisado para realização de determinada tarefa.
		
		if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
			
			System.out.println("Logout realizado com sucesso.");
			request.getSession().invalidate();//invalida a sessão e encerra o usuário
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			redirecionar.forward(request, response);
			
		} else {
			
			System.out.println("Voltando para o doPost() através do retorno do métdo doGet()");
			doPost(request, response);
			
		}
	}

	/**
	 * doPost = RECEBE OS DADOS ENVIADOS POR UM FORMULÁRIO
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		
		try {
		
		if(login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
		
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			
			System.out.println(modelLogin.getLogin());
			System.out.println(modelLogin.getSenha());
			
			//Simulação de validação do LOGIN
			if(daoLoginRepository.validarAutenticacao(modelLogin)){
			
				request.getSession().setAttribute("usuario", modelLogin.getLogin());
				
				if(url == null || url.equals("null")) {
					
					url = "/main/main.jsp";
					
				}
				
				RequestDispatcher redirecionar = request.getRequestDispatcher(url);
				redirecionar.forward(request, response);
				
			} else {
				
				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("msg", "Informa o login e  senha novamente");
				redirecionar.forward(request, response);
				
			}
			
		
		} else {
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg", "Informe o login e senha novamente!");
			redirecionar.forward(request, response);
			
		}
		
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
		
	}

}