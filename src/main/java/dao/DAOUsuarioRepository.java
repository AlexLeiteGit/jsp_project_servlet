package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import beanDto.BeanGraficoSalarioUser;
import connection.SingleConnectionBanco;
import model.ModelLogin;
import model.ModelTelefone;

public class DAOUsuarioRepository {
	
	private Connection connection;

	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	//Método de Validação do LOGIN do usuário
	public boolean validarLogin(String login) throws Exception {
		String sql = "select count(1) > 0 as existe from model_login where upper(login) = upper('?');";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		resultado.next();/*para ele entrar nos resultados do SQL*/
		return resultado.getBoolean("existe");
	}
	
	//Método para GRAVAR e ATUALIZAR o usuário no BD
	public ModelLogin gravarUsuario(ModelLogin modelLogin, Long userLogado) throws Exception {
		
		//Método de gravar usuário
		if(modelLogin.isNovo() == true && userLogado != null) {
		
		String sql = "INSERT INTO model_login(login, senha, nome, email, usuario_id, perfil, sexo, cep, logradouro, numero, complemento, bairro, localidade, uf, datanascimento, rendamensal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		preparedSql.setString(1, modelLogin.getLogin());
		preparedSql.setString(2, modelLogin.getSenha());
		preparedSql.setString(3, modelLogin.getNome());
		preparedSql.setString(4, modelLogin.getEmail());
		preparedSql.setLong(5, userLogado);
		preparedSql.setString(6, modelLogin.getPerfil());
		preparedSql.setString(7, modelLogin.getSexo());
		
		preparedSql.setString(8, modelLogin.getCep());
		preparedSql.setString(9, modelLogin.getLogradouro());
		preparedSql.setString(10, modelLogin.getNumero());
		preparedSql.setString(11, modelLogin.getComplemento());
		preparedSql.setString(12, modelLogin.getBairro());
		preparedSql.setString(13, modelLogin.getLocalidade());
		preparedSql.setString(14, modelLogin.getUf());
		
		preparedSql.setDate(15, modelLogin.getDataNascimento());
		
		preparedSql.setDouble(16, modelLogin.getRendaMensal());
		
		preparedSql.execute();
		
		connection.commit();/*Salva os dados*/
		
		if (modelLogin.getFotouser() != null && !modelLogin.getFotouser().isEmpty()){
			
			sql = "UPDATE model_login set fotouser=?, extensaofotouser=? where login=?";
			
			preparedSql = connection.prepareStatement(sql);
			
			preparedSql.setString(1, modelLogin.getFotouser());
			preparedSql.setString(2, modelLogin.getExtensaoFotoUser());
			preparedSql.setString(3, modelLogin.getLogin());
			
			preparedSql.execute();
			
			connection.commit();
			
		}
		
		//Método de atualizar usuário
		} else {
			
			String sql = "UPDATE model_login SET login=?, senha=?, nome=?, email=?, perfil=?, sexo=?, cep=?, logradouro=?, numero=?, complemento=?, bairro=?, localidade=?, uf=?, datanascimento=?, rendamensal=? WHERE id = "+modelLogin.getId()+";";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, modelLogin.getLogin());
			statement.setString(2, modelLogin.getSenha());
			statement.setString(3, modelLogin.getNome());
			statement.setString(4, modelLogin.getEmail());
			statement.setString(5, modelLogin.getPerfil());
			statement.setString(6, modelLogin.getSexo());
			
			statement.setString(7, modelLogin.getCep());
			statement.setString(8, modelLogin.getLogradouro());
			statement.setString(9, modelLogin.getNumero());
			statement.setString(10, modelLogin.getComplemento());
			statement.setString(11, modelLogin.getBairro());
			statement.setString(12, modelLogin.getLocalidade());
			statement.setString(13, modelLogin.getUf());
			
			statement.setDate(14, modelLogin.getDataNascimento());
			
			statement.setDouble(15, modelLogin.getRendaMensal());
			
			statement.executeUpdate();
			
			connection.commit();
			
			if (modelLogin.getFotouser() != null && !modelLogin.getFotouser().isEmpty()){
				
				sql = "UPDATE model_login set fotouser=?, extensaofotouser=? where id=?";
				
				statement = connection.prepareStatement(sql);
				
				statement.setString(1, modelLogin.getFotouser());
				statement.setString(2, modelLogin.getExtensaoFotoUser());
				statement.setLong(3, modelLogin.getId());
				
				statement.execute();
				
				connection.commit();
				
			}
			
		}
		
		return this.consultaUsuarioPrimeira(modelLogin.getLogin(), userLogado);
		
	}

