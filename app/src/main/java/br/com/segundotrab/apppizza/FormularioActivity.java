package br.com.segundotrab.apppizza;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.segundotrab.appPizza.R;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etQuantidade;
    private EditText etPreco;
    private Spinner spCategoria;
    private Button btnSalvar;
    private String acao;
    private Produtos produtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNome = findViewById( R.id.etNome );
        etQuantidade = findViewById( R.id.etQuantidade);
        etPreco = findViewById( R.id.etPreco);
        spCategoria = findViewById( R.id.spCategoria);
        btnSalvar = findViewById( R.id.btnSalvar );

        acao = getIntent().getStringExtra("acao");
        if( acao.equals("editar")){
            carregarFormulario();
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

    }

    private void carregarFormulario(){
          int idPizza = getIntent().getIntExtra("idProduto", 0);
        if( idPizza != 0) {
            produtos = ProdutosDAO.getProdutosById(this, idPizza);

            etNome.setText( produtos.nome );
            etQuantidade.setText( produtos.quantidade );
           // spSabor.setSelection(spSabor.getSelectedItemPosition());




            String[] arrayCategoria = getResources().getStringArray(R.array.arrayCategoria);
            for(int i = 1; i < arrayCategoria.length ; i++){
                if(  arrayCategoria[i]  == produtos.categoria){
                    spCategoria.setSelection( i );
                }
            }
        }

    }

    private void salvar(){
       if( spCategoria.getSelectedItemPosition() == 0 || etNome.getText().toString().isEmpty() ) {

            Toast.makeText(this, "Todos campos devem ser preenchidos!", Toast.LENGTH_SHORT).show();

        }else{

            if (acao.equals("novo")) {
                produtos = new Produtos();
            }

           produtos.nome = etNome.getText().toString();
           produtos.quantidade = (Integer.valueOf(etQuantidade.getText().toString()));
           produtos.categoria = spCategoria.getSelectedItem().toString();
           produtos.setPreco( Double.valueOf( etPreco.getText().toString()));

              if( acao.equals("editar")){
                ProdutosDAO.editar(produtos, this);
               finish();
            }else {
                ProdutosDAO.inserir(produtos, this);
                etNome.setText("");
                etQuantidade.setText("");
                etPreco.setText("");
                spCategoria.setSelection(0);
            }
        }
    }



}
