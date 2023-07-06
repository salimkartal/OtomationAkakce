package org.example;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import driver.Driver;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static driver.Driver.webDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class StepImplementation extends BasePage {

    @Step("Open to Akakce")
    public void openToAkakce(){

        webDriver.get("https://www.akakce.com/");
        assertThat(webDriver.getTitle()).contains("Ne Nerede En Ucuz Akakçe'de");
    }

    @Step("Sign up as <name> <surname> with email <customer@example.com> and <password>")
    public void registerCustomerWith(String name,String surname, String email, String password) throws InterruptedException {
        webDriver.findElement(By.linkText("Hesap Aç")).click();
        webDriver.findElement(By.id("rnufn")).sendKeys(name);
        webDriver.findElement(By.id("rnufs")).sendKeys(surname);
        webDriver.findElement(By.id("rnufe1")).sendKeys(email);
        webDriver.findElement(By.id("rnufe2")).sendKeys(email);
        webDriver.findElement(By.id("rnufp1")).sendKeys(password);
        webDriver.findElement(By.id("rnufp2")).sendKeys(password);
        webDriver.findElement(By.id("rngm")).click();
        webDriver.findElement(By.xpath("//*[@id=\"locpr\"]/option[2]")).click();//Şehir
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"locds\"]/option[2]")).click();//İlçe
        webDriver.findElement(By.xpath("//*[@id=\"bd\"]/option[2]")).click(); //Gün
        webDriver.findElement(By.xpath("//*[@id=\"bm\"]/option[13]")).click();//Ay
        webDriver.findElement(By.xpath("//*[@id=\"by\"]/option[12]")).click();//Yıl
        webDriver.findElement(By.id("rnufpca")).click();
        webDriver.findElement(By.id("rfb")).click();
        Thread.sleep(5000);

    }

    @Step("Search with <keyWord> <minValue> <maxValue>")
    public void SearchWithKey(String keyWord, String minValue, String maxValue) throws IOException,InterruptedException {

        webDriver.findElement(By.id("q")).sendKeys(keyWord);
        webDriver.findElement(By.xpath("//*[@id=\"H_s_v8\"]/button")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.id("pf1")).sendKeys(minValue);
        webDriver.findElement(By.id("pf2")).sendKeys(maxValue);
        webDriver.findElement(By.xpath("/html/body/div[2]/section/form[1]/span/span/button")).click();
        webDriver.findElement(By.linkText("En Yüksek Puan")).click();

        FileOutputStream file = new FileOutputStream("test.txt");
        TeePrintStream tee = new TeePrintStream(file, System.out);


        List<WebElement> listElement = webDriver.findElements(By.className("pn_v8"));
        for(int i =0;i<11;i++) {
            String elementText = listElement.get(i).getText();
            System.out.println(elementText);
            System.setOut(tee);
        }

        Thread.sleep(5000);
        webDriver.findElement(By.linkText("En Yüksek Fiyat")).click();

        List<WebElement> listElementTwo = webDriver.findElements(By.className("pn_v8"));
        for(int i =0;i<11;i++) {
            String elementText = listElementTwo.get(i).getText();
            System.out.println(elementText);
            System.setOut(tee);
        }
        Thread.sleep(2000);


    }

    @Step("Logout to Account")
    public void Logout() throws InterruptedException{
        WebDriverWait wait= new WebDriverWait(webDriver,Duration.ofSeconds(10));
        Thread.sleep(10000);
        WebElement element1=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("HM_v8")));
        Actions actions =new Actions(webDriver);
        actions.moveToElement(element1).build().perform();
        actions.moveToElement(element1,15,0).build().perform();
        Thread.sleep(5000);
        WebElement element2=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"HM_v8\"]/ul/li[5]/a")));
        element2.click();
        Thread.sleep(2000);
    }

    @Step("Login with <email> and <password>")
    public void Login(String email,String password) throws InterruptedException{
        webDriver.findElement(By.linkText("Giriş Yap")).click();
        webDriver.findElement(By.id("life")).sendKeys(email);
        webDriver.findElement(By.id("lifp")).sendKeys(password);
        webDriver.findElement(By.id("lfb")).click();
        Thread.sleep(2000);
    }

    /*
    @Step("Go to Gauge Get Started Page")
    public void gotoGetStartedPage() throws InterruptedException {
        WebElement getStartedButton = Driver.webDriver.findElement(By.xpath("//a[@href='https://docs.gauge.org/getting_started/installing-gauge.html']"));
        getStartedButton.click();

        Gauge.writeMessage("Page title is %s", Driver.webDriver.getTitle());
    }

    @Step("Ensure installation instructions are available")
    public void ensureInstallationInstructionsAreAvailable() throws InterruptedException {
        WebElement instructions = Driver.webDriver.findElement(By.xpath("//a[@href='/writing-specifications.html']"));
        assertThat(instructions).isNotNull();
    }

    @Step("Open the Gauge homepage")
    public void implementation1() {
        String app_url = System.getenv("APP_URL");
        Driver.webDriver.get(app_url + "/");
        assertThat(Driver.webDriver.getTitle()).contains("Gauge");
    }

     */
}
