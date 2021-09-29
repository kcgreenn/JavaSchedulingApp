/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * A functional interface for gathering date and time related user input.
 * @author KC Green
 */
public interface DateTimeInput {
    /**
     * Gathers data from inputs and forms data into a ZonedDateTime.
     * @param ld The LocalDate from a DatePicker.
     * @param hour The hour selected from a Spinner.
     * @param minute The minute selected from a Spinner.
     * @return The data parsed into a ZonedDateTime object.
     */
    ZonedDateTime gather(LocalDate ld, int hour, int minute);
}
