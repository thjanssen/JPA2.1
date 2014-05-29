package blog.thoughts.on.java.jpa21;

import java.util.List;

import javax.inject.Inject;

import junit.framework.Assert;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import blog.thoughts.on.java.jpa21.business.TripBean;
import blog.thoughts.on.java.jpa21.converter.VehicleConverter;
import blog.thoughts.on.java.jpa21.entity.Trip;
import blog.thoughts.on.java.jpa21.enums.Vehicle;

@RunWith(Arquillian.class)
public class TestTripBean {

	@Inject
	private TripBean tripBean;

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap
				.create(JavaArchive.class)
				.addClasses(Trip.class, VehicleConverter.class, TripBean.class,
						Vehicle.class)
				.addAsManifestResource("META-INF/persistence.xml",
						"persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	@ShouldMatchDataSet("data/trip.yml")
	public void createTrip() {
		Trip trip = new Trip();
		trip.setDestination("Berlin");
		trip.setVehicle(Vehicle.CAR);

		this.tripBean.createTrip(trip);
		Assert.assertNotNull(trip.getId());
	}

	@Test
	@UsingDataSet("data/trip.yml")
	public void findByVehicle() {
		List<Trip> trips = this.tripBean.findTripsByVehicle(Vehicle.CAR);
		if (trips.size() == 1) {
			Trip trip = trips.get(0);
			Assert.assertEquals(new Integer(1), trip.getId());
			Assert.assertEquals("Berlin", trip.getDestination());
			Assert.assertEquals(Vehicle.CAR, trip.getVehicle());
		}
	}
}
