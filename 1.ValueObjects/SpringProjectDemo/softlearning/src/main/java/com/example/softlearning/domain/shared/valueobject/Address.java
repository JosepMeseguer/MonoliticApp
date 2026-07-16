package com.example.softlearning.domain.shared.valueobject;

public final class Address {
    private final CheckedString street;
    private final CheckedString city;
    private final CheckedString region;
    private final CheckedString state;
    private final CheckedString postalCode;

    private Address(CheckedString street, CheckedString city, CheckedString region, CheckedString state, CheckedString postalCode) {
        this.street = street;
        this.city = city;
        this.region = region;
        this.state = state;
        this.postalCode = postalCode;
    }

    public static Address of(String street, String city, String region, String state,
                             String postalCode) throws IllegalArgumentException {

        CheckedString checkedStreet = CheckedString.of(street, 10, 100);
        CheckedString checkedCity = CheckedString.of(city, 1, 50);
        CheckedString checkedRegion = CheckedString.of(region, 2, 50);
        CheckedString checkedState = CheckedString.of(state, 3, 50);
        CheckedString checkedPostalCode = CheckedString.of(postalCode, 4, 20);

        return new Address(checkedStreet, checkedCity, checkedRegion, checkedState, checkedPostalCode);
    }

    public String getStreet() {
        return street.getValue();
    }

    public String getCity() {
        return city.getValue();
    }

    public String getRegion() {
        return region.getValue();
    }

    public String getState() {
        return state.getValue();
    }

    public String getPostalCode() {
        return postalCode.getValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Address)) return false;
        Address other = (Address) obj;
        return street.equals(other.street) &&
               city.equals(other.city) &&
               region.equals(other.region) &&
               state.equals(other.state) &&
               postalCode.equals(other.postalCode);
    }   

    @Override
    public int hashCode() {
        int result = street.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + region.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + postalCode.hashCode();
        return result;
    }   

    @Override
    public String toString() {
        return "street=" + street.getValue() + 
                ", city=" + city.getValue() + 
                ", region=" + region.getValue() + 
                ", state=" + state.getValue() + 
                ", postalCode=" + postalCode.getValue();
    }
}