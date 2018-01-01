package com.gongyunhaoyyy.timesaver;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText email,pass,user_name;
    Button register;
    String number;
    String mess;
    String data;
    String success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );
        email = findViewById( R.id.reg_et1 );
        pass = findViewById( R.id.reg_et3 );
        register = findViewById( R.id.reg_bt );
        user_name=findViewById( R.id.reg_et2 );
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
        register.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick(View v) {

                final String us=user_name.getText().toString();
                final String ps=pass.getText().toString();
                final String em=email.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient okHttpClient = new OkHttpClient();
                        try{
                            RequestBody requestBody = new FormBody.Builder()
                                    .add("username",us)
                                    .add("password",ps)
                                    .add("telephone",em)
                                    .build();
                            Request request = new Request.Builder()
                                    .url("http://39.106.47.27:8080/user/doregister")
                                    .post(requestBody)
                                    .build();
                            Response response = okHttpClient.newCall(request).execute();
                            final String returnmessege=response.body().string();
                            Log.d( "register---------->",returnmessege );
                            String[] messs=returnmessege.split( "," );
                            mess=messs[1].split( ":" )[1];
                            success=messs[3].split( ":" )[1];
                            //莫名的出错，擦
//                            Gson gson=new Gson();
//                            List<jsonclass> mlist=gson.fromJson( returnmessege,new TypeToken<List<jsonclass>>(){}.getType());
//                            for (jsonclass jc : mlist){
//                                mess=jc.getMessage();
//                                data=jc.getData();
//                                success=jc.isSuccess();
//                            }
                            runOnUiThread( new Runnable( ) {
                                @Override
                                public void run() {
                                    Toast.makeText( RegisterActivity.this,mess,Toast.LENGTH_SHORT ).show();
                                    if (mess.equals( "\"注册成功\"" )){
                                        finish();
                                    }else {
                                    }
                                }
                            } );
                        }catch (IOException e){
                            e.printStackTrace();
                        }

                    }
                }).start();
            }
        } );

    }
}
