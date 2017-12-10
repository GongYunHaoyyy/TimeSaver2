package com.gongyunhaoyyy.timesaver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    EditText email,pass;
    Button register;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );
        email=(EditText)findViewById( R.id.reg_et1 );
        pass=(EditText)findViewById( R.id.reg_et2 );
        register=(Button)findViewById( R.id.reg_bt );
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
    }
}
