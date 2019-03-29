package cn.buding.pages;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import cn.buding.lib.Action;
import cn.buding.variable.GlobalDataStore;

public class ViolateDetailPage {
	public AndroidDriver driver;
	public Action action;
	
	public ViolateDetailPage(AndroidDriver driver,Action action){
		this.driver = driver;
		this.action = action;
	}
	//违章缴费按钮
	public static String viopay = "cn.buding.martin:id/start_pay";
	//首次进入输入信息页 "我知道了"按钮
	public static String ikown = "cn.buding.martin:id/know";
	//违章缴费输入信息页号码
	public static String phone = "cn.buding.martin:id/phone_num";
	//同意协议
	public static String agreeCb = "cn.buding.martin:id/cb_agreement";
	//服务使用协议
	public static String seviceCb = "cn.buding.martin:id/service_agreement";
	//服务说明
	public static String seviceHelp = "cn.buding.martin:id/service_help";
	//发票
	public static String invoice_check = "cn.buding.martin:id/invoice_check";
	//收件人
	//手机号码
	//详细地址
	public static String receiver = "cn.buding.martin:id/receiver_name";
	//public static String phone = "cn.buding.martin:id/phone_num";
	public static String address = "cn.buding.martin:id/address";
	//保存
	public static String save = "cn.buding.martin:id/save";
	//发票修改
	public static String changeInvoice = "cn.buding.martin:id/invoice_container";
	//公司
	public static String rb_company = "cn.buding.martin:id/rb_company";
	//公司名
	public static String companyName = "cn.buding.martin:id/company_name";
	
	
	//点击违章缴费
	public void clickViopay() throws InterruptedException{
		while(driver.findElementById(viopay).isEnabled()!=true){
			  Thread.sleep(3000);
			}
			action.click(By.id(viopay));
	}
	
	//判断是否是加油页面
	public void ifOilPage(){
		if(driver.currentActivity().equals(".activity.life.PaymentUnavailableActivity")){
			System.out.println("当前是加油页面");
		}else{
			System.out.println("当前页面错误");
		}	
	}
}
