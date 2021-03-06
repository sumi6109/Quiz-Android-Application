package com.example.sumi6109.project_final;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;


public class level1 extends Activity {

    private String category;
    //   private DataBaseOperation level1 =new DataBaseOperation(this);
    private ListView level1_question;
    private Button button_submit;
    private int itemSelected_q1=-1;
    private String question="one";
    private String answer1;
    private String question1;
    DataBaseOperation level1=new DataBaseOperation(this);
    Random rand1=new Random();
    int i =rand1.nextInt(2)+1;
    int score =0;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);
        try {

            level1.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            level1.openDataBase();

        }catch(SQLException sqle){


        }
       // TextView tx= (TextView)findViewById(R.id.level);
        //tx.setText("level1");
        level1_question =(ListView)findViewById(R.id.level1_question);
        button_submit=(Button)findViewById(R.id.button_submit);

        category =getIntent().getStringExtra("category");
        level1_question.setAdapter(getQuestion());
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level1, menu);
        return true;
    }

    public void onResume(){

        super.onResume();

        level1_question.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemSelected_q1 = position;
            }
        });

        button_submit.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override

            public void onClick(View v) {
     Intent intent = new Intent(level1.this, level2.class);
    intent.putExtra("score",score);
                TextView text = (TextView) level1_question.getChildAt(itemSelected_q1);

                SQLiteDatabase db = level1.getReadableDatabase();
                Cursor cursor = db.query(category, new String[]{"question", "option1 ", "option2", "option3", "option4", "answer"}, "level=?", new String[]{""+i}, null, null, null);

                while (cursor.moveToNext()){
                    question1=cursor.getString(0);
                    answer1=cursor.getString(5);

                }
                cursor.close();

                if (text.getText().toString().equals(answer1)) {

                    score++;
                    Toast.makeText(level1.this,"You Answer Is Correct",
                            Toast.LENGTH_SHORT
                    ).show();

                }
                else {
                    if (text.getText().toString().equals(question1)) {
                        Toast.makeText(level1.this, "You Answer Is question,chutiye",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else {
                        Toast.makeText(level1.this, "You Answer Is Wrong,Fuckoff",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
                intent.putExtra("category", category);
                startActivity(intent);
                // intent.putExtra("category", text.getText().toString());
                //  startActivity(intent);
                itemSelected_q1 = -1;
                invalidateOptionsMenu();


            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public ArrayAdapter<String> getQuestion()
    {
        ArrayAdapter<String> adapter_question1=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked);
        adapter_question1.clear();
        SQLiteDatabase db = level1.getReadableDatabase();;
        Cursor cursor = db.query(category, new String[]{"question", "option1 ", "option2", "option3", "option4", "answer"}, "level =?", new String[]{""+i}, null, null,null);
        while (cursor.moveToNext()) {

            adapter_question1.add(cursor.getString(0));
            adapter_question1.add(cursor.getString(1));
            adapter_question1.add(cursor.getString(2));
            adapter_question1.add(cursor.getString(3));
            adapter_question1.add(cursor.getString(4));
        }
        cursor.close();
        return adapter_question1;


    }
}














