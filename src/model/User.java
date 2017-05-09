package model;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import model.PasswordHash;

public class User {	

	private Long id;
	private String name, password;
	private String role;
	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String pass){
			
		this.password=pass;

	}
	public String getRole() {
		return this.role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public boolean passwordIsValid(String pass) throws NoSuchAlgorithmException, InvalidKeySpecException{
		
		if(PasswordHash.validatePassword(pass, password)){
			return true;
		}else{
			return false;
		}
		
	}	
}
