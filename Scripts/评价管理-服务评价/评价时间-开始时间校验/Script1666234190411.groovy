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

String search_name = GlobalVariable.evaluation_time_start

List compare_names = [[By.cssSelector(GlobalVariable.evaluation_time_ele)], '日期比较',
	 By.className(GlobalVariable.empty_tips), [true]]  //  [index=3]-字段是否在列表存在(boolean)

WebUI.sendKeys(findTestObject('评价管理/服务评价/评价时间-开始'), search_name)

WebUI.click(findTestObject('评价管理/服务评价/快速查询按钮'))

WebUI.delay(1)

WebUI.switchToFrame(findTestObject('frames/主页面/评价管理-服务评价'), 30)

List total = [By.className(GlobalVariable.total_ele)]  // 总计

List next = [By.className(GlobalVariable.next_page_ele)]  // 下一页

int page_size = 15 // 每页显示条数

CustomKeywords.'my.test.kits.TestUtilities.compare_result'(total, next, page_size, search_name, compare_names, '开始')  // 断言查询结果

@SetUp(skipped = false)
void setUp() {
    WebUI.openBrowser('')

    WebUI.maximizeWindow()

    WebUI.navigateToUrl(GlobalVariable.url)

    WebUI.sendKeys(findTestObject('登录页/登录名称'), GlobalVariable.username)

    WebUI.sendKeys(findTestObject('登录页/登录密码'), GlobalVariable.password)

    WebUI.click(findTestObject('登录页/登录按钮'))
	
	WebUI.click(findTestObject('评价管理/导航栏/评价管理'))
	
	WebUI.click(findTestObject('评价管理/导航栏/服务评价'))
}

@TearDown(skipped = false)
void TearDown() {
    Thread.sleep(2000)

    WebUI.closeBrowser()
}

