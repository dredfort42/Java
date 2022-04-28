package edu.school21.models;

import edu.school21.annotations.OrmColumn;
import edu.school21.annotations.OrmColumnId;
import edu.school21.annotations.OrmEntity;

@OrmEntity(table = "myCars")
public class Car {
	@OrmColumnId
	private Long id;
	@OrmColumn(name = "car_model", length = 150)
	private String model;
	@OrmColumn(name = "car_price")
	private Double price;
	private int speed;
	@OrmColumn(name = "car_reserv")
	private Boolean reserved;

	@Override
	public String toString() {
		return "Car{" +
				"id=" + id +
				", model='" + model + '\'' +
				", price=" + price +
				", speed=" + speed +
				", reserved=" + reserved +
				'}';
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Boolean getReserved() {
		return reserved;
	}

	public void setReserved(Boolean reserved) {
		this.reserved = reserved;
	}

	public Car(Long id, String model, Double price, int speed, Boolean reserved) {
		this.id = id;
		this.model = model;
		this.price = price;
		this.speed = speed;
		this.reserved = reserved;
	}

	public Car() {
	}
}
