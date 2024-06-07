package Objects;

import java.util.Comparator;

public class StudentsCountComparator implements Comparator<StudyGroup> {
    @Override
    public int compare(StudyGroup lhs, StudyGroup rhs) {
        return lhs.getStudentsCount().compareTo(rhs.getStudentsCount());
    }
}
