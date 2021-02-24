package com.example.wanandroid.log_and_register;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.activitise.main.HomePageActivity;
import com.example.wanandroid.tools.POSTConnection_3;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LogInActivity extends AppCompatActivity {
    private EditText editText_account;
    private EditText editText_password;
    private Button button_register;
    private Button button_log_in;
    private ImageView imageView_eye;
    private ImageView imageView_user;
    private ImageView imageView_clock;
    TextView textView;
    TextView textView_visitor_log;
    TextView textView_way_of_log;
    // private POST_Connection post_connection = new POST_Connection();
    private POSTConnection_3 post_connection = new POSTConnection_3();
    private String responseData;
    private static int i = 2;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    String m = (String) msg.obj;
                    Toast.makeText(LogInActivity.this, m, Toast.LENGTH_SHORT).show();
                    Log.e("错误信息",m);
                    break;
                case 2:
                    String n = (String) msg.obj;
                    Toast.makeText(LogInActivity.this, n, Toast.LENGTH_SHORT).show();
                    Log.e("请求超时","请求超时");
                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        imageView_user = findViewById(R.id.im_log_in_username);
        imageView_clock = findViewById(R.id.im_log_in_clock);
        editText_account = findViewById(R.id.edit_LOGIN_account);
        editText_password = findViewById(R.id.edit_LOGIN_password);
        button_log_in = findViewById(R.id.bt_log_in);
        button_register = findViewById(R.id.bt_Register_intent);
        imageView_eye = findViewById(R.id.edit_eye);
        textView = findViewById(R.id.tv_log_in);
        textView_visitor_log = findViewById(R.id.visitor_log_in);
        textView_way_of_log = findViewById(R.id.way_of_log);

        SharedPreferences.Editor save_data = getSharedPreferences("user_data", MODE_PRIVATE).edit();
        SharedPreferences.Editor cookie_data = getSharedPreferences("cook_data", MODE_PRIVATE).edit();
        SharedPreferences save_da = getSharedPreferences("cook_data", MODE_PRIVATE);
        String u = save_da.getString("cookie", "");

        if (!TextUtils.isEmpty(u)) {
            Intent intent = new Intent(LogInActivity.this, HomePageActivity.class);
            startActivity(intent);
        }

//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LogInActivity.this, HomePageActivity.class);
//                cookie_data.putString("cookie", "loginUserName_wanandroid_com=Ms-url;token_pass_wanandroid_com=3e3be32c9fb0361dc09e120671e801b3;JSESSIONID=A6B832814D8FD80CB52F87BC13B84854;token_pass=3e3be32c9fb0361dc09e120671e801b3");
//                save_data.putString("username", "Ms-url");
//                save_data.putString("password", "url123");
//                save_data.putInt("user_id",87723);
//                cookie_data.apply();
//                save_data.apply();
//                startActivity(intent);
//                //测试专用通道
//            }
//        });

        textView_visitor_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });

        textView_way_of_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LogInActivity.this,"暂不支持其他方式登录",Toast.LENGTH_SHORT).show();
            }
        });



        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        button_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editText_account.getText().toString().trim();
                String password = editText_password.getText().toString().trim();
                Log.e("点击", "进入");

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LogInActivity.this, "账号和密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("username", username);
                    map.put("password", password);
                    Log.e("分支", "进入");

                    ProgressDialog progressDialog = new ProgressDialog(LogInActivity.this);
                    progressDialog.setTitle("正在登录...");
                    progressDialog.setMessage("Loading...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    new Thread(() -> {
                        try {
                            Thread.sleep(1200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        List<String> list = new ArrayList<>();
                        list = post_connection.sendGetNetRequest("https://www.wanandroid.com/user/login", map);
                        responseData = list.get(0);
                        Log.e("联接response", responseData);
                        try {
                            if (responseData.equals("1")) {
                                progressDialog.dismiss();
                                showResponse("请求超时", 2);
                            } else {
                                String cook = list.get(1);
                                cookie_data.putString("cookie", cook);
                                cookie_data.apply();
                                JSONObject jsonObject = new JSONObject(responseData);
                                int jsonerrorCode = jsonObject.getInt("errorCode");

                                if (jsonerrorCode == -1) {
                                    progressDialog.dismiss();
                                    String jsonerrorMsg = jsonObject.getString("errorMsg");
                                    showResponse(jsonerrorMsg, 1);
                                    Log.e("错误", "信息");
                                } else {
                                    progressDialog.dismiss();
                                    JSONObject jsonRightData = jsonObject.getJSONObject("data");
                                    String jsonUsername = jsonRightData.getString("username");
                                    int user_id = jsonRightData.getInt("id");

                                    save_data.putString("username", username);
                                    save_data.putString("password", password);
                                    save_data.putInt("user_id", user_id);
                                    save_data.apply();

                                    Intent intent = new Intent(LogInActivity.this, HomePageActivity.class);
                                    startActivity(intent);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }).start();
                }

            }
        });


        editText_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        imageView_eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i % 2 == 0) {
                    //如果选中，显示密码
                    imageView_eye.setImageResource(R.drawable.eye);
                    editText_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    i++;
                } else {
                    //否则隐藏密码
                    imageView_eye.setImageResource(R.drawable.closeeye);
                    editText_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    i++;
                }
            }
        });

        editText_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int mlongth = editText_password.length();
                if (mlongth == 0) {
                } else if (mlongth < 6 && mlongth > 0) {
                    editText_password.setHint("密码不能为空");//不能设置int，会闪退
                    editText_password.setHintTextColor(Color.parseColor("#FA1065"));
                    imageView_clock.setImageResource(R.drawable.clock_red2);
                } else {
                    imageView_clock.setImageResource(R.drawable.lock);
                }
            }
        });

        editText_account.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int mlongth = editText_account.length();
                if (mlongth == 0) {
                } else if (mlongth < 4 && mlongth > 0) {
                    editText_account.setHint("账号不能为空");//不能设置int，会闪退
                    editText_account.setHintTextColor(Color.parseColor("#FA1065"));
                    imageView_user.setImageResource(R.drawable.user_red);
                } else {
                    imageView_user.setImageResource(R.drawable.user);
                }
            }
        });
    }

    private void showResponse(String st, int num) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = num;
                message.obj = st;
                handler.sendMessage(message);
            }
        }).start();
    }
}
