package edu.ncsu.csc216.pack_scheduler.user;

/**
 * Abstract class definition for User.  Extended by student and registrar classes.
 * Creates a User instance containing, first name, last name, id, email, and password.
 * 
 * @author - Sam McDonald
 */
public abstract class User {

	/**
	 * First name for the student.
	 */
	private String firstName;
	/**
	 * Last name for the student.
	 */
	private String lastName;
	/**
	 * Student's id number.
	 */
	private String id;
	/**
	 * Student's email address.
	 */
	private String email;
	/**
	 * Password for the student.
	 */
	private String password;

	/**
	 * Constructor for the User class
	 * @param first first name of user
	 * @param last last name of user
	 * @param id id of user
	 * @param email email of user
	 * @param password password of user
	 * @throws IllegalArgumentException if any of the given information if null or an empty String, 
	 *     or if the email is not the valid format for an email address.
	 */
	public User(String first, String last, String id, String email, String password) {
		this.setFirstName(first);
		this.setLastName(last);
		this.setId(id);
		this.setEmail(email);
		this.setPassword(password);
	}

	/**
	 * Gets student's first name.
	 * @return Student's first name.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Get's student's last name.
	 * @return Student's last name.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Get's student's id.
	 * @return Student's id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Get's student's email.
	 * @return Student's email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Get's student's password.
	 * @return Student's password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set's the student's first name.
	 * @param name Represents the student's first name.
	 * @throws IllegalArgumentException if the parameter is invalid
	 */
	public void setFirstName(String name) {
		if("".equals(name) || name == null) {
			throw new IllegalArgumentException("Invalid first name");
		}
		firstName = name;
	}

	/**
	 * Set's the student's last name.
	 * @param name Represents the student's last name.
	 * @throws IllegalArgumentException if the parameter is invalid
	 */
	public void setLastName(String name) {
		if("".equals(name) || name == null) {
			throw new IllegalArgumentException("Invalid last name");
		}
		lastName = name;
	}

	/**
	 * Set's the student's id name.
	 * @param id Represents the student's id.
	 * @throws IllegalArgumentException if the parameter is invalid
	 */
	protected void setId(String id) {
		if("".equals(id) || id == null) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * Set's the student's email.
	 * @param email Represents the student's email.
	 * @throws IllegalArgumentException if the parameter is invalid
	 */
	public void setEmail(String email) {
			if("".equals(email) || email == null) {
				throw new IllegalArgumentException("Invalid email");
			}
			if(!email.contains("@") || !email.contains(".")) {
				throw new IllegalArgumentException("Invalid email");
			}
			int idx1 = email.indexOf("@");
			
			int indexOfLastDot = email.lastIndexOf(".");
			if(indexOfLastDot < idx1) {
				throw new IllegalArgumentException("Invalid email");
			}
	
			this.email = email;
		}

	/**
	 * Set's the student's password.
	 * @param password Represents the student's password.
	 * @throws IllegalArgumentException if the parameter is invalid
	 */
	public void setPassword(String password) {
		if("".equals(password) || password == null) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = password;
	}

	/**
	 * Generates hash code for the object.
	 * @return Integer, hash code for object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/**
	 * Determines whether objects are equal
	 * @param obj object to check if they are equal.
	 * @return boolean, true if objects are equal, false if not.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
}