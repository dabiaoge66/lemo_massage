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

String search_name = GlobalVariable.select_place_name

List compare_names = [[By.cssSelector(GlobalVariable.merchant_provinces_ele)], '其他查询',
	 By.className(GlobalVariable.empty_tips), [true]]  //  [index=3]-字段是否在列表存在(boolean),空值和逆向不用传]

WebUI.sendKeys(findTestObject('商户管理/商户管理/所属区域搜索框'), search_name)

WebUI.click(findTestObject('商户管理/商户管理/所属区域搜索框'))

List<WebElement> search_list = driver.findElements(By.cssSelector(GlobalVariable.province_box_values))

for (int i = 0; i < search_list.size(); i++) {
    if (search_name.equals(search_list[i].text)) {
        search_list[i].click()
    } 
}

WebUI.click(findTestObject('商户管理/商户管理/快速查询按钮'))

WebUI.delay(1)

WebUI.switchToFrame(findTestObject('frames/主页面/商户管理-商户管理'), 30)

List total = [By.className(GlobalVariable.total_ele)]  // 总计

List next = [By.className(GlobalVariable.next_page_ele)]  // 下一页

int page_size = 15 // 每页显示条数

CustomKeywords.'my.test.kits.TestUtilities.compare_result'(total, next, page_size, search_name, compare_names)  // 断言查询结果

@SetUp(skipped = false)
void setUp() {
    WebUI.openBrowser('')

    WebUI.maximizeWindow()

    WebUI.navigateToUrl(GlobalVariable.url)

    WebUI.sendKeys(findTestObject('登录页/登录名称'), GlobalVariable.username)

    WebUI.sendKeys(findTestObject('登录页/登录密码'), GlobalVariable.password)

    WebUI.click(findTestObject('登录页/登录按钮'))
	
	WebUI.click(findTestObject('商户管理/导航栏/商户管理'))
	
	WebUI.click(findTestObject('商户管理/导航栏/商户管理-商户管理'))
}

@TearDown(skipped = false)
void TearDown() {
    Thread.sleep(2000)

    WebUI.closeBrowser()
}

