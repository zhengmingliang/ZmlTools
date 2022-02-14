package top.wys.utils.convert;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.lang.reflect.ParameterizedType;
import java.util.Objects;

import javax.faces.convert.ConverterException;

import top.wys.utils.DataUtils;
import top.wys.utils.ReflectionUtils;

/**
 * Converter interface for input DTO.
 * @since 1.2.2
 */
public interface InputConverter<D> {

    /**
     * Convert to domain.(shallow)
     *
     * @return new domain with same value(not null)
     */
    @SuppressWarnings("unchecked")
    default D convertTo() {
        // Get parameterized type
        ParameterizedType currentType = parameterizedType();

        // Assert not equal
        Objects.requireNonNull(currentType,
            "Cannot fetch actual type because parameterized type is null");

        Class<D> domainClass = (Class<D>) currentType.getActualTypeArguments()[0];

        return transformFrom(this, domainClass);
    }
    default <T> T transformFrom(Object source, Class<T> targetClass) {
        Assert.notNull(targetClass, "Target class must not be null");

        if (source == null) {
            return null;
        }

        // Init the instance
        try {
            // New instance for the target class
            T targetInstance = targetClass.newInstance();
            // Copy properties
            BeanUtils
                    .copyProperties(source, targetInstance, DataUtils.getNullPropertyNames(source));
            // Return the target instance
            return targetInstance;
        } catch (Exception e) {
            throw new ConverterException(
                    "Failed to new " + targetClass.getName() + " instance or copy properties", e);
        }
    }
    /**
     * Update a domain by dto.(shallow)
     *
     * @param domain updated domain
     */
    default void update(D domain) {
        Assert.notNull(this, "source object must not be null");
        Assert.notNull(domain, "domain object must not be null");
        // Set non null properties from source properties to domain properties
        BeanUtils.copyProperties(this,domain , DataUtils.getNullPropertyNames(this));

    }

    /**
     * Get parameterized type.
     *
     * @return parameterized type or null
     */
    default ParameterizedType parameterizedType() {
        return ReflectionUtils.getParameterizedType(InputConverter.class, this.getClass());
    }
}

