package com.example.dk_mpis_03;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InfoActivity extends AppCompatActivity {

    TextView textViewUserInfo, textViewRoute;
    Button buttonSetPath, buttonCallTaxi, authorBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textViewUserInfo = findViewById(R.id.textViewUserInfo);
        textViewRoute = findViewById(R.id.textViewRoute);
        buttonSetPath = findViewById(R.id.buttonSetPath);
        buttonCallTaxi = findViewById(R.id.buttonCallTaxi);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String surname = intent.getStringExtra("surname");
        String phone = intent.getStringExtra("phone");

        textViewUserInfo.setText("Имя: " + name + "\nФамилия: " + surname + "\nТелефон: " + phone);

        buttonSetPath.setOnClickListener(v -> {
            Intent setPathIntent = new Intent(InfoActivity.this, PathActivity.class);
            startActivityForResult(setPathIntent, 1);
        });

        buttonCallTaxi.setOnClickListener(v -> {
            showAlertDialog("Вызов", "Такси в пути");
        });

        authorBtn = findViewById(R.id.author);
        authorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog("РАЗРАБОТАЛ", "Дима Кашпир АС-64");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String route = data.getStringExtra("route");
            textViewRoute.setText(route);
            buttonCallTaxi.setEnabled(true);
        }
    }

    private void showAlertDialog(String title, String message) {
        new AlertDialog.Builder(InfoActivity.this)
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