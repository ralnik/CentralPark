package ru.ralnik.sqlitedb.SQL;

import ru.ralnik.myLibrary.NavigationButton.DemonsrationButton;

public class CreateSQLQuery implements Where_Implements, Order_Implements {
    private String query = "";

    public CreateSQLQuery(String query) {
        this.query = query;
    }

    @Override
    public CreateSQLQuery whereIN(String colName, DemonsrationButton... button){
        String result = "";
        for(int i=0;i<button.length;i++){
            if(button[i].getStatus() == false){
                continue;
            }
            if(result.length() == 0){
                result = button[i].getTag().toString();
            }else{
                result = result + "," + button[i].getTag().toString();
            }
        }
        if(result.length() > 0) {
            query = query + " and " + colName + " in (" + result + ")";
        }
        return this;
    }

    @Override
    public CreateSQLQuery whereOR(String colName, DemonsrationButton... button){
        String result = "";
        for(int i=0;i<button.length;i++){
            if(button[i].getStatus() == false){
                continue;
            }
            if(result.length() == 0){
                result = colName + "=" + button[i].getTag().toString();
            }else{
                result = result + " or " + colName + "=" + button[i].getTag().toString();
            }
        }

        if(result.length() > 0) {
            query = query + " and (" + result + ")";
        }
        return this;
    }

    @Override
    public CreateSQLQuery whereAND(String colName, DemonsrationButton... button){
        String result = "";
        for(int i=0;i<button.length;i++){
            if(button[i].getStatus() == false){
                continue;
            }
            if(result.length() == 0){
                result = colName + "=" + button[i].getTag().toString();
            }else{
                result = result + " and " + colName + "=" + button[i].getTag().toString();
            }
        }

        if(result.length() > 0) {
            query = query + " and (" + result + ")";
        }
        return this;
    }

    @Override
    public CreateSQLQuery whereRange(String colName, String minValue, String maxValue){
        query = query + " and ( " + colName + " >= " + minValue + " and " + colName +" <= " + maxValue + ") ";
        return this;
    }


    @Override
    public CreateSQLQuery orderBy(String order_by) {
        query = query + " order by " + order_by;
        return this;
    }

    @Override
    public String toString() {
        return query;
    }
}
