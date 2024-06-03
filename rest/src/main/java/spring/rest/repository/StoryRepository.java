package spring.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.rest.model.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {

}
