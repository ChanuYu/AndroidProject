package hwashin.summerproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void Loginbtn(View view){
        // 로그인 정보 일치할 시 로그인 되게 설정하기

    }
    public void Joinbtn(View view){
        // 회원가입 프래그먼트로 이동하기
        Intent intent = new Intent(this, JoinActivity.class);
        startActivity(intent);
    }
}