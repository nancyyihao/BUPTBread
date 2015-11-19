package com.bread.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartData {
	private final static ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	private static Map<String, Object> map;

	public static void addCart(String id, String name, String price,
			String num, String image) {
		map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("name", name);
		map.put("price", price);
		map.put("num", num);
		map.put("image", image);

		list.add(map);
	}

	public static void removeCart(String id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("id") == id) {
				list.remove(i);
			}
		}
	}

	public static ArrayList<Map<String, Object>> getList() {
		return list;
	}

	public static void editCart(String id, String name, String price,
			String num, String image) {
		Boolean addflag = true;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("id") == id) {
//				removeCart(id);
//				addCart(id, name, price, num, image);
				list.get(i).put("id", id);
				list.get(i).put("name", name);
				list.get(i).put("price", price);
				list.get(i).put("num", num);
				list.get(i).put("image", image);
				addflag = false;
			}
		}
		if (addflag) {
			addCart(id, name, price, num, image);
		}
	}
	
	public static int findCart(int id){
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get("id") == String.valueOf(id)) {
				return Integer.parseInt(list.get(i).get("num").toString());
			}
		}
		return 0;
	}

	public static void removeAllCart() {
		map.clear();
		list.clear();
	}

	public static ArrayList<Map<String, Object>> getCartList() {

		return list;
	}
	
	public static int getCartCount(){
		return list.size();
	}
}
