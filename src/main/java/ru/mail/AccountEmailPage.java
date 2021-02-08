package ru.mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountEmailPage {

    private final WebDriver webDriver;
    private WebDriverWait webDriverWait;

    @FindBy(xpath = "//span[@class='compose-button__txt']")
    private WebElement composeButton;

    public AccountEmailPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriverWait = new WebDriverWait(webDriver,10);
    }

    public ComposePopUp compose() {
        composeButton.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='compose-app__compose']")));
        return PageFactory.initElements(webDriver, ComposePopUp.class);
    }


}
