package com.xixi.LibApp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.xixi.Mail.Mail;
import com.xixi.Model.Book;
import com.xixi.Model.Person;
import com.xixi.utils.MyTimer;
import com.xixi.utils.PropertiesUtils;

/**
 * Main method
 *
 */
public class App {
	public static void main(String[] args) throws Exception {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					runMain();
				} catch (Exception e) {
					System.out.println("error!!");
				}
			}
		};
		new Timer(false).schedule(task, 0, 2000);
	}

	public static void runMain() {
		Person person = new Person();
		person.setStuNum(PropertiesUtils.getPro("mylibNum"));
		person.setPasswd(PropertiesUtils.getPro("mylibCode"));
		boolean flag = person.login();
		if (flag) {
			System.out.println(person.getProfile());
			ArrayList<Book> lists = person.getBorrowBooks();
			StringBuffer buffer = new StringBuffer();
			buffer.append("学号：" + person.getStuNum() + "\n");
			boolean isChaoqi = false;
			for (int j = 0; j < lists.size(); j++) {
				Book temp = lists.get(j);
				String name = temp.getName();
				String time = temp.getPayTime();
				LocalDate date = MyTimer.createTime(time);
				int[] timeleft = MyTimer.getTwoDaysInterVal(MyTimer.getCurrentTime(), date);
				if (timeleft[0] == 0 && timeleft[1] == 0 && (timeleft[2] < 4)) {
					// 需要发送邮件进行提醒
					isChaoqi = true;
					buffer.append("\n" + (j + 1) + "." + temp.getName() + " 还有" + timeleft[1] + "月" + timeleft[2] + "天超期");
				}
			}
			if (isChaoqi) {
				Mail sqm = new Mail();
				sqm.setUsername(PropertiesUtils.getPro("qqmailNum"));
				sqm.setPassword(PropertiesUtils.getPro("qqmailCode"));
				sqm.setTo(person.getStuNum() + "@ujs.edu.cn");
				sqm.setSubject("借书情况");
				sqm.setText(buffer.toString());
				sqm.send();
			}
		}

	}

}
