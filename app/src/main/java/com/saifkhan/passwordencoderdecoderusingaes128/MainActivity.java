package com.saifkhan.passwordencoderdecoderusingaes128;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button encryption, decryption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        encryption = (Button) findViewById(R.id.Button_Encode_Main);//get id of button 1
        decryption = (Button) findViewById(R.id.Button_Decode_Main);//get id of button 2

        encryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Encryption.class));
            }
        });
        decryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, decryption.class));
            }
        });

    }
}