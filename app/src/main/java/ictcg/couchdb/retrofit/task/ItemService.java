package ictcg.couchdb.retrofit.task;


import ictcg.couchdb.retrofit.common._allChangesDto;
import ictcg.couchdb.retrofit.common.response;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ItemService {
	
	  @GET("items/_all_docs")
	  Call<_allChangesDto> getAllDocs();
	  
	  @GET("items/{id}")
	  Call<ItemDto> getItem(@Path(value = "id") String id);
	  
	  @GET("items/{id}/main_picture")
	  Call<ResponseBody> getPicture(@Path(value = "id") String id);
	  
	  @POST("items")
	  Call<response> sendItem(@Body ItemDto item);

	  @PUT("items/{id}/main_picture")
	  Call<ResponseBody> upload(@Path(value = "id") String id,
								@Query("rev") String rev,
								@Body RequestBody file);
}
