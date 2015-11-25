package mockObject;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mockobjects.servlet.MockHttpServletRequest;
import com.mockobjects.servlet.MockHttpServletResponse;

public class TestLabConverterServlet {
	
	@Test
	public void test_bad_parameter() throws Exception {
		TestingLabConverterServlet s = new TestingLabConverterServlet();
		CityTemperatureServiceProvider.turnOnMockMode();
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();

		String input = "boo!";
		request.setupAddParameter("farenheitTemperature", input);
		response.setExpectedContentType("text/html");
		s.doGet(request, response);
		response.verify();
		String output = response.getOutputStreamContents();
		String expected = "<html><head><title>Bad Temperature</title></head><body><h2>Need to enter a valid temperature!Got a NumberFormatException on "+input+"</h2></body></html>\r\n";
		assertEquals(expected, output);
	}
	
	@Test
	public void test_null_parameter() throws Exception {
		TestingLabConverterServlet s = new TestingLabConverterServlet();
		CityTemperatureServiceProvider.turnOnMockMode();
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();

		//request.setupAddParameter("farenheitTemperature", "boo!");
		response.setExpectedContentType("text/html");
		s.doGet(request, response);
		response.verify();
		String output = response.getOutputStreamContents();
		String expected = "<html><head><title>No Temperature</title></head><body><h2>Need to enter a temperature!</h2></body></html>\r\n";
		assertEquals(expected, output);
	}

	@Test
	public void test_boil() throws Exception {
		TestingLabConverterServlet s = new TestingLabConverterServlet();
		CityTemperatureServiceProvider.turnOnMockMode();
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		String input = "212";
		request.setupAddParameter("farenheitTemperature", input);
		response.setExpectedContentType("text/html");
		s.doGet(request, response);
		response.verify();
		String output = response.getOutputStreamContents();
		double outputTemp = getTemp(output);		
		double expectedTemp = 100.0;
		assertEquals(expectedTemp, outputTemp, 0.011);
		String expectedFirst = "<html><head><title>Temperature Converter Result</title></head><body><h2>"+input+" Farenheit = ";
		String expectedSecond = " Celsius </h2>\r\n<p><h3>The temperature in Austin is 451 degrees Farenheit</h3>\r\n</body></html>\r\n";
		assertTrue(output.contains(expectedFirst));
		assertTrue(output.contains(expectedSecond));
	}

	@Test
	public void test_freeze() throws Exception {
		TestingLabConverterServlet s = new TestingLabConverterServlet();
		CityTemperatureServiceProvider.turnOnMockMode();
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		String input = "0";
		request.setupAddParameter("farenheitTemperature", input);
		response.setExpectedContentType("text/html");
		s.doGet(request, response);
		response.verify();
		String output = response.getOutputStreamContents();
		double outputTemp = getTemp(output);		
		double expectedTemp = -17.7778;
		assertEquals(expectedTemp, outputTemp, 0.011);
		String expectedFirst = "<html><head><title>Temperature Converter Result</title></head><body><h2>"+input+" Farenheit = ";
		String expectedSecond = " Celsius </h2>\r\n<p><h3>The temperature in Austin is 451 degrees Farenheit</h3>\r\n</body></html>\r\n";
		assertTrue(output.contains(expectedFirst));
		assertTrue(output.contains(expectedSecond));
	}

	@Test
	public void test_too_hot() throws Exception {
		TestingLabConverterServlet s = new TestingLabConverterServlet();
		CityTemperatureServiceProvider.turnOnMockMode();
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		String input = "451";
		request.setupAddParameter("farenheitTemperature", input);
		response.setExpectedContentType("text/html");
		s.doGet(request, response);
		response.verify();
		String output = response.getOutputStreamContents();
		double outputTemp = getTemp(output);		
		double expectedTemp = 232.77778;
		assertEquals(expectedTemp, outputTemp, 0.11);
		String expectedFirst = "<html><head><title>Temperature Converter Result</title></head><body><h2>"+input+" Farenheit = ";
		String expectedSecond = " Celsius </h2>\r\n<p><h3>The temperature in Austin is 451 degrees Farenheit</h3>\r\n</body></html>\r\n";
		assertTrue(output.contains(expectedFirst));
		assertTrue(output.contains(expectedSecond));
	}

	@Test
	public void test_too_cold() throws Exception {
		TestingLabConverterServlet s = new TestingLabConverterServlet();
		CityTemperatureServiceProvider.turnOnMockMode();
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		String input = "-75.6";
		request.setupAddParameter("farenheitTemperature", input);
		response.setExpectedContentType("text/html");
		s.doGet(request, response);
		response.verify();
		String output = response.getOutputStreamContents();
		double outputTemp = getTemp(output);		
		double expectedTemp = -59.77778;
		assertEquals(expectedTemp, outputTemp, 0.11);
		String expectedFirst = "<html><head><title>Temperature Converter Result</title></head><body><h2>"+input+" Farenheit = ";
		String expectedSecond = " Celsius </h2>\r\n<p><h3>The temperature in Austin is 451 degrees Farenheit</h3>\r\n</body></html>\r\n";
		assertTrue(output.contains(expectedFirst));
		assertTrue(output.contains(expectedSecond));
	}
	
	public double getTemp(String s)
	{
		return Double.parseDouble(s.substring(s.indexOf('=') + 2, s.indexOf("Celsius")-1));
	}

}
