package br.edu.ifbaiano.guanambi.aplicacaoshape.activity;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifbaiano.guanambi.aplicacaoshape.R;
import br.edu.ifbaiano.guanambi.aplicacaoshape.dao.UserDAO;
import br.edu.ifbaiano.guanambi.aplicacaoshape.model.User;

public class ActivityPrincipal extends AppCompatActivity {

    TextView txtEmail;
    Button btnSair;
    Button btnAtualizar;
    Button btnExcluir;
    Button btnHabilidads;
    TextView txtNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        txtEmail = findViewById(R.id.txtEmail);
        txtNome = findViewById(R.id.txtNome);
        btnSair = findViewById(R.id.btnSair);
        btnAtualizar = findViewById(R.id.btnAtualizar);
        btnExcluir = findViewById(R.id.btnExcluir);
        btnHabilidads = findViewById(R.id.btnHabilidades);

        SharedPreferences sp = getSharedPreferences("appLogin",
                Context.MODE_PRIVATE);
        String email = sp.getString("email","abc");

        User user = new User();
        user.setMail(email);
        UserDAO uDao = new UserDAO(getApplicationContext(), user);
        user = uDao.obterUserByEmail();

        txtEmail.setText(user.getMail());
        txtNome.setText(user.getName());

       // Intent it = getIntent();
       // String email = it.getStringExtra("email");

        //txtEmail.setText(email);

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("appLogin", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.remove("email");
                editor.apply();

                Intent redirecionar = new Intent(ActivityPrincipal.this, MainActivity.class);
                redirecionar.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); //Usei para limpar
                startActivity(redirecionar);                                                           //a pilha de atividades
                finish();
            }
        });

        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent redirecionar = new Intent(ActivityPrincipal.this, AtualizarActivity.class);
                startActivity(redirecionar);
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent redirecionar = new Intent(ActivityPrincipal.this, ExcluirActivity.class);
                startActivity(redirecionar);
            }
        });

        btnHabilidads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent redirecionar = new Intent(ActivityPrincipal.this, DinamicaActivitiy.class);
                startActivity(redirecionar);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.op1:
                Intent redirecionar = new Intent(this, ListarActivity.class);
                startActivity(redirecionar);
                break;
            case R.id.op2:
                redirecionar = new Intent(ActivityPrincipal.this, AtualizarActivity.class);
                startActivity(redirecionar);
                break;
            case R.id.op3:
                redirecionar = new Intent(ActivityPrincipal.this, ExcluirActivity.class);
                startActivity(redirecionar);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}