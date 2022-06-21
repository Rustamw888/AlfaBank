package cloud.autotests.tests;

import cloud.autotests.helpers.DriverUtils;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("Alfa")
public class SmokeTests extends TestBase {

    // locators
    SelenideElement cardTab = $("[title='Карты']");
    SelenideElement depositsTab = $("[title='Вклады']");
    SelenideElement investmentsTab = $("[data-widget-name='Layout'] h2");


    // tests
    @Test
    @Description("Card tab testing")
    @DisplayName("Alfa")
    void cardsTest() {
        step("open main page", () -> {
            open("https://alfabank.ru/");
        });

        step("card tab checking", () -> {
            cardTab.hover().find(byText("Кредитные карты"));
            cardTab.hover().find(byText("Дебетовые карты"));
            cardTab.hover().find(byText("Специальные условия"));
            cardTab.click();
        });

        step("Card page assertions", () -> {
            assertEquals($$("[data-widget-name='CatalogCard']").size(), 3);
        });
    }

    @Test
    @Description("Deposits tab testing")
    @DisplayName("Alfa")
    void depositsTest() {
        step("open main page", () -> {
            open("https://alfabank.ru/");
        });

        step("deposits tab checking", () -> {
            depositsTab.hover().find(byText("Вклады"));
            depositsTab.hover().find(byText("Накопительные счета"));
            depositsTab.hover().find(byText("Бесплатные сервисы для накоплений"));
            depositsTab.click();
        });

        step("deposits page assertions", () -> {
            $("[data-widget-name='Heading']").shouldHave(
                    Condition.text("Накопительные продукты"));
        });
    }

    @Test
    @Description("Investments tab testing")
    @DisplayName("Alfa")
    void investmentsTest() {
        step("open investments page", () -> {
            open("https://alfabank.ru/make-money/investments/new-ab/");
        });

        step("investments tab checking", () -> {
            investmentsTab.find(byText("Как начать инвестировать"));
            investmentsTab.find(byText("Какой счет открыть"));
            investmentsTab.find(byText("Установите Альфа-Инвестиции"));
        });
    }

    @Test
    @Description("Main page headers checking")
    @DisplayName("Page title should have header text")
    void titleTest() {
        step("Open url 'https://alfabank.ru/'", () ->
            open("https://alfabank.ru/"));

        step("Page title should have text 'Альфа-Банк - кредитные и дебетовые карты, кредиты наличными, автокредитование, ипотека и другие банковские услуги физическим и юридическим лицам – Альфа-Банк'", () -> {
            String expectedTitle = "Альфа-Банк - кредитные и дебетовые карты, кредиты наличными, автокредитование, ипотека и другие банковские услуги физическим и юридическим лицам – Альфа-Банк";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @Description("Clear console log")
    @DisplayName("Page console log should not have errors")
    void consoleShouldNotHaveErrorsTest() {
        step("Open url 'https://alfabank.ru/'", () ->
            open("https://alfabank.ru/"));

        step("Console logs should not contain text 'SEVERE'", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }
}