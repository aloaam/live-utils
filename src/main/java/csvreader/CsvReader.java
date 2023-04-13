package csvreader;

import csvreader.exceptions.CsvReaderRunTimeException;
import csvreader.models.LaLigaCsvHeader;
import csvreader.models.LaLigaRecord;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


public class CsvReader {

    public List<LaLigaRecord> read(InputStream inputStream) {

        CSVFormat csvFormat = getCsvFormatSetUp();
        CSVParser records = getRecords(inputStream, csvFormat);
        return getObjects(records);
    }

    /**
     * To read an input stream you need a formatter.
     * You can set it up with different options.
     * @return a CsvFormatter
     */
    private CSVFormat getCsvFormatSetUp() {
        return CSVFormat.DEFAULT.builder()
                .setHeader(LaLigaCsvHeader.class)
                .setSkipHeaderRecord(true)
                .build();
    }

    /**
     * Once you have a formatter set-up you can use it to read an inputStream
     * into CsvRecords.
     * @param inputStream stream to decode.
     * @param csvFormat formatter with decoding rules
     * @return CsvRecords in an object called CsvParser
     */
    private CSVParser getRecords(InputStream inputStream, CSVFormat csvFormat) {
        try {
            return csvFormat.parse(new InputStreamReader(inputStream));
        } catch (IOException e) {
            throw new CsvReaderRunTimeException("Unable to get records from the input stream.", e);
        }
    }

    /**
     * Finally you can extract the data from each CsvRecord into an  object.
     * @param records with csv data.
     * @return a list of objects with the data of the csv file.
     */
    private List<LaLigaRecord> getObjects(CSVParser records) {
        return records.stream()
                .map(new EntryRowMapper())
                .toList();
    }


}
