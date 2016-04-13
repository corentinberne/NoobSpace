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

    
    /*
     * List des posts de nos amis à notre propos (sur notre mur)
     */
    @OneToMany(mappedBy = "user", targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> commentsFromFriends;

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
	this.commentsFromFriends = new ArrayList<Comment>();
    }


    public Key getId() {
        return id;
    }

    public void setId(Key id) {
        this.id = id;
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
	this.myPosts.remove(p);
    }

    public List<Comment> getPostsFromFriends() {
	return commentsFromFriends;
    }

    public void setPostsFromFriends(List<Comment> postsFromFriends) {
	this.commentsFromFriends = postsFromFriends;
    }

    public void addCommentFromFriends(Comment p) {
	if (this.commentsFromFriends == null)
	    this.commentsFromFriends = new ArrayList<Comment>();
	this.commentsFromFriends.add(p);
    }
    
    public void removeComment(Comment p) {
	this.commentsFromFriends.remove(p);
    }

}
