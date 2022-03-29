import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.Step;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;


public class HookImplementation {
    protected Logger logger = Logger.getLogger(HookImplementation.class);
    protected static AppiumDriver<MobileElement> appiumDriver;
    protected static FluentWait<AppiumDriver<MobileElement>> appiumFluentWait;
    protected boolean localAndroid = true;

    @BeforeScenario
    public void beforeScenario() throws IOException {



        if (localAndroid) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities
                    .setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
            desiredCapabilities
                    .setCapability(AndroidMobileCapabilityType.APP_PACKAGE,
                            "com.m.qr");
            desiredCapabilities
                    .setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
                            "com.m.qr.home.onboarding.ui.OnBoardingActivity");
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 3000);
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            appiumDriver = new AndroidDriver(url, desiredCapabilities);
        } else {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities
                    .setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            desiredCapabilities
                    .setCapability(MobileCapabilityType.UDID, "00008030-00157936018B802E");
            desiredCapabilities
                    .setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.monitise.mea.CCI.TNEXT");
            desiredCapabilities
                    .setCapability(MobileCapabilityType.DEVICE_NAME, "iPhonebatu");
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14.0.1");
            desiredCapabilities.setCapability("connectHardwareKeyboard", false);
            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            appiumDriver = new IOSDriver(url, desiredCapabilities);
        }

        //appiumDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        appiumFluentWait = new FluentWait<AppiumDriver<MobileElement>>(appiumDriver);
        appiumFluentWait.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(450))
                .ignoring(NoSuchElementException.class);

    }

    @AfterScenario
    public void afterScenario() {
        if(appiumDriver != null)
            appiumDriver.quit();
    }
}