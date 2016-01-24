package com.example.android.cop_final;

/**
 * Created by Akram Khan on 24-01-2016.
 */
public class CheckEntryNumber {
    public static boolean checkEntryno(String entryno) {

        if(entryno.length()!=11){return false;}

        int year = Integer.parseInt(entryno.substring(0, 4));
        String dept = entryno.substring(4, 6);
        String spec = entryno.substring(6,7);

        if (year > 2014 || year < 2005) {return false;}
        if ((dept.matches("CS")&&(spec.matches("1")||spec.matches("5")))){return true;}
        if((dept.matches("CE")&&(spec.matches("1")))){return true;}
        if((dept.matches("CH")&&(spec.matches("1")||spec.matches("5")||spec.matches("3")))){return true;}
        if((dept.matches("BB")&&(spec.matches("1")||spec.matches("5")))){return true;}
        if((dept.matches("EE")&&(spec.matches("1")||spec.matches("3")||spec.matches("5")))){return true;}
        if((dept.matches("PH")&&(spec.matches("1")))){return true;}
        if((dept.matches("MT")&&(spec.matches("1")||spec.matches("5")||spec.matches("6")))){return true;}
        if((dept.matches("ME")&&(spec.matches("1")||spec.matches("2")))){return true;}
        return false;
    }
}
