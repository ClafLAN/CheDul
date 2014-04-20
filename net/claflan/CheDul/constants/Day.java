package net.claflan.CheDul.constants;

public enum Day {
    
    SUNDAY("Sunday"),
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday");
    
    private final String day;
    
    Day(String day) {
        this.day = day;
    }
    
    @Override
    public String toString() {
        return day;
    }
}
