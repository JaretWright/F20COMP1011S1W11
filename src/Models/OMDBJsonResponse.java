package Models;

import com.google.gson.annotations.SerializedName;

public class OMDBJsonResponse {

    @SerializedName("Search")
    private String search;

    private String totalResults;

}
