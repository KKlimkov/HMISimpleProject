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
