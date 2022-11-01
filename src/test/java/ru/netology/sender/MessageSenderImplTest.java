package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class MessageSenderImplTest {
    /*
    Проверить, что MessageSenderImpl всегда отправляет только русский текст, если ip относится к российскому сегменту адресов.
    Проверить, что MessageSenderImpl всегда отправляет только английский текст, если ip относится к американскому сегменту адресов.
    Создайте тесты в соответствии с задачей (для сервиса MessageSenderImpl, нужно обязательно создать заглушки (mock)
    в виде GeoServiceImpl и LocalizationServiceImpl) */
    @Mock
    private LocalizationService localizationService = new LocalizationServiceImpl();
    private final GeoService geoService = new GeoServiceImpl();
    @InjectMocks
    private MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Тестирование - IP USA")
    void sendRU() {
        Map<String, String> map = new HashMap<>();
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.0.0");
        String actualResult = messageSender.send(map);
        String expectedResult = "Добро пожаловать";
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Тестирование - IP RU")
    void sendUS() {
        Map<String, String> map = new HashMap<>();
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.0.0.0");
        String actualResult = messageSender.send(map);
        String expectedResult = "Welcome";
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Тестирование - пустой IP")
    void sendEmptyIP() {
        Map<String, String> map = new HashMap<>();
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, "");
        String actualResult = messageSender.send(map);
        String expectedResult = "Welcome";
        Assertions.assertEquals(expectedResult, actualResult);
    }
}