package org.sstelmaj.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class EvaluationProgramModal {

    public static final Target TITLE = Target.the("titulo modal programa evaluativo")
            .locatedBy("//div[contains(@role,'dialog')]//h2[contains(text(),'Programa Evaluativo')]");

    public static final Target DISTRIBUTE_BUTTON = Target.the("boton distribuir equitativamente")
            .locatedBy("//div[contains(@role,'dialog')]//button[contains(text(),'Distribuir equitativamente')]");

    public static final Target ADD_ACTIVITY_BUTTON = Target.the("boton agregar actividad")
            .locatedBy("//div[contains(@role,'dialog')]//button[contains(text(),'Agregar actividad')]");

    public static final Target SAVE_BUTTON = Target.the("boton guardar programa")
            .located(By.cssSelector("div[role='dialog'] button[type='submit']"));

    public static final Target TOTAL_PERCENTAGE = Target.the("total porcentaje")
            .locatedBy("//div[contains(@role,'dialog')]//span[contains(text(),'%')]");

    public static final Target SUCCESS_ALERT = Target.the("alerta programa guardado")
            .located(By.cssSelector(".border-green-200"));

    public static Target activityNameField(String id) {
        return Target.the("campo nombre actividad " + id)
                .located(By.id("name-" + id));
    }

    public static Target activityPercentageField(String id) {
        return Target.the("campo porcentaje actividad " + id)
                .located(By.id("percentage-" + id));
    }

    public static Target deleteActivityButton(int index) {
        return Target.the("boton eliminar actividad " + index)
                .locatedBy("(//div[contains(@role,'dialog')]//button[contains(@class,'text-red-600')])[" + index + "]");
    }

    public static Target activityNameFieldByIndex(int index) {
        return Target.the("campo nombre actividad index " + index)
                .locatedBy("(//div[contains(@role,'dialog')]//input[contains(@id,'name-')])[" + index + "]");
    }

    public static Target activityPercentageFieldByIndex(int index) {
        return Target.the("campo porcentaje actividad index " + index)
                .locatedBy("(//div[contains(@role,'dialog')]//input[contains(@id,'percentage-')])[" + index + "]");
    }

    public static Target deleteActivityButtonByName(String activityName) {
        return Target.the("boton eliminar actividad " + activityName)
                .locatedBy("//div[contains(@role,'dialog')]//input[@value='" + activityName + "']/ancestor::div[contains(@class,'flex')]//button[contains(@class,'text-red-600')]");
    }
}
