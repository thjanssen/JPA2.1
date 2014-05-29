package blog.thoughts.on.java.jpa21.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import blog.thoughts.on.java.jpa21.enums.Vehicle;

@Converter(autoApply = true)
public class VehicleConverter implements AttributeConverter<Vehicle, String> {

	@Override
	public String convertToDatabaseColumn(Vehicle vehicle) {
		switch (vehicle) {
		case BUS:
			return "B";
		case CAR:
			return "C";
		case PLANE:
			return "P";
		case TRAIN:
			return "T";
		default:
			throw new IllegalArgumentException("Unknown value: " + vehicle);
		}
	}

	@Override
	public Vehicle convertToEntityAttribute(String dbData) {
		switch (dbData) {
		case "B":
			return Vehicle.BUS;
		case "C":
			return Vehicle.CAR;
		case "P":
			return Vehicle.PLANE;
		case "T":
			return Vehicle.TRAIN;
		default:
			throw new IllegalArgumentException("Unknown value: " + dbData);
		}
	}

}
