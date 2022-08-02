package com.example.lifestyle.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lifestyle.Models.ActivityModel;
import com.example.lifestyle.Models.ExerciseModel;
import com.example.lifestyle.Models.HistoryActivityModel;
import com.example.lifestyle.Models.HistoryFoodModel;
import com.example.lifestyle.Models.HistoryProgramModel;
import com.example.lifestyle.Models.HistoryWorkoutModel;
import com.example.lifestyle.Models.LevelModel;
import com.example.lifestyle.Models.LevelProgramModel;
import com.example.lifestyle.Models.ProgramModel;
import com.example.lifestyle.Models.ProgramWorkoutModel;
import com.example.lifestyle.Models.TotalHistoryWorkoutModel;
import com.example.lifestyle.Models.UserModel;
import com.example.lifestyle.Models.WorkoutExerciseModel;
import com.example.lifestyle.Models.WorkoutModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    //база данных, названия таблиц
    public static String DATABASE_NAME = "workout_database";
    private static final int DATABASE_VERSION = 77;

    //
    //НАЗВАНИЯ ТАБЛИЦ БД
    //

    private static final String TABLE_LEVEL = "levels";
    private static final String TABLE_PROGRAM = "programs";
    private static final String TABLE_WORKOUT = "workouts";
    private static final String TABLE_EXERCISE = "exercises";
    private static final String TABLE_ACTIVITY = "activities";
    private static final String TABLE_USER = "users";

    //private static final String TABLE_HISTORY_LEVEL = "history_level";
    private static final String TABLE_HISTORY_PROGRAM = "history_program";
    private static final String TABLE_HISTORY_WORKOUT = "history_workout";
    private static final String TABLE_HISTORY_ACTIVITY = "history_activity";
    private static final String TABLE_HISTORY_FOOD = "history_food";

    private static final String TABLE_WORKOUT_EXERCISE = "workout_exercise";
    private static final String TABLE_PROGRAM_WORKOUT = "program_workout";
    private static final String TABLE_LEVEL_PROGRAM = "level_program";

    //
    //СТОЛБЦЫ ТАБЛИЦ
    //

    //столбцы - таблица уровни
    private static final String LEVEL_ID = "level_id";
    private static final String LEVEL_NAME = "name";
    private static final String LEVEL_VALUE = "level";
    private static final String LEVEL_IMAGE_URL = "imageURL";
    private static final String LEVEL_IMAGE = "image";


    //столбцы - таблица программа тренировок
    private static final String PROGRAM_ID = "program_id";
    private static final String PROGRAM_NAME = "name";
    private static final String PROGRAM_LEVEL = "level";
    private static final String PROGRAM_WEEKS_COUNT = "weeks_count";
    //private static final String PROGRAM_STATUS = "status";
    private static final String PROGRAM_DESC = "description";
    private static final String PROGRAM_IMAGE_URL = "imageURL";
    private static final String PROGRAM_IMAGE = "image";


    //столбцы - таблица тренировки
    private static final String WORKOUT_ID = "workout_id";
    private static final String WORKOUT_NAME = "name";
    private static final String WORKOUT_TIME = "time_minutes";
    private static final String WORKOUT_CALORIE = "calorie";
    private static final String WORKOUT_STATUS = "status";
    private static final String WORKOUT_DESC = "description";

    //столбцы - таблица упражнения
    private static final String EXERCISE_ID = "exercise_id";
    private static final String EXERCISE_NAME = "name";
    private static final String EXERCISE_COUNT = "count_approaches";
    private static final String EXERCISE_TIME = "time_seconds";
    private static final String EXERCISE_DESC = "description";
    private static final String EXERCISE_IMAGE = "image";
    private static final String EXERCISE_GIFS = "gifs";

    //столбцы - таблица активности
    private static final String ACTIVITY_ID = "activity_id";
    private static final String ACTIVITY_NAME = "name";
    private static final String ACTIVITY_CALORIE = "calorie_hour";
    private static final String ACTIVITY_IMAGE_URL = "imageURL";
    private static final String ACTIVITY_IMAGE = "image";


    //столбцы - таблица пользователи
    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "name";
    private static final String USER_AGE = "age";
    private static final String USER_HEIGHT = "height";
    private static final String USER_WEIGHT = "weight";
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";
    private static final String USER_UID = "uid_user";
    //PROGRAM_ID


    //столбцы - таблица история программы тренировок
    private static final String HISTORY_PROGRAM_ID = "history_program_id";
    private static final String HISTORY_PROGRAM_STATUS = "status";
    private static final String HISTORY_PROGRAM_DATE_START = "date_start";
    private static final String HISTORY_PROGRAM_DATE_END = "date_end";
    //USER_ID
    //PROGRAM_ID

    //столбцы - таблица история тренировок
    private static final String HISTORY_WORKOUT_ID = "history_workout_id";
    private static final String HISTORY_WORKOUT_DATE = "date";
    private static final String HISTORY_WORKOUT_TIME = "time";
    private static final String HISTORY_WORKOUT_CALORIE = "calorie";
    //USER_ID
    //HISTORY_PROGRAM_ID
    //WORKOUT_ID

    //столбцы - таблица история питания
    private static final String HISTORY_FOOD_ID = "history_food_id";
    private static final String HISTORY_FOOD_CALORIE = "calorie_total";
    private static final String HISTORY_FOOD_DATE = "date";
    //USER_ID

    //столбцы - таблица история тренировок
    private static final String HISTORY_ACTIVITY_ID = "history_activity_id";
    private static final String HISTORY_ACTIVITY_DATE = "date";
    private static final String HISTORY_ACTIVITY_TIME = "time_second";
    private static final String HISTORY_ACTIVITY_CALORIE = "calorie";
    //USER_ID
    //ACTIVITY_ID

    //столбцы - таблица уровень+программа
    private static final String LEVEL_PROGRAM_ID = "level_program_id";
    //LEVEL_ID
    //PROGRAM_ID

    //столбцы - таблица программа+тренировки
    private static final String PROGRAM_WORKOUT_ID = "program_workout_id";
    //PROGRAM_ID
    //WORKOUT_ID



    //столбцы - таблица тренировка-упраженение
    private static final String WORKOUT_EXERCISE_ID = "workout_exercise_id";
    //WORKOUT_ID
    //EXERCISE_ID

    //
    //SQL-ЗАПРОСЫ НА СОЗДАНИЕ ТАБЛИЦ
    //

    //CREATE TABLE IF NOT EXISTS TABLE_USER ( USER_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, USER_NAME TEXT, USER_AGE INTEGER, USER_HEIGHT INTEGER, USER_EMAIL TEXT, USER_PASSWORD TEXT );
    private static final String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS "
            + TABLE_USER + " ( " + USER_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + USER_NAME + " TEXT, "
            + USER_AGE + " INTEGER, "
            + USER_HEIGHT + " INTEGER, "
            + USER_WEIGHT + " INTEGER, "
            + USER_EMAIL + " TEXT, "
            + USER_PASSWORD + " TEXT, "
            + PROGRAM_ID + " INTEGER REFERENCES " + TABLE_PROGRAM + "(" + PROGRAM_ID + "), "
            + USER_UID + " TEXT );";


    private static final String CREATE_TABLE_LEVEL = "CREATE TABLE IF NOT EXISTS "
            + TABLE_LEVEL + " ( " + LEVEL_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + LEVEL_NAME + " TEXT, "
            + LEVEL_VALUE + " INTEGER,"
            + LEVEL_IMAGE_URL + " TEXT, "
            + LEVEL_IMAGE + " INTEGER );";

    private static final String CREATE_TABLE_PROGRAM = "CREATE TABLE IF NOT EXISTS "
            + TABLE_PROGRAM + " ( " + PROGRAM_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + PROGRAM_NAME + " TEXT, "
            + PROGRAM_LEVEL + " INTEGER,"
            + PROGRAM_WEEKS_COUNT + " INTEGER, "
            + PROGRAM_IMAGE_URL + " TEXT, "
            + PROGRAM_IMAGE + " INTEGER, "
            + PROGRAM_DESC + " TEXT );";

    private static final String CREATE_TABLE_WORKOUT = "CREATE TABLE IF NOT EXISTS "
            + TABLE_WORKOUT + " ( " + WORKOUT_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + WORKOUT_NAME + " TEXT, "
            + WORKOUT_TIME + " INTEGER,"
            + WORKOUT_CALORIE + " INTEGER, "
            + WORKOUT_STATUS + " INTEGER NOT NULL DEFAULT 0 CHECK(" + WORKOUT_STATUS + " IN (0,1)), "
            + WORKOUT_DESC + " TEXT );";

    private static final String CREATE_TABLE_EXERCISE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_EXERCISE + " ( " + EXERCISE_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + EXERCISE_NAME + " TEXT, "
            + EXERCISE_TIME + " INTEGER,"
            + EXERCISE_COUNT + " INTEGER, "
            + EXERCISE_IMAGE + " INTEGER, "
            + EXERCISE_GIFS + " INTEGER, "
            + EXERCISE_DESC + " TEXT );";

    private static final String CREATE_TABLE_ACTIVITY = "CREATE TABLE IF NOT EXISTS "
            + TABLE_ACTIVITY + " ( " + ACTIVITY_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + ACTIVITY_NAME + " TEXT, "
            + ACTIVITY_CALORIE + " INTEGER, "
            + ACTIVITY_IMAGE_URL + " TEXT, "
            + ACTIVITY_IMAGE + " INTEGER );";

    private static final String CREATE_TABLE_WORKOUT_EXERCISE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_WORKOUT_EXERCISE + " ( " + WORKOUT_EXERCISE_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + WORKOUT_ID + " INTEGER NOT NULL,"
            + EXERCISE_ID + " INTEGER NOT NULL, "
            + "FOREIGN KEY(" + WORKOUT_ID + ") REFERENCES " + TABLE_WORKOUT + "(" + WORKOUT_ID + "), "
            + "FOREIGN KEY(" + EXERCISE_ID + ") REFERENCES " + TABLE_EXERCISE + "(" + EXERCISE_ID + "));";

    private static final String CREATE_TABLE_PROGRAM_WORKOUT = "CREATE TABLE IF NOT EXISTS "
            + TABLE_PROGRAM_WORKOUT + " ( " + PROGRAM_WORKOUT_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + PROGRAM_ID + " INTEGER NOT NULL, "
            + WORKOUT_ID + " INTEGER NOT NULL, "
            + "FOREIGN KEY(" + PROGRAM_ID + ") REFERENCES " + TABLE_PROGRAM + "(" + PROGRAM_ID + "), "
            + "FOREIGN KEY(" + WORKOUT_ID + ") REFERENCES " + TABLE_WORKOUT + "(" + WORKOUT_ID + "));";

    private static final String CREATE_TABLE_LEVEL_PROGRAM = "CREATE TABLE IF NOT EXISTS "
            + TABLE_LEVEL_PROGRAM + " ( " + LEVEL_PROGRAM_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + LEVEL_ID + " INTEGER NOT NULL, "
            + PROGRAM_ID + " INTEGER NOT NULL, "
            + "FOREIGN KEY(" + PROGRAM_ID + ") REFERENCES " + TABLE_PROGRAM + "(" + PROGRAM_ID + "), "
            + "FOREIGN KEY(" + LEVEL_ID + ") REFERENCES " + TABLE_LEVEL + "(" + LEVEL_ID + "));";

    private static final String CREATE_TABLE_HISTORY_PROGRAM = "CREATE TABLE IF NOT EXISTS " + TABLE_HISTORY_PROGRAM
            + " ( " + HISTORY_PROGRAM_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + USER_ID + " INTEGER, "
            + PROGRAM_ID + " INTEGER NOT NULL, "
            + HISTORY_PROGRAM_STATUS + " INTEGER NOT NULL DEFAULT 0 CHECK(" + WORKOUT_STATUS + " IN (0,1)), "
            + HISTORY_PROGRAM_DATE_START + " TEXT DEFAULT (datetime('now','+3 hours')), "
            + HISTORY_PROGRAM_DATE_END + " TEXT, "
            + "FOREIGN KEY ( " + USER_ID + " ) REFERENCES " + TABLE_USER + " ( " + USER_ID + " ), "
            + "FOREIGN KEY ( " + PROGRAM_ID + " ) REFERENCES " + TABLE_PROGRAM + " ( " + PROGRAM_ID + " ));";

    //CREATE TABLE IF NOT EXISTS TABLE_HISTORY_WORKOUT ( HISTORY_WORKOUT_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, USER_ID INTEGER, WORKOUT_ID INTEGER, HISTORY_WORKOUT_DATE TEXT, FOREIGN KEY ( USER_ID ) REFERENCES TABLE_USER ( USER_ID ), FOREIGN KEY ( WORKOUT_ID ) REFERENCES TABLE_WORKOUT ( WORKOUT_ID ));
    private static final String CREATE_TABLE_HISTORY_WORKOUT = "CREATE TABLE IF NOT EXISTS " + TABLE_HISTORY_WORKOUT
            + " ( " + HISTORY_WORKOUT_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + USER_ID + " INTEGER, " //убрать потом
            + HISTORY_PROGRAM_ID + " INTEGER, "
            + WORKOUT_ID + " INTEGER NOT NULL, "
            + HISTORY_WORKOUT_TIME + " INTEGER, "
            + HISTORY_WORKOUT_CALORIE + " INTEGER, "
            + HISTORY_WORKOUT_DATE + " TEXT DEFAULT (datetime('now','+3 hours')), "
            + "FOREIGN KEY ( " + USER_ID + " ) REFERENCES " + TABLE_USER + " ( " + USER_ID + " ), "
            + "FOREIGN KEY ( " + HISTORY_PROGRAM_ID + " ) REFERENCES " + TABLE_HISTORY_PROGRAM + " ( " + HISTORY_PROGRAM_ID + " ), "
            + "FOREIGN KEY ( " + WORKOUT_ID + " ) REFERENCES " + TABLE_WORKOUT + " ( " + WORKOUT_ID + " ));";

    private static final String CREATE_TABLE_HISTORY_FOOD = "CREATE TABLE IF NOT EXISTS " + TABLE_HISTORY_FOOD
            + " ( " + HISTORY_FOOD_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + USER_ID + " INTEGER, "
            + HISTORY_FOOD_CALORIE + " REAL, "
            + HISTORY_FOOD_DATE + " TEXT DEFAULT (datetime('now','+3 hours')), "
            + "FOREIGN KEY ( " + USER_ID + " ) REFERENCES " + TABLE_USER + " ( " + USER_ID + " ));";

    private static final String CREATE_TABLE_HISTORY_ACTIVITY = "CREATE TABLE IF NOT EXISTS " + TABLE_HISTORY_ACTIVITY
            + " ( " + HISTORY_ACTIVITY_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + USER_ID + " INTEGER NOT NULL, "
            + ACTIVITY_ID + " INTEGER NOT NULL, "
            + HISTORY_ACTIVITY_TIME + " INTEGER, "
            + HISTORY_ACTIVITY_CALORIE + " REAL, "
            + HISTORY_ACTIVITY_DATE + " TEXT DEFAULT (datetime('now','+3 hours')), "
            + "FOREIGN KEY ( " + USER_ID + " ) REFERENCES " + TABLE_USER + " ( " + USER_ID + " ), "
            + "FOREIGN KEY ( " + ACTIVITY_ID + " ) REFERENCES " + TABLE_ACTIVITY + " ( " + ACTIVITY_ID + " ));";


    //Создание представления История потраченных калорий (состоит из Истории тренировок+история активностей)
    private static String VIEW_TOTAL_HISTORY_WORKOUT = "total_history_workout"; //название представления

    void createViewTotalHistoryWorkout(int user_id){
        //Создание представления История потраченных калорий (состоит из Истории тренировок+история активностей)


        // select h.workout_id, h.activity_id, h.calorie, h.date from (
        //   select h.*, case when h.isWorkout = 'true' then w.name else a.name end as name,
        //         case when h.isWorkout = 'true' then w.workout_id end as workout_id,
        //         case when h.isWorkout = 'false' then a.activity_id end as activity_id  from (
        //                 select hw.workout_id as id,  strftime('%Y-%m-%d', hw.date) as date, hw.calorie, 'true' as isWorkout
        //                            from history_workout hw
        //                            where  hw.user_id=1
        //                 union all
        //                 select ha.activity_id,  strftime('%Y-%m-%d', ha.date), ha.calorie, 'false'
        //                             from history_activity ha
        //                             where  ha.user_id=1
        //                 order by date desc
        //          ) h left join workouts w on h.id=w.workout_id left join activities a on h.id=a.activity_id
        //          where CASE WHEN isWorkout is 'true' THEN w.workout_id = h.id ELSE a.activity_id = h.id end
        //) h

        final String CREATE_VIEW_TOTAL_HISTORY_WORKOUT =
                "CREATE VIEW IF NOT EXISTS "+VIEW_TOTAL_HISTORY_WORKOUT+" AS\n" +
                        "select h."+WORKOUT_ID+", h."+ACTIVITY_ID+", h."+HISTORY_WORKOUT_CALORIE+", h."+HISTORY_WORKOUT_DATE+", h."+WORKOUT_NAME+", h."+HISTORY_WORKOUT_TIME+" from (\n" +
                        "   select h.*, case when h.isWorkout = 'true' then w."+WORKOUT_NAME+" else a."+ACTIVITY_NAME+" end as name, \n" +
                        "         case when h.isWorkout = 'true' then w."+WORKOUT_ID+" end as "+WORKOUT_ID+",   \n" +
                        "         case when h.isWorkout = 'false' then a."+ACTIVITY_ID+" end as "+ACTIVITY_ID+" from (\n" +
                        "                 select hw."+WORKOUT_ID+" as id,  strftime('%Y-%m-%d', hw."+HISTORY_WORKOUT_DATE+") as date, hw."+HISTORY_WORKOUT_CALORIE+", hw."+HISTORY_WORKOUT_TIME+", 'true' as isWorkout\n" +
                        "                            from "+TABLE_HISTORY_WORKOUT+" hw\n" +
                        "                            where  hw."+USER_ID+" = "+user_id+" \n" +
                        "                 union all\n" +
                        "                 select ha."+ACTIVITY_ID+",  strftime('%Y-%m-%d', ha."+HISTORY_ACTIVITY_DATE+"), ha."+HISTORY_ACTIVITY_CALORIE+", ha."+HISTORY_ACTIVITY_TIME+", 'false'\n" +
                        "                             from "+TABLE_HISTORY_ACTIVITY+" ha \n" +
                        "                             where  ha."+USER_ID+" = "+user_id+" \n" +
                        "                 order by date desc\n" +
                        "          ) h left join "+TABLE_WORKOUT+" w on h.id=w."+WORKOUT_ID+" left join "+TABLE_ACTIVITY+" a on h.id=a."+ACTIVITY_ID+" \n" +
                        "          where CASE WHEN isWorkout is 'true' THEN w."+WORKOUT_ID+" = h.id ELSE a."+ACTIVITY_ID+" = h.id end\n" +
                        ") h ;";

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(CREATE_VIEW_TOTAL_HISTORY_WORKOUT);
    }


    public String[] getNameColumnTABLE_HISTORY_WORKOUT() { //передаем название таблицы, столбцы - дата, количество калорий
        String[] s = {TABLE_HISTORY_WORKOUT, HISTORY_WORKOUT_DATE, HISTORY_WORKOUT_CALORIE, USER_ID};
        return s;
    }
    public String[] getNameColumnTABLE_HISTORY_WORKOUT2() { //передаем название таблицы, столбцы - дата, количество калорий
        String[] s = {VIEW_TOTAL_HISTORY_WORKOUT, HISTORY_WORKOUT_DATE, HISTORY_WORKOUT_CALORIE, USER_ID};
        return s;
    }

    public String[] getNameColumnTABLE_HISTORY_FOOD() { //передаем название таблицы, столбцы - дата, количество калорий
        String[] s = {TABLE_HISTORY_FOOD, HISTORY_FOOD_DATE, HISTORY_FOOD_CALORIE, USER_ID};
        return s;
    }

    //инилицизиция
    private static DatabaseHelper sInstance;
    private static Context mContext;
    boolean isCreating = false;
    SQLiteDatabase currentDB = null;


    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context);
            mContext = context;
        }
        return sInstance;
    }

    private void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            //создание таблиц
            db.execSQL(CREATE_TABLE_USER);
            db.execSQL(CREATE_TABLE_LEVEL);
            db.execSQL(CREATE_TABLE_PROGRAM);
            db.execSQL(CREATE_TABLE_WORKOUT);
            db.execSQL(CREATE_TABLE_EXERCISE);
            db.execSQL(CREATE_TABLE_ACTIVITY);
            db.execSQL(CREATE_TABLE_WORKOUT_EXERCISE);
            db.execSQL(CREATE_TABLE_PROGRAM_WORKOUT);
            db.execSQL(CREATE_TABLE_LEVEL_PROGRAM);
            db.execSQL(CREATE_TABLE_HISTORY_FOOD);
            db.execSQL(CREATE_TABLE_HISTORY_WORKOUT);
            db.execSQL(CREATE_TABLE_HISTORY_PROGRAM);
            db.execSQL(CREATE_TABLE_HISTORY_ACTIVITY);
            //db.execSQL(CREATE_TABLE_HISTORY_LEVEL);

            //заполнение данными
            DatabaseRecords databaseRecords = new DatabaseRecords();
            databaseRecords.data(mContext);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        isCreating = true;
        currentDB = db;
        updateDatabase(currentDB, 0, DATABASE_VERSION);
        // release var
        isCreating = false;
        currentDB = null;
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        // TODO Auto-generated method stub
        if (isCreating && currentDB != null) {
            return currentDB;
        }
        return super.getWritableDatabase();
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        // TODO Auto-generated method stub
        if (isCreating && currentDB != null) {
            return currentDB;
        }
        return super.getReadableDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP VIEW IF EXISTS '" + VIEW_TOTAL_HISTORY_WORKOUT + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_HISTORY_ACTIVITY + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_HISTORY_FOOD + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_HISTORY_PROGRAM + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_HISTORY_WORKOUT + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_LEVEL_PROGRAM + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_PROGRAM_WORKOUT + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_WORKOUT_EXERCISE + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_EXERCISE + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_ACTIVITY + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_WORKOUT + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_PROGRAM + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_LEVEL + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_USER + "'");
        onCreate(db);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseAuth.getInstance().signOut();
        }
    }



    public void addUser(UserModel user, String uid) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, user.getName());
        values.put(USER_AGE, user.getAge());
        values.put(USER_HEIGHT, user.getHeight());
        values.put(USER_WEIGHT, user.getWeight());
        values.put(USER_EMAIL, user.getEmail());
        values.put(USER_PASSWORD, user.getPassword());
        values.put(USER_UID, uid);
        values.put(PROGRAM_ID, user.getProgram_id());
        db.insert(TABLE_USER, null, values);
    }

    public void addLevel(LevelModel level) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LEVEL_NAME, level.getName());
        values.put(LEVEL_VALUE, level.getLevel());
        values.put(LEVEL_IMAGE_URL, level.getImageURL());
        values.put(LEVEL_IMAGE, level.getImage());
        db.insert(TABLE_LEVEL, null, values);
    }

    public void addProgram(ProgramModel program) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PROGRAM_NAME, program.getName());
        values.put(PROGRAM_LEVEL, program.getLevel());
        values.put(PROGRAM_WEEKS_COUNT, program.getCountWeeks());
        values.put(PROGRAM_DESC, program.getDesc());
        values.put(PROGRAM_IMAGE_URL, program.getUrlImage());
        values.put(PROGRAM_IMAGE, program.getImage());
        db.insert(TABLE_PROGRAM, null, values);
    }

    public void addWorkout(WorkoutModel workout) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WORKOUT_NAME, workout.getName());
        values.put(WORKOUT_TIME, workout.getTime());
        values.put(WORKOUT_CALORIE, workout.getCalorie());
        values.put(WORKOUT_STATUS, workout.getStatus());
        values.put(WORKOUT_DESC, workout.getDesc());
        db.insert(TABLE_WORKOUT, null, values);
    }

    public void addExercise(ExerciseModel exercise) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EXERCISE_NAME, exercise.getName());
        values.put(EXERCISE_COUNT, exercise.getCount());
        values.put(EXERCISE_TIME, exercise.getTime_second());
        values.put(EXERCISE_DESC, exercise.getDesc());
        values.put(EXERCISE_IMAGE, exercise.getImage());
        values.put(EXERCISE_GIFS, exercise.getGifs());
        db.insert(TABLE_EXERCISE, null, values);
    }

    public void addActivity(ActivityModel activity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ACTIVITY_NAME, activity.getName());
        values.put(ACTIVITY_CALORIE, activity.getCalorie_hour());
        values.put(ACTIVITY_IMAGE, activity.getImage());
        values.put(ACTIVITY_IMAGE_URL, activity.getImageURL());
        db.insert(TABLE_ACTIVITY, null, values);
    }

    public void addLevelProgram(LevelProgramModel levelProgram) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LEVEL_ID, levelProgram.getLevel_id());
        values.put(PROGRAM_ID, levelProgram.getProgram_id());
        db.insert(TABLE_LEVEL_PROGRAM, null, values);
    }

    public void addProgramWorkout(ProgramWorkoutModel program_workout) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PROGRAM_ID, program_workout.getProgram_id());
        values.put(WORKOUT_ID, program_workout.getWorkout_id());
        db.insert(TABLE_PROGRAM_WORKOUT, null, values);
    }

    public void addWorkoutExercise(WorkoutExerciseModel workout_exercise) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WORKOUT_ID, workout_exercise.getWorkout_id());
        values.put(EXERCISE_ID, workout_exercise.getExercise_id());
        db.insert(TABLE_WORKOUT_EXERCISE, null, values);
    }

    public void addHistoryFood(HistoryFoodModel historyFood) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_ID, historyFood.getUser_id());
        values.put(HISTORY_FOOD_CALORIE, historyFood.getCalorieTotal());
        values.put(HISTORY_FOOD_DATE, historyFood.getDate());
        db.insert(TABLE_HISTORY_FOOD, null, values);
    }

    public void updateStatusWorkout(WorkoutModel workout, int status) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WORKOUT_NAME, workout.getName());
        values.put(WORKOUT_TIME, workout.getTime());
        values.put(WORKOUT_CALORIE, workout.getCalorie());
        values.put(WORKOUT_STATUS, status);
        values.put(WORKOUT_DESC, workout.getDesc());
        db.update(TABLE_WORKOUT, values, WORKOUT_ID + " = ?", new String[]{String.valueOf(workout.getId())});
    }

    public void updateUser_ID1(UserModel user, String uid) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, user.getName());
        values.put(USER_AGE, user.getAge());
        values.put(USER_HEIGHT, user.getHeight());
        values.put(USER_WEIGHT, user.getWeight());
        values.put(USER_EMAIL, user.getEmail());
        values.put(USER_PASSWORD, user.getPassword());
        values.put(USER_UID, uid);
        values.put(PROGRAM_ID, user.getProgram_id());
        db.update(TABLE_USER, values, USER_ID + " = ?", new String[]{String.valueOf(1)});
    }

    public void updateProgramUser(UserModel user, ProgramModel program) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, user.getName());
        values.put(USER_AGE, user.getAge());
        values.put(USER_HEIGHT, user.getHeight());
        values.put(USER_WEIGHT, user.getWeight());
        values.put(USER_EMAIL, user.getEmail());
        values.put(USER_PASSWORD, user.getPassword());
        values.put(USER_UID, user.getUid_user());
        values.put(PROGRAM_ID, program.getProgram_id());
        db.update(TABLE_USER, values, USER_ID + " = ?", new String[]{String.valueOf(user.getUser_id())});
    }

    public void addHistoryActivity(HistoryActivityModel historyActivity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_ID, historyActivity.getUser_id());
        values.put(ACTIVITY_ID, historyActivity.getActivity_id());
        values.put(HISTORY_ACTIVITY_TIME, historyActivity.getTime_second());
        values.put(HISTORY_ACTIVITY_CALORIE, historyActivity.getCalorie());
        if (!historyActivity.getDate().equals(""))
            values.put(HISTORY_ACTIVITY_DATE, historyActivity.getDate());
        db.insert(TABLE_HISTORY_ACTIVITY, null, values);
    }

    public void addHistoryWorkout(HistoryWorkoutModel historyWorkout) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_ID, historyWorkout.getUser_id());
        values.put(HISTORY_PROGRAM_ID, historyWorkout.getHistory_program_id());
        values.put(WORKOUT_ID, historyWorkout.getWorkout_id());
        values.put(HISTORY_WORKOUT_TIME, historyWorkout.getTime());
        values.put(HISTORY_WORKOUT_CALORIE, historyWorkout.getCalorie());
        if (!historyWorkout.getDateTime().equals(""))
            values.put(HISTORY_WORKOUT_DATE, historyWorkout.getDateTime());
        db.insert(TABLE_HISTORY_WORKOUT, null, values);
    }

    public void addHistoryProgram(HistoryProgramModel historyProgram) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_ID, historyProgram.getUser_id());
        values.put(PROGRAM_ID, historyProgram.getProgram_id());
        values.put(HISTORY_PROGRAM_STATUS, historyProgram.getStatus());
        if (!historyProgram.getDateStart().equals(""))
            values.put(HISTORY_PROGRAM_DATE_START, historyProgram.getDateStart());
        values.put(HISTORY_PROGRAM_DATE_END, historyProgram.getDateEnd());
        db.insert(TABLE_HISTORY_PROGRAM, null, values);
    }

    public void recreateTotalHistories(int user_id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP VIEW IF EXISTS '" + VIEW_TOTAL_HISTORY_WORKOUT + "'");
        createViewTotalHistoryWorkout(user_id);
    }

    public ArrayList<TotalHistoryWorkoutModel> getTotalHistories(int user_id) {
        ArrayList<TotalHistoryWorkoutModel> totalHistories = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();


        String selectQuery = "SELECT * FROM "+VIEW_TOTAL_HISTORY_WORKOUT;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                TotalHistoryWorkoutModel totalHistory = new TotalHistoryWorkoutModel();
                totalHistory.setUser_id(user_id);
                totalHistory.setActivity_id(cursor.getInt(cursor.getColumnIndexOrThrow(ACTIVITY_ID)));
                totalHistory.setWorkout_id(cursor.getInt(cursor.getColumnIndexOrThrow(WORKOUT_ID)));
                totalHistory.setCalorie(cursor.getDouble(cursor.getColumnIndexOrThrow(HISTORY_WORKOUT_CALORIE)));
                totalHistory.setDate(cursor.getString(cursor.getColumnIndexOrThrow(HISTORY_WORKOUT_DATE)));
                totalHistory.setName(cursor.getString(cursor.getColumnIndexOrThrow(WORKOUT_NAME)));
                totalHistory.setTime_second(cursor.getInt(cursor.getColumnIndexOrThrow(HISTORY_WORKOUT_TIME)));

                totalHistories.add(totalHistory);
            } while (cursor.moveToNext());
        }
        return totalHistories;
    }

    public void updateStatusHistoryProgram(HistoryProgramModel historyProgram, int status) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_ID, historyProgram.getUser_id());
        values.put(PROGRAM_ID, historyProgram.getProgram_id());
        values.put(HISTORY_PROGRAM_STATUS, status);
        values.put(HISTORY_PROGRAM_DATE_START, historyProgram.getDateStart());
        values.put(HISTORY_PROGRAM_DATE_END, historyProgram.getDateEnd());
        db.update(TABLE_WORKOUT, values, HISTORY_PROGRAM_ID + " = ?", new String[]{String.valueOf(historyProgram.getHistory_program_id())});
    }

    public int getUserId(String _uid) {
        int user_id = 0;
        SQLiteDatabase db = getReadableDatabase();
        String selectExerciseQuery = "SELECT * FROM " + TABLE_USER + " WHERE " + USER_UID + " = '" + _uid + "' LIMIT 1";
        Cursor cursor = db.rawQuery(selectExerciseQuery, null);

        if (cursor.moveToFirst()) {
            do {
                user_id = cursor.getInt(cursor.getColumnIndexOrThrow(USER_ID));
            } while (cursor.moveToNext());
        }
        return user_id;
    }


    public int getLevelViaProgram(int program_id){
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT l.* FROM "+TABLE_LEVEL_PROGRAM+" lp, " + TABLE_LEVEL + " l, "+TABLE_PROGRAM+" p  " +
                "WHERE lp."+LEVEL_ID+" = l."+LEVEL_ID+" AND lp."+PROGRAM_ID+" = p."+PROGRAM_ID+" AND " +
                "p." +PROGRAM_ID+" = "+program_id+" LIMIT 1";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int level = cursor.getInt(cursor.getColumnIndexOrThrow(LEVEL_VALUE));
                return level;

            } while (cursor.moveToNext());
        }
        return -1;
    }

    public int getCountWorkoutsInPrograms2(int user_id){
        SQLiteDatabase db = getReadableDatabase();
        String column_complete = "count_workout";

        //SELECT COUNT(pw.workout_id) count_workout
        //FROM  programs p, workouts w, program_workout pw, users u
        //WHERE p.program_id = pw.program_id and w.workout_id = pw.workout_id
        //and u.user_id = 1 and u.program_id = p.program_id

        String selectQuery = "SELECT COUNT(pw."+WORKOUT_ID+") count_workout "
                +"FROM "+TABLE_PROGRAM+" p, "+TABLE_WORKOUT+" w, "+TABLE_PROGRAM_WORKOUT+" pw, "+TABLE_USER+" u "
                +"WHERE p."+PROGRAM_ID+" = pw."+PROGRAM_ID+" and w."+WORKOUT_ID+" = pw."+WORKOUT_ID+" "
                +"and u."+USER_ID+" = "+user_id+" and u."+PROGRAM_ID+" = p."+PROGRAM_ID;

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int countWorkout = cursor.getInt(cursor.getColumnIndexOrThrow(column_complete));
                return countWorkout;

            } while (cursor.moveToNext());
        }
        return 0;
    }


    public int getCountWorkoutsInPrograms(int user_id){
        SQLiteDatabase db = getReadableDatabase();
        String column_complete = "count_workout";

        //SELECT COUNT(pw.workout_id) count_workout
        //FROM  programs p, workouts w, program_workout pw, users u
        //WHERE p.program_id = pw.program_id and w.workout_id = pw.workout_id
        //and u.user_id = 1 and u.program_id = p.program_id

        String selectQuery = "SELECT COUNT(pw."+WORKOUT_ID+") count_workout "
        +"FROM "+TABLE_PROGRAM+" p, "+TABLE_WORKOUT+" w, "+TABLE_PROGRAM_WORKOUT+" pw, "+TABLE_USER+" u "
        +"WHERE p."+PROGRAM_ID+" = pw."+PROGRAM_ID+" and w."+WORKOUT_ID+" = pw."+WORKOUT_ID+" "
        +"and u."+USER_ID+" = "+user_id+" and u."+PROGRAM_ID+" = p."+PROGRAM_ID;

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int countWorkout = cursor.getInt(cursor.getColumnIndexOrThrow(column_complete));
                return countWorkout;

            } while (cursor.moveToNext());
        }
        return 0;
    }

    public int getCountWorkoutsInPrograms(int user_id, int program_id){
        SQLiteDatabase db = getReadableDatabase();
        String column_complete = "count_workout";

        //SELECT COUNT(pw.workout_id) count_workout
        //FROM  programs p, workouts w, program_workout pw, users u
        //WHERE p.program_id = pw.program_id and w.workout_id = pw.workout_id
        //and u.program= 1 and u.program_id = p.program_id

        String selectQuery = "SELECT COUNT(pw."+WORKOUT_ID+") count_workout "
                +"FROM "+TABLE_PROGRAM+" p, "+TABLE_WORKOUT+" w, "+TABLE_PROGRAM_WORKOUT+" pw, "+TABLE_USER+" u "
                +"WHERE p."+PROGRAM_ID+" = pw."+PROGRAM_ID+" and w."+WORKOUT_ID+" = pw."+WORKOUT_ID+" "
                +"and u."+USER_ID+" = "+user_id+" "
                +"and p."+PROGRAM_ID+" = "+program_id;

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int countWorkout = cursor.getInt(cursor.getColumnIndexOrThrow(column_complete));
                return countWorkout;

            } while (cursor.moveToNext());
        }
        return 0;
    }

    public int getCountCompleteWorkouts(int user_id){
        SQLiteDatabase db = getReadableDatabase();
        String column_complete = "complete";

        //select count(*) complete
        //            from (SELECT hw.* FROM  programs p, workouts w, program_workout pw, history_workout hw, users u
        //                              WHERE p.program_id = pw.program_id and w.workout_id = pw.workout_id
        //                                  and w.workout_id = hw.workout_id and u.user_id = 1
        //                                  and hw.user_id=u.user_id and u.program_id = p.program_id
        //                                  GROUP BY w.workout_id) t



        String selectQuery = "select count(*) "+column_complete+" "
        +"from (SELECT hw.* FROM "+TABLE_PROGRAM +" p, "+TABLE_WORKOUT+" w, "+TABLE_PROGRAM_WORKOUT+" pw, " +TABLE_HISTORY_WORKOUT+" hw, "+TABLE_USER+" u "
                +"WHERE p."+PROGRAM_ID+" = pw."+PROGRAM_ID+" and w."+WORKOUT_ID+" = pw."+WORKOUT_ID+" "
                +"and w."+WORKOUT_ID+" = hw."+WORKOUT_ID+" and u."+USER_ID+" = "+user_id+" "
                +"and hw."+USER_ID+" = u."+USER_ID+" and u."+PROGRAM_ID+" = p."+PROGRAM_ID+" "
                +"GROUP BY w."+WORKOUT_ID+" ) t;";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int countComlete = cursor.getInt(cursor.getColumnIndexOrThrow(column_complete));
                return countComlete;

            } while (cursor.moveToNext());
        }
        return 0;
    }


    public int getCountCompleteWorkouts2(int user_id){
        SQLiteDatabase db = getReadableDatabase();
        String column_complete = "complete";

        //select count(*) complete
        //from (SELECT hw.* FROM  programs p, workouts w, program_workout pw, history_workout hw, users u, history_program hp
        //WHERE p.program_id = pw.program_id and w.workout_id = pw.workout_id
        //and w.workout_id = hw.workout_id and u.user_id = 1
        //and hp.user_id=u.user_id and u.program_id = p.program_id
        //and hp.history_program_id = 20
        //and hp.history_program_id = hw.history_program_id
        //GROUP BY w.workout_id) t

        int history_program_id = getLastHistoryProgram(user_id).getHistory_program_id();

        String selectQuery = "select count(*) "+column_complete+" "
                +"from (SELECT hw.* FROM "+TABLE_PROGRAM +" p, "+TABLE_WORKOUT+" w, "+TABLE_PROGRAM_WORKOUT+" pw, " +TABLE_HISTORY_WORKOUT+" hw, "+TABLE_USER+" u, "+TABLE_HISTORY_PROGRAM+" hp "
                +"WHERE p."+PROGRAM_ID+" = pw."+PROGRAM_ID+" and w."+WORKOUT_ID+" = pw."+WORKOUT_ID+" "
                +"and w."+WORKOUT_ID+" = hw."+WORKOUT_ID+" and u."+USER_ID+" = "+user_id+" "
                +"and hp."+USER_ID+" = u."+USER_ID+" and u."+PROGRAM_ID+" = p."+PROGRAM_ID+" "
                +"and hp."+HISTORY_PROGRAM_ID+" = "+history_program_id+" and hp."+HISTORY_PROGRAM_ID+" = hw."+HISTORY_PROGRAM_ID+" "
                +"GROUP BY w."+WORKOUT_ID+" ) t;";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int countComlete = cursor.getInt(cursor.getColumnIndexOrThrow(column_complete));
                return countComlete;

            } while (cursor.moveToNext());
        }
        return 0;
    }



    public int getCountCompleteWorkouts(int _user_id, int _program_id){
        SQLiteDatabase db = getReadableDatabase();
        String column_complete = "complete";

        //select count(*) complete
        //            from (SELECT hw.* FROM  programs p, workouts w, program_workout pw, history_workout hw, users u
        //                              WHERE p.program_id = pw.program_id and w.workout_id = pw.workout_id
        //                                  and w.workout_id = hw.workout_id and u.user_id = 1
        //                                  and hw.user_id=u.user_id and u.program_id = p.program_id
        //                                  and p.program_id = 1
        //                                  GROUP BY w.workout_id) t


        String selectQuery = "select count(*) "+column_complete+" "
                +"from (SELECT hw.* FROM "+TABLE_PROGRAM +" p, "+TABLE_WORKOUT+" w, "+TABLE_PROGRAM_WORKOUT+" pw, " +TABLE_HISTORY_WORKOUT+" hw, "+TABLE_USER+" u "
                +"WHERE p."+PROGRAM_ID+" = pw."+PROGRAM_ID+" and w."+WORKOUT_ID+" = pw."+WORKOUT_ID+" "
                +"and w."+WORKOUT_ID+" = hw."+WORKOUT_ID+" and u."+USER_ID+" = "+_user_id+" "
                +"and hw."+USER_ID+" = u."+USER_ID+" "
                +"and p."+PROGRAM_ID+" = "+_program_id
                +" GROUP BY w."+WORKOUT_ID+" ) t;";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int countComlete = cursor.getInt(cursor.getColumnIndexOrThrow(column_complete));
                return countComlete;

            } while (cursor.moveToNext());
        }
        return 0;
    }

    public String getProgramName(int program_id) {
        SQLiteDatabase db = getReadableDatabase();
        String programName ="empty", levelString;
        String selectQuery = "SELECT l."+LEVEL_NAME+", p."+PROGRAM_LEVEL+" "+
                "FROM "+TABLE_LEVEL_PROGRAM+" lp, " + TABLE_LEVEL + " l, "+TABLE_PROGRAM+" p  " +
                "WHERE lp."+LEVEL_ID+" = l."+LEVEL_ID+" AND lp."+PROGRAM_ID+" = p."+PROGRAM_ID+" AND " +
                "p." +PROGRAM_ID+" = "+program_id+" LIMIT 1";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int programLevel = cursor.getInt(cursor.getColumnIndexOrThrow(PROGRAM_LEVEL));
                switch (programLevel){
                    case 1:
                        levelString = "I";
                        break;
                    case 2:
                        levelString = "II";
                        break;
                    case 3:
                        levelString = "III";
                        break;
                    case 4:
                        levelString = "IV";
                        break;
                    case 5:
                        levelString = "V";
                        break;
                    default:
                        levelString = ""+programLevel;
                        break;
                }

                programName = cursor.getString(cursor.getColumnIndexOrThrow(LEVEL_NAME))+" "+levelString;
            } while (cursor.moveToNext());
        }
        return programName;
    }

    public Boolean User1_isDefault() {
        SQLiteDatabase db = getReadableDatabase();

        String s = "";
        String selectExerciseQuery = "SELECT * FROM " + TABLE_USER + " WHERE " + USER_ID + " = '" + 1 + "' LIMIT 1";
        Cursor cursor = db.rawQuery(selectExerciseQuery, null);

        if (cursor.moveToFirst()) {
            do {
                s = cursor.getString(cursor.getColumnIndexOrThrow(USER_UID));
            } while (cursor.moveToNext());
        }

        if (DatabaseUtils.queryNumEntries(db, TABLE_USER) == 1 && s.equals("EMPTY"))
            return true;

        return false;
    }

    public Boolean isDoneWorkout(WorkoutModel workout, int user_id) {
        String selectExerciseQuery = "SELECT * FROM " + TABLE_WORKOUT + " w, " + TABLE_HISTORY_WORKOUT + " hw "
                + "WHERE w." + WORKOUT_ID + " = hw." + WORKOUT_ID + " AND hw." + WORKOUT_ID + " = " + workout.getId() + " "
                + "AND hw." + USER_ID + " = " + user_id + " LIMIT 1";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectExerciseQuery, null);

        if (cursor.moveToFirst()) {
            do {
                return true;
            } while (cursor.moveToNext());
        }
        return false;
    }



    public Boolean isDoneWorkout2(WorkoutModel workout, int user_id) {

        //SELECT * FROM WORKOUTS w, history_workout hw, history_program hp
        // w.workout_id = hw.workout_id and hw.workout_id =  1 and hp.history_program_id = 1
        // and hw.history_program_id = hp.history_program_id limit 1

        int history_program_id = getLastHistoryProgram(user_id).getHistory_program_id();

        String selectExerciseQuery =
                "SELECT * FROM "+TABLE_WORKOUT+" w, "+TABLE_HISTORY_WORKOUT+" hw, "+TABLE_HISTORY_PROGRAM+" hp " +
                "where w."+WORKOUT_ID+" = hw."+WORKOUT_ID+" and hw."+WORKOUT_ID+" = " +workout.getId()+" "+
                "and hp."+HISTORY_PROGRAM_ID+" = "+history_program_id+" " +
                "and hw."+HISTORY_PROGRAM_ID+" = hp."+HISTORY_PROGRAM_ID+" limit 1";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectExerciseQuery, null);

        if (cursor.moveToFirst()) {
            do {
                return true;
            } while (cursor.moveToNext());
        }
        return false;
    }

    public Boolean isDoneProgram(WorkoutModel workout, int user_id) {
        String selectExerciseQuery = "SELECT * FROM " + TABLE_WORKOUT + " w, " + TABLE_HISTORY_WORKOUT + " hw "
                + "WHERE w." + WORKOUT_ID + " = hw." + WORKOUT_ID + " AND hw." + WORKOUT_ID + " = " + workout.getId() + " "
                + "AND hw." + USER_ID + " = " + user_id + " LIMIT 1";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectExerciseQuery, null);

        if (cursor.moveToFirst()) {
            do {
                return true;
            } while (cursor.moveToNext());
        }
        return false;
    }

    public Boolean isExistsHistoryFood(HistoryFoodModel historyFood) {
        SQLiteDatabase db = getReadableDatabase();

        String selectExerciseQuery = "SELECT * FROM " + TABLE_HISTORY_FOOD + " WHERE " + USER_ID + " = '" + historyFood.getUser_id() + "' AND " + HISTORY_FOOD_DATE + " = '" + historyFood.getDate() + "' LIMIT 1";
        Cursor cursor = db.rawQuery(selectExerciseQuery, null);

        if (cursor.moveToFirst()) {
            do {
                return true;

            } while (cursor.moveToNext());
        }
        return false;
    }

    public UserModel getUserOnDB(String _uid) {

        UserModel user = null;
        SQLiteDatabase db = getReadableDatabase();
        String selectExerciseQuery = "SELECT * FROM " + TABLE_USER + " WHERE " + USER_UID + " = '" + _uid + "' LIMIT 1";
        Cursor cursor = db.rawQuery(selectExerciseQuery, null);

        if (cursor.moveToFirst()) {
            do {
                user = new UserModel();

                user.setUser_id(cursor.getInt(cursor.getColumnIndexOrThrow(USER_ID)));
                user.setName(cursor.getString(cursor.getColumnIndexOrThrow(USER_NAME)));
                user.setAge(cursor.getInt(cursor.getColumnIndexOrThrow(USER_AGE)));
                user.setHeight(cursor.getInt(cursor.getColumnIndexOrThrow(USER_HEIGHT)));
                user.setWeight(cursor.getInt(cursor.getColumnIndexOrThrow(USER_WEIGHT)));
                user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(USER_PASSWORD)));
                user.setUid_user(cursor.getString(cursor.getColumnIndexOrThrow(USER_UID)));
                user.setProgram_id(cursor.getInt(cursor.getColumnIndexOrThrow(PROGRAM_ID)));

            } while (cursor.moveToNext());
        }
        return user;
    }

    public ArrayList<LevelModel> getLevelUser(int user_id) {

        ArrayList<LevelModel> levelsList = new ArrayList<>();

        String selectLevelQuery = user_id == -1
                ? "SELECT * FROM " + TABLE_LEVEL
                : "SELECT l.* FROM " + TABLE_LEVEL + " l, " + TABLE_USER + " u "
                    + "WHERE l." + LEVEL_ID + " = u." + PROGRAM_ID + " AND u." + USER_ID + " = " + user_id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectLevelQuery, null);

        if (cursor.moveToFirst()) {
            do {
                LevelModel levelModel = new LevelModel();
                levelModel.setLevel_id(cursor.getInt(cursor.getColumnIndexOrThrow(LEVEL_ID)));
                levelModel.setName(cursor.getString(cursor.getColumnIndexOrThrow(LEVEL_NAME)));
                levelModel.setLevel(cursor.getInt(cursor.getColumnIndexOrThrow(LEVEL_VALUE)));
                levelModel.setImageURL(cursor.getString(cursor.getColumnIndexOrThrow(LEVEL_IMAGE_URL)));
                levelModel.setImage(cursor.getInt(cursor.getColumnIndexOrThrow(LEVEL_IMAGE)));

                ArrayList<ProgramModel> programsList = new ArrayList<>();


                String selectProgramQuery = "SELECT p." + PROGRAM_ID + ", p." + PROGRAM_NAME
                        + ", p." + PROGRAM_LEVEL + ", p." + PROGRAM_WEEKS_COUNT + ", p." + PROGRAM_DESC
                        + ", p." + WORKOUT_DESC + ", p." + PROGRAM_IMAGE_URL + ", p." + PROGRAM_IMAGE
                        + " FROM " + TABLE_LEVEL_PROGRAM + " lp, " + TABLE_LEVEL + " l, "
                        + TABLE_PROGRAM + " p WHERE l." + LEVEL_ID
                        + " = lp." + LEVEL_ID + " AND p." + PROGRAM_ID + " = lp." + PROGRAM_ID
                        + " AND l." + LEVEL_ID + " = " + cursor.getInt(cursor.getColumnIndexOrThrow(LEVEL_ID));

                Cursor cursor2 = db.rawQuery(selectProgramQuery, null);

                if (cursor2.moveToFirst()) {
                    do {
                        ProgramModel programModel = new ProgramModel();
                        programModel.setProgram_id(cursor2.getInt(cursor2.getColumnIndexOrThrow(PROGRAM_ID)));
                        programModel.setName(cursor2.getString(cursor2.getColumnIndexOrThrow(PROGRAM_NAME)));
                        programModel.setLevel(cursor2.getInt(cursor2.getColumnIndexOrThrow(PROGRAM_LEVEL)));
                        programModel.setCountWeeks(cursor2.getInt(cursor2.getColumnIndexOrThrow(PROGRAM_WEEKS_COUNT)));
                        programModel.setDesc(cursor2.getString(cursor2.getColumnIndexOrThrow(PROGRAM_DESC)));
                        programModel.setUrlImage(cursor2.getString(cursor2.getColumnIndexOrThrow(PROGRAM_IMAGE_URL)));
                        programModel.setImage(cursor2.getInt(cursor2.getColumnIndexOrThrow(PROGRAM_IMAGE)));

                        ArrayList<WorkoutModel> workouts = new ArrayList<>();

                        //SELECT e.exercise_id, e.name, e.time_seconds, e.count_approaches, e.description
                        //FROm exercises e, workouts w, workout_exercise we
                        // where w.workout_id = 1 and e.exercise_id = we.exercise_id

                        String selectWorkoutQuery = "SELECT w." + WORKOUT_ID + ", w." + WORKOUT_NAME
                                + ", w." + WORKOUT_TIME + ", w." + WORKOUT_CALORIE + ", w." + WORKOUT_STATUS
                                + ", w." + WORKOUT_DESC
                                + " FROM " + TABLE_PROGRAM_WORKOUT + " pw, " + TABLE_WORKOUT + " w, "
                                + TABLE_PROGRAM + " p WHERE w." + WORKOUT_ID
                                + " = pw." + WORKOUT_ID + " AND p." + PROGRAM_ID + " = pw." + PROGRAM_ID
                                + " AND p." + PROGRAM_ID + " = " + cursor2.getInt(cursor2.getColumnIndexOrThrow(PROGRAM_ID));

                        Cursor cursor3 = db.rawQuery(selectWorkoutQuery, null);

                        if (cursor3.moveToFirst()) {
                            do {
                                WorkoutModel workout = new WorkoutModel();
                                workout = new WorkoutModel(
                                        cursor3.getInt(cursor3.getColumnIndexOrThrow(WORKOUT_ID)),
                                        cursor3.getString(cursor3.getColumnIndexOrThrow(WORKOUT_NAME)),
                                        cursor3.getInt(cursor3.getColumnIndexOrThrow(WORKOUT_TIME)),
                                        cursor3.getInt(cursor3.getColumnIndexOrThrow(WORKOUT_CALORIE)),
                                        cursor3.getInt(cursor3.getColumnIndexOrThrow(WORKOUT_STATUS)),
                                        cursor3.getString(cursor3.getColumnIndexOrThrow(WORKOUT_DESC))
                                );


                                String selectExerciseQuery = "SELECT e." + EXERCISE_ID + ", e." + EXERCISE_NAME
                                        + ", e." + EXERCISE_TIME + ", e." + EXERCISE_COUNT + ", e." + EXERCISE_DESC
                                        + ", e." + EXERCISE_IMAGE + ", e." + EXERCISE_GIFS
                                        + " FROM " + TABLE_WORKOUT_EXERCISE + " we, " + TABLE_EXERCISE + " e, "
                                        + TABLE_WORKOUT + " w WHERE e." + EXERCISE_ID
                                        + " = we." + EXERCISE_ID + " AND w." + WORKOUT_ID + " = we." + WORKOUT_ID
                                        + " AND w." + WORKOUT_ID + " = " + cursor3.getInt(cursor3.getColumnIndexOrThrow(WORKOUT_ID));

                                Cursor cursor4 = db.rawQuery(selectExerciseQuery, null);
                                ArrayList<ExerciseModel> exercises = new ArrayList<>();

                                if (cursor4.moveToFirst()) {
                                    do {
                                        exercises.add(
                                                new ExerciseModel(
                                                        cursor4.getInt(cursor4.getColumnIndexOrThrow(EXERCISE_ID)),
                                                        cursor4.getString(cursor4.getColumnIndexOrThrow(EXERCISE_NAME)),
                                                        cursor4.getString(cursor4.getColumnIndexOrThrow(EXERCISE_DESC)),
                                                        cursor4.getInt(cursor4.getColumnIndexOrThrow(EXERCISE_COUNT)),
                                                        cursor4.getInt(cursor4.getColumnIndexOrThrow(EXERCISE_TIME)),
                                                        cursor4.getInt(cursor4.getColumnIndexOrThrow(EXERCISE_IMAGE)),
                                                        cursor4.getInt(cursor4.getColumnIndexOrThrow(EXERCISE_GIFS))
                                                )
                                        );
                                    } while (cursor4.moveToNext());
                                }
                                workout.setExercices(exercises);
                                workouts.add(workout);

                            } while (cursor3.moveToNext());
                        }
                        programModel.setWorkouts(workouts);
                        programsList.add(programModel);

                    } while (cursor2.moveToNext());
                }
                levelModel.setPrograms(programsList);
                levelsList.add(levelModel);

            } while (cursor.moveToNext());
        }


        return levelsList;
    }

    public ProgramModel getProgramUser(int user_id) {
        ProgramModel program = new ProgramModel();

        String selectProgramQuery = "SELECT p.* FROM " + TABLE_PROGRAM + " p, " + TABLE_USER + " u " +
                "WHERE  u." + PROGRAM_ID + " = p." + PROGRAM_ID + " AND u." + USER_ID + " = " + user_id + " LIMIT 1 ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectProgramQuery, null);

        if (cursor.moveToFirst()) {
            do {
                program.setProgram_id(cursor.getInt(cursor.getColumnIndexOrThrow(PROGRAM_ID)));
                program.setName(cursor.getString(cursor.getColumnIndexOrThrow(PROGRAM_NAME)));
                program.setLevel(cursor.getInt(cursor.getColumnIndexOrThrow(PROGRAM_LEVEL)));
                program.setCountWeeks(cursor.getInt(cursor.getColumnIndexOrThrow(PROGRAM_WEEKS_COUNT)));
                program.setDesc(cursor.getString(cursor.getColumnIndexOrThrow(PROGRAM_DESC)));
                program.setUrlImage(cursor.getString(cursor.getColumnIndexOrThrow(PROGRAM_IMAGE_URL)));
                program.setImage(cursor.getInt(cursor.getColumnIndexOrThrow(PROGRAM_IMAGE)));

                ArrayList<WorkoutModel> workouts = new ArrayList<>();

                String selectWorkoutQuery = "SELECT w." + WORKOUT_ID + ", w." + WORKOUT_NAME
                        + ", w." + WORKOUT_TIME + ", w." + WORKOUT_CALORIE + ", w." + WORKOUT_STATUS
                        + ", w." + WORKOUT_DESC
                        + " FROM " + TABLE_PROGRAM_WORKOUT + " pw, " + TABLE_WORKOUT + " w, "
                        + TABLE_PROGRAM + " p WHERE w." + WORKOUT_ID
                        + " = pw." + WORKOUT_ID + " AND p." + PROGRAM_ID + " = pw." + PROGRAM_ID
                        + " AND p." + PROGRAM_ID + " = " + cursor.getInt(cursor.getColumnIndexOrThrow(PROGRAM_ID));

                Cursor cursor2 = db.rawQuery(selectWorkoutQuery, null);

                if (cursor2.moveToFirst()) {
                    do {
                        WorkoutModel workout = new WorkoutModel();
                        workout = new WorkoutModel(
                                cursor2.getInt(cursor2.getColumnIndexOrThrow(WORKOUT_ID)),
                                cursor2.getString(cursor2.getColumnIndexOrThrow(WORKOUT_NAME)),
                                cursor2.getInt(cursor2.getColumnIndexOrThrow(WORKOUT_TIME)),
                                cursor2.getInt(cursor2.getColumnIndexOrThrow(WORKOUT_CALORIE)),
                                cursor2.getInt(cursor2.getColumnIndexOrThrow(WORKOUT_STATUS)),
                                cursor2.getString(cursor2.getColumnIndexOrThrow(WORKOUT_DESC))
                        );

                        String selectExerciseQuery = "SELECT e." + EXERCISE_ID + ", e." + EXERCISE_NAME
                                + ", e." + EXERCISE_TIME + ", e." + EXERCISE_COUNT + ", e." + EXERCISE_DESC
                                + ", e." + EXERCISE_IMAGE + ", e." + EXERCISE_GIFS
                                + " FROM " + TABLE_WORKOUT_EXERCISE + " we, " + TABLE_EXERCISE + " e, "
                                + TABLE_WORKOUT + " w WHERE e." + EXERCISE_ID
                                + " = we." + EXERCISE_ID + " AND w." + WORKOUT_ID + " = we." + WORKOUT_ID
                                + " AND w." + WORKOUT_ID + " = " + cursor2.getInt(cursor2.getColumnIndexOrThrow(WORKOUT_ID));

                        Cursor cursor3 = db.rawQuery(selectExerciseQuery, null);
                        ArrayList<ExerciseModel> exercises = new ArrayList<>();

                        if (cursor3.moveToFirst()) {
                            do {
                                exercises.add(
                                        new ExerciseModel(
                                                cursor3.getInt(cursor3.getColumnIndexOrThrow(EXERCISE_ID)),
                                                cursor3.getString(cursor3.getColumnIndexOrThrow(EXERCISE_NAME)),
                                                cursor3.getString(cursor3.getColumnIndexOrThrow(EXERCISE_DESC)),
                                                cursor3.getInt(cursor3.getColumnIndexOrThrow(EXERCISE_COUNT)),
                                                cursor3.getInt(cursor3.getColumnIndexOrThrow(EXERCISE_TIME)),
                                                cursor3.getInt(cursor3.getColumnIndexOrThrow(EXERCISE_IMAGE)),
                                                cursor3.getInt(cursor3.getColumnIndexOrThrow(EXERCISE_GIFS))
                                        )
                                );
                            } while (cursor3.moveToNext());
                        }

                        workout.setExercices(exercises);
                        workouts.add(workout);

                    } while (cursor2.moveToNext());
                }
                program.setWorkouts(workouts);

            } while (cursor.moveToNext());
        }
        return program;
    }

    public ArrayList<ExerciseModel> getAllExercises() {
        ArrayList<ExerciseModel> exerciseModelArrayList = new ArrayList<>();

        String selectExerciseQuery = "SELECT * FROM " + TABLE_EXERCISE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectExerciseQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ExerciseModel exerciseModel = new ExerciseModel();

                exerciseModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(EXERCISE_ID)));
                exerciseModel.setName(cursor.getString(cursor.getColumnIndexOrThrow(EXERCISE_NAME)));
                exerciseModel.setTime_second(cursor.getInt(cursor.getColumnIndexOrThrow(EXERCISE_TIME)));
                exerciseModel.setCount(cursor.getInt(cursor.getColumnIndexOrThrow(EXERCISE_COUNT)));
                exerciseModel.setDesc(cursor.getString(cursor.getColumnIndexOrThrow(EXERCISE_DESC)));

                exerciseModelArrayList.add(exerciseModel);

            } while (cursor.moveToNext());
        }
        return exerciseModelArrayList;
    }

    public ArrayList<ActivityModel> getAllActivity() {
        ArrayList<ActivityModel> activityModelArrayList = new ArrayList<>();

        String selectExerciseQuery = "SELECT * FROM " + TABLE_ACTIVITY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectExerciseQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ActivityModel activityModel = new ActivityModel();

                activityModel.setActivity_id(cursor.getInt(cursor.getColumnIndexOrThrow(ACTIVITY_ID)));
                activityModel.setName(cursor.getString(cursor.getColumnIndexOrThrow(ACTIVITY_NAME)));
                activityModel.setCalorie_hour(cursor.getInt(cursor.getColumnIndexOrThrow(ACTIVITY_CALORIE)));
                activityModel.setImage(cursor.getInt(cursor.getColumnIndexOrThrow(ACTIVITY_IMAGE)));
                activityModel.setImageURL(cursor.getString(cursor.getColumnIndexOrThrow(ACTIVITY_IMAGE_URL)));

                activityModelArrayList.add(activityModel);

            } while (cursor.moveToNext());
        }
        return activityModelArrayList;
    }

    public ArrayList<ProgramModel> getAllPrograms() {
        ArrayList<ProgramModel> programsList = new ArrayList<>();

        String selectProgramQuery = "SELECT * FROM " + TABLE_PROGRAM;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectProgramQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ProgramModel programModel = new ProgramModel();
                programModel.setProgram_id(cursor.getInt(cursor.getColumnIndexOrThrow(PROGRAM_ID)));
                programModel.setName(cursor.getString(cursor.getColumnIndexOrThrow(PROGRAM_NAME)));
                programModel.setLevel(cursor.getInt(cursor.getColumnIndexOrThrow(PROGRAM_LEVEL)));
                programModel.setCountWeeks(cursor.getInt(cursor.getColumnIndexOrThrow(PROGRAM_WEEKS_COUNT)));
                programModel.setDesc(cursor.getString(cursor.getColumnIndexOrThrow(PROGRAM_DESC)));
                programModel.setUrlImage(cursor.getString(cursor.getColumnIndexOrThrow(PROGRAM_IMAGE_URL)));
                programModel.setImage(cursor.getInt(cursor.getColumnIndexOrThrow(PROGRAM_IMAGE)));

                ArrayList<WorkoutModel> workouts = new ArrayList<>();

                //SELECT e.exercise_id, e.name, e.time_seconds, e.count_approaches, e.description
                //FROm exercises e, workouts w, workout_exercise we
                // where w.workout_id = 1 and e.exercise_id = we.exercise_id

                String selectWorkoutQuery = "SELECT w." + WORKOUT_ID + ", w." + WORKOUT_NAME
                        + ", w." + WORKOUT_TIME + ", w." + WORKOUT_CALORIE + ", w." + WORKOUT_STATUS
                        + ", w." + WORKOUT_DESC
                        + " FROM " + TABLE_PROGRAM_WORKOUT + " pw, " + TABLE_WORKOUT + " w, "
                        + TABLE_PROGRAM + " p WHERE w." + WORKOUT_ID
                        + " = pw." + WORKOUT_ID + " AND p." + PROGRAM_ID + " = pw." + PROGRAM_ID
                        + " AND p." + PROGRAM_ID + " = " + cursor.getInt(cursor.getColumnIndexOrThrow(PROGRAM_ID));

                Cursor cursor2 = db.rawQuery(selectWorkoutQuery, null);

                if (cursor2.moveToFirst()) {
                    do {
                        WorkoutModel workout = new WorkoutModel();
                        workout = new WorkoutModel(
                                cursor2.getInt(cursor2.getColumnIndexOrThrow(WORKOUT_ID)),
                                cursor2.getString(cursor2.getColumnIndexOrThrow(WORKOUT_NAME)),
                                cursor2.getInt(cursor2.getColumnIndexOrThrow(WORKOUT_TIME)),
                                cursor2.getInt(cursor2.getColumnIndexOrThrow(WORKOUT_CALORIE)),
                                cursor2.getInt(cursor2.getColumnIndexOrThrow(WORKOUT_STATUS)),
                                cursor2.getString(cursor2.getColumnIndexOrThrow(WORKOUT_DESC))
                        );

                        String selectExerciseQuery = "SELECT e." + EXERCISE_ID + ", e." + EXERCISE_NAME
                                + ", e." + EXERCISE_TIME + ", e." + EXERCISE_COUNT + ", e." + EXERCISE_DESC
                                + ", e." + EXERCISE_IMAGE + ", e." + EXERCISE_GIFS
                                + " FROM " + TABLE_WORKOUT_EXERCISE + " we, " + TABLE_EXERCISE + " e, "
                                + TABLE_WORKOUT + " w WHERE e." + EXERCISE_ID
                                + " = we." + EXERCISE_ID + " AND w." + WORKOUT_ID + " = we." + WORKOUT_ID
                                + " AND w." + WORKOUT_ID + " = " + cursor2.getInt(cursor2.getColumnIndexOrThrow(WORKOUT_ID));

                        Cursor cursor3 = db.rawQuery(selectExerciseQuery, null);
                        ArrayList<ExerciseModel> exercises = new ArrayList<>();

                        if (cursor3.moveToFirst()) {
                            do {
                                exercises.add(
                                        new ExerciseModel(
                                                cursor3.getInt(cursor3.getColumnIndexOrThrow(EXERCISE_ID)),
                                                cursor3.getString(cursor3.getColumnIndexOrThrow(EXERCISE_NAME)),
                                                cursor3.getString(cursor3.getColumnIndexOrThrow(EXERCISE_DESC)),
                                                cursor3.getInt(cursor3.getColumnIndexOrThrow(EXERCISE_COUNT)),
                                                cursor3.getInt(cursor3.getColumnIndexOrThrow(EXERCISE_TIME)),
                                                cursor3.getInt(cursor3.getColumnIndexOrThrow(EXERCISE_IMAGE)),
                                                cursor3.getInt(cursor3.getColumnIndexOrThrow(EXERCISE_GIFS))
                                        )
                                );
                            } while (cursor3.moveToNext());
                        }

                        workout.setExercices(exercises);
                        workouts.add(workout);

                    } while (cursor2.moveToNext());
                }
                programModel.setWorkouts(workouts);
                programsList.add(programModel);

            } while (cursor.moveToNext());
        }
        return programsList;
    }



    public ArrayList<LevelModel> getAllLevels() {

        ArrayList<LevelModel> levelsList = new ArrayList<>();
        String selectLevelQuery = "SELECT * FROM " + TABLE_LEVEL;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectLevelQuery, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    LevelModel levelModel = new LevelModel();
                    levelModel.setLevel_id(cursor.getInt(cursor.getColumnIndexOrThrow(LEVEL_ID)));
                    levelModel.setName(cursor.getString(cursor.getColumnIndexOrThrow(LEVEL_NAME)));
                    levelModel.setLevel(cursor.getInt(cursor.getColumnIndexOrThrow(LEVEL_VALUE)));
                    levelModel.setImageURL(cursor.getString(cursor.getColumnIndexOrThrow(LEVEL_IMAGE_URL)));
                    levelModel.setImage(cursor.getInt(cursor.getColumnIndexOrThrow(LEVEL_IMAGE)));

                    ArrayList<ProgramModel> programsList = new ArrayList<>();


                    String selectProgramQuery = "SELECT p." + PROGRAM_ID + ", p." + PROGRAM_NAME
                            + ", p." + PROGRAM_LEVEL + ", p." + PROGRAM_WEEKS_COUNT + ", p." + PROGRAM_DESC
                            + ", p." + WORKOUT_DESC + ", p." + PROGRAM_IMAGE_URL + ", p." + PROGRAM_IMAGE
                            + " FROM " + TABLE_LEVEL_PROGRAM + " lp, " + TABLE_LEVEL + " l, "
                            + TABLE_PROGRAM + " p WHERE l." + LEVEL_ID
                            + " = lp." + LEVEL_ID + " AND p." + PROGRAM_ID + " = lp." + PROGRAM_ID
                            + " AND l." + LEVEL_ID + " = " + cursor.getInt(cursor.getColumnIndexOrThrow(LEVEL_ID));

                    Cursor cursor2 = db.rawQuery(selectProgramQuery, null);

                    try {
                        if (cursor2.moveToFirst()) {
                            do {
                                ProgramModel programModel = new ProgramModel();
                                programModel.setProgram_id(cursor2.getInt(cursor2.getColumnIndexOrThrow(PROGRAM_ID)));
                                programModel.setName(cursor2.getString(cursor2.getColumnIndexOrThrow(PROGRAM_NAME)));
                                programModel.setLevel(cursor2.getInt(cursor2.getColumnIndexOrThrow(PROGRAM_LEVEL)));
                                programModel.setCountWeeks(cursor2.getInt(cursor2.getColumnIndexOrThrow(PROGRAM_WEEKS_COUNT)));
                                programModel.setDesc(cursor2.getString(cursor2.getColumnIndexOrThrow(PROGRAM_DESC)));
                                programModel.setUrlImage(cursor2.getString(cursor2.getColumnIndexOrThrow(PROGRAM_IMAGE_URL)));
                                programModel.setImage(cursor2.getInt(cursor2.getColumnIndexOrThrow(PROGRAM_IMAGE)));

                                ArrayList<WorkoutModel> workouts = new ArrayList<>();

                                //SELECT e.exercise_id, e.name, e.time_seconds, e.count_approaches, e.description
                                //FROm exercises e, workouts w, workout_exercise we
                                // where w.workout_id = 1 and e.exercise_id = we.exercise_id

                                String selectWorkoutQuery = "SELECT w." + WORKOUT_ID + ", w." + WORKOUT_NAME
                                        + ", w." + WORKOUT_TIME + ", w." + WORKOUT_CALORIE + ", w." + WORKOUT_STATUS
                                        + ", w." + WORKOUT_DESC
                                        + " FROM " + TABLE_PROGRAM_WORKOUT + " pw, " + TABLE_WORKOUT + " w, "
                                        + TABLE_PROGRAM + " p WHERE w." + WORKOUT_ID
                                        + " = pw." + WORKOUT_ID + " AND p." + PROGRAM_ID + " = pw." + PROGRAM_ID
                                        + " AND p." + PROGRAM_ID + " = " + cursor2.getInt(cursor2.getColumnIndexOrThrow(PROGRAM_ID));

                                Cursor cursor3 = db.rawQuery(selectWorkoutQuery, null);

                                try {
                                    if (cursor3.moveToFirst()) {
                                        do {
                                            WorkoutModel workout = new WorkoutModel();
                                            workout = new WorkoutModel(
                                                    cursor3.getInt(cursor3.getColumnIndexOrThrow(WORKOUT_ID)),
                                                    cursor3.getString(cursor3.getColumnIndexOrThrow(WORKOUT_NAME)),
                                                    cursor3.getInt(cursor3.getColumnIndexOrThrow(WORKOUT_TIME)),
                                                    cursor3.getInt(cursor3.getColumnIndexOrThrow(WORKOUT_CALORIE)),
                                                    cursor3.getInt(cursor3.getColumnIndexOrThrow(WORKOUT_STATUS)),
                                                    cursor3.getString(cursor3.getColumnIndexOrThrow(WORKOUT_DESC))
                                            );


                                            String selectExerciseQuery = "SELECT e." + EXERCISE_ID + ", e." + EXERCISE_NAME
                                                    + ", e." + EXERCISE_TIME + ", e." + EXERCISE_COUNT + ", e." + EXERCISE_DESC
                                                    + ", e." + EXERCISE_IMAGE + ", e." + EXERCISE_GIFS
                                                    + " FROM " + TABLE_WORKOUT_EXERCISE + " we, " + TABLE_EXERCISE + " e, "
                                                    + TABLE_WORKOUT + " w WHERE e." + EXERCISE_ID
                                                    + " = we." + EXERCISE_ID + " AND w." + WORKOUT_ID + " = we." + WORKOUT_ID
                                                    + " AND w." + WORKOUT_ID + " = " + cursor3.getInt(cursor3.getColumnIndexOrThrow(WORKOUT_ID));

                                            Cursor cursor4 = db.rawQuery(selectExerciseQuery, null);
                                            ArrayList<ExerciseModel> exercises = new ArrayList<>();

                                            try {
                                                if (cursor4.moveToFirst()) {
                                                    do {
                                                        exercises.add(
                                                                new ExerciseModel(
                                                                        cursor4.getInt(cursor4.getColumnIndexOrThrow(EXERCISE_ID)),
                                                                        cursor4.getString(cursor4.getColumnIndexOrThrow(EXERCISE_NAME)),
                                                                        cursor4.getString(cursor4.getColumnIndexOrThrow(EXERCISE_DESC)),
                                                                        cursor4.getInt(cursor4.getColumnIndexOrThrow(EXERCISE_COUNT)),
                                                                        cursor4.getInt(cursor4.getColumnIndexOrThrow(EXERCISE_TIME)),
                                                                        cursor4.getInt(cursor4.getColumnIndexOrThrow(EXERCISE_IMAGE)),
                                                                        cursor4.getInt(cursor4.getColumnIndexOrThrow(EXERCISE_GIFS))
                                                                )
                                                        );
                                                    } while (cursor4.moveToNext());
                                                }
                                            }finally {
                                                cursor4.close();
                                            }

                                            workout.setExercices(exercises);
                                            workouts.add(workout);

                                        } while (cursor3.moveToNext());
                                    }
                                }finally {
                                    cursor3.close();
                                }


                                programModel.setWorkouts(workouts);
                                programsList.add(programModel);

                            } while (cursor2.moveToNext());
                        }
                    }finally {
                        cursor2.close();
                    }

                    levelModel.setPrograms(programsList);
                    levelsList.add(levelModel);

                } while (cursor.moveToNext());
            }
        }finally {
            cursor.close();
        }

        return levelsList;
    }

    public ArrayList<WorkoutModel> getAllWorkouts() {
        ArrayList<WorkoutModel> workoutModelArrayList = new ArrayList<WorkoutModel>();

        String selectWorkoutQuery = "SELECT * FROM " + TABLE_WORKOUT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectWorkoutQuery, null);

        if (cursor.moveToFirst()) {
            do {
                WorkoutModel workoutModel = new WorkoutModel();
                workoutModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(WORKOUT_ID)));
                workoutModel.setName(cursor.getString(cursor.getColumnIndexOrThrow(WORKOUT_NAME)));
                workoutModel.setTime(cursor.getInt(cursor.getColumnIndexOrThrow(WORKOUT_TIME)));
                workoutModel.setCalorie(cursor.getInt(cursor.getColumnIndexOrThrow(WORKOUT_CALORIE)));
                workoutModel.setStatus(cursor.getInt(cursor.getColumnIndexOrThrow(WORKOUT_STATUS)));
                workoutModel.setDesc(cursor.getString(cursor.getColumnIndexOrThrow(WORKOUT_DESC)));

                ArrayList<ExerciseModel> exercises = new ArrayList<>();

                //SELECT e.exercise_id, e.name, e.time_seconds, e.count_approaches, e.description
                //FROm exercises e, workouts w, workout_exercise we
                // where w.workout_id = 1 and e.exercise_id = we.exercise_id

                String selectExerciseQuery = "SELECT e." + EXERCISE_ID + ", e." + EXERCISE_NAME
                        + ", e." + EXERCISE_TIME + ", e." + EXERCISE_COUNT + ", e." + EXERCISE_DESC
                        + ", e." + EXERCISE_IMAGE + ", e." + EXERCISE_GIFS
                        + " FROM " + TABLE_WORKOUT_EXERCISE + " we, " + TABLE_EXERCISE + " e, "
                        + TABLE_WORKOUT + " w WHERE e." + EXERCISE_ID
                        + " = we." + EXERCISE_ID + " AND w." + WORKOUT_ID + " = we." + WORKOUT_ID
                        + " AND w." + WORKOUT_ID + " = " + cursor.getInt(cursor.getColumnIndexOrThrow(WORKOUT_ID));

                Cursor cursor2 = db.rawQuery(selectExerciseQuery, null);

                if (cursor2.moveToFirst()) {
                    do {
                        exercises.add(
                                new ExerciseModel(
                                        cursor2.getInt(cursor2.getColumnIndexOrThrow(EXERCISE_ID)),
                                        cursor2.getString(cursor2.getColumnIndexOrThrow(EXERCISE_NAME)),
                                        cursor2.getString(cursor2.getColumnIndexOrThrow(EXERCISE_DESC)),
                                        cursor2.getInt(cursor2.getColumnIndexOrThrow(EXERCISE_COUNT)),
                                        cursor2.getInt(cursor2.getColumnIndexOrThrow(EXERCISE_TIME)),
                                        cursor2.getInt(cursor2.getColumnIndexOrThrow(EXERCISE_IMAGE)),
                                        cursor2.getInt(cursor2.getColumnIndexOrThrow(EXERCISE_GIFS))
                                )
                        );
                    } while (cursor2.moveToNext());
                }

                workoutModel.setExercices(exercises);
                workoutModelArrayList.add(workoutModel);

            } while (cursor.moveToNext());
        }
        return workoutModelArrayList;
    }


    public ArrayList<HistoryWorkoutModel> getAllHistoryWorkout(String _uid) {
        ArrayList<HistoryWorkoutModel> historyWorkoutModelArrayList = new ArrayList<>();
        final String HISTORY_COUNT_EXERCISE = "count_exercise";

        String selectQuery = "SELECT hw.*, w." + WORKOUT_NAME + ", COUNT(we." + WORKOUT_ID + ") as " + HISTORY_COUNT_EXERCISE +
                " FROM " + TABLE_HISTORY_WORKOUT + " hw, " + TABLE_WORKOUT + " w, " + TABLE_USER + " u, " + TABLE_WORKOUT_EXERCISE + " we " +
                " WHERE hw." + WORKOUT_ID + " = w." + WORKOUT_ID
                + " AND hw." + USER_ID + " = u." + USER_ID + " AND u." + USER_UID + " = '" + _uid + "'"
                + " AND w." + WORKOUT_ID + " = we." + WORKOUT_ID
                + " GROUP BY hw." + HISTORY_WORKOUT_DATE + " ORDER BY hw." + HISTORY_WORKOUT_DATE + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HistoryWorkoutModel historyWorkoutModel = new HistoryWorkoutModel();
                historyWorkoutModel.setHistory_workout_id(cursor.getInt(cursor.getColumnIndexOrThrow(HISTORY_WORKOUT_ID)));
                historyWorkoutModel.setUser_id(cursor.getInt(cursor.getColumnIndexOrThrow(USER_ID)));
                historyWorkoutModel.setWorkout_id(cursor.getColumnIndexOrThrow(WORKOUT_ID));
                historyWorkoutModel.setTime(cursor.getInt(cursor.getColumnIndexOrThrow(HISTORY_WORKOUT_TIME)));
                historyWorkoutModel.setCalorie(cursor.getInt(cursor.getColumnIndexOrThrow(HISTORY_WORKOUT_CALORIE)));
                historyWorkoutModel.setDateTime(cursor.getString(cursor.getColumnIndexOrThrow(HISTORY_WORKOUT_DATE)));
                historyWorkoutModel.setWorkout_name(cursor.getString(cursor.getColumnIndexOrThrow(WORKOUT_NAME)));
                historyWorkoutModel.setCount_exercise(cursor.getInt(cursor.getColumnIndexOrThrow(HISTORY_COUNT_EXERCISE)));

                historyWorkoutModelArrayList.add(historyWorkoutModel);
            } while (cursor.moveToNext());
        }
        return historyWorkoutModelArrayList;
    }

    public ArrayList<HistoryFoodModel> getAllHistoryFood(String _uid) {
        ArrayList<HistoryFoodModel> historyWorkoutModelArrayList = new ArrayList<>();

        final String HISTORY_COUNT_EXERCISE = "count_foods";

        /*
        String selectExerciseQuery = "SELECT hw.*, w."+WORKOUT_NAME+", COUNT(we."+WORKOUT_ID+") as "+HISTORY_COUNT_EXERCISE+" FROM " + TABLE_HISTORY_WORKOUT +" hw, "
                +TABLE_WORKOUT+" w, "+TABLE_USER+" u, "+TABLE_WORKOUT_EXERCISE+" we WHERE hw."+WORKOUT_ID+" = w."+WORKOUT_ID
                +" AND hw."+USER_ID+" = u."+USER_ID+" AND u."+USER_ID +" = "+user_id +" AND "+HISTORY_WORKOUT_DATE+" = strftime('%Y-%m-%d', '"+date+"' )"
                +" AND w."+WORKOUT_ID+" = we."+WORKOUT_ID+" GROUP BY hw."+HISTORY_WORKOUT_DATE;
*/
        String selectQuery = "SELECT * FROM " + TABLE_HISTORY_FOOD + " hf ORDER BY hf." + HISTORY_FOOD_DATE + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HistoryFoodModel historyWorkoutModel = new HistoryFoodModel();
                historyWorkoutModel.setHistory_food_id(cursor.getInt(cursor.getColumnIndexOrThrow(HISTORY_FOOD_ID)));
                historyWorkoutModel.setUser_id(cursor.getInt(cursor.getColumnIndexOrThrow(USER_ID)));
                historyWorkoutModel.setCalorieTotal(cursor.getDouble(cursor.getColumnIndexOrThrow(HISTORY_FOOD_CALORIE)));
                historyWorkoutModel.setDate(cursor.getString(cursor.getColumnIndexOrThrow(HISTORY_FOOD_DATE)));

                historyWorkoutModelArrayList.add(historyWorkoutModel);
            } while (cursor.moveToNext());
        }

        return historyWorkoutModelArrayList;
    }

    public HistoryProgramModel getLastHistoryProgram(int user_id) {
        HistoryProgramModel historyProgram = new HistoryProgramModel();

        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "select hp.* from "+TABLE_USER+" u, "+TABLE_HISTORY_PROGRAM+" hp \n" +
                " where u."+USER_ID+" = hp."+USER_ID+" and u."+USER_ID+" = "+user_id+
                " and hp."+PROGRAM_ID+" = u."+PROGRAM_ID+
                " order by hp."+HISTORY_PROGRAM_DATE_START+" desc limit 1";

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                historyProgram.setHistory_program_id(cursor.getInt(cursor.getColumnIndexOrThrow(HISTORY_PROGRAM_ID)));
                historyProgram.setUser_id(cursor.getInt(cursor.getColumnIndexOrThrow(USER_ID)));
                historyProgram.setProgram_id(cursor.getInt(cursor.getColumnIndexOrThrow(PROGRAM_ID)));
                historyProgram.setDateStart(cursor.getString(cursor.getColumnIndexOrThrow(HISTORY_PROGRAM_DATE_START)));
                historyProgram.setDateEnd(cursor.getString(cursor.getColumnIndexOrThrow(HISTORY_PROGRAM_DATE_END)));
                historyProgram.setStatus(cursor.getInt(cursor.getColumnIndexOrThrow(HISTORY_PROGRAM_STATUS)));
            } while (cursor.moveToNext());
        }
        return historyProgram;
    }

}
