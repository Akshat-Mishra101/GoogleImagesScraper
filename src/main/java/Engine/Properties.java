package Engine;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Properties {

    //dynamic properties
    public static String current_browser="F";
    public static int total_images=1;
    //dynamic properties end


    static String proxy="";
    static String url_encoding="";

    static String timeout="";//scraper timeout
    static String downloader_timeout="";//tested
    static String click_delay=""; // delay in loading the page and then clicking on it

    static String scraper_retries="";
    static String downloader_retries="";




    static String multithreading="";//almost done
    static String creative_commons="";//free images
    static String image_size="";//image dimensions


    static String image_naming_policy="";//tested
    static String save_images_in_folders="";
    static String save_images_with_alts="";
    static String custom_declaration="";//custom image number declaration mode

    public static String get(String key)
    {
     if(key.equals("proxy"))
         return proxy;
     else if(key.equals("cc"))
         return creative_commons;
     else if(key.equals("size"))
         return image_size;
     else if(key.equals("custom"))//dynamic image number selection
         return custom_declaration;
     else if(key.equals("delay"))
         return click_delay;
     else if(key.equals("encode"))
         return url_encoding;
     else if(key.equals("timeout"))
         return timeout;
     else if(key.equals("threads"))
         return multithreading;// IT CAN HAVE THE STATES NO, 2 Or N
     else if(key.equals("names"))
         return image_naming_policy; //append random code to the image name
     else if(key.equals("save"))
         return save_images_with_alts; //save image data in a csv file
     else if(key.equals("image_saving"))
         return save_images_in_folders;//save images in different folders based on the keyword
     else if(key.equals("dtimeout"))
         return downloader_timeout; // downloader timeout
     else if(key.equals("sretry"))
         return scraper_retries;
     else if(key.equals("dretry"))
         return downloader_retries;
     else
         return "INVALID-KEY";

    }
    public static void load()throws Exception
    {
       Scanner sc=new Scanner(new File("Data/config.conf"));
       while(sc.hasNext())
       {
           String line=sc.nextLine();
           String key=line.substring(0,line.indexOf(":"));
           String value=line.substring(line.indexOf(":")+1);
           if(key.equals("PROXY"))
               proxy=value;
           if(key.equals("SCRAPER-TIMEOUT"))
               timeout=value;
           if(key.equals("DOWNLOADER-TIMEOUT"))
               downloader_timeout=value;
           if(key.equals("ENCODING"))
              url_encoding=value;
           if(key.equals("THREADING"))
               multithreading=value;
           if(key.equals("IMAGE-NAMING"))
               image_naming_policy=value;
           if(key.equals("CSV-FILE"))
               save_images_with_alts=value;
           if(key.equals("FOLDERS"))
               save_images_in_folders=value;
           if(key.equals("SRETRY"))
               scraper_retries=value;
           if(key.equals("DRETRY"))
               downloader_retries=value;
           if (key.equals("CC"))
               creative_commons=value;
           if(key.equals("DELAY"))
               click_delay=value;
           if(key.equals("CUSTOM"))
               custom_declaration=value;
           if(key.equals("SIZE"))
               image_size=value;




       }

    }
    public static void update(String key,String value)
    {
        if(key.equals("proxy"))
            proxy=value;
        else if(key.equals("encode"))
           url_encoding=value;
        else if(key.equals("timeout"))
            timeout=value;
        else if(key.equals("dtimeout"))
            downloader_timeout=value;
        else if(key.equals("threads"))
           multithreading=value;
        else if(key.equals("names"))
            image_naming_policy=value;
        else if(key.equals("save"))
            save_images_with_alts=value;
        else if(key.equals("image_saving"))
            save_images_in_folders=value;
        else if(key.equals("sretry"))
            scraper_retries=value;
        else if(key.equals("dretry"))
            downloader_retries=value;
        else if(key.equals("custom"))//new values begin here
            custom_declaration=value;
        else if(key.equals("cc"))
            creative_commons=value;
        else if(key.equals("size"))
            image_size=value;
        else if(key.equals("delay"))
            click_delay=value; // in Milliseconds



    }
    public static void Save()throws Exception
    {
        FileWriter fw=new FileWriter("Data/config.conf");
        String file="PROXY:"+proxy+"\r\n"+"" +
                "ENCODING:"+url_encoding+"\r\n"+"" +
                "SCRAPER-TIMEOUT:"+timeout+"\r\n"+"" +
                "DOWNLOADER-TIMEOUT:"+downloader_timeout+"\r\n"+"" +
                "THREADING:"+multithreading+"\r\n"+"" +
                "IMAGE-NAMING:"+image_naming_policy+"\r\n"+"" +
                "CSV-FILE:"+save_images_with_alts+"\r\n"+"" +
                "FOLDERS:"+save_images_in_folders+"\r\n" +
                "SRETRY:"+scraper_retries+"\r\n" +
                "DRETRY:"+downloader_retries+"\r\n"+
                "CC:"+creative_commons+"\r\n"+
                "DELAY:"+click_delay+"\r\n" +
                "CUSTOM:"+custom_declaration+"\r\n" +
                "SIZE:"+image_size;



        fw.write(file);
        fw.close();
    }
}
