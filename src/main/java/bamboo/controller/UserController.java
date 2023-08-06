package bamboo.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bamboo.dto.UserDTO;
import bamboo.service.UserService;
import bamboo.util.JToken;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService service;
	
	//이름 조회
	@GetMapping("/namecheck")
	public boolean nameCheck(String name) {
		return service.check(name);
	}
	
	//아이디 중복확인
	@GetMapping("/idcheck")
	public boolean idCheck(String id) {
		return service.dupCheck(id);
	}
	
	//회원가입
	@PostMapping("/new")
	public boolean signUp(UserDTO user) {
		System.out.println(user);
		return service.newUser(user);
	}
	
	//로그인
	@PostMapping()
	public int login(UserDTO user, HttpServletResponse response) {
		user = service.login(user);
		if(user!=null) {
			Cookie cookie = new Cookie("user",JToken.createA(user.getName()));
			response.addCookie(cookie);
			return 1;
		}
		return 0;
	}
	
	
	//유저 정보 조회
	@GetMapping
	public UserDTO getUser(UserDTO user) {
		return service.getOne(user.getId());
	}
	
	//회원 수정
	@PostMapping("/update")
	public boolean update(UserDTO user) {
		return service.newUser(user);
	}
	
	//회원 탈퇴
	@PostMapping("/delete")
	public boolean taltae(UserDTO user) {
		return service.deleteUser(user);
	}
}
