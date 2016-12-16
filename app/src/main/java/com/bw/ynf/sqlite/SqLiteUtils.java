package com.bw.ynf.sqlite;

import android.content.Context;

/**Sqlite工具类，用来返回SQlite类的对象
 * Created by GaoJun on 2016/12/16 0016.
 */

public class SqLiteUtils {
    private static MySqlite mySqlite;

    public static MySqlite getMySqlite(Context context) {
        if(mySqlite==null){
            mySqlite = new MySqlite(context);
        }else {
            return mySqlite;
        }
        return mySqlite;
    }
}
