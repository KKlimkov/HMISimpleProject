import io.qameta.allure.*;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Scanner;
import io.qameta.allure.LabelAnnotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Owner("KKlimkov")
@Feature("HMITestCases")
@TestMethodOrder(OrderAnnotation.class)
public class HMITest {

    public static ChromeDriver driver;
    public static String IdText = "";
    public static String IdWindowPump = "";
    public WebElement getShadowRootElement(WebElement element) {
        WebElement ele = (WebElement) ((JavascriptExecutor)driver)
                .executeScript("return arguments[0].shadowRoot", element);
        return ele;
    }

    @BeforeAll
    @Step("Запуск драйвера и чтение id файлов")
    static void setUp() throws IOException {

        String fileName = "C:\\Users\\kiril\\Desktop\\Autotests\\IDE\\Data.csv";
        Optional<String> line = Files.lines(Paths.get(fileName)).findFirst();
        String[] words = line.get().split(",");
        IdText = words[0];
        IdWindowPump = words[2];

        System.setProperty("webdriver.chrome.driver","C:\\Users\\kiril\\Desktop\\Autotests\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions ChromeOptions = new ChromeOptions();
        ChromeOptions.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
        //driver = new ChromeDriver(ChromeOptions);
        driver = new ChromeDriver();
        System.out.println("test start");
    }

    @DisplayName("ConnectAndTitle")
    @Test
    @Tags({@Tag("HMI"),@Tag("ProjectTitle")})
    @Story("Base.Objects Насос")
    @Order(1)
    @Step("Проверка имени тестового проекта")
    public void GetTitle() throws InterruptedException {
        String Host = "http://"+System.getProperty("HostIP")+"/";
        //String Host = "http://127.0.0.1:8043/";
        driver.get(Host);
        String title = driver.getTitle();
        WebDriverWait waitForOne = new WebDriverWait(driver, 1000);
        assertTrue(title.equals("Тестовый проект"));
        //HMISteps.AttemptUseStep(driver,"Тестовый проект");
        Thread.sleep(1000);
    }

    @DisplayName("StartPump")
    @Story("Base.Objects Насос")
    @Test
    @Tag("HMI")
    @Order(2)
    @Step("Запуск насоса")
    public void PumpStart() throws InterruptedException {

        WebElement element = driver.findElement(By.xpath("//*[@id='" + IdWindowPump + "']"));
        element.click();
        Thread.sleep(1000);
        WebElement element1 = driver.findElement(By.xpath("//*[@id='18733']"));
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
    @Step("Отключение насоса")
    @Story("Base.Objects Насос")
    public void PumpStop() throws InterruptedException {

        WebElement element = driver.findElement(By.xpath("//*[@id='" + IdWindowPump + "']"));
        WebElement element1 = driver.findElement(By.xpath("//*[@id='18789']"));
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
    @Step("Включение насоса")
    @Story("Base.Objects Насос")
    public void PumpStartTwo() throws InterruptedException {

        WebElement element = driver.findElement(By.xpath("//*[@id='" + IdWindowPump + "']"));
        WebElement element1 = driver.findElement(By.xpath("//*[@id='18733']"));
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
    @Step("Закрытие всплываюшего окна")
    @Story("Base.Objects Насос")
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
    @Step("Отключение web-драйвера")
    public static void cleanUp(){
        driver.quit();
        System.out.println("After All cleanUp() method called");
    }

}
