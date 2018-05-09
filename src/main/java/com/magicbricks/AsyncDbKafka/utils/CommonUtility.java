package com.magicbricks.AsyncDbKafka.utils;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Divya.Gupta on 02-01-2017.
 */

public class CommonUtility {

    private static final String DEFAULT_ZERO = "0";
    private static final String COMMA = ",";

// --Commented out by Inspection START (08-11-2016 12:28 PM):
//    public static boolean chkNull(Object value[]) {
//        if (value == null || value.length == 0) {
//            return true;
//        }
//        boolean status = false;
//        for (Object element : value) {
//            if (chkNull(element)) {
//                status = true;
//                break;
//            }
//        }
//        return status;
//    }
// --Commented out by Inspection STOP (08-11-2016 12:28 PM)

    public static boolean chkNull(Object value) {
        String strValue = null;

        if (value instanceof Integer) {
            strValue = value.toString();
        } else if (value instanceof Long) {
            strValue = value.toString();
        } else if (value instanceof String) {
            strValue = value.toString();
        }

        return (strValue == null) || (("".equals(strValue.trim()) || "null".equals(strValue.trim()) || "-1".equals(strValue.trim()) || strValue.trim().length() == 0));


    }

    public static boolean isEmailCorrect(String email) {
        if (CommonUtility.chkNull(email))
            return false;
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher m = pattern.matcher(email);
        boolean b = m.find();
        return !(!b || email.length() < 5);
    }

    public static boolean isMobNumCorrect(String mobileNum) {
        if (chkNull(mobileNum) || mobileNum.length() != 10)
            return false;

        char[] str = mobileNum.toCharArray();
        char ch = str[0];

        if (Character.getNumericValue(ch) < 7)
            return false;

        int count = 1;
        for (int i = 1; i < str.length; i++) {
            if (str[i] == ch)
                count++;
        }

        return count != 10;
    }

    public static boolean isNRINum(short code) {
        return !(code == 91 || code == 0);
    }

    public static String getFirstLetterCaps(String word) {
        if (!CommonUtility.chkNull(word)) {
            return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
        }
        return "";
    }

    public static String getUserTypeCode(String userTypeName) {
        String userType = "";
//        switch (userTypeName) {
//            case "Owners":
//                userType = "O";
//                break;
//            case "Agents":
//                userType = "A";
//                break;
//            case "Buyers/Tenents":
//                userType = "B";
//                break;
//        }

        if ("Owners".equalsIgnoreCase(userTypeName))
            userType = "O";
        else if ("Agents".equalsIgnoreCase(userTypeName))
            userType = "A";
        else if ("Buyers/Tenents".equalsIgnoreCase(userTypeName))
            userType = "B";

        return userType;
    }

    public static String getEmailSubjectKey(String listUserType) {
        String key = "";

        if ("S:O".equalsIgnoreCase(listUserType))
            key = "email_subject_SO";
        else if ("R:O".equalsIgnoreCase(listUserType))
            key = "email_subject_RO";
        else if ("S:A".equalsIgnoreCase(listUserType))
            key = "email_subject_SA";
        else if ("S:B".equalsIgnoreCase(listUserType))
            key = "email_subject_SB";
        else if ("R:B".equalsIgnoreCase(listUserType))
            key = "email_subject_RB";
        else if ("R:A".equalsIgnoreCase(listUserType))
            key = "email_subject_RA";

        return key;
    }

    public static String getSMSTemplateKey(String listUserType, int format) {
        String key = "";
//        switch (listUserType) {
//            case "S:O":
//            case "R:O":
//            case "S:A":
//                key = "sms_owner_sell_msg_";
//                break;
//            case "S:B":
//            case "R:B":
//            case "R:A":
//                key = "sms_owner_buy_msg_";
//                break;
//        }

        if ("S:O".equalsIgnoreCase(listUserType))
            key = "sms_SO_msg_";
        else if ("R:O".equalsIgnoreCase(listUserType))
            key = "sms_RO_msg_";
        else if ("S:A".equalsIgnoreCase(listUserType))
            key = "sms_SA_msg_";
        else if ("S:B".equalsIgnoreCase(listUserType))
            key = "sms_SB_msg_";
        else if ("R:B".equalsIgnoreCase(listUserType))
            key = "sms_RB_msg_";
        else if ("R:A".equalsIgnoreCase(listUserType))
            key = "sms_RA_msg_";

        return key + format;
    }

    public static String getEmailVMFileName(String listUserType, boolean isPropPriceNotPrsnt) {
        String fileName = "";
//        switch (listUserType) {
//            case "S:O":
//            case "R:O":
//                fileName = isPropPricePrsnt ? "email_SO" : "email_SB_SO_common";
//                break;
//            case "S:A":
//            case "R:A":
//                fileName = isPropPricePrsnt ? "email_SA" : "email_SA_common";
//                break;
//            case "S:B":
//            case "R:B":
//                fileName = isPropPricePrsnt ? "email_SB" : "email_SB_SO_common";
//                break;
//        }

        if ("S:O".equalsIgnoreCase(listUserType))
            fileName = isPropPriceNotPrsnt ? "email_SB_SO_common" : "email_SO";
        else if ("S:A".equalsIgnoreCase(listUserType))
            fileName = isPropPriceNotPrsnt ? "email_SA_common" : "email_SA";
        else if ("S:B".equalsIgnoreCase(listUserType))
            fileName = isPropPriceNotPrsnt ? "email_SB_SO_common" : "email_SB";
        else if ("R:O".equalsIgnoreCase(listUserType))
            fileName = /*isPropPriceNotPrsnt ?*/  "email_SB_SO_common"/*:"email_SO"*/;
        else if ("R:B".equalsIgnoreCase(listUserType))
            fileName = /*isPropPriceNotPrsnt ?*/ "email_SB_SO_common"/*:"email_SB" */;
        else if ("R:A".equalsIgnoreCase(listUserType))
            fileName = /*isPropPriceNotPrsnt ?*/  "email_SA_common"/*:"email_SA" */;

        return fileName;
    }

