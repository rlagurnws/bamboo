package bamboo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bamboo.dto.PostDTO;
import bamboo.entity.PostEntity;
import bamboo.repository.CommentRepository;
import bamboo.repository.PostRepository;

@Service
public class PostService {
	@Autowired
	private PostRepository repository;
	@Autowired
	private CommentRepository CRepository;
	private ModelMapper mapper = new ModelMapper();
	
	//조회
	public PostDTO find(int no) {
		PostEntity pe = repository.findById(no).get();
		pe.setViewCnt(pe.getViewCnt()+1);
		PostDTO post = mapper.map(pe,PostDTO.class);
		post.setCommentList(CRepository.findByPostId(no));
		return post;
	}
	
	//등록 및 수정
	public boolean post(PostDTO post) {
		repository.save(mapper.map(post, PostEntity.class));
		return true;
	}
	
	//삭제
	public boolean delete(int no) {
		repository.deleteById(no);
		return true;
	}
	
	//리스트 조회
	public List<PostDTO> findAll(){
		return ((List<PostEntity>)repository.findAll()).stream().map(e-> mapper.map(e, PostDTO.class)).toList();
	}
	
}
















