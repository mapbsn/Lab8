package Objects;

public enum FormOfEducation {
    DISTANCE,
    FULL,
    EVENING;
    private static FormOfEducation[] $values() {
        return new FormOfEducation[]{DISTANCE, FULL, EVENING};
    }
}
