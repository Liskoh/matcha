package io.github.mlearning.objects;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Data
@AllArgsConstructor
public class BornData {

    private int year;
    private int month;
    private int day;

    public static Optional<BornData> fromString(String dateString) {
        try {
            System.out.println(dateString + " called");
            final String[] dateParts = dateString.split("-");
            System.out.println(dateParts[0]);
            System.out.println(dateParts[1]);
            System.out.println(dateParts[2]);
            return Optional.of(new BornData(
                    Integer.parseInt(dateParts[0]),
                    Integer.parseInt(dateParts[1]),
                    Integer.parseInt(dateParts[2])
            ));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    public int getAge() {
        System.out.println(Period.between(LocalDate.of(year, month, day), LocalDate.now()).getYears());
        return Period.between(LocalDate.of(year, month, day), LocalDate.now()).getYears();
    }
}
