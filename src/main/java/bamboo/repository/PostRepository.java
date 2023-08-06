package bamboo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bamboo.dto.PostDTO;
import bamboo.entity.PostEntity;

@Repository
public interface PostRepository extends CrudRepository<PostEntity,Integer>{
	
	public List<PostDTO> findByUserId(String id);
}
