package servlets;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.apache.tomcat.jakartaee.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.ModelLogin;
import util.ReportUtil;

@MultipartConfig
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
				request.setAttribute("totalPaginas", daoUsuarioRepository.totalPaginas(this.getUserLogado(request)));
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
				
				/*As '' estão ai pois este método retorna String e a consulta retorna um int. Para acabarmos com o erro devemos concatenar o int com uma String vazia ("")*/
				response.addHeader("totalPaginas", "" + daoUsuarioRepository.consultaUsuarioListTotalPaginaPaginacao(nomeBusca, this.getUserLogado(request)));
				
				response.getWriter().write(json);
				
				System.out.println("Fim do ServletUsuarioController - buscarUsuarioAjax!");
			
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUsuarioAjaxPage")) {
				
				System.out.println("Inicio do ServletUsuarioController - buscarUsuarioAjaxPage!");
				
				String nomeBusca = request.getParameter("nomeBusca");
				String pagina = request.getParameter("pagina");
				
				List<ModelLogin> dadosJsonUser = daoUsuarioRepository.consultaUsuarioListOffset(nomeBusca, super.getUserLogado(request), Integer.parseInt(pagina));
				
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(dadosJsonUser);
				
				/*As '' estão ai pois este método retorna String e a consulta retorna um int. Para acabarmos com o erro devemos concatenar o int com uma String vazia ("")*/
				response.addHeader("totalPaginas", "" + daoUsuarioRepository.consultaUsuarioListTotalPaginaPaginacao(nomeBusca, this.getUserLogado(request)));
				response.getWriter().write(json);
			
				System.out.println("Fim do ServletUsuarioController - buscarUsuarioAjaxPage!");
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {
				
				System.out.println("Inicio do ServletUsuarioController - buscarEditar!");
				
				String id = request.getParameter("id");
				
				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(id, super.getUserLogado(request));
				
				List<ModelLogin> listaModel = daoUsuarioRepository.consultaUsuarioListJstl(super.getUserLogado(request));
				request.setAttribute("listaModel", listaModel);
				
				System.out.println(modelLogin);
				
				request.setAttribute("msg", "USUÁRIO EM EDIÇÃO");
				request.setAttribute("totalPaginas", daoUsuarioRepository.totalPaginas(this.getUserLogado(request)));
				request.setAttribute("modelLogin", modelLogin);
				request.getRequestDispatcher("main/usuario.jsp").forward(request, response);
				
				System.out.println("Fim do ServletUsuarioController - buscarEditar!");
			
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {
				
				System.out.println("Inicio do ServletUsuarioController - listarUser!");
				
				List<ModelLogin> listaModel = daoUsuarioRepository.consultaUsuarioListJstl(super.getUserLogado(request));
				
				request.setAttribute("msg", "USUÁRIO CARREGADOS");
				request.setAttribute("totalPaginas", daoUsuarioRepository.totalPaginas(this.getUserLogado(request)));
				request.setAttribute("listaModel", listaModel);
				request.getRequestDispatcher("main/usuario.jsp").forward(request, response);
				
				System.out.println("Fim do ServletUsuarioController - listarUser!");
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("downloadFoto")) {
				
				System.out.println("Inicio do ServletUsuarioController - downloadFoto");
				
				String idUser = request.getParameter("id");
				
				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(idUser, super.getUserLogado(request));
				
				if(modelLogin.getFotouser() != null && !modelLogin.getFotouser().isEmpty()) {
					response.setHeader("Content-Disposition", "attachment;filename=arquivo." + modelLogin.getExtensaoFotoUser());
					response.getOutputStream().write(new Base64().decodeBase64(modelLogin.getFotouser().split("\\,")[1]));
				}
				
				System.out.println("Fim do ServletUsuarioController - downloadFoto");
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("paginar")) {
				
				Integer offset = Integer.parseInt(request.getParameter("pagina"));
				
				List<ModelLogin> listaModel = daoUsuarioRepository.consultaUsuarioListPaginada(this.getUserLogado(request), offset);
				request.setAttribute("listaModel", listaModel);
				request.setAttribute("totalPaginas", daoUsuarioRepository.totalPaginas(this.getUserLogado(request)));
				request.getRequestDispatcher("main/usuario.jsp").forward(request, response);
				
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorioUser")) {
				
				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");
				
				System.out.println("Tudo funcionando corretamente ate agora!");
				
				if(dataInicial == null || dataInicial.isEmpty() && dataFinal == null || dataFinal.isEmpty()){
					
					request.setAttribute("listaUser", daoUsuarioRepository.consultaUsuarioListRelatorio(super.getUserLogado(request)));
					
				} else {
					
					request.setAttribute("listaUser", daoUsuarioRepository.consultaUsuarioListRelatorio(super.getUserLogado(request), dataInicial, dataFinal));
					
				}
				
				request.setAttribute("dataInicial", dataInicial);
				request.setAttribute("dataFinal", dataFinal);
				request.getRequestDispatcher("main/reluser.jsp").forward(request, response);
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorioPDF") || acao.equalsIgnoreCase("imprimirRelatorioExcel")) {
				
				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");
				
				List<ModelLogin> modelLogins = null;
				
				if(dataInicial == null || dataInicial.isEmpty() && dataFinal == null || dataFinal.isEmpty()){
					
					modelLogins = daoUsuarioRepository.consultaUsuarioListRelatorio(super.getUserLogado(request));
					
				} else {
					
					modelLogins = daoUsuarioRepository.consultaUsuarioListRelatorio(super.getUserLogado(request), dataInicial, dataFinal);
					
				}
				
				HashMap<String, Object> params = new HashMap<String, Object>();
				params.put("PARAM_SUB_REPORT", request.getServletContext().getRealPath("relatorio") + File.separator);
				
				byte[] relatorio = null;
				
				String extensao = "";
				
				if(acao.equalsIgnoreCase("imprimirRelatorioPDF")) {
					
					relatorio = new ReportUtil().geraRelatorioPDF(modelLogins, "relatorio-usuario-jsp", params, request.getServletContext());
					extensao = "pdf";
				
				} else if(acao.equalsIgnoreCase("imprimirRelatorioExcel")) {
					
					relatorio = new ReportUtil().geraRelatorioExcel(modelLogins, "relatorio-usuario-jsp", params, request.getServletContext());
					extensao = "xls";
					
				}
				
				response.setHeader("Content-Disposition", "attachment;filename=arquivo." + extensao);
				response.getOutputStream().write(relatorio);
				
			} else {
				
				System.out.println("Inicio do ServletUsuarioController - ELSE!");
				
				List<ModelLogin> listaModel = daoUsuarioRepository.consultaUsuarioListJstl(super.getUserLogado(request));
				request.setAttribute("totalPaginas", daoUsuarioRepository.totalPaginas(this.getUserLogado(request)));
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
		
		String cep = request.getParameter("cep");
		String logradouro = request.getParameter("logradouro");
		String numero = request.getParameter("numero");
		String complemento = request.getParameter("complemento");
		String bairro = request.getParameter("bairro");
		String localidade = request.getParameter("localidade");
		String uf = request.getParameter("uf");
		
		String dataNascimento = request.getParameter("dataNascimento");
		
		String rendaMensal = request.getParameter("rendaMensal");		
		//rendaMensal = rendaMensal.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");
		
		ModelLogin modelLogin = new ModelLogin();
		
		modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
		modelLogin.setNome(nome);
		modelLogin.setLogin(login);
		modelLogin.setSenha(senha);
		modelLogin.setEmail(email);
		modelLogin.setPerfil(perfil);
		modelLogin.setSexo(sexo);
		
		modelLogin.setCep(cep);
		modelLogin.setLogradouro(logradouro);
		modelLogin.setNumero(numero);
		modelLogin.setComplemento(complemento);
		modelLogin.setBairro(bairro);
		modelLogin.setLocalidade(localidade);
		modelLogin.setUf(uf);
		
		modelLogin.setDataNascimento(Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataNascimento))));
		
		modelLogin.setRendaMensal(Double.valueOf(rendaMensal));
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			Part part = request.getPart("fileFoto");/*Pega a foto da tela*/
			
			if(part.getSize() > 0) {
				byte[] foto = IOUtils.toByteArray(part.getInputStream());/*converte a imagem em um array de bytes*/
				String imagemBase64 = "data:image/" + part.getContentType().split("\\/")[1] + ";base64," + new Base64().encodeBase64String(foto);
				
				System.out.println(imagemBase64);
				
				modelLogin.setFotouser(imagemBase64);
				modelLogin.setExtensaoFotoUser(part.getContentType().split("\\/")[1]);
			}
		}

		
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
		request.setAttribute("totalPaginas", daoUsuarioRepository.totalPaginas(this.getUserLogado(request)));
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
