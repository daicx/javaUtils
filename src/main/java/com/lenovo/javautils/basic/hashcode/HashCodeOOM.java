package com.lenovo.javautils.basic.hashcode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HashCodeOOM {
    private int a;

    @Override
    public int hashCode() {
        return Integer.hashCode(a);
    }

    @Override
    public boolean equals(Object obj) {
        HashCodeOOM hashCodeOOM = (HashCodeOOM) obj;
        return hashCodeOOM.getA() == a;
    }
}
