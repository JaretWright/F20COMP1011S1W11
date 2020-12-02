package Utilities;

import Models.OMDBJsonResponse;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class is designed to call the OMDB API and return
 * various types of content
 */
public class APIUtility {

    /**
     * This method will recieve a "search" String and send that to
     * the OMDB API to receive a JSON file.  The file will
     * be written to movieInfo.json
     */
    public static void callOmdbAPI(String searchText) throws IOException, InterruptedException {
        //this is the same as what you would put in a browser if you wanted to
        //call the API
        String uri = "http://www.omdbapi.com/?apikey=4a1010ab&s="+searchText;

        String jsonLocation = "src/Utilities/movieInfo.json";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                                        .uri(URI.create(uri))
                                        .build();
        HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers
                                                            .ofFile(Paths.get(jsonLocation)));

        OMDBJsonResponse movieResponse = getMoviesFromJSON(new File(jsonLocation));
    }


    /**
     * This method receives a JSON file and extracts the
     * high level movie data out of it and stores it in
     * a OMDBJsonResponse object
     * @param jsonFile - this file must adhere to the OMDB standard response
     * @return - the result of the search
     */
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
