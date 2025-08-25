package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.Porder_Detail;

public class Tool {

	public static void main(String[] args) {
		/*Tool.saveFile(new Member("abc","abc","789","taipei","000"), "D:/ABC/member.txt");
		
		System.out.println(Tool.readFile("D:/ABC/member.txt"));*/
		/*
		Member m=(Member)Tool.readFile("D:/ABC/member.txt");
				
		System.out.println(m.getName()+"\t"+m.getUsername()+"\t"+m.getPassword());*/

	}
	
	//存物件檔
	public static void saveFile(Object object,String fileName)
	{
		try {
			FileOutputStream fos=new FileOutputStream(fileName);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(object);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//讀取物件檔
	public static Object readFile(String fileName)
	{
		Object object=null;
		
		try {
			FileInputStream fis=new FileInputStream(fileName);
			ObjectInputStream ois=new ObjectInputStream(fis);
			object=ois.readObject();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return object;
	}
	
	public static void writeFile(String filename, List<Porder_Detail> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Porder_Detail> readPorderDetailFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Porder_Detail>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

}