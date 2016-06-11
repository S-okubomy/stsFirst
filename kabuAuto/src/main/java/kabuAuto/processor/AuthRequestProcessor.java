package kabuAuto.processor;

import java.io.IOException;
import java.lang.annotation.Annotation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kabuAuto.annotation.Auth;
import kabuAuto.createException.NotLoginException;
import kabuAuto.dto.RootLoginDataDto;

import org.apache.struts.action.ActionMapping;
import org.seasar.framework.container.SingletonS2Container;
import org.seasar.struts.action.S2RequestProcessor;
import org.seasar.struts.config.S2ExecuteConfig;
import org.seasar.struts.util.S2ExecuteConfigUtil;


/**
 * リクエストプロセッサーによる認証チェック
 * @author t_okubomy
 *
 */
public class AuthRequestProcessor extends S2RequestProcessor {


	/**
	 * 認証チェックを行うメソッド
	 */
	protected boolean processRoles(HttpServletRequest request,
			HttpServletResponse response, ActionMapping mapping)
			throws IOException, ServletException {

		S2ExecuteConfig executeConfig = S2ExecuteConfigUtil.getExecuteConfig();


		//アノテーションを取得
		Annotation[] annotations = executeConfig.getMethod().getAnnotations();

		for (Annotation annotation : annotations) {
			//アノテーションの型をチェック
			if (annotation.annotationType().equals(Auth.class)) {

				RootLoginDataDto dto = SingletonS2Container.getComponent(RootLoginDataDto.class);

				if (!dto.isLogin()) {
					throw new NotLoginException();
				}
				break;
			}
		}


		return super.processRoles(request, response, mapping);


	}

}



