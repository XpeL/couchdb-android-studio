package ictcg.couchdb.adapter;//package couchdb.couchdb.adapter;
//
//import java.util.List;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.provider.MediaStore;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.SimpleAdapter;
//import android.widget.TextView;
//import couchdb.couchdb.CouchDbAndroid;
//import couchdb.couchdb.R;
//import couchdb.couchdb.retrofit.task.ItemDto;
//import couchdb.couchdb.retrofit.task.ItemTask;
//
//public class EditItemAdapter extends SimpleAdapter<ItemDto>{
//	private static final int CAMERA_REQUEST = 1888;
//    Context context; 
//    int layoutResourceId;    
//    List<ItemDto> data;
//    
//    public EditItemAdapter(Context context, int layoutResourceId, List<ItemDto> data) {
//        super(context, layoutResourceId,data);
//        this.layoutResourceId = layoutResourceId;
//        this.context = context;
//        this.data = data;
//    }
//
//	@Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//		final ItemDto item = data.get(position);
//		
//		if(item.isInEditMode())
//			convertView = setShowModeView(position, convertView, parent);
//		else
//			convertView = setShowModeView(position, convertView, parent);
//		return convertView;
//    }
//	
//	private View setShowModeView(int position, View convertView, ViewGroup parent){
//		ItemHolder holder = null;
//		if(convertView == null || convertView.getTag().getClass() == EditItemHolder.class) {
//            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
//            convertView = inflater.inflate(R.layout.show_row, parent, false);
//            
//            holder = new ItemHolder();
//            holder.txtTitle = (TextView)convertView.findViewById(R.id.txtTitle);
//            holder.imgIcon = (ImageView)convertView.findViewById(R.id.imgIcon);
//        } else{
//        	holder = (ItemHolder) convertView.getTag();
//        }
//
//        ItemDto item = data.get(position);
//        holder.txtTitle.setText(item.get_rev());
//        if(item.getPhoto() != null)
//        	holder.imgIcon.setImageBitmap(item.getPhoto());
//        convertView.setTag(holder);
//        
//        return convertView;
//	}
//	
//	private View setEditModeView(final int position, View convertView, ViewGroup parent){
//		EditItemHolder holder = null;
//        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
//        convertView = inflater.inflate(R.layout.editable_row, parent, false);
//        
//        ItemDto item = data.get(position);
//        holder = new EditItemHolder();
//        holder.backBtn = (Button) convertView.findViewById(R.id.backBtn);
//        holder.backBtn.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				CouchDbAndroid couchDb = ((CouchDbAndroid) context);
//				ItemDto item = EditItemAdapter.this.getData().get(position);
//				item.setEditMode(false);
//				couchDb.refreshAdapter(EditItemAdapter.this.getData());
//			}
//		});
//        holder.imgBtn = (ImageButton) convertView.findViewById(R.id.imageButton);
//        holder.imgBtn.setOnClickListener(openCamera);
//        holder.editText = (EditText) convertView.findViewById(R.id.enter_txt);
//        holder.sendBtn = (Button) convertView.findViewById(R.id.sendImage);
//        holder.sendBtn.setOnClickListener(new OnClickListener() {
//			
//			public void onClick(View v) {
//				ItemDto item = EditItemAdapter.this.getData().get(position);
//				View parent = (View) v.getParent();
//				EditText editText = (EditText) parent.findViewById(R.id.enter_txt);
//				try {
//					item.setName(editText.getText().toString());
//					item.setEditMode(false);
//					new ItemTask<Object, Object, Object>().update(item);
//					CouchDbAndroid couchDb = ((CouchDbAndroid) context);
//					item.setEditMode(false);
//					couchDb.refreshAdapter(new ItemTask<Object, Object, Object>().execute().get());
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//        holder.imgBtn.setImageBitmap(item.getPhoto());
//        holder.editText.setText(item.getName());
//        
//        convertView.setTag(holder);
//        
//		return convertView;
//	}
//
//	private OnClickListener openCamera = new OnClickListener() {
//		public void onClick(View v) {
//			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//			((CouchDbAndroid) context).startActivityForResult(intent, CAMERA_REQUEST); 
//		}
//	};
//	
//    class ItemHolder {
//        ImageView imgIcon;
//        TextView txtTitle;
//    }
//    
//    
//    class EditItemHolder{
//    	Button backBtn;
//    	ImageButton imgBtn;
//    	EditText editText;
//    	Button sendBtn;
//    }
//
//
//	public List<ItemDto> getData() {
//		return data;
//	}
//
//	public void setData(List<ItemDto> data) {
//		this.data = data;
//	}
//    
//}