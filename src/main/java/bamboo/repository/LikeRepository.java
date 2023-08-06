package bamboo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bamboo.entity.LikeEntity;

@Repository
public interface LikeRepository extends CrudRepository<LikeEntity,Integer>{

}
