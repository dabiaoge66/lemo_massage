package my.test.kits
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.SimpleDateFormat

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.exception.WebElementNotFoundException
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable


class TestUtilities {
	/**
	 * Refresh browser
	 */
	@Keyword
	def refreshBrowser() {
		KeywordUtil.logInfo("Refreshing")
		WebDriver webDriver = DriverFactory.getWebDriver()
		webDriver.navigate().refresh()
		KeywordUtil.markPassed("Refresh successfully")
	}

	/**
	 * Click element
	 * @param to Katalon test object
	 */
	@Keyword
	def clickElement(TestObject to) {
		try {
			WebElement element = WebUiBuiltInKeywords.findWebElement(to);
			KeywordUtil.logInfo("Clicking element")
			element.click()
			KeywordUtil.markPassed("Element has been clicked")
		} catch (WebElementNotFoundException e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			KeywordUtil.markFailed("Fail to click on element")
		}
	}

	/**
	 * Get all rows of HTML table
	 * @param table Katalon test object represent for HTML table
	 * @param outerTagName outer tag name of TR tag, usually is TBODY
	 * @return All rows inside HTML table
	 */
	@Keyword
	def List<WebElement> getHtmlTableRows(TestObject table, String outerTagName) {
		WebElement mailList = WebUiBuiltInKeywords.findWebElement(table)
		List<WebElement> selectedRows = mailList.findElements(By.xpath("./" + outerTagName + "/tr"))
		return selectedRows
	}

	/**
	 *	 月份转换
	 */
	@Keyword
	//	enum Month{
	//		January,February,March,April,May,June,July,August,September,October,November,December
	//	}
	def transformMonth(int month) {
		String result = ''
		switch(month) {
			case 1:
				result = '一月'
				break
			case 2:
				result = '二月'
				break
			case 3:
				result = '三月'
				break
			case 4:
				result = '四月'
				break
			case 5:
				result = '五月'
				break
			case 6:
				result = '六月'
				break
			case 7:
				result = '七月'
				break
			case 8:
				result = '八月'
				break
			case 9:
				result = '九月'
				break
			case 10:
				result = '十月'
				break
			case 11:
				result = '十一月'
				break
			case 12:
				result = '十二月'
				break
		}
		return result
	}

	/**
	 *	用于提取重复代码
	 */
	@Keyword

	def is_error_search(int total_count, List compare_name, String search_name) {
		WebDriver driver = DriverFactory.getWebDriver()

		WebElement empty_tips =null

		if (total_count == 0) {
			try {
				empty_tips = driver.findElement(compare_name[2]) // 空数据查询结果
			} catch (org.openqa.selenium.NoSuchElementException f){
				throw new Exception('本次‘' + compare_name[1] + '’可能查询到了数据，或因为空数据的提示元素不存在；请检查！' + '\n' + f)
			}

			System.out.println('键入查询字段：' + search_name)

			System.out.println((('列表没有查询到符合条件的数据，并给出了提示：' + ' ‘') + empty_tips.text) + '’')

			assert empty_tips.text.contains('暂无') && empty_tips.text.contains('数据')
			return false
		} else {
			return true
		}
	}

	/**
	 *	用于日期比较
	 */
	@Keyword

