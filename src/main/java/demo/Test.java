package demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Test {
public static void main(String[] args) {
	String test="<tbody><td><span class=\"bluetext\">姓名：</span>奚家祥</td><td><span class=\"bluetext\">姓名：</span>奚家祥</td></tbody>";
	Document doc=Jsoup.parse(test);
	Elements tds=doc.select("td");
	System.out.println(tds.text());
}
}
