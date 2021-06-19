package br.com.segundotrab.apppizza;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.com.segundotrab.appPizza.R;

public class AdapterProduto extends BaseAdapter {

    private List<Produtos> produtoList;
    private Context context;
    private LayoutInflater inflater;

    public AdapterProduto(Context context, List<Produtos> listaProdutos){
        this.produtoList = listaProdutos;
        this.context = context;
        this.inflater = LayoutInflater.from( context );
    }

    @Override
    public int getCount() {
        return produtoList.size();
    }

    @Override
    public Object getItem(int i) {
        return produtoList.get( i );
    }

    @Override
    public long getItemId(int i) {
        return produtoList.get(i).id;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ItemSuporte item;

        if( convertView == null){
            convertView = inflater.inflate(R.layout.layout_listagem, null);

            item = new ItemSuporte();
            item.tvNome = convertView.findViewById(R.id.tvListaNome);
            item.tvQuantidade = convertView.findViewById(R.id.tvListaQuantidade);
            item.tvCategoria = convertView.findViewById(R.id.tvListaCategoria);
            item.tvPreco = convertView.findViewById(R.id.tvListaPreco);
            item.tvPrecoTotal= convertView.findViewById(R.id.tvListaTotal);
            item.layout = convertView.findViewById(R.id.llFundoLista);
            convertView.setTag( item );
        }else {
            item = (ItemSuporte) convertView.getTag();
        }

        Produtos produtos = produtoList.get(i);
        item.tvNome.setText(  produtos.nome );
        item.tvQuantidade.setText(  String.valueOf(produtos.quantidade) );
        item.tvCategoria.setText( produtos.categoria );
        item.tvPreco.setText(  String.valueOf( produtos.getPreco() ) );
        item.tvPrecoTotal.setText(  String.valueOf( produtos.getPrecoTotal() ) );


        if( i % 2 == 0 ){
            item.layout.setBackgroundColor(Color.rgb(230, 230, 230));
        }else {
            item.layout.setBackgroundColor( Color.WHITE );
        }
        return convertView;
    }

    private class ItemSuporte{
        TextView tvNome, tvQuantidade, tvCategoria, tvPreco, tvPrecoTotal;
        LinearLayout layout;
    }


}
