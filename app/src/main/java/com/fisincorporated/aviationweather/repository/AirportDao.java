package com.fisincorporated.aviationweather.repository;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface AirportDao {

    //TODO figure out how to return LiveData results but that can also be testable under AndroidJUnit4

    @Query("SELECT * FROM airport WHERE ident like :searchTerm or name like :searchTerm  or " +
            "municipality like :searchTerm  collate nocase")
    Maybe<List<Airport>> findAirports(String searchTerm );

    @Query("SELECT * FROM airport WHERE ident = :ident  collate nocase")
    Maybe<Airport> getAirportByIdent(String ident);

    @Query("Select * from airport order by state, name")
    Maybe<List<Airport>> listAllAirports();

    @Query("Select * from airport where ident in (:iacoAirports)")
    Maybe<List<Airport>> selectIcaoIdAirports(List<String> iacoAirports);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Airport airport);

    @Delete
    void delete(Airport airport);

    @Query("SELECT count(*) FROM airport")
    Single<Integer> getCountOfAirports();




}
