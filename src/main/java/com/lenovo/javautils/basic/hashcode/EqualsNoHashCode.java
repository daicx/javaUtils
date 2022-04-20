package com.lenovo.javautils.basic.hashcode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EqualsNoHashCode {
    private int a;

    @Override
    public boolean equals(Object obj) {
        EqualsNoHashCode obj1 = (EqualsNoHashCode) obj;
        return obj1.getA() == a;
    }
}
