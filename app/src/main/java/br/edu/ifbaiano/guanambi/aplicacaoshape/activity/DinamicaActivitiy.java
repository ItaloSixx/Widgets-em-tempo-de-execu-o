package br.edu.ifbaiano.guanambi.aplicacaoshape.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifbaiano.guanambi.aplicacaoshape.R;

public class DinamicaActivitiy extends AppCompatActivity {

    Button btnAdcHabilidades;
    Button btnRmvHabilidades;
    LinearLayout habilidadesContainer;
    int habilidade;
    List<EditText> editTextList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinamica);

        habilidadesContainer = findViewById(R.id.habilidadesContainer);
        btnAdcHabilidades = findViewById(R.id.btnAdcHabilidades );
        btnRmvHabilidades = findViewById(R.id.btnRmvHabilidades);
        editTextList = new ArrayList<>();

        btnAdcHabilidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addHabilidadeEditText();
            }
        });

        btnRmvHabilidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rmvUltimoHabilidadeEditText();
            }
        });

        }

        private void addHabilidadeEditText(){
            habilidade++;
            EditText habilidadeEditText = new EditText(DinamicaActivitiy.this);
            habilidadeEditText.setHint("Digite a sua habilidade " + habilidade);

            habilidadesContainer.addView(habilidadeEditText);

            //adiciona a referencia do editText na lista.
            editTextList.add(habilidadeEditText);
        }

        private void rmvUltimoHabilidadeEditText(){
            if (!editTextList.isEmpty()){
                EditText ultimoEditText = editTextList.get(editTextList.size() - 1); // -1 pq os indices começam em 0
                habilidadesContainer.removeView(ultimoEditText);
                editTextList.remove(ultimoEditText);
                habilidade--;
            }
        }
    }
