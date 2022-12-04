package top.wys.utils.math;

import org.junit.Test;
import top.wys.utils.NumberUtils;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by 郑明亮 on 2022/2/7 21:40.
 */
public class NumbersTest {

    @Test
    public void add() {
    }

    @Test
    public void subtract() {
        Number subtract = Numbers.subtract(new Number("3.151"), new Number("1.011"));
        System.out.println(subtract.toPlainString());

        Number subtract2 = Numbers.subtract(new BigDecimal("3.151"), new BigDecimal("1.011"));
        System.out.println(subtract2.toPlainString());
    }

    @Test
    public void amount2rmb() {
        System.out.println(NumberUtils.amount2rmb("1,003,003"));
        System.out.println(NumberUtils.amount2rmb("1,003,003.01"));
        System.out.println(NumberUtils.amount2rmb("1020503.00"));
        System.out.println(NumberUtils.amount2rmb("102050"));
        // log4j?
        System.out.println("壹万陆仟肆佰零玖元零贰分".equals(NumberUtils.amount2rmb("16,409.02")));
        System.out.println("壹仟肆佰零玖元伍角".equals(NumberUtils.amount2rmb("1,409.50")));
        System.out.println("陆仟零柒元壹角肆分".equals(NumberUtils.amount2rmb("6,007.14")));
        System.out.println("壹仟陆佰捌拾元叁角贰分".equals(NumberUtils.amount2rmb("1,680.32")));
        System.out.println("叁佰贰拾伍元零肆分".equals(NumberUtils.amount2rmb("325.04")));
        System.out.println("肆仟叁佰贰拾壹元整".equals(NumberUtils.amount2rmb("4,321.00")));
        System.out.println("壹分".equals(NumberUtils.amount2rmb("0.01")));

        System.out.println(NumberUtils.amount2rmb("1234,5678,9012.34")
                .equals("壹仟贰佰叁拾肆亿伍仟陆佰柒拾捌万玖仟零壹拾贰元叁角肆分"));
        System.out.println(NumberUtils.amount2rmb("1000,1000,1000.10")
                .equals("壹仟亿零壹仟万零壹仟元壹角"));
        System.out.println(NumberUtils.amount2rmb("9009,9009,9009.99")
                .equals("玖仟零玖亿玖仟零玖万玖仟零玖元玖角玖分"));
        System.out.println(NumberUtils.amount2rmb("5432,0001,0001.01")
                .equals("伍仟肆佰叁拾贰亿零壹万零壹元零壹分"));
        System.out.println(NumberUtils.amount2rmb("1000,0000,1110.00")
                .equals("壹仟亿零壹仟壹佰壹拾元整"));
        System.out.println(NumberUtils.amount2rmb("1010,0000,0001.11")
                .equals("壹仟零壹拾亿零壹元壹角壹分"));
        System.out.println(NumberUtils.amount2rmb("1000,0000,0000.01")
                .equals("壹仟亿元零壹分"));
    }
}