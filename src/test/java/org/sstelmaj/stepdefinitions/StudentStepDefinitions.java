package org.sstelmaj.stepdefinitions;

import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.actors.OnStage;
import org.sstelmaj.tasks.AddStudent;

import java.util.concurrent.atomic.AtomicLong;

public class StudentStepDefinitions {

    private static final AtomicLong ID_COUNTER =
            new AtomicLong(System.currentTimeMillis() % 9_000_000L + 1_000_000L);

    @Y("agrega al estudiante con ID {string} nombre {string} y correo {string}")
    public void agregaAlEstudianteConIdNombreYCorreo(String id, String name, String email) {
        String prefix = id.length() >= 3 ? id.substring(0, 3) : id;
        String uniqueId = prefix + String.format("%07d", ID_COUNTER.getAndIncrement() % 9_999_999L);
        OnStage.theActorInTheSpotlight().attemptsTo(
                AddStudent.withDetails(uniqueId, name, email)
        );
    }
}
