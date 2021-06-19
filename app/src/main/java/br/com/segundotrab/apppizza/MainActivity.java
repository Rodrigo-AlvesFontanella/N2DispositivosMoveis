package br.com.segundotrab.apppizza;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.segundotrab.appPizza.R;

public class MainActivity extends AppCompatActivity {

    private ListView lvProdutos;
//    private ArrayAdapter adapter;
    private AdapterProduto adapter;
    private List<Produtos> listaProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("acao", "novo");
                startActivity( intent );
            }
        });


        lvProdutos = findViewById(R.id.lvProdutos);

        carregarProdutos();

        configurarListView();

    }

    private void configurarListView(){

        lvProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Produtos produtoSelecionado = listaProdutos.get(position);
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("acao", "editar");
                intent.putExtra("idProduto", produtoSelecionado.id );
                startActivity( intent );
            }
        });

        lvProdutos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                Produtos produtoSelecionado = listaProdutos.get(position);
                excluirProduto( produtoSelecionado );
                return true;
            }
        });

    }



    private void excluirProduto( Produtos produtos ){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setIcon(android.R.drawable.ic_input_delete);
        alerta.setTitle(R.string.txtAtencao);
        alerta.setMessage("Confirma a exclusão do pedido " + produtos.nome +"?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ProdutosDAO.excluir( produtos.id, MainActivity.this);
                carregarProdutos();
            }
        });
        alerta.show();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        carregarProdutos();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void carregarProdutos(){
        listaProdutos = ProdutosDAO.getProdutos(this);
//        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaPizzas);
        adapter = new AdapterProduto(this, listaProdutos);
        lvProdutos.setAdapter( adapter );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
