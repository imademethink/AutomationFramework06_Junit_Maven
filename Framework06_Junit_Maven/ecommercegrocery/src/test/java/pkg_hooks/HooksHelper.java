package pkg_hooks;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pkg_Global.GlobalObjects;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HooksHelper extends GlobalObjects {

    public WebDriver LocalDriver = null;

    @Before  // before each scenario
    public void beforeEachScenario(){
        System.out.println("Log: Before every Scenario hook");
        LocalDriver = new GlobalObjects().getDriver();
    }

    @After  // after each scenario
    public void afterEachScenarioWithScreenShot(Scenario scenario){
        System.out.println("Log: After the every Scenario hook");

        // Add screenshot only if scenario fails
        if (scenario.isFailed()) {
            System.out.println("Log: Taking screenshot for failed screenshot");
            String sDDMMYY = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            File src       = ((TakesScreenshot) LocalDriver).getScreenshotAs(OutputType.FILE);
            String dest    = System.getProperty("user.dir") + "//site//cucumber-reports-advanced//"+"screenshot_"+sDDMMYY+".jpg";
            File finalDestination = new File(dest);

            try {
                FileUtils.copyFile(src, finalDestination);
                byte[] fileContent = Files.readAllBytes(finalDestination.toPath());
                scenario.embed(fileContent, "image/png");
            }catch (IOException eScreenshot) {
                eScreenshot.printStackTrace();
            }
        }
        LocalDriver.quit();
        LocalDriver = null;
        bBrowserInvoked = false;
    }


}
