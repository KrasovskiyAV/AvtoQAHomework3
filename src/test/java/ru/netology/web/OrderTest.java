package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class OrderTest {
    @Test
    void shouldTest() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Красовский Алексей");
        form.$("[data-test-id=phone] input").setValue("+79139999999");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNotEndApplicationIfCheckboxEmpty() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Красовский Алексей");
        form.$("[data-test-id=phone] input").setValue("+79139999999");
        form.$(".button").click();
        $("[data-test-id=agreement].input_invalid").shouldBe(visible);
    }

    @Test
    void shouldShowErrorIfNameEmptyTest() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id='name'] input").setValue("");
        form.$("[data-test-id='phone'] input").setValue("+79139999999");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldShowErrorIfPhoneEmptyTest() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Красовский Алексей");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }

    @Test
    void shouldShowErrorIfNameInvalid() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Krasovskiy");
        form.$("[data-test-id=phone] input").setValue("+79139999999");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldShowErrorIfPhoneInvalid() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Красовский Алексей");
        form.$("[data-test-id=phone] input").setValue("+7913999");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}

