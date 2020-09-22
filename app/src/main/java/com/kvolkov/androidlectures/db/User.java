package com.kvolkov.androidlectures.db;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public final class User {

    @PrimaryKey(autoGenerate = true)
    Long mId;

    @ColumnInfo(name="name")
    private final String mName;

    @ColumnInfo(name="password")
    private final String mPassword;

    public User(String name, String password) {
        mName = name;
        mPassword = password;
    }

    public String getName() {
        return mName;
    }

    public String getPassword() {
        return mPassword;
    }
}
