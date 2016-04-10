package noobspace.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int		id;
	private String	message;
	private String	publicationDate;

	// Note

	public Post(String message)
	{
		super();
		this.message = message;
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
