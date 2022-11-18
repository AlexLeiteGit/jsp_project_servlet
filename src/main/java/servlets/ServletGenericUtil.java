package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

import dao.DAOUsuarioRepository;

/**
 * Servlet implementation class ServletGenericUtil
 */
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
