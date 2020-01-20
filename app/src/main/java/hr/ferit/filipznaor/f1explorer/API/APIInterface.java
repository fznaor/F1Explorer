package hr.ferit.filipznaor.f1explorer.API;

import hr.ferit.filipznaor.f1explorer.POJO.APIResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("seasons.json")
    Call<APIResponse> getSeasons(@Query("limit") int limit);

    @GET("{season}.json")
    Call<APIResponse> getSeasonInfo(@Path("season") String season);

    @GET("{season}/circuits/{circuit}/results.json")
    Call<APIResponse> getRaceResults(@Path("season") int season, @Path("circuit") String circuit, @Query("limit") int limit);

    @GET("{season}/circuits/{circuit}/qualifying.json")
    Call<APIResponse> getQualifyingResults(@Path("season") int season, @Path("circuit") String circuit, @Query("limit") int limit);

    @GET("drivers.json")
    Call<APIResponse> getDrivers(@Query("limit") int limit);

    @GET("constructors.json")
    Call<APIResponse> getConstructors(@Query("limit") int limit);

    @GET("circuits.json")
    Call<APIResponse> getCircuits(@Query("limit") int limit);

    @GET("circuits/{circuit}/races.json")
    Call<APIResponse> getCircuitInfo(@Path("circuit") String circuit, @Query("limit") int limit);

    @GET("{season}/driverStandings.json")
    Call<APIResponse> getDriverStandings(@Path("season") String season, @Query("limit") int limit);

    @GET("{season}/constructorStandings.json")
    Call<APIResponse> getConstructorStandings(@Path("season") String season, @Query("limit") int limit);

    @GET("drivers/{driver}/seasons.json")
    Call<APIResponse> getDriverSeasons(@Path("driver") String driver);

    @GET("constructors/{constructor}/seasons.json")
    Call<APIResponse> getConstructorSeasons(@Path("constructor") String constructor, @Query("limit") int limit);

    @GET("{season}/drivers/{driver}/results.json")
    Call<APIResponse> getDriverSeasonResults(@Path("season") String season, @Path("driver") String driver);

    @GET("{season}/constructors/{constructor}/results.json")
    Call<APIResponse> getConstructorSeasonResults(@Path("season") String season, @Path("constructor") String constructors, @Query("limit") int limit);

    @GET("{season}/drivers/{driver}/driverStandings.json")
    Call<APIResponse> getSingleDriverStandings(@Path("season") String season, @Path("driver") String driver);

    @GET("{season}/constructors/{constructor}/constructorStandings.json")
    Call<APIResponse> getSingleConstructorStandings(@Path("season") String season, @Path("constructor") String constructor);
}
