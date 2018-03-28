package com.example.vignesh.gpacalc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by vignesh on 14/3/18.
 */

public class dialogbox extends AppCompatDialogFragment {
    private EditText name;
    private EditText regno;
    private dialoglistener listener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_box,null);
        builder.setView(view)
                .setTitle("User Details")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
           //             String name1 = name.getText().toString();
                        String regno1 = regno.getText().toString();
                        listener.applytext(regno1);
                    }
                });
      //  name = view.findViewById(R.id.name);
        regno = view.findViewById(R.id.regno);
        return builder.create();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (dialoglistener) context;
        } catch (ClassCastException e)
        {
           throw new ClassCastException(context.toString()+ "must implement dialog listener");
        }


    }
public interface dialoglistener{
        void applytext(String regno);

}
}
