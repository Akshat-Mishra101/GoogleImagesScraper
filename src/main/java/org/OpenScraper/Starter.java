package org.OpenScraper;
import Engine.ImageScrapingService;
import Engine.Properties;

import java.io.File;
import java.io.FileWriter;

public class Starter {
    public static void main(String args[])throws Exception
    {   //Secondary Controller - has been tested
        // Primary Controller - has been tested
        //Properties - has been tested
        //properties have been tested
        //single threading works
        //iss works with the three browsers and drivers
        /**
        Properties.load();
        ImageScrapingService iss=new ImageScrapingService(false,"firefox");
        iss.search("cat");
        iss.stop();

  **/

        boolean exists=new File("Data").exists();//checking to see if the folder exists
        boolean exists2=new File("ScrapedImages").exists();
        if(!exists2)
        {
            new File("ScrapedImages").mkdir();
        }
        if(!exists) {
            new File("Data").mkdir();
            //code for the first run
            String DefaultkeysAndValues[][] = {{"proxy", "encode", "timeout", "dtimeout", "threads", "names", "save", "image_saving", "sretry", "dretry", "custom", "cc", "size", "delay"}, {"scraperapi", "YES", "3000", "3000", "N", "YES", "YES", "YES", "40", "40", "YES", "YES", "L", "2000"}};
            for (int i = 0; i < 14; i++) {
                Properties.update(DefaultkeysAndValues[0][i], DefaultkeysAndValues[1][i]);

            }
            Properties.Save();
        }
       // boolean exists=new File("Data").exists();//checking to see if the folder exists
        if(!exists)
        {
            new File("Data").mkdir();

                String properties="gecko_driver_path:" +  "Drivers/geckodriver.exe"+
                            "\n"+"chrome_driver_path:" +   "Drivers/chromedriver.exe"+
                            "\n"+"edge_driver_path:"+"Drivers/edgedriver.exe";


                FileWriter fw = new FileWriter("Data/config2.conf");
                fw.write(properties);
                fw.close();

        }
         Properties.load();
        App.main(args);
     }

}
