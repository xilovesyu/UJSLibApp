package com.xixi.LibApp;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.ArrayList;

import com.xixi.Model.Book;

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
    	int times=7000;
    	//BufferedWriter wirter=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("name.txt"))));
		
    	for (int i = 0; i < 1; i++) {
    		client.setName("2211507014");
    		client.setPass("2211507014");
    		boolean flag=client.login();
    		if(flag){
//    			System.out.println(client.getName());
//    			wirter.write(baseName+(num+i)+":"+client.getName());
//    			wirter.newLine();
//    			wirter.flush();
    			
    			System.out.println(client.getProfile());
    			ArrayList<Book> lists=client.getBorrowBooks();
    			for (int j = 0; j < lists.size(); j++) {
					System.out.println(lists.get(j).toString());
				}
    		}
		}
    	//wirter.flush();
    	//wirter.close();
    	
    	
    }
}
