package bamboo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bamboo.dto.PostDTO;
import bamboo.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostService service;
	
	//post 조회
	@GetMapping
	public PostDTO getPost(int no) {
		return service.find(no);
	}
	
	@PostMapping
	public boolean post(PostDTO post) {
		return service.post(post);
	}
	
	@PostMapping("/delete")
	public boolean delete(int no) {
		return service.delete(no);
	}
	
	@GetMapping("/all")
	public List<PostDTO> findAll(){
		return service.findAll();
	}
}
