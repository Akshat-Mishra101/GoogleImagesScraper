package Engine;

import javafx.scene.control.ListView;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

/**
 * This Class is Used To download The Images Serially
 */
public class SerialDownloader
{


    /**
     * Returns 'S' for a successful download, and returns an Exception Whenever the download is unsuccessful
     *
     * @param link takes the url of the image resource
     * @return returns the Success Or Failure
     */
    public static String SecureDownload(String keyword,String link, ListView lv)
    {

        String report="";
        // This will get input data from the server
        InputStream inputStream = null;
        String prop=Properties.get("save_images");
        String Folder_addition=getAFolderName(keyword)+"/";




        String destName ="ScrapedImages/"+Folder_addition+"/"+getAName(link);

        // This will read the data from the server;


System.out.println(keyword+" link"+link+" downloader "+Properties.get("sretry"));
        OutputStream outputStream = null;
        int retry_count=0;

        while(retry_count!=Integer.parseInt(Properties.get("sretry"))) //loop to implement the Retry Mechanism
        {       System.out.println("Retry Count "+retry_count+" Report is "+report+" OO "+report.equals("S"));
            try {
                // This will open a socket from client to server


                URL url = new URL(Properties.get("proxy")+(Properties.get("encode").equals("YES")? URLEncoder.encode(link, StandardCharsets.UTF_8):link));


                // This user agent is for if the server wants real humans to visit


                // This socket type will allow to set user_agent
                URLConnection con = url.openConnection();

                con.setConnectTimeout(Integer.parseInt(Properties.get("dtimeout")));//timeout has been changed to 60 seconds, this might get rid of the corrupted images problem
                // Setting the user agent


                //Getting content Length


                // Requesting input data from server
                inputStream = con.getInputStream();

                // Open local file writer
                outputStream = new FileOutputStream(destName);

                // Limiting byte written to file per loop
                byte[] buffer = new byte[2048];

                // Increments file size
                int length;
                int downloaded = 0;

                // Looping until server finishes
                while ((length = inputStream.read(buffer)) != -1) {
                    // Writing data
                    outputStream.write(buffer, 0, length);
                    downloaded += length;
                    //System.out.println("Downlad Status: " + (downloaded * 100) / (contentLength * 1.0) + "%");


                }
                outputStream.close();
                inputStream.close();
                report = "S";
                if(report.equals("S"))
                    break;


            } catch (Exception ex) {
                report = ex.toString();
                retry_count++;
                System.out.println(ex + " is the exception");
            }
        }

        return report;
    }

    private static String getAFolderName(String s)
    {
        boolean result=Properties.get("image_saving").equals("YES")?true:false;

        String folder_name="";
        if(result)
        {
            folder_name=s;
        }
        String resultant=folder_name.length()>0?folder_name:randomAlphabetic(10).toUpperCase();
        return result?resultant:"";
    }

    private static String getAName(String link) {

        String greatest_slug=link.substring(link.lastIndexOf("/")+1);
        String empty="";
        for(int i=0;i<greatest_slug.length();i++)
        {
          char c=greatest_slug.charAt(i);
          if((c>=48&&c<=57)||c==46||(c>=65&&c<=90)||(c>=97&&c<=122)||c==45)
          {
              empty+=c;
          }
        }
        greatest_slug=empty;


        String appended_string=Properties.get("names").equals("YES")?randomAlphabetic(10).toUpperCase():"";//Appends Random Code When The Option Is Enabled
        return appended_string+(greatest_slug.length()>0?greatest_slug:randomAlphabetic(10).toUpperCase());
    }

}
