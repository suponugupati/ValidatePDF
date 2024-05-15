//package com.qa.util;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.safari.SafariDriver;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//
//public class DriverFactory {
//
//	public WebDriver driver;
//
//	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
//
//	/**
//	 * This method is used to initialize the thradlocal driver on the basis of given
//	 * browser
//	 *
//	 * @param browser
//	 * @return this will return tldriver.
//	 */
//	public WebDriver init_driver(String browser) {
//
//
//		System.out.println("browser value is: " + browser);
//
//		if (browser.equals("chrome")) {
//			WebDriverManager.chromedriver().setup();
//			tlDriver.set(new ChromeDriver());
//		} else if (browser.equals("firefox")) {
//			WebDriverManager.firefoxdriver().setup();
//			tlDriver.set(new FirefoxDriver());
//		} else if (browser.equals("safari")) {
//			tlDriver.set(new SafariDriver());
//		} else {
//
//			System.out.println("Please pass the correct browser value: " + browser);
//		}
//
//
//		getDriver().manage().deleteAllCookies();
//		getDriver().manage().window().maximize();
//
//		// Set the driver instance in LocalDriverManager
//
//		return getDriver();
//
//	}
//
//	/**
//	 * this is used to get the driver with ThreadLocal
//	 *
//	 * @return
//	 */
//	public static synchronized WebDriver getDriver() {
//		return tlDriver.get();
//	}
//}

package helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	public static  WebDriver getDriver() {
		WebDriver chromeDriver;
		
		//System.setProperty("webdriver.chrome.driver","/usr/bin/google-chrome");
		ChromeOptions chromeOptions=new ChromeOptions();
		chromeOptions.addArguments("--remote-allow-origins=*");
		chromeOptions.addArguments("headless");
		chromeDriver=WebDriverManager.chromedriver().capabilities(chromeOptions).create();
		//chromeDriver=new ChromeDriver(chromeOptions);
		//chromeDriver.manage().deleteAllCookies();
		//chromeDriver.manage().window().maximize();
		return chromeDriver;
	}

	/*public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}*/

}

