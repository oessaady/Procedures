package procedures.ma.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import org.hibernate.validator.constraints.Length;


@Entity
@Table(name="users")
public class User implements Serializable {
	@Id
  private String username;
	@Length(min=6)
  private String password;
  private Boolean actived;
  @ManyToMany
  @JoinTable(name="USERS_ROLES")
  private Collection<Role> roles;
 
 
  
public User(String username, String password, Collection<Role> roles) {
	super();
	this.username = username;
	this.password = password;
	this.roles = roles;
}

public User() {
	super();
	// TODO Auto-generated constructor stub
}
 
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Collection<Role> getRoles() {
	return roles;
}
public void setRoles(Collection<Role> roles) {
	this.roles = roles;
}
public Boolean getActived() {
	return actived;
}
public void setActived(Boolean actived) {
	this.actived = actived;
}
  
}
