package com.gongyunhaoyyy.timesaver;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    Button login;
    TextView register;
    EditText tell,pass;
    String mess;
    String data;
    String success;
    SharedPreferences.Editor nameeditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        SharedPreferences ud=getSharedPreferences( "userdata", MODE_PRIVATE );
        String uddt=ud.getString( "getuserdata","" );
        if (!uddt.isEmpty()){
            Intent it=new Intent( LoginActivity.this,MainActivity.class );
            startActivity( it );
            finish();
        }else{
            setContentView( R.layout.activity_login );
            nameeditor = getSharedPreferences( "userdata", MODE_PRIVATE ).edit( );
            login=(Button)findViewById( R.id.login_bt );
            register=(TextView)findViewById( R.id.register_tv );
            tell=findViewById( R.id.telllll );
            pass=findViewById( R.id.passsss );
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
            }
            login.setOnClickListener( this );
            register.setOnClickListener( this );
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_bt:
                final String mtel=tell.getText().toString();
                final String mpass=pass.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient okHttpClient = new OkHttpClient();
                        try{
                            RequestBody requestBody = new FormBody.Builder()
                                    .add("telephone",mtel)
                                    .add("password",mpass)
                                    .build();
                            Request request = new Request.Builder()
                                    .url("http://39.106.47.27:8080//user/dologinT")
                                    .post(requestBody)
                                    .build();
                            Response response = okHttpClient.newCall(request).execute();
                            final String returnmessege=response.body().string();
                            Log.d( "login---------->",returnmessege );
                            String[] messs=returnmessege.split( "," );
                            mess=messs[1].split( ":" )[1];
                            success=messs[3].split( ":" )[1];

                            runOnUiThread( new Runnable( ) {
                                @Override
                                public void run() {
                                    if (mess.equals( "\"登录成功\"" )){
                                        String userdt=mtel+pass;
                                        nameeditor.putString( "getuserdata",userdt );
                                        nameeditor.apply();
                                        Toast.makeText( LoginActivity.this,mess,Toast.LENGTH_SHORT ).show();
                                        Intent intent=new Intent( LoginActivity.this,MainActivity.class );
                                        startActivity( intent );
                                        finish();
                                    }else {
                                        Toast.makeText( LoginActivity.this,mess,Toast.LENGTH_SHORT ).show();
                                    }
                                }
                            } );
                        }catch (IOException e){
                            e.printStackTrace();
                        }

                    }
                }).start();
                break;
            case R.id.register_tv:
                Intent intent1=new Intent( LoginActivity.this,RegisterActivity.class );
                startActivity( intent1 );
                break;
        }
    }
}
