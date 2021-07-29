package ru.netology.bonus;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BonusServiceTest {
    @ParameterizedTest
    @CsvSource(
            value={
                    "'registered user, bonus under limit',100060,true,30",
                    "'registered user, bonus over limit',100000060,true,500"
            }
    )
    void shouldCalculate(String test, long amount, boolean registered, long expected) {
        BonusService service = new BonusService();

        // вызываем целевой метод:
        long actual = service.calculate(amount, registered);

        // производим проверку (сравниваем ожидаемый и фактический):
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    void shouldCalculateForNotRegistered(String test, long amount, boolean registered, long expected) {
        BonusService service = new BonusService();

        long actual = service.calculate(amount, registered);

        assertEquals(expected, actual);
    }
}