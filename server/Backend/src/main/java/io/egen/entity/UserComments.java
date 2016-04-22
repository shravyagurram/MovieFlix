package io.egen.entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="UserComments")
public class UserComments {

	
	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO)                
	private int Id;      
	
	private double userRating;
	private String userComment;
	
	
	@ManyToOne   
	@JoinColumn(name="emailId")
	//@JsonIgnore
	private UserDetails userdetails;
	

	@ManyToOne
	//(optional=false)
	@JoinColumn(name="MovieId")
	private ShowDetails showdetails;


	public int getId() {
		return Id;
	}


	public void setId(int id) {
		Id = id;
	}


	public double getUserRating() {
		return userRating;
	}


	public void setUserRating(double userRating) {
		this.userRating = userRating;
	}


	public String getUserComment() {
		return userComment;
	}


	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}

	//@JsonIgnore
	public UserDetails getUserdetails() {
		return userdetails;
	}


	public void setUserdetails(UserDetails userdetails) {
		this.userdetails = userdetails;
	}

	//@JsonIgnore
	public ShowDetails getShowdetails() {
		return showdetails;
	}


	public void setShowdetails(ShowDetails showdetails) {
		this.showdetails = showdetails;
	}
	
	
}
