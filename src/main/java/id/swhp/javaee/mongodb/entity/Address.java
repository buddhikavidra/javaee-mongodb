package id.swhp.javaee.mongodb.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Sukma Wardana
 * @since 1.0.0
 */
@XmlType(name = "address")
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {

    private String address1;
    private String address2;
    private String city;
    private String state;

    public Address() {
    }

    public Address(String address1, String address2, String city, String state) {
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Address{" + "address1=" + address1 + ", address2=" + address2 
                + ", city=" + city + ", state=" + state + '}';
    }
}
