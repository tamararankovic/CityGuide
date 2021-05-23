package sbnz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sbnz.app.fact.event.LocationTypeCreated;

@Repository
public interface LocationTypeCreatedRepository extends JpaRepository<LocationTypeCreated, Long> {

}
