package cn.buding.pages;


import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cn.buding.lib.Action;
import cn.buding.variable.GlobalDataStore;
import io.appium.java_client.android.AndroidDriver;



public class AddCarPage {
	
public  AndroidDriver driver;
public  Action action;
	
	public AddCarPage(AndroidDriver driver,Action action){
		this.driver = driver;
		this.action = action;
	}
	
	//行驶证不在身边
	public static String LeaveDriverLicese = "cn.buding.martin:id/set_alarm";
	//车辆前缀
	public static String PrefixLicenceplate = "cn.buding.martin:id/city_alias";
	//车牌号码
	public static String LicencePlatenum = "cn.buding.martin:id/license_plate_text";
	
	//发动机号帮助
	public static String enginehelp = "cn.buding.martin:id/engine_info";
	//发动机号
	public static String enginenum = "cn.buding.martin:id/engine_text";
	//查询城市
	public static String SelectCity = "cn.buding.martin:id/city_container";
	
	//保存并查询
	public  static String save = "cn.buding.martin:id/save";
	
	//微车用户使用协议
	public static String UserAgreement = "cn.buding.martin:id/agreement";
	
	/*
	 * 车辆保存对话框
	 * 一首再说
	 * 马上完善
	 */
	public static String SayLater = "android:id/button2";
	public static String DoNow = "android:id/button1";
	public static String activityname = null;
	
	//删除按钮
	public static String deleteCar = "cn.buding.martin:id/delete";
	
	/*
	 * 删除车辆对话框
	 * 删除
	 * 取消
	 */
	public static String confirm = "android:id/button1";
	public static String cancel = "android:id/button2";
	

	
	

	public void addCar(){

		action.click(By.id(HomePage.add_vehicle));
		action.waitForRep(10);
	    action.inputDataById(LicencePlatenum, "N1S273");
	    action.inputDataById(enginenum, "7706690");
	    action.click(By.id(save));
	    action.click(By.id(SayLater));
	    action.waitForRep(20);
		action.click(By.id(GlobalDataStore.back));
		action.waitForRep(60);
		activityname = driver.currentActivity();
		Assert.assertTrue(activityname.concat("ButterflyActivity"), true);
	    		
	}
	//添加随机车
	public String addRandomCar(){
		
		action.click(By.id(HomePage.add_vehicle));
		String vehicleNumber = action.carString();
		String vehicleEngine = action.carString();
		action.inputDataById(LicencePlatenum, vehicleNumber);
		action.inputDataById(enginenum, vehicleEngine);
	    action.click(By.id(save));
	    action.click(By.id(SayLater));
	    action.waitForRep(20);
		action.click(By.id(GlobalDataStore.back));
		action.waitForRep(60);
		activityname = driver.currentActivity();
		Assert.assertTrue(activityname.concat("ButterflyActivity"), true);
		String changedVehicleNumber = "京"+vehicleNumber.toUpperCase();
		return changedVehicleNumber;
	}
	
	public void deleteCar(){
		driver.scrollTo("N1S273").click();
		action.click(By.id(HomePage.editcarbutton));	
		action.waitForRep(60);
		action.click(By.id(deleteCar));	
		action.click(By.id(confirm));	
		
	}
	
	public void backTohome(){
		 action.click(By.id(SayLater));
		    action.waitForRep(20);
			action.click(By.id(GlobalDataStore.back));
			action.waitForRep(60);
	}
			
	
	public void addexistVehicle() throws InterruptedException{
		action.click(By.id(HomePage.add_vehicle));
		action.waitForRep(10);
	    action.inputDataById(LicencePlatenum, "N1S273");
	    action.inputDataById(enginenum, "7706690");
	    action.click(By.id(save));
	    action.click(By.id(SayLater));
	    List<WebElement> unhandled;
	    action.waitForRep(60);
		try {
			if(driver.findElementsById("cn.buding.martin:id/handled")!=null){
			unhandled = driver.findElementsById("cn.buding.martin:id/handled");
			System.out.println("未处理违章："+unhandled.size());}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("无未处理违章");
		}
	    action.back();
		action.click(By.id(HomePage.add_vehicle));
		action.waitForRep(10);
	    action.inputDataById(LicencePlatenum, "N1S273");
	    action.inputDataById(enginenum, "7706690");
	    action.click(By.id(save));
	    action.click(By.id(SayLater));
	    //System.out.println(driver.currentActivity());
	    assertEquals((driver.currentActivity()),".activity.violation.AddVehicleActivity");
	    action.back();
	    action.click(By.id(SayLater));
	    action.click(By.name("京N1S273"));
		action.click(By.id("cn.buding.martin:id/edit"));	
		action.waitForRep(60);
		action.click(By.id(deleteCar));	
		action.click(By.id(confirm));
	}
	
