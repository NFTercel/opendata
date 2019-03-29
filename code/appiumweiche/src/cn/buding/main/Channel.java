package cn.buding.main;

import static org.junit.Assert.assertEquals;
import io.appium.java_client.NetworkConnectionSetting;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.junit.runners.MethodSorters;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager.WifiLock;
import cn.buding.lib.AppiumBase;
import cn.buding.pages.HomePage;
import cn.buding.variable.GlobalDataStore;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Channel extends AppiumBase{
	
	/*
	 * android采用两种方式定位：1.gps，2网络
	 * 如果拿到定位城市安装后就不显示选择城市列表
	 */
	
	@Test
	public void test001entryHome() throws Exception{
        vgp.entryHome();
	}
	
	/*登陆手机账号查看车辆信息
	 * 
	 */
	@Test
	public void test002entryPhoneAcount() throws InterruptedException{
        try {
			hp.login();
			sp.EntryAccount();
			//断言
			hp.compareCars(GlobalDataStore.vehicle_phoneuser);
			//退出登录
			hp.login();
			sp.LogOut();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			driver.startActivity("cn.buding.martin", ".activity.SplashActivity");
			assertEquals(1, 2);
		}	
	}
	
	/*登录绑定账号查看车辆信息
	 * 
	 * 
	 */
	@Test
	public void test003entryBindingAccount() throws InterruptedException{
		try {
			hp.login();
			sp.EntryBindingAccount();
			//断言
			hp.compareCars(GlobalDataStore.vehicle_bindinguser);
			//退出登录
			hp.login();
			sp.LogOut();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			driver.startActivity("cn.buding.martin", ".activity.SplashActivity");
			assertEquals(1, 2);
		}
	}
	
	/*添加车辆
	 * 
	 */
	@Test
	public void test004AddVehicle() throws InterruptedException{
		try {
			action.click(By.id(HomePage.add_vehicle));
			action.waitForRep(10);
			action.inputDataById(ac.LicencePlatenum, "N1S273");
			action.inputDataById(ac.enginenum, "7706690");
			action.click(By.id(ac.save));
			action.click(By.id(ac.SayLater));
			List<WebElement> unhandled;
			action.waitForRep(60);
			try {
				if(driver.findElementById("cn.buding.martin:id/handled")!=null){
					unhandled = driver.findElementsById("cn.buding.martin:id/handled");
					System.out.println("未处理违章："+unhandled.size());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("无未处理违章");
			}
			action.back();
			action.waitForRep(30);
			action.click(By.name("京N1S273"));
			action.click(By.id("cn.buding.martin:id/edit"));	
			action.waitForRep(60);
			action.click(By.id(ac.deleteCar));	
			action.click(By.id(ac.confirm));
			System.out.println("*************************");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			driver.startActivity("cn.buding.martin", ".activity.SplashActivity");
			assertEquals(1, 2);
		}
	}

	/*life页面验证
	 * 
	 */
	@Test
	public void test005entryLife(){
		try {
			action.click(By.name("微生活"));
			//driver.scrollTo("微车life").click();
			//获得频道title列表
			List<WebElement> lifeChannel =driver.findElementsById("cn.buding.martin:id/title");
			for(int i=0;i<lifeChannel.size();i++){
				System.out.println(lifeChannel.get(i).getText());
			}
			//截图保存
			action.prtsc("channelscreen");
			action.back();
			action.click(By.name("微生活"));
			List<WebElement> lifeChannel1 =driver.findElementsById("cn.buding.martin:id/title");
			for(int i=0;i<lifeChannel1.size();i++){
				System.out.println(lifeChannel1.get(i).getText());
			}
			action.prtsc("channellife");
			 //比较截图和原图图片，相似度大于0.7返回true
			 System.out.println(action.sameAs("d:\\caseOutput\\channelscreen.png", "d:\\caseOutput\\channellife.png",0.75));
			 action.back();
			 System.out.println("*************************");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			driver.startActivity("cn.buding.martin", ".activity.SplashActivity");
			assertEquals(1, 2);
		}
	}
	
	/*贴条地图页面验证
	 * 
	 */
	@Test
	public void test006tietiaoMap() throws InterruptedException{
		try {
			action.click(By.name("贴条地图"));
			//判断是否有 WEBVIEW
			// action.isWebviewornot();
			Thread.sleep(3000);
			action.prtsc("channeltietiao原图");
			//action.zoom();
			//action.pinch();
			action.back();
			action.click(By.name("贴条地图"));
			Thread.sleep(3000);
			action.prtsc("channeltietiao比较图");
			System.out.println(action.sameAs("D:\\caseOutput\\channeltietiao比较图.png", "D:\\caseOutput\\channeltietiao原图.png", 0.75));
			//回到主页
			action.back();
			System.out.println("*************************");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			driver.startActivity("cn.buding.martin", ".activity.SplashActivity");
			assertEquals(1, 2);
		}
	}
	
	//删除appium安装的apk
	@Test
	public void test007deleteApp(){
		driver.removeApp("io.appium.unlock");
		driver.removeApp("io.appium.settings");
		driver.removeApp("io.appium.android.ime");
		
	}
	
	
	
	@AfterClass
	public  static void tearDown() throws Exception {
		driver.quit();
	}
	

}
