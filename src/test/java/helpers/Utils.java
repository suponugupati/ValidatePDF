package helpers;

import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.function.Function;



public class Utils {
	public static FileInputStream propFile = null;

	/*public void waitForVisibilityOfElement(WebDriver driver, By by) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}*/
	
	public void waitForVisibilityOfElement(By by) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitForVisibilityOfElement( WebElement element) throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 20);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	
	public void delay(String dataValue){
		try {
			Thread.sleep(Long.parseLong(Integer.toString(((int)Float.parseFloat(dataValue)))));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public boolean verifyElementNotPresent(By element, String value){
		boolean flag;
		try{
			List<WebElement> elements = DriverFactory.getDriver().findElements(element);
			if(elements.size()==0)
				flag= true;
			else
				flag= false;
		}
		catch(Exception e){
			flag=true;
			if (flag==true) {
				ExecutionHelper.getLogger().log(LogStatus.PASS,value+" not present");
			} else if (flag==false) {
				ExecutionHelper.getLogger().log(LogStatus.FAIL,value +" present");
			}
		}
		return flag;
	}

//	public static String randomEmailAddress(Integer length) {
//		String email = "bobsautomationuser_"+Props.getProp("site").toLowerCase()+"_"+randomString(length) + "@test.com";
//		//String email = "bobsautomation_july15_"+length + "@test.com";
//		return email.toLowerCase();
//	}
	private static String randomAlphanumeric(Integer length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}
	private static String randomString(Integer length) {
		return RandomStringUtils.random(length, true, false);
	}
	public static  int randomNumber(Integer length){
		Random rand = new Random();
		return rand.nextInt(length);

	}

	public static int getXpathCount(List<WebElement> element)

	{
		int xpathCount=element.size();

		return xpathCount;
	}



	public static void scrollWithOffset(WebElement element, WebDriver driver, int x, int y)
	{


		try {
			//WebDriver driver= null;

			String code="window.scroll("+(element.getLocation().x+x)+"," +
					(element.getLocation().y+y)+");";
			((JavascriptExecutor)DriverFactory.getDriver()).executeScript(code, element, x, y);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void clickOnWebElement(WebElement element, WebDriver driver) {
		String getElementLocator = null;
		try {
			element.click();

		} catch (Exception e) {
			try {
				String getElementLocatorTemp = element.toString().split("->")[1];
				getElementLocator = getElementLocatorTemp.substring(0, (getElementLocatorTemp.length() - 1));
				JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
				js.executeScript("arguments[0].click()", element);

			} catch (Exception e2) {

			}
		}

	}

	public static void scroll(WebDriver driver, int x, int y)
	{


		try {
			//WebDriver driver= null;

			String code="window.scroll("+(x)+"," +
					(y)+");";
			((JavascriptExecutor)DriverFactory.getDriver()).executeScript(code, x, y);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isExistElement(WebElement element)
	{
		try {
			element.isDisplayed();
		}
		catch(NoSuchElementException e)
		{
			return false;
		}
		return true;
	}


	/*
	 * Validating the text matching with the expected text
	 *
	 * @param actual
	 *            Text
	 * @param expected
	 *            Text
	 */
	public boolean verifyTextMatches(final String actualtext, final String expectedText) throws Throwable {
		boolean flag = false;
		try {
			if (actualtext.equalsIgnoreCase(expectedText)) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (final Exception e) {
			flag = false;
		} finally {
			if (flag == false) {
				ExecutionHelper.getLogger().log(LogStatus.FAIL,
						"Given Actual text  " + actualtext + " doesn't matches with the expected text  " + expectedText);
			} else if (flag == true) {
				ExecutionHelper.getLogger().log(LogStatus.PASS,
						"Given Actual text  " + actualtext + " matches with the expected text  " + expectedText);
			}
		}
		return flag;
	}

		/*
		 * Verifying the text contains with the expected text value
		 *
		 * @param actual
		 *            Text
		 * @param expected
		 *            Text
		 */
	public boolean verifyTextContains(final String actualtext, final String expectedText) throws Throwable {
		boolean flag = false;
		try {
			if (actualtext.contains(expectedText)) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (final Exception e) {
			flag = false;
		} finally {
			if (flag == false) {
				ExecutionHelper.getLogger().log(LogStatus.FAIL,
						"Given Actual text  " +"\"" +actualtext +"\""+ " doesn't contains with the expected text  "+"\"" + expectedText+"\"");
			} else if (flag == true) {
				ExecutionHelper.getLogger().log(LogStatus.PASS,
						"Given Actual text  "+"\"" + actualtext +"\""+ " contains with the expected text  "+"\"" + expectedText+"\"");
			}
		}
		return flag;
	}

	/*
	 * Verifying the texts contains are not matching
	 *
	 * @param actual
	 *            Text
	 * @param expected
	 *            Text
	 */
	public boolean verifyTextContainsNotMatching(final String actualtext, final String expectedText) throws Throwable {
		boolean flag = false;
		try {
			if (!actualtext.contains(expectedText)) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (final Exception e) {
			flag = false;
		} finally {
			if (flag == false) {
				ExecutionHelper.getLogger().log(LogStatus.FAIL,
						"Given text  " +"\"" +actualtext +"\""+ " is matching with the text  "+"\"" + expectedText+"\"");
			} else if (flag == true) {
				ExecutionHelper.getLogger().log(LogStatus.PASS,
						"Given text  "+"\"" + actualtext +"\""+ " is not matching with the text  "+"\"" + expectedText+"\"");
			}
		}
		return flag;
	}

	public boolean verifyTextNotMatching(final String actualtext, final String expectedText) throws Throwable {
		boolean flag = false;
		try {
			if (!actualtext.equals(expectedText)) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (final Exception e) {
			flag = false;
		} finally {
			if (flag == false) {
				ExecutionHelper.getLogger().log(LogStatus.FAIL,
						"Given text  " +"\"" +actualtext +"\""+ " is matching with the text  "+"\"" + expectedText+"\"");
			} else if (flag == true) {
				ExecutionHelper.getLogger().log(LogStatus.PASS,
						"Given text  "+"\"" + actualtext +"\""+ " is not matching with the text  "+"\"" + expectedText+"\"");
			}
		}
		return flag;
	}

	/*
	 * Validating the WebElement text matching with the expected text
	 *
	 * @param actual
	 *            Text
	 * @param expected
	 *            Text
	 */
	public boolean verifyWebElementTextMatches(final WebElement element, final String expectedText) throws Throwable {
		boolean flag = false;
		String actualtext = null;
		try {
			actualtext = element.getText().trim();
			if (actualtext.equalsIgnoreCase(expectedText)) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (final Exception e) {
			flag = false;
		} finally {
			if (flag == false) {
				ExecutionHelper.getLogger().log(LogStatus.FAIL,
						"Given Actual text  " + actualtext + " doesn't matches with the expected text  " + expectedText);
			} else if (flag == true) {
				ExecutionHelper.getLogger().log(LogStatus.PASS,
						"Given Actual text  " + actualtext + " matches with the expected text  " + expectedText);
			}
		}
		return flag;
	}

	/*
	 * Verifying the WebElement text contains with the expected text value
	 *
	 * @param actual
	 *            Text
	 * @param expected
	 *            Text
	 */
	public boolean verifyWebElementTextContains(final WebElement element, final String expectedText) throws Throwable {
		boolean flag = false;
		String actualtext = null;
		try {
			actualtext = element.getText().trim();
			if (actualtext.contains(expectedText)) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (final Exception e) {
			flag = false;
		} finally {
			if (flag == false) {
				ExecutionHelper.getLogger().log(LogStatus.FAIL,
						"Given Actual text  " +"\"" +actualtext +"\""+ " doesn't contains with the expected text  "+"\"" + expectedText+"\"");
			} else if (flag == true) {
				ExecutionHelper.getLogger().log(LogStatus.PASS,
						"Given Actual text  "+"\"" + actualtext +"\""+ " contains with the expected text  "+"\"" + expectedText+"\"");
			}
		}
		return flag;
	}

	/*
	 * Select the dropdown value by index
	 * @param Web Element
	 * @param integer index
	 * @return
	 * @throws Exception
	 */

	public boolean SelectByIndex(final WebElement element, final int IndexValue) throws Exception {
		boolean flag = false;
		try {
			Select sel = new Select(element);
			sel.selectByIndex(IndexValue);
			flag = true;
		} catch (final Exception e) {
			flag = false;
		} finally {
			if (!flag) {
				ExecutionHelper.getLogger().log(LogStatus.FAIL, "Unable to Select the value from the drop down" );
			} else if (flag) {
				ExecutionHelper.getLogger().log(LogStatus.PASS, "Selected the value from the drop down with the index value" + IndexValue );
			}
		}return flag;
	}

	/*
	 * Select the dropdown value by visible text
	 * @param Web Element
	 * @param String text
	 * @return
	 * @throws Exception
	 */

	public boolean SelectByVisibleText(final WebElement element, final String IndexValue) throws Exception {
		boolean flag = false;
		try {
			Select sel = new Select(element);
			sel.selectByVisibleText(IndexValue);
			flag = true;
		} catch (final Exception e) {
			flag = false;
		} finally {
			if (!flag) {
				ExecutionHelper.getLogger().log(LogStatus.FAIL, "Unable to Select the value from the drop down" );
			} else if (flag) {
				ExecutionHelper.getLogger().log(LogStatus.PASS, "Selected the value from the drop down with the text" + IndexValue );
			}
		}return flag;
	}

	/*
	 * Move cursor to given web element
	 * @param Web Element
	 * 
	 */
	public boolean MouseHover(WebElement elem) throws Exception {
		boolean flag = false;
		try {
			Actions act = new Actions(DriverFactory.getDriver());
			act.moveToElement(elem).perform();
			flag = true;
		} catch (final Exception exception) {
			flag = false;
		}finally {
			if (!flag) {
				ExecutionHelper.getLogger().log(LogStatus.FAIL, "Unable to move cursor to web element");
			} else if (flag) {
				ExecutionHelper.getLogger().log(LogStatus.PASS, "Succesfully moved the cursor given web element "+"'"+elem.getText()+"'");
			}
		}return flag;
	}

	/*
	 * Click on the Web element
	 * @param By element Locator
	 * @param String text
	 * 
	 */

	public boolean click( WebElement elementLocator , String value) throws Throwable {
		boolean status = false;
		try {
			final WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 60);
			wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
			elementLocator.click();
			status = true;
			return true;
		} catch (final Exception e) {
			status = false;
			return false;
		} finally {
			if (!status) {
				ExecutionHelper.getLogger().log(LogStatus.FAIL, "User is Unable to click on "+"\""+value+"\""+" locator ");
			} else if (status) {
				ExecutionHelper.getLogger().log(LogStatus.PASS,
						"User has clicked on "+"\""+value+"\""+" locator Successfully");
			}
		}
	}

	/*
	 * Java Script Click on the Web element
	 * @param By element Locator
	 * @param String Text
	 * 
	 */

	public boolean Jsclick( WebElement elementLocator , String value) throws Throwable {
		boolean status = false;
		try {
			((JavascriptExecutor)DriverFactory.getDriver()).executeScript("arguments[0].click();", elementLocator);
			status = true;
			return true;
		} catch (final Exception e) {
			status = false;
			return false;
		} finally {
			if (!status) {
				ExecutionHelper.getLogger().log(LogStatus.FAIL, "User is Unable to click on "+"\""+value+"\""+" locator ");
			} else if (status) {
				ExecutionHelper.getLogger().log(LogStatus.PASS,
						"User has clicked on "+"\""+value+"\""+" Locator Successfully");
			}
		}
	}

	/*
	 *  validating the element is displayed or not
	 *  
	 *  @param Web Element
	 *  @param String text
	 */

	public boolean isElementDisplayed(final WebElement element , String value) throws Exception {
		boolean flag = false;
		try {
			element.isDisplayed();
			flag = true;
		} catch (final Exception exception) {
			flag = false;
		}finally {
			if (flag == false) {
				ExecutionHelper.getLogger().log(LogStatus.FAIL, "\""+value+"\""+" web Element is NOT displayed");
			} else if (flag == true) {
				ExecutionHelper.getLogger().log(LogStatus.PASS, "\""+value+"\""+" web Element is displayed");
			}
		}return flag;
	}

	public boolean elementNotDisplayed(final WebElement element , String value) throws Exception {
		boolean flag = false;
		try {
			element.isDisplayed();
			flag = true;
		} catch (final Exception exception) {
			flag = false;
		}finally {
			if (flag == false) {
				ExecutionHelper.getLogger().log(LogStatus.PASS, "\""+value+"\""+" web Element is NOT displayed");
			} else if (flag == true) {
				ExecutionHelper.getLogger().log(LogStatus.FAIL, "\""+value+"\""+" web Element is displayed");
			}
		}return flag;
	}



	/*
	 *  validating the element is enabled or not
	 *  
	 *  @param Web Element
	 *  @param String text
	 */

	public boolean isElementEnabled(final WebElement element , String value) throws Exception {
		boolean flag = false;
		try {
			element.isEnabled();
			flag = true;
		} catch (final Exception exception) {
			flag = false;
		}finally {
			if (flag == false) {
				ExecutionHelper.getLogger().log(LogStatus.FAIL, "\""+value+"\""+" web  Element is NOT Enabled");
			} else if (flag == true) {
				ExecutionHelper.getLogger().log(LogStatus.PASS, "\""+value+"\""+" web Element is Enabled");
			}
		}return flag;
	}

	/*
	 *  validating the element is Selected or not
	 *  
	 *  @param Web Element
	 *  @param String text
	 */

	public boolean isElementSelected(final WebElement element , String value) throws Exception {
		boolean flag = false;
		try {
			element.isSelected();
			flag = true;
		} catch (final Exception exception) {
			flag = false;
		}finally {
			if (flag == false) {
				ExecutionHelper.getLogger().log(LogStatus.FAIL, "\""+value+"\""+" web Element is NOT selected");
			} else if (flag == true) {
				ExecutionHelper.getLogger().log(LogStatus.PASS, "\""+value+"\""+" web Element is selected");
			}
		}return flag;
	}



	/*
	 * Generate a random Alphabets string of given length
	 * 
	 * @param length
	 *            Length of string to be generated
	 */
	public static String generateRandomAlphabetsString(int length)
	{
		Random rd = new Random();
		String aphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++)
		{
			sb.append(aphaNumericString.charAt(rd.nextInt(aphaNumericString.length())));
		}

		return sb.toString();
	}

	/*
	 * Generate a random Alpha-Numeric string of given length
	 * 
	 * @param length
	 *            Length of string to be generated
	 */
	public static String generateRandomAlphaNumericString(int length)
	{
		Random rd = new Random();
		String aphaNumericString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++)
		{
			sb.append(aphaNumericString.charAt(rd.nextInt(aphaNumericString.length())));
		}

		return sb.toString();
	}

	/*
	 * Generate a random Special Character string of given length
	 * 
	 * @param length
	 *            Length of string to be generated
	 */

	public static String generateRandomSpecialCharacterString(int length)
	{
		Random rd = new Random();
		String specialCharString =  "~!@#$%^*()_<>?/{}[]|\";";
		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++)
		{
			sb.append(specialCharString.charAt(rd.nextInt(specialCharString.length())));
		}

		return sb.toString();
	}

	public static void doubleClick(WebElement element) {
		Actions action = new Actions(DriverFactory.getDriver());
		((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView();", element);
		action.doubleClick(element).build().perform();
	}

	/*
	 * Verify the given web element is clickable or not
	 * 
	 * @param WebElement 
	 * 
	 */
	public static boolean isClickable(WebElement el) 
	{
		try{
			WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 6);
			wait.until(ExpectedConditions.elementToBeClickable(el));
			return true;
		}
		catch (Exception e){
			return false;
		}
	}

	public boolean isElementClickable(WebElement el, String value) 
	{
		boolean flag = false;
		try{
			WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 6);
			wait.until(ExpectedConditions.elementToBeClickable(el));
			flag = true;

		} catch (final Exception exception) {
			flag = false;
		}finally {
			if (flag == false) {
				ExecutionHelper.getLogger().log(LogStatus.FAIL, "\""+value+"\""+" web  Element is NOT Clickable");
			} else if (flag == true) {
				ExecutionHelper.getLogger().log(LogStatus.PASS, "\""+value+"\""+" web Element is Clickable");
			}
		}return flag;
	}

	public String getCurrentPageUrl()
	{
		String url = DriverFactory.getDriver().getCurrentUrl();

		return url;
	}

	/*
	 * Clears and SendKeys on the Web element
	 * @param By element Locator
	 * @param String text
	 * 
	 */

	public boolean SendKeys( WebElement elementLocator , String value) throws Throwable {
		boolean status = false;
		try {
			final WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 60);
			wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
			elementLocator.clear();
			elementLocator.sendKeys(value);
			status = true;
			return true;
		} catch (final Exception e) {
			status = false;
			return false;
		} finally {
			if (!status) {
				ExecutionHelper.getLogger().log(LogStatus.FAIL, "Unable to pass "+"\""+value+"\""+" text on the given locator ");
			} else if (status) {
				ExecutionHelper.getLogger().log(LogStatus.PASS,
						"User has successfully passed  "+"\""+value+"\""+" text on the given locator ");
			}
		}
	}
	
	public boolean elementExists(List<WebElement> elem){
		return !elem.isEmpty();
	}

	/*
	 * Generate a random number of given length
	 * 
	 * @param length
	 *            Length of number to be generated
	 * @return
	 */
	public static String generateRandomNumber(int length)
	{
		String randomNumber = "1";
		int retryCount = 1;

		// retryCount added for generating specified length's number
		while (retryCount > 0)
		{
			String strNum = Double.toString(Math.random());
			strNum = strNum.replace(".", "");

			if (strNum.length() > length)
			{
				randomNumber = strNum.substring(0, length);
			}
			else
			{
				int remainingLength = length - strNum.length() + 1;
				randomNumber = generateRandomNumber(remainingLength);

			}
			if (randomNumber.length() < length)
			{
				retryCount++;
			}
			else
			{
				retryCount = 0;
			}
		}
		return randomNumber;
	}

	/*
	 * Wait for the complete page Load
	 */
	public void waitForPageLoad() {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 20);
		wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				return String
						.valueOf(((JavascriptExecutor) DriverFactory.getDriver()).executeScript("return document.readyState"))
						.equals("complete");
			}
		});
	}
	
	/*
	 * Wait for the page to be ready
	 */
	public void waitForPageToBeReady() {
		JavascriptExecutor js = (JavascriptExecutor)DriverFactory.getDriver();
		//This loop will rotate for 100 times to check If page Is ready after every 1 second.
		//You can replace your if you wants to Increase or decrease wait time.
		for (int i=0; i<60; i++) { 
			try {
				Thread.sleep(500);
			}catch (InterruptedException e) {} 
			if (js.executeScript("return document.readyState").toString().equals("complete")) { 
				break; 
			}   
		}
	}

	/*
	 * Wait till the page  get loaded
	 */
	public boolean waitTime() {
		boolean status;
		Long loadTime;
		try {
			loadTime = (Long) ((JavascriptExecutor) DriverFactory.getDriver())
					.executeScript("return performance.timing.loadEventEnd - performance.timing.navigationStart;");
			Thread.sleep(loadTime);
			status = true;
		} catch (TimeoutException | InterruptedException e) {
			status = false;
		}
		return status;
	}

	public void selectByTextInList(List<WebElement> element, String string) throws Throwable {
		WebElement Element = null;
		try {
			for(int i=0;i<element.size();i++)
			{
				if(element.get(i).getText().contains(string))
					Element = element.get(i);
			}
			Jsclick(Element,string);
		} catch (final Exception e) {
			ExecutionHelper.getLogger().log(LogStatus.FAIL, "Unable to select by text on the given locator due to exception "+e);
		} 
	}

	public void switchToNewWindowFromCurrentWindow() throws Throwable {
		try {
			ArrayList<String> tabs = new ArrayList<String> (DriverFactory.getDriver().getWindowHandles());
			DriverFactory.getDriver().switchTo().window(tabs.get(1));
		} catch (final Exception e) {
			ExecutionHelper.getLogger().log(LogStatus.FAIL, "Unable to switch to new window "+e);
		}
	}
	
	public void switchToParentWindow() throws Throwable {
		try {
			ArrayList<String> tabs = new ArrayList<String> (DriverFactory.getDriver().getWindowHandles());
			DriverFactory.getDriver().switchTo().window(tabs.get(0));
		} catch (final Exception e) {
			ExecutionHelper.getLogger().log(LogStatus.FAIL, "Unable to switch to parent window "+e);
		}
	}
	
	public void closeCurrentWindow() throws Throwable {
		try {
			DriverFactory.getDriver().close();
		} catch (final Exception e) {
			ExecutionHelper.getLogger().log(LogStatus.FAIL, "Unable to close window"+e);
		}
	}
}
