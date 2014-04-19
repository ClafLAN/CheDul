package net.claflan.CheDul.constants;

public enum Month {
    
    JANUARY("January"),
    FEBRUARY("February"),
    MARCH("March"),
    APRIL("April"),
    MAY("May"),
    JUNE("June"),
    JULY("July"),
    AUGUST("August"),
    SEPTEMBER("September"),
    OCTOBER("October"),
    NOVEMBER("November"),
    DECEMBER("December");
    
    private final String name;
    
    Month(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
