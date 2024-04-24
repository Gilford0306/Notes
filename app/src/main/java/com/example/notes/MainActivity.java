package com.example.notes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText header,editText;
    private Button load,save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        header = findViewById(R.id.header);
        editText = findViewById(R.id.editText);
        load = findViewById(R.id.load);
        save = findViewById(R.id.save);
    }
    public void saveData(View view) {
    String header_text = header.getText().toString();
    String text_text = editText.getText().toString();
        try {
            FileOutputStream fileOutput = openFileOutput("Data.txt", MODE_PRIVATE );
            try {
                fileOutput.write((header_text + "-" + text_text).getBytes());
                fileOutput.close();

                header.setText("");
                editText.setText("");
                Toast.makeText(this,"File is save", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this,"File not found", Toast.LENGTH_SHORT).show();


        }
    }

    public void getData(View view) {

            FileInputStream fileInput = null;
            try {
                fileInput = openFileInput("Data.txt" );
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            InputStreamReader inputStream = new InputStreamReader(fileInput);
            BufferedReader bR = new BufferedReader(inputStream);
            String lines ="";
            StringBuilder result = new StringBuilder();
                try {
                    while (((lines = bR.readLine())!=null)) {
                        result.append(lines).append("\n");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        Toast.makeText(this,result,Toast.LENGTH_LONG).show();


}
}