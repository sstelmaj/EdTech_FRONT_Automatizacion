package org.sstelmaj.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CreateCourseModal {

    public static final Target TITLE = Target.the("titulo modal crear curso")
            .locatedBy("//div[contains(@role,'dialog')]//h2[contains(text(),'Crear nuevo curso')]");

    public static final Target COURSE_NAME_FIELD = Target.the("campo nombre del curso")
            .located(By.id("courseName"));

    public static final Target CANCEL_BUTTON = Target.the("boton cancelar")
            .locatedBy("//div[contains(@role,'dialog')]//button[contains(text(),'Cancelar')]");

    public static final Target SAVE_BUTTON = Target.the("boton guardar curso")
            .located(By.cssSelector("div[role='dialog'] button[type='submit']"));

    public static final Target SUCCESS_ALERT = Target.the("alerta curso creado")
            .located(By.cssSelector(".border-green-200"));

    public static final Target ERROR_ALERT = Target.the("alerta error crear curso")
            .located(By.cssSelector("[role='alert']"));
}
