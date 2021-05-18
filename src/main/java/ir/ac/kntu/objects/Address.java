package ir.ac.kntu.objects;

import java.util.Objects;

public class Address {
    private String neighbor;

    private String fullAddress;

    private String zipCode;

    public Address(String neighbor, String fullAddress, String zipCode) {
        this.neighbor = neighbor;
        this.fullAddress = fullAddress;
        this.zipCode = zipCode;
    }

    public String getNeighbor() {
        return neighbor;
    }

    public void setNeighbor(String neighbor) {
        this.neighbor = neighbor;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return neighbor + "\n\t" + fullAddress + "\n\t" + zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return neighbor.equals(address.neighbor) && zipCode.equals(address.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(neighbor, zipCode);
    }
}
