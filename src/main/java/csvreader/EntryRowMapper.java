package csvreader;

import csvreader.models.LaLigaRecord;
import org.apache.commons.csv.CSVRecord;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.function.Function;

import static csvreader.models.LaLigaCsvHeader.*;

/**
 * This object maps each entry of the csv into an object. The mapping logic,
 * if any should be implemented here, like date formatting.
 */
public class EntryRowMapper implements Function<CSVRecord, LaLigaRecord> {

    @Override
    public LaLigaRecord apply(CSVRecord csvRecord) {
        return new LaLigaRecord(
                Integer.parseInt(csvRecord.get(ROUND)),
                parseDate(csvRecord.get(DATE)),
                csvRecord.get(TEAM1),
                csvRecord.get(FT),
                csvRecord.get(TEAM2)
        );
    }

    /**
     * Converts a date stored as String in the format: Sat Sep 12 2020, to LocalDate
     * When you parse the month-day be careful to use a single "d" and not "dd" because the
     * latter will crash when you are reading single-digits like:
     * Thu Oct 1 2020 instead of Thu Oct 01 2020
     *
     * @param date read from the csv.
     * @return parsed date
     */
    private LocalDate parseDate(String date) {
        String dateFormat = "EEE MMM d yyyy";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat, Locale.US);
        return LocalDate.parse(date, dateTimeFormatter);
    }

}
