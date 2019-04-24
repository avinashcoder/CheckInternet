package avinash.xcelcorp.checkinternet;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements ConnectivityReceiver.ConnectivityReceiverListener {

    private Button btnCheck;
    DialogActivity dialogActivity;
    boolean checkDialog=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCheck = (Button) findViewById(R.id.btn_check);


        dialogActivity = new DialogActivity();

        checkConnection();

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkConnection();
            }
        });
    }

    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showMessage(isConnected);
    }

    private void showMessage(boolean isConnected) {
        String message;
        if (isConnected) {
            message = "Nice, Connected to Internet";
            if(checkDialog) {
                dialogActivity.dismiss();
            }

        } else {
            message = "Sorry!! Not connected to internet";
            showDialog();
        }

        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        MyApplication.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showMessage(isConnected);
    }

    private boolean showDialog() {
        FragmentManager manager = getFragmentManager();
        dialogActivity.show(manager, "DialogActivity");
        checkDialog=true;
        return true;
    }
}
