package Engine;
import javafx.application.Platform;
import javafx.scene.control.ListView;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


import java.util.Iterator;
import java.util.List;

import static java.lang.Thread.sleep;

public class ImageScrapingService {
    ListView lv;


    boolean headless;
    //firefox driver
    FirefoxDriver fd;
    FirefoxOptions fo;

    //chrome driver
    ChromeDriver cd;
    ChromeOptions co;

    //Edge driver
    EdgeDriver ed;
    EdgeOptions eo;

    String browser_name;
    public ImageScrapingService(boolean headless,String Browser_name,ListView lv)
    {
        this.browser_name= Browser_name;
        this.lv=lv;
        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe"); //Path To The Chosen WebDriver
        System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
        System.setProperty("webdriver.edge.driver", "Drivers/msedgedriver.exe");
       // System.setProperty(Properties.driver_type, Properties.path_to_binary);"E:\\gecko\\geckodriver.exe"
        this.headless=headless;

     if(Browser_name.equals("F")) {
         fo = new FirefoxOptions();
         fo.setHeadless(headless);
         fd = new FirefoxDriver(fo);// initiate a firefox driver with the given configuration
     }
     else if(Browser_name.equals("C"))
     {
         co=new ChromeOptions();
         co.setHeadless(headless);
         cd=new ChromeDriver(co);

     }
     else
     {
         eo=new EdgeOptions();
         eo.setCapability("headless",true);
         ed=new EdgeDriver(eo);
     }

    }

    public void start()
    {


    }
    public String search(String query)throws Exception
    {
        System.out.println(browser_name+"  is the browser name");
        String urls="";
        String url = Properties.get("proxy") + "https://www.google.com/search?q=" + query + "&hl=EN&tbm=isch" + (Properties.get("cc").equals("YES")?"&tbs=il:cl":"")+"&tbs=isz:l";
        String creative_commons="&tbs=il:cl";
        if(browser_name.equals("F")) {


            fd.get(url);
            // Page Loading Delay


            List<WebElement> images = fd.findElements(By.className("rg_i"));
            Platform.runLater(() -> {
                lv.getItems().add(images.size() + " Found");
            });


            Platform.runLater(() -> {
                lv.getItems().add("Attempting To Scrape " + Properties.total_images);
            });
            Iterator<WebElement> image = images.iterator();
            int counter = 0;
            while (image.hasNext()) {
                image.next().click();
                sleep(1000);
                String source = fd.findElement(By.xpath("/html/body/div[2]/c-wiz/div[3]/div[2]/div[3]/div/div/div[3]/div[2]/c-wiz/div/div[1]/div[1]/div[2]/div[1]/a/img")).getAttribute("src");
                urls += source + ",";
                counter++;
                if (counter == Properties.total_images) {
                    break;
                }

                sleep(1000);
            }

        }
        else if(browser_name.equals("C")){
            System.out.println("and here");
            cd.get(url);
            System.out.println("here we are");
            List<WebElement> images = cd.findElements(By.className("rg_i"));

            Platform.runLater(() -> {
                lv.getItems().add("Attempting To Scrape " + Properties.total_images);
            });
            Iterator<WebElement> image = images.iterator();
            int counter = 0;
            while (image.hasNext()) {
                image.next().click();
                sleep(1000);
                String source = cd.findElement(By.xpath("/html/body/div[2]/c-wiz/div[3]/div[2]/div[3]/div/div/div[3]/div[2]/c-wiz/div/div[1]/div[1]/div[2]/div[1]/a/img")).getAttribute("src");
                urls += source + ",";
                counter++;
                if (counter == Properties.total_images) {
                    break;
                }

                sleep(1000);
            }
        }
        else{
            System.out.println("We Are Using Edge");
            ed.get(url);
            List<WebElement> images = ed.findElements(By.className("rg_i"));
            Platform.runLater(() -> {
                lv.getItems().add("Attempting To Scrape " + Properties.total_images);
            });
            Iterator<WebElement> image = images.iterator();
            int counter = 0;
            while (image.hasNext()) {
                image.next().click();
                sleep(1000);
                String source = ed.findElement(By.xpath("/html/body/div[2]/c-wiz/div[3]/div[2]/div[3]/div/div/div[3]/div[2]/c-wiz/div/div[1]/div[1]/div[2]/div[1]/a/img")).getAttribute("src");
                urls += source + ",";
                counter++;
                if (counter == Properties.total_images) {
                    break;
                }

                sleep(1000);
            }

        }
        return urls;
    }
    public void stop()
    {

        fd.quit();
    }


}
