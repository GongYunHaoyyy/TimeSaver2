package com.gongyunhaoyyy.timesaver;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    Button login;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );
        login=(Button)findViewById( R.id.login_bt );
        register=(TextView)findViewById( R.id.register_tv );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        login.setOnClickListener( this );
        register.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_bt:
                Intent intent=new Intent( LoginActivity.this,MainActivity.class );
                startActivity( intent );
                break;
            case R.id.register_tv:
                Intent intent1=new Intent( LoginActivity.this,RegisterActivity.class );
                startActivity( intent1 );
                break;
        }
    }
}
