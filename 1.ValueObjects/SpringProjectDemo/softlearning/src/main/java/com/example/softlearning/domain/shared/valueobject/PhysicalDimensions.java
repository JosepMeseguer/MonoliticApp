package com.example.softlearning.domain.shared.valueobject;

public class PhysicalDimensions {
    private final LinearDistance length;
    private final LinearDistance width;
    private final LinearDistance height;

    private PhysicalDimensions(LinearDistance length, LinearDistance width, LinearDistance height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    /*
     * Al basarnos en LinearDistance, que ya tiene validaciones para valores
     * negativos, no es necesario agregar validaciones adicionales aquí.
     * Por el mismo motivo, no es necesario agregar validaciones para dimensiones
     * nulas, ya que se asume que las dimensiones siempre serán
     * proporcionadas y válidas, ya que el método factory de LinearDistance ya
     * incluye estas validaciones.
     */
    public static PhysicalDimensions of(LinearDistance length, LinearDistance width, LinearDistance height) {
        return new PhysicalDimensions(length, width, height);
    }

    public LinearDistance getLength() {
        return length;
    }

    public LinearDistance getWidth() {
        return width;
    }

    public LinearDistance getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "length=" + length +
                ", width=" + width +
                ", height=" + height;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof PhysicalDimensions))
            return false;
        PhysicalDimensions other = (PhysicalDimensions) obj;
        return length.equals(other.length) &&
                width.equals(other.width) &&
                height.equals(other.height);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + length.hashCode();
        result = 31 * result + width.hashCode();
        result = 31 * result + height.hashCode();
        return result;
    }
}