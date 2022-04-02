package pkg_testrunner;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import pkg_Global.GlobalObjects;

@RunWith(Cucumber.class)
@CucumberOptions(
    features 	= {"src/test/java/resources/features"},
    glue 	 	= {"pkg_stepdefinition","pkg_hooks"},
    plugin 		= {
            "pretty",
            "html:target/cucumber",
            "json:target/cucumber/cucumber.json",
    },

    tags        = {"@search"},
//    tags        = {"@login,@register"},         // OR operation
//    tags        = {"@search","@search_valid"},  // AND operation

    dryRun 	 	= false,
    strict 	 	= false,
    monochrome  = true
)

public class FrameworkTestRunner extends GlobalObjects {

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

//  Integration with maven
//  mvn archetype:generate -DgroupId=com.flipcart.grocery -DartifactId=ecommercegrocery -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
//  POM.xml Maven dependencies
//  Maven plugin - Compiler plugin, Surefire plugin (versioning)

//  Junit based project with before/ after cucumber hooks
//  Junit based project with beforeclass/ afterclass hooks
//  Masterthought reporting
//  Add screenshot for failed tests in master-thought html report
//  Add screenshot for failed tests in normal cucumber html report

