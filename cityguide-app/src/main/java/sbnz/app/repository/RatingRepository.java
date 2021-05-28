package sbnz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sbnz.app.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

}
