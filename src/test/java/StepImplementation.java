import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

public class StepImplementation extends HookImplementation{

    @Step("click <id>")
    public void click(String element) {
        appiumDriver.findElement(By.id(element)).click();
        logger.info(element+"tiklandi");
        }
    @Step("click element<xpath>")
    public void clickXpath(String element) {
        appiumDriver.findElement(By.xpath(element)).click();
        logger.info(element+"tiklandi");
    }
        @Step("wait until visible <id>")
        public void gorunurbekle(String id){
            WebDriverWait wait = new WebDriverWait(appiumDriver,20);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        }
    @Step("wait until visible xpath <xpath>")
    public void gorunurbekleXpath(String id){
        WebDriverWait wait = new WebDriverWait(appiumDriver,20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(id)));
    }
    @Step("<id>'li elemente <deger> key gonder")
    public void sendKeys(String id,String deger){
        appiumDriver.findElement(By.id(id)).sendKeys(deger);
        logger.info("sended"+deger);
    }
    @Step("send <xpath> key to <value> element")
    public void sendKeysXpath(String id,String deger){

        appiumDriver.findElement(By.xpath(id)).sendKeys(deger);
    }
    @Step("<id>click one of the random elements named")
    public void clickRandomByname(String id) {
        List<MobileElement> elements = appiumDriver.findElements(By.xpath(id));
        Random rand = new Random();
        int randomNumber = rand.nextInt(elements.size());
        elements.get(randomNumber).click();
    }
    @Step("scroll element")
    public void scroll(){
        Dimension dimensions = appiumDriver.manage().window().getSize();
        int startPoint = (int) (dimensions.getHeight() * 0.9);
        int endPoint = (int) (dimensions.getHeight() * 0.2);
        TouchAction action = new TouchAction(appiumDriver);
        action.longPress(point(500,startPoint));
        action.moveTo(point(500,endPoint));
        action.release();
        action.perform();
    }

    @Step("<yon> yönüne swipe et")
    public void swipe(String yon) {

        Dimension d = appiumDriver.manage().window().getSize();
        int height = d.height;
        int width = d.width;

        if (yon.equals("SAĞ")) {

            int swipeStartWidth = (width * 80) / 100;
            int swipeEndWidth = (width * 30) / 100;

            int swipeStartHeight = height / 2;
            int swipeEndHeight = height / 2;

            //appiumDriver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);
            new TouchAction(appiumDriver)
                    .press(PointOption.point(swipeStartWidth, swipeStartHeight))
                    .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                    .moveTo(PointOption.point(swipeEndWidth, swipeEndHeight))
                    .release()
                    .perform();
        } else if (yon.equals("SOL")) {

            int swipeStartWidth = (width * 30) / 100;
            int swipeEndWidth = (width * 80) / 100;

            int swipeStartHeight = height / 2;
            int swipeEndHeight = height / 2;

            //appiumDriver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);

            new TouchAction(appiumDriver)
                    .press(PointOption.point(swipeStartWidth, swipeStartHeight))
                    .waitAction(WaitOptions.waitOptions(ofMillis(1000)))
                    .moveTo(PointOption.point(swipeEndWidth, swipeEndHeight))
                    .release()
                    .perform();

        }
    }
    @Step("find and clear <key>ed element")
    public void clearTextByKey(String key) {
        MobileElement webElement = appiumDriver.findElement(By.xpath(key));
        webElement.clear();
    }
    @Step("<id> li element varsa tıkla")
    public void tapElementWithKeyControl(String id) {
        MobileElement e1= appiumDriver.findElement(By.id(id));
        try
        {
            if (e1.isEnabled())
            {
                e1.click();
            }
        }
        catch (NoSuchElementException e) {
            System.out.println("devam et");
        }
       }
}




