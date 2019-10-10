package business;

import java.io.Serializable;

final public class Author extends Person implements Serializable {
	private String bio;
	public String getBio() {
		return bio;
	}
	

	public Author(String f, String l, String t, Address a, String bio) {
		super(f, l, t, a);
		this.bio = bio;
	}

	public Author() {
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 7508481940058530471L;
	public void setBio(String text) {
		// TODO Auto-generated method stub
		this.bio=text;
		
	}

}
