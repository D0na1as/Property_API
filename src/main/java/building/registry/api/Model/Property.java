package building.registry.api.Model;

import javax.persistence.*;

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String Owner;
    private double size;
    private double value;
    private String type;
    private Address address;

    public Property() {
    }

    public Property(int id, String owner, double size, double value, String type, Address address) {
        this.id = id;
        Owner = owner;
        this.size = size;
        this.value = value;
        this.type = type;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
