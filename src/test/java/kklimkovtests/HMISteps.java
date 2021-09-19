package kklimkovtests;

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


public class HMISteps {

    //@Step("Проверка имени тестового проекта")
    public static void AttemptUseStep(ChromeDriver driver, String ProjectName) throws InterruptedException {
        String Host = "http://"+System.getProperty("HostIP")+"/";
        //String Host = "http://127.0.0.1:8043/";
        driver.get(Host);
        String title = driver.getTitle();
        WebDriverWait waitForOne = new WebDriverWait(driver, 1000);
        assertTrue(title.equals(ProjectName));

    }

}
