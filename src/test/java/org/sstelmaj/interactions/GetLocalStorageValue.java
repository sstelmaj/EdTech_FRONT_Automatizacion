package org.sstelmaj.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.annotations.Step;
import org.openqa.selenium.JavascriptExecutor;

public class GetLocalStorageValue implements Interaction {

    private final String key;

    public GetLocalStorageValue(String key) {
        this.key = key;
    }

    @Override
    @Step("{0} obtiene el valor de localStorage para la clave '#key'")
    public <T extends Actor> void performAs(T actor) {
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        String value = (String) js.executeScript("return window.localStorage.getItem(arguments[0]);", key);
        actor.remember(key, value);
    }

    public static GetLocalStorageValue forKey(String key) {
        return Tasks.instrumented(GetLocalStorageValue.class, key);
    }
}
