package bamboo.repository;

import org.springframework.data.repository.CrudRepository;

import bamboo.entity.PeopleEntity;

public interface PeopleRepository extends CrudRepository<PeopleEntity,String>{

}
