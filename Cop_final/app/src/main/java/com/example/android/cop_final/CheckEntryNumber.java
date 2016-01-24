package com.example.android.cop_final;

// checks whether the given entry number is valid or not

public class CheckEntryNumber {
    public static boolean checkEntryno(String entryno) {

        if(entryno.length()!=11){return false;} // checks the length of entry number

        int year = Integer.parseInt(entryno.substring(0, 4));
        String dept = entryno.substring(4, 6);
        String spec = entryno.substring(6,7);

        if (year > 2014 || year < 2005) {return false;} // checks the year
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
