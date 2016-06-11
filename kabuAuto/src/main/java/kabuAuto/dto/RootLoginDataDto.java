package kabuAuto.dto;

import java.io.Serializable;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;


/**
 * 管理者用のログインデータを保持するためのDTO
 * @author t_okubomy
 *
 */
@Component(instance = InstanceType.SESSION)
public class RootLoginDataDto implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * ユーザIDのパラメータ
	 */
	public String rootId;

	public boolean isLogin() {
		return (this.rootId != null);
	}


}
