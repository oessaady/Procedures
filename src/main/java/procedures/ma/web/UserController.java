package procedures.ma.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import groovy.lang.Delegate;
import procedures.ma.dao.RoleRepository;
import procedures.ma.dao.UserRepository;
import procedures.ma.entities.Role;
import procedures.ma.entities.User;

@Secured("ROLE_DBA")
@Controller
public class UserController {
	@Autowired
	 private UserRepository userRep;
	@Autowired
	private RoleRepository roleRep;
	
    @GetMapping("/register")
	public String ajouterUser(Model model){
		model.addAttribute("user", new User());
		  model.addAttribute("roles", roleRep.findAll());
		return "register";
	}
    
    @PostMapping("/register")
    public String saveUser(@Valid User user,BindingResult bindingResult,Model model){	
    	if (bindingResult.hasErrors()) {
  		  model.addAttribute("roles", roleRep.findAll());
  		return "register";
    	}else{
    		userRep.save(user);
    		return "redirect:/users";
    	}
	
	}
    
    @GetMapping("/users")
	public String gelAllUser(Model model){
	model.addAttribute("users", userRep.findAll());
		return "users";
	}
    @GetMapping("/users/ajouterRole/{username}")
    public String addRole(Model model,@PathVariable String username){
    	User user=userRep.findOne(username);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleRep.findAll());
    	return "role";
    }
    @PostMapping("/users/ajouterRole")
	public String saveRole(@RequestParam("username") String username,@RequestParam("rolename") String rolename){
    	User user=userRep.findOne(username);
    	Role role=roleRep.findOne(rolename);
    	if(!user.getRoles().contains(role)) user.getRoles().add(role);
		return "redirect:/users";
	}
    
    @GetMapping("/users/deleteUser")
    public String deleteUser(@RequestParam("username") String username){
    	userRep.delete(username);
    	return "redirect:/users";
    }
    
	
}
