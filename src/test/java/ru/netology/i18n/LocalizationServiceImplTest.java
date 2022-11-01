package ru.netology.i18n;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;

import java.util.stream.Stream;

/*Написать тесты для проверки возвращаемого текста (класс LocalizationServiceImpl)
Проверить работу метода public String locale(Country country)*/
@ExtendWith(MockitoExtension.class)
public class LocalizationServiceImplTest {
    private LocalizationService localizationService;

    @BeforeEach
    void setUp() {
        localizationService = new LocalizationServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    void locale(String greeting, Country country) {
        greeting.equals(localizationService.locale(country));
    }

    private static Stream<Arguments> getArguments() {
        return Stream.of(
                Arguments.of("Welcome", Country.USA),
                Arguments.of("Добро пожаловать", Country.RUSSIA),
                Arguments.of("Welcome", Country.BRAZIL),
                Arguments.of("Welcome", Country.GERMANY));
    }
}