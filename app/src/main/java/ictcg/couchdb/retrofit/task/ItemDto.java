package ictcg.couchdb.retrofit.task;

import android.graphics.Bitmap;

public class ItemDto{
    	private String _id;
    	private String name;
    	private String modification_date;
    	private String creation_date;
    	private Bitmap _attachments;
    	private String _rev;
    	
		public String getId() {
			return _id;
		}
		public void setId(String id) {
			this._id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getModificationDate() {
			return modification_date;
		}
		public void setModificationDate(String modificationDate) {
			this.modification_date = modificationDate;
		}
		public String getCreationDate() {
			return creation_date;
		}
		public void setCreationDate(String creationDate) {
			this.creation_date = creationDate;
		}
		@Override
		public String toString() {
			return "id"+_id+" name = "+name+ " modificationDate = "+modification_date;
		}
		public Bitmap getPhoto() {
			return _attachments;
		}
		public void setPhoto(Bitmap photo) {
			this._attachments = photo;
		}
		public String get_rev() {
			return _rev;
		}
		public void set_rev(String _rev) {
			this._rev = _rev;
		}
    }