package leandrocolevati.com.br.socketclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);

        String[] param = new String[]{"Bubble", "Int", "[6,5,4,3,2,1]"};

        View.OnClickListener cSocket = new ChamadaSocket(textView, param);
        button.setOnClickListener(cSocket);
    }
}
