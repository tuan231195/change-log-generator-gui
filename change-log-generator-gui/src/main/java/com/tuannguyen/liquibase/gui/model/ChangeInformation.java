package com.tuannguyen.liquibase.gui.model;

import com.tuannguyen.liquibase.config.model.BooleanWrapper;
import com.tuannguyen.liquibase.config.model.ModificationType;
import javafx.beans.property.*;

public class ChangeInformation {
	private ObjectProperty<ModificationType> modificationType;
	private StringProperty                   table;
	private StringProperty                   column;
	private StringProperty                   type;
	private BooleanProperty                  computed;
	private BooleanProperty                  quoted;
	private StringProperty                   defaultValue;
	private ObjectProperty<BooleanWrapper>   nullable;
	private StringProperty                   constraintName;
	private StringProperty                   where;
	private StringProperty                   value;
	private StringProperty                   sql;
	private StringProperty                   newColumn;
	private StringProperty                   afterColumn;
	private ObjectProperty<BooleanWrapper>   unique;

	public ModificationType getModificationType() {
		return modificationType().get();
	}

	public void setModificationType(ModificationType modificationType) {
		this.modificationType()
		    .set(modificationType);
	}

	public ObjectProperty<ModificationType> modificationType() {
		if (modificationType == null) {
			modificationType = new SimpleObjectProperty<>(ModificationType.A);
		}
		return modificationType;
	}

	public String getAfterColumn() {
		return afterColumn().get();
	}

	public void setAfterColumn(String afterColumn) {
		this.afterColumn()
		    .set(afterColumn);
	}

	public StringProperty afterColumn() {
		if (afterColumn == null) {
			afterColumn = new SimpleStringProperty("");
		}
		return afterColumn;
	}

	public String getTable() {
		return table().get();
	}

	public void setTable(String table) {
		table().set(table);
	}

	public StringProperty table() {
		if (table == null) {
			table = new SimpleStringProperty("");
		}
		return table;
	}

	public String getColumn() {
		return column().get();
	}

	public void setColumn(String column) {
		column().set(column);
	}

	public StringProperty column() {
		if (column == null) {
			column = new SimpleStringProperty("");
		}
		return column;
	}

	public String getType() {
		return type().get();
	}

	public void setType(String type) {
		this.type()
		    .set(type);
	}

	public StringProperty type() {
		if (type == null) {
			type = new SimpleStringProperty();
		}
		return type;
	}

	public boolean isComputed() {
		return computed().get();
	}

	public void setComputed(boolean computed) {
		this.computed()
		    .set(computed);
	}

	public BooleanProperty computed() {
		if (computed == null) {
			computed = new SimpleBooleanProperty();
		}
		return computed;
	}

	public boolean isQuoted() {
		return quoted().get();
	}

	public void setQuoted(boolean quoted) {
		this.quoted()
		    .set(quoted);
	}

	public BooleanProperty quoted() {
		if (quoted == null) {
			quoted = new SimpleBooleanProperty();
		}
		return quoted;
	}

	public String getDefaultValue() {
		return defaultValue().get();
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue()
		    .set(defaultValue);
	}

	public StringProperty defaultValue() {
		if (defaultValue == null) {
			defaultValue = new SimpleStringProperty("");
		}
		return defaultValue;
	}

	public BooleanWrapper getNullable() {
		return nullable().get();
	}

	public void setNullable(BooleanWrapper nullable) {
		this.nullable()
		    .set(nullable);
	}

	public ObjectProperty<BooleanWrapper> nullable() {
		if (nullable == null) {
			nullable = new SimpleObjectProperty<>(BooleanWrapper.NULL);
		}
		return nullable;
	}

	public String getConstraintName() {
		return constraintName().get();
	}

	public void setConstraintName(String constraintName) {
		this.constraintName()
		    .set(constraintName);
	}

	public StringProperty constraintName() {
		if (constraintName == null) {
			constraintName = new SimpleStringProperty();
		}
		return constraintName;
	}

	public String getWhere() {
		return where().get();
	}

	public void setWhere(String where) {
		this.where()
		    .set(where);
	}

	public StringProperty where() {
		if (where == null) {
			where = new SimpleStringProperty();
		}
		return where;
	}

	public String getValue() {
		return value().get();
	}

	public void setValue(String value) {
		this.value()
		    .set(value);
	}

	public StringProperty value() {
		if (value == null) {
			value = new SimpleStringProperty();
		}
		return value;
	}

	public String getSql() {
		return sql().get();
	}

	public void setSql(String sql) {
		this.sql()
		    .set(sql);
	}

	public StringProperty sql() {
		if (sql == null) {
			sql = new SimpleStringProperty("");
		}
		return sql;
	}

	public String getNewColumn() {
		return newColumn().get();
	}

	public void setNewColumn(String newColumn) {
		this.newColumn()
		    .set(newColumn);
	}

	public StringProperty newColumn() {
		if (newColumn == null) {
			newColumn = new SimpleStringProperty();
		}
		return newColumn;
	}

	public BooleanWrapper getUnique() {
		return unique().get();
	}

	public void setUnique(BooleanWrapper unique) {
		this.unique()
		    .set(unique);
	}

	public ObjectProperty<BooleanWrapper> unique() {
		if (unique == null) {
			unique = new SimpleObjectProperty<>(BooleanWrapper.NULL);
		}
		return unique;
	}

}
