package atomic.enums;

public enum CategoryProcessingZone {
    KITCHEN, BAR, PLATE;

    public static CategoryProcessingZone standard() {
        return KITCHEN;
    }
}
