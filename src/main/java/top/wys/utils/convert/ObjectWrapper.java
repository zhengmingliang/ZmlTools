package top.wys.utils.convert;


public class ObjectWrapper {
    private Object object;

    public ObjectWrapper(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }
    public Object getObjectOrDefault(Object defaultObject) {
        return object == null ? defaultObject : object;
    }

    public String getString() {
        if (object == null) {
            return "";
        }
        return object.toString();
    }

    public String getStringOrDefault(String defaultString) {
        if (object == null) {
            return defaultString;
        }
        return object.toString();
    }


    public Integer getInteger() {
        if (object == null) {
            return 0;
        }
        return Integer.parseInt(object.toString());
    }
    public Integer getIntegerOrDefault(Integer defaultInteger) {
        if (object == null) {
            return defaultInteger;
        }
        return Integer.parseInt(object.toString());
    }

    public Long getLong() {
        if (object == null) {
            return 0L;
        }
        return Long.parseLong(object.toString());
    }

    public Long getLongOrDefault(Long defaultLong) {
        if (object == null) {
            return defaultLong;
        }
        return Long.parseLong(object.toString());
    }

    public Double getDouble() {
        if (object == null) {
            return 0.0;
        }
        return Double.parseDouble(object.toString());
    }
    public Double getDoubleOrDefault(Double defaultDouble) {
        if (object == null) {
            return defaultDouble;
        }
        return Double.parseDouble(object.toString());
    }

    public boolean getBoolean() {
        if (object == null) {
            return false;
        }
        return Boolean.parseBoolean(object.toString());
    }
    public boolean getBooleanOrDefault(Boolean defaultBoolean) {
        if (object == null) {
            return defaultBoolean;
        }
        return Boolean.parseBoolean(object.toString());
    }
}
