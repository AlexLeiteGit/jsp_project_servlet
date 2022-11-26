package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ModelLogin;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

import dao.DAOTelefoneRepository;
import dao.DAOUsuarioRepository;

@WebServlet("/ServletGenericUtil")
public class ServletGenericUtil extends HttpServlet implements Serializable{
	
	private static final long serialVersionUID = 1L;
       
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
    public ServletGenericUtil() {
        
    }
    
    public Long getUserLogado(HttpServletRequest request) throws Exception {
    	
    	HttpSession session = request.getSession();
    	String usuarioLogado = (String) session.getAttribute("usuario");
    	
    	return daoUsuarioRepository.consultaUsuarioLogado(usuarioLogado).getId();
    	
    }
    
    public ModelLogin getUserLogadoObjeto(HttpServletRequest request) throws Exception {
    	
    	HttpSession session = request.getSession();
    	String usuarioLogado = (String) session.getAttribute("usuario");
    	
    	return daoUsuarioRepository.consultaUsuarioLogado(usuarioLogado);
    	
    }

}