	public void editVehicleBeizhu() throws InterruptedException{
		action.waitForRep(60);
		action.click(By.id(HomePage.add_vehicle));
		action.waitForRep(10);
	    action.inputDataById(LicencePlatenum, "N1S273");
	    action.inputDataById(enginenum, "7706690");
	    action.click(By.id(save));
	    action.click(By.id(SayLater));
	    action.back();
	    action.click(By.id("cn.buding.martin:id/empty_car"));
	    action.click(By.id("cn.buding.martin:id/show_more"));
	    action.inputDataById("cn.buding.martin:id/remark_text", "我的爱车");
	    driver.scrollTo("保存并查询").click();
	    List<WebElement> unhandled;
	    action.waitForRep(60);
		try {
			if(driver.findElementsById("cn.buding.martin:id/handled")!=null){
			unhandled = driver.findElementsById("cn.buding.martin:id/handled");
			System.out.println("未处理违章："+unhandled.size());}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("无未处理违章");
		}
		action.back();
		action.waitForRep(60);
		Thread.sleep(5000);
	    action.click(By.name("我的爱车"));
		action.click(By.id("cn.buding.martin:id/edit"));	
		action.waitForRep(60);
		action.click(By.id(deleteCar));	
		action.click(By.id(confirm));
	}
	
	public void addVehicleType() throws InterruptedException{
		action.click(By.id(HomePage.add_vehicle));
		action.waitForRep(10);
	    action.inputDataById(LicencePlatenum, "N1S273");
	    action.inputDataById(enginenum, "7706690");
	    action.click(By.id(save));
	    action.click(By.id(SayLater));
	    action.back();
	    Thread.sleep(5000);
	    action.click(By.id("cn.buding.martin:id/empty_car"));
	    //完善信息
	    action.click(By.id("cn.buding.martin:id/show_more"));
	    //选择车型
	    action.click(By.id("cn.buding.martin:id/type_row"));
	    action.click(By.id("cn.buding.martin:id/type_row"));
	    action.click(By.className("android.widget.RelativeLayout"));
	    action.waitForRep(2);
	    action.click(By.className("android.widget.RelativeLayout"));
	    assertEquals(driver.currentActivity(), ".activity.violation.EditVehicleActivity");
	    driver.scrollTo("保存并查询").click();
	    List<WebElement> unhandled;
	    action.waitForRep(60);
		try {
			if(driver.findElementsById("cn.buding.martin:id/handled")!=null){
			unhandled = driver.findElementsById("cn.buding.martin:id/handled");
			System.out.println("未处理违章："+unhandled.size());}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("无未处理违章");
		}
		action.back();
		Thread.sleep(8000);
	    action.click(By.name("京N1S273"));
		action.click(By.id("cn.buding.martin:id/edit"));	
		action.waitForRep(60);
		action.click(By.id(deleteCar));	
		action.click(By.id(confirm));
	}
	
	public void engineHelp() throws InterruptedException{
	action.click(By.id(HomePage.add_vehicle));
	action.waitForRep(10);
	action.click(By.id("cn.buding.martin:id/engine_info"));
	action.constrainClick(10);
	Thread.sleep(1000);
	action.back();
	}
	
	public void changesearchCity() throws InterruptedException{
		action.click(By.id(HomePage.add_vehicle));
		action.click(By.id(SelectCity));
		action.click(By.name("北京(北京)"));
		action.click(By.name("上海(上海)"));
		action.back();
		System.out.println(driver.findElementById(SelectCity).getText());
		assertEquals(driver.findElementById(SelectCity).getText(),"上海  ");
		action.click(By.id(SelectCity));
		action.click(By.name("上海(上海)"));
		action.click(By.name("北京(北京)"));
		action.back();
		assertEquals(driver.findElementById(SelectCity).getText(),"北京  ");
		Thread.sleep(500);
		action.back();
		//未保存车辆信息返回
		action.click(By.id("android:id/button2"));	
	}
	
	
	public void searchtoChoseCity() throws InterruptedException{
		action.click(By.id(HomePage.add_vehicle));
		action.click(By.id(SelectCity));
		action.click(By.name("北京(北京)"));
		//搜索输入框
		action.inputDataById("cn.buding.martin:id/et_search","上海");
		action.click(By.name("上海(上海)"));
		action.back();
		Thread.sleep(500);
		action.back();
		assertEquals(driver.findElementById(SelectCity).getText(),"上海  ");
		action.click(By.id(SelectCity));
		action.click(By.name("上海(上海)"));
		action.click(By.name("北京(北京)"));
		action.back();
		assertEquals(driver.findElementById(SelectCity).getText(),"北京  ");
		Thread.sleep(500);
		action.back();
		action.click(By.id("android:id/button2"));	
	}
	
	public void voilationDetail(){
		try {
			System.out.println(driver.findElementById("cn.buding.martin:id/tv_status").getText());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		action.click(By.id("cn.buding.martin:id/name"));
		System.out.println("违章地点:"+driver.findElementById("cn.buding.martin:id/address").getText());
		System.out.println(driver.findElementById("cn.buding.martin:id/content").getText());
		System.out.println(driver.findElementById("cn.buding.martin:id/time").getText());
		System.out.println(driver.findElementById("cn.buding.martin:id/status").getText());
		System.out.println(driver.findElementById("cn.buding.martin:id/fine").getText());
		System.out.println(driver.findElementById("cn.buding.martin:id/deduction").getText());
		System.out.println(driver.findElementById("cn.buding.martin:id/count").getText());
		action.waitForRep(5);
		try {
			if(driver.findElementById("cn.buding.martin:id/correct")!=null){
				System.out.println("有纠错按钮");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("无纠错按钮");
		}
		action.back();
		//进入统计页面
		action.click(By.id("cn.buding.martin:id/mode"));
		action.click(By.id("cn.buding.martin:id/mode"));
		action.back();
	}

}
