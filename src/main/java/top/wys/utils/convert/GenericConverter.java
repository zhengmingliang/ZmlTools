/*
 * Created by 郑明亮 on 2021/4/23 15:05.
 */

//

package top.wys.utils.convert;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * <ol>
 *
 * </ol>
 *
 * @author 郑明亮
 * @version 1.0
 * @date 2021/4/4 15:05
 * @email mpro@vip.qq.com
 */
public interface GenericConverter<I, O> extends Function<I, O> {

    default O convert(final I input) {
        O output = null;
        if (input != null) {
            output = this.apply(input);
        }
        return output;
    }

    default List<O> convert(final List<I> input) {
        List<O> output = new ArrayList<O>();
        if (input != null) {
            output = input.stream().map(this::apply).collect(toList());
        }
        return output;
    }

}
