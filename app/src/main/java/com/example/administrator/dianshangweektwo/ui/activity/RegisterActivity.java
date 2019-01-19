package com.example.administrator.dianshangweektwo.ui.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.dianshangweektwo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_pwd)
    EditText edPwd;
    @BindView(R.id.ed_pwd2)
    EditText edPwd2;
    @BindView(R.id.btn_zc)
    Button btnZc;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_zc)
    public void onViewClicked() {
        final String name = edName.getText().toString();
        final String pwd = edPwd.getText().toString();
        String pwd2 = edPwd2.getText().toString();

        if (name.trim().equals("") || pwd.trim().equals("") || pwd2.trim().equals("")){
            Toast.makeText(this, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }else if (!(pwd.trim().equals(pwd2.trim()))) {
            Toast.makeText(this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }

        /*AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterActivity.this)
                .setTitle("注册")
                .setMessage("你确定注册信息吗")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {*/
                        sharedPreferences = getSharedPreferences("userinfo",MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        editor.putString("name",name);
                        editor.putString("pwd",pwd);
                        editor.commit();

                        Toast.makeText(RegisterActivity.this, "恭喜，注册成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                 /*   }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });*/





    }
}
