package noobspace.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class Interest
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;
	private String nom;
	private String description;
	
	public Interest(){}
	
	public Interest(String nom){
		this.setNom(nom);
	}
	public Interest(String nom,String description){
		this(nom);
		this.setDescription(description);
	}

	public String getNom()
	{
		return nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
	
	
}
