package sbnz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sbnz.app.fact.event.LocationInfoPageVisited;

@Repository
public interface LocationInfoPageVisitedRepository extends JpaRepository<LocationInfoPageVisited, Long> {

}
