package com.one234gift.customerservice.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class JobTitle {
    private final String jobTitle;

    protected JobTitle(){jobTitle=null;}

    public JobTitle(String jobTitle) {
        jobTitleValidation(jobTitle);
        this.jobTitle = jobTitle;
    }

    private final static Pattern PATTERN = Pattern.compile("^[가-힣]{1,10}$");
    private void jobTitleValidation(String jobTitle) {
        if(!PATTERN.matcher(jobTitle).matches()){
            throw new IllegalArgumentException("직위는 한글조합 1자이상 10자 이하로 입력해주세요.");
        }
    }

    public String get() {
        return jobTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobTitle jobTitle1 = (JobTitle) o;
        return Objects.equals(jobTitle, jobTitle1.jobTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobTitle);
    }
}
