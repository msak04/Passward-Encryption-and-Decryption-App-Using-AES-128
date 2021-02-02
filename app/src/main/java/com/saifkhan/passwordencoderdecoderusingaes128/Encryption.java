package com.saifkhan.passwordencoderdecoderusingaes128;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encryption extends AppCompatActivity {
    Button encrypt;
    EditText eKey,PlainPass,EncryptedPass;
    private static final String characterEncoding = "UTF-8";
    private static final String cipherTransformation = "AES/CBC/PKCS5PADDING";
    private static final String aesEncryptionAlgorithem = "AES";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption);

        encrypt = (Button) findViewById(R.id.Button_EnterKey_Encryption);//get id of button 2
        eKey = (EditText)findViewById(R.id.EditText_EnterKey_Encryption);
        PlainPass = (EditText)findViewById(R.id.EditText_EnterPassword_Encryption);
        EncryptedPass = (EditText)findViewById(R.id.EditText_EncryptedText_Encryption);


        encrypt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String PPassword,EKey;
                PPassword = PlainPass.getText().toString();
                EKey = eKey.getText().toString();
                if(EKey.length()==16) {
                    String EPassword = encryptpassword(PPassword, EKey);
                    EncryptedPass.setText(EPassword);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Enter A 16 letter key", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private static String encryptpassword(String plainText, String encryptionKey) {

        String StringEncText = "";
        try {
            Cipher cipher = Cipher.getInstance(cipherTransformation);
            byte[] key = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivparameterspec);
            byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF8"));
            Base64.Encoder encoder = Base64.getEncoder();
            StringEncText = encoder.encodeToString(cipherText);

        } catch (Exception E) {

        }
        return StringEncText;

    }




}