package com.example.sabod.testapp1;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName, editSurname, editMarks, editID;
    Button btnAddData, btnViewAll, btnID, btnDelete;

    public final static String EXTRA_MESSAGE = "com.example.sabod.testapp1.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.name_editText);
        editSurname = (EditText) findViewById(R.id.surname_editText);
        editMarks = (EditText) findViewById(R.id.marks_editText);
        editID = (EditText) findViewById(R.id.ID_editText);
        btnAddData = (Button) findViewById(R.id.add_button);
        btnViewAll = (Button) findViewById(R.id.viewAll_button);
        btnID = (Button) findViewById(R.id.update_button);
        btnDelete = (Button) findViewById(R.id.delete_button);

        // AddData();
        ViewAll();
        UpdateData();
        DeleteData();


        //getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editName.getText().toString());
                        if (deletedRows > 0)
                            Toast.makeText(MainActivity.this, "Data delete", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data Not delete", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void UpdateData() {
        btnID.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(editID.getText().toString(),
                                editName.getText().toString(),
                                editSurname.getText().toString(),
                                editMarks.getText().toString());
                        if (isUpdate)
                            Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data Not Updated", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void AddData(View v) {
        boolean isInserted = myDb.insertData(editName.getText().toString(),
                editSurname.getText().toString(),
                editMarks.getText().toString());
        if (isInserted)
            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();

    }

    public void ViewAll() {
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();

                        if (res.getCount() == 0) {
                            // Show message
                            ShowMessage("Error", "Nothing Found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            // int index1 = res.getColumnIndex("UID");
                            buffer.append("ID: " + res.getString(0) + "\n");
                            buffer.append("Name: " + res.getString(1) + "\n");
                            buffer.append("Surname: " + res.getString(2) + "\n");
                            buffer.append("Marks: " + res.getString(3) + "\n\n");

                        }
                        //Show all data
                        ShowMessage("Data", buffer.toString());
                    }
                }
        );
    }

    public void getNameData(View view) {

        String name = editName.getText().toString();
        String res = myDb.getData(name);
        ShowMessage("Data", res);
    }

    public void ShowMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        //return true;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_display_message, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        //int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.action_settings:
                // something;
                return true;
            case R.id.action_whiteboard:
                //openSearch();
                return true;
            case R.id.action_wod:
               // Intent intent = new Intent(this, DisplayMessageActivity.class);
                Intent intent = new Intent(this, WODActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_Previous_Performance:
                //openSearch();
                return true;
            case R.id.action_lift:
                //openSearch();
                Intent intent_lift = new Intent(this, LiftActivity.class);
                startActivity(intent_lift);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}
