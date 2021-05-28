package sbnz.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sbnz.app.model.LocationType;

@Repository
public interface LocationTypeRepository extends JpaRepository<LocationType, Long> {

}
