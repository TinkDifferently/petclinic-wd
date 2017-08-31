package ru.pflb.wd;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.concurrent.TimeUnit;
//import static org.assertj.core.api.Assertions.assertThat;


import static org.openqa.selenium.firefox.FirefoxDriver.PROFILE;

/**
 * @author <a href="mailto:8445322@gmail.com">Ivan Bonkin</a>.
 */
public class PetclinicTest {

    /**
     * При клике по меню "Pet Types" не должно появляться 'Not Found - 404 error'
     */
    @Test(expected = NoSuchElementException.class)
    public void shouldDisplayPetTypes() {
        System.setProperty("webdriver.chrome.driver", new File("src/main/resources/chromedriver.exe").getAbsolutePath());
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://localhost:4200/petclinic/");

        driver.findElement(By.xpath("//span[text()='Pet Types']")).click();

       // System.out.print(
                driver.findElement(By.xpath("//h1[text()='List of pets']")).getText();
    }
    @Test (expected = ClassCastException.class)
    public void AddOwnerAndMakeTheCheck() { //throws IOException, URISyntaxException{
        System.setProperty("webdriver.chrome.driver", new File("src/main/resources/chromedriver.exe").getAbsolutePath());
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost:4200/petclinic/");

        driver.findElement(By.xpath("//div[1]/nav/div/ul/li[2]/a")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//li[2]/ul/li[2]/a")).click();
       // driver.findElement(By.xpath("//li[2]/ul/li[2]/a")).click();;
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='firstName']")).sendKeys("Korwin");
        driver.findElement(By.xpath("//*[@id='lastName']")).sendKeys("Oberon");
        driver.findElement(By.xpath("//*[@id='address']")).sendKeys("BakerStreet 221B");
        String s="London"+System.currentTimeMillis();
        driver.findElement(By.xpath("//*[@id='city']")).sendKeys(s);
        driver.findElement(By.xpath("//*[@id='telephone']")).sendKeys("799999123");
        driver.findElement(By.xpath("//div[7]/div/button[2]")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String G="coll";
        //driver.findElement(By.xpath("//span[text()=London]")).click();

           for (int i = 1; ; i++) {//System.out.print(driver.findElement(By.xpath("//div/div/div/table/tbody/tr[" + Integer.toString(i) + "]/td[2]")).getText());
               //System.out.print(driver.findElement(By.xpath("//div/div/div/table/tbody/tr[" + Integer.toString(i) + "]/td[3]")).getText());
             //  System.out.print(driver.findElement(By.xpath("//div/div/div/table/tbody/tr[" + Integer.toString(i) + "]/td[4]")).getText()); System.out.println();
                           try {G = driver.findElement(By.xpath("//tbody/tr[" + Integer.toString(i) + "]/td[1]/a")).getText();} catch (NoSuchElementException E){throw new NullPointerException("The owner was not found"); }
               if (G.compareTo("Korwin Oberon")==0) System.out.println(G);
               if (G.compareTo("Korwin Oberon")==0)
                   if (driver.findElement(By.xpath("//tbody/tr[" + Integer.toString(i) + "]/td[2]")).getText().compareTo("BakerStreet 221B")==0)
                       if (driver.findElement(By.xpath("//tbody/tr[" + Integer.toString(i) + "]/td[3]")).getText().compareTo(s)==0)
                           if (driver.findElement(By.xpath("//tbody/tr[" + Integer.toString(i) + "]/td[4]")).getText().compareTo("799999123")==0)
                               try {if (driver.findElement(By.xpath("//table/tbody/tr["+Integer.toString(i)+"]/td[5]/tbody/tr")).getText().compareTo("THIS MUST ALWAYS FAIL")==0) System.out.print("THIS MUST ALWAYS FAIL");} catch (NoSuchElementException E) {throw new ClassCastException("No pets here, be happy");};


                  // System.out.println(G);
           }

        //System.out.println(G);
    }

    @Test
    public void shouldFindOwnerAndChangeHisName() throws IOException, URISyntaxException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        FirefoxProfile profile = new FirefoxProfile();

        File firebug = new File(FirefoxDriver.class.getResource("/firebug-1.12.7-fx.xpi").toURI());
        File firepath = new File(FirefoxDriver.class.getResource("/firepath-0.9.7-fx.xpi").toURI());

        profile.addExtension(firebug);
        profile.addExtension(firepath);

        profile.setPreference("extensions.firebug.showFirstRunPage", false);
        capabilities.setCapability(PROFILE, profile);
        WebDriver driver = new FirefoxDriver(capabilities);


        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);



        driver.get("http://localhost:8080/");

        // клик по Find Owners
        driver.findElement(By.xpath("//span[text()='Find owners']")).click();

        // ввод фамилии
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Franklin");

        // клик по Find Owner
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        System.out.printf("TODO");
    }



    /**
     * Домашнее задание.
     * <p>
     * Сценарий:<ol>
     * <li>Открыть http://localhost:4200/</li>
     * <li>Перейти в меню Owners -> Add new</li>
     * <li>Ввести валидные случайные данные (новые для каждого запуска теста)</li>
     * <li>Нажать Add Owner, открылась страница Owners</li>
     * <li>Проверить, что добавилась новая запись, и все ее поля соответствуют введенным значениям</li>
     * </ul>
     */
    public void shouldValidateAddedUser() {
        // TODO
    }
}
