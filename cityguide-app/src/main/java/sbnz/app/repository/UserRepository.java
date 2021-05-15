package sbnz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sbnz.app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
