package edu.java.sonny.fileutil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import edu.java.sonny.model.Sonny;

public class FileUtil {

	public static final String DATA_DIR = "data";
	public static final String DATA_FILE = "sonnyDate.dat";
	
	private FileUtil () {}
			
	
	// 손흥민 데이터 저장 폴더
	public static File intiDataDir() {
	
		File newFolder = new File(DATA_DIR);
		if (newFolder.exists()) {
			System.out.println("데이터 폴더가 존재한다");
		}  else {
			newFolder.mkdir();
			System.out.println("폴더를 생성했다/.");
		}
		return newFolder;
	}
	
	public static List<Sonny> readDataFromFile(File file) { 
		//12) 메서드 생성과 return null까지만 만들어놓고 나머지는 TODO. 아래 write도

		List<Sonny> list = new ArrayList<>(); // 20) 리턴을 List<Contact> 타입으로 보내야하기 때문에 생성,초기화해준다.

		try (
				FileInputStream in = new FileInputStream(file); // 21) 파일타입. 파일을 불러와서 읽을거야.
				BufferedInputStream bin = new BufferedInputStream(in);
				ObjectInputStream oin = new ObjectInputStream(bin);	// 22) 파일의 데이터는 object이기 때문에 ois가 필요한것
		)	{
			list = (List<Sonny>) oin.readObject();  // 23) List<Contact> list 에 파일의 데이터를 읽어온것을 저장

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		// 24 ) write로 가자
	}

	/**
	 * writeDataToFile. argument로 전달된 data를 (직렬화해서) file에 씀.
	 * 
	 * @param data 파일에 쓸 데이터. Contact 타입을 저장하는 리스트(List<Contact>)
	 * @param file 데이터 파일(File) 객체 
	 */
	public static void writeDataToFile(List<Sonny> data, File file) {
		//13) 이것도 12와 마찬가지로 메서드 생성을 해놓고 TODO해놓고 initData로 가자
		
		try ( // 25) try catch 만들자.
				FileOutputStream out = new FileOutputStream(file);
				BufferedOutputStream bout = new BufferedOutputStream(out);
				ObjectOutputStream oout = new ObjectOutputStream(bout);
		) 	{
			oout.writeObject(data);	// 26) object 타입으로 data를 write 하겠다. 그럼 contact.dat 파일이 생기게 된다.
									// 27) FileUtil에서 할건 끝났다. 이제 다오임플 메서드를 수정해주자.
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * initData. 연락처 데이터 파일이 있으면, 파일의 내용을 읽어서 리스트를 생성하고 리턴. 
	 * 연락처 데이터 파일이 없으면, 빈 리스트를 리턴.
	 * 
	 * @return Contact 타입을 원소로 갖는 리스트(List<Contact>).
	 */
	public static List<Sonny> initData() { //11) 이걸 하기 전에 readDataFromFile을 먼저 만들어야 한다. 읽어야하기 때문에
		
		// 14) 파일의 존재를 확인해야하는 메서드를 만든다.
		
		File newFile = new File(DATA_DIR, DATA_FILE); // 15) .\data\contacts.dat 파일 객체를 만든다

		List<Sonny> emptyList = new ArrayList<>(); // 16) 없으면 빈 리스트를 리턴해야하기 때문에 빈리스트 생성
		if(newFile.exists()) {
			emptyList = readDataFromFile(newFile);  // 17) 읽은 연락처를 저장해야 하기때문에 미리 메서드의 틀을 만든것.
			System.out.println("연락처 파일 로딩...");
		} 
		
		return emptyList;
		
	}	// 18) 다오임플 싱글톤 2단계로 ㄱ

}

