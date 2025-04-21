package entity;

import java.beans.Transient;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="recruiter_profile")
public class RecruiterProfile {
	@Id
	private int userAccountId;
	
	@OneToOne
	@JoinColumn(name="user_account_id")
	@MapsId
	private Users userId;
	
	private String company;
	
	private String city;
	
	private String country;
	
	private String firstName;
	
	private String lastName;
	
	@Transient // Not persisting data in database
	public String getPhotosImagePath() {
		if(profilePhoto==null) {
			return null;
		}
		return "/photos/recruiter/"+userAccountId+"/"+profilePhoto;
	}
	
	@Column(nullable = true, length = 64)
	private String profilePhoto;
	
	private String state;

	public RecruiterProfile(int userAccountId, Users userId, String company, String city, String country,
			String firstName, String lastName, String profilePhoto, String state) {
		super();
		this.userAccountId = userAccountId;
		this.userId = userId;
		this.company = company;
		this.city = city;
		this.country = country;
		this.firstName = firstName;
		this.lastName = lastName;
		this.profilePhoto = profilePhoto;
		this.state = state;
	}

	public RecruiterProfile() {
		super();
	}
	
	public RecruiterProfile(Users user) {
		this.userId=user; 
	}

	public int getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(int userAccountId) {
		this.userAccountId = userAccountId;
	}

	public Users getUserId() {
		return userId;
	}

	public void setUserId(Users userId) {
		this.userId = userId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
}
