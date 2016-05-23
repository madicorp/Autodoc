package net.madicorp.autodoc.services;

import java.util.Collection;




import net.madicorp.autodoc.models.User;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Diop Sega
 *
 */
public class LoginUserService  implements UserDetailsService {
	
	private  ClassPathXmlApplicationContext context;
	private  UserService userService;
	
	public LoginUserService() {
		context = new ClassPathXmlApplicationContext("net/madicorp/autodoc/config/autodoc-context.xml");
		userService = (UserService) context.getBean("User");
	}
	
	@Override
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException  {
		User user =  (User) userService.findByName(login).get(0) ;

		
		if(user == null){
			throw new UsernameNotFoundException("Login ou Mot de Passe Incorrecte !");
		}
		
		 Collection<? extends GrantedAuthority> authorities =  AuthorityUtils.createAuthorityList("ROLE_"+user.getRole().getCodeRole());

		 context.close();
		 return new org.springframework.security.core.userdetails.User(user.getLogin(),user.getPassword(), authorities);

	}
	
	


}
