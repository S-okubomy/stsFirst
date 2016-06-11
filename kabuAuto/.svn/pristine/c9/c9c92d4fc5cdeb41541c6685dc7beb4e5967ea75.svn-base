package kabuAuto.service;

import static kabuAuto.entity.TLoninUserNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.util.List;

import javax.annotation.Generated;

import kabuAuto.entity.TLoninUser;
import kabuAuto.form.RootLoginForm;

import org.seasar.extension.jdbc.where.SimpleWhere;

/**
 * {@link TLoninUser}のサービスクラスです。
 *
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/11/29 21:14:53")
public class TLoninUserService extends AbstractService<TLoninUser> {

    /**
     * 識別子でエンティティを検索します。
     *
     * @param userManegeId
     *            識別子
     * @return エンティティ
     */
    public TLoninUser findById(Long userManegeId) {
        return select().id(userManegeId).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     *
     * @return エンティティのリスト
     */
    public List<TLoninUser> findAllOrderById() {
        return select().orderBy(asc(userManegeId())).getResultList();
    }

    /**
     * ユーザテーブルによりユーザ情報を照会するメソッド
     * @param rootLoginForm  ログインフォームの情報
     * @return
     */
    public boolean isExistedRootUser(RootLoginForm rootLoginForm) {
    	long count = select()
	    			.where(new SimpleWhere()
	    			.eq(userLoginId(), rootLoginForm.rootId)
	    			.eq(password(), rootLoginForm.password))
	    			.orderBy(userManegeId().toString())
					.getCount();

    	return  count == 1;
    }

    public TLoninUser findUser(RootLoginForm rootLoginForm) {
        return 	select()
        		.where(new SimpleWhere()
        		.eq(userLoginId(),rootLoginForm.rootId))
        		.orderBy(userManegeId().toString())
        		.getSingleResult();
    }
}