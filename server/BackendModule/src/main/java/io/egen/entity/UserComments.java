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
@Data
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
	
	private UserDetails userdetails;
	

	@ManyToOne
	
	@JoinColumn(name="MovieId")
	private ShowDetails showdetails;


	
	
}
