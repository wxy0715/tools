package com.hotel.utils.json;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SampleDemo01  {
	//1Map集合、JavaBean、字符串  -> JSon对象
	//a.Map集合JSon对象
	public static void demo01() {
		Map<String,String> map = new HashMap<>();
		map.put("s01", "zs") ;
		map.put("s02", "ls") ;
		map.put("s03", "ww") ;
		//map->json
		JSONObject json = new JSONObject(map);
		System.out.println(json);
		//可以得到json格式： { "key":value,"key":value,"key":value }
	}
	//b.JavaBean(普通对象 Person ) ->Json
	public static void demo02() {
		Person per = new Person();
		per.setName("zs");
		per.setAge(23);
		Address address = new Address("西安","北京");
		per.setAddress(address);
		//Person(JavaBean) -> Json
		//json方式
//		JSONObject json = new JSONObject( per );
		//json-lib方式
		net.sf.json.JSONObject json = new net.sf.json.JSONObject();
		json = json.fromObject(per ) ;
		
		System.out.println(json);
		//对象->json:  {对象的属性名1:属性值1, 对象的属性名2:属性值2 }
		/*
		 * 	{"address":{"schoolAddress":"北京","homeAddress":"西安"},
		 * 	 "name":"zs",
		 * 	 "age":23}
		 */
	}
	//String->json
	public static void demo03() {
		String str = "{\"name\":\"zs\",\"age\":23}" ;
		//json.jar
//		JSONObject json = new JSONObject( str );
		net.sf.json.JSONObject json = new net.sf.json.JSONObject();
		json = json.fromObject(str ) ;
		System.out.println(json);
	}
	
	//文件->JSON （文件->String->JSON）
	public  void demo04() throws IOException {//static->static 
//		 绝对地址
//		 new 绝对地址 FIle("e:\\abc\\a.txt")
//	 InputStream in = 	super.getClass().getClassLoader().getResourceAsStream("json/per.json") ;
//	 byte[] bs = new byte[10];
//	 int len = -1 ;
//	 StringBuffer sb = new StringBuffer();
//	 while( (len=in.read(bs)) !=-1) {
//		//byte[]->String
//		 String str = new String(bs,0,len);
//		 sb.append(str) ;
//	 }
	//文件->字符串		commons-io.jar
		String s= FileUtils.readFileToString(new File("D:\\study\\eclipse-workspace\\ViewProject\\src\\json\\per.json")) ;
		
	 //String->Json
//	 String s = sb.toString() ;
	 JSONObject json = new JSONObject( s );
	 System.out.println(json);
	}
	
	//生成json文件
	public static void demo05() throws JSONException, IOException {
		//准备json数据 (map->json)
		Map<String,Person> map = new HashMap<>();
		Person p1 = new Person(23,"zs", new Address("xa","bj")  );
		Person p2 = new Person(24,"ls", new Address("xa1","bj1")  );
		Person p3 = new Person(25,"ww", new Address("xa2","bj2")  );
		map.put("zs", p1) ;
		map.put("ls", p2) ;
		map.put("ww",p3 ) ;
		
		//map->json
		JSONObject json = new JSONObject(map);
		//生成json文件
		Writer writer  = new FileWriter("e:\\p.obj") ;
		json.write(writer  ) ;
		writer.close();
	}
	
	//jsonArry: 
	/*
	 * 	[{"name":"zs","age":23},{"classname":"lq","classno":1},{"schoolname":"xj","zone":"xj"}]
	 */
	//	//String格式的json数组 -> json数组
	public static void demo06() {
		String jsonArrayStr = "[{\"name\":\"zs\",\"age\":23},{\"classname\":\"lq\",\"classno\":1},{\"schoolname\":\"xj\",\"zone\":\"xj\"}]" ;
		JSONArray jArray = new JSONArray(jsonArrayStr);
		System.out.println(jArray);
	}
	
	//对于json的类型转换，通常需要引入另一个json-lib库
	//map->JSONArray
	public static void demo07() {
		Map<String,String> map = new HashMap<>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
//		JSONArray jArray = new JSONArray(map);
//		System.out.println(jArray);
		
		//冲突：JSONArray既存在于 json.jar  又存在于json-lib库
		net.sf.json.JSONArray jArray = new net.sf.json.JSONArray();
		//map->json
		jArray = jArray.fromObject( map  ) ;
		System.out.println(jArray);
	}
	
	//之后，全部使用json-lib
	//JSONArray->map
	public static void demo08() {
		//JSONArray->获取每一个json -> key：value ->map
		
		//准备jsonarry数据
		String jArrayStr = "["
					      + "{\"name\":\"zs\",\"age\":23},"
					      + "{\"classname\":\"lq\",\"classno\":1},"
					      + "{\"schoolname\":\"xa\",\"zone\":\"xj\"}"
					   + "]	" ;
		//string -> jsonArray
		net.sf.json.JSONArray jArray = new net.sf.json.JSONArray();
		jArray = jArray.fromObject(jArrayStr) ;
		//JSONArray->获取每一个json
		Map<String,Object> map = new HashMap<>();
		
		for(int i=0;i<jArray.size();i++) {
			Object o =  jArray.get(i) ;//获取每一个json
			net.sf.json.JSONObject json = (net.sf.json.JSONObject)o;
			//获取每一个json的key/value  ->map
			Set<String> keys = json.keySet();//每个json的所有Key
			for(String key:keys) {//遍历每个json的所有key
				Object value = json.get(key);
				map.put(key, value) ;
			}
		}
		
		System.out.println(map);
	}
	
	
	public static void main(String[] args) throws IOException {
//		demo01();
//		demo02();
//		demo03();
//		SampleDemo01 sample = new SampleDemo01();
//		sample.demo04();
//		demo05();
//		demo06();
//		demo07();
		demo08();
	}
}
