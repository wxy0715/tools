package com.hotel.utils.xml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

//XML解析
public class Sample {
	public static void main(String[] args) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException {
		List<Dog> dogs = XMLParseUtil.parseXmlToList( "E:\\wxySvn\\王星宇\\MyProject\\demo\\src\\main\\java\\com\\hotel\\utils\\xml\\dogs.xml") ;
		System.out.println(dogs);
		for (Dog dog : dogs) {
			System.out.println(dog);
		}
	}
}
