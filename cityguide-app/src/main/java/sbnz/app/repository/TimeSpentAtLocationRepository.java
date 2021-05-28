package sbnz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sbnz.app.model.TimeSpentAtLocation;

@Repository
public interface TimeSpentAtLocationRepository extends JpaRepository<TimeSpentAtLocation, Long> {

}
