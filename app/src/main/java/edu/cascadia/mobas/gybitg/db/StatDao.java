package edu.cascadia.mobas.gybitg.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import edu.cascadia.mobas.gybitg.models.Stat;

@Dao
public interface StatDao {

    @Insert
    void insert(Stat... stats);

    @Insert
    void insert(Stat stat);

    @Update
    void update(Stat stat);

    @Delete
    void delete(Stat stat);

    @Query("SELECT * FROM stat_table ORDER BY date_of_entry ASC")
    List<Stat> getAllStats();

    @Query("SELECT * FROM stat_table WHERE user_id = :userId ORDER BY date_of_entry ASC")
    List<Stat> getStatsByUserId(String userId);

    @Query("SELECT points FROM stat_table WHERE user_id = :userId")
    List<Integer> getPointsByUserId(String userId);

    @Query("SELECT rebounds FROM stat_table WHERE user_id = :userId")
    List<Integer> getReboundsByUserId(String userId);

    @Query("SELECT steals FROM stat_table WHERE user_id = :userId")
    List<Integer> getStealsByUserId(String userId);

    @Query("SELECT blocks FROM stat_table WHERE user_id = :userId")
    List<Integer> getBlocksByUserId(String userId);

    @Query("SELECT assists FROM stat_table WHERE user_id = :userId")
    List<Integer> getAssistsByUserId(String userId);

    @Query("SELECT minutes_played FROM stat_table WHERE user_id = :userId")
    List<Integer> getMinutesPlayedByUserId(String userId);
}
