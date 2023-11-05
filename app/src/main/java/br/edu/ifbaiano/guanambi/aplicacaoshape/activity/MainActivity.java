package br.edu.ifbaiano.guanambi.aplicacaoshape.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import br.edu.ifbaiano.guanambi.aplicacaoshape.R;
import br.edu.ifbaiano.guanambi.aplicacaoshape.dao.UserDAO;
import br.edu.ifbaiano.guanambi.aplicacaoshape.model.User;

    public class MainActivity extends AppCompatActivity {

        EditText edtEmail, edtSenha;
        Button btnEntrar;
        UserDAO uDao;

        TextView textViewCada;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            edtEmail = findViewById(R.id.edtEmail);
            edtSenha = findViewById(R.id.edtSenha);
            btnEntrar = findViewById(R.id.btnCad);
            textViewCada = findViewById(R.id.tvCadastrar);

            //limpar o preferences quando o app for instalado
            SharedPreferences sp = getSharedPreferences("appLogin", Context.MODE_PRIVATE);
            sp.edit().clear().apply();

            btnEntrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sp = getSharedPreferences("appLogin", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("email", edtEmail.getText().toString());
                    editor.apply();

                    uDao = new UserDAO(getApplicationContext(), new User(edtEmail.getText().toString(), edtSenha.getText().toString()));

                    if (uDao.verificarEmailESenha()){
                        Intent it = new Intent(MainActivity.this, ActivityPrincipal.class);
                        startActivity(it);
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "Dados Incorretos", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            if (isLogado()){
                Intent redirecionar = new Intent(MainActivity.this, ActivityPrincipal.class);
                startActivity(redirecionar);
                finish();
            }else{
                Intent redirecionar = new Intent(MainActivity.this, MainActivity.class);
            }

            textViewCada.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(MainActivity.this, Cadastro.class);
                    startActivity(it);
                }
            });
        }

        private boolean isLogado(){
            SharedPreferences sp = getSharedPreferences("appLogin", Context.MODE_PRIVATE);
            return sp.contains("email");
        }
    }