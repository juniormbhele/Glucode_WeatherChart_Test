import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sphiwe.Mbhele on 2017/11/06.
 */

public class WeatherChartTestSuite
{
    WebDriver driver;
    AppiumDriver appDriver;
    AndroidDriver andrDriver;
    private String firstCity = "Durban";
    private String secondCity = "Johannesburg";
    private String _forthCity = "London";
    private String _thirdCity = "Pretoria";

    @BeforeTest
    public void setUp() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        // Android deviceName
        capabilities.setCapability("deviceName", "XT1562");

        //BROWSER_NAME
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");

        // Android VERSION
        capabilities.setCapability(CapabilityType.VERSION, "6.0.1");

        // Android platformName
        capabilities.setCapability("platformName", "Android");

        // Android appPackage
        capabilities.setCapability("appPackage", "com.tjaartviljoen.weatherchart");

        // Android appActivity
        capabilities.setCapability("appActivity", "com.tjaartviljoen.weatherchart.activity.MainActivity");

        // Created object of RemoteWebDriver will all set capabilities.
        // Set appium server address and port number in URL string.
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }

    @Test(description = "Adding first city to the list", priority = 1)
    protected void AddFirstCity() throws InterruptedException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date dateobj = new Date();

        String DateTime = ((df.format(dateobj)));
        String cityName = firstCity;

        Reporter.log("Add City Test STARTED AT: " + DateTime);

        Boolean isPresent = driver.findElements(By.id("button_add_city"))
                .size() > 0;

        if (isPresent) {

            driver.findElement(By.id("button_add_city")).click();
            Thread.sleep(500);

        } else
            {
                driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]")).click();
                driver.findElement(By.xpath("hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.support.v7.widget.LinearLayoutCompat[1]/android.widget.CheckedTextView")).click();
                Thread.sleep(500);
                Reporter.log("Couldn't find a Add City Button");

            }

        Boolean isPresentAutocomplete = driver.findElements(By.id("place_autocomplete_search_input"))
                .size() > 0;

        if (isPresentAutocomplete)
        {
            WebElement autoComplete = driver.findElement(By.id("place_autocomplete_search_input"));
            autoComplete.click();
        }
        else
        {
            Reporter.log("Couldn't find a Google AutoComplete Box");
        }

        Boolean isPresentEdit_text = driver.findElements(By.id("com.google.android.gms:id/edit_text"))
                .size() > 0;

        if (isPresentEdit_text)
        {
            driver.findElement(By.id("com.google.android.gms:id/edit_text")).sendKeys(cityName);

        }
        else
        {

            Reporter.log("Couldn't type in a Google AutoComplete Box");
        }

        Boolean isPresentCityEntry = driver.findElements(By.id("com.google.android.gms:id/place_autocomplete_prediction_primary_text"))
                .size() > 0;

        if (isPresentCityEntry)
        {
            driver.findElement(By.id("com.google.android.gms:id/place_autocomplete_prediction_primary_text")).click();
        }
        else
        {
            Reporter.log("Couldn't find a City " +cityName+" Entry");
        }

        Boolean isPresentAddCity = driver.findElements(By.id("com.tjaartviljoen.weatherchart:id/button_add_city"))
                .size() > 0;
        if (isPresentAddCity)
        {
            driver.findElement(By.id("com.tjaartviljoen.weatherchart:id/button_add_city")).click();
            Thread.sleep(50);
        }
        else
        {
            Reporter.log("Couldn't find a a second Add City button");
        }

        driver.navigate().back();
       // driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);


    }

    @Test (description = "View First city's details", priority = 5)
    public void ViewFirstCityWeather() throws InterruptedException
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date dateobj = new Date();

        String DateTime = ((df.format(dateobj)));
        String cityName = firstCity;

        Reporter.log("View City Test STARTED AT: " + DateTime);

        Boolean isCityPresent = driver.findElements(By.id("com.tjaartviljoen.weatherchart:id/recycler_text_view_city_name"))
                .size() > 0 && driver.findElement(By.id("com.tjaartviljoen.weatherchart:id/recycler_text_view_city_name")).getText().equalsIgnoreCase(cityName);
        if (isCityPresent)
        {
            driver.findElement(By.id("com.tjaartviljoen.weatherchart:id/recycler_text_view_city_name")).click();

            Reporter.log(""+cityName+" city was available in the main screen");
            Thread.sleep(500);
        }
        else
        {
            Reporter.log("Couldn't find a Add City Button");
            this.AddFirstCity();

            driver.findElement(By.id("com.tjaartviljoen.weatherchart:id/recycler_text_view_city_name")).click();
            System.out.println("It's getting here");
            Thread.sleep(500);
        }
        driver.navigate().back();

    }

    @Test (description = "Adding second city to the list", priority = 2)
    public void AddSecondCity() throws InterruptedException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date dateobj = new Date();

        String DateTime = ((df.format(dateobj)));
        String cityName = secondCity;

        Reporter.log("Add City Test STARTED AT: " + DateTime);

        Boolean isPresent = driver.findElements(By.id("button_add_city"))
                .size() > 0;
        if (isPresent)
        {
            driver.findElement(By.id("button_add_city")).click();
            Thread.sleep(500);
        } else
        {
            driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]")).click();
            driver.findElement(By.xpath("hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.support.v7.widget.LinearLayoutCompat[1]/android.widget.CheckedTextView")).click();
            Thread.sleep(500);
            Reporter.log("Couldn't find a Add City Button");
        }

        Boolean isPresentAutocomplete = driver.findElements(By.id("place_autocomplete_search_input"))
                .size() > 0;

        if (isPresentAutocomplete)
        {
            WebElement autoComplete = driver.findElement(By.id("place_autocomplete_search_input"));
            autoComplete.click();
        }
        else
        {
            Reporter.log("Couldn't find a Google AutoComplete Box");
        }

        Boolean isPresentEdit_text = driver.findElements(By.id("com.google.android.gms:id/edit_text"))
                .size() > 0;
        if (isPresentEdit_text)
        {
            driver.findElement(By.id("com.google.android.gms:id/edit_text")).sendKeys(cityName);
        }
        else
        {
            Reporter.log("Couldn't type in a Google AutoComplete Box");
        }

        Boolean isPresentCityEntry = driver.findElements(By.xpath("//android.widget.TextView[@text='" + cityName + "']"))
                .size() > 0;
        System.out.println(isPresentCityEntry);

        if (isPresentCityEntry)
        {
            driver.findElement(By.xpath("//android.widget.TextView[@text='" + cityName + "']")).click();
        }
        else
        {
            Reporter.log("Couldn't find a City " +cityName+" Entry");
        }

        Boolean isPresentAddCity = driver.findElements(By.id("com.tjaartviljoen.weatherchart:id/button_add_city"))
                .size() > 0;
        if (isPresentAddCity)
        {
            driver.findElement(By.id("com.tjaartviljoen.weatherchart:id/button_add_city")).click();
            Thread.sleep(50);
        }
        else
        {
            Reporter.log("Couldn't find a a second Add City button");
        }
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);

        driver.navigate().back();
    }

    @Test (description = "View second city's details", priority = 6)
    public void ViewSecondCityWeather() throws InterruptedException
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date dateobj = new Date();

        String DateTime = ((df.format(dateobj)));
        String cityName = secondCity;

        Reporter.log("View City Test STARTED AT: " + DateTime);

        Boolean isCityPresent = driver.findElements(By.xpath("//android.widget.TextView[@text='"+cityName+"']"))
                .size() > 0;
        if (isCityPresent)
        {

            driver.findElement(By.xpath("//android.widget.TextView[@text='"+cityName+"']")).click();

            Reporter.log("" + cityName + "City was available in the main screen");
            Thread.sleep(500);

        }
        else
        {

            Reporter.log("Couldn't find a Add City Button");
            this.AddSecondCity();

            driver.findElement(By.xpath("//android.widget.TextView[@text='" + cityName + "']")).click();
            System.out.println("It's getting here");
            Thread.sleep(500);
        }

        driver.navigate().back();

    }

    @Test(description = "Adding third city to the list", priority = 3)
    public void AddThirdCity() throws InterruptedException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date dateobj = new Date();

        String DateTime = ((df.format(dateobj)));
        String cityName = _thirdCity;

        Reporter.log("Add City Test STARTED AT: " + DateTime);

        Boolean isPresent = driver.findElements(By.id("button_add_city"))
                .size() > 0;
        if (isPresent)
        {
            driver.findElement(By.id("button_add_city")).click();
            Thread.sleep(500);

        }
        else
        {

            driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]")).click();
            driver.findElement(By.xpath("hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.support.v7.widget.LinearLayoutCompat[1]/android.widget.CheckedTextView")).click();
            Thread.sleep(500);
            Reporter.log("Couldn't find a Add City Button");

        }

        Boolean isPresentAutocomplete = driver.findElements(By.id("place_autocomplete_search_input"))
                .size() > 0;

        if (isPresentAutocomplete)
        {
            WebElement autoComplete = driver.findElement(By.id("place_autocomplete_search_input"));

            autoComplete.click();

        }
        else
        {
            Reporter.log("Couldn't find a Google AutoComplete Box");
        }

        Boolean isPresentEdit_text = driver.findElements(By.id("com.google.android.gms:id/edit_text"))
                .size() > 0;

        if (isPresentEdit_text)
        {
            driver.findElement(By.id("com.google.android.gms:id/edit_text")).sendKeys(cityName);
        }
        else
        {
            Reporter.log("Couldn't type in a Google AutoComplete Box");
        }

        Boolean isPresentCityEntry = driver.findElements(By.id("com.google.android.gms:id/place_autocomplete_prediction_primary_text"))
                .size() > 0;
        if (isPresentCityEntry)
        {
            driver.findElement(By.id("com.google.android.gms:id/place_autocomplete_prediction_primary_text")).click();
        }
        else
        {
            Reporter.log("Couldn't find a City " +cityName+" Entry");
        }

        Boolean isPresentAddCity = driver.findElements(By.id("com.tjaartviljoen.weatherchart:id/button_add_city"))
                .size() > 0;
        if (isPresentAddCity)
        {
            driver.findElement(By.id("com.tjaartviljoen.weatherchart:id/button_add_city")).click();
            Thread.sleep(50);
        }
        else
        {
            Reporter.log("Couldn't find a a second Add City button");
        }

        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        driver.navigate().back();
    }

    @Test (description = "View third city's details", priority = 7)
    public void ViewThirdCityWeather() throws InterruptedException
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date dateobj = new Date();

        String DateTime = ((df.format(dateobj)));
        String cityName = _thirdCity;

        Reporter.log("View City Test STARTED AT: " + DateTime);

        Boolean isCityPresent = driver.findElements(By.id("com.tjaartviljoen.weatherchart:id/recycler_text_view_city_name"))
                .size() > 0 && driver.findElement(By.id("com.tjaartviljoen.weatherchart:id/recycler_text_view_city_name")).getText().equals(cityName);

        if (isCityPresent)
        {
            driver.findElement(By.id("com.tjaartviljoen.weatherchart:id/recycler_text_view_city_name")).click();
            Reporter.log(""+cityName+" city was available in the main screen");
            Thread.sleep(500);
        }
        else
        {
            Reporter.log("Couldn't find a Add City Button");
            this.AddThirdCity();

            driver.findElement(By.id("com.tjaartviljoen.weatherchart:id/recycler_text_view_city_name")).click();
            Thread.sleep(500);
        }
        driver.navigate().back();
    }

    @Test (description = "Adding forth city to the list", priority = 4)
    public void AddForthCity() throws InterruptedException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date dateobj = new Date();

        String DateTime = ((df.format(dateobj)));
        String cityName = _forthCity;

        Reporter.log("Add City Test STARTED AT: " + DateTime);

        Boolean isPresent = driver.findElements(By.id("button_add_city"))
                .size() > 0;

        if (isPresent) {


            driver.findElement(By.id("button_add_city")).click();
            Thread.sleep(500);

        } else
        {

            driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]")).click();
            driver.findElement(By.xpath("hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.support.v7.widget.LinearLayoutCompat[1]/android.widget.CheckedTextView")).click();
            Thread.sleep(500);
            Reporter.log("Couldn't find a Add City Button");
        }

        Boolean isPresentAutocomplete = driver.findElements(By.id("place_autocomplete_search_input"))
                .size() > 0;

        if (isPresentAutocomplete)
        {
            WebElement autoComplete = driver.findElement(By.id("place_autocomplete_search_input"));

            autoComplete.click();
        }
        else
        {
            Reporter.log("Couldn't find a Google AutoComplete Box");
        }

        Boolean isPresentEdit_text = driver.findElements(By.id("com.google.android.gms:id/edit_text"))
                .size() > 0;

        if (isPresentEdit_text)
        {
            driver.findElement(By.id("com.google.android.gms:id/edit_text")).sendKeys(cityName);
        }
        else
        {
            Reporter.log("Couldn't type in a Google AutoComplete Box");
        }

        Boolean isPresentCityEntry = driver.findElements(By.xpath("//android.widget.TextView[@text='" + cityName + "']"))
                .size() > 0;
        if (isPresentCityEntry)
        {
            driver.findElement(By.xpath("//android.widget.TextView[@text='" + cityName + "']")).click();
        }
        else
        {
            Reporter.log("Couldn't find a City " +cityName+" Entry");
        }

        Boolean isPresentAddCity = driver.findElements(By.id("com.tjaartviljoen.weatherchart:id/button_add_city"))
                .size() > 0;
        if (isPresentAddCity)
        {
            driver.findElement(By.id("com.tjaartviljoen.weatherchart:id/button_add_city")).click();
            Thread.sleep(50);
        }
        else
        {
            Reporter.log("Couldn't find a a second Add City button");
        }

        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        driver.navigate().back();

    }

    @Test (description = "View forth city's details", priority = 8)
    public void ViewForthCityWeather() throws InterruptedException
    {


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date dateobj = new Date();

        String DateTime = ((df.format(dateobj)));
        String cityName = _forthCity;

        Reporter.log("View City Test STARTED AT: " + DateTime);

        Boolean isCityPresent = driver.findElements(By.xpath("//android.widget.TextView[@text='"+cityName+"']"))
                .size() > 0;

        if (isCityPresent) {

            driver.findElement(By.xpath("//android.widget.TextView[@text='"+cityName+"']")).click();

            Reporter.log("" + cityName + "City was available in the main screen");
            Thread.sleep(500);
        } else
        {
            Reporter.log("Couldn't find a Add City Button");

            this.AddForthCity();

            driver.findElement(By.xpath("//android.widget.TextView[@text='" + cityName + "']")).click();

            Thread.sleep(500);
        }


        driver.navigate().back();

    }


    @AfterTest
    public void End() {
        driver.close();
    }
}
