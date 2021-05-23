package sbnz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sbnz.app.fact.event.LocationCreated;

@Repository
public interface LocationCreatedRepository extends JpaRepository<LocationCreated, Long> {

}
