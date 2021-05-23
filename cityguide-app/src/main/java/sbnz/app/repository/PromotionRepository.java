package sbnz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sbnz.app.fact.event.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

}
