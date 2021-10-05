package Engine;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Downloader extends Task<Void> {
    String keywords;
    int number_of_images;
    ListView lv;
    public Downloader(String keywords,int number_of_images,ListView lv)
    {
        this.lv=lv;
        this.keywords=keywords;
        this.number_of_images=number_of_images;
    }

    @Override
    protected Void call() throws Exception {
        Platform.runLater(()->{
            lv.getItems().add("Mode: Serial/Parallel");
        });

        Platform.runLater(()->{
            lv.getItems().add("Initiating Selenium WebDriver");
        });

        ImageScrapingService iss=new ImageScrapingService(false,Properties.current_browser,lv);// We are Setting the Browser To Be Headless By Default
         List<String> urls=new ArrayList<String>();

     //Serial Download

        String tokens[] = keywords.split(",");
     if(Properties.get("threads").equals("NO")) {

         for (String token : tokens) {
             boolean folders = Properties.get("image_saving").equals("YES") ? true : false;
             if (folders)
                 new File("ScrapedImages/" + token).mkdir();
             Platform.runLater(() -> {
                 lv.getItems().add("Searching For " + "\"" + token + "\"");
             });
             String url[] = iss.search(token).split(",");
             for (String ur : url) {
                 String result = SerialDownloader.SecureDownload(token, ur, lv); // Download The Token
                 if (result.equals("S"))
                     Platform.runLater(() -> {
                         lv.getItems().add(token + " Downloaded Successfully");
                     });
                 else
                     Platform.runLater(() -> {
                         lv.getItems().add(token + " Download Failed");
                     });
             }

         }
     }
     else {
         //Parallel Download

         for (String token : tokens) {
             boolean folders = Properties.get("image_saving").equals("YES") ? true : false;
             if (folders)
                 new File("ScrapedImages/" + token).mkdir();
             Platform.runLater(() -> {
                 lv.getItems().add("Searching For " + "\"" + token + "\"");
             });
             String url[] = iss.search(token).split(",");
             int midpoint=url.length/2;
             if(Properties.get("Threads").equals("2")) // 2 threads approach
             {

             Thread rtx1=new Thread(){
                 @Override
                 public void run()
                 {
                     for(int i=0;i<midpoint;i++)
                     {
                     DownloaderEngine de=new DownloaderEngine();
                     String res=de.SecureDownload(url[i],lv);
                         int newi=i;
                         if (res.equals("S"))
                             Platform.runLater(() -> {
                                 lv.getItems().add(url[newi] + " Downloaded Successfully");
                             });
                         else
                             Platform.runLater(() -> {
                                 lv.getItems().add(url[newi] + " Download Failed");
                             });
                     }
                 }
             };

                 Thread rtx2=new Thread(){
                     @Override
                     public void run()
                     {
                         for(int i=midpoint;i<url.length;i++)
                         {
                             DownloaderEngine de=new DownloaderEngine();
                             String res=de.SecureDownload(url[i],lv);
                             int newi=i;
                             if (res.equals("S"))
                                 Platform.runLater(() -> {
                                     lv.getItems().add(url[newi] + " Downloaded Successfully");
                                 });
                             else
                                 Platform.runLater(() -> {
                                     lv.getItems().add(url[newi] + " Download Failed");
                                 });
                         }
                     }
                 };

             rtx1.start();
             rtx2.start();
             }
             else  //n threads approach
             {

               DownloaderEngine[] de=new DownloaderEngine[url.length];
               Thread rt[]=new Thread[url.length];
               for(int i=0;i<url.length;i++)
               {
                   de[i] = new DownloaderEngine();
                   de[i].scheduledDownload(url[i],lv);
                   rt[i] = new Thread(de[i]);
                   rt[i].start();
               }

                    }
         }

         //We Download the images parallely

     }

        iss.stop();






        /**
        Stream.of(tokens)
                .forEach(query-> {
                    try {
                        iss.search("");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        iss.search("");
         **/
        return null;
    }
}
