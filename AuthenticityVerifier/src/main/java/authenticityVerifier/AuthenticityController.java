package authenticityVerifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class AuthenticityController {
	@Autowired
	AuthenticityDAO DAO;
	
	//@GetMapping("/noticias/users")
	//public ResponseEntity<ArrayList<PhoneCompany>> abrir(){
	//	return new ResponseEntity<ArrayList<PhoneCompany>>(dao.listarTodos(), HttpStatus.OK);
	//}

//	@PostMapping("/messages")
//	<phone_data>
//	AuthenticityCompaniesInformation AuthenticateMessages(@RequestBody AuthenticityCompaniesInformation phone_data) {
//		try {
//			return new AuthenticityCompaniesInformation(DAO.authenticateNumber(phone_data));
//		} catch (Exception e) {
//			return new AuthenticityCompaniesInformation(e.getMessage());
//		}
//	}
}