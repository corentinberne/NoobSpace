package noobspace.model;

import java.util.List;

public class Profile {
	private String birthDate;
	private String address;
	private String codePostal;
	private String city;
	private List<String> interests;
	private List<Post> myPosts;
	private List<Post> postsFromFriends;
	
	public Profile(String birthDate, String address, String codePostal,
			String city) {
		super();
		this.birthDate = birthDate;
		this.address = address;
		this.codePostal = codePostal;
		this.city = city;
	}
	
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public List<String> getInterests() {
		return interests;
	}
	public void setInterests(List<String> interests) {
		this.interests = interests;
	}
	
	
}
