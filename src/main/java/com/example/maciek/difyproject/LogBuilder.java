package com.example.maciek.difyproject;

import android.util.Log;

public class LogBuilder
{
    public static void createDebugLog(int lineNumber, Object object, String methodName, String description)
    {
        ClassName classNameObject = new ClassName();

        classNameObject.getClassName(object);

        String className = classNameObject.getClassNameString();

        Log.d(lineNumber + " " + className + "." + methodName + "()", description);
    }
}
