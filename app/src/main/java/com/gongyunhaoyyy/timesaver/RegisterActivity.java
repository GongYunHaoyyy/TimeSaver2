package com.gongyunhaoyyy.timesaver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import java.io.IOException;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText email,pass,user_name;
    Button register;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );
        email = findViewById( R.id.reg_et1 );
        pass = findViewById( R.id.reg_et2 );
        register = findViewById( R.id.reg_bt );
        email.addTextChangedListener( new TextWatcher( ) {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                number=email.getText().toString();
                if (number.length()<10){
                    register.setEnabled( false );
                }else {
                    register.setEnabled( true );
                }
            }
        } );

        new Thread(new Runnable() {
            @Override
            public void run() {

                OkHttpClient okHttpClient = new OkHttpClient();

                try{

                    RequestBody requestBody = new FormBody.Builder()
                            .add("username","6")
                            .add("password","6")
                            .add("telephone","6")
                            .build();

                    Request request = new Request.Builder()
                            .url("http://39.106.47.27:8080/user/doregister")
                            .post(requestBody)
                            .build();

                    Response response = okHttpClient.newCall(request).execute();

                    Log.d("RegisterActivity",response.body().string());

                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        }).start();


    }
}
