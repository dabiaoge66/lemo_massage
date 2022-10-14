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

WebUI.delay(2)

driver = DriverFactory.getWebDriver()

WebUI.switchToFrame(findTestObject('frames/主页面/商户管理-商户管理'), 30)

merchants = driver.findElements(By.cssSelector(GlobalVariable.merchant_names_ele))

for (int i = 0; i < merchants.size(); i++) {
    if (merchants[i].text.equals('UItestData_merchantName')) {
        System.out.println(merchants[i].text)

        (merchants[i]).click()

    } else {
        System.out.println(merchants)
    }
}

WebUI.switchToDefaultContent()

WebUI.click(findTestObject('商户管理/商户管理/编辑按钮'))
	
WebUI.delay(2)

WebUI.click(findTestObject('商户管理/商户管理/弹窗元素/确定按钮'))

WebUI.delay(1)

String expect = WebUI.getText(findTestObject('公共元素/弹框提示'))

System.out.println('弹框提示如下：' + expect)

assert expect.equals('修改商家成功')

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

