package com.example.mypersonalvault;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    @Headers({
            "Content-Type: application/json",
            "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InZ6eXphZHBidnpiYmNzZnd6ZGpqIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzUxNTQxNTcsImV4cCI6MjA5MDczMDE1N30.y2PtYju7uf8S2gjtapktPHWp5_ikIZKrPHF9fm_Dl6Y",
            "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InZ6eXphZHBidnpiYmNzZnd6ZGpqIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzUxNTQxNTcsImV4cCI6MjA5MDczMDE1N30.y2PtYju7uf8S2gjtapktPHWp5_ikIZKrPHF9fm_Dl6Y"
    })
    @POST("auth/v1/token?grant_type=password")
    Call<ResponseBody> login(@Body Map<String, String> body);


    @Headers({
            "apikey:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InZ6eXphZHBidnpiYmNzZnd6ZGpqIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzUxNTQxNTcsImV4cCI6MjA5MDczMDE1N30.y2PtYju7uf8S2gjtapktPHWp5_ikIZKrPHF9fm_Dl6Y",
            "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InZ6eXphZHBidnpiYmNzZnd6ZGpqIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzUxNTQxNTcsImV4cCI6MjA5MDczMDE1N30.y2PtYju7uf8S2gjtapktPHWp5_ikIZKrPHF9fm_Dl6Y ",
            "Content-Type: application/octet-stream"
    })
    @PUT("storage/v1/object/documents/{fileName}")
    Call<ResponseBody> uploadFile(
            @Path("fileName") String fileName,
            @Body RequestBody body
    );
    @Headers({
            "Content-Type: application/json",
            "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InZ6eXphZHBidnpiYmNzZnd6ZGpqIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzUxNTQxNTcsImV4cCI6MjA5MDczMDE1N30.y2PtYju7uf8S2gjtapktPHWp5_ikIZKrPHF9fm_Dl6Y",
            "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InZ6eXphZHBidnpiYmNzZnd6ZGpqIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzUxNTQxNTcsImV4cCI6MjA5MDczMDE1N30.y2PtYju7uf8S2gjtapktPHWp5_ikIZKrPHF9fm_Dl6Y"
    })
    @POST("auth/v1/signup")
    Call<ResponseBody> signup(@Body Map<String, String> body);
}