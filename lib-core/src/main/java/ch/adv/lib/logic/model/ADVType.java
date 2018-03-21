package ch.adv.lib.logic.model;

public enum ADVType {
    CIRCLE("circle"), SQUARE("square"), RECTANGLE("rectangle"), ARROW("arrow"), ELIPSE("elipse");

    private String type;

    ADVType(String type) {
        this.type = type.toLowerCase();
    }

    public String getType() {
        return type;
    }

    public static ADVType byName(String colorName) {
        return valueOf(colorName.toUpperCase());
    }
}
