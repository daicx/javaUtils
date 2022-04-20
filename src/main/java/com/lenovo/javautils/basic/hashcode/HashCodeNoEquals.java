package com.lenovo.javautils.basic.hashcode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HashCodeNoEquals {
    private Integer a;

    @Override
    public int hashCode() {
        return a.hashCode();
    }
}
