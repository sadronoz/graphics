package kalkus.school.singleton;

public class Singleton {
    private static Singleton instance;
    public final String ANSI_RED = "\u001B[31m";
    public final String ANSI_RESET = "\u001B[0m";

    /**
     * This static method returns the only instance of Singleton that there is, so by accident we don't create another
     * Using this method we can touch any variable that is in this instance, so we can hold "static" variables all the time
     * that we want to use throughout the code
     * @return Returns the current instance of Singleton or creates a new one if none exists
     */
    public static Singleton getInstance() {
        if (instance == null) instance = new Singleton();
        return instance;
    }

    private int color;

    private Singleton() {
        color = 0x00ff00;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
