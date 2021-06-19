package br.com.segundotrab.apppizza;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {

    public static void inserir(Produtos produtos, Context context){
        ContentValues valores = new ContentValues();
       valores.put("nome", produtos.nome );
        valores.put("quantidade", produtos.quantidade );
        valores.put("categoria", produtos.categoria );
        valores.put("Preco", produtos.getPreco() );

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.insert("produtos", null, valores);
    }

    public static void editar(Produtos produtos, Context context){
        ContentValues valores = new ContentValues();
        valores.put("nome", produtos.nome );
        valores.put("quantidade", produtos.quantidade );
        valores.put("categoria", produtos.categoria );
        valores.put("Preco", produtos.getPreco() );


        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.update("produtos", valores, " id = " + produtos.id , null );
    }

    public static void excluir(int id, Context context){
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("produtos", " id = " + id , null);
    }

    public static List<Produtos> getProdutos(Context context){
        List<Produtos> lista = new ArrayList<>();
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, nome, quantidade, preco, categoria FROM produtos ORDER BY nome", null );
        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Produtos produtos = new Produtos();
                produtos.id = cursor.getInt( 0);
                produtos.nome = cursor.getString(1);
                produtos.quantidade = cursor.getInt(2);
                produtos.setPreco( cursor.getDouble(3) );
                produtos.categoria = cursor.getString(4);

                lista.add(produtos);
            }while( cursor.moveToNext() );
        }
        return lista;
    }

    public static Produtos getProdutosById(Context context, int id){
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, nome, quantidade, preco, categoria FROM produtos  WHERE id = " + id, null );
        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            Produtos produtos = new Produtos();
            produtos.id = cursor.getInt( 0);
            produtos.nome = cursor.getString(1);
            produtos.quantidade = cursor.getInt(2);
            produtos.setPreco( cursor.getDouble(3) );
            produtos.categoria = cursor.getString(4);

            return produtos;
        }else{
            return null;
        }
    }



}
