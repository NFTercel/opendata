package cn.buding.pages;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import cn.buding.lib.Action;
import cn.buding.variable.GlobalDataStore;



public class HomePage {
	
public AndroidDriver driver;
public Action action;
	
	public HomePage(AndroidDriver driver,Action action){
		this.driver = driver;
		this.action = action;
	}
	
	/*
	 * 账户与设置
	 * 请登录
	 * 刷新
	 * 添加车辆
	 */
	public static String account = "cn.buding.martin:id/more";
	public static String loginPl = "cn.buding.martin:id/tv_login_tip";
	public static String newMess = "cn.buding.martin:id/new_events";
	public static String refresh = "cn.buding.martin:id/refresh";
	public static String add_vehicle = "cn.buding.martin:id/add_vehicle";
	
	/*
	 * 贴条地图
	 * @description text
	 * 
	 */
	public static String labelbytext = "贴条地图";
	
	/*
	 * 天气widget
	 * 天气
	 * 温度
	 * 控件页
	 * 限行尾号
	 * @description text
	 * 广告
	 */
	public static String weather = "cn.buding.martin:id/weather_content";
	public static String weatherDetail = "cn.buding.martin:id/weather";
	public static String temperature = "cn.buding.martin:id/temp";
	public static String weatherPaper = "cn.buding.martin:id/pager";
	public static String limNumber = "cn.buding.martin:id/numlim";
	public static String weatherTitle = "cn.buding.martin:id/title";
	public static String homepageAd = "cn.buding.martin:id/bottom_ad_text";
	public static String lifeicon = "微生活";
	
	/*
	 * Onroadicon
	 */
	public static String onroadicon = "cn.buding.martin:id/rec";
	public static String onroadButton = "cn.buding.martin:id/onroad_title";
	public static String onroadNum = "cn.buding.martin:id/text_onroad_dist";
	
	
	/*
	 * 车辆widget
	 * 车牌号
	 * 车牌前缀
	 * 新违章
	 * 未处理违章
	 */
	public static String editcarbutton = "cn.buding.martin:id/empty_car";
	public static String searchvlolation = "cn.buding.martin:id/count_unhandle";
	public static String vehicle_number = "cn.buding.martin:id/plate";
	public static String car_prefix = "cn.buding.martin:id/city_alias";
	public static String newVio = "cn.buding.martin:id/count";
	public static String unhandledVio = "cn.buding.martin:id/count_unhandle";
	public static String welcomeSen = "欢迎使用微车违章查询";
	


	
	public  void login(){
		action.waitForRep(60);
		action.click(By.id(account));
	}
	
	
	public void compareCars(String st) throws InterruptedException{
		action.waitForRep(30);
		try {
			List<WebElement> carInfo =driver.findElementsById(vehicle_number);
			/*for(int i=0;i<carInfo.size();i++){
				System.out.println(carInfo.get(i).getText());
			}*/
			assertEquals(carInfo.get(0).getText(), st);
				System.out.println("车辆信息正确");
			System.out.println("**********");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("无车辆信息");
			
		}
		Thread.sleep(3000);
	}
	
	public void verifyCurrentCity(String s){
		action.click(By.id(weather));
		if(driver.findElementById(weatherTitle).getText().contains(s)){
			System.out.println("当前天气城市上海");
		}else{
			System.out.println("切换城市失败");
		}
		login();
		action.back();
		action.click(By.id(add_vehicle));
		assertEquals("沪", driver.findElementById(car_prefix).getText());
		System.out.println("城市前缀上海");
		action.waitForRep(60);
		action.back();
		action.click(By.id(weatherTitle));
        //action.click(By.id("cn.buding.martin:id/no"));
	}
		

		public void loginPlease(){
			String a = driver.findElementById(loginPl).getText();
			System.out.println(a);
			if(a.equals("请登录")){
				System.out.println("用户未登录页面");
			}else{
				System.out.println("用户已登录页面");
			}
		
		}
		
		public void noLoginPlease() throws InterruptedException{
			//断言
			compareCars(GlobalDataStore.vehicle_phoneuser);
			action.waitForRep(10);
			try {
				if(driver.findElementByName("请登录")!=null){
					System.out.println("未登录");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("用户已登录");
			}
		}
		
		public void refresh() throws InterruptedException{
			//断言
			compareCars(GlobalDataStore.vehicle_phoneuser);
			Thread.sleep(5000);
			action.waitForRep(5);
			try {
				if(driver.findElementById(newMess)!=null){
					System.out.println("有新消息提示");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("无新消息提示");
			}
			action.waitForRep(5);
			try {
				if(driver.findElementByName("请登录")!=null){
					System.out.println("未登录");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("用户已登录");
			}
			action.click(By.id(refresh));
			Thread.sleep(5000);
			action.waitForRep(5);
			try {
				if(driver.findElementById(newMess)!=null){
					System.out.println("有新消息提示");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("无新消息提示");
			}
			action.waitForRep(5);
			try {
				if(driver.findElementByName("请登录")!=null){
					System.out.println("未登录");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("用户已登录");
			}
		}
		
		
		public void onroadStatus() throws InterruptedException{
			if(driver.findElementById("cn.buding.martin:id/onroad_title").getText().equals("今日行驶")){
				System.out.println("在路上已开启");
				System.out.println("今日行驶："+driver.findElementById("cn.buding.martin:id/text_onroad_dist").getText()+"km");
			}else if(driver.findElementById("cn.buding.martin:id/onroad_title").getText().equals("在路上")){
				System.out.println("在路上未开启");
			}
		}
		
		public void openOnroad() throws InterruptedException{
			driver.findElementById("cn.buding.martin:id/onroad_title").click();
			Thread.sleep(3000);
			action.swipeUp();
			Thread.sleep(1000);
			action.click(By.id("cn.buding.martin:id/ic_starter"));
			action.click(By.id("cn.buding.martin:id/image_onroad_start"));
			Thread.sleep(1000);
			action.constrainClick(2);
			Thread.sleep(1000);
			action.back();
		}
		
		public void openOnroadAgain() throws InterruptedException{
			driver.findElementById("cn.buding.martin:id/onroad_title").click();
			Thread.sleep(3000);
			action.click(By.id("cn.buding.martin:id/image_onroad_start"));
			Thread.sleep(1000);
			action.back();
		}
		
		public void shutdownOnroad(){
			driver.findElementById("cn.buding.martin:id/onroad_title").click();
			action.click(By.id("cn.buding.martin:id/setting"));
			action.click(By.id("cn.buding.martin:id/power"));
			//点击确定
			action.click(By.id("android:id/button1"));
			action.click(By.id("cn.buding.martin:id/back"));
		}
	
       public void entryLifePrint(String st){
    	   action.click(By.name("微生活"));
   		//driver.scrollTo("微生活").click();
   		//获得频道title列表
   		List<WebElement> lifeChannel =driver.findElementsById("cn.buding.martin:id/title");
   		for(int i=0;i<lifeChannel.size();i++){
   			System.out.println(lifeChannel.get(i).getText());
   		}
   		//截图保存
   		action.prtsc(st);
   		action.back();
       }
	   
	
	
	

}
