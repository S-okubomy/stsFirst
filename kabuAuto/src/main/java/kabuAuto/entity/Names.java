package kabuAuto.entity;

import javax.annotation.Generated;
import kabuAuto.entity.TKabuDataNames._TKabuDataNames;
import kabuAuto.entity.TLoninUserNames._TLoninUserNames;

/**
 * 名前クラスの集約です。
 * 
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.NamesAggregateModelFactoryImpl"}, date = "2014/11/29 21:14:47")
public class Names {

    /**
     * {@link TKabuData}の名前クラスを返します。
     * 
     * @return TKabuDataの名前クラス
     */
    public static _TKabuDataNames tKabuData() {
        return new _TKabuDataNames();
    }

    /**
     * {@link TLoninUser}の名前クラスを返します。
     * 
     * @return TLoninUserの名前クラス
     */
    public static _TLoninUserNames tLoninUser() {
        return new _TLoninUserNames();
    }
}