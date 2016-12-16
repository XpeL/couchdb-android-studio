package ictcg.couchdb;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

import ictcg.couchdb.activity.EditActivity;
import ictcg.couchdb.adapter.ItemListAdapter;
import ictcg.couchdb.retrofit.task.ItemDto;
import ictcg.couchdb.retrofit.task.ItemTask;


public class MainActivity extends AppCompatActivity {

	private static final int CAMERA_REQUEST = 1888;
	private ImageView imgBtn;
	private ListView listView1;
	private Bitmap currentPicture;
	private List<ItemDto> items;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try {
			items = new ItemTask<Object, Object, Object>().execute().get();

			ItemListAdapter adapter = new ItemListAdapter(this,R.layout.list_view_item_row, items);
			listView1 = (ListView) findViewById(R.id.listView1);
			listView1.setAdapter(adapter);
			listView1.setOnItemClickListener(new AdapterView.OnItemClickListener(){

				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					Intent intent = new Intent(MainActivity.this, EditActivity.class);
					intent.putExtra("id", arg2);
					startActivity(intent);

				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
			currentPicture = (Bitmap) data.getExtras().get("data");
			imgBtn.setImageBitmap(currentPicture);
		}
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		currentPicture = savedInstanceState.getParcelable("BitmapImage");
		imgBtn.setImageBitmap(currentPicture);
		super.onRestoreInstanceState(savedInstanceState);
	};

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putParcelable("BitmapImage", currentPicture);
		super.onSaveInstanceState(outState);
	};



}






