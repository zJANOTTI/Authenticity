package authenticityVerifier;

import java.util.ArrayList;

import authenticityVerifier.Login.LoginEmail;
import authenticityVerifier.Login.LoginUsername;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class PhoneCompanyControl {
	@Autowired PhoneCompanyDAO dao;
	
	//@GetMapping("/noticias/users")
	//public ResponseEntity<ArrayList<PhoneCompany>> abrir(){
	//	return new ResponseEntity<ArrayList<PhoneCompany>>(dao.listarTodos(), HttpStatus.OK);
	//}

	@PostMapping("/authenticate/phone_number")
	<phone_data>
	PhoneCompany Authenticate(@RequestBody PhoneCompany phone_data) {
		try {
			return new PhoneCompany(dao.authenticateNumber(phone_data));
		} catch (Exception e) {
			return new PhoneCompany(e.getMessage());
		}
	}

	//
}