package com.lenovo.javautils.utils.enums;

/**
 * 特定于常量的方法实现。
 *
 * @author dcx
 * @date 2021/4/6 下午7:54
 */
public enum CalcEnum {
    PLUS {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DEVIDE {
        @Override
        public double apply(double x, double y) {
            return x / y;
        }
    };

    public abstract double apply(double x, double y);

    public static void main(String[] args) {
        CalcEnum[] values = CalcEnum.values();
        for (CalcEnum value : values) {
            double apply = value.apply(1, 2);
            System.out.println(apply);
        }
    }
}
