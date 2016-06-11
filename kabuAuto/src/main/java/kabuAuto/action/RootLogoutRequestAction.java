package kabuAuto.action;

import javax.annotation.Resource;

import kabuAuto.dto.RootLoginDataDto;
import kabuAuto.dto.SaveUserDto;

import org.seasar.framework.aop.annotation.RemoveSession;
import org.seasar.struts.annotation.Execute;


/**
 * 管理者用ログアウト部分のアクションクラス
 * @author t_okubomy
 *
 */
public class RootLogoutRequestAction {

	@Resource
	RootLoginDataDto rootLoginDataDto;

	@Resource
	protected SaveUserDto saveUserDto;

	/**
	 * ログアウトを行う
	 * @return 遷移先パス
	 */
	@Execute(validator = false)
	@RemoveSession(name = {"rootLoginDataDto","saveUserDto"})
	public String index() {
		this.rootLoginDataDto.rootId = null;

        return "/rootLoginRequest/?redirect=true";
	}

}
