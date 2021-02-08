package ru.mail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.openqa.selenium.chrome.ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY;
import static org.openqa.selenium.chrome.ChromeOptions.CAPABILITY;

public class AccountSendEmailTest {

    private WebDriver webDriver;

    @BeforeClass
    public void init() {
        System.setProperty(CHROME_DRIVER_EXE_PROPERTY, "chromedriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setCapability(CAPABILITY, options);
        webDriver = new ChromeDriver(options);
        webDriver.manage().deleteAllCookies();
        webDriver.manage().window().maximize();
    }

    @Test(dataProvider = "test data")
    public void loginAsUserAndSendEmail(String userEmail, String password, String textContent) {
        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        Assert.assertTrue(loginPage.openLoginPage()
                .login(userEmail, password)
                .compose()
                .setRecipient(userEmail + "@mail.ru")
                .enterTextContent(textContent)
                .sendEmail()
                .isSentEmailComposeDisplayedCorrectly(userEmail + "@mail.ru"));
    }

    @DataProvider(name = "test data")
    public Object[][] testData() {
        return new Object[][]{
                {"", "", ""}};// enter user email without '@mail.ru', password, text content
    }

    @AfterClass
    public void tearDown() {
        webDriver.quit();
    }
}
