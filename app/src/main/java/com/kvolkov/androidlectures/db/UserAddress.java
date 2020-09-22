package com.kvolkov.androidlectures.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

@Entity(tableName = "user_address",
        foreignKeys = @ForeignKey(entity = User.class, parentColumns = "mId", childColumns = "mUserId"))
public final class UserAddress {

    @PrimaryKey(autoGenerate = true)
    Long mId;

    @ColumnInfo(name = "user_id")
    private Long mUserId;

    @ColumnInfo(name = "city")
    private String mCity;

}
