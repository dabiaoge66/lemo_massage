import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.SetUp
import com.kms.katalon.core.annotation.TearDown
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

WebDriver driver = DriverFactory.getWebDriver()

String search_data = GlobalVariable.creat_delivery_start + '/' + GlobalVariable.creat_delivery_end

String[] search_name = search_data.split('/')

List compare_names = [[By.cssSelector(GlobalVariable.delivery_created_time)], '日期比较',
	 By.className(GlobalVariable.empty_tips), [true]]  //  [index=3]-字段是否在列表存在(boolean)

WebUI.sendKeys(findTestObject('活动管理/注册会员送券/创建时间-开始'), search_name[0])

WebUI.sendKeys(findTestObject('活动管理/注册会员送券/创建时间-结束'), search_name[1])

WebUI.click(findTestObject('活动管理/注册会员送券/快速查询按钮'))

WebUI.delay(1)

WebUI.switchToFrame(findTestObject('frames/主页面/活动管理-注册会员送券'), 30)

List total = [By.className(GlobalVariable.total_ele)]  // 总计

List next = [By.className(GlobalVariable.next_page_ele)]  // 下一页

int page_size = 15 // 每页显示条数

CustomKeywords.'my.test.kits.TestUtilities.compare_result'(total, next, page_size, search_data, compare_names, '区间')  // 断言查询结果

@SetUp(skipped = false)
void setUp() {
    WebUI.openBrowser('')

    WebUI.maximizeWindow()

    WebUI.navigateToUrl(GlobalVariable.url)

    WebUI.sendKeys(findTestObject('登录页/登录名称'), GlobalVariable.username)

    WebUI.sendKeys(findTestObject('登录页/登录密码'), GlobalVariable.password)

    WebUI.click(findTestObject('登录页/登录按钮'))
	
	WebUI.click(findTestObject('活动管理/导航栏/活动管理'))
	
	WebUI.click(findTestObject('活动管理/导航栏/注册会员送券'))
}

@TearDown(skipped = false)
void TearDown() {
    Thread.sleep(2000)

    WebUI.closeBrowser()
}

