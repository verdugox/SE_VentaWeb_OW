/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.se_ventaweb.se_ventaweb;

import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author mcetsqa
 */

public class VentaWeb_OW {

    private static WebDriver driver = null;

    @BeforeClass
    public static void inicializarDriver() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();

//        System.setProperty("webdriver.gecko.driver", "geckodriver"); 
//        driver = new FirefoxDriver();
    }

      @Test
    public void comprobarFlujoCorrectoBusqueda() throws InterruptedException {
        driver.get("http://testredcoach.mcets-inc.com/public/websale");

        //ORIGEN
        WebElement origen = driver.findElement(By.id("departureCityCbx-combobox"));
        origen.clear();
        origen.sendKeys("Miami - Airport, FL");
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.DOWN).build().perform();
        actions.sendKeys(Keys.ENTER).build().perform();
        actions.click();

        //DESTINO
        WebElement destino = driver.findElement(By.id("arrivalCityCbx-combobox"));
        destino.clear();
        destino.sendKeys("Orlando RedCoach Station, FL");
        Actions actions2 = new Actions(driver);
        actions2.sendKeys(Keys.DOWN).build().perform();
        actions2.sendKeys(Keys.ENTER).build().perform();
        actions2.click();

        //Calendario de IDA
        driver.findElement(By.id("dDate")).click();
        Thread.sleep(2000);
        WebElement fechaida = driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[2]/table/tbody/tr[6]/td[2]/a"));
        fechaida.click();

        WebElement btnSearch = driver.findElement(By.id("btnSearch"));
        Thread.sleep(2000);
        btnSearch.click();

    }

    @Test
    public void comprobarFlujoCorrectoSeleccion() throws InterruptedException {
        Thread.sleep(1000);
        List<WebElement> travelGO = driver.findElements(By.name("travelsGO"));
        for (WebElement Select_travelGO : travelGO) {
            if (!Select_travelGO.isSelected()) {
                Select_travelGO.click();
            }
        }

        WebElement btnStepSelectSeats = driver.findElement(By.id("btnStepSelectSeats"));
        btnStepSelectSeats.click();

    }

    @Test
    public void comprobarFlujoCorrectoAsientos() {
        List<WebElement> Asientos = driver.findElements(By.cssSelector(".bus-travel.iwsc-corner-all tbody tr td div.selectable"));
        int randomIndex = (int) (0 + Math.random() * Asientos.size());

        System.out.println("Voy a escoger el asiento: " + randomIndex);
        WebElement Asiento = Asientos.get(randomIndex);
        Asiento.click();
        WebElement btnStepPassInfo = driver.findElement(By.id("btnStepPassInfo"));
        btnStepPassInfo.click();
    }

    @Test
    public void comprobarFlujoCorrectoPasajeros() throws InterruptedException {

        //Nombre
        List<WebElement> nombres = driver.findElements(By.cssSelector("input[name^='p_firstName']"));
        WebElement nombre = nombres.get(0);

        nombre.sendKeys("Luis Andres");
        nombre.click();

        List<WebElement> apellidos = driver.findElements(By.cssSelector("input[name^='p_lastName']"));
        WebElement apellido = apellidos.get(0);

        apellido.sendKeys("Acuña Ramos");
        apellido.click();

        WebElement email = driver.findElements(By.cssSelector("input[name^='p_mail']")).get(0);
        email.sendKeys("luis.acuna@ibusplus.com");
        email.click();

        //Telefono
        WebElement telefono = driver.findElements(By.cssSelector("input[name^='p_phone_num']")).get(0);
        telefono.sendKeys("20304050");
        telefono.click();

        WebElement btnStepPurchase = driver.findElement(By.id("btnStepPurchase"));
        Thread.sleep(2000);
        btnStepPurchase.click();

        //TERMINOS Y CONDICIONES:
        if (!driver.findElement(By.id("termAndConditions")).isSelected()) {
            driver.findElement(By.id("termAndConditions")).click();
        }
        WebElement btn_accept = driver.findElement(By.id("btn-accept"));
        btn_accept.click();

        Thread.sleep(2000);
        WebElement p_nombre = driver.findElement(By.cssSelector("input[name^='firstName']"));
        p_nombre.sendKeys("Luis Andres");
        p_nombre.click();

        WebElement p_apellido = driver.findElement(By.cssSelector("input[name^='lastName']"));
        p_apellido.sendKeys("Acuna Ramos");
        p_apellido.click();

        WebElement p_numero_tarjeta = driver.findElement(By.id("creditCardNumber"));
        p_numero_tarjeta.sendKeys("5555555555554444");
        p_numero_tarjeta.click();

        driver.findElement(By.id("idcardExpMonth")).click();
        Thread.sleep(2000);
        WebElement idcardExpMonth = driver.findElement(By.xpath(".//*[@id='idcardExpMonth']/option[9]"));
        idcardExpMonth.click();
        
        driver.findElement(By.id("idcardExpYear")).click();
        Thread.sleep(2000);
        WebElement idcardExpYear = driver.findElement(By.xpath(".//*[@id='idcardExpYear']/option[8]"));
        idcardExpYear.click();

        WebElement p_verificacion = driver.findElement(By.id("cvv2Number"));
        p_verificacion.sendKeys("213");
        p_verificacion.click();

        WebElement c_postal = driver.findElement(By.id("zip"));
        c_postal.sendKeys("051");
        c_postal.click();

        WebElement btn_paypalRegister = driver.findElement(By.id("btn-paypalRegister"));
        Thread.sleep(2000);
        btn_paypalRegister.click();
        
    }
    
    //    @AfterClass
//    public static void liquidarDriver() {
//        driver.close();
//        driver.quit();
//    }

}
