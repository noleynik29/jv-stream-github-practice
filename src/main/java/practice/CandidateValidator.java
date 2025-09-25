package practice;

import java.util.function.Predicate;
import model.Candidate;

public class CandidateValidator implements Predicate<Candidate> {
    private static final int MIN_AGE = 35;
    private static final int MIN_YEARS_IN_UKR = 10;
    private static final String REQUIRED_NATIONALITY = "Ukrainian";

    @Override
    public boolean test(Candidate candidate) {
        if (candidate == null) {
            return false;
        }
        if (candidate.getAge() < MIN_AGE) {
            return false;
        }
        if (!candidate.isAllowedToVote()) {
            return false;
        }
        if (!REQUIRED_NATIONALITY.equals(candidate.getNationality())) {
            return false;
        }
        if (candidate.getPeriodsInUkr() == null || candidate.getPeriodsInUkr().isBlank()) {
            return false;
        }
        String[] periods = candidate.getPeriodsInUkr().split(",");
        int totalYears = 0;
        for (String period : periods) {
            String[] years = period.trim().split("-");
            if (years.length != 2) {
                return false;
            }
            try {
                int fromYear = Integer.parseInt(years[0].trim());
                int toYear = Integer.parseInt(years[1].trim());
                totalYears += (toYear - fromYear + 1);
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return totalYears >= MIN_YEARS_IN_UKR;
    }
}
