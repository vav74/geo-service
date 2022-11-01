package ru.netology.geo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

/*Написать тесты для проверки определения локации по ip (класс GeoServiceImpl)
Проверить работу метода public Location byIp(String ip)*/
@ExtendWith(MockitoExtension.class)
public class GeoServiceImplTest {
    private GeoService geoService;
    private static final String MOSCOW_IP = "172.0.32.11";
    private static final String NEW_YORK_IP = "96.44.183.149";

    @BeforeEach
    void setUp() {
        geoService = new GeoServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    void geoServiceTest(String ip, Location location) {
        location.equals(geoService.byIp(ip));
    }

    private static Stream<Arguments> getArguments() {
        return Stream.of(
                Arguments.of(MOSCOW_IP, new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of(NEW_YORK_IP, new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.", new Location("New York", Country.USA, null, 0)));
    }
}