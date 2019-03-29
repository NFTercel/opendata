package cn.buding.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import cn.buding.lib.Action;

public class LifePage {


public AndroidDriver driver;
public Action action;
	
	public LifePage(AndroidDriver driver, Action action){
		this.driver = driver;
		this.action = action;

	}
	//服务说明
	public static String serviceInstr = "cn.buding.martin:id/help";
	
	//进入违章缴费
	public void entryVioPay() throws InterruptedException{
		action.click(By.name("微生活"));
		Thread.sleep(3000);
		action.clickweizhangInlife();
	}

	//无车辆进入违章缴费判断
	public void entryVioNoCar(){
		try {
			System.out.println("页面提示："+driver.findElementByName("请您先添加车辆信息哦~~").getText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("无页面提示");
		}
	}
	
	//判断是否有可缴费的违章
	public void ifPayValuebale(){
		try {
			List <WebElement> jiaofei = driver.findElementsById("cn.buding.martin:id/pay");
			System.out.println("缴费"+jiaofei.size());
			jiaofei.get(0).click();
			Thread.sleep(2000);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			List <WebElement> zanwu = driver.findElementsById("cn.buding.martin:id/no_violation");
			System.out.println("暂无违章"+zanwu.size());
			zanwu.get(0).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			List <WebElement> notsupp= driver.findElementsByName("暂不支持");
			System.out.println("暂不支持"+notsupp.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}
}