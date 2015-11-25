package edu.plas.testautoandci.pageobjectmodels;

import edu.plas.testautoandci.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CustomSitePage {
    private WebDriver driver = Driver.getWebDriver();

    // Parking Dropdown

    private Select getParkingDropdown() {
        return new Select(driver.findElement(By.id("Parking")));
    }

    public void selectParkingDropdownByText(String dropdownText) {
        getParkingDropdown().selectByVisibleText(dropdownText);
    }

    public void selectParkingDropdownByValue(String dropdownValue) {
        getParkingDropdown().selectByValue(dropdownValue);
    }

    public String getParkingSelectionText() {
        return driver.findElement(By.cssSelector("#dropdownSection #selectResult")).getText();
    }


    // Hover Text

    private WebElement getHoverTextElement() {
        return driver.findElement(By.cssSelector("#hoverSection .onHoverOnly"));
    }

    public boolean isHoverTextDisplayed() {
        return getHoverTextElement().isDisplayed();
    }

    public String getHoverText() {
        return getHoverTextElement().getText();
    }

    public void hoverOverTextTrigger() {
        WebElement textToHoverOver = driver.findElement(By.cssSelector("#hoverSection span"));
        Actions action = new Actions(driver);
        action.moveToElement(textToHoverOver).build().perform();
    }


    // Google Link

    public void clickOnGoogleLink() {
        driver.findElement(By.cssSelector("#linkSection a")).click();
    }

    public String getGoogleLinkCssValue(String cssName) {
        return driver.findElement(By.cssSelector("#linkSection a")).getCssValue(cssName);
    }


    // IFrames

    public String getTextInFirstIFrame() {
        return driver.findElement(By.tagName("h1")).getText();
    }

    public WebElement getSecondIFrame() {
        return driver.findElement(By.id("secondFrame"));
    }

    public String getTextInSecondIFrame() {
        return driver.findElement(By.tagName("h3")).getText();
    }

    public int getNumberOfWebElementsByTagName(String tagName) {
        return driver.findElements(By.tagName(tagName)).size();
    }


    // Alert

    public void clickAlertButton() {
        driver.findElement(By.cssSelector("#alertSection button")).click();
    }

    public String getAlertSelectionText() {
        return driver.findElement(By.id("alertResult")).getText();
    }


    // Drag and Drop

    private WebElement getImageOfDogElement() {
        return driver.findElement(By.cssSelector("#dragAndDropSection #drag1 img"));
    }

    private WebElement getEmptyBoxElement() {
        return driver.findElement(By.cssSelector("#dragAndDropSection #drop1"));
    }

    public void dragAndDropImageOfDogIntoEmptyBox() {
        Actions action = new Actions(Driver.getWebDriver());
        action.dragAndDrop(getImageOfDogElement(), getEmptyBoxElement()).build().perform();
    }

    public boolean isImageOfDogDisplayedInsideTheBox() {
        return driver.findElement(By.cssSelector("#dragAndDropSection #drop1 #drag1 img")).isDisplayed();
    }
}
