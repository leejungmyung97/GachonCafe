package gachon.mpclass.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class sideMenu extends AppCompatActivity implements View.OnClickListener{
    TextView name;
    TextView point;
    ImageButton cancel;
    Button charge;
    Button recent;
    Button revoke;
    private FirebaseUser user;
    private String username = null;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side_menu);
        name = (TextView)findViewById(R.id.userName);
        cancel = (ImageButton)findViewById(R.id.btn_cancel);
        String email = null;
        charge = (Button)findViewById(R.id.btn_pointCharge);
        recent = (Button)findViewById(R.id.btn_recent);
        revoke = (Button)findViewById(R.id.btn_revoke);
        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            // Name, email address, and profile photo Url
            username = user.getDisplayName();
            email = user.getEmail();
        }
        name.setText(username + " " + email);

        charge.setOnClickListener(this);
        cancel.setOnClickListener(this);
        recent.setOnClickListener(this);
        revoke.setOnClickListener(this);


    }
    private void revokeAccess() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(sideMenu.this, "계정이 삭제 되었습니다.", Toast.LENGTH_LONG).show();
                        finishAffinity();
                    }
                });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_pointCharge :
                Intent intent = new Intent(this, PriorityActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_cancel :
                onBackPressed();
                break;
            case R.id.btn_revoke :
                revokeAccess();
                break;
            case R.id.btn_recent :
                Intent intent2 = new Intent(this,RecentActivity.class);
                startActivity(intent2);
                finish();
                break;
        }
    }
}