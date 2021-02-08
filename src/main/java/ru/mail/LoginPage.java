package ru.mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private final WebDriver webDriver;
    private WebDriverWait webDriverWait;


    @FindBy(name = "login")
    private WebElement loginInput;

    @FindBy(xpath = "//button[@data-testId='enter-password']")
    private WebElement enterPasswordButton;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@data-testId='login-to-mail']")
    private WebElement loginButton;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriverWait = new WebDriverWait(webDriver, 10);
    }

    public LoginPage openLoginPage() {
        webDriver.get("https://mail.ru/");
        return this;
    }

    public AccountEmailPage login(String email, String password) {
        loginInput.click();
        loginInput.sendKeys(email);
        enterPasswordButton.click();
        webDriverWait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.click();
        passwordInput.sendKeys(password);
        loginButton.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='compose-button__txt']")));
        return PageFactory.initElements(webDriver, AccountEmailPage.class);
    }
}
