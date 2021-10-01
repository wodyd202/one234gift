package com.one234gift.customerservice.domain.value;

import java.util.Objects;
import java.util.regex.Pattern;

public class Location {
    private final String location;

    protected Location(){location=null;}

    public Location(String location) {
        locationValidation(location);
        this.location = location;
    }

    private final static Pattern PATTERN = Pattern.compile("^[가-힣0-9a-zA-Z\\s]{1,15}$");
    private void locationValidation(String location) {
        if(!PATTERN.matcher(location).matches()){
            throw new IllegalArgumentException("지역은 [한글,숫자,영어](공백가능) 1자이상 15자 이하로 입력해주세요.");
        }
    }

    public String get() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location1 = (Location) o;
        return Objects.equals(location, location1.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }

    @Override
    public String toString() {
        return "Location{" +
                "location='" + location + '\'' +
                '}';
    }
}
