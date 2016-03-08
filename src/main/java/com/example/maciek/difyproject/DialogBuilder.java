package com.example.maciek.difyproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DialogBuilder
{
    private AlertDialog.Builder alertDialogBuilder;
    private LayoutInflater layoutInflater;
    private View dialogView;
    private int dialogStyle;
    private int dialogLayoutWithEditText;
    private Dialog dialog;
    private String countryString;
    private String cityString;

    public DialogBuilder()
    {
        int dialogLayout = R.style.AlertDialogCustom;
        setDialogStyle(dialogLayout);

        int dialogLayoutWithEditText = R.layout.dialog_edit_text;
        setDialogLayoutWithEditText(dialogLayoutWithEditText);
    }

    public void setCountryString(String countryString)
    {
        this.countryString = countryString;
    }

    public String getCountryString()
    {
        return countryString;
    }

    public void setCityString(String countryString)
    {
        this.cityString = cityString;
    }

    public String getCityString()
    {
        return cityString;
    }

    public void setDialog(Dialog dialog)
    {
        this.dialog = dialog;
    }

    public Dialog getMyDialog()
    {
        return dialog;
    }

    public void setDialogLayoutWithEditText(int dialogLayoutWithEditText)
    {
        this.dialogLayoutWithEditText = dialogLayoutWithEditText;
    }

    public int getDialogLayoutWithEditText()
    {
        return dialogLayoutWithEditText;
    }

    public void setDialogStyle(int dialogLayout)
    {
        this.dialogStyle = dialogLayout;
    }

    public int getDialogStyle()
    {
        return dialogStyle;
    }

    public AlertDialog.Builder getAlertDialogBuilder()
    {
        return alertDialogBuilder;
    }

    public void setAlertDialogBuilder(AlertDialog.Builder alertDialogBuilder)
    {
        this.alertDialogBuilder = alertDialogBuilder;
    }

    public LayoutInflater getLayoutInflater()
    {
        return layoutInflater;
    }

    public void setLayoutInflater(LayoutInflater layoutInflater)
    {
        this.layoutInflater = layoutInflater;
    }

    public View getDialogView()
    {
        return dialogView;
    }

    public void setDialogView(View dialogView)
    {
        this.dialogView = dialogView;
    }

    public void createAlertDialogBuilder(Context context)
    {
        int dialogLayout = getDialogStyle();
        AlertDialog.Builder alertDiaBuilder= new AlertDialog.Builder(context, dialogLayout);
        setAlertDialogBuilder(alertDiaBuilder);
    }

    public void createLayoutInflater(Activity currentActivity)
    {
        LayoutInflater layoutInflater= currentActivity.getLayoutInflater(); //TODO getActivity().getLayoutInflater();
        setLayoutInflater(layoutInflater);
    }

    public void createDialogView(LayoutInflater layoutInflater)
    {
        int dialogLayout = getDialogLayoutWithEditText();
        View dialogview = layoutInflater.inflate(dialogLayout, null);
    }

    public void configureDialog(AlertDialog.Builder alertDialogBuilder, View dialogView, String title)
    {
        alertDialogBuilder.setView(dialogView);
        alertDialogBuilder.setTitle(title);
    }

    public void configurePositiveButtonOfDialog(AlertDialog.Builder alertDialogBuilder, final View v)
    {
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                MainActivity mainActivity = new MainActivity();

                Dialog mainDialog = getMyDialog();

                EditText editText = (EditText) mainDialog.findViewById(R.id.editTextDialog);

                switch (v.getId())
                {
                    case R.id.countryButton:

                        String countryString;

                        Button countryButton = mainActivity.getcountryButton();

                        countryString = editText.getText().toString();

                        countryButton.setText(countryString);

                        break;

                    case R.id.cityButton:

                        String cityString;

                        Button cityButton = mainActivity.getCityButton();

                        cityString = editText.getText().toString();

                        cityButton.setText(cityString);

                        break;
                }
            }
        });
    }

    public void configureNegativeButtonOfDialog(AlertDialog.Builder alertDialogBuilder)
    {
        alertDialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.dismiss();
            }
        });
    }

    public void createDialogWithEditText(Context context, Activity currentActivity, View v)
    {
        createAlertDialogBuilder(context);

        AlertDialog.Builder alertDialogBuilder = getAlertDialogBuilder();

        createLayoutInflater(currentActivity);

        LayoutInflater layoutInflater = getLayoutInflater();

        createDialogView(layoutInflater);

        View dialogView = getDialogView();

        String title = "SELECT";

        configureDialog(alertDialogBuilder, dialogView, title);

        configurePositiveButtonOfDialog(alertDialogBuilder, v);

        configureNegativeButtonOfDialog(alertDialogBuilder);

        Dialog dialog;

        dialog = alertDialogBuilder.create();

        setDialog(dialog);
    }

    public void showMyDialog()
    {
        Dialog dialog = getMyDialog();
        dialog.show();
    }
}
