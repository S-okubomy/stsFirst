package kabuAuto.entity;

import java.sql.Timestamp;
import javax.annotation.Generated;
import org.seasar.extension.jdbc.name.PropertyName;

/**
 * {@link TKabuData}のプロパティ名の集合です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesModelFactoryImpl"}, date = "2014/11/29 21:14:47")
public class TKabuDataNames {

    /**
     * kabuManegeIdのプロパティ名を返します。
     * 
     * @return kabuManegeIdのプロパティ名
     */
    public static PropertyName<Long> kabuManegeId() {
        return new PropertyName<Long>("kabuManegeId");
    }

    /**
     * kabuDateのプロパティ名を返します。
     * 
     * @return kabuDateのプロパティ名
     */
    public static PropertyName<Timestamp> kabuDate() {
        return new PropertyName<Timestamp>("kabuDate");
    }

    /**
     * kabuCodeのプロパティ名を返します。
     * 
     * @return kabuCodeのプロパティ名
     */
    public static PropertyName<String> kabuCode() {
        return new PropertyName<String>("kabuCode");
    }

    /**
     * kabuNameのプロパティ名を返します。
     * 
     * @return kabuNameのプロパティ名
     */
    public static PropertyName<String> kabuName() {
        return new PropertyName<String>("kabuName");
    }

    /**
     * kabuOpenのプロパティ名を返します。
     * 
     * @return kabuOpenのプロパティ名
     */
    public static PropertyName<Long> kabuOpen() {
        return new PropertyName<Long>("kabuOpen");
    }

    /**
     * kabuHighのプロパティ名を返します。
     * 
     * @return kabuHighのプロパティ名
     */
    public static PropertyName<Long> kabuHigh() {
        return new PropertyName<Long>("kabuHigh");
    }

    /**
     * kabuLowのプロパティ名を返します。
     * 
     * @return kabuLowのプロパティ名
     */
    public static PropertyName<Long> kabuLow() {
        return new PropertyName<Long>("kabuLow");
    }

    /**
     * kabuCloseのプロパティ名を返します。
     * 
     * @return kabuCloseのプロパティ名
     */
    public static PropertyName<Long> kabuClose() {
        return new PropertyName<Long>("kabuClose");
    }

    /**
     * kabuVolumeのプロパティ名を返します。
     * 
     * @return kabuVolumeのプロパティ名
     */
    public static PropertyName<Long> kabuVolume() {
        return new PropertyName<Long>("kabuVolume");
    }

    /**
     * @author S2JDBC-Gen
     */
    public static class _TKabuDataNames extends PropertyName<TKabuData> {

        /**
         * インスタンスを構築します。
         */
        public _TKabuDataNames() {
        }

        /**
         * インスタンスを構築します。
         * 
         * @param name
         *            名前
         */
        public _TKabuDataNames(final String name) {
            super(name);
        }

        /**
         * インスタンスを構築します。
         * 
         * @param parent
         *            親
         * @param name
         *            名前
         */
        public _TKabuDataNames(final PropertyName<?> parent, final String name) {
            super(parent, name);
        }

        /**
         * kabuManegeIdのプロパティ名を返します。
         *
         * @return kabuManegeIdのプロパティ名
         */
        public PropertyName<Long> kabuManegeId() {
            return new PropertyName<Long>(this, "kabuManegeId");
        }

        /**
         * kabuDateのプロパティ名を返します。
         *
         * @return kabuDateのプロパティ名
         */
        public PropertyName<Timestamp> kabuDate() {
            return new PropertyName<Timestamp>(this, "kabuDate");
        }

        /**
         * kabuCodeのプロパティ名を返します。
         *
         * @return kabuCodeのプロパティ名
         */
        public PropertyName<String> kabuCode() {
            return new PropertyName<String>(this, "kabuCode");
        }

        /**
         * kabuNameのプロパティ名を返します。
         *
         * @return kabuNameのプロパティ名
         */
        public PropertyName<String> kabuName() {
            return new PropertyName<String>(this, "kabuName");
        }

        /**
         * kabuOpenのプロパティ名を返します。
         *
         * @return kabuOpenのプロパティ名
         */
        public PropertyName<Long> kabuOpen() {
            return new PropertyName<Long>(this, "kabuOpen");
        }

        /**
         * kabuHighのプロパティ名を返します。
         *
         * @return kabuHighのプロパティ名
         */
        public PropertyName<Long> kabuHigh() {
            return new PropertyName<Long>(this, "kabuHigh");
        }

        /**
         * kabuLowのプロパティ名を返します。
         *
         * @return kabuLowのプロパティ名
         */
        public PropertyName<Long> kabuLow() {
            return new PropertyName<Long>(this, "kabuLow");
        }

        /**
         * kabuCloseのプロパティ名を返します。
         *
         * @return kabuCloseのプロパティ名
         */
        public PropertyName<Long> kabuClose() {
            return new PropertyName<Long>(this, "kabuClose");
        }

        /**
         * kabuVolumeのプロパティ名を返します。
         *
         * @return kabuVolumeのプロパティ名
         */
        public PropertyName<Long> kabuVolume() {
            return new PropertyName<Long>(this, "kabuVolume");
        }
    }
}