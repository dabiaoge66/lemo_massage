import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.By as By
import com.kms.katalon.core.annotation.SetUp as SetUp
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.annotation.TearDown as TearDown

WebUI.click(findTestObject('产商品管理/排班管理/新增排班按钮'))

WebUI.click(findTestObject('产商品管理/排班管理/弹窗元素/选择门店下拉框')) 

//driver = DriverFactory.getWebDriver()

//String expect = WebUI.getText(findTestObject('公共元素/弹框提示'))

//System.out.println('弹框提示如下：' + expect)

//assert expect.equals('新增商家成功')

WebUI.click(findTestObject('产商品管理/排班管理/弹窗元素/选择门店-值(UItest)'))

WebUI.sendKeys(findTestObject('产商品管理/排班管理/弹窗元素/技师姓名下拉框'), GlobalVariable.exact_tech_name)

WebUI.click(findTestObject('产商品管理/排班管理/弹窗元素/技师姓名-值(UItest)'))

WebUI.click(findTestObject('产商品管理/排班管理/弹窗元素/备注栏'))

WebUI.click(findTestObject('产商品管理/排班管理/弹窗元素/班次下拉框'))

WebUI.click(findTestObject('产商品管理/排班管理/弹窗元素/班次-值(早班)'))

//WebUI.sendKeys(findTestObject('产商品管理/排班管理/弹窗元素/开始时间下拉框'), GlobalVariable.scheduleBeginTime)

//WebUI.sendKeys(findTestObject('产商品管理/排班管理/弹窗元素/结束时间下拉框'), GlobalVariable.scheduleEndTime)

WebUI.click(findTestObject('产商品管理/排班管理/弹窗元素/开始日期下拉框'), FailureHandling.STOP_ON_FAILURE)

driver = DriverFactory.getWebDriver()

WebUI.switchToFrame(findTestObject('frames/弹出窗/产商品管理-排班管理/新增修改弹窗'), 30)

driver.findElement(By.cssSelector(GlobalVariable.scheduleYearEntrance)).click()  // 年份入口

List begin_year = driver.findElements(By.cssSelector(GlobalVariable.scheduleYear))  // 年份列表

List begin_month = driver.findElements(By.cssSelector(GlobalVariable.scheduleMonth))  // 月份列表

List begin_date = driver.findElements(By.cssSelector(GlobalVariable.scheduleDate))  // 日期列表

Calendar cal = Calendar.getInstance();//it return same time as new Date()

GlobalVariable.search_year = cal.get(Calendar.YEAR)

GlobalVariable.search_month = CustomKeywords.'my.test.kits.TestUtilities.transformMonth'(cal.get(Calendar.MONTH) + 1)

GlobalVariable.search_date = cal.get(Calendar.DAY_OF_MONTH) + 1

CustomKeywords.'my.test.kits.TestUtilities.select_date_method'(begin_year, begin_month, begin_date)

WebUI.click(findTestObject('产商品管理/排班管理/弹窗元素/结束日期下拉框'), FailureHandling.STOP_ON_FAILURE)

WebUI.switchToFrame(findTestObject('frames/弹出窗/产商品管理-排班管理/新增修改弹窗'), 30)

driver.findElement(By.cssSelector(GlobalVariable.scheduleYearEntrance)).click()

List end_year = driver.findElements(By.cssSelector(GlobalVariable.scheduleYear))  // 年份列表

List end_month = driver.findElements(By.cssSelector(GlobalVariable.scheduleMonth))  // 月份列表

List end_date = driver.findElements(By.cssSelector(GlobalVariable.scheduleDate))  // 日期列表

GlobalVariable.search_year = cal.get(Calendar.YEAR)

GlobalVariable.search_month = CustomKeywords.'my.test.kits.TestUtilities.transformMonth'(cal.get(Calendar.MONTH) + 1)

GlobalVariable.search_date = cal.get(Calendar.DAY_OF_MONTH) + 1

CustomKeywords.'my.test.kits.TestUtilities.select_date_method'(end_year, end_month, end_date)

WebUI.sendKeys(findTestObject('产商品管理/排班管理/弹窗元素/备注栏'), 'UItest_remarks')

WebUI.click(findTestObject('产商品管理/排班管理/弹窗元素/确定按钮'))

WebUI.delay(1)

String expect = WebUI.getText(findTestObject('公共元素/弹框提示'))

System.out.println('弹框提示如下：' + expect)

assert expect.equals('新增排班成功！')

@SetUp(skipped = false)
void setUp() {
    WebUI.openBrowser('')

    WebUI.maximizeWindow()

    WebUI.navigateToUrl(GlobalVariable.url)
	
	WebUI.refresh()

	String username = GlobalVariable.username
	String password = GlobalVariable.password
	
    WebUI.sendKeys(findTestObject('登录页/登录名称'), username)

    WebUI.sendKeys(findTestObject('登录页/登录密码'), password)

    WebUI.click(findTestObject('登录页/登录按钮'))

    WebUI.click(findTestObject('产商品管理/导航栏/产商品管理'))

    WebUI.click(findTestObject('产商品管理/导航栏/排班管理'))
}

@TearDown(skipped = false)
void TearDown() {
    Thread.sleep(2000)

    WebUI.closeBrowser()
}

