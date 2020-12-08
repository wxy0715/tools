package com.hotel.utils.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SampleDemo {
	//(字符串形式)json数组 ->List
	public static void demo01() {
		String jsonArrayStr = "[{\"name\":\"zs\",\"age\":23},{\"classname\":\"lq\",\"classno\":1},{\"schoolname\":\"xa\",\"zone\":\"xj\"}]" ;
		JSONArray jsonArray = JSONArray.fromObject(jsonArrayStr);//String ->json数组
		//json数组 ->List
		/*
		 * 1.获取 json数组 的每一个json， 再将每一个json 放入list
		 * 2.JSONArray.toCollection()
		 */
		  Collection<JSONObject> collection = JSONArray.toCollection(jsonArray) ;
		  //Collection ->List
		  List<JSONObject> list = new ArrayList(collection);
		  System.out.println(list);
	}
	
	//(字符串形式)json数组 ->数组
	public static void demo02() {
		String jsonArrayStr = "[{\"name\":\"zs\",\"age\":23},{\"classname\":\"lq\",\"classno\":1},{\"schoolname\":\"xa\",\"zone\":\"xj\"}]" ;
		JSONArray jsonArray = JSONArray.fromObject(jsonArrayStr);//String ->json数组
		//json数组 ->List
		/*
		 * 1.获取 json数组 的每一个json， 再将每一个json 放入list
		 * 2.JSONArray.toCollection()
		 */
		  Object array = JSONArray.toArray(jsonArray) ;
		  Object[] arrs = (Object[])array ;
		  System.out.println(arrs[1]);
	}
	
	public static void main(String[] args) {
		//(字符串形式)json数组 ->List/数组
		demo02();
	}
}
