package com.example.softlearning.domain.shared.valueobject;

public enum LinearLongDistanceUnit {
    //REVISAR ESPECIFICACIONES PARA DETERMINAR SI ES ADECUADO PARA LA PRECISION REQUERIDA
    KILOMETER(1L, "km"),
    ASTRONOMICAL_UNIT(149_597_870L, "au"), //si se precisara mayor precision se puede usar 149_597_870_700L tomando como base el metro
    LIGHT_YEAR(9_460_730_472_580L, "ly"),  //si se precisara mayor precision se puede usar 9_460_730_472_580_800L tomando como base el metro
    PARSEC(30_856_775_814_913L, "pc");   // 1 parsec = 3,261563777 light-years

    // EN CONTEXTOS RELACIONADOS CON ASTRO DISTANCES, SE PUEDEN AÑADIR UNIDADES DE DISTANCIA MAYORES COMO:
    // KILOPARSEC("kpc"), MEGAPARSEC("Mpc"), GIGAPARSEC, TERAPARSEC, PETAPARSEC, EXAPARSEC, ZETTAPARSEC, YOTTAPARSEC
    // Y EN ESTOS CONTEXTOS TENDREMOS QUE AÑADIR: LinearAstroDistanceUnit, LinearLongAstroDistance ...
    
    private final long kilometersFactor;
    private final String symbol;

    LinearLongDistanceUnit(long kilometersFactor, String symbol) {
        this.kilometersFactor = kilometersFactor;
        this.symbol = symbol;
    }

    long toKilometers(long value) {
        return value * kilometersFactor;
    }

    long fromKilometers(long kmValue) {
        return kmValue / kilometersFactor;
    }

    public String symbol() {
        return symbol;
    }
}