import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.SetUp
import com.kms.katalon.core.annotation.TearDown
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

WebDriver driver = DriverFactory.getWebDriver()

String search_data = 'ForUiTest,UItestData_storeName,' + GlobalVariable.schedule_begin_time + '/' + 
GlobalVariable.schedule_end_time + ',' + GlobalVariable.shift_search_data + ',管理员'

String[] search_name = search_data.split(',')

List compare_names = [[By.cssSelector(GlobalVariable.schedule_names_ele), By.cssSelector(GlobalVariable.schedule_sids_ele),
	 By.cssSelector(GlobalVariable.schedule_start_date), By.cssSelector(GlobalVariable.schedule_shifts_ele), 
	 By.cssSelector(GlobalVariable.schedule_entry_ele)], '精确查询,其他查询,日期比较,其他查询,精确查询',
	By.className(GlobalVariable.empty_tips), [true, true, true, true, true]]  //  [index=3]-字段是否在列表存在(boolean)]

WebUI.sendKeys(findTestObject('产商品管理/排班管理/技师姓名搜索框'), search_name[0])

WebUI.delay(1)

WebUI.sendKeys(findTestObject('产商品管理/排班管理/门店名称搜索框'), search_name[1])

WebUI.click(findTestObject('产商品管理/排班管理/门店名称搜索框'))

List<WebElement> sid_box_values = driver.findElements(By.cssSelector(GlobalVariable.sid_box_values))

for (int i = 0; i < sid_box_values.size(); i++) {
	if (search_name[1].equals(sid_box_values[i].text)) {
		sid_box_values[i].click()
	}
}

WebUI.delay(1)

WebUI.sendKeys(findTestObject('产商品管理/排班管理/开始日期-开始时间'), search_name[2].split('/')[0])

WebUI.sendKeys(findTestObject('产商品管理/排班管理/开始日期-结束时间'), search_name[2].split('/')[1])

WebUI.delay(1)

WebUI.sendKeys(findTestObject('产商品管理/排班管理/班次搜索框'), search_name[3])

WebUI.click(findTestObject('产商品管理/排班管理/班次搜索框'))

List<WebElement> shift_box_values = driver.findElements(By.cssSelector(GlobalVariable.shift_box_values))

for (int i = 0; i < shift_box_values.size(); i++) {
	if (search_name[3].equals(shift_box_values[i].text)) {
		shift_box_values[i].click()
	}
}

WebUI.delay(1)

WebUI.sendKeys(findTestObject('产商品管理/排班管理/录入人搜索框'), search_name[4])

WebUI.click(findTestObject('产商品管理/排班管理/快速查询按钮'))

WebUI.delay(1)

WebUI.switchToFrame(findTestObject('frames/主页面/产商品管理-排班管理'), 30)

List total = [By.className(GlobalVariable.total_ele)] // 总计

List next = [By.className(GlobalVariable.next_page_ele)] // 下一页

int page_size = 15 // 每页显示条数

CustomKeywords.'my.test.kits.TestUtilities.compare_result'(total, next, page_size, search_data, compare_names, '区间') // 断言查询结果

@SetUp(skipped = false)
void setUp() {
    WebUI.openBrowser('')

    WebUI.maximizeWindow()

    WebUI.navigateToUrl(GlobalVariable.url)

    WebUI.sendKeys(findTestObject('登录页/登录名称'), GlobalVariable.username)

    WebUI.sendKeys(findTestObject('登录页/登录密码'), GlobalVariable.password)

    WebUI.click(findTestObject('登录页/登录按钮'))

    WebUI.click(findTestObject('产商品管理/导航栏/产商品管理'))

    WebUI.click(findTestObject('产商品管理/导航栏/排班管理'))
}

@TearDown(skipped = false)
void TearDown() {
    Thread.sleep(2000)

    WebUI.closeBrowser()
}

