package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.ContextThemeWrapper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Hospital";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_USER = "create table user(id integer primary key autoincrement," +
            "idnum text," +
            "password text," +
            "tel text)";

    private static final String CREATE_DEPARTMENT_DOCTOR = "create table department_doctor" +
            "(id integer primary key autoincrement, department text, doctor text)";

    private static final String CREATE_USER_RESERVATION = "create table user_reservation" +
            "(id integer primary key autoincrement, user_id_num text, date text, time_slot text," +
            "department text, doctor text)";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    //创建数据表
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_DEPARTMENT_DOCTOR);
        db.execSQL(CREATE_USER_RESERVATION);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void addDepartmentDoctor(Department_doctor dd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("department", dd.getDepartment());
        values.put("doctor", dd.getDoctor());
        db.insert("department_doctor", null, values);
        db.close();
    }

    public Department_doctor getDepartmentDoctor(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("department_doctor", new String[]{"id", "department", "doctor"},
                "id=?", new String[]{String.valueOf(id)}, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        Department_doctor dd = new Department_doctor(Integer.parseInt(cursor.getString(0))
                , cursor.getString(1), cursor.getString(2));
        cursor.close();
        db.close();
        return dd;
    }

    public int delDepartmentDoctor(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = "id = ?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        int deletedRow = db.delete("department_doctor", whereClause, whereArgs);
        db.close();
        return deletedRow;

    }












    //注册方法实现
    public long RegisterActivity(User u) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", u.getId());
        cv.put("username ", u.getIdnum());
        cv.put("password", u.getPassword());
        cv.put("phone", u.getTel());
        long users = db.insert("user", null, cv);
        return users;
    }

    //登录方法实现
    public boolean LoginActivity(String username, String password) {
        SQLiteDatabase db = getWritableDatabase();
        boolean result = false;
        Cursor users = db.query("user", null, "username like?", new String[]{username}, null, null, null);
        if (users != null) {
            while (users.moveToNext()) {
                @SuppressLint("Range") String username1 = users.getString(users.getColumnIndex("username"));
                Log.i("users", "login: " + username1);
                String password1 = users.getString(2);
                Log.i("users", "login: " + password1);
                result = password1.equals(password);
                return result;
            }
        }
        return false;
    }

    //根据idnum和telno查找当前登录用户信息
    public User select(String username) {
        SQLiteDatabase db = getWritableDatabase();
        User SelectUser = new User();
        Cursor user = db.query("user", new String[]{"username", "phone"}, "username=?", new String[]{username}, null, null, null);
        while (user.moveToNext()) {
            @SuppressLint("Range") String uname = user.getString(user.getColumnIndex("username"));
            @SuppressLint("Range") String phone = user.getString(user.getColumnIndex("phone"));
            SelectUser.setIdnum(uname);
            SelectUser.setTel(phone);
        }
        user.close();
        return SelectUser;
    }


}

