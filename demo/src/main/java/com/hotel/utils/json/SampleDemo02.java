package com.hotel.utils.json;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

/**
 * List转JSONArray  (只有 单值形式  )
 * @author Administrator
 */
public class SampleDemo02 {
	public static void main(String[] args) {
		List<String> names = new ArrayList<>() ;
		names.add("zs") ;
		names.add("ls") ;
		names.add("ww") ;
		JSONArray array = JSONArray.fromObject(names  );
		System.out.println(array);
	}
}
