package org.sstelmaj.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class AddStudentModal {

    public static final Target TITLE = Target.the("titulo modal agregar estudiante")
            .locatedBy("//div[contains(@role,'dialog')]//h2[contains(text(),'Agregar estudiante al curso')]");

    public static final Target STUDENT_ID_FIELD = Target.the("campo ID estudiante")
            .located(By.id("studentId"));

    public static final Target SEARCH_BUTTON = Target.the("boton buscar")
            .locatedBy("//div[contains(@role,'dialog')]//button[contains(text(),'Buscar')]");

    public static final Target FOUND_ALERT = Target.the("alerta estudiante encontrado")
            .located(By.cssSelector(".border-blue-200"));

    public static final Target NOT_FOUND_ALERT = Target.the("alerta estudiante no encontrado")
            .located(By.cssSelector(".border-yellow-200"));

    public static final Target NAME_FIELD = Target.the("campo nombre estudiante")
            .located(By.id("name"));

    public static final Target EMAIL_FIELD = Target.the("campo email estudiante")
            .located(By.id("email"));

    public static final Target ADD_BUTTON = Target.the("boton agregar al curso")
            .located(By.cssSelector("div[role='dialog'] button[type='submit']"));

    public static final Target SUCCESS_ALERT = Target.the("alerta estudiante agregado")
            .located(By.cssSelector(".border-green-200"));
}
