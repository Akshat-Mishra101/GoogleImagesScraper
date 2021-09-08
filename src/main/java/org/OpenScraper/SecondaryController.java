package org.OpenScraper;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Engine.Properties;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class SecondaryController implements Initializable {

    @FXML
    MFXRadioButton small;
    @FXML
    MFXRadioButton medium;
    @FXML
    MFXRadioButton large;

    @FXML
    MFXRadioButton single_thread;
    @FXML
    MFXRadioButton multi_thread;
    @FXML
    TextField proxy;
    @FXML
    MFXCheckbox url_encoding;
    @FXML
    MFXCheckbox separate_folders;
    @FXML
    MFXCheckbox csv_file;
    @FXML
    MFXCheckbox custom_number_selection;
    @FXML
    MFXCheckbox random_string_concatenation;
    @FXML
    Spinner scraper_retries;
    @FXML
    Spinner Downloader_retries;
    @FXML
    Spinner click_delay;
    @FXML
    Spinner scraping_timeout;
    @FXML
    Spinner downloader_timeout;
    @FXML
    MFXComboBox total_threads;
    @FXML
    MFXCheckbox creative_commons_licence;
    @FXML
    MFXCheckbox custom_sizes;
    @FXML
    ToggleGroup size_group;
    @FXML
    ToggleGroup threading_group;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ObservableList<String> ol= FXCollections.observableArrayList();
            ol.add("2 Threads");
            ol.add("N Threads");
            total_threads.setItems(ol);
            total_threads.setDisable(true);

            load_gui();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load_gui()throws Exception{
        Properties.load();
        proxy.setText(Properties.get("proxy"));
        url_encoding.setSelected(Properties.get("encode").equals("YES")?true:false);
        separate_folders.setSelected(Properties.get("image_saving").equals("YES")?true:false);
        csv_file.setSelected(Properties.get("save").equals("YES")?true:false);
        custom_number_selection.setSelected(Properties.get("custom").equals("YES")?true:false);
        random_string_concatenation.setSelected(Properties.get("names").equals("YES")?true:false);
        scraper_retries.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100000,Integer.parseInt(Properties.get("sretry"))));
        Downloader_retries.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100000,Integer.parseInt(Properties.get("dretry"))));
        click_delay.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100000,Integer.parseInt(Properties.get("delay"))));
        scraping_timeout.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100000,Integer.parseInt(Properties.get("timeout"))));
        downloader_timeout.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100000,Integer.parseInt(Properties.get("dtimeout"))));

        String threads=Properties.get("threads");
        if(threads.equals("NO"))
            single_thread.setSelected(true);
        else
        {   total_threads.setDisable(false);
            multi_thread.setSelected(true);
            if(threads.equals("2"))
            total_threads.getSelectionModel().selectFirst();
            else
                total_threads.getSelectionModel().selectLast();

        }


        creative_commons_licence.setSelected(Properties.get("cc").equals("YES")?true:false);


        custom_sizes.setSelected(Properties.get("size").equals("NO")?false:true);
        if(custom_sizes.isSelected())
        {
            if(Properties.get("size").equals("S"))
                small.setSelected(true);
            else if(Properties.get("size").equals("M"))
                medium.setSelected(true);
            else
                large.setSelected(true);

        }
    }

    @FXML
    public void select_deselect(ActionEvent event)
    {
        if(event.toString().contains("Single"))
        {
            total_threads.setDisable(true);
        }
        else
        {
            total_threads.setDisable(false);
        }
        System.out.println(event);
    }
    @FXML
    public void setCustom_sizes()
    {
        if(custom_sizes.isSelected())
        {
            small.setDisable(false);
            medium.setDisable(false);
            large.setDisable(false);
        }
        else{
            small.setDisable(true);
            medium.setDisable(true);
            large.setDisable(true);
        }
    }
    @FXML
    public void apply_properties()
    {

    }
    @FXML
    public void reset_defaults()
    {

    }

}