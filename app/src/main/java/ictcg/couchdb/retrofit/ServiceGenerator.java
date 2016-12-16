package ictcg.couchdb.retrofit;

import android.os.StrictMode;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public abstract class ServiceGenerator {
   public static String couchdbLogin = "vincent";
   public static String couchdbPassword = "couchdb";

   private static final String API_BASE_URL = "http://185.26.127.124:5984/";
   private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
		   .readTimeout(180, TimeUnit.SECONDS)
		   .writeTimeout(180, TimeUnit.SECONDS)
		   .connectTimeout(180, TimeUnit.SECONDS);
   private static Retrofit.Builder builder =
		   new Retrofit.Builder()
				   .baseUrl(API_BASE_URL)
				   .addConverterFactory(GsonConverterFactory.create());

   public static <S> S createService(Class<S> serviceClass) {

	   final String credential = Credentials.basic(couchdbLogin, couchdbPassword);
	   StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	   StrictMode.setThreadPolicy(policy);

	   httpClient.interceptors().clear();
	   httpClient.interceptors().add(new Interceptor() {
		   public Response intercept(Chain chain) throws IOException {
			   Request original = chain.request();

			   Request.Builder requestBuilder = original.newBuilder()
				   .header("Authorization", credential)
				   .header("Accept", "applicaton/json")
				   .method(original.method(), original.body());

			   Request request = requestBuilder.build();
			   return chain.proceed(request);
		   }
	   });


	   Retrofit retrofit = builder.client(httpClient.build()).build();
	   return retrofit.create(serviceClass);
   }
}
