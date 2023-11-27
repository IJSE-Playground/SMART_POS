package lk.ijse.dep11.pos.controller;

import lk.ijse.dep11.pos.db.CustomerDataAccess;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep11.pos.tm.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PlaceOrderFormController {
    public AnchorPane root;
    public JFXTextField txtCustomerName;
    public JFXTextField txtDescription;
    public JFXTextField txtQtyOnHand;
    public JFXButton btnSave;
    public TableView tblOrderDetails;
    public JFXTextField txtUnitPrice;
    public JFXComboBox<Customer> cmbCustomerId;
    public JFXComboBox cmbItemCode;
    public JFXTextField txtQty;
    public Label lblId;
    public Label lblDate;
    public Label lblTotal;
    public JFXButton btnPlaceOrder;

    public void initialize() throws IOException {
        lblDate.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        newOrder();
        try {
            cmbCustomerId.getItems().addAll(CustomerDataAccess.getAllCustomers());
            cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((ov, prev,cur)->{
                if (cur != null){
                    txtCustomerName.setText(cur.getName());
                    txtCustomerName.setDisable(false);
                    txtCustomerName.setEditable(false);
                }else{
                    txtCustomerName.clear();
                    txtCustomerName.setDisable(true);
                }
            });
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to establish database connection, try later").show();
            e.printStackTrace();
            navigateToHome(null);
        }
    }

    private void newOrder(){
        for (TextField txt : new TextField[]{txtCustomerName, txtDescription, txtQty, txtQtyOnHand, txtUnitPrice}) {
            txt.clear();
            txt.setDisable(true);
        }
        tblOrderDetails.getItems().clear();
        lblTotal.setText("TOTAL: Rs. 0.00");
        btnSave.setDisable(true);
        btnPlaceOrder.setDisable(true);
        cmbCustomerId.getSelectionModel().clearSelection();
        cmbItemCode.getSelectionModel().clearSelection();
        Platform.runLater(cmbCustomerId::requestFocus);
    }

    public void navigateToHome(MouseEvent mouseEvent) throws IOException {
        MainFormController.navigateToMain(root);
    }

    public void btnAdd_OnAction(ActionEvent actionEvent) {
    }

    public void txtQty_OnAction(ActionEvent actionEvent) {
    }

    public void btnPlaceOrder_OnAction(ActionEvent actionEvent) {
    }
}
