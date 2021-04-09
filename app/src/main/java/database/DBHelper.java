package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends AppCompatActivity {
    Context context;
    public DBHelper(Context context){
        this.context=context;
    }
    SQLiteDatabase sqLiteDatabase=null;
    String DB_NAME="sqlite.db";
    String DB_PATH="/databases/";
    public void addDB(){
        File file=context.getDatabasePath(DB_NAME);
        if(!file.exists()){
            copyDB();
        }else {
            file.delete();
            copyDB();
        }
    }
    void copyDB(){
        try {
            InputStream inputStream=context.getAssets().open(DB_NAME);
            String outFileName=context.getApplicationInfo().dataDir+DB_PATH+DB_NAME;
            File f=new File(context.getApplicationInfo().dataDir+DB_PATH);
            if(!f.exists()){
                f.mkdir();
            }
            OutputStream outputStream=new FileOutputStream(outFileName);
            byte[] buffer=new byte[1024];
            int len;
            while (((len=inputStream.read(buffer))>0)){
                outputStream.write(buffer,0,len);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            Log.e("Lỗi xịt","vcc");
        }catch (Exception e){
            Log.e("Lỗi",e.toString());
        }
    }
}
