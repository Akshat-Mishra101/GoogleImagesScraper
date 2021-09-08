package Engine;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

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
       // System.setProperty(Properties.driver_type, Properties.path_to_binary);
        this.headless=headless;


        fo=new FirefoxOptions();
        fo.setHeadless(headless);
        fd=new FirefoxDriver(fo);// initiate a firefox driver with the given configuration


    }

    public void start()
    {


    }
    public String search(String query)
    {
        String url="https://www.google.com/search?q="+query+"&hl=EN&tbm=isch";

        fd.get(url);




        return "";
    }
    public void stop()
    {}


}
