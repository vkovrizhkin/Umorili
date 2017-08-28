package com.kovrizhkin.rssretrofit2.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.kovrizhkin.rssretrofit2.R;

/**
 * Created by Kovrizhkin V.A. on 28.08.2017.
 */

public class FeedDialogFragment extends DialogFragment {

    private boolean type;

    private static final String TYPE = "type";

    public static FeedDialogFragment newInstance(boolean type) {
        FeedDialogFragment dialogFragment = new FeedDialogFragment();

        Bundle args = new Bundle();
        args.putBoolean(TYPE, type);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        type = getArguments().getBoolean(TYPE);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (type) {
            builder.setItems(R.array.actions_1, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // The 'which' argument contains the index position
                    // of the selected item
                }
            });

        } else {
            builder.setItems(R.array.actions_2, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // The 'which' argument contains the index position
                    // of the selected item
                }
            });

        }

        return builder.create();
    }
}
