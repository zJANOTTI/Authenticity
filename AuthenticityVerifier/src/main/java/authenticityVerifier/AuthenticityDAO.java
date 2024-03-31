package authenticityVerifier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthenticityDAO {

	@Autowired JdbcTemplate jdbcTemplate;
	private String query = null;


//	public <array> AuthenticityDAO authenticateNumber(array phone_data){
//		query = "SELECT COMPANY FROM COMPANIES WHERE PHONENUMBER = PHONE_DATA";
//		try (Connection con = jdbcTemplate.getDataSource().getConnection();
//			 Statement stmt = con.createStatement();
//			 ResultSet rs = stmt.executeQuery(query);) {
//
//	        } catch (Exception ex) {
//	            System.err.println("Erro ao obter os dados:" + ex);
//
//	        }
//
//		return;
//
//		//@TODO Receive an array from the POST method and if the phone number is identified on our database return an array back.
		// Exaple below
//		 String sqlInsert = "INSERT INTO usuario (login, senha) VALUES (?, ?)";
//	        try (Connection con = jdbcTemplate.getDataSource().getConnection();
//	             PreparedStatement ps = con.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);) {
//				con.setAutoCommit(false);
//
//	            ps.setString(1, i.getUsuario());
//	            ps.setString(2, i.getSenha());
//	            int result = ps.executeUpdate();
//    }
}
