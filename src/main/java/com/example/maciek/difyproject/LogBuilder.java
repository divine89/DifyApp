package com.example.maciek.difyproject;

import android.util.Log;

//TODO Pomyśleć nad flagami do logów ERROR, DEBUG, WARNING, INFO
//TODO czy klasa moze byc abstrakcyjna z metodą staic. Chodzi o to aby nie tworzyc obiektu LogBuilder tylko wywoływac funkcje tej klasy bez tworzenia obiektu.
public class LogBuilder
{
    //TODO poprawić budowanie loga, numer lini i nazwa metody
    public static void createDebugLog(int lineNumber, Object object, String methodName, String description)
    {
        ClassName classNameObject = new ClassName();

        classNameObject.getClassName(object);

        String className = classNameObject.getClassNameString();

        Log.d(lineNumber + " " + className + "." + methodName + "()", description);
    }
}
