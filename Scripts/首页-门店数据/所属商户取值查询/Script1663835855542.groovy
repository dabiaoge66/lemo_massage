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

String search_name = 'UItestData_merchantName'

List compare_names = [[By.cssSelector(GlobalVariable.store_data_ele)], '其他查询',
	 By.className(GlobalVariable.empty_text), [false]]  //  [index=3]-字段是否在列表存在(boolean),空值和逆向不用传

WebUI.click(findTestObject('首页/门店数据/门店情况/所属商户-下拉框'))

WebUI.switchToFrame(findTestObject('frames/主页面/Home'), 30)

List<WebElement> search_list = driver.findElements(By.cssSelector(GlobalVariable.store_box_values))

for (int i = 0; i < search_list.size(); i++) {
	
    if (search_name.equals(search_list[i].text)) {
        search_list[i].click()
		
		WebUI.switchToDefaultContent()
    }
}

WebUI.click(findTestObject('首页/门店数据/门店情况/查询时间(类型)-下拉框'))

WebUI.click(findTestObject('首页/公共元素/查询时间(类型)-周2nd'))

WebUI.click(findTestObject('首页/门店数据/门店情况/查询时间(时间)-下拉框'))

WebUI.click(findTestObject('首页/公共元素/时间-年份入口1st'))

Calendar cal = Calendar.getInstance()

def year_now = cal.get(Calendar.YEAR)

def month_now = CustomKeywords.'my.test.kits.TestUtilities.transformMonth'(cal.get(Calendar.MONTH) + 1)

def date_now = cal.get(Calendar.DAY_OF_MONTH)

List diy_date = [year_now, month_now, date_now]

WebUI.switchToFrame(findTestObject('frames/主页面/Home'), 30)

List year = [By.cssSelector(GlobalVariable.time_year_1st)] // 年份下拉框

List month = [By.cssSelector(GlobalVariable.time_month_1st)]// 月份下拉框

List date = [By.cssSelector(GlobalVariable.time_date_1st)] // 日期下拉框

String search_date1 = CustomKeywords.'my.test.kits.TestUtilities.select_date_method'(year, month, date, diy_date) //  选择当前时间

WebUI.switchToDefaultContent()

WebUI.click(findTestObject('首页/门店数据/门店情况/查询按钮'))

WebUI.delay(1)

WebUI.switchToFrame(findTestObject('frames/主页面/Home'), 30)

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
}

@TearDown(skipped = false)
void TearDown() {
    Thread.sleep(2000)

    WebUI.closeBrowser()
}

