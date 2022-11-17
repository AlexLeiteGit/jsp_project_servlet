package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import model.ModelLogin;

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
	public ModelLogin gravarUsuario(ModelLogin modelLogin) throws SQLException {
		
		//Método de gravar usuário
		if(modelLogin.isNovo() == true) {
		
		String sql = "INSERT INTO model_login(login, senha, nome, email) VALUES (?, ?, ?, ?);";
		
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		preparedSql.setString(1, modelLogin.getLogin());
		preparedSql.setString(2, modelLogin.getSenha());
		preparedSql.setString(3, modelLogin.getNome());
		preparedSql.setString(4, modelLogin.getEmail());
		
		preparedSql.execute();
		
		connection.commit();/*Salva os dados*/
		
		//Método de atualizar usuário
		} else if(modelLogin.isNovo() == false) {
			
			String sql = "UPDATE model_login SET login=?, senha=?, nome=?, email=? WHERE id = "+modelLogin.getId()+";";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, modelLogin.getLogin());
			statement.setString(2, modelLogin.getSenha());
			statement.setString(3, modelLogin.getNome());
			statement.setString(4, modelLogin.getEmail());
			
			statement.executeUpdate();
			
			connection.commit();
			
		}
		
		return this.consultaUsuarioPrimeira(modelLogin.getLogin());
		
	}
	
	//Método de Confirmação de Deletar usuário
	public void deletarUsuario(String idUser) throws Exception {
		
		String sql = "DELETE FROM model_login WHERE id = ?;";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setLong(1, Long.parseLong(idUser));
		
		statement.executeUpdate();
		
		connection.commit();
		
	}
	
	//1º Método de Consulta de Usuário.
	public ModelLogin consultaUsuarioPrimeira(String login) throws SQLException {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where upper(login) = upper('"+login+"');";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setSenha(resultSet.getString("senha"));
			modelLogin.setNome(resultSet.getString("nome"));
		}
		
		return modelLogin;
	}

}
