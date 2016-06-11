package kabuAuto.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * リクエストプロセッサーを経由したログイン処理用のアノテーション
 * @author t_okubomy
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD})
@Documented
public @interface Auth {

}
