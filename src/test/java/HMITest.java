import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.io.*;
import java.util.Scanner;

@TestMethodOrder(OrderAnnotation.class)
public class HMITest {

    public static ChromeDriver driver;
    public static String IdText = "";
    public static String IdPump = "";

    public WebElement getShadowRootElement(WebElement element) {
        WebElement ele = (WebElement) ((JavascriptExecutor)driver)
                .executeScript("return arguments[0].shadowRoot", element);
        return ele;
    }

    @BeforeAll
    static void setUp() throws IOException {

        FileReader fr = new FileReader("C:\\Users\\kiril\\Desktop\\Autotests\\IDE\\Data.txt");
        Scanner scan = new Scanner(fr);
        String[] GetDataId = new String[4];
        int i = 1;
        while (scan.hasNextLine()) {
            GetDataId[i] = scan.nextLine();
            System.out.println(GetDataId[i]);
            i++;
        }
        fr.close();
        IdText = GetDataId[1];
        IdPump = GetDataId[3];

        System.setProperty("webdriver.chrome.driver","C:\\Users\\kiril\\Desktop\\Autotests\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions ChromeOptions = new ChromeOptions();
        ChromeOptions.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
        //driver = new ChromeDriver(ChromeOptions);
        driver = new ChromeDriver();
        System.out.println("test start");
    }

    @DisplayName("ConnectAndTitle")
    @Test
    @Tag("HMI")
    @Order(1)
    public void GetTitle() throws InterruptedException {
        driver.get("http://"+System.getProperty("HostIP")+"/");
        String title = driver.getTitle();
        WebDriverWait waitForOne = new WebDriverWait(driver, 1000);

        assertTrue(title.equals("Тестовый проект"));
        Thread.sleep(1000);
    }

    @DisplayName("StartPump")
    @Test
    @Tag("HMI")
    @Order(2)
    public void PumpStart() throws InterruptedException {

        WebElement element = driver.findElement(By.xpath("//*[@id='" + IdPump + "']"));
        element.click();
        Thread.sleep(1000);
        WebElement element1 = driver.findElement(By.xpath("//*[@id='18438']"));
        element1.click();
        Thread.sleep(1000);
        WebElement shadowRootPump = getShadowRootElement(element);
        WebElement shadowElementPump = shadowRootPump.findElement(By.cssSelector("svg[id='svgrootid']"));
        WebElement shadowElementPump2 = shadowElementPump.findElement(By.cssSelector("g[class]"));
        String SVGClass = shadowElementPump2.getAttribute("class");
        System.out.println(SVGClass);
        assertTrue(SVGClass.equals("Start"));
    }

    @DisplayName("StopPump")
    @Test
    @Tag("HMI")
    @Order(3)
    public void PumpStop() throws InterruptedException {

        WebElement element = driver.findElement(By.xpath("//*[@id='" + IdPump + "']"));
        WebElement element1 = driver.findElement(By.xpath("//*[@id='18494']"));
        element1.click();
        Thread.sleep(1000);
        WebElement shadowRootPump = getShadowRootElement(element);
        WebElement shadowElementPump = shadowRootPump.findElement(By.cssSelector("svg[id='svgrootid']"));
        WebElement shadowElementPump2 = shadowElementPump.findElement(By.cssSelector("g[class]"));
        String SVGClass = shadowElementPump2.getAttribute("class");
        System.out.println(SVGClass);
        assertTrue(SVGClass.equals("Stop"));
    }

    @DisplayName("StartPumpTwo")
    @Test
    @Tag("HMI")
    @Order(4)
    public void PumpStartTwo() throws InterruptedException {

        WebElement element = driver.findElement(By.xpath("//*[@id='" + IdPump + "']"));
        WebElement element1 = driver.findElement(By.xpath("//*[@id='18438']"));
        element1.click();
        Thread.sleep(1000);
        WebElement shadowRootPump = getShadowRootElement(element);
        WebElement shadowElementPump = shadowRootPump.findElement(By.cssSelector("svg[id='svgrootid']"));
        WebElement shadowElementPump2 = shadowElementPump.findElement(By.cssSelector("g[class]"));
        String SVGClass = shadowElementPump2.getAttribute("class");
        System.out.println(SVGClass);
        assertTrue(SVGClass.equals("Start"));
    }

    @DisplayName("ClosePopUP")
    @Test
    @Tag("HMI")
    @Order(5)
    public void ClosePopUP() throws InterruptedException {

    WebElement element3 = driver.findElement(By.xpath("/html/body/div[2]/ms-popup"));
    assertTrue(driver.findElements(By.xpath("/html/body/div[2]/ms-popup")).size() > 0);
    WebElement shadowRoot3 = getShadowRootElement(element3);
    System.out.println(shadowRoot3);
        WebElement shadowElement3 = shadowRoot3.findElement(By.cssSelector("div[class]"));
        System.out.println(shadowElement3);
        WebElement shadowElement4 = shadowElement3.findElement(By.cssSelector("div[class]"));
        WebElement shadowElement5 = shadowElement4.findElement(By.cssSelector("div[class]"));
        WebElement shadowElement6 = shadowElement5.findElement(By.cssSelector("button[id='closed']"));

        shadowElement6.click();

    }

    @AfterAll
    public static void cleanUp(){
        driver.quit();
        System.out.println("After All cleanUp() method called");
    }

}
