package bamboo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bamboo.dto.CommentDTO;
import bamboo.dto.PostDTO;
import bamboo.dto.UserDTO;
import bamboo.entity.CommentEntity;
import bamboo.entity.PeopleEntity;
import bamboo.entity.PostEntity;
import bamboo.entity.UserEntity;
import bamboo.repository.CommentRepository;
import bamboo.repository.PeopleRepository;
import bamboo.repository.PostRepository;
import bamboo.repository.UserRepository;
import bamboo.util.Boan;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userR;
	@Autowired
	private PostRepository postR;
	@Autowired
	private CommentRepository commentR;
	@Autowired
	private PeopleRepository peopleR;
	
	ModelMapper mapper = new ModelMapper();
	
	//회원정보
	public UserDTO getOne(String id) {
		UserDTO user =  mapper.map(userR.findById(id).get(),UserDTO.class);
		List<PostDTO> pl = postR.findByUserId(id);
		List<CommentDTO> cl = commentR.findByUserId(id);
		user.setPassword("안알려줄거얌");
		user.setPostList(pl);
		user.setCommentList(cl);
		return user;
	}
	
	//아이디 중복확인
	public boolean dupCheck(String id) {
		if(userR.findById(id).isPresent()) {
			return false;
		}
		return true;
	}
	
	
	
	//회원가입,수정
	public boolean newUser(UserDTO user) {
		if(user.getId() == null|| user.getPassword()==null || user.getNickName()==null) {
			return false;
		}
		UserEntity userE = mapper.map(user, UserEntity.class);
		
		String salt = Boan.salt();
		userE.setSalt(salt);
		userE.setPassword(Boan.change(userE.getPassword()+salt));
		
		userR.save(userE);
		return true;
	}
	
	//회원탈퇴
	public boolean deleteUser(UserDTO user) {
		UserEntity userE = mapper.map(user, UserEntity.class);
		List<PostEntity> pl = user.getPostList().stream().map(p->mapper.map(p, PostEntity.class)).toList();
		List<CommentEntity> cl = user.getCommentList().stream().map(p->mapper.map(p, CommentEntity.class)).toList();
		
		userR.delete(userE);
		postR.deleteAll(pl);
		commentR.deleteAll(cl);
		
		return true;
	}
	
	//로그인
	public UserDTO login(UserDTO user) {
		if(userR.findById(user.getId()).isPresent()) {
			UserEntity userE = userR.findById(user.getId()).get();
			if(!Boan.change(user.getPassword()+userE.getSalt()).equals(userE.getPassword())) {
				return null;
			}
			return mapper.map(userE, UserDTO.class);
		}
		return null;
	}
	
	//이름확인
	public boolean check(String name) {
		PeopleEntity person = peopleR.findById(name).get();
		if(person==null || person.getStatus()==1) {
			return false;
		}
		return true;
	}
}






























