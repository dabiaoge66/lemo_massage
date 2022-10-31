import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.By as By
import com.kms.katalon.core.annotation.SetUp as SetUp
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.annotation.TearDown as TearDown
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint

String search_name = GlobalVariable.recharge_vague_guid

List compare_names = [[By.cssSelector(GlobalVariable.recharge_guid_ele)],'模糊查询',
	By.className(GlobalVariable.empty_tips), [true]]  //  [index=3]-字段是否在列表存在(boolean),空值和逆向不用传]

WebUI.sendKeys(findTestObject('订单中心/充值订单/充值订单号搜索框'), search_name)

WebUI.click(findTestObject('订单中心/充值订单/快速查询按钮'))

WebUI.delay(1)

WebUI.switchToFrame(findTestObject('frames/主页面/订单中心-充值订单'), 30)

List total = [By.className(GlobalVariable.total_ele)] // 总计

List next = [By.className(GlobalVariable.next_page_ele)] // 下一页

int page_size = 15 // 每页显示条数

CustomKeywords.'my.test.kits.TestUtilities.compare_result'(total, next, page_size, search_name, compare_names) // 断言查询结果

@SetUp(skipped = false)
void setUp() {
    WebUI.openBrowser('')

    WebUI.maximizeWindow()

    WebUI.navigateToUrl(GlobalVariable.url)

	WebUI.refresh()

    WebUI.sendKeys(findTestObject('登录页/登录名称'), GlobalVariable.username)

    WebUI.sendKeys(findTestObject('登录页/登录密码'), GlobalVariable.password)

    WebUI.click(findTestObject('登录页/登录按钮'))

    WebUI.click(findTestObject('订单中心/导航栏/订单中心'))

    WebUI.click(findTestObject('订单中心/导航栏/充值订单'))
}

@TearDown(skipped = false)
void TearDown() {
    Thread.sleep(2000)

    WebUI.closeBrowser()
}

