package com.example.maciek.difyproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
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
        Log.d("DEBUS", "createAlertDialogBuilder");
        int dialogStyle = getDialogStyle();
        AlertDialog.Builder alertDiaBuilder= new AlertDialog.Builder(context, dialogStyle);
        setAlertDialogBuilder(alertDiaBuilder);
    }

    //public void createLayoutInflater(Activity currentActivity)
    public void createLayoutInflater(Context context)
    {
        Log.d("DEBUS", "createLayoutInflater");
        //LayoutInflater: it takes as input an XML file and builds the View objects from it.
        //Coretc way: LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        //Old one: LayoutInflater layoutInflater= currentActivity.getLayoutInflater(); //TODO getActivity().getLayoutInflater();
        
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        setLayoutInflater(layoutInflater);
    }

    public void createDialogView(int dialogLayout)
    {
        //int dialogLayout = getDialogLayoutWithEditText();
        Log.d("DEBUS", "createDialogView");

        //dialogLayout = getDialogLayoutWithEditText();

        LayoutInflater layoutInflater = getLayoutInflater();
        
        View dialogview = layoutInflater.inflate(dialogLayout, null);
        
        setDialogView(dialogview);
    }

    public void configureDialog(View dialogView, String title)
    {
        Log.d("DEBUS", "configureDialog");

        AlertDialog.Builder alertDialogBuilder = getAlertDialogBuilder();
        
        alertDialogBuilder.setView(dialogView);
        
        alertDialogBuilder.setTitle(title);
    }
    
    public void setButtonTitleAfterPositiveDialogDismiss(View v, Button button)
    {
        Log.d("DEBUS", "setButtonTitleAfterPositiveDialogDismiss");

        String title;
        
        Dialog mainDialog = getMyDialog();

        EditText editText = (EditText) mainDialog.findViewById(R.id.editTextDialog);

        title = editText.getText().toString();

        button.setText(title);
    }

    public String getTextFromEditText()
    {
        String title;

        Dialog mainDialog = getMyDialog();

        EditText editText = (EditText) mainDialog.findViewById(R.id.editTextDialog);

        title = editText.getText().toString();

        Log.d("DEBUG", "DialogBuilder title:"+title);

        return title;
    }
    
    public void setButtonTitleAfterNegativeDialogDismiss(Button button)
    {
        Log.d("DEBUS", "setButtonTitleAfterNegativeDialogDismiss");

        String title = "SELECT";

        button.setText(title);
    }
    
    public void makeNullCountryString()
    {
        Log.d("DEBUS", "makeNullCountryString");

        String country = getCountryString();
        
        country = null;
        
        setCountryString(country);
    }
    
    public void makeNullCityString()
    {
        Log.d("DEBUS", "makeNullCityString");

        String city = getCityString();

        city = null;
        
        setCityString(city);
    }

    public void configurePositiveButtonOfDialog(final View v, final Button countryButton, final Button cityButton)
    {
        Log.d("DEBUS", "configurePositiveButtonOfDialog");

        String buttonTitle = "OK";

        AlertDialog.Builder alertDialogBuilder = getAlertDialogBuilder();

        alertDialogBuilder.setPositiveButton(buttonTitle, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {


                switch (v.getId())
                {
                    case R.id.countryButton:

                        setButtonTitleAfterPositiveDialogDismiss(v, countryButton);

                        break;

                    case R.id.cityButton:

                        setButtonTitleAfterPositiveDialogDismiss(v, cityButton);

                        break;
                }
            }
        });
    }

    public void configureNegativeButtonOfDialog(final View v, final Button countryButton, final Button cityButton)
    {
        Log.d("DEBUS", "configureNegativeButtonOfDialog");

        String buttonTitle = "CANCEL";
        
        AlertDialog.Builder alertDialogBuilder = getAlertDialogBuilder();
        
        alertDialogBuilder.setNegativeButton(buttonTitle, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                switch (v.getId())
                {
                    case R.id.countryButton:

                        setButtonTitleAfterNegativeDialogDismiss(countryButton);
                        
                        makeNullCountryString();

                        break;

                    case R.id.cityButton:

                        setButtonTitleAfterNegativeDialogDismiss(cityButton);
                        
                        makeNullCityString();

                        break;
                }

            }
        });
    }

    public Dialog createDialogWithEditText(Context context, Activity currentActivity, View v, Button countryButton, Button cityButton)
    {

        createAlertDialogBuilder(context);

        AlertDialog.Builder alertDialogBuilder = getAlertDialogBuilder();

        createLayoutInflater(context);

        int dialogLayout = R.layout.dialog_edit_text;

        createDialogView(dialogLayout);

        View dialogView = getDialogView();

        String title = "SELECT";

        configureDialog(dialogView, title);

        configurePositiveButtonOfDialog(v, countryButton, cityButton);

        configureNegativeButtonOfDialog(v, countryButton, cityButton);

        return alertDialogBuilder.create();


    }

    public void showMyDialog()
    {
        Log.d("DEBUS", "showMyDialog");

        Dialog dialog = getMyDialog();

        dialog.show();

    }
}
