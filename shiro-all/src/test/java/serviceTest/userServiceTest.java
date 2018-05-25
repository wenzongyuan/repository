package serviceTest;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wzy.shiro.dao.entity.Role;
import com.wzy.shiro.dao.entity.User;
import com.wzy.shiro.exception.ShiroException;
import com.wzy.shiro.handler.IRoleHandler;
import com.wzy.shiro.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/spring-config.xml")
public class userServiceTest {
	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleHandler roleHandler;
	
	@Test
	public void testAddUser() throws ShiroException{
		User user=new User();
		user.setUsername("wen");
		user.setPassword("123");
		userService.addUser(user);	
	}
	
	@Test
	public void testGetPermissionsByName() throws ShiroException{
		Set<String> set=userService.findPermissions("admin");
		for(String permission:set){
			System.out.println(permission);
		}
	}
	
	@Test
	public void testFindRoleById() throws ShiroException{
		Role role =roleHandler.fingRoleById(1l);
		System.out.println(role.getRole());
	}
	
	@Test
	public void testFindUserByName() throws ShiroException{
		User user=userService.findUserByName("admin");
		System.out.println(user.toString());
	}

}
