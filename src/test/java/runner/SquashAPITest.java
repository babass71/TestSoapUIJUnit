package runner;

import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.model.testsuite.TestCaseRunner;
import com.eviware.soapui.model.testsuite.TestRunner;
import com.eviware.soapui.model.testsuite.TestSuite;
import com.eviware.soapui.support.SoapUIException;
import org.apache.xmlbeans.XmlException;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

//Test SoapUI with a SOAP project
//No SUT needed, tester from the website https://graphical.weather.gov/
public class SquashAPITest {
    TestSuite testSuite;
    TestCase testCase;
    String testSuiteName = "Testsuite1";
    String testCaseName = "Testcase1";

    @BeforeEach
    public void setUp(){
        try {
            WsdlProject project = new WsdlProject("resources/REST-Project-1-soapui-project.xml");
            testSuite = project.getTestSuiteByName(testSuiteName);
            testCase = testSuite.getTestCaseByName(testCaseName);
        } catch (SoapUIException | XmlException | IOException ex){
            Logger.getLogger(SquashAPITest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void contactTest(){
        TestCaseRunner runner = testCase.run(new PropertiesMap(), false);
        Assert.assertEquals(TestRunner.Status.FINISHED, runner.getStatus());
    }


    @AfterEach
    public void tearDown(){

    }
}
