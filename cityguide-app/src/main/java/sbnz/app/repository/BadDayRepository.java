package sbnz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sbnz.app.fact.event.BadDay;

@Repository
public interface BadDayRepository extends JpaRepository<BadDay, Long> {

}
