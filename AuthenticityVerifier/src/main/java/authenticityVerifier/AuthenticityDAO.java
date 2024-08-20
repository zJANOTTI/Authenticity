package authenticityVerifier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthenticityDAO {

	@Autowired JdbcTemplate jdbcTemplate;
	private String query = null;


	public Boolean authenticateNumber(String phone_number) {
		query = "SELECT COMPANY FROM COMPANIES WHERE TELEPHONE = ?";
		try  {
			Connection con = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, phone_number);
			ResultSet a = stmt.executeQuery();
			//see if result encountered the phono_number on the database
			System.out.println(a);
			return true;

		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados:" + ex);

		}
		return false;
	}
}
