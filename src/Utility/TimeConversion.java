/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * A Class used to convert different Time classes and localize times to specific TimeZones.
 * @author KC Green
 */
public class TimeConversion {
    /**
     * Convert a Timestamp from the mySQL database to ZonedDateTime based on the system's default ZoneID;
     * @param t A Timestamp from the mySQL database.
     * @return A ZonedDateTime object, created with the system's default zone.
     */
    public static ZonedDateTime getZonedFromTimestamp(Timestamp t){
        return t.toInstant().atZone(ZoneId.systemDefault());
    }
    /**
     * Convert a ZonedDateTime object from one Zone to the system's default ZoneID.
     * @param zdt A ZonedDateTime object.
     * @return A ZonedDateTime object based on the system's default ZoneID.
     */
    public static ZonedDateTime getLocalDateTime(ZonedDateTime zdt){
        return zdt.withZoneSameInstant(ZoneId.systemDefault());
    }
    /**
     * Converts a ZonedDateTime object to a ZonedDateTime object with the UTC ZoneID.
     * @param zdt A ZonedDateTime object.
     * @return A ZonedDateTime object with ZoneID of UTC.
     */
    public static ZonedDateTime getUTC(ZonedDateTime zdt){
        return zdt.withZoneSameInstant(ZoneId.of("UTC"));
    }
    /**
     * Converts a ZonedDateTime object to a Timestamp based on UTC Zone.
     * @param zdt A ZonedDateTime object.
     * @return A Timestamp based on UTC.
     */
    public static Timestamp getUTCTimestamp(ZonedDateTime zdt){
        ZonedDateTime zdtUTC = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        return Timestamp.from(zdtUTC.toInstant());
    }
    /**
     * Converts a ZonedDateTime to the Eastern Standard Time zone.
     * @param zdt A ZonedDateTime object.
     * @return A ZonedDateTime object with EST5EDT ZoneID.
     */
    public static ZonedDateTime getOfficeTime(ZonedDateTime zdt){
        return zdt.withZoneSameInstant(ZoneId.of("EST5EDT"));
    }
    /**
     * Gets input from DatePicker and TextFields, and converts it into a ZonedDateTime.
     * @param ld The LocalDate object from a DatePicker.
     * @param hour The hour integer from a TextField.
     * @param minute The minute integer from a TextField.
     * @return A ZonedDateTime based on the user's input.
     */
    public static ZonedDateTime getDateTimeInput(LocalDate ld, int hour, int minute){
        LocalTime localTime = LocalTime.of(hour, minute, 0);
        LocalDateTime localDateTime = LocalDateTime.of(ld, localTime);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());     
        return zonedDateTime;
    }
    
}
