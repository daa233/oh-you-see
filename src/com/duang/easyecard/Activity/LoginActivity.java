package com.duang.easyecard.Activity;

import com.duang.easyecard.R;
import com.duang.easyecard.R.id;
import com.duang.easyecard.R.layout;
import com.duang.easyecard.db.MyDatabaseHelper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	private MyDatabaseHelper dbHelper;
	
	private Button signin_button;
	private Button login_signup_button;
	private EditText mUsername;
	private EditText mPassword;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        
        signin_button = (Button) findViewById(R.id.signin_button);
        login_signup_button = (Button) findViewById(R.id.login_signup_button);
        mUsername = (EditText) findViewById(R.id.username_edit);
    	mPassword = (EditText) findViewById(R.id.password_edit); 
    	
    	//��¼��ť�ĵ���¼�
    	signin_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				login_main(v);
			}
		});
    	
    	//ע�ᰴť�ĵ���¼�
    	login_signup_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
				startActivity(intent);
			}
		});
	}
	
	//��¼�ж�
	public void login_main(View v)	{
		String username = mUsername.getText().toString();
		String password = mPassword.getText().toString();
		
		if (!username.isEmpty())	{
			if (wasSigned(username))	{
				if (!password.isEmpty())	{
					if (passwordIsRight(username, password))	{
						//��ת�������ܽ��棬����LoginActivity
						
					}	else	{
						Toast.makeText(LoginActivity.this, "�������", Toast.LENGTH_SHORT).show();
					}
				}	else	{
					Toast.makeText(LoginActivity.this, "����������", Toast.LENGTH_SHORT).show();
				}
				
			}	else	{
				Toast.makeText(LoginActivity.this, "δע����ʺ�", Toast.LENGTH_SHORT).show();
			}
		}	else	{
			Toast.makeText(LoginActivity.this, "�ʺŲ���Ϊ��", Toast.LENGTH_SHORT).show();
		}
	}
	
	//�ж������Ƿ���ȷ
	private boolean passwordIsRight(String username, String password)	{
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.query("BasicInfo", null, null, null, null, null, null);
		if (cursor.moveToFirst())	{
			do	{
				if (username.equals(cursor.getString(cursor.getColumnIndex("stu_id"))))	{
					if (password.equals(cursor.getString(cursor.getColumnIndex("password"))))	{
						return true;
					}	else
						return false;
				}	else	{
					return false;
				}
			}	while (cursor.moveToNext());
		}
		cursor.close();
		
		return false;
    }
	
	//�ж��ʺ��Ƿ��Ѿ�ע��
	private boolean wasSigned(String stu_id_input) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.query("BasicInfo", null, null, null, null, null, null);
		if (cursor.moveToFirst())	{
			do	{
				if (stu_id_input.equals(cursor.getString(cursor.getColumnIndex("stu_id"))))	{
					return true;
				}
			}	while (cursor.moveToNext());
		}
		cursor.close();
		return false;
	}
    
}