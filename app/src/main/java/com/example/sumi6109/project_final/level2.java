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


public class level2 extends Activity {

    private String category;
    //   private DataBaseOperation level1 =new DataBaseOperation(this);
    private ListView level1_question;
    private Button button_submit;
    private int itemSelected_q1=-1;
    private String question="one";
    private String answer1;
    private String question1;
    //private String score;
    private int score1;
    DataBaseOperation level2=new DataBaseOperation(this);

    Random rand2=new Random();
    int i1 =rand2.nextInt(2)+1;
    // int score =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // TextView tx1= (TextView)findViewById(R.id.level2);
      //  tx1.setText("level2");
        setContentView(R.layout.activity_level2);
        try {

            level2.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {
            level2.openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        level1_question =(ListView)findViewById(R.id.level2_question);
        button_submit=(Button)findViewById(R.id.button_submit2);
       score1=getIntent().getIntExtra("score", 0);
        category =getIntent().getStringExtra("category");
       // level1_question.clearChoices();
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
                TextView text = (TextView) level1_question.getChildAt(itemSelected_q1);

                SQLiteDatabase db = level2.getReadableDatabase();
                Cursor cursor = db.query(category, new String[]{"question", "option1 ", "option2", "option3", "option4", "answer"}, "level=?", new String[]{""+i1}, null, null, null);

                while (cursor.moveToNext()){
                    question1=cursor.getString(0);
                    answer1=cursor.getString(5);

                }
                cursor.close();

                if (text.getText().toString().equals(answer1)) {

                    score1++;
                    Toast.makeText(level2.this,"You Answer Is Correct",
                            Toast.LENGTH_SHORT
                    ).show();
                    //  intent.putExtra("category", text.getText().toString());
                    //startActivity(intent);
                }
                else {
                    if (text.getText().toString().equals(question1)) {
                        Toast.makeText(level2.this, "You Answer Is question,chutiye",
                                Toast.LENGTH_SHORT
                        ).show();
                    } else {
                        Toast.makeText(level2.this, "You Answer Is Wrong,Fuckoff",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }

                Intent intent = new Intent(level2.this, level3.class);
                intent.putExtra("score",score1);
                intent.putExtra("category", category);

                // intent.putExtra("category", text.getText().toString());
                startActivity(intent);
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
        ArrayAdapter<String> adapter_question2=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked);
       adapter_question2.clear();
        SQLiteDatabase db = level2.getReadableDatabase();;
        Cursor cursor = db.query(category, new String[]{"question", "option1 ", "option2", "option3", "option4", "answer"},"level=?", new String[]{""+i1}, null, null,null);
        while (cursor.moveToNext()) {

            adapter_question2.add(cursor.getString(0));
            adapter_question2.add(cursor.getString(1));
            adapter_question2.add(cursor.getString(2));
            adapter_question2.add(cursor.getString(3));
            adapter_question2.add(cursor.getString(4));
        }
        cursor.close();
        return adapter_question2;


    }
}






























