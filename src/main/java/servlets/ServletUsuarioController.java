package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;

@WebServlet(urlPatterns = {"/ServletUsuarioController"})
public class ServletUsuarioController extends ServletGenericUtil {
	
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
				
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioListJstl(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);
				
				request.setAttribute("msg", "USUÁRIO EXCLUÍDO COM SUCESSO");
				request.getRequestDispatcher("main/usuario.jsp").forward(request, response);
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarAjax")) {
				
				String idUser = request.getParameter("id");
				daoUsuarioRepository.deletarUsuario(idUser);
				
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioListJstl(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);
				
				response.getWriter().write("USUÁRIO EXCLUÍDO COM SUCESSO");
			
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUsuarioAjax")) {
				
				String nomeBusca = request.getParameter("nomeBusca");
				
				List<ModelLogin> dadosJsonUser = daoUsuarioRepository.consultaUsuarioList(nomeBusca, super.getUserLogado(request));
				
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(dadosJsonUser);
				
				response.getWriter().write(json);
			
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {
				
				String id = request.getParameter("id");
				
				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(id, super.getUserLogado(request));
				
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioListJstl(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);
				
				request.setAttribute("msg", "USUÁRIO EM EDIÇÃO");
				request.setAttribute("modelLogin", modelLogin);
				request.getRequestDispatcher("main/usuario.jsp").forward(request, response);
			
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {
				
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioListJstl(super.getUserLogado(request));
				
				request.setAttribute("msg", "USUÁRIO CARREGADOS");
				request.setAttribute("modelLogins", modelLogins);
				request.getRequestDispatcher("main/usuario.jsp").forward(request, response);
				
			} else {
				
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioListJstl(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);
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
			modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin, super.getUserLogado(request));
		}
		
		List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioListJstl(super.getUserLogado(request));
		request.setAttribute("modelLogins", modelLogins);
		
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
