package csvreader.exceptions;

public class CsvReaderRunTimeException extends RuntimeException {
    public CsvReaderRunTimeException(String message) {
        super(message);
    }

    public CsvReaderRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
