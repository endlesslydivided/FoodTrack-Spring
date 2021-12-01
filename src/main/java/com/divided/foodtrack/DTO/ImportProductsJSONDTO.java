package com.divided.foodtrack.DTO;

public class ImportProductsJSONDTO {

    public ImportProductsJSONDTO(String valueString) {
        this.valueString = valueString;
    }

    public ImportProductsJSONDTO() {
    }

    private String valueString;

    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }
}
