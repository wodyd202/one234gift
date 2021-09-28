package com.one234gift.userservice.domain.value;

import java.util.Objects;
import java.util.regex.Pattern;

public class Username {
    private final String username;

    protected Username(){
        username = null;
    }

    public Username(String username) {
        usernameValidation(username);
        this.username = username;
    }

    private final static Pattern PATTERN = Pattern.compile("^[가-힣]{1,10}$");
    private void usernameValidation(String username) {
        if(!PATTERN.matcher(username).matches()){
            throw new IllegalArgumentException("사용자의 이름은 한글 조합 1자 이상 10자 이하로 입력해주세요.");
        }
    }

    public String get() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Username username1 = (Username) o;
        return Objects.equals(username, username1.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
