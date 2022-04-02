package pkg_testrunner;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import com.cucumber.listener.Reporter;
import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(
    features 	= {"src/test/java/resources/features"},
    glue 	 	= {"pkg_stepdefinition","pkg_hooks"},
    plugin 		= {"com.cucumber.listener.ExtentCucumberFormatter:target/html/report.html"},
    tags        = {"@login"},
//    tags        = {"@register"},
//    tags        = {"@search"},
//    tags        = {"@login,@register"},         // OR operation
//    tags        = {"@search","@search_valid"},  // AND operation
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
        System.out.println("Generating Extent Report");
        Reporter.loadXMLConfig(new File(System.getProperty("user.dir") + "/src/test/java/resources/extent-config.xml"));
        Reporter.setSystemInfo("Test User", "Tony Stark");
        Reporter.setSystemInfo("Operating System Type", "Windows");
        Reporter.setSystemInfo("Build version", "v 1.2.3");
        Reporter.setTestRunnerOutput("Extent Report for Regression");
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

//  Integration with maven
//  mvn archetype:generate -DgroupId=com.flipcart.fashion -DartifactId=ecommercefashion -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
//
//      mvn archetype:generate
//      -DgroupId=com.flipcart.fashion
//      -DartifactId=ecommercefashion
//      -DarchetypeArtifactId=maven-archetype-quickstart
//      -DinteractiveMode=false
//
//  POM.xml Maven dependencies
//  Maven plugin - Compiler plugin, Surefire plugin (versioning)
//  Runner file naming precaution
//  Surefire plugin - options, running multiple runners


//  Junit based project with before/ after cucumber hooks
//  Junit based project with beforeclass/ afterclass hooks
//  Cucumber tag for test filtering, multiple tag ( AND, OR, NOT operation)
//  Extent reporting
//  Add screenshot for failed tests in extent report

