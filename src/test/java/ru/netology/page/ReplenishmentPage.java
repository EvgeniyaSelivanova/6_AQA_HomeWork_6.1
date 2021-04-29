package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class ReplenishmentPage {
    private SelenideElement heading = $(withText("Пополнение карты"));

    public ReplenishmentPage() {
        heading.shouldBe(Condition.visible);
    }

    public DashboardPage replenishment (String quantity, String from){
        $("[data-test-id=amount] input").setValue(quantity);
        $("[data-test-id=from] input").setValue(from);
        $("[data-test-id=action-transfer]").click();
        return new DashboardPage();
    }


}
