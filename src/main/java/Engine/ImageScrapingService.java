package Engine;
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
    public ImageScrapingService(boolean headless,String Browser_name)
    {


        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver"); //Path To The Chosen WebDriver
        System.setProperty("webdriver.gecko.driver", "E:\\gecko\\geckodriver.exe");
       // System.setProperty(Properties.driver_type, Properties.path_to_binary);
        this.headless=headless;


        fo=new FirefoxOptions();
        fo.setHeadless(headless);
        fd=new FirefoxDriver(fo);// initiate a firefox driver with the given configuration


    }

    public void start()
    {


    }
    public String search(String query)throws Exception
    {
        String url="https://www.google.com/search?q="+query+"&hl=EN&tbm=isch"+"";

        fd.get(url);
        sleep(1000);    // Page Loading Delay


            String xpath_pattern = "/html/body/div[2]/c-wiz/div[3]/div[1]/div/div/div/div[1]/div[1]/span/div[1]/div[1]/div[" + "" + "]/a[1]/div[1]/img";

      System.out.println("Gathering images");
         List<WebElement> images= fd.findElements(By.className("rg_i"));
        System.out.println("Images Gathered");
            Iterator<WebElement> image=images.iterator();
            while(image.hasNext()) {
                image.next().click();
                sleep(1000);
                System.out.println(fd.findElement(By.xpath("/html/body/div[2]/c-wiz/div[3]/div[2]/div[3]/div/div/div[3]/div[2]/c-wiz/div/div[1]/div[1]/div[2]/div[1]/a/img")).getAttribute("src"));
                sleep(1000);
            }
        System.out.println("quits");
        sleep(10000);

        return "";
    }
    public void stop()
    {

        fd.quit();
    }


}
