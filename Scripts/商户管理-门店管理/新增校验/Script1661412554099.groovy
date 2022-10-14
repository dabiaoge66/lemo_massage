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
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement

WebUI.click(findTestObject('商户管理/门店管理/新增按钮'))

WebUI.click(findTestObject('商户管理/门店管理/弹窗元素/所属商户-下拉框'))

WebUI.click(findTestObject('商户管理/门店管理/弹窗元素/所属商户-值(UItest)'))

WebUI.sendKeys(findTestObject('商户管理/门店管理/弹窗元素/门店名称'), 'UItestData_storeName')

WebUI.click(findTestObject('商户管理/门店管理/弹窗元素/所属城市-下拉框'))

WebUI.click(findTestObject('商户管理/门店管理/弹窗元素/所属城市-值(index-0)'))

WebUI.sendKeys(findTestObject('商户管理/门店管理/弹窗元素/门店地址'), 'UItestData_address')

WebUI.sendKeys(findTestObject('商户管理/门店管理/弹窗元素/地点搜索-下拉框'), '福州')

WebUI.click(findTestObject('商户管理/门店管理/弹窗元素/地点搜索-值'))

WebUI.sendKeys(findTestObject('商户管理/门店管理/弹窗元素/负责人'), 'UItestData_headName')

WebUI.sendKeys(findTestObject('商户管理/门店管理/弹窗元素/联系电话'), '19900001111')

WebUI.sendKeys(findTestObject('商户管理/门店管理/弹窗元素/营业时间-起始时间'), '09:00')

WebUI.sendKeys(findTestObject('商户管理/门店管理/弹窗元素/营业时间-起始时间'), Keys.chord(Keys.ENTER))

WebUI.sendKeys(findTestObject('商户管理/门店管理/弹窗元素/营业时间-结束时间'), '18:00')

WebUI.sendKeys(findTestObject('商户管理/门店管理/弹窗元素/营业时间-结束时间'), Keys.chord(Keys.ENTER))

WebUI.sendKeys(findTestObject('商户管理/门店管理/弹窗元素/容纳人数'), '100')

WebUI.click(findTestObject('商户管理/门店管理/弹窗元素/可用服务项目(index-0)'))

WebUI.sendKeys(findTestObject('商户管理/门店管理/弹窗元素/门店简介'), 'UItestData_intron')

WebUI.sendKeys(findTestObject('商户管理/门店管理/弹窗元素/上传门店头图'), GlobalVariable.headImg)

WebUI.sendKeys(findTestObject('商户管理/门店管理/弹窗元素/上传门店banner'), GlobalVariable.banner)

WebUI.click(findTestObject('商户管理/门店管理/弹窗元素/状态-下拉框'))

WebUI.click(findTestObject('商户管理/门店管理/弹窗元素/状态-值(禁用)'))

WebUI.click(findTestObject('商户管理/门店管理/弹窗元素/确定按钮'))

WebUI.delay(1)

String expect = WebUI.getText(findTestObject('公共元素/弹框提示'))

System.out.println('弹框提示如下：' + expect)

assert expect.equals('新增门店成功！')

@SetUp(skipped = false)
void setUp() {
    WebUI.openBrowser('')

    WebUI.maximizeWindow()

    WebUI.navigateToUrl(GlobalVariable.url)

    WebUI.sendKeys(findTestObject('登录页/登录名称'), GlobalVariable.username)

    WebUI.sendKeys(findTestObject('登录页/登录密码'), GlobalVariable.password)

    WebUI.click(findTestObject('登录页/登录按钮'))

    WebUI.click(findTestObject('商户管理/导航栏/商户管理'))

    WebUI.click(findTestObject('商户管理/导航栏/商户管理-门店管理'))
}

@TearDown(skipped = false)
void TearDown() {
    Thread.sleep(2000)

    WebUI.closeBrowser()
}

