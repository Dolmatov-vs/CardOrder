
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardOrderTest {

    @BeforeEach
    void setUp() {
        Configuration.headless = true;
        open("http://localhost:9999");
        $("[data-test-id='name']").shouldHave(text("Укажите точно как в паспорте"));
        $$(By.className("input__sub")).get(1).shouldHave(text("На указанный номер моб. тел. будет отправлен смс-код для подтверждения заявки на карту. Проверьте, что номер ваш и введен корректно."));
        $("[class='input__sub']").shouldHave(cssValue("color", "rgba(11, 31, 53, 0.6)"));
        $$(By.className("input__sub")).get(1).shouldHave(cssValue("color", "rgba(11, 31, 53, 0.6)"));
    }

    @Test
    void shouldApplicationSentSuccessfully() {
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("+79281234567");
        $("[class='checkbox checkbox_size_m checkbox_theme_alfa-on-white']").click();
        $("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").click();
        $("[class='Success_successBlock__2L3Cw']").shouldHave(text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNameAndSurnameEnteredIncorrectly() {
        $("[data-test-id='name'] input").setValue("Ivanov Ivan");
        $("[data-test-id='phone'] input").setValue("+79281234567");
        $("[class='checkbox checkbox_size_m checkbox_theme_alfa-on-white']").click();
        $("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").click();
        $("[data-test-id='name']").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        $("[data-test-id='name']").shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));
    }

    @Test
    void shouldTelephoneEnteredIncorrectly() {
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+7928123456");
        $("[class='checkbox checkbox_size_m checkbox_theme_alfa-on-white']").click();
        $("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").click();
        $(By.className("input_invalid")).shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        $("[data-test-id='phone']").shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));
    }

    @Test
    void shouldTelephoneAndNameEnteredIncorrectly() {
        $("[data-test-id='name'] input").setValue("Ivanov Ivan");
        $("[data-test-id='phone'] input").setValue("+7928123456");
        $("[class='checkbox checkbox_size_m checkbox_theme_alfa-on-white']").click();
        $("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").click();
        $(By.className("input_invalid")).shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        $("[data-test-id='name']").shouldHave(cssValue("color", "rgba(255, 92, 92, 1)"));
    }
}
