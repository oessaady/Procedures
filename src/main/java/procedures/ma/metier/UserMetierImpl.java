
package procedures.ma.metier;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import procedures.ma.dao.RoleRepository;
import procedures.ma.dao.UserRepository;
import procedures.ma.entities.Role;
import procedures.ma.entities.User;


@Service
public class UserMetierImpl implements UserMetier{

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
	
	 
//
//	@Override
//	public void saveUser(User user,String role) {
//         
//        Role userRole = roleRepository.findOne(role);
//        user.setRole(new HashSet<Role>(Arrays.asList(userRole)));
//		userRepository.save(user);
//	}

}
