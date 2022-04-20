package com.lenovo.javautils;

import com.google.common.collect.Maps;
import com.lenovo.javautils.basic.hashcode.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HashCodeTest {

    @Test
    public void testIntHashCode() {
        int a = 1;
        System.out.println("int 1 的hashcode:" + Integer.hashCode(a));
        double b = 1.0;
        System.out.println(Double.hashCode(b));
    }

    @Test
    public void testObjHashCode() {
        HashCode hashCode = new HashCode();
        hashCode.setA(1);
        System.out.println(hashCode.hashCode());
        HashCode hashCode1 = new HashCode();
        hashCode1.setA(1);
        System.out.println(hashCode1.hashCode());
    }

    @Test
    public void testObj1HashCode() {
        HashCode1 hashCode = new HashCode1();
        hashCode.setA(1);
        System.out.println(hashCode.hashCode());
        HashCode1 hashCode1 = new HashCode1();
        hashCode1.setA(1);
        System.out.println(hashCode1.hashCode());
    }

    @Test
    public void equalsNoHashCode() {
        EqualsNoHashCode equalsNoHashCode = new EqualsNoHashCode();
        equalsNoHashCode.setA(1);
        EqualsNoHashCode equalsNoHashCode1 = new EqualsNoHashCode();
        equalsNoHashCode1.setA(1);
        HashMap<EqualsNoHashCode, Integer> map = Maps.newHashMap();
        map.put(equalsNoHashCode, 2);
        map.put(equalsNoHashCode1, 2);
        System.out.println(map.size());
        EqualsNoHashCode equalsNoHashCode2 = new EqualsNoHashCode();
        equalsNoHashCode2.setA(1);
        Integer integer = map.get(equalsNoHashCode2);
        System.out.println(integer);
    }

    @Test
    public void hashCodeNoEquals() {
        HashCodeNoEquals equalsNoHashCode = new HashCodeNoEquals();
        equalsNoHashCode.setA(1);
        HashCodeNoEquals equalsNoHashCode1 = new HashCodeNoEquals();
        equalsNoHashCode1.setA(1);
        HashMap<HashCodeNoEquals, Integer> map = Maps.newHashMap();
        map.put(equalsNoHashCode, 2);
        map.put(equalsNoHashCode1, 2);
        System.out.println(map.size());
        HashCodeNoEquals equalsNoHashCode2 = new HashCodeNoEquals();
        equalsNoHashCode2.setA(1);
        Integer integer = map.get(equalsNoHashCode2);
        System.out.println(integer);
    }

    @Test
    public void hashCodeAndEquals() {
        HashCode hashCode = new HashCode();
        hashCode.setA(1);
        HashCode hashCode1 = new HashCode();
        hashCode1.setA(1);
        HashMap<HashCode, Integer> map = Maps.newHashMap();
        map.put(hashCode, 2);
        map.put(hashCode1, 2);
        System.out.println(map.size());
        HashCode hashCode2 = new HashCode();
        hashCode2.setA(1);
        Integer integer = map.get(hashCode2);
        System.out.println(integer);
    }

    @Test
    public void hashCodeOOM(){
        HashCodeOOM hashCodeOOM = new HashCodeOOM();
        hashCodeOOM.setA(1);
        HashCodeOOM hashCodeOOM1 = new HashCodeOOM();
        hashCodeOOM1.setA(2);
        HashMap<HashCodeOOM, Integer> map = Maps.newHashMap();
        map.put(hashCodeOOM,2);
        map.put(hashCodeOOM1,2);
        hashCodeOOM.setA(3);
        Integer remove = map.remove(hashCodeOOM);
        System.out.println(remove);
        System.out.println(map.size());
    }
}
