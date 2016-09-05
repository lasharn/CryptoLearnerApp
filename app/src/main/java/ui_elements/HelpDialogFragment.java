package ui_elements;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.cryptolearner.mobile.cryptolearner.R;


public class HelpDialogFragment extends DialogFragment {

    private String helpMessage = "CryptoLerner is a great application";

    public static HelpDialogFragment newInstance(String helpMessage) {
        HelpDialogFragment h = new HelpDialogFragment();
        h.helpMessage = helpMessage;
        return h;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_help_dialog, null);
        builder.setView(view);

        view.findViewById(R.id.closeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeClicked();
            }
        });

        ((TextView)view.findViewById(R.id.helpText)).setText(helpMessage);

        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        closeClicked();
    }

    private void closeClicked() {
        this.dismiss();
    }


}
