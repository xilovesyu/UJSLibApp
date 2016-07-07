package com.xixi.Recog;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class MyParser {
	static int width=44;
	static int each_width=11;
	static int height=10;
	static int black=-16514556;
	public static void main(String[] args) throws Exception {
//		BufferedImage bfimage=ImageIO.read(new File("yzm.png"));
//		BufferedImage trueImage=bfimage.getSubimage(6, 16, 44, 10);
//		ImageIO.write(trueImage, "png", new File("trueimage.png"));
//		int width=trueImage.getWidth();
//		int height=trueImage.getHeight();
//		System.out.println("width:"+width+", height:"+height);
//		int each_width=width/4;
//		System.out.println(each_width);
//		BufferedImage subimage=trueImage.getSubimage(each_width*2, 0, each_width, height);
//		ImageIO.write(subimage, "png", new File("subtest.png"));
		
//		BufferedImage test=ImageIO.read(new File("template/6.png"));
//		jundug69(test);
//		BufferedImage test2=ImageIO.read(new File("template/9.png"));
//		jundug69(test2);
	}
	public static String recogYZM(File f){
		int tempalteInt[]=null;
		StringBuffer buffer=new StringBuffer();
		try {
			tempalteInt = getTemplateInt();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedImage bfimage=null;
		try {
			bfimage = ImageIO.read(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedImage trueImage=gettrueImage(bfimage);
		int pics=4;
		for (int i = 0; i < pics; i++) {
			BufferedImage temp=trueImage.getSubimage(each_width*i, 0, each_width, height);
			int tempInt=getEachInt(temp);
			for (int j = 0; j < tempalteInt.length; j++) {
				if(tempInt!=39){
					if(tempInt==tempalteInt[j]){
//						System.out.print(j);
						buffer.append(j);
					}
				}
				else{
					//分辨6 和 9
					int num=jundug69(temp);
//					System.out.print(num);
					buffer.append(num);
					break;
				}
			}
		}
//		System.out.println();
		return buffer.toString();
	}
	public static BufferedImage gettrueImage(BufferedImage image){
		return image.getSubimage(6, 16, 44, 10);
	}
	public static int getEachInt(BufferedImage tempImage){
		int count=0;
		for (int j = 0; j < tempImage.getWidth(); j++) {
			for (int j2 = 0; j2 < tempImage.getHeight(); j2++) {
				if(tempImage.getRGB(j, j2)==black) {
					count++;
				}
			}
		}
		return count;
	}
	public static int jundug69(BufferedImage temp){
		int half_height=height/2;
		int firstpart=getEachInt(temp.getSubimage(0, 0, each_width, half_height));
		int secondpart=getEachInt(temp.getSubimage(0, half_height, each_width, half_height));
//		System.out.println(firstpart+" "+secondpart);
		if(firstpart==18){return 6;}
		else if(firstpart==21){return 9;}
		else{return -1;}
	}
	public static int[] getTemplateInt() throws Exception{
		int [] results=new int[10];
		for (int i = 0; i < results.length; i++) {
			results[i]=0;
			BufferedImage tempImage=ImageIO.read(new File("template/"+i+".png"));
			for (int j = 0; j < tempImage.getWidth(); j++) {
				for (int j2 = 0; j2 < tempImage.getHeight(); j2++) {
					if(tempImage.getRGB(j, j2)==black) {
						results[i]++;
					}
				}
			}
		}
		return results;
	}
}
