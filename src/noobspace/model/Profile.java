package noobspace.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.google.appengine.api.datastore.Key;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key id;
    private String mail;
    private String birthDate;
    private String address;
    private String codePostal;
    private String city;
    private List<String> interests;

    @OneToMany(mappedBy = "user", targetEntity = Post.class, cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Post> myPosts;

    @OneToMany(mappedBy = "user", targetEntity = Post.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> postsFromFriends;

    public Profile(String mail, String birthDate, String address, String codePostal, String city) {
	this(mail);
	this.birthDate = birthDate;
	this.address = address;
	this.codePostal = codePostal;
	this.city = city;
	// this.postsFromFriends = new ArrayList<Post>();
    }

    public Profile(String mail) {
	super();
	this.mail = mail;
	this.myPosts = new ArrayList<Post>();
	this.postsFromFriends = new ArrayList<Post>();
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

    public boolean searchInterest(String interest) {
	for (String currentInterest : interests) {
	    if (currentInterest.equals(interest))
		return true;
	}
	return false;
    }
    
    public void addInterest(String interest){
    	if(!searchInterest(interest)){
    		interests.add(interest);
    	}
    }
    
    public void removeInterest(String interest){
    	for( int i = 0; i < this.interests.size(); i++)
			if (this.interests.get(i).equals(interest)) {
				this.interests.remove(i);
				return;
			}
    }

    public String getCity() {
	System.out.println(city);
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

    public String getMail() {
	return mail;
    }

    public List<Post> getMyPosts() {
	return myPosts;
    }

    public void setMyPosts(List<Post> myPosts) {
	this.myPosts = myPosts;
    }

    public void addMyPosts(Post p) {
	if (this.myPosts == null)
	    this.myPosts = new ArrayList<Post>();
	this.myPosts.add(p);
    }

    public void removeMyPosts(Post p) {
	int i = 0;
	while (i < this.myPosts.size()) {
	    if (this.myPosts.get(i).equals(p))
		break;
	    i++;
	}
	if (i < this.myPosts.size())
	    this.myPosts.remove(i);
    }

    public List<Post> getPostsFromFriends() {
	return postsFromFriends;
    }

    public void setPostsFromFriends(List<Post> postsFromFriends) {
	this.postsFromFriends = postsFromFriends;
    }

    public void addPostsFromFriends(Post p) {
	this.postsFromFriends.add(p);
    }

}
