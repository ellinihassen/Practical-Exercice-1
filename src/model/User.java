package model;

public class User extends Personne {

public 	enum Role {
		ADMIN ("admin"),
		USER ("user");
		private final String role;

		Role(String role) {
			this.role=role;
		}
		public boolean equalsRole(String otherRole) {

			return role.equals(otherRole);
		}
		public String toString() {
			return this.role;
		}
	}


	private String email;
	private String password;
	private Role role;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String name, String lastName, String email, String password) {
		super(name,lastName);
		this.email = email;
		this.password = password;
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role.toString();
	}

	public void setRole(String role) {
		if(role.equals("admin")){
			this.role=Role.ADMIN;
		}else if(role.equals("user")){
			this.role=Role.USER;
		}
	}

	@Override
	public String toString() {
		return "id : "+super.getId()+" name:" + super.getName() + ", lastName:" + super.getLastName() + ", email:" + email + " ";
	}
	
	
	
	
}
