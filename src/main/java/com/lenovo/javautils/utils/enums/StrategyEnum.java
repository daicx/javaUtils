package com.lenovo.javautils.utils.enums;

/**
 * 策略枚举
 * 周末加班工资翻倍，工作日加班1.5倍薪资。
 * 输入工作时长（单位/小时），薪资/小时。输出应得薪资。
 *
 * @author dcx
 * @date 2021/4/7 上午9:55
 */
public enum StrategyEnum {

    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY,
    SATURDAY(PayType.WEEKEND), SUNDAY(PayType.WEEKEND);
    private final PayType payType;

    StrategyEnum(PayType payType) {
        this.payType = payType;
    }

    StrategyEnum() {
        this(PayType.WEEKDAY);
    }

    double payMonet(int time, int pay) {
        return payType.payMoney(time, pay);
    }

    /**
     * 费用计算
     *
     * @author dcx
     * @date 2021/4/7 上午11:45
     */
    private enum PayType {
        WEEKDAY {
            @Override
            double overtimePay(int time, int pay) {
                return time > COMMON_HOUR ? (time - COMMON_HOUR) * 1.5 : 0;
            }
        },
        WEEKEND {
            @Override
            double overtimePay(int time, int pay) {
                return time * pay;
            }
        };

        abstract double overtimePay(int time, int pay);

        private static final int COMMON_HOUR = 8;

        /**
         * 总薪资计算
         *
         * @param time
         * @param pay
         * @return int
         * @author dcx
         * @date 2021/4/7 上午11:47
         */
        double payMoney(int time, int pay) {
            int base = time * pay;
            return base + overtimePay(time, pay);
        }
    }

    public static void main(String[] args) {
        StrategyEnum[] values = StrategyEnum.values();
        for (StrategyEnum value : values) {
            System.out.println(value.payMonet(10, 10));
        }
    }
}
