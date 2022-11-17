package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

import java.io.IOException;

import dao.DAOUsuarioRepository;


public class ServletUsuarioController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
       

    public ServletUsuarioController() {
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			String acao = request.getParameter("acao");/*parâmetro hidden que criamos dentro do formulário*/
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
				String idUser = request.getParameter("id");
				daoUsuarioRepository.deletarUsuario(idUser);
				request.setAttribute("msg", "usuário EXCLUÍDO COM SUCESSO!!!");
				request.getRequestDispatcher("main/usuario.jsp").forward(request, response);
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarAjax")) {
				
				String idUser = request.getParameter("id");
				daoUsuarioRepository.deletarUsuario(idUser);
				
				response.getWriter().write("usuário EXCLUÍDO COM SUCESSO!!!");
			
			} else {
				
				request.getRequestDispatcher("main/usuario.jsp").forward(request, response);
			
			}
			
			} catch (Exception e) {
				
				e.printStackTrace();
				RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
				request.setAttribute("msg", e.getMessage());
				redirecionar.forward(request, response);
			
			}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
		String msg = "Operação realizada com sucesso!";
		
		System.out.println("Começamos a usar a ServletUsuarioController");
		
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");
		
		ModelLogin modelLogin = new ModelLogin();
		
		modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
		modelLogin.setNome(nome);
		modelLogin.setLogin(login);
		modelLogin.setSenha(senha);
		modelLogin.setEmail(email);
		
		if (daoUsuarioRepository.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
			msg = "Usuário já existente com este LOGIN, informe outro por favor!";
		} else {
			if(modelLogin.isNovo()) {
				msg = "Usuário Gravado com Sucesso!";
			} else {
				msg = "Usuário Atualizado com Sucesso!";
			}
			modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin);
		}
		
		RequestDispatcher redirecionar = request.getRequestDispatcher("main/usuario.jsp");
		request.setAttribute("msg", "Operação Realizada com Sucesso!");
		request.setAttribute("modelLogin", modelLogin);//o primeiro parâmetro é o nome e o segundo o objeto modelLogin que estamos usando acima. Ele é que será retornado.
		redirecionar.forward(request, response);
		
		System.out.println("Terminamos de usar a ServletUsuarioController");
		
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

}
