package bamboo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bamboo.dto.CommentDTO;
import bamboo.entity.CommentEntity;
import bamboo.repository.CommentRepository;

@Service
public class CommentService {
	@Autowired
	private CommentRepository repository;
	private ModelMapper mapper = new ModelMapper();
	
	//등록 및 수정
	public boolean postComment(CommentDTO comment) {
		repository.save(mapper.map(comment, CommentEntity.class));
		return true;
	}
	
	//삭제
	public boolean deleteComment(int no) {
		repository.deleteById(no);
		return true;
	}
	
}
