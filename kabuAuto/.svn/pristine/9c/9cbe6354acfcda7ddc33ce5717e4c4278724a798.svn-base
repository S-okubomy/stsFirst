package kabuAuto.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * TKabuDataエンティティクラス
 *
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2014/11/27 21:34:11")
public class TKabuData implements Serializable {

    private static final long serialVersionUID = 1L;

    /** kabuManegeIdプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 10, nullable = false, unique = true)
    public Long kabuManegeId;

    /** kabuDateプロパティ */
    @Column(nullable = false, unique = false)
    public Timestamp kabuDate;

    /** kabuCodeプロパティ */
    @Column(length = 100, nullable = false, unique = false)
    public String kabuCode;

    /** kabuNameプロパティ */
    @Column(length = 200, nullable = false, unique = false)
    public String kabuName;

    /** kabuOpenプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Long kabuOpen;

    /** kabuHighプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Long kabuHigh;

    /** kabuLowプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Long kabuLow;

    /** kabuCloseプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Long kabuClose;

    /** kabuVolumeプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Long kabuVolume;


    public void Display(){
		System.out.println("--------- 書き込み ----------") ;
		System.out.println("日付："+kabuDate ) ;
		System.out.println("株コード："+kabuCode ) ;
		System.out.println("銘柄："+kabuName ) ;
		System.out.println("出来高："+kabuVolume) ;
		System.out.println("始値："+kabuOpen ) ;
		System.out.println("高値："+kabuHigh ) ;
		System.out.println("安値："+kabuLow ) ;
		System.out.println("終値："+kabuClose ) ;
		System.out.println("------------------------") ;
	}


}