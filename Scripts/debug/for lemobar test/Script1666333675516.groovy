import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
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

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.SetUp
import com.kms.katalon.core.annotation.TearDown
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable


WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl('http://lmb-web.test.lemobar.cn/admin/index.html' // http://web.test.lemobar.cn/admin/index.html#/
    )

// http://web.2ndtest.lemobar.cn/admin/index.html
WebDriver driver = DriverFactory.getWebDriver()

driver.findElement(By.cssSelector('[placeholder="账号"]')).sendKeys('wyj')

driver.findElement(By.cssSelector('[placeholder="密码"]')).sendKeys('wengyj123456')

driver.findElement(By.cssSelector('[placeholder="验证码"]')).sendKeys('010203')

driver.findElement(By.cssSelector('.el-form-item__content .el-button')).click()

Thread.sleep(10000)

driver.findElement(By.cssSelector('.el-icon-ico-stats-bars')).click()

driver.findElement(By.cssSelector('.is-opened [role="menuitem"]:nth-child(2)')).click()

while(true){
	List year = [By.cssSelector('.el-year-table .available a.cell')]
	
	List month = [By.cssSelector('.el-month-table tr td a.cell')]
	
	List date = [By.cssSelector('.el-picker-panel__content .el-date-table__row td span')]
	
	String search_date = CustomKeywords.'my.test.kits.TestUtilities.select_date_method'(year, month, date)
	
	driver.findElements(By.cssSelector('[readonly="readonly"]'))[1].click()
	
	List<WebElement> pay_type = driver.findElements(By.cssSelector('[x-placement="bottom-start"] .el-select-dropdown__item span'))
	
	search_type = '微信+余额+现金券'
	
	for (int i=0; i<pay_type.size(); i++) {
		if (search_type.equals(pay_type[i].text)) {
			pay_type[i].click()
		}
	}
	
	driver.findElement(By.cssSelector('.el-icon-search')).click()
	
	try {
		no_data = driver.findElement(By.cssSelector('.el-table__empty-text')).text
	} catch(org.openqa.selenium.NoSuchElementException e) {
			System.out.print('找到了：' + search_date)
			break
	}
}
		

Thread.sleep(2000)

WebUI.closeBrowser()


