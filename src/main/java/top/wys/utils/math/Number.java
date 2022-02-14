/**
 * Created by 郑明亮 on 2022/2/7 0:28.
 */
package top.wys.utils.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * <p> this is your description</p>
 *
 * @author 郑明亮
 * @version 1.0.0
 * @time 2022/2/7 0:28
 */
public class Number extends BigDecimal {

    public static final Number ZERO =
            new Number(BigInteger.ZERO,0,1);
    public Number(BigInteger val) {
        super(val);
    }

    public Number(String val) {
        super(val);
    }

    public Number(BigInteger unscaledVal, int scale) {
        super(unscaledVal, scale);
    }

    public Number(int val) {
        super(val);
    }

    public Number(long val) {
        super(val);
    }

    public Number(BigInteger intVal, int scale, int prec) {
        super(intVal,scale,new MathContext(prec));
    }

    public Number(String val, int scale) {
        super(val);
        setScale(scale);
    }
    public Number(String val, MathContext mc) {
        super(val, mc);
    }

    public BigDecimal add(String number) {
        return super.add(new BigDecimal(number));
    }

    public BigDecimal add(String... numbers) {
        if (numbers != null) {
            for (String number : numbers) {
                add(number);
            }
        }

        return super.add(BigDecimal.ZERO);
    }

    public static Number valueOf(BigDecimal bigDecimal){
        return new Number(bigDecimal.unscaledValue(), bigDecimal.scale(), bigDecimal.precision());
    }

    public  String toScaleString(int scale){
        this.setScale(scale);
        return toPlainString();
    }

    public  String toScaleString(int scale,RoundingMode roundingMode){
        this.setScale(scale,roundingMode);
        return toPlainString();
    }
}
