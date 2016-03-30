package noobspace.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class NoobspaceUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String firstName;
	private String mail;
	private String password;
	private Profile profil; // Âge, lieu de vie, intérêts...
	
	public NoobspaceUser(String name, String firstName, String mail, String password) {
		this.name = name;
		this.firstName = firstName;
		this.mail = mail;
		this.password = password;
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Profile getProfil() {
		return profil;
	}
	public void setProfil(Profile profil) {
		this.profil = profil;
	}
	
	
}
