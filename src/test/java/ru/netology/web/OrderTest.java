package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class OrderTest {
    @Test
    void shouldTest() throws InterruptedException {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Красовский Алексей");
        form.$("[data-test-id=phone] input").setValue("+79139999999");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
        Thread.sleep(5000);
    }

    @Test
    void shouldNotEndApplicationIfCheckboxEmpty() throws InterruptedException {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Красовский Алексей");
        form.$("[data-test-id=phone] input").setValue("+79139999999");
        $("[data-test-id=agreement]");
        form.$(".button").click();
        $("[data-test-id=agreement] input_invalid");
        Thread.sleep(5000);
    }

    @Test
    void shouldShowErrorIfNameEmptyTest() throws InterruptedException {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("+79139999999");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
        Thread.sleep(3000);
    }

    @Test
    void shouldShowErrorIfPhoneEmptyTest() throws InterruptedException {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Красовский Алексей");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
        Thread.sleep(3000);
    }

    @Test
    void shouldShowErrorIfNameInvalid() throws InterruptedException {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Krasovskiy");
        form.$("[data-test-id=phone] input").setValue("+79139999999");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
        Thread.sleep(3000);
    }

    @Test
    void shouldShowErrorIfPhoneInvalid() throws InterruptedException {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Красовский Алексей");
        form.$("[data-test-id=phone] input").setValue("+7913999");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        Thread.sleep(3000);
    }
}

