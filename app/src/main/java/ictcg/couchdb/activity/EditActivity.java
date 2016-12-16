package ictcg.couchdb.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import ictcg.couchdb.R;
import ictcg.couchdb.factory.ItemManager;
import ictcg.couchdb.retrofit.task.ItemDto;
import ictcg.couchdb.retrofit.task.ItemTask;


public class EditActivity extends Activity {
	
	private ItemTask<Object, Object, Object> task;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		String id = getIntent().getStringExtra("id");
		
		ItemDto item = ItemManager.getInstance().getItem(id);
		
		TextView nameValueTxtView = (TextView) findViewById(R.id.nameValueTxtView);
		nameValueTxtView.setText(item.getName());
		
		TextView creationDateTextView = (TextView) findViewById(R.id.creationDateValueTxtView);
		creationDateTextView.setText(item.getCreationDate());
		
		ImageView imageView = (ImageView) findViewById(R.id.editImageButton);
		imageView.setImageBitmap(item.getPhoto());
		
		
	}
	
	
}
