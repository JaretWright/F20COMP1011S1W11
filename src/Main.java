import Utilities.APIUtility;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        APIUtility.getMoviesFromJSON(new File("src/Utilities/movieInfo.json"));
    }
}
