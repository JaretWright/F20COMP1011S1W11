package Utilities;

import Models.OMDBJsonResponse;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;

/**
 * This class is designed to call the OMDB API and return
 * various types of content
 */
public class APIUtility {

    public static OMDBJsonResponse getMoviesFromJSON(File jsonFile)
    {
        Gson gson = new Gson();
        OMDBJsonResponse searchResult = null;

        //using try "with resources"
        try(
                FileReader fileReader = new FileReader(jsonFile);
                JsonReader jsonReader = new JsonReader(fileReader);
                )
        {
            searchResult = gson.fromJson(jsonReader, OMDBJsonResponse.class);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return searchResult;
    }
}