    public static boolean retargetToday(Date inBetween) {
        LocalDate now = new DateTime().toLocalDate();
        boolean ans;
        LocalDate beforeDate, afterDate;
        LocalDate event = new DateTime(inBetween).toLocalDate();
        switch (now.getDayOfWeek()) {
            case 6:
                beforeDate = now.minusDays(5);
                afterDate = now.minusDays(3);

                ans = (event.isBefore(afterDate) && event.isAfter(beforeDate)) || event.isEqual(afterDate) || event.isEqual(beforeDate);

                break;
            case 7:
                beforeDate = now.minusDays(3);
                afterDate = now.minusDays(1);

                ans = (event.isBefore(afterDate) && event.isAfter(beforeDate)) || event.isEqual(afterDate) || event.isEqual(beforeDate);
                break;
            default:
                ans = false;
                break;
        }
        return ans;
    }

    public static String getFirstWord(String text) {
        if (text.indexOf(' ') > -1) { // Check if there is more than one word.
            return text.substring(0, text.indexOf(' ')).trim(); // Extract first word.
        } else {
            return text; // Text is the first word itself.
        }
    }

    public static boolean targetToday(int smshour) {
        LocalDate now = new DateTime().toLocalDate();
        boolean ans = false;
        switch (smshour) {
            case 1:     //all from Mon-Sun
                ans = true;
                break;
            case 2:     // Mon, Tues, Sat and Sun
                if (now.getDayOfWeek() == 1 || now.getDayOfWeek() == 2 || now.getDayOfWeek() == 6 || now.getDayOfWeek() == 7)
                    ans = true;
                break;
            case 3:     //Wed , Thurs
                if (now.getDayOfWeek() == 3 || now.getDayOfWeek() == 4)
                    ans = true;
                break;
            case 4:     // Fri
                if (now.getDayOfWeek() == 5)
                    ans = true;
                break;
            case 5:     // Sat-Sun
                if (now.getDayOfWeek() == 6 || now.getDayOfWeek() == 7)
                    ans = true;
                break;
            case 6:     // Mon-Tues
                if (now.getDayOfWeek() == 1 || now.getDayOfWeek() == 2)
                    ans = true;
                break;
            default:
                break;
        }

        return ans;
    }

    public static String formatCurrencyInWord(long number) {
        try {
            if (number == 0) {
                return DEFAULT_ZERO;
            } else if (number < 1000) {
                return String.valueOf(number);
            } else if (number >= 10000000) {
                // In Crore(s)
                long croresAmt = number / 10000000;
                int lacsAmt = (int) (number % 10000000);
                StringBuilder buffer = new StringBuilder(String.valueOf(croresAmt));
                buffer.append(".");
                String lacsString = String.valueOf(lacsAmt);
                if (!chkNull(lacsString)) {
                    if (lacsString.length() == 6) {
                        buffer.append(DEFAULT_ZERO).append(lacsString.substring(0, 1));
                    } else if (lacsString.length() == 7) {
                        buffer.append(lacsString.substring(0, 2));
                    } else {
                        buffer.append(DEFAULT_ZERO);
                    }
                } else {
                    buffer.append(lacsString);
                }
                /*if(buffer.toString().equals("999999.0")) {
					buffer.replace(0, 11, "5+");
				}*/
                //buffer.append(" Cr");
				/*if(buffer.toString().equals("100.0 Cr")) {
					return "10+ Lacs";
				}*/
                //float val=Math.round(Float.parseFloat(buffer.toString())*10)/10f;
                return buffer.toString() + " Cr";
            } else if (number >= 100000 && number < 10000000) {
                // In Lac(s)
                long lacsAmt = number / 100000;
                int thousandAmt = (int) (number % 100000);
                StringBuilder buffer = new StringBuilder(String.valueOf(lacsAmt));
                buffer.append(".");
                String thousandString = String.valueOf(thousandAmt);
                if (!chkNull(thousandString)) {
                    if (thousandString.length() == 4) {
                        buffer.append(DEFAULT_ZERO).append(thousandString.substring(0, 1));
                    } else if (thousandString.length() == 5) {
                        buffer.append(thousandString.substring(0, 2));
                    } else {
                        buffer.append(DEFAULT_ZERO);
                    }
                } else {
                    buffer.append(thousandString);
                }
                float val = Math.round(Float.parseFloat(buffer.toString()) * 10) / 10f;
                if (Float.compare(val, 100.0f) == 0)
                    return "1.0 Cr"; //100.0 Lac
                //buffer.append(" Lacs");
                return val + " Lac";
            } else {
                String numberString = String.valueOf(number);
                StringBuilder buffer = new StringBuilder(numberString);
                int length = buffer.length();
                length = length - 3;
                buffer.insert(length, COMMA);
                while (length > 2) {
                    length = length - 2;
                    buffer.insert(length, COMMA);
                }

                return buffer.toString();
            }
        } catch (Exception e) {
            //logger.error("Exception in formatCurrencyInWord", e);
        }
        return number + "";
    }

    public static long getTimeInMillis(DateTime start, DateTime stop) {
        return stop.getMillis() - start.getMillis();
    }

    public static Date getDateFromStrng(String sDate1) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

}
