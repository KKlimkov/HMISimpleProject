package com.kklimkovtests;

import io.qameta.allure.*;
import io.qameta.allure.model.Link;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HMISteps {
    public static ChromeDriver driver;

    public static WebElement getShadowRootElement(WebElement element) {
        WebElement ele = (WebElement) ((JavascriptExecutor)driver)
                .executeScript("return arguments[0].shadowRoot", element);
        return ele;
    }

    @Step("Запуск драйвера")
    public static void LaunchDriver() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Public\\Autotests\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions ChromeOptions = new ChromeOptions();
        //ChromeOptions.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
        //driver = new ChromeDriver(ChromeOptions);
        driver = new ChromeDriver();
        String Host = "http://"+System.getProperty("HostIP")+"/";
        //String Host = "http://127.0.0.1:8043/";
        driver.get(Host);
        System.out.println("test start");
        Thread.sleep(3000);
    }

    @Step("Остановка драйвера")
    public static void StopDriver()  {
        driver.quit();
        System.out.println("After All cleanUp() method called");
    }


    @Step("Проверка имени проекта")
    public static void CheckTitle(String ProjectName) throws InterruptedException {
        Allure.addLinks(new Link()
                .withName("TFS 13514")
                .withUrl("http://vpn.mps-soft.ru:8080/DefaultCollection/" +
                        "MS4/_workitems?id=13514&triage=true&fullScreen=false&_a=edit"));
        String title = driver.getTitle();
        WebDriverWait waitForOne = new WebDriverWait(driver, 1000);
        assertTrue(title.equals(ProjectName));
        Thread.sleep(1000);
    }

    @Step("Клик мыши на элемент по id")
    public static void ClickButton(String Id) throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//*[@id='" + Id + "']"));
        element.click();
        Thread.sleep(1000);
    }

    @Step("Проверка состояния насоса")
    public static void CheckPumpClass(String Id, String Value) throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//*[@id='" + Id + "']"));
        WebElement shadowRootPump = getShadowRootElement(element);
        WebElement shadowElementPump = shadowRootPump.findElement(By.cssSelector("svg[id='svgrootid']"));
        WebElement shadowElementPump2 = shadowElementPump.findElement(By.cssSelector("g[class]"));
        String SVGClass = shadowElementPump2.getAttribute("class");
        System.out.println(SVGClass);
        assertTrue(SVGClass.equals(Value));
    }

    @Step("Проверка цвета овала")
    public static void CheckOvalColor(String Id, String Value) throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//*[@id='" + Id + "']"));
        WebElement shadowRootPump = getShadowRootElement(element);
        WebElement shadowElementPump = shadowRootPump.findElement(By.cssSelector("svg"));
        WebElement shadowElementPump2 = shadowElementPump.findElement(By.cssSelector("ellipse[fill]"));
        String SVGClass = shadowElementPump2.getAttribute("fill");
        System.out.println(SVGClass);
        assertTrue(SVGClass.equals(Value));
    }

    @Step("Закрытие всплывающего окна")
    public static void ClosePopUP(String Div) throws InterruptedException {
        WebElement element3 = driver.findElement(By.xpath(Div));
        assertTrue(driver.findElements(By.xpath(Div)).size() > 0);
        WebElement shadowRoot3 = getShadowRootElement(element3);
        System.out.println(shadowRoot3);
        WebElement shadowElement3 = shadowRoot3.findElement(By.cssSelector("div[class]"));
        System.out.println(shadowElement3);
        WebElement shadowElement4 = shadowElement3.findElement(By.cssSelector("div[class]"));
        WebElement shadowElement5 = shadowElement4.findElement(By.cssSelector("div[class]"));
        WebElement shadowElement6 = shadowElement5.findElement(By.cssSelector("button[id='closed']"));
        shadowElement6.click();
    }

}
