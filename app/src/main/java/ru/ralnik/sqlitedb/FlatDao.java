package ru.ralnik.sqlitedb;

import android.database.Cursor;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import java.util.List;

import ru.ralnik.model.Flat;

@Dao
public interface FlatDao {
    @RawQuery
   Cursor getFlatsByQuery(SupportSQLiteQuery query);

    @Query("SELECT * FROM flats")
    List<Flat> getAll();

    @Query("SELECT * FROM flats")
    Cursor getFlats();

    @Query("SELECT * FROM flats WHERE _id IN (:id)")
    List<Flat> FindByIds(String[] id);

    @Query("SELECT * FROM flats WHERE _id = :id")
    Flat findById(String id);

//   В режиме REPLACE старая запись будет заменена новой. Этот режим хорошо подходит, если вам надо вставить запись, если ее еще нет в таблице или обновить запись, если она уже есть.
//   Также есть режим IGNORE. В этом режиме будет оставлена старая запись и операция вставки не будет выполнена.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Flat... flats);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Flat flat);


    @Delete
    void delete(Flat flat);

    @Query("DELETE FROM flats")
    void deleteAll();

    //**************Floor
    @Query("SELECT min(etag) FROM flats")
    int getMinFloor();

    @Query("SELECT max(etag) FROM flats")
    int getMaxFloor();

    //**************SQUARE
    @Query("SELECT min(ploshad) FROM flats")
    Float getMinSquare();

    @Query("SELECT max(ploshad) FROM flats")
    Float getMaxSquare();

    //**************COST
    @Query("SELECT min(price) FROM flats")
    Float getMinCost();

    @Query("SELECT max(price) FROM flats")
    Float getMaxCost();

    //**************Budget
    @Query("SELECT min(price) FROM flats")
    Float getMinBudget();

    @Query("SELECT max(price) FROM flats")
    Float getMaxBudget();

//    @Query("UPDATE flats set _id = :id, id_flat = :id_flat, nom_kv = :nom_kv, corpus = :corpus, etag = :etag, comnat = :comnat, ploshad = :ploshad, price = :price, status = :status, planirovka = :planirovka")
//    void update(Long id, String id_flat, int nom_kv, int corpus, int etag, int comnat, Float ploshad, Float price, int status, String planirovka);
// }

    @Query("UPDATE flats set _id = :id, nom_kv = :nom_kv, corpus = :corpus, etag = :etag, comnat = :comnat, ploshad = :ploshad, price = :price, status = :status, planirovka = :planirovka")
    void update(String id, int nom_kv, int corpus, int etag, int comnat, Float ploshad, Float price, int status, String planirovka);
 }
