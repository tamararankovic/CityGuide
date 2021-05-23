package sbnz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sbnz.app.fact.event.RatingCreated;

@Repository
public interface RatingCreatedRepository extends JpaRepository<RatingCreated, Long> {

}
