import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj numer PESEL: ");
        String pesel = scanner.nextLine();

        if (PeselUtils.sprawdzPlec(pesel)) {
            System.out.println("Twoja płeć:  Kobieta");
        } else {
            System.out.println("Twoja płeć:  Mężczyzna");
        }

        LocalDate birthDate = PeselUtils.getBirthDateFromPesel(pesel);
        System.out.println("Data urodzenia: " + birthDate);
    }
}

class PeselUtils {
    public static boolean sprawdzPlec(String pesel) {
        int cyfra = Character.getNumericValue(pesel.charAt(9));
        return cyfra % 2 == 0;
    }

    public static LocalDate getBirthDateFromPesel(String pesel) {
        int year = Integer.parseInt(pesel.substring(0, 2));
        int month = Integer.parseInt(pesel.substring(2, 4));
        int day = Integer.parseInt(pesel.substring(4, 6));

        int century;
        if (month > 20) {
            century = 2000;
            month -= 20;
        } else if (month > 40) {
            century = 2100;
            month -= 40;
        } else if (month > 60) {
            century = 2200;
            month -= 60;
        } else {
            century = 1900;
        }

        int fullYear = century + year;

        return LocalDate.of(fullYear, month, day);
    }
}