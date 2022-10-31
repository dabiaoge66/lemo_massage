import java.text.SimpleDateFormat

//WebUI.openBrowser('')
//
//WebUI.navigateToUrl(GlobalVariable.url)
//
//WebDriver driver = DriverFactory.getWebDriver()
//
//driver.findElement(By.xpath('123456'))
//Calendar cal = Calendar.getInstance() //it return same time as new Date()
//
//def year = cal.get(Calendar.YEAR)
//
//def month = cal.get(Calendar.MONTH) + 1
//
//def date = cal.get(Calendar.DAY_OF_MONTH)
//
//throw new Exception('总计栏数据数不在0-9999区间内，或数据异常')
//String search_data = 'ForUiTest,UItestData_storeName,' + GlobalVariable.schedule_begin_time + '/' + 
//GlobalVariable.schedule_end_time + ',' + GlobalVariable.shift_search_data + ',管理员'
//
//String[] search_name = search_data.split(',')
//
//System.out.println(search_name[3].split('-')[0])

//String s = '2022-08-12 10:00:00'
//
//SimpleDateFormat time_test = new SimpleDateFormat("yyyy-MM-dd")
//
//Date test = time_test.parse(s)
//
//
//long ts2 = test.getTime()
//
//
//res2 = String.valueOf(ts2)
//
//System.out.println(ts2)

//for (int i=0;i<search_name.size();i++) {
//	System.out.println(search_name[i])
//}
//System.out.println(Integer.parseInt(date[1]))
//System.out.println(Integer.parseInt(date2[1]))
//System.out.println(Integer.parseInt(date[1]) == Integer.parseInt(date2[1]))
//System.out.println(Integer.parseInt(date[1]) < Integer.parseInt(date2[1]))
//System.out.println(Integer.parseInt(date[1]) > Integer.parseInt(date2[1]))

//System.out.print(year)
//
//System.out.println(month + 1)
//
//System.out.println(date)
//
//WebUI.verifyEqual(Exception.printStackTrace([]), NoSuchElementException.getSupportUrl())
//
//String time = ''
for (int year=2021; year<=2022; year++) {
	String data  = ''
	data += year + '-'
	for (int month=1; month<=12; month++) {
		data += month + '-'
		int size = 0
		switch(month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				size = 31
				break
			case 2:
				size = 28
				break
			case 4:
			case 6:
			case 9:
			case 11:
				size = 30
				break
		}
		for (int date=1; date<=size; date++) {
			data += date
			
			System.out.println(data)
			
			data = year + '-' + month + '-'
		}
		data = year + '-'
	}
}

