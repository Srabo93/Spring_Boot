package spring.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import spring.rest.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query("SELECT u FROM User u JOIN FETCH u.stories s WHERE u.id = :userId AND s.publicVisible = true")
  Optional<User> findByIdAllPublicVisibleStories(@Param("userId") Long userId);

  @Query("SELECT u FROM User u LEFT JOIN FETCH u.stories s WHERE u.id = :userId AND (:publicVisible IS NULL OR s.publicVisible = :publicVisible)")
  Optional<User> findByIdAllStories(@Param("userId") Long userId,
      @Param("publicVisible") Optional<Boolean> publicVisible);

}
