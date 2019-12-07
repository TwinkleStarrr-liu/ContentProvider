package com.example.providertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addData = (Button)findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //添加数据
                Uri uri = Uri.parse("content://com.example.ex_2_wordbook/WordsBook");
                ContentValues values = new ContentValues();
                values.put("word","school");
                values.put("meanings","null");
                values.put("exS","null");
                Uri newUri=getContentResolver().insert(uri,values);
                newId=newUri.getPathSegments().get(1);
            }
        });
        Button queryData=(Button)findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("content://com.example.ex_2_wordbook/WordsBook");
                Cursor cursor=getContentResolver().query(uri,null,null,null,null);
                if (cursor!=null){
                    while(cursor.moveToNext()){
                        String word=cursor.getString(cursor.getColumnIndex("word"));
                        String meanings=cursor.getString(cursor.getColumnIndex("meanings"));
                        String exS=cursor.getString(cursor.getColumnIndex("exampleSentence"));
                        Log.d("MainActivity","word is"+word);
                        Log.d("MainActivity","meanings is"+meanings);
                        Log.d("MainActivity","exS is"+exS);
                    }
                    cursor.close();
                }
            }
        });
        Button updateData=(Button)findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("content://com.example.ex_2_wordbook/WordsBook/"+newId);
                ContentValues values=new ContentValues();
                values.put("word","orange");
                values.put("meanings","null");
                values.put("exS","null");
                getContentResolver().update(uri,values,null,null);
            }
        });
        Button deleteData = (Button)findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("content://com.example.ex_2_wordbook/WordsBook/"+newId);
                getContentResolver().delete(uri,null,null);
            }
        });
    }
}
