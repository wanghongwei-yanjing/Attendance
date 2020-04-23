package rede;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
//创建Attendance类。
public class Attendance {
	Map<String,String> map =new HashMap<String,String>();//定义Map将信息存入
	Date date =new Date();	//定义时间
	SimpleDateFormat abc =new SimpleDateFormat("yyyy年MM月dd日,HH:mm:ss:"); //时间格式
	int hour=date.getHours(); //定义小时。调用date.getHours。
	String ID;		//定义ID名为字符串形式
	File file;		
	//判断用户名及密码是否正确
	public void login(){
		//将信息存入
		map.put("1001","1001");
		map.put("1002","1002");
		map.put("1003","1003");
		map.put("1004","1004");
		//从键盘上输入账号和密码
		Scanner sc =new Scanner(System.in);
		System.out.println("请输入账户");
		ID =sc.next();
		System.out.println("请输入密码");
		String password=sc.next();
		//进行循环
		while(true){
			//判断输入的用户名及密码与Map进行比较
			if(map.containsKey(ID)){
				if(map.get(ID).equals(password)){
					//如果用户名及密码正确，调用界面与操作类型 两个方法
					Interface();
					option();
				}else{
					System.out.println("用户名或密码有误！");
					//如果用户名错误，继续判断。
					login();
					break;
				}
			}else{
				System.out.println("用户名或密码有误！");	
				//如果密码错误，继续判断。
				login();
			}
		}
	}
	//创建界面的方法。
	public void Interface(){
		System.out.println("*********************************");
		System.out.println("******欢迎登陆迟到就扣钱考勤系统！*******");
		System.out.println("*********************************");
		System.out.println("1-上班签到");
		System.out.println("2-下班签出");
		System.out.println("3-考勤信息查阅");
		System.out.println("4-退出");
	}
	//创建操作类型的方法。
	public void option (){
		System.out.println("请选择操作类型");
		//定义option为输入的数字。
		int option ;
		Scanner sv =new Scanner(System.in);
		option = sv.nextInt();
		switch(option ){
		//输入1调用上班签到check_in这个方法。
		case 1:
			check_in();
			option ();
			break;
			//输入2调用下班签到check_out这个方法。
		case 2:
			check_out();
			option ();
			break;
			//输入3调用判断文件是否为空Estimate这个方法。
		case 3:
			//注意：先判断文件是否存在，再进行查阅信息。
			Estimate();
			option ();
			break;
		case 4:
			//输入4返回判断用户及密码是否正确login这个方法。
			login();
		default:
			//如果输入其他数字提示有误！
			System.out.println("你输入的选项有误，请重新输入！");
		}
	}
	//定义上班签到check_in方法。
	public void check_in(){
		//判断时间是否大于9点。
		if(hour>=9.00) {
			System.out.println("你上班迟到了!");
			FileOutputStream fos=null;
			//创建文件将迟到信息写入文件里。
			String work="上班打卡时间为:"+(abc.format(date))+"你上班迟到了!"+"\r\n";
			file=new File("d:\\attendance"+ ID +".txt");
			try {
				fos=new FileOutputStream(file,true);
				fos.write(work.getBytes());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try{
					fos.close();
				}catch (IOException e){
					e.printStackTrace();
				}
			}
		}
		else {
			//如果上班时间小于9点。
			System.out.println("上班打卡成功，祝您工作顺利!");
			FileOutputStream fos=null;
			//创建文件将上班成功信息写入文件里。
			String work="上班打卡时间为:"+(abc.format(date))+"\r\n";
			file=new File("d:\\attendance"+ID+".txt");
			try {
				fos=new FileOutputStream(file,true);
				fos.write(work.getBytes());
			} catch (FileNotFoundException e) {		
				e.printStackTrace();
			} catch (IOException e) {				
				e.printStackTrace();
			}finally{
				try{
					//关闭输出流。
					fos.close();
				}catch (IOException e){
					e.printStackTrace();
				}
			}
		}
	}
	//创建下班check_out这个方法。
	public void check_out(){
		//判断时间是否大于18点。
		if(hour>=18.00) {
			System.out.println("下班打卡成功,工作辛苦了！");
			FileOutputStream fos=null;
			//创建文件将下班成功信息写入文件里。
			String work="下班打卡时间为:"+(abc.format(date))+"下班打卡成功!"+"\r\n";
			file=new File("d:\\attendance"+ID+".txt");
			try {
				fos=new FileOutputStream(file,true);
				fos.write(work.getBytes());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try{
					//关闭输出流。
					fos.close();
				}catch (IOException e){
					e.printStackTrace();
				}
			}
		}
		else {	
			//如果下班时间小于18点。
			System.out.println("下班打卡成功,你早退了!扣工资!");
			FileOutputStream fos=null;
			//创建文件将下班早退信息写入文件里。
			String a="下班打卡时间为:"+(abc.format(date))+"你早退了!扣工资!"+"\r\n";
			file=new File("d:\\attendance"+ID+".txt");
			try {
				fos=new FileOutputStream(file,true);
				fos.write(a.getBytes());
			} catch (FileNotFoundException e) {		
				e.printStackTrace();
			} catch (IOException e) {				
				e.printStackTrace();
			}finally{
				try{
					fos.close();
				}catch (IOException e){
					e.printStackTrace();
				}
			}
		}
	}
	//创建先判断文件是否存在Estimate方法。
	public void Estimate(){
		file=new File("d:\\attendance"+ID+".txt");
		if(file.exists()){
			//如果文件存在调用信息查阅information方法。
			information();
		}else{
			//如果文件不存在输出文件为空。
			System.out.println("您查询的文件为空！");
		}
	}
	//创建考勤信息查阅information方法。
	public void information() {
		try {
			FileInputStream input = new FileInputStream("d:\\attendance"+ID+".txt");
			//将d:\\attendance"+ID+".txt 输出出来。
			byte[] b = new byte[input.available()];
			input.read(b);
			String str=new String(b);
			System.out.println(str);
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}