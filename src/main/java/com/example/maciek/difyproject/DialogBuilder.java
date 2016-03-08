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
        int dialogStyle = getDialogStyle();
        AlertDialog.Builder alertDiaBuilder= new AlertDialog.Builder(context, dialogStyle);
        setAlertDialogBuilder(alertDiaBuilder);
    }

    //public void createLayoutInflater(Activity currentActivity)
    public void createLayoutInflater(Context context)
    {
        //LayoutInflater: it takes as input an XML file and builds the View objects from it.
        //Coretc way: LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        //Old one: LayoutInflater layoutInflater= currentActivity.getLayoutInflater(); //TODO getActivity().getLayoutInflater();
        
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        setLayoutInflater(layoutInflater);
    }

    public void createDialogView(int dialogLayout);
    {
        //int dialogLayout = getDialogLayoutWithEditText();
        
        LayoutInflater layoutInflater = getLayoutInflater();
        
        View dialogview = layoutInflater.inflate(dialogLayout, null);
        
        setDialogView(dialogView);
    }

    public void configureDialog(View dialogView, String title)
    {
        alertDialog.Builder alertDialogBuilder = getAlertDialogBuilder();
        
        alertDialogBuilder.setView(dialogView);
        
        alertDialogBuilder.setTitle(title);
    }
    
    /*public void getClickedButton(View v)
    {
        //TODO czy funkcja będzie potrzebna
    }*/
    
    public void setButtonTitleAfterDialogDismiss(Button button)
    {
        String title;
        
        Dialog mainDialog = getMyDialog();

        EditText editText = (EditText) mainDialog.findViewById(R.id.editTextDialog);

        title = editText.getText().toString();

        button.setText(countryString);
    }

    public void configurePositiveButtonOfDialog(final View v)
    {
        String buttonTitle = "OK";
        
        AlertDialog.Builder alertDialogBuilder = getalertDialogBuilder();
        
        alertDialogBuilder.setPositiveButton(buttonTitle, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                MainActivity mainActivity = new MainActivity();

                switch (v.getId())
                {
                    case R.id.countryButton:
                        
                        Button countryButton = mainActivity.getCountryButton();
                        
                        setButtonTitleAfterDialogDismiss(countryButton);

                        break;

                    case R.id.cityButton:

                        Button cityButton = mainActivity.getCityButton();
                        
                        setButtonTitleAfterDialogDismiss(cityButton);

                        break;
                }
            }
        });
    }

    public void configureNegativeButtonOfDialog(final View v)
    {
        String buttonTitle = "CANCEL";
        
        AlertDialog.Builder alertDialogBuilder = getAlertDialogBuilder();
        
        alertDialogBuilder.setNegativeButton(buttonTitle, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                MainActivity mainActivity = new MainActivity();

                switch (v.getId())
                {
                    case R.id.countryButton:
                        
                        Button countryButton = mainActivity.getCountryButton();
                        
                        setButtonTitleAfterDialogDismiss(countryButton);

                        break;

                    case R.id.cityButton:

                        Button cityButton = mainActivity.getCityButton();
                        
                        setButtonTitleAfterDialogDismiss(cityButton);

                        break;
                }
                
                Dialog dialog = getMyDialog();
                
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
