package edu.cascadia.mobas.gybitg.models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "stat_table",
        indices = {@Index("user_id")})
public class Stat {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "stat_id")
    private int id;

    @ColumnInfo(name = "user_id")
    @NonNull
    private String userId;

    @ColumnInfo(name = "date_of_entry")
    private Date dateOfEntry;

    private int points;

    private int rebounds;

    private int assists;

    private int blocks;

    private int steals;

    @ColumnInfo(name = "minutes_played")
    private int minutesPlayed;

    public void setId(int id) { this.id = id; }

    public int getId() { return id; }

    public void setUserId(@NonNull String userId) { this.userId = userId; }

    @NonNull
    public String getUserId() { return userId; }

    public void setDateOfEntry(Date dateOfEntry) { this.dateOfEntry = dateOfEntry; }

    public Date getDateOfEntry() { return dateOfEntry; }

    public void setPoints(int points) { this.points = points; }

    public int getPoints() { return points; }

    public void setRebounds(int rebounds) { this.rebounds = rebounds; }

    public int getRebounds() { return rebounds; }

    public void setAssists(int assists) { this.assists = assists; }

    public int getAssists() { return assists; }

    public void setBlocks(int blocks) { this.blocks = blocks; }

    public int getBlocks() { return blocks; }

    public void setSteals(int steals) { this.steals = steals; }

    public int getSteals() { return steals; }

    public void setMinutesPlayed(int minutesPlayed) { this.minutesPlayed = minutesPlayed; }

    public int getMinutesPlayed() { return minutesPlayed; }
}
