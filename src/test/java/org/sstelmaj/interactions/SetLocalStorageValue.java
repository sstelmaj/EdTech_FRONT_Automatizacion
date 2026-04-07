package org.sstelmaj.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.annotations.Step;
import org.openqa.selenium.JavascriptExecutor;

public class SetLocalStorageValue implements Interaction {

    private final String key;
    private final String value;

    public SetLocalStorageValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    @Step("{0} establece en localStorage la clave '#key' con valor '#value'")
    public <T extends Actor> void performAs(T actor) {
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        js.executeScript("window.localStorage.setItem(arguments[0], arguments[1]);", key, value);
    }

    public static SetLocalStorageValue withKey(String key, String value) {
        return Tasks.instrumented(SetLocalStorageValue.class, key, value);
    }
}
