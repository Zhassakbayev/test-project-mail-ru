package ru.mail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ComposePopUp {

    private final WebDriver webDriver;
    private WebDriverWait webDriverWait;

    @FindBy(xpath = "//div[@role='textbox']//div")
    private WebElement textBox;

    @FindBy(xpath = "//span[@data-title-shortcut='Ctrl+Enter']")
    private WebElement sendSpan;

    @FindBy(xpath = "//div[@data-name='to']//input")
    private WebElement recipientEmailInput;//a[@class='layer__link']

    @FindBy(xpath = "//a[@class='layer__link']")
    private WebElement layerSendLink;

    @FindBy(xpath = "//span[@class='layer-sent-page__contact-item']")
    private WebElement layerSentPageContactItemSpan;

    public ComposePopUp(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriverWait = new WebDriverWait(webDriver, 10);

    }

    public ComposePopUp setRecipient(String recipientEmail) {
        recipientEmailInput.click();
        recipientEmailInput.sendKeys(recipientEmail);
        recipientEmailInput.click();
        return this;
    }

    public ComposePopUp enterTextContent(String textContent) {
        textBox.click();
        textBox.sendKeys(textContent);
        textBox.click();
        return this;
    }

    public ComposePopUp sendEmail() {
        sendSpan.click();
        return this;
    }

    public boolean isSentEmailComposeDisplayedCorrectly(String recipientEmail) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='layer-window']")));
        if (!webDriver.findElements(By.xpath("//a[@class='layer__link']")).isEmpty()) {
            return webDriver.findElements(By.xpath("//span[@class='layer-sent-page__contact-item']")).get(0).getText().contains(recipientEmail);
        } else {
            return false;
        }

    }
}
