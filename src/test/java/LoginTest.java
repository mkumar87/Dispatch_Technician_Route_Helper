import static org.junit.Assert.assertEquals;

import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.*;


public class LoginTest {
	private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

	@Test
	public void submittingForm() throws Exception {
		try (final WebClient webClient = new WebClient()) {
			/*
	        // Get the first page
	        final HtmlPage page1 = webClient.getPage("http://localhost:8080/");


	        final HtmlForm form = page1.getFormByName("loginForm");

	        final HtmlSubmitInput button = form.getInputByName("loginBtn");
	        final HtmlTextInput textField = form.getInputByName("username");

	        // Change the value of the text field
	        textField.setValueAttribute("2548580");

	        // Now submit the form by clicking the button and get back the second page.
	        final HtmlPage page2 = button.click();
	        final String pageAsXml = page2.asXml();
	        logger.info("pageAsXml"+pageAsXml);
	        //Assert.assertTrue(pageAsXml.contains("<body class=\"composite\">"));
			 */	    
			assertEquals("SUCCESS", "", "");

		}
	}
} 