//	Método de Gravação de Usuário pelo Cadastro de Primeiro Acesso
//	public ModelLogin gravarUsuarioCadastro(ModelLogin modelLogin) throws Exception {
//			
//				String sql = "INSERT INTO model_login(login, senha, nome, email, perfil) VALUES (?, ?, ?, ?, ?);";
//				
//				PreparedStatement preparedSql = connection.prepareStatement(sql);
//				preparedSql.setString(1, modelLogin.getLogin());
//				preparedSql.setString(2, modelLogin.getSenha());
//				preparedSql.setString(3, modelLogin.getNome());
//				preparedSql.setString(4, modelLogin.getEmail());
//				preparedSql.setString(5, modelLogin.getPerfil());
//				
//				preparedSql.execute();
//				
//				connection.commit();/*Salva os dados*/
//				
//				return this.consultaUsuario(modelLogin.getLogin());
//				
//	}
				
	//Método de Confirmação de Deletar usuário
	public void deletarUsuario(String idUser) throws Exception {
		
		String sql = "DELETE FROM model_login WHERE id = ? and useradmin is false;";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setLong(1, Long.parseLong(idUser));
		
		statement.executeUpdate();
		
		connection.commit();
		
	}
	
	//Método para criar paginação da tela
	public int totalPaginas(Long userLogado) throws Exception {
		
		String sql = "select count(1) as total from model_login where usuario_id = " + userLogado;
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		resultado.next();//para evitar o estou na tela
		
		Double cadastros = resultado.getDouble("total");
		
		Double porPagina = 5.0;
		
		Double pagina = cadastros / porPagina;
		
		Double resto = pagina % 2;
		
		if(resto > 0) {
			pagina++;
		}
		
		return pagina.intValue();
		
	}
	
	//1º Método de Consulta de Usuário.
	public ModelLogin consultaUsuarioPrimeira(String login, Long userLogado) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where upper(login) = upper('"+login+"') and useradmin is false and usuario_id = " + userLogado;
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setSenha(resultSet.getString("senha"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setFotouser(resultSet.getString("fotouser"));
			
			modelLogin.setCep(resultSet.getString("cep"));
			modelLogin.setLogradouro(resultSet.getString("logradouro"));
			modelLogin.setNumero(resultSet.getString("numero"));
			modelLogin.setComplemento(resultSet.getString("complemento"));
			modelLogin.setBairro(resultSet.getString("bairro"));
			modelLogin.setLocalidade(resultSet.getString("localidade"));
			modelLogin.setUf(resultSet.getString("uf"));
			
			modelLogin.setDataNascimento(resultSet.getDate("datanascimento"));
			
			modelLogin.setRendaMensal(resultSet.getDouble("rendamensal"));
		}
		
		return modelLogin;
	}
	
	//2º Método de Consulta - Consulta do Botão Buscar do Modal
	public List<ModelLogin> consultaUsuarioList(String nome, Long userLogado) throws Exception{
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "select * from model_login where upper(nome) like upper(?) and useradmin is false and usuario_id = ? limit 5";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + nome + "%");/*Os % são utilizados por causa do operador LIKE*/
		statement.setLong(2, userLogado);
		
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()){
			
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setEmail(resultado.getString("email"));/*O retorno vem das colunas do banco de dados*/
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			//modelLogin.setSenha(resultado.getString("senha"));/*deixar de mostrar a senha por uma questão de segurança*/
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			
			retorno.add(modelLogin);
			
		}
		
		return retorno;		
	}
	
	//3º Método de Consulta - Consulta de Usuário por ID dentro do  Botão detalhar do Modal
	public ModelLogin consultaUsuarioID(String id, Long userLogado) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where id = ? and useradmin is false and usuario_id = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(id));
		statement.setLong(2, userLogado);
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setSenha(resultSet.getString("senha"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setFotouser(resultSet.getString("fotouser"));
			modelLogin.setExtensaoFotoUser(resultSet.getString("extensaofotouser"));
			
			modelLogin.setCep(resultSet.getString("cep"));
			modelLogin.setLogradouro(resultSet.getString("logradouro"));
			modelLogin.setNumero(resultSet.getString("numero"));
			modelLogin.setComplemento(resultSet.getString("complemento"));
			modelLogin.setBairro(resultSet.getString("bairro"));
			modelLogin.setLocalidade(resultSet.getString("localidade"));
			modelLogin.setUf(resultSet.getString("uf"));
			
			modelLogin.setDataNascimento(resultSet.getDate("datanascimento"));
			
			modelLogin.setRendaMensal(resultSet.getDouble("rendamensal"));
		}
		
		return modelLogin;
	}
	
	//4º Método de Consulta - Consulta de Usuários por JSTL
	public List<ModelLogin> consultaUsuarioListJstl(Long userLogado) throws Exception{
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "select * from model_login where useradmin is false and usuario_id = " + userLogado + " limit 5";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()){
			
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			
			retorno.add(modelLogin);
			
		}
		
		return retorno;		
	}
	
	//5º Método de Consulta - Consulta no ServletGenericUtil
	public ModelLogin consultaUsuario(String login) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where upper(login) = upper('"+login+"') and useradmin is false";
		
		PreparedStatement statement = connection.prepareStatement(sql);
				
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setSenha(resultSet.getString("senha"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setFotouser(resultSet.getString("fotouser"));
		}
		
		return modelLogin;
	}
	
	//6º Método de Consulta - Consulta no ServletGenericUtil o userLogado
	public ModelLogin consultaUsuarioLogado(String login) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where upper(login) = upper('"+login+"')";
		
		PreparedStatement statement = connection.prepareStatement(sql);
				
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setSenha(resultSet.getString("senha"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setUserAdmin(resultSet.getBoolean("useradmin"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setFotouser(resultSet.getString("fotouser"));
			
			modelLogin.setCep(resultSet.getString("cep"));
			modelLogin.setLogradouro(resultSet.getString("logradouro"));
			modelLogin.setNumero(resultSet.getString("numero"));
			modelLogin.setComplemento(resultSet.getString("complemento"));
			modelLogin.setBairro(resultSet.getString("bairro"));
			modelLogin.setLocalidade(resultSet.getString("localidade"));
			modelLogin.setUf(resultSet.getString("uf"));
			
			modelLogin.setDataNascimento(resultSet.getDate("datanascimento"));
			
			modelLogin.setRendaMensal(resultSet.getDouble("rendamensal"));
		}
		
		return modelLogin;
	}
	
	//7º Método de Consulta - Paginando a Consulta JSTL a 5 amostras por tela
	public List<ModelLogin> consultaUsuarioListPaginada(Long userLogado, Integer offset) throws Exception{
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "select * from model_login where useradmin is false and usuario_id = " + userLogado + " order by nome offset " + offset + " limit 5";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()){
			
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			
			retorno.add(modelLogin);
			
		}
		
		return retorno;		
	}
	
	//8º Método de Consulta - Método para paginação diretamente no modal
	public int consultaUsuarioListTotalPaginaPaginacao(String nome, Long userLogado) throws Exception{
		
		String sql = "select count(1) as total from model_login where upper(nome) like upper(?) and useradmin is false and usuario_id = ? ";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + nome + "%");/*Os % são utilizados por causa do operador LIKE*/
		statement.setLong(2, userLogado);
		
		ResultSet resultado = statement.executeQuery();
		
		resultado.next();//para evitar o estou na tela
		
		Double cadastros = resultado.getDouble("total");
		
		Double porPagina = 5.0;
		
		Double pagina = cadastros / porPagina;
		
		Double resto = pagina % 2;
		
		if(resto > 0) {
			pagina++;
		}
		
		return pagina.intValue();		
	}
	
	//9º Método de Consulta - Cosulta com retorno de OFFSET
	public List<ModelLogin> consultaUsuarioListOffset(String nome, Long userLogado, Integer offset) throws Exception{
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "select * from model_login where upper(nome) like upper(?) and useradmin is false and usuario_id = ? offset=? limit 5";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + nome + "%");/*Os % são utilizados por causa do operador LIKE*/
		statement.setLong(2, userLogado);
		statement.setInt(3, offset);
		
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()){
			
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setEmail(resultado.getString("email"));/*O retorno vem das colunas do banco de dados*/
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			
			retorno.add(modelLogin);
			
		}
		
		return retorno;		
	}
	
	//10º Método de Consulta - Consulta de Usuário por ID para utilização na Tabela de Telefones
	public ModelLogin consultaUsuarioIDTelefone(Long id) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where id = ? and useradmin is false";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, id);
				
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setSenha(resultSet.getString("senha"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			modelLogin.setFotouser(resultSet.getString("fotouser"));
			modelLogin.setExtensaoFotoUser(resultSet.getString("extensaofotouser"));
			
			modelLogin.setCep(resultSet.getString("cep"));
			modelLogin.setLogradouro(resultSet.getString("logradouro"));
			modelLogin.setNumero(resultSet.getString("numero"));
			modelLogin.setComplemento(resultSet.getString("complemento"));
			modelLogin.setBairro(resultSet.getString("bairro"));
			modelLogin.setLocalidade(resultSet.getString("localidade"));
			modelLogin.setUf(resultSet.getString("uf"));
			
			modelLogin.setDataNascimento(resultSet.getDate("datanascimento"));
			
			modelLogin.setRendaMensal(resultSet.getDouble("rendamensal"));
		}
		
		return modelLogin;
	}
	
	//11º Método de Consulta - Consulta Para Imprimir da Tela Relatório
	public List<ModelLogin> consultaUsuarioListRelatorio(Long userLogado) throws Exception {

		List<ModelLogin> retorno = new ArrayList<ModelLogin>();

		String sql = "select * from model_login where useradmin is false and usuario_id = " + userLogado;
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {

			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setDataNascimento(resultado.getDate("datanascimento"));
			
			modelLogin.setTelefones(this.listaTelefone(modelLogin.getId()));

			retorno.add(modelLogin);

		}

		return retorno;
	}
	
	
	//Polimorfismo da classe acima
	public List<ModelLogin> consultaUsuarioListRelatorio(Long userLogado, String dataInicial, String dataFinal) throws Exception {

		List<ModelLogin> retorno = new ArrayList<ModelLogin>();

		String sql = "select * from model_login where useradmin is false and usuario_id = " + userLogado + "and datanascimento >= ? and datanascimento <= ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setDate(1, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataInicial))));
		statement.setDate(2, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataFinal))));

		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {

			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setDataNascimento(resultado.getDate("datanascimento"));
			
			modelLogin.setTelefones(this.listaTelefone(modelLogin.getId()));

			retorno.add(modelLogin);

		}

		return retorno;
	}
	
	//12º Método de Consulta - Consulta de Telefone para o Relatório do Usuário
	public List<ModelTelefone> listaTelefone(Long idUserPai) throws Exception {
		
		List<ModelTelefone> retorno = new ArrayList<ModelTelefone>();
		
		String sql = "select * from telefone where usuario_pai_id = ?";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setLong(1, idUserPai);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while(rs.next()) {
			
			ModelTelefone modelTelefone = new ModelTelefone();
			
			modelTelefone.setId(rs.getLong("id"));
			modelTelefone.setNumero(rs.getString("numero"));
			modelTelefone.setUsuario_cad_id(this.consultaUsuarioIDTelefone(rs.getLong("usuario_cad_id")));
			modelTelefone.setUsuario_pai_id(this.consultaUsuarioIDTelefone(rs.getLong("usuario_pai_id")));
			
			retorno.add(modelTelefone);
						
		}
		
		return retorno;	
	}
	
	public BeanGraficoSalarioUser montarGraficoMediaSalario(Long userLogado)  throws Exception{
		
		String sql = "select avg(rendamensal) as media_salarial, perfil from model_login where usuario_id = ? group by perfil";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setLong(1, userLogado);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		List<String> perfils = new ArrayList<String>();/*Estas 2 listas são criadas para sincronizar o perfil com seu respectivo valor de salário médio*/
		List<Double> salarios = new ArrayList<Double>();/*Assim, no gráfico tudo ficará correto!*/
		
		BeanGraficoSalarioUser beanGraficoSalarioUser = new BeanGraficoSalarioUser();
		
		while(resultSet.next()) {
			
			Double media_salarial = resultSet.getDouble("media_salarial");
			String perfil = resultSet.getString("perfil");
			
			perfils.add(perfil);
			salarios.add(media_salarial);
			
		}
		
		beanGraficoSalarioUser.setPerfils(perfils);
		beanGraficoSalarioUser.setSalarios(salarios);
		
		return beanGraficoSalarioUser;
		
	}
	
	public BeanGraficoSalarioUser montarGraficoMediaSalario(Long userLogado, String dataInicial, String dataFinal) throws Exception{
		
		String sql = "select avg(rendamensal) as media_salarial, perfil from model_login where usuario_id = ? and datanascimento >= ? and datanascimento <= ? group by perfil";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setLong(1, userLogado);
		preparedStatement.setDate(2, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataInicial))));
		preparedStatement.setDate(3, Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataFinal))));
		
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		List<String> perfils = new ArrayList<String>();/*Estas 2 listas são criadas para sincronizar o perfil com seu respectivo valor de salário médio*/
		List<Double> salarios = new ArrayList<Double>();/*Assim, no gráfico tudo ficará correto!*/
		
		BeanGraficoSalarioUser beanGraficoSalarioUser = new BeanGraficoSalarioUser();
		
		while(resultSet.next()) {
			
			Double media_salarial = resultSet.getDouble("media_salarial");
			String perfil = resultSet.getString("perfil");
			
			perfils.add(perfil);
			salarios.add(media_salarial);
			
		}
		
		beanGraficoSalarioUser.setPerfils(perfils);
		beanGraficoSalarioUser.setSalarios(salarios);
		
		return beanGraficoSalarioUser;

	}
	
}
