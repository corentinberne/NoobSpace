package noobspace.model;

import java.io.Serializable;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.google.appengine.api.datastore.Key;
@Entity
public class Comment implements Serializable
{

    private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key	id;


	private String	message;
	private String	publicationDate;
	private String 	mailPublisher;
	
	
	@ManyToOne(fetch = FetchType.LAZY) 
	private Profile user;
	// Note

	public Comment(String message, String date, String publisherMail)
	{
		super();
		this.message = message;
		this.publicationDate = date;
		this.mailPublisher = publisherMail;
	}
	public Key getId() {
	    return id;
	}

	public void setId(Key id) {
	    this.id = id;
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

	public String getMailPublisher() {
	    return mailPublisher;
	}

	public void setMailPublisher(String mailPublisher) {
	    this.mailPublisher = mailPublisher;
	}

}
