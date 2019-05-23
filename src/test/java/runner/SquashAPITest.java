package runner;

import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.model.iface.Submit.Status;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.model.testsuite.TestCaseRunner;
import com.eviware.soapui.model.testsuite.TestRunner;
import com.eviware.soapui.model.testsuite.TestSuite;
import com.eviware.soapui.support.SoapUIException;
import org.apache.xmlbeans.XmlException;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//Test SoapUI with a SOAP project
//No SUT needed, tester from the website https://graphical.weather.gov/
public class SquashAPITest {
    TestSuite testSuite;
    TestCase testCase;
    String testSuiteName = "TestSuite Attachments";
    String testCaseName = "Get_attachment";

    @Test
    public void fullControl() throws Exception {
            WsdlProject project = new WsdlProject("resources/API_REST_Squash-soapui-project.xml");
            List<TestSuite> testSuites = project.getTestSuiteList();
            
            for( TestSuite suite : testSuites ) {
            	List<TestCase> testCases = suite.getTestCaseList();
            	
            	for( TestCase testCase : testCases ) {
            		System.out.println("Execution du test SoapUI [" + testCase.getName() + "]");
            		TestRunner runner2 = testCase.run(new PropertiesMap(), false);
            		assertEquals(Status.FINISHED, runner2.getStatus());
            	}
            	
            }

    }

    @AfterEach
    public void tearDown(){

    }
}
