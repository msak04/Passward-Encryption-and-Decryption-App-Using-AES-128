package com.saifkhan.passwordencoderdecoderusingaes128;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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

public class decryption extends AppCompatActivity {
    Button decrypt;
    EditText eKey,CypherPass,DecryptedPass;
    private static final String characterEncoding = "UTF-8";
    private static final String cipherTransformation = "AES/CBC/PKCS5PADDING";
    private static final String aesEncryptionAlgorithem = "AES";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decryption);

        decrypt = (Button) findViewById(R.id.Button_Decrypt_Decryption);//get id of button 2
        eKey = (EditText)findViewById(R.id.EditText_EnterKey_Decryption);
        CypherPass = (EditText)findViewById(R.id.EditText_EnterCyphertext_Decryption);
        DecryptedPass = (EditText)findViewById(R.id.EditText_Password_Decryption);


        decrypt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String CPassword,EKey;
                CPassword = CypherPass.getText().toString();
                EKey = eKey.getText().toString();
                if(EKey.length()==16) {
                    String DPassword = decryptpassword(CPassword, EKey);
                    DecryptedPass.setText(DPassword);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Enter A 16 letter key"+EKey.length(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private String decryptpassword(String stringcyphertext, String encryptionKey) {
        // TODO Auto-generated method stub

        String StringDecText = "";
        try {
            Cipher cipher = Cipher.getInstance(cipherTransformation);
            byte[] key = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKey = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivparameterspec = new IvParameterSpec(key);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivparameterspec);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] cipherText = decoder.decode(stringcyphertext.getBytes("UTF8"));
            StringDecText = new String(cipher.doFinal(cipherText), "UTF-8");

            Toast.makeText(getApplicationContext(),StringDecText, Toast.LENGTH_SHORT).show();
        } catch (Exception E) {
            System.err.println("decrypt Exception : "+E.getMessage());
        }
        return StringDecText;
    }
}