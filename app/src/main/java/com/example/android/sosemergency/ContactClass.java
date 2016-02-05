package com.example.android.sosemergency;

/**
 * Created by User on 08-07-2015.
 */
public class ContactClass {
    String name;
    String phoneNo;
    public ContactClass()
    {}
    public ContactClass(String ContName,String ContPhone)
    {
        name=ContName;
        phoneNo=ContPhone;
    }
    public   String getName()
    {
        return this.name;
    }
    public String getPhoneNo()
    {
        return phoneNo;
    }
    public void setName(String ContName)
    {
        name=ContName;
    }
    public void setPhoneNo(String ContPhone)
    {
        phoneNo=ContPhone;
    }
}
