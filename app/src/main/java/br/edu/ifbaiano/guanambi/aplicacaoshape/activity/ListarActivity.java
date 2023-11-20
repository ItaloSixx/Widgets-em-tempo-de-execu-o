package br.edu.ifbaiano.guanambi.aplicacaoshape.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import br.edu.ifbaiano.guanambi.aplicacaoshape.R;
import br.edu.ifbaiano.guanambi.aplicacaoshape.dao.UserDAO;
import br.edu.ifbaiano.guanambi.aplicacaoshape.model.User;

public class ListarActivity extends AppCompatActivity {
    ListView lv;
    UserDAO uDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        lv = findViewById(R.id.lv);

        uDao = new UserDAO(getApplicationContext(), new User());

        Cursor c = uDao.listarUsers();

        SimpleCursorAdapter adapter =
                        new SimpleCursorAdapter(getApplicationContext(),
                        R.layout.layout_users, uDao.listarUsers(),
                        new String[]{"_id", "nome","senha"},
                        new int[]{R.id.textEmail, R.id.textNome, R.id.textSenha}, 0);
        lv.setAdapter(adapter);
    }
}