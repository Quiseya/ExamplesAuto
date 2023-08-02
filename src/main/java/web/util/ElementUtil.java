package web.util;

import io.netty.channel.ConnectTimeoutException;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.fail;
import static web.util.WebConfig.BASE_CONFIG;

public class ElementUtil extends MainUtil {

    private static final Object clock = new Object();

    private static final String httpConnectionTimeout = BASE_CONFIG.httpConnectionTimeout();


    @Step("Переход по ссылке")
    public static void goToUrl(String url) {
        try {
            connect(url);
        } catch (Exception e) {
            if (e instanceof ConnectTimeoutException) {
                try {
                    connect(url);
                } catch (Exception t) {
                    if (t instanceof ConnectTimeoutException) {
                        saveScreenshot();
                        fail("Ошибка соединения с сервером по адресу = " + url);
                    } else {
                        fail("Ошибка, что-то пошло не так... Результат ошибки = " + e.getMessage());
                    }
                }
            } else {
                try {
                    connect(url);
                } catch (Exception t) {
                    saveScreenshot();
                    fail("Ошибка, что-то пошло не так... Результат ошибки = " + e.getMessage());
                }

            }
        }
    }

    private static void connect(String url) {
        var config = RestAssured.config = RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig().
                setParam("http.connection.timeout", Integer.parseInt(httpConnectionTimeout)).
                setParam("http.socket.timeout", Integer.parseInt(httpConnectionTimeout)));
        given().config(config).get(url);
        getDriver().get(url);
        System.out.println("Стенд = " + getDriver().getCurrentUrl());
        getDriver().getPageSource();
    }

    @Step("Скриншот")
    @Attachment(value = "Большая картинка, мяу")
    public static byte[] saveScreenshot() {
        var scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
        return scrFile;
    }

    public static void generateAllureEnvironment() {
        File source = new File("src/main/resources/environment.properties");
        File dest = new File("target/allure-results");

        var list = List.of("STAND=" + BASE_CONFIG.testUrl());
        try {
            synchronized (clock) {
                var patch = dest.getAbsoluteFile().getCanonicalPath();
                FileUtils.copyToDirectory(source, dest);
                FileUtils.writeLines(new File(patch + "environment.properties"), list);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Нажать на элемент {0}")
    public static void click(By xpath) {
        try {
            getDriver().findElement(xpath).click();
        } catch (NoSuchElementException e) {
            saveScreenshot();
            fail("Ошибка, элемент присутствует в HTML DOM, он не находится в состоянии, с которым можно взаимодействовать: элемент = " + xpath);
        } catch (UnhandledAlertException e) {
            switchToAlert(e);
        }
    }

    @Step("Переключиться на всплывающее окно")
    private static void switchToAlert(UnhandledAlertException e) {
        getDriver().switchTo().alert();
        saveScreenshot();
        fail("Ошибка. Непредвиденное Аллерт окно = " + e.getAlertText());
    }

    @Step("Наведение курсора на элемент по xpath {0}")
    public static void moveToElement(By xpath) {
        try {
            Actions builder = new Actions(getDriver());
            builder.moveToElement(getDriver().findElement(xpath)).perform();
        } catch (NoSuchElementException e) {
            saveScreenshot();
            fail("Ошибка, элемент xpath = " + xpath + " не найден на странице");
        } catch (UnhandledAlertException e) {
            switchToAlert(e);
        }
    }
    @Step("Проверить что по xpath {0}, ожидаемое значение {1}")
    public static void equals(By xpath, String expected) {
        try {
            var value = getDriver().findElement(xpath).getText();
            if (!expected.equalsIgnoreCase(value)) {
                saveScreenshot();
                fail("Ошибка, ожидаемый результат = " + expected + " не равен фактическому " + value);
            }
        } catch (NoSuchElementException e) {
            saveScreenshot();
            fail("Ошибка, элемент xpath = " + xpath + " не найден на странице");
        } catch (ElementNotInteractableException e) {
            saveScreenshot();
            fail("Ошибка, элемент присутствует в HTML DOM, он не находится в состоянии, с которым можно взаимодействовать: элемент = " + xpath);
        } catch (UnhandledAlertException e) {
            switchToAlert(e);
        }
    }
    @Step("Удаление текста по xpath {0}")
    public static void clearText(By xpath) {
        try {
            getDriver().findElement(xpath).clear();
        } catch (NoSuchElementException e) {
            saveScreenshot();
            fail("Ошибка, элемент xpath = " + xpath + " не найден на странице");
        } catch (ElementNotInteractableException e) {
            saveScreenshot();
            fail("Ошибка, элемент присутствует в HTML DOM, он не находится в состоянии, с которым можно взаимодействовать: элемент = " + xpath);
        } catch (UnhandledAlertException e) {
            switchToAlert(e);
        }
    }
    @Step("Ввести текст по xpath {0} со значением {1}")
    public static void sendKeys(By xpath, String value) {
        try {
            getDriver().findElement(xpath).sendKeys(value);
        } catch (NoSuchElementException e) {
            saveScreenshot();
            fail("Ошибка, элемент xpath = " + xpath + " не найден на странице со значением " + value);
        } catch (ElementNotInteractableException e) {
            saveScreenshot();
            fail("Ошибка, элемент присутствует в HTML DOM, он не находится в состоянии, с которым можно взаимодействовать: элемент = " + xpath);
        } catch (UnhandledAlertException e) {
            switchToAlert(e);
        }
    }
    @Step("Получить текст по xpath {0}")
    public static String getText(By xpath) {
        try {
            return getDriver().findElement(xpath).getText();
        } catch (NoSuchElementException e) {
            return "";
        } catch (ElementNotInteractableException e) {
            saveScreenshot();
            fail("Ошибка, элемент присутствует в HTML DOM, он не находится в состоянии, с которым можно взаимодействовать: элемент = " + xpath);
            return "";
        } catch (UnhandledAlertException e) {
            switchToAlert(e);
            return "";
        }
    }
    @Step("Сравнить адреса страниц")
    public static void checkUrls(String url){
        var currentUrl = getDriver().getCurrentUrl();
        if (!currentUrl.equalsIgnoreCase(url)){
            fail("фактический: "+currentUrl + " не равен ожидаемому: "+ url);
        }
    }
}








