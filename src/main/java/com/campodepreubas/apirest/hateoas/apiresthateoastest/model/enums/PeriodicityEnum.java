package com.campodepreubas.apirest.hateoas.apiresthateoastest.model.enums;

public enum PeriodicityEnum {
    DAYLY("Diariamente"),
    WEEKLY("Semanalmente"),
    BIWEEKLY("Quincenalmente"),
    THREEWEEKLY("Tres semanas"),
    MONTHLY("Mensualmente"),
    BIMONTHLY("Bimensualmente"),
    QUARTERLY("Trimestralmente"),
    SEMIANNUAL("Semestralmente"),
    ANNUAL("Anualmente"),
    PUNTUAL("Puntual");

    private String description;

    PeriodicityEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}


