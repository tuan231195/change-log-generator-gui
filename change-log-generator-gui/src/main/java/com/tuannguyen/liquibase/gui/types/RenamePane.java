package com.tuannguyen.liquibase.gui.types;

import com.tuannguyen.liquibase.gui.helper.JFXTextFieldWrapper;
import com.tuannguyen.liquibase.gui.model.ChangeInformation;
import javafx.fxml.FXML;
import javafx.scene.control.TextInputControl;

import java.util.Arrays;
import java.util.List;

public class RenamePane extends SubtypePane {

	@FXML
	private JFXTextFieldWrapper tableNameTF;

	@FXML
	private JFXTextFieldWrapper columnNameTF;

	@FXML
	private JFXTextFieldWrapper newColumnTF;

	public RenamePane() {
		super("/rename-pane.fxml");
	}

	@Override
	public List<TextInputControl> textInputControlList() {
		return Arrays.asList(tableNameTF, columnNameTF, newColumnTF);
	}

	@Override
	@FXML
	public void initialize() {
		super.initialize();
		tableNameTF.textProperty()
		           .addListener((observable, oldValue, newValue) -> {
			           changeInformation.setTable(newValue);
		           });
		columnNameTF.textProperty()
		            .addListener((observable, oldValue, newValue) -> {
			            changeInformation.setColumn(newValue);
		            });
		newColumnTF.textProperty()
		           .addListener((observable, oldValue, newValue) -> changeInformation.setNewColumn(newValue));


	}

	@Override
	public void setChangeInformation(ChangeInformation changeInformation) {
		super.setChangeInformation(changeInformation);
		tableNameTF.textProperty()
		           .setValue(changeInformation.table()
		                                      .getValue());
		columnNameTF.textProperty()
		            .setValue(changeInformation.column()
		                                       .getValue());
		newColumnTF.textProperty()
		           .setValue(changeInformation.newColumn()
		                                      .getValue());
	}
}