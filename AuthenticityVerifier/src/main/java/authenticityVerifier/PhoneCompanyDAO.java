package authenticityVerifier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PhoneCompanyDAO {

	@Autowired JdbcTemplate jdbcTemplate;
	private String query = null;


	public <array> PhoneCompanyDAO authenticateNumber(array phone_data){
		query = "select * from Usuario";
		try (Connection con = jdbcTemplate.getDataSource().getConnection();
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);) {

	        } catch (Exception ex) {
	            System.err.println("Erro ao obter os dados:" + ex);

	        }

		return;

		//@TODO Receive an array from the POST method and if the phone number is identified on our database return an array back.

    }
}
