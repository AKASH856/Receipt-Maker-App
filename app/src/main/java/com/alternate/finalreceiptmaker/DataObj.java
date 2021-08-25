package com.alternate.finalreceiptmaker;

public class DataObj {
        String mEmail;
         long InvoiceNo;
         long date;
         String Name;
         String FullName;
         double Amount;
         double PhoneNo;
         String MandalName;
         double BldNo;
         double RoomNo;

    public DataObj() {
    }

    public String getmEmail() {
        return mEmail;
    }
    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getFullName() {
        return FullName;
    }
    public void setFullName(String FullName) {
        this.FullName = FullName;
    }


    public long getInvoiceNo() {
        return InvoiceNo;
    }

    public void setInvoiceNo(long invoiceNo) {
        InvoiceNo = invoiceNo;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public  String getName() {
        return Name;
    }

    public  void setName(String name) {
        Name = name;
    }

    public  double getAmount() {
        return Amount;
    }

    public  void setAmount(double amount) {
        Amount = amount;
    }

    public  double getPhoneNo() {
        return PhoneNo;
    }

    public  void setPhoneNo(double phoneNo) {
        PhoneNo = phoneNo;
    }

    public  String getMandalName() {
        return MandalName;
    }

    public  void setMandalName(String mandalName) {
        MandalName = mandalName;
    }

    public  double getBldNo() {
        return BldNo;
    }

    public  void setBldNo(double bldNo) {
        BldNo = bldNo;
    }

    public  double getRoomNo() {
        return RoomNo;
    }

    public  void setRoomNo(double roomNo) {
        RoomNo = roomNo;
    }
}
