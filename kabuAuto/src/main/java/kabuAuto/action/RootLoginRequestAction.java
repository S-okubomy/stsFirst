package kabuAuto.action;

import javax.annotation.Resource;

import kabuAuto.dto.RootLoginDataDto;
import kabuAuto.dto.SaveUserDto;
import kabuAuto.form.RootLoginForm;
import kabuAuto.service.TLoninUserService;

import org.seasar.framework.beans.util.Beans;
import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;

/**
 * ログイン処理を行うアクションクラス
 * @author t_okubomy
 *
 */
public class RootLoginRequestAction {

	/*
	 * アクションフォーム
	 */
	@Resource
	@ActionForm
	protected RootLoginForm rootLoginForm;

	/*
	 * 認証情報を格納するセッションスコープのDTO
	 */
	@Resource
	protected RootLoginDataDto rootLoginDataDto;

	/*
	 * ログイン情報取得のためのサービスクラス
	 */
	@Resource
	protected TLoninUserService tLoninUserService;

	/*
	 * ユーザ情報を保存して表示するため
	 */
	@Resource
	protected SaveUserDto saveUserDto;




	/**
	 *ログイン画面を表示する実行メソッド
	 * @return  遷移パス
	 */
	@Execute(validator=false)
	public String index(){
		return "rootLoginRequest.jsp";
	}


	/**
	 * ログイン
	 * @return
	 */
	@Execute(validator = false)
	public String loginRequest() {
		//ログイン画面で入力されたユーザ名とパスワードでユーザを検索に変更したい

		if (tLoninUserService.isExistedRootUser(rootLoginForm)) {
			Beans.copy(this.rootLoginForm, this.rootLoginDataDto).execute();
			saveUserDto.userName = tLoninUserService.findUser(rootLoginForm).name;
			saveUserDto.loginId = tLoninUserService.findUser(rootLoginForm).userLoginId;
			saveUserDto.userManegeId = tLoninUserService.findUser(rootLoginForm).userManegeId.toString();

			//ログイン後に表示するtopメニュー画面に遷移
			return "/kabuCalList/?redirect=true";

		} else {
			//ログイン失敗なのでもう一度ログイン画面に遷移
			return "/rootLoginRequest/?redirect=true";
		}

	}


}
