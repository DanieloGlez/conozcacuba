package util;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.ArrayList;

public class Validator {


    private JFXTextField jfxTextField;

    public JFXTextField getJfxTextField() {
        return jfxTextField;
    }

    public boolean validateText(JFXTextField currentJfxTextField) {
        boolean isEmpty=currentJfxTextField.getText().isEmpty();
        //check if de text from the textfield is text or not
        boolean isText = currentJfxTextField.getText().matches("[a-zA-Z].*"+ "");

        return isText&&!isEmpty?true:false;

    }
  public boolean validateTextOfTextArea(JFXTextArea currentJfxTextArea) {
        boolean isEmpty=currentJfxTextArea.getText().isEmpty();
        //check if de text from the textfield is text or not
        boolean isText = currentJfxTextArea.getText().matches("[a-zA-Z].*"+ "");

        return isText&&!isEmpty?true:false;

    }

    public boolean validateNumberInteger(JFXTextField currentJfxTextField) {
        this.jfxTextField=currentJfxTextField;

        boolean isEmpty=currentJfxTextField.getText().isEmpty();
        //check if de text from the textfield is numeric or not
        boolean isNumber = currentJfxTextField.getText().matches("[+-]?\\d*(\\.\\d+)?");



        return isNumber&&!isEmpty?true:false;

    }


    public boolean validateNumberFloat(JFXTextField currentJfxTextField) {
        this.jfxTextField=currentJfxTextField;

        boolean isEmpty=currentJfxTextField.getText().isEmpty();
        //check if de text from the textfield is numeric or not
        boolean isNumber = currentJfxTextField.getText().matches("^[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)$");



        return isNumber&&!isEmpty?true:false;

    }



    public boolean validateDates(JFXDatePicker jfxStartDatePicker,JFXDatePicker jfxFinishDatePicker, JFXDatePicker jfxConciliationDatePicker){
        int logicDateRange=jfxStartDatePicker.getValue().compareTo(jfxFinishDatePicker.getValue());
        int logicDateConciliation=jfxConciliationDatePicker.getValue().compareTo(jfxStartDatePicker.getValue());
        return logicDateRange<0 &&logicDateConciliation<0?true:false;
    }

    public boolean validateCombobox(JFXComboBox jfxComboBox){
        return !jfxComboBox.getSelectionModel().isEmpty()?true:false;
    }

    public boolean validateRegistration(JFXTextField jfxTextField){
        return jfxTextField.getText().length()==7?true:false;
    }


}
