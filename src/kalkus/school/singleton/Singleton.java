package kalkus.school.singleton;

public class Singleton {
    private static Singleton instance;

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
}
