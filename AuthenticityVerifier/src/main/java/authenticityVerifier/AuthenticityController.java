package authenticityVerifier;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@CrossOrigin
@RestController
public class AuthenticityController {
	@Autowired AuthenticityDAO DAO;

	@PostMapping("/messages")
	public String AuthenticateMessages(@RequestBody String body) {
		JSONObject objJSON = new JSONObject(body);
		return DAO.authenticateNumber(objJSON.getString("sender"));
	}
}