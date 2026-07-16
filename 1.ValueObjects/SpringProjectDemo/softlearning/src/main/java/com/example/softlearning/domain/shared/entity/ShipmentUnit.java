package com.example.softlearning.domain.shared.entity;

import com.example.softlearning.domain.shared.valueobject.Address;
import com.example.softlearning.domain.shared.valueobject.CheckedString;
import com.example.softlearning.domain.shared.valueobject.PhysicalDimensions;
import com.example.softlearning.domain.shared.valueobject.Weight;

public class ShipmentUnit {
    private final CheckedString identifier;
    private final long customerId;
    private final PhysicalDimensions dimensions;
    private final Weight weight;
    private final Address originAddress;
    private final Address destinationAddress;
    private final boolean isFragile;
    private final String deliveryInstructions;

    private ShipmentUnit ( CheckedString identifier, long customerId, PhysicalDimensions dimensions, Weight weight, Address originAddress, 
                      Address destinationAddress, boolean isFragile, String deliveryInstructions) {

        this.identifier = identifier;
        this.customerId = customerId;
        this.dimensions = dimensions;
        this.weight = weight;
        this.originAddress = originAddress;
        this.destinationAddress = destinationAddress;
        this.isFragile = isFragile;
        this.deliveryInstructions = deliveryInstructions;
    }

    public static ShipmentUnit of (String identifier, long customerId, PhysicalDimensions dimensions, Weight weight, Address originAddress, 
                              Address destinationAddress, boolean isFragile, String deliveryInstructions) throws IllegalArgumentException {

        // pueden ser necesarios más validaciones de los parámetros, pero por simplicidad solo se valida el identificador
        // por ejemplo validar un peso y dimensiones que no excedan de unos determinados límites                        
        CheckedString checkedIdentifier = CheckedString.of(identifier, 8, 50);
        return new ShipmentUnit(checkedIdentifier, customerId, dimensions, weight, originAddress, destinationAddress, isFragile, deliveryInstructions);
    }

    public CheckedString getIdentifier() {
        return identifier;
    }

    public long getCustomerId() {
        return customerId;
    }

    public PhysicalDimensions getDimensions() {
        return dimensions;
    }

    public Weight getWeight() {
        return weight;
    }

    public Address getOriginAddress() {
        return originAddress;
    }

    public Address getDestinationAddress() {
        return destinationAddress;
    }

    public boolean isFragile() {
        return isFragile;
    }

    public String getDeliveryInstructions() {
        return deliveryInstructions;
    }

    @Override
    public String toString() {
        return "ShipmentUnit{" +
                "identifier=" + identifier +
                ", customerId=" + customerId +
                ", dimensions=[" + dimensions + "]" +
                ", weight=" + weight +
                ", originAddress=[" + originAddress + "]" +
                ", destinationAddress=[" + destinationAddress + "]" +
                ", isFragile=" + isFragile +
                ", deliveryInstructions='" + deliveryInstructions + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShipmentUnit)) return false;
        ShipmentUnit shipmentUnit = (ShipmentUnit) o;
        return customerId == shipmentUnit.customerId &&
                isFragile == shipmentUnit.isFragile &&
                identifier.equals(shipmentUnit.identifier) &&
                dimensions.equals(shipmentUnit.dimensions) &&
                weight.equals(shipmentUnit.weight) &&
                originAddress.equals(shipmentUnit.originAddress) &&
                destinationAddress.equals(shipmentUnit.destinationAddress) &&
                deliveryInstructions.equals(shipmentUnit.deliveryInstructions);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(identifier, customerId, dimensions, weight, originAddress, destinationAddress, isFragile, deliveryInstructions);
    }
}