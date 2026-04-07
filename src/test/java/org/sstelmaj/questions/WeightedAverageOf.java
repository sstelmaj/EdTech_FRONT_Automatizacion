package org.sstelmaj.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.sstelmaj.ui.GradesTable;

public class WeightedAverageOf implements Question<String> {

    private final String studentName;

    public WeightedAverageOf(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String answeredBy(Actor actor) {
        return GradesTable.weightedAverage(studentName).resolveFor(actor).getText().trim();
    }

    public static WeightedAverageOf student(String studentName) {
        return new WeightedAverageOf(studentName);
    }
}
