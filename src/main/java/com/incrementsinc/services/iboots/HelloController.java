package com.incrementsinc.services.iboots;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class HelloController {
    @FXML
    public Button btnSelectUSBDrive;
    @FXML
    public Label txtSelectUSBDrive;
    @FXML
    public Button btnSelectISOFile;
    @FXML
    public Label txtSelectISOFile;
    @FXML
    public ProgressBar pbProcessProgress;
    @FXML
    public Label txtProcessProgressLogs;
    @FXML
    public Button btnCancel;
    @FXML
    public Button btnCreateBootable;
    @FXML
    public Button btnFormatDrive;


    String diskPath="No drive selected!";
    @FXML
    public void onSelectUSBDriveClick(ActionEvent actionEvent) {
        // Create a new instance of a file chooser dialog
        DirectoryChooser directoryChooser = new DirectoryChooser();

        // Configure the file chooser dialog
        directoryChooser.setTitle("Select USB Flash Drive");
        directoryChooser.setInitialDirectory(new File("/Volumes"));

        // Display the file chooser dialog and wait for the user to select a file
        File selectedDrive = directoryChooser.showDialog(null);

        // Do something with the selected file (in this case, print the path to the console)
        diskPath = selectedDrive.getAbsolutePath();
        txtSelectUSBDrive.setText(diskPath);
    }

    @FXML
    public void onSelectISOFileClick(ActionEvent actionEvent) {
    }

    @FXML
    public void onCancelClick(ActionEvent actionEvent) {
    }

    @FXML
    public void onCreateBootableClick(ActionEvent actionEvent) {
    }

    @FXML
    public void onFormatDriveClick(ActionEvent actionEvent) {
        String[] divs = diskPath.split("/");
        String dOldName = divs[divs.length-1];
        try {
            // Unmount the drive
            Runtime.getRuntime().exec("diskutil unmountDisk " + diskPath);

            // Format the drive as FAT32
            Runtime.getRuntime().exec("diskutil eraseDisk MBRFormat FAT32 " + diskPath);
            Runtime.getRuntime().exec("diskutil rename "+dOldName+" iBootDrive");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
