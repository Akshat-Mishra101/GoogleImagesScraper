package org.OpenScraper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Engine.DownloaderEngine;
import Engine.Properties;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import Engine.Downloader;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrimaryController implements Initializable {
    @FXML
    Spinner totalimagesperkeyword;
    @FXML
    TextField keywords;
    @FXML
    ProgressBar pb;
    @FXML
    Label infolabel;
    @FXML
    ListView lv;
    @FXML
    ToggleGroup browser;

    Downloader downloader;//The Thread instance which Manages Our Downloads
    boolean isRunning=false;
    @FXML
    public void Initiate()
    {

      //start downloading

        String Browser=browser.getSelectedToggle().toString().contains("Edge")?"E":browser.getSelectedToggle().toString().contains("Firefox")?"F":"C";
        Properties.current_browser=Browser;

        System.out.println(Browser);

        if(!isRunning) {
            System.out.println("Running");
            isRunning=true;
        }
        else {
            System.out.println("Stopped");
            isRunning=false;
        }

        String all_keywords=keywords.getText().trim();
        System.out.println(all_keywords+" Total keywords");
        System.out.println(totalimagesperkeyword.getValue()+" Total images");
        Properties.total_images=Integer.parseInt(totalimagesperkeyword.getValue().toString());

        Downloader downloader=new Downloader(keywords.getText(),Properties.total_images,lv);
        Thread rtx=new Thread(downloader);
        rtx.start();

        }
    @FXML
    public void terminate()
    {

        //terminates the thread
    }
    @FXML
    public void openSettings()throws Exception
    {

        FXMLLoader fxmlloader=new FXMLLoader(getClass().getResource("secondary.fxml"));
        Parent root1=(Parent)fxmlloader.load();
        Stage stage=new Stage();
        stage.getIcons().add(new Image("file:///../Images/qis.png"));
        stage.setTitle("Settings Dialog");
        stage.setScene(new Scene(root1));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        stage.setResizable(false);
        //stage.getIcons().add(new Image(Properties.path_to_icon));

    }
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        totalimagesperkeyword.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100000,1));
    }
}
