package edu.school21.models;

public class Product {
    private Long id;
    private String name;
    private int price;

    public Product(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        String string = new String();
        string.concat("[PRODUCT] id: ").concat(id.toString());
        string.concat(" | name: ").concat(name);
        string.concat(" | price: ").concat(Integer.toString(price));
        return string;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
//
//package edu.school21.models;
//
//public class Product {
//    private Long id;
//    private String name;
//    private int price;
//
//    public Product(Long id, String name, int price) {
//        this.id = id;
//        this.name = name;
//        this.price = price;
//    }
//
//    @Override
//    public String toString() {
//        return "Product{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", price=" + price +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Product product = (Product) o;
//
//        return new org.apache.commons.lang.builder.EqualsBuilder().append(id, product.id).append(price, product.price).append(name, product.name).isEquals();
//    }
//
//    @Override
//    public int hashCode() {
//        return new org.apache.commons.lang.builder.HashCodeBuilder(17, 37).append(id).append(name).append(price).toHashCode();
//    }
//
//
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getPrice() {
//        return price;
//    }
//
//    public void setPrice(int price) {
//        this.price = price;
//    }
//}
