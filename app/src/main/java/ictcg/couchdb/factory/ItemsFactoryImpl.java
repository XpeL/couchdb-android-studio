package ictcg.couchdb.factory;

import android.content.ClipData.Item;

import java.util.List;
import java.util.Map;

import ictcg.couchdb.retrofit.task.ItemDto;


public class ItemsFactoryImpl implements ItemsFactory{
	private Map<String, ItemDto> items;
	
	public ItemsFactoryImpl(List<Item> items) {
		items = items;
	}
	
}
