package academy.elqoo.java8.dateandtime;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;

import static java.time.Month.JANUARY;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DateAndTimeTest {

    @Test
    public void shouldCreateNewDate(){
        LocalDate newYearsEve = DateTime8.createNewYearsEve2017(); // create new years eve 2017 using the localdate static factory methods
        assertThat(newYearsEve.getYear(), is(equalTo(2017)));
        assertThat(newYearsEve.getMonth(), is(equalTo(Month.DECEMBER)));
        assertThat(newYearsEve.getDayOfMonth(), is(equalTo(31)));
    }

    @Test
    public void shouldGotoFirstOfNextMonth(){
        LocalDate newYearsEve = DateTime8.createNewYearsEve2017();
        LocalDate firstJanuary2018 = newYearsEve.plusDays(1);
        assertThat(firstJanuary2018.getYear(), is(equalTo(2018)));
        assertThat(firstJanuary2018.getMonth(), is(equalTo(Month.JANUARY)));
        assertThat(firstJanuary2018.getDayOfMonth(), is(equalTo(1)));
    }

    @Test
    public void shouldRetrieveDateInformationUsingChronoFields(){
        LocalDate newYearsEve = DateTime8.createNewYearsEve2017();
        int year = newYearsEve.getYear(); // replace this by getting the year using chrono fields interface
        int month =newYearsEve.getMonthValue();
        int day = newYearsEve.getDayOfMonth();
        assertThat(year, is(equalTo(newYearsEve.getYear())));
        assertThat(month, is(equalTo(12)));
        assertThat(day, is(equalTo(newYearsEve.getDayOfMonth())));
    }

    @Test
    public void shouldParseLocalDate(){
        String newYearsEveAsString = "31-12-2017";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate newYearsEve = LocalDate.parse(newYearsEveAsString, formatter); // parse the string to a date
        assertThat(newYearsEve.getYear(), is(equalTo(2017)));
        assertThat(newYearsEve.getMonth(), is(equalTo(Month.DECEMBER)));
        assertThat(newYearsEve.getDayOfMonth(), is(equalTo(31)));
    }

    @Test(expected = DateTimeParseException.class)
    public void shouldThrowParseException(){
        // parse the an error
        String newYearsEveAsString = "31-Dec-2017";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate newYearsEve = LocalDate.parse(newYearsEveAsString, formatter);
        assertThatExceptionOfType(DateTimeParseException.class).isThrownBy(()-> formatter.parse(newYearsEveAsString));
    }

    @Test
    public void shouldParseLocalTime(){
        String midnightAsString = "00:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm:ss");
        LocalTime midnight = LocalTime.parse(midnightAsString, formatter); // parse the time
        assertThat(midnight.getHour(), is(equalTo(0)));
        assertThat(midnight.getMinute(), is(equalTo(0)));
        assertThat(midnight.getSecond(), is(equalTo(0)));
    }

    @Test
    public void shouldCreateLocalDateTimeNewYearsEve(){
        LocalDateTime newYearsEve = LocalDateTime.of(2017, 12,31,00,00,00); // create LocalDateTime for new years eve at midnight
        assertThat(newYearsEve.getYear(), is(equalTo(2017)));
        assertThat(newYearsEve.getMonth(), is(equalTo(Month.DECEMBER)));
        assertThat(newYearsEve.getDayOfMonth(), is(equalTo(31)));
        assertThat(newYearsEve.getHour(), is(equalTo(0)));
        assertThat(newYearsEve.getMinute(), is(equalTo(0)));
        assertThat(newYearsEve.getSecond(), is(equalTo(0)));
    }

    @Test
    public void shouldCreateNewYearsInstant(){
        Instant newYearsEveInstant = Instant.ofEpochSecond(1514674800); // use https://www.epochconverter.com/ to create the instance
        assertNotNull(newYearsEveInstant);
    }

    @Test
    public void shouldCalculateDaysBetween(){
        LocalDate[] dates = DateTime8.getTwoLocalDates();
        long daysBetween = dates[1].compareTo(dates[0]); // calculate days between dates[0] and dates[1]
        assertThat(DateTime8.DAYS_BETWEEN, equalTo(daysBetween));
    }

    @Test
    public void shouldCreateDurationFromTemporalUnit(){
        Duration duration = Duration.ofSeconds(5L); // create a duration of 5 seconds
        long seconds = duration.getSeconds();
        assertThat(5L, equalTo(seconds));
    }

    @Test
    public void shouldCheckIfDurationIsZero(){
        Duration duration = Duration.ofDays(5L); // create a duration of 5 days
        boolean isZero = duration.isZero(); // check for the duration if it's zero
        assertThat(false, equalTo(isZero));
    }

    @Test
    public void shouldFormatToString(){
        LocalDate newYearsEve = DateTime8.createNewYearsEve2017();
        String format = newYearsEve.toString(); // format the string
        assertThat("2017-12-31",equalTo(format));
    }

    @Test
    public void shouldUseWithMethodtoChangeDate() {
        LocalDate newYearsEve = DateTime8.createNewYearsEve2017();
        LocalDate newYearsEve2018 = newYearsEve.withYear(2018); // change the newYearsEve using the with method
        LocalDate firstJanuary = newYearsEve2018.withDayOfMonth(1);
        assertThat(2018, equalTo(newYearsEve2018.getYear()));
        assertThat(1, equalTo(firstJanuary.getDayOfMonth()));
    }

    @Test
    public void shouldAdjustToNewYearsEve(){
        LocalDate firstOfDecember = LocalDate.of(2017,12,1);
        LocalDate newYearsEve = firstOfDecember.with(TemporalAdjusters.lastDayOfMonth()); // write a temporal adjuster to ajust the firstOfDecember to new years eve
        assertThat(newYearsEve.getYear(), is(equalTo(2017)));
        assertThat(newYearsEve.getMonth(), is(equalTo(Month.DECEMBER)));
        assertThat(newYearsEve.getDayOfMonth(), is(equalTo(31)));
    }

    @Test
    public void shouldFindTheNextFriday13th() {
        LocalDate from = LocalDate.of(2017, JANUARY, 1);
        LocalDate expected = LocalDate.of(2017, JANUARY, 13);
        assertEquals(expected, DateTime8.findNextFriday13th(from));
    }

}
