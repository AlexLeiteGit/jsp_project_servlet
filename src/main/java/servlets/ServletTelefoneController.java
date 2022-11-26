package servlets;

import java.io.IOException;
import java.util.List;

import dao.DAOTelefoneRepository;
import dao.DAOUsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;
import model.ModelTelefone;

@WebServlet("/ServletTelefoneController")
public class ServletTelefoneController extends ServletGenericUtil {
	
	private static final long serialVersionUID = 1L;
	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	private DAOTelefoneRepository daoTelefoneRepository = new DAOTelefoneRepository();
       
    public ServletTelefoneController() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String acao = request.getParameter("acao");
			
			if(acao != null && !acao.isEmpty() && acao.equals("excluir")) {
				
				String idFone = request.getParameter("id");
				
				daoTelefoneRepository.deleteFone(Long.parseLong(idFone));
				
				String userPai = request.getParameter("userpai");
				
				ModelLogin modelLoginTelefone = daoUsuarioRepository.consultaUsuarioIDTelefone(Long.parseLong(userPai));
				
				List<ModelTelefone> listaModelTelefones = daoTelefoneRepository.listTelefone(modelLoginTelefone.getId());			
				request.setAttribute("listaModelTelefones", listaModelTelefones);
				
				request.setAttribute("msg", "Telefone Excluído!");
				request.setAttribute("modelLoginTelefone", modelLoginTelefone);
				request.getRequestDispatcher("main/telefone.jsp").forward(request, response);
				
				return;
			
			}
				
			String idUser = request.getParameter("idUser");
			
			if(idUser != null && !idUser.isEmpty()) {
			
				ModelLogin modelLoginTelefone = daoUsuarioRepository.consultaUsuarioIDTelefone(Long.parseLong(idUser));
				
				List<ModelTelefone> listaModelTelefones = daoTelefoneRepository.listTelefone(modelLoginTelefone.getId());			
				request.setAttribute("listaModelTelefones", listaModelTelefones);
				
				request.setAttribute("modelLoginTelefone", modelLoginTelefone);
				request.getRequestDispatcher("main/telefone.jsp").forward(request, response);
			
			} else {
				
				List<ModelLogin> listaModel = daoUsuarioRepository.consultaUsuarioListJstl(super.getUserLogado(request));
				request.setAttribute("listaModel", listaModel);			
				request.setAttribute("totalPaginas", daoUsuarioRepository.totalPaginas(this.getUserLogado(request)));
				request.getRequestDispatcher("main/usuario.jsp").forward(request, response);
			}
			
			} catch (Exception e) {
				
				e.printStackTrace();
				
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String usuario_pai_id = request.getParameter("id");
			String numero = request.getParameter("numero");
			
			ModelTelefone modelTelefone = new ModelTelefone();
			
			modelTelefone.setNumero(numero);
			modelTelefone.setUsuario_pai_id(daoUsuarioRepository.consultaUsuarioIDTelefone(Long.parseLong(usuario_pai_id)));
			modelTelefone.setUsuario_cad_id(super.getUserLogadoObjeto(request));
			
			daoTelefoneRepository.gravaTelefone(modelTelefone);
			
			List<ModelTelefone> listaModelTelefones = daoTelefoneRepository.listTelefone(Long.parseLong(usuario_pai_id));
			request.setAttribute("listaModelTelefones", listaModelTelefones);
			
			ModelLogin modelLoginTelefone = daoUsuarioRepository.consultaUsuarioIDTelefone(Long.parseLong(usuario_pai_id));
			request.setAttribute("modelLoginTelefone", modelLoginTelefone);
			
			request.setAttribute("msg", "Telefone Salvo com Sucesso!");
			request.getRequestDispatcher("main/telefone.jsp").forward(request, response);
			
			} catch (Exception e) {

				e.printStackTrace();
				
			}
		
	}

}
