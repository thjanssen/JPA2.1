package blog.thoughts.on.java.jpa21.business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import blog.thoughts.on.java.jpa21.entity.Trip;
import blog.thoughts.on.java.jpa21.enums.Vehicle;

@Stateless
@LocalBean
public class TripBean {

	@PersistenceContext
	private EntityManager em;

	public Trip createTrip(Trip trip) {
		this.em.persist(trip);
		return trip;
	}

	public Trip getTrip(Integer id) {
		return this.em.find(Trip.class, id);
	}

	public List<Trip> findTripsByVehicle(Vehicle vehicle) {
		Query query = this.em.createNamedQuery("Trip.findByVehicle");
		query.setParameter("vehicle", vehicle);
		return query.getResultList();
	}
}
