package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.SingleConnectionBanco;

@WebFilter("/main/")/*Intercepta todas as requisições que vierem do projeto ou mapeamento*/
public class FilterAutenticacao extends HttpFilter implements Filter {
       
	private static final long serialVersionUID = 1L;
	
	private static Connection connection;


	public FilterAutenticacao() {
        
    }


	public void destroy() {
		
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {

			HttpServletRequest req = (HttpServletRequest) request;/* Pega a requisição */

			HttpSession session = req.getSession();/* abre a sessão */

			String usuarioLogado = (String) session.getAttribute("usuario");/* pega o atributo a ser verificado */

			String urlParaAutenticar = req.getServletPath();/* URL que está sendo acessada */

			if (usuarioLogado == null
					&& !urlParaAutenticar.equalsIgnoreCase("/main/ServletLoginController")) {/* ainda não está logado */

				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "Por Favor, Realize o login!");
				redirecionar.forward(request, response);
				return;/* Para a execução e redireciona o login */

			} else {

				chain.doFilter(request, response);

			}

			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		System.out.println("Classe FilterAutenticacao funcionando corretamente!");

	}


	public void init(FilterConfig fConfig) throws ServletException {
		
		connection = SingleConnectionBanco.getConnection();
		
	}

}
