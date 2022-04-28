package edu.school21;

public class Car {
    private Long number;
    private String model;
    private Double price;
    private Boolean reserved;

    public Car() {
        number = 0L;
        model = "default";
        price = 0.0;
        reserved = false;
    }

    public Car(Long number, String model, Double price, Boolean reserved) {
        this.number = number;
        this.model = model;
        this.price = price;
        this.reserved = reserved;
    }

    public float setSale(Double newPrice, float fix) {
        price = newPrice;
        return fix;
    }

    @Override
    public String toString() {
        return "Car{" +
                "number=" + number +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", reserved=" + reserved +
                '}';
    }
}
