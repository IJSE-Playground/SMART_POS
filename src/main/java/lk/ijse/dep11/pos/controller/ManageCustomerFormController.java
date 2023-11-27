package lk.ijse.dep11.pos.controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep11.pos.tm.Customer;

import java.io.IOException;
import java.net.URL;
public class ManageCustomerFormController {
    public AnchorPane root;
    public JFXTextField txtCustomerId;
    public JFXTextField txtCustomerName;
    public JFXTextField txtCustomerAddress;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public TableView<Customer> tblCustomers;

    public void navigateToHome(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/MainForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(primaryStage::sizeToScene);
    }

    public void initialize(){
        tblCustomers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        txtCustomerId.setEditable(false);
        btnDelete.setDisable(true);
    }

    public void btnAddNew_OnAction(ActionEvent actionEvent) {
        for (TextField textField : new TextField[]{txtCustomerId, txtCustomerName, txtCustomerAddress})
            textField.clear();
        tblCustomers.getSelectionModel().clearSelection();
        txtCustomerName.requestFocus();
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
    }
    public void btnDelete_OnAction(ActionEvent actionEvent) {
    }
}