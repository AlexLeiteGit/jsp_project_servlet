package servlets;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

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
				
				System.out.println("Inicio do ServletUsuarioController - deletar!");
				
				String idUser = request.getParameter("id");
				daoUsuarioRepository.deletarUsuario(idUser);
				
				List<ModelLogin> listaModel = daoUsuarioRepository.consultaUsuarioListJstl(super.getUserLogado(request));
				request.setAttribute("listaModel", listaModel);
				
				request.setAttribute("msg", "USUÁRIO EXCLUÍDO COM SUCESSO");
				request.getRequestDispatcher("main/usuario.jsp").forward(request, response);
				
				System.out.println("Fim do ServletUsuarioController - deletar!");
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarAjax")) {
				
				System.out.println("Inicio do ServletUsuarioController - deletarAjax!");
				
				String idUser = request.getParameter("id");
				daoUsuarioRepository.deletarUsuario(idUser);
				
				List<ModelLogin> listaModel = daoUsuarioRepository.consultaUsuarioListJstl(super.getUserLogado(request));
				request.setAttribute("listaModel", listaModel);
				
				response.getWriter().write("USUÁRIO EXCLUÍDO COM SUCESSO");
				
				System.out.println("Fim do ServletUsuarioController - deletarAjax!");
			
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUsuarioAjax")) {
				
				System.out.println("Inicio do ServletUsuarioController - buscarUsuarioAjax!");
				
				String nomeBusca = request.getParameter("nomeBusca");
				
				List<ModelLogin> dadosJsonUser = daoUsuarioRepository.consultaUsuarioList(nomeBusca, super.getUserLogado(request));
				
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(dadosJsonUser);
				
				response.getWriter().write(json);
				
				System.out.println("Fim do ServletUsuarioController - buscarUsuarioAjax!");
			
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {
				
				System.out.println("Inicio do ServletUsuarioController - buscarEditar!");
				
				String id = request.getParameter("id");
				
				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(id, super.getUserLogado(request));
				
				List<ModelLogin> listaModel = daoUsuarioRepository.consultaUsuarioListJstl(super.getUserLogado(request));
				request.setAttribute("listaModel", listaModel);
				
				System.out.println(modelLogin);
				
				request.setAttribute("msg", "USUÁRIO EM EDIÇÃO");
				request.setAttribute("modelLogin", modelLogin);
				request.getRequestDispatcher("main/usuario.jsp").forward(request, response);
				
				System.out.println("Fim do ServletUsuarioController - buscarEditar!");
			
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {
				
				System.out.println("Inicio do ServletUsuarioController - listarUser!");
				
				List<ModelLogin> listaModel = daoUsuarioRepository.consultaUsuarioListJstl(super.getUserLogado(request));
				
				request.setAttribute("msg", "USUÁRIO CARREGADOS");
				request.setAttribute("listaModel", listaModel);
				request.getRequestDispatcher("main/usuario.jsp").forward(request, response);
				
				System.out.println("Fim do ServletUsuarioController - listarUser!");
				
			} else {
				
				System.out.println("Inicio do ServletUsuarioController - ELSE!");
				
				List<ModelLogin> listaModel = daoUsuarioRepository.consultaUsuarioListJstl(super.getUserLogado(request));
				request.setAttribute("listaModel", listaModel);
				request.getRequestDispatcher("main/usuario.jsp").forward(request, response);
			
				System.out.println("Fim do ServletUsuarioController - ELSE!");
				
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
		
		System.out.println("Iniciamos a usar a ServletUsuarioController");
		
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String email = request.getParameter("email");
		String perfil = request.getParameter("perfil");
		String sexo = request.getParameter("sexo");
		
		ModelLogin modelLogin = new ModelLogin();
		
		modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
		modelLogin.setNome(nome);
		modelLogin.setLogin(login);
		modelLogin.setSenha(senha);
		modelLogin.setEmail(email);
		modelLogin.setPerfil(perfil);
		modelLogin.setSexo(sexo);

		
		if (daoUsuarioRepository.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
			msg = "Usuário já existente com este LOGIN, informe outro por favor!";
		} else {
			if(modelLogin.isNovo()) {
				
				msg = "Usuário Gravado com Sucesso!";
				modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin, super.getUserLogado(request));			
				/*
				@RequestMapping(value="/main/cadastro/{id}");
				if(modelLogin.getId() ==  null) {
					msg = "Usuário Cadastrado com Sucesso!";
					modelLogin = daoUsuarioRepository.gravarUsuarioCadastro(modelLogin);
				} 
				
				@RequestMapping(value="/main/usuario.jsp(id)");
				if (modelLogin.getId() ==  null){
					msg = "Usuário Gravado com Sucesso!";
					modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin, super.getUserLogado(request));
				}
				*/
			} else {
				msg = "Usuário Atualizado com Sucesso!";
				modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin, super.getUserLogado(request));
			}
			
		}
		
		List<ModelLogin> listaModel = daoUsuarioRepository.consultaUsuarioListJstl(super.getUserLogado(request));
		request.setAttribute("listaModel", listaModel);
		
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
