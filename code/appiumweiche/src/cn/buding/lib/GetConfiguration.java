package cn.buding.lib;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;


public class GetConfiguration {
	
	private String filePath;
	private String nextLine[];
	List<String[]> approvalList = new ArrayList<String[]>();
	CSVReader reader;
	
	private String value;
	
	public GetConfiguration(String filePath){
		this.filePath = filePath;
	}
	
	public GetConfiguration(){
		
	}
	
	/**
	 * @author liliang
	 * @param  columnName
	 * @param  lineName
	 * @return
	 */
	public String getTestData(String columnName,String lineName){
				
		try {
			//get cvs data to array
			reader = new CSVReader(new FileReader(filePath));
			try {
				while ((nextLine = reader.readNext()) != null) {
					approvalList.add(nextLine);		
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		
			
			
			//获得字段所在列
			int count = 0;
			int index = 0;
			
			for(String j:approvalList.get(0)){
				count++;
//				System.out.println("count:"+count );
				if(j.equals(columnName)){
					index = count-1;
//					System.out.println(j );
				}
			}
					
			
			//获得某一行
			for(String[] i : approvalList){
				if(i[0].equals(lineName))
					   value = i[index].toString();
//				System.out.println(value);

			}
							
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	//get app directory
	   public static String getApp(String appFile) {
	        File projectRoot = new File(System.getProperty("user.dir"));
	        File app = new File(projectRoot, appFile);
	        
//	        System.out.println(app.getAbsolutePath());
//	        return app.toString();
	        return app.getAbsolutePath();
	    }
	
	   
	
	
	
	public static void main(String[] args){
//		GetConfiguration a = new GetConfiguration(System.getProperty("user.dir")+"/src/cn/buding/variable/gobalsettings.csv");
//		a.getServer();
		
//		System.out.println(getApp("/apps/weiche/weiche350.apk"));
//		System.out.println(a.getTestData("deviceName", "TC001"));
//		System.out.println(a.getTestData("ignoreUnimportantViews", "TC001"));
//		System.out.println(a.getTestData("newCommandTimeout", "TC001"));
//		System.out.println(a.getTestData("timeout", "TC001"));
//		System.out.println(a.getTestData("server", "TC001"));
//
//		String url = "http://"+ a.getTestData("server", "TC001")+"/wd/hub";
//		System.out.println(url);
		
		
	}
	
	
	
	
}
