package Engine;

import javafx.concurrent.Task;

public class Downloader extends Task<Void> {
    String keywords;
    int number_of_images;
    public Downloader(String keywords,int number_of_images)
    {
        this.keywords=keywords;
        this.number_of_images=number_of_images;
    }

    @Override
    protected Void call() throws Exception {
        ImageScrapingService iss=new ImageScrapingService(true,"");// We are Setting the Browser To Be Headless By Default

        return null;
    }
}
