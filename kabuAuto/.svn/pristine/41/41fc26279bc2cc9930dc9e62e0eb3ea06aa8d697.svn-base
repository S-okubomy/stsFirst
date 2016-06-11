package kabuAuto.entity;

import java.io.Serializable;
import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * TLoninUserエンティティクラス
 * 
 */
@Entity
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.EntityModelFactoryImpl"}, date = "2014/11/29 21:14:41")
public class TLoninUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /** userManegeIdプロパティ */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(precision = 10, nullable = false, unique = true)
    public Long userManegeId;

    /** userLoginIdプロパティ */
    @Column(length = 30, nullable = false, unique = true)
    public String userLoginId;

    /** passwordプロパティ */
    @Column(length = 30, nullable = false, unique = false)
    public String password;

    /** nameプロパティ */
    @Column(length = 40, nullable = false, unique = false)
    public String name;

    /** classificationIdプロパティ */
    @Column(precision = 10, nullable = false, unique = false)
    public Long classificationId;
}