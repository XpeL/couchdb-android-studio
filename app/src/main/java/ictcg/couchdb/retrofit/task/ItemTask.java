package ictcg.couchdb.retrofit.task;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ictcg.couchdb.retrofit.ServiceGenerator;
import ictcg.couchdb.retrofit.common.Row;
import ictcg.couchdb.retrofit.common._allChangesDto;
import ictcg.couchdb.retrofit.common.response;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemTask<X, Y, Z> extends AsyncTask<String, Void, List<ItemDto>>{
	public List<String> ids = new ArrayList<String>();
	public List<ItemDto> items = new ArrayList<ItemDto>();
	public ItemService client;
	public ItemTask() {
		client = ServiceGenerator.createService(ItemService.class);
	}
	
	@Override
	protected void onPreExecute() {
		try {
			Response<_allChangesDto> response = client.getAllDocs().execute();
			_allChangesDto _allChanges = response.body();
			for (Row row : _allChanges.getRows()) {
				ids.add(row.getId());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected List<ItemDto> doInBackground(String... params) {
		try{
			ItemDto item;
			for (String id : ids) {
				item = client.getItem(id).execute().body();
				item.setCreationDate(new Date().toString());
				items.add(item);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return items;
	}
	
	public void postObject(ItemDto item, Bitmap bmp) throws IOException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
		byte[] byteArray = stream.toByteArray();
	    
	    response resp = client.sendItem(item).execute().body();
	    
		RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), byteArray);
		Call<ResponseBody> call = client.upload(resp.getId(), resp.getRev(),requestBody);
	    call.enqueue(new Callback<ResponseBody>() {

			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
				Log.v("Upload", "success");
			}

			public void onFailure(Call<ResponseBody> call, Throwable t) {
				Log.v("Upload", t.getMessage());
			}
	       
	    });
	}
	
}

