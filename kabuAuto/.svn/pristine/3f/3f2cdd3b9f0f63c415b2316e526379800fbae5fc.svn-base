package kabuAuto.service;

import static kabuAuto.entity.TKabuDataNames.*;
import static org.seasar.extension.jdbc.operation.Operations.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.annotation.Generated;

import kabuAuto.dto.CalListDto;
import kabuAuto.entity.TKabuData;

/**
 * {@link TKabuData}のサービスクラスです。
 *
 */
@Generated(value = {"S2JDBC-Gen 2.4.46", "org.seasar.extension.jdbc.gen.internal.model.ServiceModelFactoryImpl"}, date = "2014/11/27 21:34:24")
public class TKabuDataService extends AbstractService<TKabuData> {

    /**
     * 識別子でエンティティを検索します。
     *
     * @param kabuManegeId
     *            識別子
     * @return エンティティ
     */
    public TKabuData findById(Long kabuManegeId) {
        return select().id(kabuManegeId).getSingleResult();
    }

    /**
     * 識別子の昇順ですべてのエンティティを検索します。
     *
     * @return エンティティのリスト
     */
    public List<TKabuData> findAllOrderById() {
        return select().orderBy(asc(kabuManegeId())).getResultList();
    }


    /**
     * データベースへ登録情報を書き込む
     * @param itemRegistForm フォームファイル
     * @return 書き込み成功 True、失敗false
     * @throws SQLException
     * @throws ParseException
     */
    public boolean insertKabuData(TKabuData tKabuData) throws SQLException, ParseException {
		return insert(tKabuData) == 1;
	}


	/**
	 * 標準偏差、Aveをデータベースよりもとめる
	 */
	public CalListDto calbol(String kabuCode) {
		return	 jdbcManager
						.selectBySql(CalListDto.class,
								"select *, avg(kabu_close) as 'trans_ave20', stddev(kabu_close) as 'sigma20' from ( "
								+ 		"select distinct kabu_date, kabu_code, kabu_close from t_kabu_data "
								+ 		"where kabu_date <= DATE_SUB(NOW(), INTERVAL 0 DAY) "
								+ 		"and kabu_code = ? "
								+ 		"order by kabu_date desc "
								+		"limit 20 offset 0 ) as sub_table "
								+	"group by kabu_code" , kabuCode)
			            .getSingleResult();
	}


	/**
	 * データベースの株コードのリストを返す
	 */
	public  List<CalListDto> findKabuCodeList() {
		return	 jdbcManager
						.selectBySql(CalListDto.class,
								"select "
								+ 		"kabu_code,kabu_name "
								+ "from "
								+ 		"t_kabu_data "
								+ "group by kabu_code "
								+ "order by kabu_code asc"
								)
						.getResultList();
	}


	/**
	 * GAより求めた係数を代入する
	 */
	public CalListDto gaCalbol(int calTerm, String day, String kabuCode, String baseDay) {
		return	 jdbcManager
						.selectBySql(CalListDto.class,
								"select *, avg(kabu_close) as 'trans_ave20', stddev(kabu_close) as 'sigma20' from ( "
								+ 		"select distinct kabu_date, kabu_code, kabu_close from t_kabu_data "
								+ 		"where kabu_date <= DATE_SUB(?, INTERVAL ? DAY) "
								+ 		"and kabu_code = ? "
								+ 		"order by kabu_date desc "
								+		"limit ? offset 0 ) as sub_table "
								+	"group by kabu_code" , baseDay,day,kabuCode,calTerm)
			            .getSingleResult();
	}


}