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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep11.pos.tm.Item;

import java.io.IOException;
import java.net.URL;

public class ManageItemFormController {
    public AnchorPane root;
    public JFXTextField txtCode;
    public JFXTextField txtDescription;
    public JFXTextField txtQtyOnHand;
    public JFXButton btnSave;
    public JFXButton btnDelete;
    public TableView<Item> tblItems;
    public JFXTextField txtUnitPrice;


    public void initialize(){
        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        btnDelete.setDisable(true);
        btnSave.setDefaultButton(true);
        Platform.runLater(txtCode::requestFocus);
    }

    public void navigateToHome(MouseEvent mouseEvent) throws IOException {
        MainFormController.navigateToMain(root);
    }

    public void btnAddNew_OnAction(ActionEvent actionEvent) {
        for (TextField textField : new TextField[]{txtCode, txtDescription, txtQtyOnHand, txtUnitPrice})
            textField.clear();
        txtCode.requestFocus();
        tblItems.getSelectionModel().clearSelection();
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
        if (!isDataValid()) return;

    }

    private boolean isDataValid(){
        String code = txtCode.getText();
        String description = txtDescription.getText();
        String qty = txtQtyOnHand.getText();
        String unitPrice = txtUnitPrice.getText();

        if (!code.matches("\\d{4,}")){
            txtCode.requestFocus();
            txtCode.selectAll();
            return false;
        }else if (!description.matches("[A-Za-z0-9 ]{4,}")){
            txtDescription.requestFocus();
            txtDescription.selectAll();
            return false;
        }else if(!qty.matches("\\d+") || Integer.parseInt(qty) <= 0){
            txtQtyOnHand.requestFocus();
            txtQtyOnHand.selectAll();
            return false;
        }else if(!isPrice(unitPrice)){
            txtUnitPrice.requestFocus();
            txtUnitPrice.selectAll();
            return false;
        }

        return true;
    }

    private boolean isPrice(String input) {
        try {
            double price = Double.parseDouble(input);
            return price > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
    }
}
