package authenticityVerifier;

public class AuthenticityCompaniesInformation {
	private  String PhoneNumber;
	private  String CompanyName;

	//TODO create all the elements needed to retrieve CompanyName, CompanyImage(Maybe).

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String PhoneNumber) {
		this.PhoneNumber = PhoneNumber;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public void setCompanyName(String CompanyName) {
		this.CompanyName = CompanyName;
	}
}
