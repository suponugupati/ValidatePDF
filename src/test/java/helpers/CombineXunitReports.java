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

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class CombineXunitReports {

	public List<File> getAllXmlFiles(String rootDir) {
		List<File> xmlFiles = new ArrayList<>();
		File directory = new File(rootDir);

		if (!directory.exists()) {
			System.out.println("Directory " + rootDir + " does not exist. Creating now.");
			boolean dirCreated = directory.mkdirs();
			if (!dirCreated) {
				System.err.println("Failed to create directory: " + rootDir);
				return xmlFiles;
			}
		}

		File[] files = directory.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".xml");
			}
		});

		if (files != null) {
			for (File file : files) {
				xmlFiles.add(file);
			}
		}

		return xmlFiles;
	}
}
