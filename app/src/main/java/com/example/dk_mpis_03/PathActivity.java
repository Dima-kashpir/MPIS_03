package com.example.dk_mpis_03;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PathActivity extends AppCompatActivity {
    EditText editTextStartPoint, editTextEndPoint, editTextDistance, editTextTime, editTextPrice, editTextCarType;
    Button buttonOk, authorBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_path);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextStartPoint = findViewById(R.id.editTextStartPoint);
        editTextEndPoint = findViewById(R.id.editTextEndPoint);
        editTextDistance = findViewById(R.id.editTextDistance);
        editTextTime = findViewById(R.id.editTextTime);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextCarType = findViewById(R.id.editTextCarType);
        buttonOk = findViewById(R.id.buttonOk);

        buttonOk.setOnClickListener(v -> {
            String route = "От " + editTextStartPoint.getText().toString() + " До " + editTextEndPoint.getText().toString() + "\n" +
                    "Дистанция: " + editTextDistance.getText().toString() + "\n" + "Время: " + editTextTime.getText().toString() + "\n" +
                    "Цена: " + editTextPrice.getText().toString() + "\n" + "Машина: " + editTextCarType.getText().toString();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("route", route);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        authorBtn = findViewById(R.id.author);
        authorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog("РАЗРАБОТАЛ", "Дима Кашпир АС-64");
            }
        });
    }

    private void showAlertDialog(String title, String message) {
        new AlertDialog.Builder(PathActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }
}