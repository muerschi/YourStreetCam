package model;


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
	
	public void setPassword(String pass) {
		this.password = pass;
	}
	public String getRole() {
		return this.role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public boolean passwordIsValid(String pass){
		if(pass.equals(password)){
			return true;
		}else{
			return false;
		}
		
	}	
}
