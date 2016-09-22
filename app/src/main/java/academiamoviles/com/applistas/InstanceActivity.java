package academiamoviles.com.applistas;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class InstanceActivity extends AppCompatActivity {

    @InjectView(R.id.btn_adicionar)
    Button btn_adicionar;

    @InjectView(R.id.tv_numero)
    TextView tv_numero;

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instance);
        ButterKnife.inject(this);


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
            Log.d("TestOnSave", "" + i);
            outState.putInt("CONT", i);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        int cont = savedInstanceState.getInt("CONT");
        Log.d("TestOnRestore", ""+cont);
        tv_numero.setText(String.valueOf(cont));
        i = cont;
    }

    @OnClick(R.id.btn_adicionar)
    public void adicionar(){

        i = i+1;
        tv_numero.setText(""+i);

    }
}
