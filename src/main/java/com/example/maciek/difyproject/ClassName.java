package com.example.maciek.difyproject;

public class ClassName
{
    private Class className;
    private String classNameString;

    public Class getClassNameField()
    {
        return className;
    }

    public String getClassNameString()
    {
        return classNameString;
    }

    public void setClassName(Class className)
    {
        this.className = className;
    }

    public void setClassNameString(String classNameString)
    {
        this.classNameString = classNameString;
    }

    public void getClassName(Object object)
    {
        Class className = object.getClass();

        setClassName(className);

        String string = getClassNameField().getSimpleName();

        setClassNameString(string);
    }
}
