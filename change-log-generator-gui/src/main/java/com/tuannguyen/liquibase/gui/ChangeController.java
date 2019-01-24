package com.tuannguyen.liquibase.gui;

import com.jfoenix.controls.JFXComboBox;
import com.tuannguyen.liquibase.config.model.ModificationType;
import com.tuannguyen.liquibase.gui.helper.DefaultCallbackListCell;
import com.tuannguyen.liquibase.gui.model.ChangeInformation;
import com.tuannguyen.liquibase.gui.types.*;
import com.tuannguyen.liquibase.gui.util.AlertUtil;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChangeController {

	private Stage stage;

	private ObservableList<ChangeInformation> changeInformationList;
	private ChangeInformation                 currentInformation;

	@FXML
	private JFXComboBox<ModificationType> modificationTypeTF;
	@FXML
	private ListView<ChangeInformation>   changeList;

	@FXML
	private Button deleteBtn;

	@FXML
	private Pane subTypePaneContainer;

	private Map<ModificationType, SubtypePane> subTypePaneMap;

	void initialise(Stage stage) {
		this.stage = stage;

		subTypePaneMap = new HashMap<>();
		subTypePaneMap.put(ModificationType.A, new AddPane());
		subTypePaneMap.put(ModificationType.M, new ModifyPane());
		subTypePaneMap.put(ModificationType.D, new DeletePane());
		subTypePaneMap.put(ModificationType.R, new RenamePane());
		subTypePaneMap.put(ModificationType.S, new SQLPane());
		subTypePaneMap.put(ModificationType.U, new UpdatePane());

		modificationTypeTF.setItems(FXCollections.observableArrayList(ModificationType.values()));
		changeInformationList = FXCollections.observableArrayList();
		changeList.setItems(changeInformationList);
		changeList.getSelectionModel()
		          .selectedItemProperty()
		          .addListener((observable, oldValue, newValue) -> {
			          if (currentInformation == null) {
				          setChangeInformation(changeInformationList.get(0));
				          return;
			          }
			          if (newValue != currentInformation) {
				          if (changeInformationList.contains(oldValue)) {
					          boolean validate = validate();
					          if (!validate) {
						          displayRequiredMessage();
						          Platform.runLater(() -> changeList.getSelectionModel()
						                                            .select(oldValue));
						          return;
					          }
				          }
				          setChangeInformation(newValue);
			          }
		          });
		changeList.setCellFactory(new Callback<ListView<ChangeInformation>, ListCell<ChangeInformation>>() {
			@Override
			public ListCell<ChangeInformation> call(
					ListView<ChangeInformation> param
			) {
				return new ListCell<ChangeInformation>() {
					@Override
					protected void updateItem(ChangeInformation item, boolean empty) {
						super.updateItem(item, empty);
						if (item != null && !empty) {
							if (item.getModificationType() == ModificationType.S) {
								setText("sql");
							} else {
								String table = item.getTable()
								                   .trim();
								String column = item.getColumn()
								                    .trim();
								if (!table.isEmpty() || !column.isEmpty()) {
									setText(String.format("%s-%s", table, column));
								} else {
									setText("Untitled");
								}
							}
						} else {
							setGraphic(null);
							setText(null);
						}
					}
				};
			}
		});

		newChange();
		changeList.getSelectionModel()
		          .selectFirst();
		DefaultCallbackListCell<ModificationType> modificationCellFactory = new DefaultCallbackListCell<ModificationType>() {
			@Override
			public String getTitle(ModificationType item) {
				return item.getName();
			}
		};

		modificationTypeTF.setCellFactory(modificationCellFactory);
		modificationTypeTF.setValue(ModificationType.A);

		modificationTypeTF.valueProperty()
		                  .addListener((observable, oldValue, newValue) -> {
			                  updateSubPane(newValue);
			                  SubtypePane oldPane = subTypePaneMap.get(oldValue);
			                  SubtypePane newPane = subTypePaneMap.get(newValue);
			                  if (oldPane != null && (oldPane.getChangeInformation() == newPane.getChangeInformation())) {
				                  oldPane.reset();
			                  }
			                  updateSubPane(newValue);
		                  });

		IntegerBinding integerBinding = Bindings.size(changeInformationList);

		deleteBtn.disableProperty()
		         .bind(Bindings.createBooleanBinding(() -> integerBinding.get() <= 1, integerBinding));

		attachListeners();
	}

	public void newChange() {
		if (currentInformation != null) {
			boolean validate = validate();
			if (!validate) {
				displayRequiredMessage();
				return;
			}
		}
		ChangeInformation newInformation = new ChangeInformation();
		changeInformationList.add(newInformation);
		changeList.getSelectionModel()
		          .selectLast();
	}

	private void setChangeInformation(ChangeInformation currentInformation) {
		this.currentInformation = currentInformation;
		updateSubPane(
				this.currentInformation.getModificationType()
		);
		modificationTypeTF.valueProperty()
		                  .setValue(currentInformation.getModificationType());

		currentInformation.table()
		                  .addListener((observable, oldValue, newValue) -> {
			                  changeList.refresh();
		                  });
		currentInformation.column()
		                  .addListener((observable, oldValue, newValue) -> {
			                  changeList.refresh();
		                  });
	}

	private void updateSubPane(ModificationType newValue) {
		SubtypePane subtypePane = subTypePaneMap.get(newValue);
		subTypePaneContainer.getChildren()
		                    .setAll(subtypePane);
		subtypePane.setChangeInformation(currentInformation);
	}

	private void attachListeners() {
		modificationTypeTF.valueProperty()
		                  .addListener((observable, oldValue, newValue) -> {
			                  currentInformation.setModificationType(newValue);
		                  });

		modificationTypeTF.valueProperty()
		                  .addListener((observable, oldValue, newValue) -> {
			                  changeList.refresh();
		                  });
	}

	public void delete() {
		changeInformationList.remove(currentInformation);
		changeList.getSelectionModel()
		          .selectFirst();
	}

	boolean validate() {
		SubtypePane subtypePane = getCurrentPane();
		if (subtypePane != null) {
			return subtypePane.validate();
		}
		return true;
	}

	private SubtypePane getCurrentPane() {
		if (modificationTypeTF.getValue() != null) {
			return subTypePaneMap.get(modificationTypeTF.getValue());
		}
		return null;
	}

	private void displayRequiredMessage() {
		AlertUtil.showAlert(Alert.AlertType.ERROR, "Please enter required fields");
	}

	List<ChangeInformation> getChangeList() {
		return changeInformationList;
	}
}
