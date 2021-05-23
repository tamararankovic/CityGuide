package sbnz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sbnz.app.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{

}
