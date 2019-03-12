package edu.cascadia.mobas.gybitg.models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;

import java.util.Date;

@Entity(tableName = "stat_table",
        indices = {@Index("user_id")})
public class StatEntity {

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

    @Ignore
    public StatEntity() {
    }

    public StatEntity(int id,@NonNull String userId, Date dateOfEntry, int points, int rebounds, int assists, int blocks, int steals, int minutesPlayed) {
        this.id = id;
        this.userId = userId;
        this.dateOfEntry = dateOfEntry;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
        this.blocks = blocks;
        this.steals = steals;
        this.minutesPlayed = minutesPlayed;
    }

    @Ignore
    public StatEntity(@NonNull String userId, Date dateOfEntry, int points, int rebounds, int assists, int blocks, int steals, int minutesPlayed) {
        this.userId = userId;
        this.dateOfEntry = dateOfEntry;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
        this.blocks = blocks;
        this.steals = steals;
        this.minutesPlayed = minutesPlayed;
    }

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


    @Override
    public String toString() {
        return "StatEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", dateOfEntry=" + dateOfEntry +
                ", points=" + points +
                ", rebounds=" + rebounds +
                ", assists=" + assists +
                ", blocks=" + blocks +
                ", steals=" + steals +
                ", minutesPlayed=" + minutesPlayed +
                '}';
    }
}