	def date_compare_method(String search_date, String compare_date, String compare) {
		SimpleDateFormat time = compare_date.length() > 10 ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				: new SimpleDateFormat("yyyy-MM-dd")

		Date d1 = time.parse(search_date)

		Date d2 = time.parse(compare_date)

		long search_time = d1.getTime()

		long compare_time = d2.getTime()

		switch(compare) {
			case '开始':
				if (compare_time >= search_time) {
					return true
				} else {
					throw new Exception('列表显示开始日期：‘' + compare_date + '’小于查询开始日期：‘'+search_date + '’；查询结果有误！')
				}
				break
			case '结束':
				if (compare_time <= search_time) {
					return true
				} else {
					throw new Exception('列表显示开始日期：‘' + compare_date + '’大于查询开始日期：‘'+search_date + '’；查询结果有误！')
				}
				break
		}
		return false
	}
	/**
	 *	用于选择查询类型
	 */
	@Keyword
	// 查询方法
	def select_search_type(String search_type, int total_count, String search_name, List compare_name, int index, int num, String compare = null) {
		WebDriver driver = DriverFactory.getWebDriver()

		switch(search_type) {
			case '模糊查询':
				if (is_error_search(total_count, compare_name, search_name.split(',')[num])) {
					try {
						if (compare_name[3][num]) {
							boolean is_contains = driver.findElements(compare_name[0][num])[index].text.contains(search_name.split(',')[num])

							System.out.println('查询字段：' + driver.findElements(compare_name[0][num])[index].text)

							System.out.println('字段是否包含' + ' ‘' + search_name.split(',')[num] + '’ :' + is_contains)

							assert is_contains
						} else {
							driver.findElements(compare_name[0][num])[index].click()
						}
					} catch(org.openqa.selenium.NoSuchElementException a) {
						throw new Exception('本次查询方式：‘' + search_type + '’没有查询到数据，请检查是否造了数据、查询条件是否符合；或更新元素位置吧：' + '\n' + c)
					}
				}
				break
			case '精确查询':
				if (is_error_search(total_count, compare_name, search_name.split(',')[num])) {
					try {
						if (compare_name[3][num]) {
							boolean is_equals = driver.findElements(compare_name[0][num])[index].text.equals(search_name.split(',')[num])

							System.out.println('查询字段' + ' ‘' + search_name.split(',')[num] + '’' + '与预期名称' + ' ‘' +
									driver.findElements(compare_name[0][num])[index].text +'’' + ' 是否一致：' + is_equals)

							assert is_equals
						} else {
							driver.findElements(compare_name[0][num])[index].click()
						}
					} catch(org.openqa.selenium.NoSuchElementException b) {
						throw new Exception('本次查询方式：‘' + search_type + '’没有查询到数据，请检查是否造了数据、查询条件是否符合；或更新元素位置吧：' + '\n' + b)
					}
				}
				break
			case '空值查询':
				if (is_error_search(total_count, compare_name, search_name.split(',')[num])) {
					try {
						System.out.println('查询字段：' + driver.findElements(compare_name[0][num])[index].text)
					} catch(org.openqa.selenium.NoSuchElementException c) {
						throw new Exception('本次查询方式：‘' + search_type + '’没有查询到数据，请检查是否造了数据、查询条件是否符合；或更新元素位置吧：' + '\n' + c)
					}
				}
				break

			case '逆向查询':
				if (is_error_search(total_count, compare_name, search_name.split(',')[num])) {
					throw new Exception('本次查询方式为：‘' + search_type + '’；请检查查询方式是否错误！')
				}
				break
			case '其他查询':  // 下拉框查询 传递参数：[index=2]-空提示元素，[index=3]-字段是否在列表存在(boolean)))
				if (is_error_search(total_count, compare_name, search_name.split(',')[num])) {
					try {
						if (compare_name[3][num]) {
							boolean is_equals = driver.findElements(compare_name[0][num])[index].text.equals(search_name.split(',')[num])

							System.out.println('查询字段' + ' ‘' + search_name.split(',')[num] + '’' + '与预期' + ' ‘' +
									driver.findElements(compare_name[0][num])[index].text +'’' + ' 是否一致：' + is_equals)

							assert is_equals
						} else {
							driver.findElements(compare_name[0][num])[index].click()
						}
					} catch (org.openqa.selenium.NoSuchElementException e1) {
						throw new Exception('本次‘' + search_type + '’没有查询到数据，请检查是否造了数据、查询条件是否符合；或更新元素位置吧：' + '\n' + e1)
					}
				}
				break
			case '日期比较':
				if (is_error_search(total_count, compare_name, search_name.split(',')[num])) {  // 开始或结束区间仅填写一个，传其中一个；填写两个， serach需传两个
					try {
						if (compare_name[3][num]) {

							boolean is_right = false

							if (compare!=null) {
								String search_date = search_name.split(',')[num].split('/')[0]

								String compare_date = driver.findElements(compare_name[0][num])[index].text

								if (compare.equals('开始') || compare.equals('结束')) {

									assert date_compare_method(search_date, compare_date, compare)

									is_right = true
								} else if (compare.equals('区间')) {  // search开始时间与结束时间用逗号分割,compare用list
									String search_end_date = search_name.split(',')[num].split('/')[1]
									//  只要比较开始时间就好了；结束时间必小等于开始时间
									assert date_compare_method(search_date, compare_date, '开始')

									assert date_compare_method(search_end_date, compare_date, '结束')

									is_right = true
								} else {
									throw new Exception('日期类型有误！（填写：‘开始’、‘结束’或‘区间’）')
								}
							} else {
								throw new Exception('日期类型没有传！（填写：‘开始’、‘结束’或‘区间’）')
							}

							if (compare.equals('开始')){
								System.out.println('查询结果：' + ' ‘' + driver.findElements(compare_name[0][num])[index].text
										+ '’' + '是否在查询时间：' + ' ‘' + search_name.split(',')[num].split('/')[0] +'’' + ' 之后：' + is_right)
							} else if (compare.equals('结束')) {
								System.out.println('查询结果：' + ' ‘' + driver.findElements(compare_name[0][num])[index].text
										+ '’' + '是否在查询时间：' + ' ‘' + search_name.split(',')[num].split('/')[0] +'’' + ' 之前：' + is_right)
							} else {
								System.out.println('查询结果：' + ' ‘' + driver.findElements(compare_name[0][num])[index].text
										+ '’' + '是否在查询时间：' + ' ‘' + search_name.split(',')[num].split('/')[0]
										+ ',' + search_name.split(',')[num].split('/')[1]
										+'’' + ' 区间内：' + is_right)
							}

						} else {
							driver.findElements(compare_name[0][num])[index].click()
						}
					} catch (org.openqa.selenium.NoSuchElementException e1) {
						throw new Exception('本次‘' + search_type + '’没有查询到数据，请检查是否造了数据、查询条件是否符合；或更新元素位置吧：' + '\n' + e1)
					}
				}
				break
		}
	}
	/**
	 *	用于日期选择
	 */
	@Keyword
	// 查询方法
	def select_date_method(List year_ele, List month_ele, List date_ele = null, List diy_date = null) {

		WebDriver driver = DriverFactory.getWebDriver()

		List<WebElement> year = null

		List<WebElement> month = null

		List<WebElement> date = null

		try {
			year = driver.findElements(year_ele[0])

			month = driver.findElements(month_ele[0])

			if (date_ele != null) {
				date = driver.findElements(date_ele[0])
			}
		} catch(org.openqa.selenium.NoSuchElementException e) {
			throw new Exception('年份、月份或日期元素找不到！')
		}

		int first_year = Integer.parseInt(year[0].text)

		int last_year = Integer.parseInt(year[(year.size() - 1)].text)

		String click_year = ''

		String click_month = ''

		String click_date = ''
		// 遍历年份
		int search_year = GlobalVariable.search_year

		String search_month = GlobalVariable.search_month

		int search_date = GlobalVariable.search_date

		if (diy_date != null) {
			search_year = diy_date[0]

			search_month = diy_date[1]

			search_date = diy_date[2]
		}

		for (int i = 0; i < year.size(); i++) {
			if (search_year < first_year) {
				WebUI.click(findTestObject('首页/公共元素/年份选择-前一年1st'))

				first_year = Integer.parseInt(year[0].text)

				i--
			} else if (search_year > last_year) {
				WebUI.click(findTestObject('首页/公共元素/年份选择-后一年1st'))

				first_year = Integer.parseInt(year[0].text)

				i--
			} else if (search_year == Integer.parseInt(year[i].text)) {
				click_year = year[i].text

				System.out.println('点击年份：' + year[i].text)

				(year[i]).click()
				// 遍历月份
				for (int j = 0; j < month.size(); j++) {
					if (search_month.equals(month[j].text)) {
						click_month = month[i].text

						System.out.println('点击月份：' + month[j].text)

						(month[j]).click()
						// 遍历日期
						if (date != null) {
							for (int k = 0; k < date.size(); k++) {
								if (search_date == Integer.parseInt(date[k].text)) {
									click_date = date[i].text

									System.out.println('点击日期：' + date[k].text)

									(date[k]).click()
									break
								} else {
									continue
								}
							}
						}
						break
					} else {
						continue
					}
				}

				break
			} else {
				continue
			}
		}

		WebUI.switchToDefaultContent()
		return click_year + '年' + click_month + click_date + '日'
	}

