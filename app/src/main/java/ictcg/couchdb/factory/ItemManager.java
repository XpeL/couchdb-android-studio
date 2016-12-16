package ictcg.couchdb.factory;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ictcg.couchdb.retrofit.task.ItemDto;

public class ItemManager
{	
	/** Instance unique pré-initialisée */
	private static ItemManager INSTANCE = new ItemManager();
	
	private Map<String, ItemDto> items;
	
	public ItemManager(){
		items = new HashMap<String, ItemDto>();
	}
	/** Point d'accès pour l'instance unique du singleton */
	public static ItemManager getInstance()
	{	return INSTANCE;
	}
	public Collection<ItemDto> getItems() {
		return items.values();
	}
	public void addItem(ItemDto item){
		items.put(item.getId(), item);
	}
	public ItemDto getItem(String id){
		return items.get(id);
	}
}