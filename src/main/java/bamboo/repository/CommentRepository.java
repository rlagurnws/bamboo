package bamboo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bamboo.dto.CommentDTO;
import bamboo.entity.CommentEntity;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity,Integer>{
	
	public List<CommentDTO> findByUserId(String id);
	public List<CommentDTO> findByPostId(int no); 
}
