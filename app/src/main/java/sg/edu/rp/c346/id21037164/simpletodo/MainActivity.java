package sg.edu.rp.c346.id21037164.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvAdd_DeleteTask;
    EditText etTask;
    Button btnAdd, btnDelete, btnClear;
    Spinner spnTaskOrDelete;
    ListView lvTask;
    ArrayList<String> alTask;
    ArrayAdapter<String> aaTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAdd_DeleteTask = findViewById(R.id.tvAdd_Remove);
        etTask = findViewById(R.id.etAddTask);
        spnTaskOrDelete = findViewById(R.id.spinner);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        btnClear = findViewById(R.id.btnClear);
        lvTask = findViewById(R.id.lvTask);

        alTask = new ArrayList<>();

        aaTask = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, alTask);

        lvTask.setAdapter(aaTask);

        spnTaskOrDelete.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 0:
                        tvAdd_DeleteTask.setText("Add a new Task");
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;

                    case 1:
                        tvAdd_DeleteTask.setText("Type the index of task in which you want to remove");
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String addTask = etTask.getText().toString();
                alTask.add(addTask);
                aaTask.notifyDataSetChanged();

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = Integer.parseInt(etTask.getText().toString());

                if (alTask.isEmpty() == true) {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_LONG).show();
                } else if (pos > alTask.size()) {
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_LONG).show();
                } else {
                    alTask.remove(pos);
                    aaTask.notifyDataSetChanged();
                }

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                alTask.clear();
                aaTask.notifyDataSetChanged();

            }
        });

    }
}