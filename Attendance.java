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
//����Attendance�ࡣ
public class Attendance {
	Map<String,String> map =new HashMap<String,String>();//����Map����Ϣ����
	Date date =new Date();	//����ʱ��
	SimpleDateFormat abc =new SimpleDateFormat("yyyy��MM��dd��,HH:mm:ss:"); //ʱ���ʽ
	int hour=date.getHours(); //����Сʱ������date.getHours��
	String ID;		//����ID��Ϊ�ַ�����ʽ
	File file;		
	//�ж��û����������Ƿ���ȷ
	public void login(){
		//����Ϣ����
		map.put("1001","1001");
		map.put("1002","1002");
		map.put("1003","1003");
		map.put("1004","1004");
		//�Ӽ����������˺ź�����
		Scanner sc =new Scanner(System.in);
		System.out.println("�������˻�");
		ID =sc.next();
		System.out.println("����������");
		String password=sc.next();
		//����ѭ��
		while(true){
			//�ж�������û�����������Map���бȽ�
			if(map.containsKey(ID)){
				if(map.get(ID).equals(password)){
					//����û�����������ȷ�����ý������������ ��������
					Interface();
					option();
				}else{
					System.out.println("�û�������������");
					//����û������󣬼����жϡ�
					login();
					break;
				}
			}else{
				System.out.println("�û�������������");	
				//���������󣬼����жϡ�
				login();
			}
		}
	}
	//��������ķ�����
	public void Interface(){
		System.out.println("*********************************");
		System.out.println("******��ӭ��½�ٵ��Ϳ�Ǯ����ϵͳ��*******");
		System.out.println("*********************************");
		System.out.println("1-�ϰ�ǩ��");
		System.out.println("2-�°�ǩ��");
		System.out.println("3-������Ϣ����");
		System.out.println("4-�˳�");
	}
	//�����������͵ķ�����
	public void option (){
		System.out.println("��ѡ���������");
		//����optionΪ��������֡�
		int option ;
		Scanner sv =new Scanner(System.in);
		option = sv.nextInt();
		switch(option ){
		//����1�����ϰ�ǩ��check_in���������
		case 1:
			check_in();
			option ();
			break;
			//����2�����°�ǩ��check_out���������
		case 2:
			check_out();
			option ();
			break;
			//����3�����ж��ļ��Ƿ�Ϊ��Estimate���������
		case 3:
			//ע�⣺���ж��ļ��Ƿ���ڣ��ٽ��в�����Ϣ��
			Estimate();
			option ();
			break;
		case 4:
			//����4�����ж��û��������Ƿ���ȷlogin���������
			login();
		default:
			//�����������������ʾ����
			System.out.println("�������ѡ���������������룡");
		}
	}
	//�����ϰ�ǩ��check_in������
	public void check_in(){
		//�ж�ʱ���Ƿ����9�㡣
		if(hour>=9.00) {
			System.out.println("���ϰ�ٵ���!");
			FileOutputStream fos=null;
			//�����ļ����ٵ���Ϣд���ļ��
			String work="�ϰ��ʱ��Ϊ:"+(abc.format(date))+"���ϰ�ٵ���!"+"\r\n";
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
			//����ϰ�ʱ��С��9�㡣
			System.out.println("�ϰ�򿨳ɹ���ף������˳��!");
			FileOutputStream fos=null;
			//�����ļ����ϰ�ɹ���Ϣд���ļ��
			String work="�ϰ��ʱ��Ϊ:"+(abc.format(date))+"\r\n";
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
					//�ر��������
					fos.close();
				}catch (IOException e){
					e.printStackTrace();
				}
			}
		}
	}
	//�����°�check_out���������
	public void check_out(){
		//�ж�ʱ���Ƿ����18�㡣
		if(hour>=18.00) {
			System.out.println("�°�򿨳ɹ�,���������ˣ�");
			FileOutputStream fos=null;
			//�����ļ����°�ɹ���Ϣд���ļ��
			String work="�°��ʱ��Ϊ:"+(abc.format(date))+"�°�򿨳ɹ�!"+"\r\n";
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
					//�ر��������
					fos.close();
				}catch (IOException e){
					e.printStackTrace();
				}
			}
		}
		else {	
			//����°�ʱ��С��18�㡣
			System.out.println("�°�򿨳ɹ�,��������!�۹���!");
			FileOutputStream fos=null;
			//�����ļ����°�������Ϣд���ļ��
			String a="�°��ʱ��Ϊ:"+(abc.format(date))+"��������!�۹���!"+"\r\n";
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
	//�������ж��ļ��Ƿ����Estimate������
	public void Estimate(){
		file=new File("d:\\attendance"+ID+".txt");
		if(file.exists()){
			//����ļ����ڵ�����Ϣ����information������
			information();
		}else{
			//����ļ�����������ļ�Ϊ�ա�
			System.out.println("����ѯ���ļ�Ϊ�գ�");
		}
	}
	//����������Ϣ����information������
	public void information() {
		try {
			FileInputStream input = new FileInputStream("d:\\attendance"+ID+".txt");
			//��d:\\attendance"+ID+".txt ���������
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