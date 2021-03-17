package com.example.testproject.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.testproject.model.Consultationrequest;

@Dao
public interface ConsultationrequestDao {
    @Delete
    void delete(Consultationrequest patients);
    @Query("SELECT * FROM consultationreq WHERE doctor_id IN(:doctor_id) LIMIT 1")
    Consultationrequest getuser(int doctor_id);
    @Query("SELECT * FROM consultationreq  LIMIT 1")
    Consultationrequest getuserone();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Consultationrequest details);
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void Update(Consultationrequest details);
}
