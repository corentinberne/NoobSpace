package noobspace.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.google.appengine.api.datastore.Key;
 
@Entity
public class Profile
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;
	private String			mail;
	private String			birthDate;
	private String			address;
	private String			codePostal;
	private String			city;
	private List<String>	interests;
	
	@OneToMany( targetEntity=Post.class )
	private List<Post>		myPosts;
	
	@OneToMany( targetEntity=Post.class )
	private List<Post>		postsFromFriends;

	public Profile(String mail, String birthDate, String address, String codePostal, String city)
	{
		this(mail);
		this.birthDate = birthDate;
		this.address = address;
		this.codePostal = codePostal;
		this.city = city;
	}

	public Profile(String mail)
	{
		super();
		this.mail = mail;
	}
	
	public String getBirthDate()
	{
		return birthDate;
	}

	public void setBirthDate(String birthDate)
	{
		this.birthDate = birthDate;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getCodePostal()
	{
		return codePostal;
	}

	public void setCodePostal(String codePostal)
	{
		this.codePostal = codePostal;
	}

	public String getCity()
	{
		System.out.println(city);
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public List<String> getInterests()
	{
		return interests;
	}

	public void setInterests(List<String> interests)
	{
		this.interests = interests;
	}

	public String getMail()
	{
		return mail;
	}

}
