/**
 * 
 */
package javabeat.net.thymeleaf.model;

import java.util.Date;

/**
 * @author Celine Patag
 *
 */
public class Dog {
	
	private String name;
	
	private String breed;
	
	private Date birthdate;		

	public Dog(String name, String breed, Date birthdate) {
		super();
		this.name = name;
		this.breed = breed;
		this.birthdate = birthdate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}	

}
