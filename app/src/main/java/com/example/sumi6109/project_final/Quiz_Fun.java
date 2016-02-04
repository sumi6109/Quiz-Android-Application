package com.example.sumi6109.project_final;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
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

import java.io.IOException;
import java.util.logging.Level;


public class Quiz_Fun extends Activity {

    private ListView category;
    private int itemSelected = -1;
    // DataBaseOperation category_list = new DataBaseOperation(this);
    //category_list = new DataBaseOperation(this);
    private Button button_op;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz__fun);

        category = (ListView) findViewById(R.id.categories);
        // button_op=(Button)findViewById(R.id.button_open);
        category.setAdapter(getAllCategories());
        //  SQLiteDatabase db = .getWritableDatabase();

        //button_op.setOnClickListener(new View.OnClickListener() {
        // @Override
        //  public void onClick(View v) {


        //}
        //});

        category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Quiz_Fun.this.itemSelected = position;
                Intent intent = new Intent(Quiz_Fun.this, level1.class);
                TextView text = (TextView) category.getChildAt(itemSelected);
                intent.putExtra("category", text.getText().toString());
                startActivity(intent);
                itemSelected = -1;
                Quiz_Fun.this.invalidateOptionsMenu();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz__fun, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Intent intent = new Intent(this, level1.class);
        //  if (item.getItemId() == R.id.open) {
        //TextView text = (TextView) category.getChildAt(itemSelected);
        //   intent.putExtra("category", text.getText().toString());
        //startActivity(intent);
        //itemSelected = -1;
        //invalidateOptionsMenu();

        //}
        return true;

    }

    public void onResume(){
        super.onResume();
        category.setAdapter(getAllCategories());

    }

    public ArrayAdapter<String> getAllCategories(){


        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice);
        adapter.add("General");
        adapter.add("Math");
        adapter.add("Games");
        adapter.add("Literature");
        adapter.add("Geography");
        adapter.add("History");
        adapter.add("Sports");
        adapter.add("Events");
        adapter.add("Science");
        adapter.add("Movies");

        return adapter;

    }






}






































