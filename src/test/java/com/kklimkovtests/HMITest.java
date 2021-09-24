package com.kklimkovtests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;


@Owner("KKlimkov")
@Layer("HMI")
@Feature("BaseObjects")
@TestMethodOrder(OrderAnnotation.class)

public class HMITest {

    public static String IdText = "";
    public static String IdWindowPump = "";

    @BeforeAll
    @Step("Запуск драйвера и чтение id файлов")
    static void setUp() throws IOException, InterruptedException {
        String fileName = "C:\\Users\\kiril\\Desktop\\Autotests\\IDE\\Data.csv";
        Optional<String> line = Files.lines(Paths.get(fileName)).findFirst();
        String[] words = line.get().split(",");
        IdText = words[0];
        IdWindowPump = words[2];
        HMISteps.LaunchDriver();
    }

    @Test
    @DisplayName("ConnectAndTitle")
    @Story("Base.Objects Pump")
    @Tags({@Tag("HMI")})
    @Order(1)
    public void GetTitle() throws InterruptedException {
        HMISteps.CheckTitle("Тестовый проект");
        HMISteps.ClickButton(IdWindowPump);
        HMISteps.ClickButton("18733");
        HMISteps.CheckPumpClass(IdWindowPump,"Start");
        HMISteps.CheckOvalColor("18619","GREEN");
        HMISteps.ClickButton("18789");
        HMISteps.CheckPumpClass(IdWindowPump,"Stop");
        HMISteps.CheckOvalColor("18619","WHITE");
        HMISteps.ClickButton("18733");
        HMISteps.CheckOvalColor("18619","GREEN");
        HMISteps.CheckPumpClass(IdWindowPump,"Start");
        HMISteps.ClosePopUP("/html/body/div[2]/ms-popup");
    }
    @AfterAll
    public static void cleanUp(){
       HMISteps.StopDriver();
    }

}
