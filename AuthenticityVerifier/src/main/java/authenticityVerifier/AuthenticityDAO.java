package authenticityVerifier;

import java.sql.*;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthenticityDAO {

	@Autowired JdbcTemplate jdbcTemplate;
	private String query = null;


	public String authenticateNumber(String phone_number) {
		query = "SELECT COMPANY FROM COMPANIES WHERE TELEPHONE = ?";
		JSONObject jsonResponse = new JSONObject();
		try  {
			Connection con = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, phone_number);
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				String company = result.getString("COMPANY");
				System.out.println("Phone number found in the database. Company: " + company);

				jsonResponse.put("company", company);
				jsonResponse.put("status", true);

				return jsonResponse.toString();
			}

		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados:" + ex);

		}

		jsonResponse.put("status", false);
		return jsonResponse.toString();
	}
}
