package es.iesoretania.ejemplosqlitelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListaActivity extends AppCompatActivity {
    ListView milistView;
    List<Articulo> milistaarticulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        milistView = (ListView) findViewById(R.id.lv_lista);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(
                this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getReadableDatabase();

        Cursor cursor = BaseDeDatos.rawQuery("select * from articulos", null);
        String codigo, producto, precio;
        milistaarticulos = new ArrayList<>();
        if (cursor.moveToFirst()){
            do{
                codigo = cursor.getString(0);
                producto = cursor.getString(1);
                precio = cursor.getString(2);
                milistaarticulos.add(new Articulo(codigo, producto, precio));
            } while (cursor.moveToNext());
        }

        MiAdaptadorArticulo miadaptador = new MiAdaptadorArticulo (
                this, R.layout.articulos_item,milistaarticulos);

        milistView.setAdapter(miadaptador);
    }
}
