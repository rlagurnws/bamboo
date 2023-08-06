package bamboo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bamboo.dto.CommentDTO;
import bamboo.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentService service;
	
	@PostMapping
	public boolean postComment(CommentDTO comment) {
		return service.postComment(comment);
	}
	
	@PostMapping("/delete")
	public boolean delete(int no) {
		return service.deleteComment(no);
	}
}
