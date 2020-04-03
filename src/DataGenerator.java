public class DataGenerator {
    static DataGenerator instance = null;

    public static DataGenerator getInsetance() {
        return instance == null ? new DataGenerator() : instance;
    }





}
