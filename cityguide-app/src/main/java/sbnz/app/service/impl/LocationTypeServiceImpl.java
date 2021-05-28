package sbnz.app.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.app.dto.LocationTypeDTO;
import sbnz.app.dto.NewLocationTypeDTO;
import sbnz.app.exception.LocationNotFoundException;
import sbnz.app.exception.LocationTypeNotFoundException;
import sbnz.app.fact.event.LocationTypeCreated;
import sbnz.app.fact.event.LocationTypeDeleted;
import sbnz.app.model.Feature;
import sbnz.app.model.LocationType;
import sbnz.app.repository.LocationRepository;
import sbnz.app.repository.LocationTypeCreatedRepository;
import sbnz.app.repository.LocationTypeRepository;
import sbnz.app.service.KieSessionService;
import sbnz.app.service.LocationService;
import sbnz.app.service.LocationTypeService;

@Service
public class LocationTypeServiceImpl implements LocationTypeService {

	private LocationTypeRepository typeRepository;
	private LocationTypeCreatedRepository createdRepository;
	private LocationRepository locationRepository;
	private LocationService locationService;
	private KieSessionService sessionService;
	
	@Autowired
	public LocationTypeServiceImpl(LocationTypeRepository typeRepository,
			LocationTypeCreatedRepository createdRepository, LocationRepository locationRepository,
			LocationService locationService, KieSessionService sessionService) {
		super();
		this.typeRepository = typeRepository;
		this.createdRepository = createdRepository;
		this.locationRepository = locationRepository;
		this.locationService = locationService;
		this.sessionService = sessionService;
	}

	@Override
	public void addLocationType(NewLocationTypeDTO newType) {
		LocationType lt = new LocationType(newType.getName());
		for(String feature : newType.getFeatures()) {
			Feature f = Feature.valueOf(feature);
			if(f != null) lt.addFeature(f);
		}
		lt = typeRepository.save(lt);
		LocationTypeCreated ltc = new LocationTypeCreated(lt, new Date());
		ltc = createdRepository.save(ltc);
		sessionService.getPromotionSession().insert(ltc);
	}

	@Override
	public void deleteLocationType(long typeId) throws LocationTypeNotFoundException {
		LocationType lt = typeRepository.findById(typeId).orElse(null);
		if(lt == null)
			throw new LocationTypeNotFoundException();
		sessionService.getPromotionSession().insert(new LocationTypeDeleted(lt));
		locationRepository.findAll().stream().filter(l -> l.getType().getId() == lt.getId()).
		forEach(l -> {
			try {
				locationService.deleteLocation(l.getId());
			} catch (LocationNotFoundException e) {
			}
		});
		createdRepository.findAll().stream().filter(ltc -> ltc.getLocationType().getId() == lt.getId()).forEach(ltc -> createdRepository.delete(ltc));
		typeRepository.delete(lt);
	}

	@Override
	public Set<String> getFeatures() {
		Set<String> result = new HashSet<String>();
		for(Feature f : Feature.values())
			result.add(f.toString());
		return result;
	}

	@Override
	public Set<LocationTypeDTO> getAll() {
		Set<LocationTypeDTO> result = new HashSet<LocationTypeDTO>();
		for(LocationType lt : typeRepository.findAll()) {
			Set<String> features = new HashSet<String>();
			for(Feature f : lt.getFeatures())
				features.add(f.toString());
			result.add(new LocationTypeDTO(lt.getId(), lt.getName(), features));
		}
		return result;
	}
}
