package pkg_testrunner;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features 	= {"src/test/java/resources/features"},
    glue 	 	= {"pkg_stepdefinition","pkg_hooks"},
    plugin 		= {
            "pretty",
            "html:target/cucumber",
            "json:target/cucumber/cucumber.json",
    },
//    tags        = {"@search_valid"},
    tags        = {"@login"},
    dryRun 	 	= false,
    strict 	 	= false,
    monochrome  = true
)

public class FrameworkTestRunner {

    @BeforeClass
    public static void setupBeforeClass() {
        // empty
    }

    @AfterClass
    public static void setup() {
        // empty
    }

    @Before
    public void setupBeforeTest(){
        // empty
    }
    @After
    public void tearDownBeforeTest(){
        // empty
    }

}

//  POM.xml Maven dependencies
//  Allure reporting
//  2 Step running process
//      mvn clean test
//      mvn site
//  Add screenshot for failed tests