	/**
	 *	 查询校验，对比查询数据数量，条件正确性
	 */
	@Keyword
	def compare_result(List total_ele, List next_ele, int page_size, String search_name, List compare_name, String compare = null) {

		WebDriver driver = DriverFactory.getWebDriver()

		WebElement total = null

		WebElement next_btn = null

		try {
			total = driver.findElement(total_ele[0])

			next_btn = driver.findElement(next_ele[0])

		} catch(org.openqa.selenium.NoSuchElementException d) {  //  需要往后走断言，避免元素不存在时走不到后续代码
			System.out.println('请确认数据已造好，且查询方式、条件正确；并且元素位置最新：')

			System.out.println(d)

			total = null

			next_btn = null
		}

		int total_count = 0
		//  转换总计栏显示的数据数（0-9999条）
		if (total != null && next_btn != null) {
			switch(total.text.length()) {
				case 5:
					total_count = Integer.parseInt(total.text[2])

					break
				case 6:
					total_count = Integer.parseInt(total.text[2]) * 10 + Integer.parseInt(total.text[3])

					break
				case 7:
					total_count = Integer.parseInt(total.text[2]) * 100 + Integer.parseInt(total.text[3]) * 10 + Integer.parseInt(total.text[4])

					break
				case 8:
					total_count = Integer.parseInt(total.text[2]) * 1000 + Integer.parseInt(total.text[3]) * 100 + Integer.parseInt(total.text[4]) * 10 + Integer.parseInt(total.text[5])

					break
				default:
					System.out.println('总计栏文本长度：' + total.text.length())

					throw new Exception('总计栏数据数不在0-9999区间内，或数据异常')
			}
		}

		int search_result_count = 0 // 比较总次数（列表实际查询到并显示的数据量）

		int search_count_1 = 0  // 遍历次数

		int search_count_2 = 0

		boolean flag = false  // 标记
		// 总计栏结果大等于每页显示数量，遍历每页数量次；总计栏结果小于每页显示数量，遍历总计栏结果次。
		if (total_count > page_size) {
			//  处理一下总计数是分页条数的倍数的情况
			search_count_1 = total_count % page_size == 0 ? total_count / page_size - 1 : total_count / page_size

			search_count_2 = page_size

			search_result_count = page_size

			flag = true
		}else if (total_count == 0) {
			search_count_2 =total_count + 1  // 必须执行一次走后续判断
		}else {
			search_count_1 = 1

			search_count_2 = total_count
		}
		for (int i = 0; i <= search_count_1; i++) {
			if (i == search_count_1 - 1) {  //  避免多页数据下最后一页遍历中产生索引越界
				//  处理一下总计数是分页条数的倍数的情况
				search_count_2 = total_count % page_size == 0 ? page_size : total_count % page_size
			}

			for (int j = 0; j < search_count_2; j++) {
				String search_types = compare_name[1]

				String[] search_type = search_types.split(',')

				for (int k=0; k<search_type.length; k++) {
					if (compare!=null) {
						select_search_type(search_type[k], total_count, search_name, compare_name, j, k, compare)
					} else {
						select_search_type(search_type[k], total_count, search_name, compare_name, j, k)
					}
				}
			}
			if (i == search_count_1) {  // 避免最后一页遍历中再次点击下一页
				break
			} else if (flag) {  // 多页数据
				driver.findElement(next_ele[0]).click()

				WebUI.delay(2)

				search_result_count += driver.findElements(compare_name[0][0]).size()
			}else {
				search_result_count += driver.findElements(compare_name[0][0]).size()

				i++  // 避免单页数据场景多余的结果校验
			}
		}

		System.out.println('列表查询数据数：' + search_result_count)
		System.out.println('总计栏显示数量：' + total_count)
		System.out.println('每一页显示数量：' + page_size)

		WebUI.switchToDefaultContent()
		assert search_result_count == total_count
	}
}