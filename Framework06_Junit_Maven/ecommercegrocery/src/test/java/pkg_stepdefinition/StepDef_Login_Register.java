package pkg_stepdefinition;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import pkg_Global.GlobalObjects;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class StepDef_Login_Register extends GlobalObjects {

    public WebDriver LocalDriver = null;

    @When("^User attempts to login with Username \"(.*?)\" and Password \"(.*?)\"$")
    public void User_attempts_to_login_with_Username_and_Password(String sUser, String sPwd){
        LocalDriver = new GlobalObjects().getDriver();
        System.out.println("Log: Navigating to login section " + sUrlLogin + "\n");
        LocalDriver.get(sUrlLogin);
        LocalDriver.manage().timeouts().implicitlyWait(8000, TimeUnit.MILLISECONDS);
        System.out.println("Log: Attempting to login");
        LocalDriver.findElement(By.id("email")).sendKeys(sUser);
        LocalDriver.findElement(By.id("passwd")).sendKeys(sPwd);
        LocalDriver.findElement(By.cssSelector("i[class='icon-lock left']")).click();
        LocalDriver.manage().timeouts().implicitlyWait(8000, TimeUnit.MILLISECONDS);
    }

    @Then("^Login should be successful$")
    public void Login_should_be_successful(){
        boolean bLoginSuccess = false;
        if(LocalDriver.findElements(By.cssSelector("a[title='Log me out']")).size() > 0){
            bLoginSuccess = true;
            // Actual log out
            LocalDriver.findElement(By.cssSelector("a[title='Log me out']")).click();
            LocalDriver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        }

        if(bLoginSuccess){
            System.out.println("Log: Login is successful");
        }
        else{
            Assert.fail("Log: Login is NOT successful due to incorrect credentials");
        }
    }

    @Then("^Login should NOT be successful$")
    public void Login_should_NOT_be_successful(){
        boolean bLoginSuccess = false;
        if(LocalDriver.findElements(By.cssSelector("a[title='Log me out']")).size() > 0){
            bLoginSuccess = true;
            // Actual log out
            LocalDriver.findElement(By.cssSelector("a[title='Log me out']")).click();
            LocalDriver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        }

        if(bLoginSuccess){
            Assert.fail("Log: Login is successful for incorrect credentials");
        }
        else{
            System.out.println("Log: Login is NOT successful");
        }
    }

    @When("^User attempts to register with following data$")
    public void User_attempts_to_register_with_following_data(DataTable dtUserData){
        LocalDriver = new GlobalObjects().getDriver();
        System.out.println("Log: Navigating to register section " + sUrlRegister + "\n");
        LocalDriver.get(sUrlRegister);
        LocalDriver.manage().timeouts().implicitlyWait(8000, TimeUnit.MILLISECONDS);

        // Converting DataTable into hashmap
        Map<String, String> hmUserData = dtUserData.asMap(String.class, String.class);

        System.out.println("Log: Registration begins");
        LocalDriver.findElement(By.id("email_create")).sendKeys(
                hmUserData.get("Email") + new Random().nextInt(99999) + "@mailinator.com");
        LocalDriver.findElement(By.cssSelector("i[class*='icon-user']")).click();
        LocalDriver.manage().timeouts().implicitlyWait(12000, TimeUnit.MILLISECONDS);

        LocalDriver.findElement(By.id("id_gender1")).click();
        LocalDriver.findElement(By.id("customer_firstname")).sendKeys(hmUserData.get("NameFirst"));
        LocalDriver.findElement(By.id("customer_lastname")).sendKeys(hmUserData.get("NameLast"));
        LocalDriver.findElement(By.id("passwd")).sendKeys(hmUserData.get("Password"));

        new Select(LocalDriver.findElement(By.id("days"))).selectByValue(hmUserData.get("DOB_DD"));
        new Select(LocalDriver.findElement(By.id("months"))).selectByValue(hmUserData.get("DOB_MM"));
        new Select(LocalDriver.findElement(By.id("years"))).selectByValue(hmUserData.get("DOB_YYYY"));

        LocalDriver.findElement(By.id("firstname")).sendKeys(hmUserData.get("AddressNameFirst"));
        LocalDriver.findElement(By.id("lastname")).sendKeys(hmUserData.get("AddressNameLast"));
        LocalDriver.findElement(By.id("address1")).sendKeys(hmUserData.get("Address"));
        LocalDriver.findElement(By.id("city")).sendKeys(hmUserData.get("City"));
        new Select(LocalDriver.findElement(By.id("id_state"))).selectByValue("15");
        LocalDriver.findElement(By.id("postcode")).sendKeys(hmUserData.get("Zip"));

        LocalDriver.findElement(By.id("other")).sendKeys(hmUserData.get("AdditionalInfo"));
        LocalDriver.findElement(By.id("phone_mobile")).sendKeys(hmUserData.get("Mobile"));

        LocalDriver.findElement(By.id("submitAccount")).click();
        LocalDriver.manage().timeouts().implicitlyWait(8000, TimeUnit.MILLISECONDS);
    }

    @Then("^Registration should be successful$")
    public void Registration_should_be_successful(){
        boolean bLoginSuccess = false;
        if(LocalDriver.findElements(By.cssSelector("a[title='Log me out']")).size() > 0){
            bLoginSuccess = true;
            LocalDriver.findElement(By.cssSelector("a[title='Log me out']")).click();
        }

        if(bLoginSuccess){
            System.out.println("Log: Registration is successful");
        }
        else{
            Assert.fail("Log: Registration is NOT successful");
        }
    }


}