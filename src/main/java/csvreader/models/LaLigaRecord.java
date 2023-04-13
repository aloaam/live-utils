package csvreader.models;

import java.time.LocalDate;

public record LaLigaRecord(
        int round,
        LocalDate date,
        String Team1,
        String FullTimeScore,
        String Team2

) {}
