package ictcg.couchdb.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ictcg.couchdb.R;
import ictcg.couchdb.retrofit.task.ItemDto;
import ictcg.couchdb.retrofit.task.ItemTask;


public class ItemListAdapter extends ArrayAdapter<ItemDto>{

    Context context; 
    int layoutResourceId;    
    List<ItemDto> data;
    private ItemTask<Object, Object, Object> task;
    
    public ItemListAdapter(Context context, int layoutResourceId, List<ItemDto> data) {
        super(context, layoutResourceId,data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ItemHolder holder = null;
        
        if(row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            
            holder = new ItemHolder();
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            
            row.setTag(holder);
        } else {
            holder = (ItemHolder) row.getTag();
        }
        
        ItemDto item = data.get(position);
        holder.txtTitle.setText(item.getName()+" "+item.getModificationDate());
        /*holder.imgIcon.setImageBitmap(item.getPhoto());*/
        
        return row;
    }
	
    class ItemHolder {
        ImageView imgIcon;
        TextView txtTitle;
    }
}