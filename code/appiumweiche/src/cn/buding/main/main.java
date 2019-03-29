package cn.buding.main;

import static org.junit.Assert.assertEquals;
import io.appium.java_client.NetworkConnectionSetting;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.junit.runners.MethodSorters;

import cn.buding.lib.AppiumBase;
import cn.buding.pages.HomePage;
import cn.buding.variable.GlobalDataStore;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class main extends AppiumBase {

	/*
	 * android采用两种方式定位：1.gps，2网络 如果拿到定位城市安装后就不显示选择城市列表
	 */

	@Test
	public void test001entryHome() throws Exception {
		System.out.println("**********test001**********");
		try {
			vgp.entryHome();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 未登录情况下首页用户头像上“请登录”文字
	 */

	@Test
	public void test002loginPlease() throws InterruptedException {
		System.out.println("**********test002**********");
		try {
			hp.loginPlease();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 登录账户后验证首页头像上方文字消失
	 */

	@Test
	public void test003loginO() throws InterruptedException {
		System.out.println("**********test003**********");
		// 登陆手机账号
		try {
			hp.login();
			sp.EntryAccount();
			hp.noLoginPlease();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 刷新查看用户数据
	 */

	@Test
	public void test004refresh() throws InterruptedException {
		System.out.println("**********test004**********");
		try {
			hp.refresh();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 查看天气数据、限行数据
	 */

	@Test
	public void test005weather() throws InterruptedException {
		System.out.println("**********test005**********");
		try {
			action.click(By.id(hp.weather));
			System.out.println(driver.findElementById(hp.weatherTitle)
					.getText());
			System.out
					.println("天气:"
							+ driver.findElementById(hp.weatherDetail)
									.getText() + " 温度:"
							+ driver.findElementById(hp.temperature).getText());
			action.waitForRep(5);
			action.scrollElementToLeft(driver.findElementById(hp.weatherPaper));
			System.out.println("今日限行"
					+ driver.findElementById(hp.limNumber).getText());
			action.constrainClick(4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 
	 * 登陆账号查看违章列表 查看首页车辆widget
	 */
	@Test
	public void test006vehicleViolation() throws InterruptedException {
		System.out.println("**********test006**********");
		try {
			System.out.println("车牌号："
					+ driver.findElementById(hp.vehicle_number).getText());
			System.out.println("首页新违章："
					+ driver.findElementById(hp.newVio).getText());
			System.out.println("首页未处理违章："
					+ driver.findElementById(hp.unhandledVio).getText());
			hp.login();
			sp.LogOut();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 查看在路上状态
	 */

	@Test
	public void test007onroadStatus() throws InterruptedException {
		System.out.println("**********test007**********");
		try {
			hp.onroadStatus();
			// 打开onroad
			hp.openOnroad();
			hp.onroadStatus();
			// 关闭onroad
			hp.shutdownOnroad();
			hp.onroadStatus();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 断网下切换城市查看天气
	 */

	// @Test
	public void test008switchcityNodata() throws InterruptedException {
		System.out.println("**********test008**********");
		// 断网
		try {
			NetworkConnectionSetting network = new NetworkConnectionSetting(
					false, false, false);
			driver.setNetworkConnection(network);
			System.out.println(action.checkNet());
			hp.login();
			sp.citySwitch("上海(上海)");
			action.waitForRep(5);
			try {
				if (driver.findElementById("cn.buding.martin:id/no_data") != null) {
					System.out.println("无天气信息数据");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("有天气信息");
			}
			hp.login();
			sp.citySwitch("北京(北京)");
			// 恢复wifi
			NetworkConnectionSetting network1 = new NetworkConnectionSetting(
					false, true, true);
			driver.setNetworkConnection(network1);
			// 等待五秒wifi开启
			Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 添加重复车辆
	 */

	@Test
	public void test009addRightCar() throws InterruptedException {
		System.out.println("**********test009**********");
		try {
			ac.addexistVehicle();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 编辑车辆增加备注
	 */

	@Test
	public void test010editVehicle() throws InterruptedException {
		System.out.println("**********test010**********");
		try {
			ac.editVehicleBeizhu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 编辑车辆添加车型
	 */

	@Test
	public void test011addVehicleType() throws InterruptedException {
		System.out.println("**********test011**********");
		try {
			ac.addVehicleType();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 发动机号说明
	 */

	@Test
	public void test012help() throws InterruptedException {
		System.out.println("**********test012**********");
		try {
			ac.engineHelp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 修改查询城市
	 */

	@Test
	public void test013searchCity() throws InterruptedException {
		System.out.println("**********test013**********");
		try {
			ac.changesearchCity();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 搜索选择城市
	 */

	@Test
	public void test014searchToCity() throws InterruptedException {
		System.out.println("**********test014**********");
		try {
			ac.searchtoChoseCity();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 特殊字符匹配
	 */
	@Test
	public void test015specialChar() throws InterruptedException {
		System.out.println("**********test015**********");
		try {
			action.click(By.id(HomePage.add_vehicle));
			action.click(By.id(ac.SelectCity));
			action.click(By.name("北京(北京)"));
			// 搜索输入框
			action.inputDataById("cn.buding.martin:id/et_search", "#");
			assertEquals("没有该城市",
					driver.findElementById("cn.buding.martin:id/tv_label")
							.getText());
			action.back();
			Thread.sleep(1000);
			action.back();
			// 不保存车辆信息返回
			action.click(By.id("android:id/button2"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 匹配字符
	 */
	@Test
	public void test016matchChar() {
		System.out.println("**********test016**********");
		try {
			action.click(By.id(HomePage.add_vehicle));
			action.click(By.id(ac.SelectCity));
			action.click(By.name("北京(北京)"));
			// 搜索输入框
			action.inputDataById("cn.buding.martin:id/et_search", "北");
			if (driver.findElementByName("北京(北京)") != null) {
				assertEquals(1, 1);
			} else {
				assertEquals(1, 2);
			}
			driver.findElementById("cn.buding.martin:id/et_search").clear();
			action.inputDataById("cn.buding.martin:id/et_search", "b");
			if (driver.findElementByName("北京(北京)") != null) {
				assertEquals(1, 1);
			} else {
				assertEquals(1, 2);
			}
			driver.findElementById("cn.buding.martin:id/et_search").clear();
			action.inputDataById("cn.buding.martin:id/et_search", "bj");
			if (driver.findElementByName("北京(北京)") != null) {
				assertEquals(1, 1);
			} else {
				assertEquals(1, 2);
			}
			driver.findElementById("cn.buding.martin:id/et_search").clear();
			action.inputDataById("cn.buding.martin:id/et_search", "beijing");
			if (driver.findElementByName("北京(北京)") != null) {
				assertEquals(1, 1);
			} else {
				assertEquals(1, 2);
			}
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			// 不保存车辆信息返回
			action.click(By.id("android:id/button2"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 登陆手机账号验证车辆信息
	 */

	@Test
	public void test017entryPhoneAccount() throws InterruptedException {
		System.out.println("**********test017**********");
		try {
			hp.login();
			sp.EntryAccount();
			// 断言
			hp.compareCars(GlobalDataStore.vehicle_phoneuser);
			// 退出登录
			hp.login();
			sp.LogOut();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 登录绑定账号验证车辆信息
	 */

	@Test
	public void test018entryBindingAccount() throws InterruptedException {
		System.out.println("**********test018**********");
		try {
			hp.login();
			sp.EntryBindingAccount();
			// 断言
			hp.compareCars(GlobalDataStore.vehicle_bindinguser);
			// 退出登录
			hp.login();
			sp.LogOut();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 添加两个查询城市
	 */
	@Test
	public void test019addTwoCity() {
		System.out.println("**********test019**********");
		try {
			action.click(By.id(HomePage.add_vehicle));
			action.waitForRep(10);
			action.click(By.id(ac.SelectCity));
			action.click(By.name("上海(上海)"));
			action.back();
			assertEquals(
					"北京  上海  ",
					driver.findElement(
							By.id("cn.buding.martin:id/city_container"))
							.getText());
			action.back();
			action.click(By.id("android:id/button2"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 找回密码
	 */

	// @Test
	public void test020findPassword() throws InterruptedException {
		System.out.println("**********test020**********");
		try {
			hp.login();
			// 存储验证码
			String str = sp.readVerifyCode();
			// 通过验证码登录
			hp.login();
			sp.entryBySMS(str);
			// 断言
			hp.compareCars(GlobalDataStore.vehicle_bindinguser);
			// 修改密码 原因 ：保持账户密码不变（手机号资源）
			hp.login();
			sp.resetPasscode(str, "qwerty");
			sp.LogOut();
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}

	}

	/*
	 * 进入life检查频道 截图在d盘caseOutput
	 */

	@Test
	public void test021entryLife() throws InterruptedException {
		System.out.println("**********test021**********");
		try {
			hp.entryLifePrint("screen");
			hp.entryLifePrint("life");
			// 比较截图和原图图片，相似度大于0.7返回true
			System.out.println(action.sameAs("d:\\caseOutput\\life.png",
					"d:\\caseOutput\\screen.png", 0.75));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 注册后车辆同步逻辑验证
	 */

	// @Test
	public void test022registration() throws InterruptedException {
		System.out.println("**********test022**********");
		try {
			String newVehicle = ac.addRandomCar();
			hp.login();
			sp.RegisterAccount();
			// 注册后验证首页车辆同步
			List<WebElement> carInfo = driver
					.findElementsById(hp.vehicle_number);
			// 断言
			assertEquals(carInfo.get(0).getText(), newVehicle);
			System.out.println("车辆同步到新注册账号");
			hp.login();
			sp.LogOut();
			Thread.sleep(5000);
			String noVehicle = null;
			// 断言
			try {
				noVehicle = driver.findElementsByName("欢迎使用微车违章查询").get(0)
						.getText();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			assertEquals("欢迎使用微车违章查询", noVehicle);
			System.out.println("退出登录后无车辆，逻辑正确");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}

	}

	/*
	 * 贴条地图页面测试
	 */

	@Test
	public void test023tietiaoMap() throws InterruptedException {
		System.out.println("**********test023**********");
		try {
			action.click(By.name("贴条地图"));
			// 判断是否有 WEBVIEW
			// action.isWebviewornot();
			Thread.sleep(3000);
			action.prtsc("tietiao原图");
			// action.zoom();
			// action.pinch();
			action.back();
			action.click(By.name("贴条地图"));
			Thread.sleep(3000);
			action.prtsc("tietiao比较图");
			System.out.println(action.sameAs("D:\\caseOutput\\tietiao比较图.png",
					"D:\\caseOutput\\tietiao原图.png", 0.75));
			// 回到主页
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 账户交换
	 */

	@Test
	public void test024exchangeAccount() throws InterruptedException {
		System.out.println("**********test024**********");
		try {
			hp.login();
			sp.EntryAccount();
			hp.login();
			// 修改密码
			sp.resetPasscode("wangzhen", "qwerty");
			// 此处有一个4.0.1有一个bug,重置密码后需要两次返回键返回
			Thread.sleep(2000);
			action.back();
			hp.compareCars(GlobalDataStore.vehicle_phoneuser);
			// 密码改回，退出登录
			hp.login();
			sp.resetPasscode("qwerty", "wangzhen");
			sp.LogOut();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}

	}

	/*
	 * 
	 * 切换城市验证
	 */

	@Test
	public void test025citySwitch() throws InterruptedException {
		System.out.println("**********test025**********");
		try {
			hp.login();
			sp.citySwitch("上海(上海)");
			hp.verifyCurrentCity("上海");
			hp.login();
			sp.citySwitch("北京(北京)");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * "关于我们"中版本号验证
	 */

	@Test
	public void test026aboutUs() throws InterruptedException {
		System.out.println("**********test026**********");
		try {
			hp.login();
			sp.checkAboutUs();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 
	 * 在路上每日通知状态 默认GPS已打开 操作完成关闭onroad
	 */

	@Test
	public void test027onroadInform() throws InterruptedException {
		System.out.println("**********test027**********");
		try {
			hp.login();
			sp.onroadDialyPushCheck();
			hp.shutdownOnroad();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 反馈
	 */
	@Test
	public void test028feedback() throws InterruptedException {
		System.out.println("**********test028**********");
		try {
			hp.login();
			sp.giveFeedback();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 车辆违章详情
	 */
	@Test
	public void test029violationDetail() throws InterruptedException {
		System.out.println("**********test029**********");
		try {
			hp.login();
			sp.EntryAccount();
			hp.compareCars(GlobalDataStore.vehicle_phoneuser);
			Thread.sleep(5000);
			action.click(By.id(hp.vehicle_number));
			action.waitForRep(60);
			ac.voilationDetail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}

	}

	/*
	 * 违章缴费输入信息页
	 */
	@Test
	public void test030violationPay() throws InterruptedException {
		System.out.println("**********test030**********");
		try {
			hp.compareCars(GlobalDataStore.vehicle_phoneuser);
			Thread.sleep(5000);
			action.click(By.id(hp.vehicle_number));
			vdp.clickViopay();
			// 首次进入违章缴费输入信息页点击我知道了按钮
			action.click(By.id(vdp.ikown));
			// 返回违章列表页
			action.back();
			Thread.sleep(500);
			// 返回主页
			action.back();
			hp.login();
			sp.LogOut();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}

	}

	/*
	 * 不支持城市点击违章缴费进入加油页面
	 */
	@Test
	public void test031cityNosupport() throws InterruptedException {
		System.out.println("**********test031**********");
		try {
			hp.login();
			sp.EntryBindingAccount();
			hp.compareCars(GlobalDataStore.vehicle_bindinguser);
			// bug
			Thread.sleep(5000);
			action.click(By.id(hp.vehicle_number));
			// action.click(By.id(hp.vehicle_number));
			vdp.clickViopay();
			Thread.sleep(6000);
			vdp.ifOilPage();
			action.back();
			Thread.sleep(500);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 加油页面loading过程中按返回键
	 */
	@Test
	public void test032cityNosupportBack() throws InterruptedException {
		System.out.println("**********test032**********");
		try {
			action.click(By.id(hp.vehicle_number));
			// action.click(By.id(hp.vehicle_number));
			vdp.clickViopay();
			driver.sendKeyEvent(4);
			Thread.sleep(500);
			action.back();
			hp.login();
			sp.LogOut();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * life进入违章缴费(无车辆)
	 */
	@Test
	public void test033lifeChannelPay() throws InterruptedException {
		System.out.println("**********test033**********");
		try {
			lp.entryVioPay();
			lp.entryVioNoCar();
			// 进入服务说明页
			action.click(By.id(lp.serviceInstr));
			Thread.sleep(5000);
			assertEquals(driver.currentActivity(), ".activity.WebViewActivity");
			action.back();
			Thread.sleep(500);
			action.back();
			Thread.sleep(500);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}

	}

	/*
	 * 已添加可缴费车辆从life进入违章缴费频道
	 */
	@Test
	public void test034lifeChannelPay1() throws InterruptedException {
		System.out.println("**********test034**********");
		try {
			hp.login();
			sp.EntryAccount();
			// 断言
			hp.compareCars(GlobalDataStore.vehicle_phoneuser);
			lp.entryVioPay();
			action.waitForRep(10);
			lp.ifPayValuebale();
			// 进入服务说明页
			action.click(By.id(lp.serviceInstr));
			Thread.sleep(5000);
			assertEquals(driver.currentActivity(), ".activity.WebViewActivity");
			Thread.sleep(1000);
			action.back();
			Thread.sleep(500);
			action.back();
			Thread.sleep(500);
			action.back();
			hp.login();
			sp.LogOut();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 已添加暂不支持城市车辆从life进入违章缴费
	 */
	@Test
	public void test035lifeChannelPay2() throws InterruptedException {
		System.out.println("**********test035**********");
		try {
			hp.login();
			sp.EntryBindingAccount();
			hp.compareCars(GlobalDataStore.vehicle_bindinguser);
			lp.entryVioPay();
			action.waitForRep(3);
			lp.ifPayValuebale();
			// 进入服务说明页
			action.click(By.id(lp.serviceInstr));
			Thread.sleep(5000);
			assertEquals(driver.currentActivity(), ".activity.WebViewActivity");
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			action.back();
			hp.login();
			sp.LogOut();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 手机账户登陆后违章缴费输入信息页自动填入手机号
	 */
	@Test
	public void test036autoPhoneInput() throws InterruptedException {
		System.out.println("**********test036**********");
		try {
			hp.login();
			sp.EntryAccount();
			hp.compareCars(GlobalDataStore.vehicle_phoneuser);
			Thread.sleep(10000);
			action.click(By.id(hp.vehicle_number));
			vdp.clickViopay();
			assertEquals(driver.findElementById(vdp.phone).getText(),
					"13683116962");
			action.back();
			Thread.sleep(500);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 断网下进入违章缴费输入信息页
	 */
	// @Test
	public void test037nodataIntoInformation() throws InterruptedException {
		System.out.println("**********test037**********");
		try {
			hp.login();
			sp.EntryAccount();
			hp.compareCars(GlobalDataStore.vehicle_phoneuser);
			Thread.sleep(5000);
			action.click(By.id(hp.vehicle_number));
			while (driver.findElementById("cn.buding.martin:id/start_pay")
					.isEnabled() != true) {
				Thread.sleep(3000);
			}
			driver.setNetworkConnection(new NetworkConnectionSetting(false,
					false, false));
			Thread.sleep(5000);
			action.click(By.id("cn.buding.martin:id/start_pay"));
			Thread.sleep(3000);
			try {
				if (driver
						.findElementById("cn.buding.martin:id/net_error_container") != null) {
					System.out.println("无网下显示获取数据失败");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("页面显示异常");
			}
			driver.setNetworkConnection(new NetworkConnectionSetting(false,
					true, true));
			Thread.sleep(5000);
			action.back();
			Thread.sleep(500);
			action.back();
			hp.login();
			sp.LogOut();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 服务使用协议默认勾选
	 */
	@Test
	public void test038seviceProtocol() throws InterruptedException {
		System.out.println("**********test038**********");
		try {
			hp.compareCars(GlobalDataStore.vehicle_phoneuser);
			Thread.sleep(5000);
			action.click(By.id(hp.vehicle_number));
			vdp.clickViopay();
			assertEquals("true", driver.findElementById(vdp.agreeCb)
					.getAttribute("checked").toString());
			action.click(By.id(vdp.agreeCb));
			action.click(By.id(vdp.seviceCb));
			Thread.sleep(6000);
			action.back();
			Thread.sleep(500);
			assertEquals("true", driver.findElementById(vdp.agreeCb)
					.getAttribute("checked").toString());
			action.back();
			Thread.sleep(500);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}

	}

	/*
	 * 输入信息页没有违章
	 */
	@Test
	public void test039noviolationtopay() throws InterruptedException {
		System.out.println("**********test039**********");
		try {
			hp.compareCars(GlobalDataStore.vehicle_phoneuser);
			Thread.sleep(5000);
			action.click(By.id(hp.vehicle_number));
			vdp.clickViopay();
			List<WebElement> nopay = driver
					.findElementsById("cn.buding.martin:id/empty_text");
			assertEquals(nopay.get(0).getText(), "没有可缴费的违章");
			// assertEquals(nopay.get(1).getText(), "没有不支持缴费的违章");
			action.back();
			Thread.sleep(500);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 进入违章缴费输入信息页，点击服务说明
	 */
	@Test
	public void test040payExplanation() throws InterruptedException {
		System.out.println("**********test040**********");
		try {
			hp.compareCars(GlobalDataStore.vehicle_phoneuser);
			Thread.sleep(5000);
			action.click(By.id(hp.vehicle_number));
			Thread.sleep(5000);
			// 点击违章缴费
			vdp.clickViopay();
			// 点击服务说明
			action.click(By.id(vdp.seviceHelp));
			Thread.sleep(5000);
			action.back();
			Thread.sleep(500);
			action.back();
			Thread.sleep(500);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}

	}

	// /*
	// * 无网进入服务说明 非首次进入有缓存
	// */
	// @Test
	// public void test038payExplanationNoData() throws InterruptedException{
	// hp.login();
	// sp.EntryAccount();
	// hp.compareCars(GlobalDataStore.vehicle_phoneuser);
	// action.click(By.id(hp.vehicle_number));
	// Thread.sleep(5000);
	// //点击违章缴费
	// action.click(By.id("cn.buding.martin:id/start_pay"));
	// //点击服务说明
	// driver.setNetworkConnection(new NetworkConnectionSetting(false, false,
	// false));
	// Thread.sleep(5000);
	// action.click(By.id("cn.buding.martin:id/service_help"));
	//
	//
	// }

	/*
	 * 填写个人发票信息
	 */
	@Test
	public void test041receiptPrivate() throws InterruptedException {
		System.out.println("**********test041**********");
		try {
			hp.compareCars(GlobalDataStore.vehicle_phoneuser);
			Thread.sleep(5000);
			action.click(By.id(hp.vehicle_number));
			Thread.sleep(5000);
			// 点击违章缴费
			vdp.clickViopay();
			// 打开发票开关
			action.click(By.id(vdp.invoice_check));
			action.inputDataById(vdp.receiver, "微车");
			action.inputDataById(vdp.phone, "13426006327");
			action.inputDataById(vdp.address, "布丁移动");
			action.click(By.id(vdp.save));
			action.click(By.id(vdp.changeInvoice));
			assertEquals("微车", driver.findElementById(vdp.receiver).getText());
			assertEquals("13426006327", driver.findElementById(vdp.phone)
					.getText());
			assertEquals("布丁移动", driver.findElementById(vdp.address).getText());
			action.back();
			Thread.sleep(500);
			action.back();
			Thread.sleep(500);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 填写个人发票信息少填一项
	 */
	@Test
	public void test042receiptPrivateMiss() throws InterruptedException {
		System.out.println("**********test042**********");
		try {
			hp.compareCars(GlobalDataStore.vehicle_phoneuser);
			Thread.sleep(5000);
			action.click(By.id(hp.vehicle_number));
			Thread.sleep(5000);
			// 点击违章缴费
			vdp.clickViopay();
			// 打开发票开关
			action.click(By.id(vdp.invoice_check));
			action.inputDataById(vdp.receiver, "微车");
			action.inputDataById(vdp.phone, "13426006327");
			// action.inputDataById(vdp.address, "布丁移动");
			action.click(By.id(vdp.save));
			// action.click(By.id(vdp.changeInvoice));
			// assertEquals("微车",
			// driver.findElementById(vdp.receiver).getText());
			// assertEquals("13426006327",
			// driver.findElementById(vdp.phone).getText());
			// assertEquals("布丁移动",
			// driver.findElementById(vdp.address).getText());
			action.back();
			// 发票不保存
			action.click(By.id("android:id/button1"));
			action.back();
			Thread.sleep(500);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 填写公司发票信息
	 */
	@Test
	public void test043receiptEnterprise() throws InterruptedException {
		System.out.println("**********test043**********");
		try {
			hp.compareCars(GlobalDataStore.vehicle_phoneuser);
			Thread.sleep(5000);
			action.click(By.id(hp.vehicle_number));
			Thread.sleep(5000);
			// 点击违章缴费
			vdp.clickViopay();
			// 打开发票开关
			action.click(By.id(vdp.invoice_check));
			action.click(By.id(vdp.rb_company));
			action.inputDataById(vdp.companyName, "布丁");
			action.inputDataById(vdp.receiver, "微车");
			action.inputDataById(vdp.phone, "13426006327");
			action.inputDataById(vdp.address, "布丁移动");
			action.click(By.id(vdp.save));
			action.click(By.id(vdp.changeInvoice));
			assertEquals("布丁", driver.findElementById(vdp.companyName)
					.getText());
			assertEquals("微车", driver.findElementById(vdp.receiver).getText());
			assertEquals("13426006327", driver.findElementById(vdp.phone)
					.getText());
			assertEquals("布丁移动", driver.findElementById(vdp.address).getText());
			action.back();
			Thread.sleep(500);
			action.back();
			Thread.sleep(500);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 填写公司发票信息少填一项
	 */
	@Test
	public void test044receiptEnterpriseMiss() throws InterruptedException {
		System.out.println("**********test044**********");
		try {
			hp.compareCars(GlobalDataStore.vehicle_phoneuser);
			Thread.sleep(5000);
			action.click(By.id(hp.vehicle_number));
			Thread.sleep(5000);
			// 点击违章缴费
			vdp.clickViopay();
			// 打开发票开关
			action.click(By.id(vdp.invoice_check));
			action.click(By.id(vdp.rb_company));
			action.inputDataById(vdp.companyName, "布丁");
			action.inputDataById(vdp.receiver, "微车");
			action.inputDataById(vdp.phone, "13426006327");
			// action.inputDataById(vdp.address, "布丁移动");
			action.click(By.id(vdp.save));
			// action.click(By.id(vdp.changeInvoice));
			// assertEquals("布丁",
			// driver.findElementById(vdp.companyName).getText());
			// assertEquals("微车",
			// driver.findElementById(vdp.receiver).getText());
			// assertEquals("13426006327",
			// driver.findElementById(vdp.phone).getText());
			// assertEquals("布丁移动",
			// driver.findElementById(vdp.address).getText());
			action.back();
			// 发票不保存
			action.click(By.id("android:id/button1"));
			action.back();
			Thread.sleep(500);
			action.back();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 修改发票信息
	 */
	@Test
	public void test045receiptChange() throws InterruptedException {
		System.out.println("**********test045**********");
		try {
			hp.compareCars(GlobalDataStore.vehicle_phoneuser);
			Thread.sleep(5000);
			action.click(By.id(hp.vehicle_number));
			Thread.sleep(5000);
			// 点击违章缴费
			vdp.clickViopay();
			// 打开发票开关
			action.click(By.id(vdp.invoice_check));
			action.click(By.id(vdp.rb_company));
			action.inputDataById(vdp.companyName, "布丁");
			action.inputDataById(vdp.receiver, "微车");
			action.inputDataById(vdp.phone, "13426006327");
			action.inputDataById(vdp.address, "布丁移动");
			action.click(By.id(vdp.save));
			action.click(By.id(vdp.changeInvoice));
			driver.findElementById(vdp.companyName).clear();
			action.inputDataById(vdp.companyName, "布丁移动");
			action.click(By.id(vdp.save));
			action.click(By.id(vdp.changeInvoice));
			assertEquals("布丁移动", driver.findElementById(vdp.companyName)
					.getText());
			action.back();
			Thread.sleep(500);
			action.back();
			Thread.sleep(500);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 点返回发票不保存
	 */
	@Test
	public void test046receiptNotSave() throws InterruptedException {
		System.out.println("**********test046**********");
		try {
			hp.compareCars(GlobalDataStore.vehicle_phoneuser);
			Thread.sleep(5000);
			action.click(By.id(hp.vehicle_number));
			Thread.sleep(5000);
			// 点击违章缴费
			vdp.clickViopay();
			// 打开发票开关
			action.click(By.id(vdp.invoice_check));
			action.inputDataById(vdp.receiver, "微车");
			action.inputDataById(vdp.phone, "13426006327");
			action.inputDataById(vdp.address, "布丁移动");
			action.back();
			// 点击返回
			action.click(By.id("android:id/button1"));
			assertEquals("false", driver.findElementById(vdp.invoice_check)
					.getAttribute("checked").toString());
			action.back();
			Thread.sleep(500);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}

	}

	/*
	 * 
	 * 点返回发票保存
	 */
	@Test
	public void test047receiptSave() throws InterruptedException {
		System.out.println("**********test047**********");
		try {
			hp.compareCars(GlobalDataStore.vehicle_phoneuser);
			Thread.sleep(5000);
			action.click(By.id(hp.vehicle_number));
			Thread.sleep(5000);
			// 点击违章缴费
			vdp.clickViopay();
			// 打开发票开关
			action.click(By.id(vdp.invoice_check));
			action.inputDataById(vdp.receiver, "微车");
			action.inputDataById(vdp.phone, "13426006327");
			action.inputDataById(vdp.address, "布丁移动");
			action.back();
			// 点击取消
			action.click(By.id("android:id/button2"));
			assertEquals(".activity.life.InvoiceActivity",
					driver.currentActivity());
			action.back();
			action.click(By.id("android:id/button1"));
			action.back();
			Thread.sleep(500);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 查看我的订单（无订单）
	 */
	@Test
	public void test048myOrder() throws InterruptedException {
		System.out.println("**********test048**********");
		try {
			// 断言
			hp.compareCars(GlobalDataStore.vehicle_phoneuser);
			action.click(By.name("微生活"));
			int x = driver.manage().window().getSize().width;
			int y = driver.manage().window().getSize().height;
			driver.swipe(x / 2, 4 * y / 5, x / 2, y / 5, 500);
			Thread.sleep(2000);
			driver.swipe(x / 2, 4 * y / 5, x / 2, y / 5, 500);
			driver.scrollTo("我的订单").click();
			assertEquals("您还没有订单，快去下单吧~",
					driver.findElementByName("您还没有订单，快去下单吧~").getText());
			action.back();
			Thread.sleep(500);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 违章列表页下拉刷新
	 */
	@Test
	public void test049swipDownviolist() throws InterruptedException {
		System.out.println("**********test049**********");
		try {
			// 断言
			hp.compareCars(GlobalDataStore.vehicle_phoneuser);
			Thread.sleep(5000);
			action.click(By.id(hp.vehicle_number));
			action.waitForRep(60);
			try {
				System.out.println(driver.findElementById(
						"cn.buding.martin:id/tv_status").getText());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
			}
			// 下拉刷新
			action.swipeDownRefresh();
			action.waitForRep(60);
			try {
				System.out.println(driver.findElementById(
						"cn.buding.martin:id/tv_status").getText());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
			}
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 刷新违章列表时进入编辑车辆页面 返回主页
	 */
	@Test
	public void test050stopRefresh() throws InterruptedException {
		System.out.println("**********test050**********");
		try {
			// 断言
			hp.compareCars(GlobalDataStore.vehicle_phoneuser);
			Thread.sleep(5000);
			action.click(By.id(hp.vehicle_number));
			action.click(By.id("cn.buding.martin:id/edit"));
			action.back();
			action.swipeDownRefresh();
			action.back();
			hp.login();
			sp.LogOut();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 绑定账户登陆后违章缴费输入信息页自动填入手机号
	 */
	// @Test
	public void test051autoPhoneInput() throws InterruptedException {
		System.out.println("**********test051**********");
		try {
			hp.login();
			sp.EntryBindingAccount();
			hp.compareCars(GlobalDataStore.vehicle_bindinguser);
			Thread.sleep(10000);
			action.click(By.id(hp.vehicle_number));
			vdp.clickViopay();
			assertEquals(driver.findElementById(vdp.phone).getText(),
					"13810276832");
			action.back();
			Thread.sleep(500);
			action.back();
			hp.login();
			sp.LogOut();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 登陆微信账号校验车辆,微信已登录状况下 yxgbuding 此帐号不可修改
	 */

	@Test
	public void test052wechatAccount() throws InterruptedException {
		System.out.println("**********test052**********");
		try {
			hp.login();
			sp.EntryWechatAccount();
			// 断言
			hp.compareCars(GlobalDataStore.vehicle_wechatuser);
			hp.login();
			sp.LogOut();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 个性化背景图片
	 */
	@Test
	public void test053diyBackground() throws InterruptedException {
		System.out.println("**********test053**********");
		try {
			hp.login();
			sp.entryDiyBackAccount();
			// 断言
			Thread.sleep(10000);
			action.prtsc("diyBackground");
			hp.login();
			action.click(By.id("cn.buding.martin:id/customer_check"));
			action.back();
			action.prtsc("defaultBackground");
			System.out.println(action.sameAs(
					"D:\\caseOutput\\diyBackground.png",
					"D:\\caseOutput\\defaultBackground.png", 0.75));
			hp.login();
			action.click(By.id("cn.buding.martin:id/customer_check"));
			sp.LogOut();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 添加车辆页面显示微车闹钟
	 */
	@Test
	public void test054addCarClock() throws InterruptedException {
		System.out.println("**********test054**********");
		try {
			action.click(By.id(HomePage.add_vehicle));
			try {
				action.click(By.id("cn.buding.martin:id/set_alarm"));
				assertEquals("2小时", driver.findElementById("android:id/text1")
						.getText());
				action.click(By.id("cn.buding.martin:id/cancle"));
				assertEquals("行驶证不在身边",
						driver.findElementById("cn.buding.martin:id/set_alarm")
								.getText());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("ERROR没有找到闹钟按钮");
			} finally {
				action.back();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 编辑车辆不显示微车闹钟
	 */
	@Test
	public void test055editCarClock() throws InterruptedException {
		System.out.println("**********test055**********");
		try {
			hp.login();
			sp.EntryAccount();
			hp.compareCars(GlobalDataStore.vehicle_phoneuser);
			Thread.sleep(5000);
			action.click(By.id("cn.buding.martin:id/empty_car"));
			WebElement we = null;
			try {
				we = driver.findElementById("cn.buding.martin:id/set_alarm");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("编辑车辆没有闹钟");
				assertEquals(null, we);
			}
			action.back();
			hp.login();
			sp.LogOut();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 点击黑色蒙版取消闹钟设置
	 */

	@Test
	public void test056clickBlacktoCancel() throws InterruptedException {
		System.out.println("**********test056**********");
		try {
			action.click(By.id(HomePage.add_vehicle));
			try {
				action.click(By.id("cn.buding.martin:id/set_alarm"));
				action.constrainClick(10);
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}

	}

	/*
	 * 设置闹钟成功等待提示框消失
	 */
	@Test
	public void test057setClockSuccess() throws InterruptedException {
		System.out.println("**********test057**********");
		try {
			action.click(By.id(HomePage.add_vehicle));
			try {
				action.click(By.id("cn.buding.martin:id/set_alarm"));
				action.click(By.id("android:id/text1"));
				action.click(By.name("10分钟"));
				action.click(By.name("确定"));
				Thread.sleep(4000);
				assertEquals("重置闹钟",
						driver.findElementById("cn.buding.martin:id/set_alarm")
								.getText());
				// action.constrainClick(10);
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}
			action.click(By.id("cn.buding.martin:id/set_alarm"));
			action.click(By.id("cn.buding.martin:id/cancel_alarm"));
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}

	}

	/*
	 * 修改闹钟
	 */
	@Test
	public void test058resetClock() throws InterruptedException {
		System.out.println("**********test058**********");
		try {
			action.click(By.id(HomePage.add_vehicle));
			try {
				action.click(By.id("cn.buding.martin:id/set_alarm"));
				action.click(By.id("android:id/text1"));
				action.click(By.name("10分钟"));
				action.click(By.name("确定"));
				Thread.sleep(4000);
				assertEquals("重置闹钟",
						driver.findElementById("cn.buding.martin:id/set_alarm")
								.getText());
				// action.constrainClick(10);
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}
			action.click(By.id("cn.buding.martin:id/set_alarm"));
			action.click(By.id("cn.buding.martin:id/reset_alarm"));
			action.click(By.id("android:id/text1"));
			action.click(By.name("30分钟"));
			action.click(By.name("确定"));
			Thread.sleep(4000);
			assertEquals("重置闹钟",
					driver.findElementById("cn.buding.martin:id/set_alarm")
							.getText());
			action.click(By.id("cn.buding.martin:id/set_alarm"));
			action.click(By.id("cn.buding.martin:id/cancel_alarm"));
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}

	}

	/*
	 * 非首次进入添加车辆页面 未编辑信息返回
	 */
	@Test
	public void test059notFirst() throws InterruptedException {
		System.out.println("**********test059**********");
		try {
			action.click(By.id(HomePage.add_vehicle));
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
		assertEquals(".activity.butterfly.ButterflyActivity",
				driver.currentActivity());
	}

	/*
	 * 非首次进入添加车辆页面 编辑信息返回
	 */
	@Test
	public void test060notFirstEdit() throws InterruptedException {
		System.out.println("**********test060**********");
		try {
			action.click(By.id(HomePage.add_vehicle));
			action.inputDataById("cn.buding.martin:id/license_plate_text",
					"aaaaaa");
			action.back();
			action.click(By.id("android:id/button2"));
			assertEquals(".activity.butterfly.ButterflyActivity",
					driver.currentActivity());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 打开行车记录仪
	 */
	@Test
	public void test061drivingRecord() throws InterruptedException {
		System.out.println("**********test061**********");
		try {
			driver.findElementById("cn.buding.martin:id/onroad_title").click();
			Thread.sleep(1000);
			// action.swipeUp();
			// Thread.sleep(1000);
			// action.click(By.id("cn.buding.martin:id/ic_starter"));
			action.click(By.id("cn.buding.martin:id/image_onroad_start"));
			Thread.sleep(1000);
			// action.constrainClick(2);
			action.click(By.id("cn.buding.martin:id/setting"));
			action.click(By.id("cn.buding.martin:id/rec"));
			Thread.sleep(2000);
			action.back();
			Thread.sleep(2000);
			action.back();
			Thread.sleep(2000);
			action.back();
			hp.shutdownOnroad();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 行车记录仪录制
	 */
	@Test
	public void test062drivingRecordRec() throws InterruptedException {
		System.out.println("**********test062**********");
		try {
			driver.findElementById("cn.buding.martin:id/onroad_title").click();
			Thread.sleep(1000);
			// action.swipeUp();
			// Thread.sleep(1000);
			// action.click(By.id("cn.buding.martin:id/ic_starter"));
			action.click(By.id("cn.buding.martin:id/image_onroad_start"));
			Thread.sleep(1000);
			// action.constrainClick(2);
			action.click(By.id("cn.buding.martin:id/setting"));
			// 打开行车记录仪
			action.click(By.id("cn.buding.martin:id/rec"));
			// 开始录制
			action.click(By.id("cn.buding.martin:id/rec"));
			assertEquals(false,
					driver.findElementById("cn.buding.martin:id/photo")
							.isEnabled());
			assertEquals(false,
					driver.findElementById("cn.buding.martin:id/file")
							.isEnabled());
			action.back();
			Thread.sleep(2000);
			action.back();
			action.click(By.id("cn.buding.martin:id/setting"));
			action.click(By.id("cn.buding.martin:id/power"));
			// 点击确定
			action.click(By.id("android:id/button1"));
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 行车记录仪拍照
	 */
	@Test
	public void test063drivingTakePhoto() throws InterruptedException {
		System.out.println("**********test063**********");
		try {
			driver.findElementById("cn.buding.martin:id/onroad_title").click();
			Thread.sleep(1000);
			// 打开行车记录仪
			action.click(By.id("cn.buding.martin:id/rec"));
			// 拍照
			action.click(By.id("cn.buding.martin:id/photo"));
			action.back();
			Thread.sleep(2000);
			action.back();
			Thread.sleep(2000);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 删除文件
	 */
	@Test
	public void test064deleteFile() throws InterruptedException {
		System.out.println("**********test064**********");
		try {
			driver.findElementById("cn.buding.martin:id/onroad_title").click();
			Thread.sleep(1000);
			// 打开行车记录仪
			action.click(By.id("cn.buding.martin:id/rec"));
			// 拍照
			action.click(By.id("cn.buding.martin:id/file"));
			action.click(By.id("cn.buding.martin:id/btn_delete_rec"));
			action.click(By.id("android:id/button1"));
			action.back();
			Thread.sleep(2000);
			action.back();
			Thread.sleep(2000);
			action.back();
			Thread.sleep(2000);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 限行提醒 未添加车辆
	 */
	@Test
	public void test065restricRemindNull() throws InterruptedException {
		System.out.println("**********test065**********");
		try {
			action.click(By.name("微生活"));
			int x = driver.manage().window().getSize().width;
			int y = driver.manage().window().getSize().height;
			driver.swipe(x / 2, 4 * y / 5, x / 2, y / 5, 500);
			Thread.sleep(2000);
			driver.swipe(x / 2, 4 * y / 5, x / 2, y / 5, 500);
			driver.scrollTo("限行提醒").click();
			assertEquals(
					"添加车辆信息",
					driver.findElementById("cn.buding.martin:id/tv_remind_text")
							.getText());
			action.back();
			Thread.sleep(1000);
			action.back();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			action.backToHomepage();
		}

	}

	/*
	 * 登录有车辆账号查看限行提醒 13683116962
	 */
	@Test
	public void test066restricRemind() throws InterruptedException {
		System.out.println("**********test066**********");
		try {
			hp.login();
			sp.EntryAccount();
			action.click(By.name("微生活"));
			int x = driver.manage().window().getSize().width;
			int y = driver.manage().window().getSize().height;
			driver.swipe(x / 2, 4 * y / 5, x / 2, y / 5, 500);
			Thread.sleep(2000);
			driver.swipe(x / 2, 4 * y / 5, x / 2, y / 5, 500);
			driver.scrollTo("限行提醒").click();
			assertEquals(
					"京N2P289",
					driver.findElementById("cn.buding.martin:id/tv_license_num")
							.getText());
			action.back();
			Thread.sleep(1000);
			action.back();
			hp.login();
			sp.LogOut();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}

	}

	/*
	 * 应急电话
	 */
	@Test
	public void test067emergencyCall() {
		System.out.println("**********test067**********");
		try {
			action.click(By.name("微生活"));
			int x = driver.manage().window().getSize().width;
			int y = driver.manage().window().getSize().height;
			driver.swipe(x / 2, 4 * y / 5, x / 2, y / 5, 500);
			Thread.sleep(2000);
			driver.swipe(x / 2, 4 * y / 5, x / 2, y / 5, 500);
			driver.scrollTo("应急电话").click();
			// assertEquals("添加车辆信息",
			// driver.findElementById("cn.buding.martin:id/tv_remind_text").getText());
			action.click(By.name("排序"));
			action.click(By.name("中石化免费救援"));
			assertEquals(
					"中石化免费救援",
					driver.findElementById("cn.buding.martin:id/insurance_name")
							.getText());
			action.click(By.name("完成"));
			action.back();
			Thread.sleep(1000);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 贴条地图页面切换
	 */
	@Test
	public void test068pageSwicthTietiao() throws InterruptedException {
		System.out.println("**********test068**********");
		try {
			action.click(By.name("贴条地图"));
			// 判断是否有 WEBVIEW
			// action.isWebviewornot();
			Thread.sleep(3000);
			action.click(By.id("cn.buding.martin:id/btn_rank"));
			Thread.sleep(3000);
			action.click(By.id("cn.buding.martin:id/btn_park"));
			Thread.sleep(3000);
			action.click(By.id("cn.buding.martin:id/btn_more"));
			action.click(By.id("cn.buding.martin:id/btn_patrol"));
			action.click(By.id("cn.buding.martin:id/btn_more"));
			action.click(By.id("cn.buding.martin:id/btn_camera"));
			action.click(By.id("cn.buding.martin:id/btn_more"));
			action.click(By.id("cn.buding.martin:id/btn_station"));
			action.click(By.id("cn.buding.martin:id/btn_more"));
			action.click(By.id("cn.buding.martin:id/btn_bank"));
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 进入微江湖
	 */
	@Test
	public void test069articleList() throws InterruptedException {
		System.out.println("**********test069**********");
		try {
			action.clickweijianghu();
			action.back();
			Thread.sleep(1000);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 未登录点击我的收藏
	 */
	@Test
	public void test070saveList() throws InterruptedException {
		System.out.println("**********test070**********");
		try {
			action.clickweijianghu();
			action.click(By.id("cn.buding.martin:id/favorite_article"));
			// 点击取消
			action.click(By.id("android:id/button2"));
			action.back();
			Thread.sleep(1000);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	@Test
	public void test071saveList() throws InterruptedException {
		System.out.println("**********test071**********");
		try {
			action.clickweijianghu();
			action.click(By.id("cn.buding.martin:id/favorite_article"));
			// 点击登录
			action.click(By.id("android:id/button1"));
			action.inputDataById("cn.buding.martin:id/phone", "13683116962");
			action.inputDataById("cn.buding.martin:id/password", "wangzhen");
			List<WebElement> denglu = driver.findElementsByName("登录");
			denglu.get(1).click();
			try {
				if (driver.findElementById("cn.buding.martin:id/empty_tips") != null) {
					assertEquals(1, 1);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				assertEquals(1, 2);
			}
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			action.back();
			hp.login();
			sp.LogOut();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	@Test
	public void test072saveList() throws InterruptedException {
		System.out.println("**********test072**********");
		try {
			action.clickweijianghu();
			action.click(By.id("cn.buding.martin:id/favorite_article"));
			// 点击取消
			action.click(By.id("android:id/button1"));
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 登录点击我的收藏
	 */
	@Test
	public void test073saveList() throws InterruptedException {
		System.out.println("**********test073**********");
		try {
			hp.login();
			sp.EntryAccount();
			action.clickweijianghu();
			action.click(By.id("cn.buding.martin:id/favorite_article"));
			try {
				if (driver.findElementById("cn.buding.martin:id/empty_tips") != null) {
					assertEquals(1, 1);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				assertEquals(1, 2);
			}
			// action.click(By.name("微江湖"));
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			action.back();
			hp.login();
			sp.LogOut();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}

	}

	/*
	 * 进入文章详情页
	 */
	@Test
	public void test074articleDetail() throws InterruptedException {
		System.out.println("**********test074**********");
		try {
			action.clickweijianghu();
			// 点击第一篇文章
			action.click(By.id("cn.buding.martin:id/article_title"));
			System.out.println("分享数："
					+ driver.findElementById("cn.buding.martin:id/share")
							.getText());
			System.out.println("评论数："
					+ driver.findElementById(
							"cn.buding.martin:id/tv_comment_count").getText());
			assertEquals("想说点什么",
					driver.findElementById("cn.buding.martin:id/tv_comment")
							.getText());
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}

	}

	/*
	 * 收藏文章
	 */
	@Test
	public void test075saveAnArticle() throws InterruptedException {
		System.out.println("**********test075**********");
		try {
			hp.login();
			sp.EntryAccount();
			action.clickweijianghu();
			// 点击第一篇文章
			action.click(By.id("cn.buding.martin:id/article_title"));
			action.click(By.id("cn.buding.martin:id/fl_favorate_background"));
			action.back();
			action.click(By.id("cn.buding.martin:id/favorite_article"));
			try {
				if (driver.findElementById("cn.buding.martin:id/article_title") != null) {
					assertEquals(1, 1);
					driver.findElementById("cn.buding.martin:id/article_title")
							.click();
					action.click(By
							.id("cn.buding.martin:id/fl_favorate_background"));
					action.back();
					Thread.sleep(1000);
					action.back();
					Thread.sleep(1000);
					action.back();
					Thread.sleep(1000);
					action.back();
					hp.login();
					sp.LogOut();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				assertEquals(2, 1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}

	}

	/*
	 * 未登录点击文章收藏
	 */
	@Test
	public void test076saveAnArticle() throws InterruptedException {
		System.out.println("**********test076**********");
		try {
			action.clickweijianghu();
			// 点击第一篇文章
			action.click(By.id("cn.buding.martin:id/article_title"));
			action.click(By.id("cn.buding.martin:id/fl_favorate_background"));
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 未登录点击文章收藏登陆收藏
	 */
	@Test
	public void test077saveArticle() throws InterruptedException {
		System.out.println("**********test077**********");
		try {
			action.clickweijianghu();
			// 点击第一篇文章
			action.click(By.id("cn.buding.martin:id/article_title"));
			action.click(By.id("cn.buding.martin:id/fl_favorate_background"));
			action.inputDataById("cn.buding.martin:id/phone", "13683116962");
			action.inputDataById("cn.buding.martin:id/password", "wangzhen");
			List<WebElement> denglu = driver.findElementsByName("登录");
			denglu.get(1).click();
			action.back();
			action.click(By.id("cn.buding.martin:id/favorite_article"));
			try {
				if (driver.findElementById("cn.buding.martin:id/article_title") != null) {
					assertEquals(1, 1);
					Thread.sleep(3000);
					driver.findElementById("cn.buding.martin:id/article_title")
							.click();
					action.click(By
							.id("cn.buding.martin:id/fl_favorate_background"));
					action.back();
					Thread.sleep(1000);
					action.back();
					Thread.sleep(1000);
					action.back();
					Thread.sleep(1000);
					action.back();
					hp.login();
					sp.LogOut();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				assertEquals(2, 1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 缓存评论
	 */
	@Test
	public void test078commentsCache() throws InterruptedException {
		System.out.println("**********test078**********");
		try {
			action.clickweijianghu();
			// 点击第一篇文章
			action.click(By.id("cn.buding.martin:id/article_title"));
			action.click(By.id("cn.buding.martin:id/tv_comment"));
			action.click(By.id("cn.buding.martin:id/iv_submit"));
			assertEquals("说点什么吧",
					driver.findElementById("cn.buding.martin:id/tv_title")
							.getText());
			action.inputDataById("cn.buding.martin:id/et_comment", "我是老司机");
			// 点击提交
			// action.click(By.id("cn.buding.martin:id/iv_submit"));
			action.click(By.id("cn.buding.martin:id/iv_close"));
			assertEquals("[草稿]我是老司机",
					driver.findElementById("cn.buding.martin:id/tv_comment")
							.getText());
			action.back();
			Thread.sleep(1000);
			action.click(By.id("cn.buding.martin:id/article_title"));
			assertEquals("想说点什么",
					driver.findElementById("cn.buding.martin:id/tv_comment")
							.getText());
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 字数超过140个字
	 */
	@Test
	public void test079wordsExceed() throws InterruptedException {
		System.out.println("**********test079**********");
		try {
			action.clickweijianghu();
			// 点击第一篇文章
			action.click(By.id("cn.buding.martin:id/article_title"));
			action.click(By.id("cn.buding.martin:id/tv_comment"));
			action.inputDataById(
					"cn.buding.martin:id/et_comment",
					"测试科目聪明的我村民们刺猬成为秘密文秘沉迷文明覅文秘聪明沉迷我们覅草莓味草莓味从我没我聪明么哦吃我饿们从我么哦哇么哦文件佛恶魔从么我饿服务莫每次我哦么没从而为么哦么问哦方面我额莫文明佛闻佛从事大口大口觅偶的都快成迷我们草莓味没吃饭聪明蝶窦成为魔的场面从我沉默么我恶魔从没问哦哦密码错误出卖我");
			assertEquals("您还可以输入-5个字",
					driver.findElementById("cn.buding.martin:id/tv_count")
							.getText());
			action.click(By.id("cn.buding.martin:id/iv_submit"));
			assertEquals("超出字数了喔",
					driver.findElementById("cn.buding.martin:id/tv_title")
							.getText());
			action.click(By.id("cn.buding.martin:id/iv_close"));
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 提交评论成功
	 */
	@Test
	public void test080submitComments() throws InterruptedException {
		System.out.println("**********test080**********");
		try {
			hp.login();
			sp.EntryAccount();
			action.clickweijianghu();
			// 点击第一篇文章
			action.click(By.id("cn.buding.martin:id/article_title"));
			action.click(By.id("cn.buding.martin:id/tv_comment"));
			action.inputDataById("cn.buding.martin:id/et_comment", "文章写的不错");
			action.click(By.id("cn.buding.martin:id/iv_submit"));
			Thread.sleep(5000);
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 提交评论成功
	 */
	@Test
	public void test081submitAnonymous() throws InterruptedException {
		System.out.println("**********test081**********");
		try {
			action.clickweijianghu();
			// 点击第一篇文章
			action.click(By.id("cn.buding.martin:id/article_title"));
			action.click(By.id("cn.buding.martin:id/tv_comment"));
			action.inputDataById("cn.buding.martin:id/et_comment", "文章写的不错");
			action.click(By.id("cn.buding.martin:id/cb_anonymity"));
			action.click(By.id("cn.buding.martin:id/iv_submit"));
			Thread.sleep(5000);
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			action.back();
			hp.login();
			sp.LogOut();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 进入车友评论页 Cache
	 */
	@Test
	public void test082commentsPage() throws InterruptedException {
		System.out.println("**********test082**********");
		try {
			action.clickweijianghu();
			// 点击第一篇文章
			action.click(By.id("cn.buding.martin:id/article_title"));
			// 点击评论数
			action.click(By.id("cn.buding.martin:id/tv_commment_container"));
			// 点击评论输入框
			action.click(By.id("cn.buding.martin:id/make_comment"));
			action.inputDataById("cn.buding.martin:id/et_comment", "微生活真不错");
			action.click(By.id("cn.buding.martin:id/iv_close"));
			action.back();
			action.click(By.id("cn.buding.martin:id/tv_commment_container"));
			assertEquals("[草稿]微生活真不错",
					driver.findElementById("cn.buding.martin:id/make_comment")
							.getText());
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 文章分享（朋友圈） 取消
	 */
	@Test
	public void test083articleShare() throws InterruptedException {
		System.out.println("**********test083**********");
		try {
			action.clickweijianghu();
			// 点击第一篇文章
			action.click(By.id("cn.buding.martin:id/article_title"));
			System.out.println(driver.findElementById(
					"cn.buding.martin:id/share").getText());
			action.click(By.id("cn.buding.martin:id/share"));
			// 分享朋友圈
			action.click(By.id("cn.buding.martin:id/friend_circle"));
			// 点击返回
			action.click(By.id("com.tencent.mm:id/ew"));
			action.click(By.id("com.tencent.mm:id/axw"));
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 文章成功分享
	 */
	@Test
	public void test084articleShareSucc() throws InterruptedException {
		System.out.println("**********test084**********");
		// 进入微江湖
		try {
			action.clickweijianghu();
			// 点击第一篇文章
			action.click(By.id("cn.buding.martin:id/article_title"));
			System.out.println(driver.findElementById(
					"cn.buding.martin:id/share").getText());
			action.click(By.id("cn.buding.martin:id/share"));
			// 分享朋友圈
			action.click(By.id("cn.buding.martin:id/friend_circle"));
			// 点击发送
			action.click(By.id("com.tencent.mm:id/eb"));
			System.out.println(driver.findElementById(
					"cn.buding.martin:id/share").getText());
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 分享点击蒙版 文章
	 */
	@Test
	public void test085mengban() throws InterruptedException {
		System.out.println("**********test085**********");
		try {
			action.clickweijianghu();
			// 点击第一篇文章
			action.click(By.id("cn.buding.martin:id/article_title"));
			System.out.println(driver.findElementById(
					"cn.buding.martin:id/share").getText());
			action.click(By.id("cn.buding.martin:id/share"));
			Thread.sleep(3000);
			action.constrainClick(2);
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 账户与设置页的分享 取消
	 */
	@Test
	public void test086spShareCancel() throws InterruptedException {
		System.out.println("**********test086**********");
		try {
			hp.login();
			action.click(By.id("cn.buding.martin:id/share"));
			action.click(By.id("cn.buding.martin:id/friend_circle"));
			Thread.sleep(5000);
			action.click(By.id("com.tencent.mm:id/ew"));
			action.click(By.id("com.tencent.mm:id/axw"));
			Thread.sleep(2000);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}

	}

	/*
	 * 账户与设置页的分享 成功分享
	 */
	@Test
	public void test087spShareSuccess() throws InterruptedException {
		System.out.println("**********test087**********");
		try {
			hp.login();
			action.click(By.id("cn.buding.martin:id/share"));
			action.click(By.id("cn.buding.martin:id/friend_circle"));
			Thread.sleep(5000);
			action.click(By.id("com.tencent.mm:id/eb"));
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 账户与设置页的分享 点击蒙版取消
	 */
	@Test
	public void test088spShareClickMask() throws InterruptedException {
		System.out.println("**********test088**********");
		try {
			hp.login();
			action.click(By.id("cn.buding.martin:id/share"));
			Thread.sleep(2000);
			action.constrainClick(2);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 消息native直接跳转 登陆账号13810276832 用例执行完毕未退出
	 */
	@Test
	public void test089nativeDirectJump() throws InterruptedException {
		System.out.println("**********test089**********");
		try {
			hp.login();
			sp.EntryBindingAccount();
			hp.login();
			// 点击消息盒子
			action.click(By.id("cn.buding.martin:id/message"));
			action.click(By.name("native直接跳转"));
			Thread.sleep(2000);
			assertEquals(".activity.butterfly.ButterflyActivity",
					driver.currentActivity());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * native非直接跳转
	 */
	@Test
	public void test090nativeDirectJump() throws InterruptedException {
		System.out.println("**********test090**********");
		try {
			hp.login();
			// 点击消息盒子
			action.click(By.id("cn.buding.martin:id/message"));
			action.click(By.name("native非直接跳转"));
			// 点击查看详情
			action.click(By.id("cn.buding.martin:id/web_text"));
			Thread.sleep(2000);
			assertEquals(".activity.butterfly.ButterflyActivity",
					driver.currentActivity());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * web直接跳转
	 */
	@Test
	public void test091nativeDirectJump() throws InterruptedException {
		System.out.println("**********test091**********");
		try {
			hp.login();
			// 点击消息盒子
			action.click(By.id("cn.buding.martin:id/message"));
			action.click(By.name("web页面直接跳转"));
			Thread.sleep(5000);
			assertEquals(".activity.WebViewActivity", driver.currentActivity());
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}

	}

	/*
	 * web非直接跳转
	 */
	@Test
	public void test092nativeDirectJump() throws InterruptedException {
		System.out.println("**********test092**********");
		try {
			hp.login();
			// 点击消息盒子
			action.click(By.id("cn.buding.martin:id/message"));
			action.click(By.name("web页面非直接跳转"));
			// 点击查看详情
			action.click(By.id("cn.buding.martin:id/web_text"));
			Thread.sleep(5000);
			assertEquals(".activity.WebViewActivity", driver.currentActivity());
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			action.back();
			hp.login();
			sp.LogOut();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}

	}

	/*
	 * 违章详情页 分享
	 */
	@Test
	public void test093vioDetailShare() throws InterruptedException {
		System.out.println("**********test093**********");
		try {
			hp.login();
			sp.EntryBindingAccount();
			Thread.sleep(5000);
			action.click(By.name("津AHJ236"));
			action.click(By.id("cn.buding.martin:id/name"));
			action.click(By.id("cn.buding.martin:id/share"));
			action.click(By.id("cn.buding.martin:id/friend_circle"));
			Thread.sleep(5000);
			action.click(By.id("com.tencent.mm:id/eb"));
			action.back();
			Thread.sleep(1000);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}

	}

	/*
	 * 违章详情页无纠错按钮
	 */
	@Test
	public void test094vioErrorCorrectFalse() {
		System.out.println("**********test094**********");
		try {
			Thread.sleep(5000);
			action.click(By.name("津AHJ236"));
			action.click(By.id("cn.buding.martin:id/name"));
			try {
				if (driver.findElementById("cn.buding.martin:id/correct") != null) {
					System.out.println("有纠错按钮");
					;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("无纠错按钮");
			}
			action.back();
			Thread.sleep(1000);
			action.back();
			hp.login();
			sp.LogOut();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}

	}

	/*
	 * 违章详情页有纠错按钮
	 */
	@Test
	public void test095vioErrorCorrectTrue() {
		System.out.println("**********test095**********");
		try {
			hp.login();
			sp.EntryAccount();
			Thread.sleep(5000);
			action.click(By.name("京N2P289"));
			action.click(By.id("cn.buding.martin:id/name"));
			try {
				if (driver.findElementById("cn.buding.martin:id/correct") != null) {
					System.out.println("有纠错按钮");
					// 第一次进入显示纠错说明
					assertEquals("如果您发现位置有误，还请帮忙修正哦，举手之劳可以帮助更多车友！", driver
							.findElementById("cn.buding.martin:id/text_popup")
							.getText());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("无纠错按钮");
			}
			action.back();
			Thread.sleep(1000);
			action.back();
			hp.login();
			sp.LogOut();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 贴条地图分享
	 */
	@Test
	public void test096tietiaoShare() throws InterruptedException {
		System.out.println("**********test096**********");
		try {
			action.click(By.name("贴条地图"));
			Thread.sleep(5000);
			action.click(By.id("cn.buding.martin:id/share"));
			action.click(By.id("cn.buding.martin:id/friend_circle"));
			Thread.sleep(5000);
			action.click(By.id("com.tencent.mm:id/eb"));
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 在路上详情页分享
	 */
	@Test
	public void test097onroadShare() throws InterruptedException {
		System.out.println("**********test097**********");
		try {
			driver.findElementById("cn.buding.martin:id/onroad_title").click();
			Thread.sleep(1000);
			action.click(By.id("cn.buding.martin:id/image_onroad_start"));
			Thread.sleep(1000);
			action.click(By.id("cn.buding.martin:id/share"));
			// 分享日汇总及详情
			action.click(By.id("cn.buding.martin:id/share_day_detail"));
			action.click(By.id("cn.buding.martin:id/friend_circle"));
			Thread.sleep(5000);
			action.click(By.id("com.tencent.mm:id/eb"));
			// 仅分享日汇总
			action.click(By.id("cn.buding.martin:id/share"));
			action.click(By.id("cn.buding.martin:id/share_day_summary"));
			action.click(By.id("cn.buding.martin:id/friend_circle"));
			Thread.sleep(5000);
			action.click(By.id("com.tencent.mm:id/eb"));
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}

	}

	/*
	 * 登陆账号覆盖安装
	 */
	@Test
	public void test098coverInstall() throws InterruptedException {
		System.out.println("**********test098**********");
		try {
			hp.login();
			sp.EntryAccount();
			// 断言
			hp.compareCars(GlobalDataStore.vehicle_phoneuser);
			action.coverInstall();
			driver.startActivity("cn.buding.martin", ".activity.SplashActivity");
			Thread.sleep(5000);
			hp.compareCars(GlobalDataStore.vehicle_phoneuser);
			hp.login();
			sp.LogOut();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}

	}

	/*
	 * 未登录账号覆盖安装
	 */
	@Test
	public void test099coverInstallUnLogin() throws InterruptedException {
		System.out.println("**********test099**********");
		// 添加车辆
		try {
			action.click(By.id(HomePage.add_vehicle));
			action.waitForRep(10);
			action.inputDataById(ac.LicencePlatenum, "N1S273");
			action.inputDataById(ac.enginenum, "7706690");
			action.click(By.id(ac.save));
			action.click(By.id(ac.SayLater));
			action.back();
			hp.compareCars("京N1S273");
			action.coverInstall();
			driver.startActivity("cn.buding.martin", ".activity.SplashActivity");
			Thread.sleep(5000);
			hp.compareCars("京N1S273");
			action.click(By.name("京N1S273"));
			action.click(By.id("cn.buding.martin:id/edit"));
			action.waitForRep(60);
			action.click(By.id(ac.deleteCar));
			action.click(By.id(ac.confirm));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 未登录无车辆信息覆盖安装
	 */
	@Test
	public void test100coverInstallNoVehicle() throws InterruptedException {
		System.out.println("**********test100**********");
		try {
			hp.compareCars("京N1S273");
			// 覆盖安装
			action.coverInstall();
			driver.startActivity("cn.buding.martin", ".activity.SplashActivity");
			Thread.sleep(5000);
			hp.compareCars("京N1S273");
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 已登录无车辆信息覆盖安装
	 */
	@Test
	public void test101coverInstallNoVehicleLogin() throws InterruptedException {
		System.out.println("**********test101**********");
		try {
			hp.login();
			action.waitForRep(60);
			sp.entryNocarAccount();
			hp.compareCars("京N1S273");
			// 覆盖安装
			action.coverInstall();
			driver.startActivity("cn.buding.martin", ".activity.SplashActivity");
			Thread.sleep(5000);
			hp.compareCars("京N1S273");
			hp.login();
			sp.LogOut();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 首次进入未编辑信息返回，不设置闹钟直接返回主页
	 */
	@Test
	public void test102firstNoEdit() throws InterruptedException {
		System.out.println("**********test102**********");
		try {
			driver.removeApp("cn.buding.martin");
			Thread.sleep(10000);
			driver.installApp(action.obtainAbsolutePath());
			Thread.sleep(20000);
			driver.startActivity("cn.buding.martin", ".activity.SplashActivity");
			vgp.entryHome();
			action.click(By.id(HomePage.add_vehicle));
			action.back();
			action.click(By.id("cn.buding.martin:id/no"));
			assertEquals(".activity.butterfly.ButterflyActivity",
					driver.currentActivity());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 首次进入编辑信息返回，不设置闹钟直接返回主页
	 */
	@Test
	public void test103firstEdit() throws InterruptedException {
		try {
			System.out.println("**********test103**********");
			driver.removeApp("cn.buding.martin");
			Thread.sleep(10000);
			driver.installApp(action.obtainAbsolutePath());
			Thread.sleep(20000);
			driver.startActivity("cn.buding.martin", ".activity.SplashActivity");
			vgp.entryHome();
			action.click(By.id(HomePage.add_vehicle));
			action.inputDataById("cn.buding.martin:id/license_plate_text",
					"aaaaaa");
			action.back();
			action.click(By.id("cn.buding.martin:id/no"));
			assertEquals(".activity.butterfly.ButterflyActivity",
					driver.currentActivity());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 首次进入未编辑信息返回，点击设置闹钟
	 */
	@Test
	public void test104firstNoEditSet() throws InterruptedException {
		System.out.println("**********test104**********");
		try {
			driver.removeApp("cn.buding.martin");
			Thread.sleep(10000);
			driver.installApp(action.obtainAbsolutePath());
			Thread.sleep(20000);
			driver.startActivity("cn.buding.martin", ".activity.SplashActivity");
			vgp.entryHome();
			action.click(By.id(HomePage.add_vehicle));
			action.back();
			action.click(By.id("cn.buding.martin:id/to_create"));
			action.click(By.id("cn.buding.martin:id/cancle"));
			assertEquals(".activity.butterfly.ButterflyActivity",
					driver.currentActivity());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 首次进入编辑信息返回，点击设置闹钟
	 */
	@Test
	public void test105firstEditSet() throws InterruptedException {
		System.out.println("**********test105**********");
		try {
			driver.removeApp("cn.buding.martin");
			Thread.sleep(10000);
			driver.installApp(action.obtainAbsolutePath());
			Thread.sleep(20000);
			driver.startActivity("cn.buding.martin", ".activity.SplashActivity");
			vgp.entryHome();
			action.click(By.id(HomePage.add_vehicle));
			action.inputDataById("cn.buding.martin:id/license_plate_text",
					"aaaaaa");
			action.back();
			action.click(By.id("cn.buding.martin:id/to_create"));
			action.click(By.id("cn.buding.martin:id/cancle"));
			Thread.sleep(2000);
			assertEquals(".activity.butterfly.ButterflyActivity",
					driver.currentActivity());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 卸载安装 查看onroad状态
	 */
	@Test
	public void test106uninstallOnroad() throws InterruptedException {
		System.out.println("**********test106**********");
		try {
			hp.onroadStatus();
			hp.openOnroad();
			driver.removeApp("cn.buding.martin");
			Thread.sleep(10000);
			driver.installApp(action.obtainAbsolutePath());
			Thread.sleep(20000);
			driver.startActivity("cn.buding.martin", ".activity.SplashActivity");
			vgp.entryHome();
			hp.onroadStatus();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 覆盖安装查看onroad状态
	 */
	@Test
	public void test107coverinstallOnroad() throws InterruptedException {
		System.out.println("**********test107**********");
		try {
			hp.onroadStatus();
			hp.openOnroad();
			action.coverInstall();
			driver.startActivity("cn.buding.martin", ".activity.SplashActivity");
			hp.onroadStatus();
			hp.shutdownOnroad();
			hp.onroadStatus();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}

	}

	/*
	 * 卸载安装 查看onroad状态
	 */
	@Test
	public void test108uninstallOnroad() throws InterruptedException {
		System.out.println("**********test108**********");
		try {
			hp.onroadStatus();
			hp.openOnroadAgain();
			driver.removeApp("cn.buding.martin");
			Thread.sleep(10000);
			driver.installApp(action.obtainAbsolutePath());
			Thread.sleep(20000);
			driver.startActivity("cn.buding.martin", ".activity.SplashActivity");
			vgp.entryHome();
			hp.onroadStatus();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}
	}

	/*
	 * 覆盖安装查看onroad状态
	 */
	@Test
	public void test109coverinstallOnroad() throws InterruptedException {
		System.out.println("**********test109**********");
		try {
			hp.onroadStatus();
			hp.openOnroad();
			action.coverInstall();
			driver.startActivity("cn.buding.martin", ".activity.SplashActivity");
			hp.onroadStatus();
			hp.shutdownOnroad();
			hp.onroadStatus();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}

	}

	/*
	 * 缓存评论 kill应用
	 */
	@Test
	public void test110commentsCache() throws InterruptedException {
		System.out.println("**********test110**********");
		try {
			action.clickweijianghu();
			// 点击第一篇文章
			action.click(By.id("cn.buding.martin:id/article_title"));
			action.click(By.id("cn.buding.martin:id/tv_comment"));
			action.inputDataById("cn.buding.martin:id/et_comment", "我是老司机");
			// 点击提交
			// action.click(By.id("cn.buding.martin:id/iv_submit"));
			action.click(By.id("cn.buding.martin:id/iv_close"));
			assertEquals("[草稿]我是老司机",
					driver.findElementById("cn.buding.martin:id/tv_comment")
							.getText());
			driver.startActivity("cn.buding.martin", ".activity.SplashActivity");
			driver.findElementByName("微生活").click();
			Thread.sleep(3000);
			driver.findElementById("cn.buding.martin:id/service_title").click();
			action.click(By.id("cn.buding.martin:id/article_title"));
			assertEquals("想说点什么",
					driver.findElementById("cn.buding.martin:id/tv_comment")
							.getText());
			action.back();
			Thread.sleep(1000);
			action.back();
			Thread.sleep(1000);
			action.back();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 账户与设置页面分享至新浪微博
	 */
	@Test
	public void test111spShareSinaSucc() {
		System.out.println("**********test111**********");
		try {
			hp.login();
			action.click(By.id("cn.buding.martin:id/share"));
			action.click(By.id("cn.buding.martin:id/weibo"));
			action.click(By.id("com.sina.weibo:id/titleSave"));
			action.back();

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 账户与设置页面分享至新浪微博 跳至微博取消
	 */
	@Test
	public void test112spShareSinaCancel() {
		System.out.println("**********test112**********");
		try {
			hp.login();
			action.click(By.id("cn.buding.martin:id/share"));
			action.click(By.id("cn.buding.martin:id/weibo"));
			action.click(By.id("com.sina.weibo:id/titleBack"));
			action.click(By.name("不保存"));
			action.back();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 账户与设置页面分享至qq 跳至qq取消
	 */
	@Test
	public void test113spShareQQCancel() {
		System.out.println("**********test113**********");
		try {
			hp.login();
			action.click(By.id("cn.buding.martin:id/share"));
			action.click(By.id("cn.buding.martin:id/qq"));
			action.click(By.id("com.tencent.mobileqq:id/ivTitleBtnRightText"));
			action.back();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 账户与设置页面分享至qq 成功分享
	 */
	@Test
	public void test114spShareQQSuccesss() {
		System.out.println("**********test114**********");
		try {
			hp.login();
			action.click(By.id("cn.buding.martin:id/share"));
			action.click(By.id("cn.buding.martin:id/qq"));
			action.click(By.name("我的电脑"));
			action.click(By.id("com.tencent.mobileqq:id/dialogRightBtn"));
			action.click(By.id("com.tencent.mobileqq:id/dialogLeftBtn"));
			action.back();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 账户与设置页面分享至微信好友 成功分享
	 */
	@Test
	public void test115spShareWCSuccesss() {
		System.out.println("**********test115**********");
		try {
			hp.login();
			action.click(By.id("cn.buding.martin:id/share"));
			action.click(By.id("cn.buding.martin:id/weixin"));
			action.click(By.id("com.tencent.mm:id/hm"));
			action.click(By.id("com.tencent.mm:id/axw"));
			action.click(By.id("com.tencent.mm:id/a0o"));
			action.back();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 账户与设置页面分享至微信好友 微信内取消
	 */
	@Test
	public void test116spShareWCCancel() {
		System.out.println("**********test116**********");
		try {
			hp.login();
			action.click(By.id("cn.buding.martin:id/share"));
			action.click(By.id("cn.buding.martin:id/weixin"));
			action.click(By.id("com.tencent.mm:id/ew"));
			action.back();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 汽车代驾 webview
	 */
	@Test
	public void test117designatedDriving() {
		System.out.println("**********test117**********");
		try {
			action.click(By.name("微生活"));
			int x = driver.manage().window().getSize().width;
			int y = driver.manage().window().getSize().height;
			driver.swipe(x / 2, 4 * y / 5, x / 2, y / 5, 500);
			Thread.sleep(2000);
			driver.swipe(x / 2, 4 * y / 5, x / 2, y / 5, 500);
			driver.scrollTo("汽车代驾").click();
			Thread.sleep(3000);
			assertEquals("汽车代驾",
					driver.findElementById("cn.buding.martin:id/title")
							.getText());
			action.back();
			Thread.sleep(1000);
			action.back();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			action.backToHomepage();
		}
	}

	/*
	 * 车险试算
	 */
	@Test
	public void test118autoInsurance() {
		System.out.println("**********test118**********");
		try {
			action.click(By.name("微生活"));
			int x = driver.manage().window().getSize().width;
			int y = driver.manage().window().getSize().height;
			driver.swipe(x / 2, 4 * y / 5, x / 2, y / 5, 500);
			Thread.sleep(2000);
			driver.swipe(x / 2, 4 * y / 5, x / 2, y / 5, 500);
			driver.scrollTo("车险试算").click();
			Thread.sleep(3000);
			assertEquals("车险试算",
					driver.findElementById("cn.buding.martin:id/title")
							.getText());
			action.back();
			Thread.sleep(1000);
			action.back();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			action.backToHomepage();
		}
	}

	/*
	 * 限行提醒添加车辆 删除限行提醒 取消
	 */
	@Test
	public void test119resributeAddCar() {
		System.out.println("**********test119**********");
		try {
			action.click(By.name("微生活"));
			int x = driver.manage().window().getSize().width;
			int y = driver.manage().window().getSize().height;
			driver.swipe(x / 2, 4 * y / 5, x / 2, y / 5, 500);
			Thread.sleep(2000);
			driver.swipe(x / 2, 4 * y / 5, x / 2, y / 5, 500);
			driver.scrollTo("限行提醒").click();
			// 点击添加车辆
			action.click(By.id("cn.buding.martin:id/add_taillimit_vehicle"));
			// 车牌号
			action.inputDataById("cn.buding.martin:id/license_plate_text",
					"000000");
			// 保存
			action.click(By.id("cn.buding.martin:id/tv_save"));
			// action.click(By.id("cn.buding.martin:id/tv_delete_or_cancle"));
			assertEquals(
					"京000000",
					driver.findElementById("cn.buding.martin:id/tv_license_num")
							.getText());
			action.click(By.id("cn.buding.martin:id/tv_license_num"));
			// 删除
			action.click(By.id("cn.buding.martin:id/tv_delete_or_cancle"));
			action.click(By.id("android:id/button2"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	/*
	 * 限行提醒添加车辆 删除限行提醒
	 */
	@Test
	public void test120resributeAddCarDelete() {
		System.out.println("**********test120**********");
		try {
			// 删除
			action.click(By.id("cn.buding.martin:id/tv_delete_or_cancle"));
			action.click(By.id("android:id/button1"));
			assertEquals(
					"添加车辆信息",
					driver.findElementById("cn.buding.martin:id/tv_remind_text")
							.getText());
			action.back();
			Thread.sleep(1000);
			action.back();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			action.backToHomepage();
		}
	}

	/*
	 * 添加车牌号不保存
	 */
	@Test
	public void test121resributeAddCarCancel() {
		System.out.println("**********test121**********");
		try {
			action.click(By.name("微生活"));
			int x = driver.manage().window().getSize().width;
			int y = driver.manage().window().getSize().height;
			driver.swipe(x / 2, 4 * y / 5, x / 2, y / 5, 500);
			Thread.sleep(2000);
			driver.swipe(x / 2, 4 * y / 5, x / 2, y / 5, 500);
			driver.scrollTo("限行提醒").click();
			// 点击添加车辆
			action.click(By.id("cn.buding.martin:id/add_taillimit_vehicle"));
			// 车牌号
			action.inputDataById("cn.buding.martin:id/license_plate_text",
					"000000");
			// 不保存
			// action.click(By.id("cn.buding.martin:id/tv_save"));
			action.click(By.id("cn.buding.martin:id/tv_delete_or_cancle"));
			action.click(By.id("android:id/button1"));
			assertEquals(
					"添加车辆信息",
					driver.findElementById("cn.buding.martin:id/tv_remind_text")
							.getText());
			action.back();
			Thread.sleep(1000);
			action.back();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			action.backToHomepage();
		}
	}

	/*
	 * 应急电话调起拨号页面
	 */
	@Test
	public void test122emergencyCallPage() {
		System.out.println("**********test122**********");
		try {
			action.click(By.name("微生活"));
			int x = driver.manage().window().getSize().width;
			int y = driver.manage().window().getSize().height;
			driver.swipe(x / 2, 4 * y / 5, x / 2, y / 5, 500);
			Thread.sleep(2000);
			driver.swipe(x / 2, 4 * y / 5, x / 2, y / 5, 500);
			driver.scrollTo("应急电话").click();
			// assertEquals("添加车辆信息",
			// driver.findElementById("cn.buding.martin:id/tv_remind_text").getText());
			action.click(By.id("cn.buding.martin:id/insurance_name"));
			// System.out.println(driver.currentActivity());
			assertEquals(".activities.DialtactsActivity",
					driver.currentActivity());
			driver.sendKeyEvent(4);
			action.back();
			Thread.sleep(1000);
			action.back();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 安装后微信快速登录
	 */
	@Test
	public void test123firstWechatLogin() throws InterruptedException {
		System.out.println("**********test123**********");
		try {
			action.resetApp();
			if (action.checkNet() != true) {
				action.waitForRep(60);
				action.click(By.name("北京(北京)"));
				Thread.sleep(1000);
			} else {
				Thread.sleep(4000);
				if (driver.currentActivity().equals(
						".activity.ChooseSelectedCity")) {
					action.click(By.name("北京(北京)"));
				}
			}

			// 滑动viewgroup
			Thread.sleep(2000);
			action.viewgroupslide(3);

			// 进入微车主页
			action.waitForRep(60);
			action.click(By.id(vgp.startUsing));
			action.click(By.id("cn.buding.martin:id/weixin_login"));
			int x = driver.findElementByClassName(
					"com.tencent.smtt.webkit.WebView").getSize().width;
			int y = driver.findElementByClassName(
					"com.tencent.smtt.webkit.WebView").getSize().height;
			// action.tap(by);
			int x0 = driver.findElementByClassName(
					"com.tencent.smtt.webkit.WebView").getLocation().x;
			int y0 = driver.findElementByClassName(
					"com.tencent.smtt.webkit.WebView").getLocation().y;
			// 点击登陆
			// action.click(By.className("android.widget.Button"));
			driver.tap(1, x0 + x / 2, y0 + y / 2, 1);
			Thread.sleep(7000);
			assertEquals((driver.currentActivity()),
					".activity.butterfly.ButterflyActivity");
			hp.login();
			sp.LogOut();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			action.resetApp();
			vgp.entryHome();
			assertEquals(1, 2);
		}

	}

	/*
	 * 首页广告
	 */
	@Test
	public void test124homepageAd() {
		System.out.println("**********test124**********");
		try {
			try {
				if (driver.findElementById(hp.homepageAd) != null) {
					System.out.println("首页广告： "
							+ driver.findElementById(hp.homepageAd).getText());
					action.click(By.id(hp.homepageAd));
					action.back();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("首页无广告");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 首页广告分享 朋友圈
	 */
	@Test
	public void test125homepageAdShare() {
		System.out.println("**********test125**********");
		try {
			try {
				if (driver.findElementById(hp.homepageAd) != null) {
					System.out.println("首页广告： "
							+ driver.findElementById(hp.homepageAd).getText());
					action.click(By.id(hp.homepageAd));
					action.click(By.id("cn.buding.martin:id/share"));
					action.click(By.id("cn.buding.martin:id/friend_circle"));
					Thread.sleep(5000);
					action.click(By.id("com.tencent.mm:id/eb"));
					action.back();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("首页无广告");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 关闭首页广告
	 */
	@Test
	public void test126homepageAdClose() {
		System.out.println("**********test126**********");
		try {
			try {
				if (driver.findElementById(hp.homepageAd) != null) {
					System.out.println("首页广告： "
							+ driver.findElementById(hp.homepageAd).getText());
					// 首页广告关闭按钮
					action.click(By.id("cn.buding.martin:id/close_ad"));
					try {
						if (driver.findElementById(hp.homepageAd) != null) {
							assertEquals(1, 2);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
						System.out.println("首页无广告");
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				System.out.println("首页无广告");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 修改城市 弹出提示窗口
	 */
	@Test
	public void test127changeCity() {
		System.out.println("**********test127**********");
		try {
			hp.login();
			sp.citySwitch("上海(上海)");
			action.waitForRep(5);
			driver.startActivity("cn.buding.martin", ".activity.SplashActivity"); // 切换至北京
			action.click(By.id("android:id/button1"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			action.backToHomepage();
		}
	}

	/*
	 * 
	 * 删除appium安装的三个apk
	 */
	@Test
	public void test150deleteApp() {
		driver.removeApp("io.appium.unlock");
		driver.removeApp("io.appium.settings");
		driver.removeApp("io.appium.android.ime");
	}

	@AfterClass
	public static void tearDown() throws Exception {
		driver.quit();
	}

}
