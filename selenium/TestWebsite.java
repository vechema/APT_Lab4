package selenium;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestWebsite {
	String url = "http://apt-public.appspot.com/";
	String login = "testing-lab-login.html";
	String convert = "testing-lab-calculator.html";
	
	String userId = "userId";
	String password = "userPassword";
	List<String> users = Arrays.asList("andy", "bob" , "charley");
	List<String> pws = Arrays.asList("apple", "bathtub" , "china");
	
	String tempParam = "farenheitTemperature";
	String h2xPath = "//h2";
	String h3xPath = "//h3";
	String titlexPath = "//title";

	static WebDriver wd;

	@BeforeClass
	public static void setup() {
		wd = new FirefoxDriver();
	}
	
	/**
	 * Login test - correct for andy, case insensitivity proven
	 * There's only one test per login because of lock out "feature"
	 */
	@Test
	public void login01() {
		int index = 0;
		wd.get(url + login);
		WebElement we;
		we = wd.findElement(By.name(userId));
		we.clear();
		we.sendKeys(users.get(index).toUpperCase());
		we = wd.findElement(By.name(password));
		we.clear();
		we.sendKeys(pws.get(index));
		we.submit();
		WebElement result = wd.findElement(By.xpath(h3xPath));
		String output = result.getText();
		assertEquals("Convert from Farenheit to Celsius", output);
	}
	
	/**
	 * Login test - correct for bob, leading & trailing white space in username
	 */
	@Test
	public void login02() {
		int index = 1;
		wd.get(url + login);
		WebElement we;
		we = wd.findElement(By.name(userId));
		we.clear();
		we.sendKeys("  " + users.get(index) + " ");
		we = wd.findElement(By.name(password));
		we.clear();
		we.sendKeys(pws.get(index));
		we.submit();
		WebElement result = wd.findElement(By.xpath(h3xPath));
		String output = result.getText();
		assertEquals("Convert from Farenheit to Celsius", output);
	}
	
	/**
	 * Login test - correct for charley, leading & trailing white space in password
	 */
	@Test
	public void login03() {
		int index = 2;
		wd.get(url + login);
		WebElement we;
		we = wd.findElement(By.name(userId));
		we.clear();
		we.sendKeys(users.get(index));
		we = wd.findElement(By.name(password));
		we.clear();
		we.sendKeys("  " + pws.get(index) + " ");
		we.submit();
		WebElement result = wd.findElement(By.xpath(h3xPath));
		String output = result.getText();
		assertEquals("Convert from Farenheit to Celsius", output);
	}
	
	/**
	 * Login test - incorrect, login three times wrong then wait one minute
	 * I found this to never happen
	 */
	@Ignore
	public void login04() {
		
		try {
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int index = 0;
		wd.get(url + login);
		WebElement we;
		we = wd.findElement(By.name(userId));
		we.clear();
		we.sendKeys(users.get(index));
		we = wd.findElement(By.name(password));
		we.clear();
		we.sendKeys("Not the password");
		we.submit();		
		WebElement result = wd.findElement(By.xpath(h2xPath));
		String output = result.getText();
		assertEquals("Input combination of user id and password is incorrect.", output);
		
		wd.get(url + login);
		we = wd.findElement(By.name(userId));
		we.clear();
		we.sendKeys(users.get(index));
		we = wd.findElement(By.name(password));
		we.clear();
		we.sendKeys("Not the password");
		we.submit();		
		result = wd.findElement(By.xpath(h2xPath));
		output = result.getText();
		assertEquals("Input combination of user id and password is incorrect.", output);
		
		wd.get(url + login);
		we = wd.findElement(By.name(userId));
		we.clear();
		we.sendKeys(users.get(index));
		we = wd.findElement(By.name(password));
		we.clear();
		we.sendKeys("Not the password");
		we.submit();		
		result = wd.findElement(By.xpath(h2xPath));
		output = result.getText();
		assertEquals("Wait for 60 seconds before trying to login again", output);
	}
	
	/**
	 * Login test - incorrect, login two times then wait 10 seconds
	 * Not the specifications but what happened
	 */
	@Test
	public void login05() {

		try {
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int index = 1;
		wd.get(url + login);
		WebElement we;
		we = wd.findElement(By.name(userId));
		we.clear();
		we.sendKeys(users.get(index));
		we = wd.findElement(By.name(password));
		we.clear();
		we.sendKeys("Not the password");
		we.submit();		
		WebElement result = wd.findElement(By.xpath(h2xPath));
		String output = result.getText();
		assertEquals("Input combination of user id and password is incorrect.", output);
		
		wd.get(url + login);
		we = wd.findElement(By.name(userId));
		we.clear();
		we.sendKeys(users.get(index));
		we = wd.findElement(By.name(password));
		we.clear();
		we.sendKeys("Not the password");
		we.submit();		
		result = wd.findElement(By.xpath(h2xPath));
		output = result.getText();
		assertEquals("Wait for 10 seconds before trying to login again", output);
	}
	
	@Test
	public void temp01() {
		String input = "212";
		wd.get(url + convert);
		WebElement we;
		we = wd.findElement(By.name(tempParam));
		we.clear();
		we.sendKeys(input);
		we.submit();
		WebElement result = wd.findElement(By.xpath(h2xPath));
		String output = result.getText();
		double answerActual = getTemp(output);
		double answerExpected = 100;
		assertEquals(answerExpected, answerActual, 0.011);
	}
	
	@Test
	public void temp02() {
		String input = "0";
		wd.get(url + convert);
		WebElement we;
		we = wd.findElement(By.name(tempParam));
		we.clear();
		we.sendKeys(input);
		we.submit();
		WebElement result = wd.findElement(By.xpath(h2xPath));
		String output = result.getText();
		double answerActual = getTemp(output);
		double answerExpected = -17.7778;
		assertEquals(answerExpected, answerActual, 0.011);
	}
	
	@Test
	public void temp03() {
		String input = "-75.6";
		wd.get(url + convert);
		WebElement we;
		we = wd.findElement(By.name(tempParam));
		we.clear();
		we.sendKeys(input);
		we.submit();
		WebElement result = wd.findElement(By.xpath(h2xPath));
		String output = result.getText();
		double answerActual = getTemp(output);
		double answerExpected = -59.77778;
		assertEquals(answerExpected, answerActual, 0.11);
	}
	
	@Test
	public void temp04() {
		String input = "451";
		wd.get(url + convert);
		WebElement we;
		we = wd.findElement(By.name(tempParam));
		we.clear();
		we.sendKeys(input);
		we.submit();
		WebElement result = wd.findElement(By.xpath(h2xPath));
		String output = result.getText();
		double answerActual = getTemp(output);
		double answerExpected = 232.77778;
		assertEquals(answerExpected, answerActual, 0.11);
	}
	
	@Test
	public void temp05() {
		String input = "Not a Number";
		wd.get(url + convert);
		WebElement we;
		we = wd.findElement(By.name(tempParam));
		we.clear();
		we.sendKeys(input);
		we.submit();
		WebElement result = wd.findElement(By.xpath(h2xPath));
		String output = result.getText();
		String expected = "Need to enter a valid temperature!Got a NumberFormatException on " + input;
		assertEquals(output,expected);
	}
	
	public double getTemp(String s)
	{
		return Double.parseDouble(s.substring(s.indexOf('=') + 2, s.indexOf("Celsius")-1));
	}

	@AfterClass
	public static void cleanup() {
		wd.quit();
	}

}
