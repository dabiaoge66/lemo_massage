import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.By as By
import com.kms.katalon.core.annotation.SetUp as SetUp
import com.kms.katalon.core.annotation.TearDown as TearDown
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

// 门店情况按周查询
WebUI.click(findTestObject('首页/门店数据/门店情况/查询时间(类型)-下拉框'))

WebUI.click(findTestObject('首页/公共元素/查询时间(类型)-周1st'))

WebUI.click(findTestObject('首页/门店数据/门店情况/查询时间(时间)-下拉框'))

WebUI.click(findTestObject('首页/公共元素/时间-年份入口1st'))

driver = DriverFactory.getWebDriver()

WebUI.switchToFrame(findTestObject('frames/主页面/首页-门店数据'), 30)

// 年份下拉框
List year = [By.cssSelector(GlobalVariable.time_year_1st)]

// 月份下拉框
List month = [By.cssSelector(GlobalVariable.time_month_1st)]

// 日期下拉框
List date = [By.cssSelector(GlobalVariable.time_date_1st)]

// 选择全局变量中的search_time
String search_date1 = CustomKeywords.'my.test.kits.TestUtilities.select_date_method'(year, month, date)

WebUI.click(findTestObject('首页/门店数据/门店情况/查询按钮'))

WebUI.switchToFrame(findTestObject('frames/主页面/首页-门店数据'), 30)

// 查询结果
List compare_date1 = [[By.cssSelector(GlobalVariable.store_data_ele)], '其他查询',
	 By.className(GlobalVariable.empty_text), [false]]  //  [index=3]-字段是否在列表存在(boolean),空值和逆向不用传

// 总计
List store_total = [By.cssSelector(GlobalVariable.store_total_ele)]

// 下一页
List store_next = [By.xpath(GlobalVariable.store_next_page)]

int store_page_size = 10 // 每页显示条数

// 断言查询结果
CustomKeywords.'my.test.kits.TestUtilities.compare_result'(store_total, store_next, store_page_size, search_date1, compare_date1)

// 技师情况按周查询
WebUI.delay(1)

WebUI.scrollToPosition(0, 1)

WebUI.scrollToElement(findTestObject('首页/门店数据/技师情况/查询时间(类型)-下拉框'), 30)

WebUI.click(findTestObject('首页/门店数据/技师情况/查询时间(类型)-下拉框'))

WebUI.click(findTestObject('首页/公共元素/查询时间(类型)-周2nd'))

WebUI.click(findTestObject('首页/门店数据/技师情况/查询时间(时间)-下拉框'))

WebUI.click(findTestObject('首页/公共元素/时间-年份入口2nd'))

WebUI.switchToFrame(findTestObject('frames/主页面/首页-门店数据'), 30)

// 年份下拉框
List year2 = [By.cssSelector(GlobalVariable.time_year_2nd)]

// 月份下拉框
List month2 = [By.cssSelector(GlobalVariable.time_month_2nd)]

// 日期下拉框
List date2 = [By.cssSelector(GlobalVariable.time_date_2nd)]

// 选择全局变量中的search_time
search_date2 = CustomKeywords.'my.test.kits.TestUtilities.select_date_method'(year2, month2, date2)

WebUI.click(findTestObject('首页/门店数据/技师情况/查询按钮'))

WebUI.switchToFrame(findTestObject('frames/主页面/首页-门店数据'), 30)

// 查询结果
List compare_date2 = [[By.cssSelector(GlobalVariable.technician_data_ele)], '其他查询',
	By.cssSelector(GlobalVariable.empty_text_tech), [false]]  //  [index=3]-字段是否在列表存在(boolean),空值逆向不用传]

// 总计
List tech_total = [By.cssSelector(GlobalVariable.technician_total_ele)]

// 下一页按钮
List next_btn = [By.xpath(GlobalVariable.technician_next_page)]

int tech_page_size = 10 // 每页显示条数

// 断言查询结果
CustomKeywords.'my.test.kits.TestUtilities.compare_result'(tech_total, next_btn, tech_page_size, search_date2, compare_date2)

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

