package avinash.xcelcorp.checkinternet;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

public class DialogActivity extends android.app.DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Plese connect with Internet or Wifi to proceed")
                .setTitle("No Internet Connection");
        setCancelable(false);
        return builder.create();
    }
}
