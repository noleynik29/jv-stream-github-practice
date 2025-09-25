package practice;

import java.util.function.Predicate;
import model.Candidate;

public class CandidateValidator implements Predicate<Candidate> {

    @Override
    public boolean test(Candidate candidate) {
        if (candidate == null) {
            return false;
        }
        if (candidate.getAge() < 35) {
            return false;
        }
        if (!candidate.isAllowedToVote()) {
            return false;
        }
        if (candidate.getNationality() == null
                || !candidate.getNationality().trim().equalsIgnoreCase("Ukrainian")) {
            return false;
        }

        String[] periods = candidate.getPeriodsInUkr().split(",");
        int totalYears = 0;
        for (String period : periods) {
            String[] years = period.trim().split("-");
            int fromYear = Integer.parseInt(years[0]);
            int toYear = Integer.parseInt(years[1]);
            totalYears += (toYear - fromYear + 1);
        }

        return totalYears >= 10;
    }
}
