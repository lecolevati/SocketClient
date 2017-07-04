package leandrocolevati.com.br.socketclient;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by 7x64 on 04/07/2017.
 */

public class Client extends AsyncTask<Void, Void, String> {
    private String dstAddress;
    private int dstPort;
    private String response = "";
    private TextView textResponse;
    private String[] param;

    Client(String addr, int port, TextView textResponse, String[] param) {
        dstAddress = addr;
        dstPort = port;
        this.textResponse = textResponse;
        this.param = param;
    }

    @Override
    protected String doInBackground(Void... arg0) {

        Socket socket = null;

        try {
            socket = new Socket(dstAddress, dstPort);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(
                    1024);
            byte[] buffer = new byte[1024];

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            out.writeObject(param);

            String mensagem = (String) objectInputStream.readObject();
            Log.i("ordenado", mensagem);

            // outputStream.close();
            objectInputStream.close();
            socket.close();

//            int bytesRead;
//            InputStream inputStream = socket.getInputStream();

			/*
             * notice: inputStream.read() will block if no data return
			 */
//            while ((bytesRead = inputStream.read(buffer)) != -1) {
//                byteArrayOutputStream.write(buffer, 0, bytesRead);
//                response += byteArrayOutputStream.toString("UTF-8");
//            }

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            Log.e("Exception ", e.getMessage());
            response = "UnknownHostException: " + e.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Log.e("Exception ", e.getMessage());
            response = "IOException: " + e.toString();
        } catch (ClassNotFoundException e) {
            Log.e("Exception ", e.getMessage());
            response = "ClassNotFoundException: " + e.toString();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    Log.e("Exception ", e.getMessage());
                }
            }
        }
        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        textResponse.setText(response);
        super.onPostExecute(result);
    }
}
