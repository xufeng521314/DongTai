package com.example.biyingxiangmu.ui;



import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biyingxiangmu.R;
import com.example.biyingxiangmu.base.BaseActivity;
import com.example.biyingxiangmu.bean.UserInfoBean;
import com.example.biyingxiangmu.constract.LoginConstract;
import com.example.biyingxiangmu.presenter.LoginPersenter;
import com.example.biyingxiangmu.utils.SecretUtils;
import com.example.biyingxiangmu.utils.SpUtils;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;

public class LoginActivity extends BaseActivity<LoginConstract.Presenter> implements LoginConstract.View {

    @BindView(R.id.edit_username)
    EditText editUsername;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.txt_login)
    TextView txtLogin;
    @BindView(R.id.layout_edit)
    LinearLayout layoutEdit;
    @BindView(R.id.radiobtn)
    RadioButton radiobtn;
    @BindView(R.id.txt_tips)
    TextView txtTips;

    @Override
    protected void initData() {
        String str1 = "已阅读并同意";
        String str2 = "《用户服务协议》";
        String str3 = "和";
        String str4 = "《隐私政策》";
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(str1);
        SpannableString spannableString = new SpannableString(str2);
        //设置str2的点击事件
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(LoginActivity.this, WebActivity.class);
                intent.putExtra("url", "https://www.baidu.com");
                startActivity(intent);
            }
        }, 0, str2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置str2的文字颜色
        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), 0, str2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(spannableString);
        builder.append(str3);
        SpannableString spannableString4 = new SpannableString(str4);
        //设置str2的点击事件
        spannableString4.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(LoginActivity.this, WebActivity.class);
                intent.putExtra("url", "https://www.baidu.com");
                startActivity(intent);
            }
        }, 0, str4.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置str2的文字颜色
        spannableString4.setSpan(new ForegroundColorSpan(Color.BLUE), 0, str4.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(spannableString4);
        txtTips.setText(builder);
        txtTips.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    protected LoginConstract.Presenter initPresenter() {
        return new LoginPersenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void loginReturn(UserInfoBean result) {
        if (result.getErr() == 200) {
            //把接收到的登录成功返回的值保存到sp
            SpUtils.getInstance().setValue("token", result.getData().getToken());
            SpUtils.getInstance().setValue("username", result.getData().getUsername());
            SpUtils.getInstance().setValue("avater", result.getData().getAvater());
            SpUtils.getInstance().setValue("phone", result.getData().getPhone());
            finish();
        } else {
            Toast.makeText(context, result.getErrmsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.txt_login)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_login:
                login();
                break;
        }
    }

    @Override
    public void showToast(String str) {

    }

    //登录
    private void login() {
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(context, "请输入正确的用户名或密码", Toast.LENGTH_SHORT).show();
            return;
        }
        //判断协议是否有同意
        if(!radiobtn.isChecked()){
            Toast.makeText(context,"未同意协议内容",Toast.LENGTH_SHORT).show();
            return;
        }

        //把输入的密码进行sha1加密
        password = SecretUtils.getSha1(password);
        //正常的业务逻辑下 password
        presenter.login(username, password);
    }
}
