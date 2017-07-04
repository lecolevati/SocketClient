package leandrocolevati.com.br.socketclient;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by 7x64 on 04/07/2017.
 */

public class ChamadaSocket implements View.OnClickListener {

    private TextView textView;
    private String[] param;

    public ChamadaSocket(TextView textView, String[] param){
        this.textView = textView;
        this.param = param;
    }

    @Override
    public void onClick(View v) {
        callAsyncSocket();
    }

    public void callAsyncSocket(){
        Client c = new Client("192.168.0.16", 12345, textView, param);
        c.execute();
    }

}
