package noobspace.model;

import java.io.Serializable;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Post implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int	id;
	private String	message;
	private String	publicationDate;
	@ManyToOne(fetch = FetchType.LAZY) private Profile user;
	// Note

	public Post(String message)
	{
		super();
		this.message = message;
	}
	
	public Post(String message, String date)
	{
		super();
		this.message = message;
		this.publicationDate = date;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getPublicationDate()
	{
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate)
	{
		this.publicationDate = publicationDate;
	}

}
