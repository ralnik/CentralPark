package ru.ralnik.sqlitedb.SQL;

import ru.ralnik.myLibrary.NavigationButton.DemonsrationButton;
import ru.ralnik.sqlitedb.SQL.CreateSQLQuery;

public interface Where_Implements {
    CreateSQLQuery whereIN(String colName, DemonsrationButton... button);
    CreateSQLQuery whereOR(String colName, DemonsrationButton... button);
    CreateSQLQuery whereAND(String colName, DemonsrationButton... button);
    CreateSQLQuery whereRange(String colName, String minValue, String maxValue);
}
