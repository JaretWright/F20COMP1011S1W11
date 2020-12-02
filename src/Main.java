import Utilities.APIUtility;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            APIUtility.callOmdbAPI("rocky");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
