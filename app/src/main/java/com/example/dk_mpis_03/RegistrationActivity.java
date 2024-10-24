package com.example.dk_mpis_03;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistrationActivity extends AppCompatActivity {

    TextView textViewTitle;
    EditText editTextName, editTextSurname, editTextPhone;
    Button buttonRegister, authorBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textViewTitle = findViewById(R.id.textViewTitle);
        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextPhone = findViewById(R.id.editTextPhone);
        buttonRegister = findViewById(R.id.buttonRegister);

        SharedPreferences sharedPreferences = getSharedPreferences("TaxiAppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String savedName = sharedPreferences.getString("name", "");
        String savedSurname = sharedPreferences.getString("surname", "");
        String savedPhone = sharedPreferences.getString("phone", "");

        if (!savedName.isEmpty()) {
            editTextName.setText(savedName);
            editTextSurname.setText(savedSurname);
            editTextPhone.setText(savedPhone);
            buttonRegister.setText("Логин");
            textViewTitle.setText("Вход");
        }

        buttonRegister.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String surname = editTextSurname.getText().toString();
            String phone = editTextPhone.getText().toString();

            editor.putString("name", name);
            editor.putString("surname", surname);
            editor.putString("phone", phone);
            editor.apply();

            Intent intent = new Intent(RegistrationActivity.this, InfoActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("surname", surname);
            intent.putExtra("phone", phone);
            startActivity(intent);
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
        new AlertDialog.Builder(RegistrationActivity.this)
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