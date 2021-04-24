package top.wys.utils.convert;


import top.wys.utils.DataUtils;

/**
 * Converter interface for output DTO.
 *
 * <b>The implementation type must be equal to DTO type</b>
 *
 * @param <DtoT> the implementation class type
 * @param <D> domain type
 * @since 1.2.2
 */
public interface OutputConverter<DtoT extends OutputConverter<DtoT, D>, D> {

    /**
     * Convert from domain.(shallow)
     *
     * @param domain domain data
     * @return converted dto data
     */
    @SuppressWarnings("unchecked")

    default <T extends DtoT> T convertFrom(D domain) {
        org.springframework.beans.BeanUtils
                .copyProperties(domain, this, DataUtils.getNullPropertyNames(domain));
        return (T) this;
    }
}
