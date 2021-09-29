package com.one234gift.customerservice.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class Note {
    private final String note;

    protected Note(){note = null;}

    public Note(String note) {
        noteValidation(note);
        this.note = note;
    }

    private final static Pattern PATTERN = Pattern.compile("^[가-힣ㄱ-ㅎa-zA-Z0-9\\s]{1,100}$");
    private void noteValidation(String note) {
        if(!PATTERN.matcher(note).matches()){
            throw new IllegalArgumentException("비고는 특수문자를 특수문자를 허용하지 않고 1자 이상 100자 이하로 입력해주세요.");
        }
    }

    public String get() {
        return note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note1 = (Note) o;
        return Objects.equals(note, note1.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(note);
    }
}
