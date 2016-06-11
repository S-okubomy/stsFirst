package kabuAuto.batch;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import kabuAuto.CaluculateDateUtil;
import kabuAuto.entity.TKabuData;
import kabuAuto.service.TKabuDataService;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

public class CopyOfMainKabuDay {


	/*
	 * 株データテーブルにアクセスする
	 */
	public static void main(String[] args) {

        SingletonS2ContainerFactory.setConfigPath("app.dicon");
        SingletonS2ContainerFactory.init();
        S2Container container = SingletonS2ContainerFactory.getContainer();
        TKabuDataService tKabuDataService = (TKabuDataService) container.getComponent(TKabuDataService.class);
        //TKabuData tKabuData = (TKabuData) container.getComponent(TKabuData.class);

        TKabuData tKabuData = new TKabuData();
        CopyOfMainKabuDay mainKabu1 = new CopyOfMainKabuDay();

		// TODO 自動生成されたメソッド・スタブ

		String line ;
		//String reqUrl = "http://www5f.biglobe.ne.jp/~uzy/smis/p1301.htm";
		String reqUrl = "http://stocks.finance.yahoo.co.jp/stocks/history/?code=";

		String yesterdayKabu = CaluculateDateUtil.caluculateDate2(-1);

		//クラスの定義
		Tag tag = new Tag() ;

		try{

			//URLクラスのインスタンスを生成
			//java.net.URL urlObj = new java.net.URL(reqUrl);
			java.net.URL urlObj = new java.net.URL( reqUrl+args[0]+".T" );
			//java.net.URL urlObj = new java.net.URL( reqUrl+args[0] );

			//接続の取得
			java.net.HttpURLConnection urlConn = (java.net.HttpURLConnection)urlObj.openConnection();
			urlConn.setRequestMethod("GET");

			//文字エンコーディング
			java.io.InputStreamReader isr
										= new java.io.InputStreamReader(urlConn.getInputStream(), "UTF-8");

			//入力ストリームを生成
			java.io.BufferedReader br = new java.io.BufferedReader(isr);

			//受信したストリームから値を取り出す
			while (null != (line = br.readLine())) {
				if(line.indexOf("<td>" + yesterdayKabu) != -1){
					tKabuData.kabuDate    = mainKabu1.convertDate(tag.word(line, 3).replace("年", "/").replace("月", "/").replace("日", ""));  //日付
					line = br.readLine(); //一行下へ
					tKabuData.kabuOpen =Long.parseLong(tag.word(line, 1).replace(",", "")); //始値
					line = br.readLine(); //一行下へ
					tKabuData.kabuHigh = Long.parseLong(tag.word(line, 1).replace(",", "")); //高値
					line = br.readLine(); //一行下へ
					tKabuData.kabuLow = Long.parseLong(tag.word(line, 1).replace(",", "")); //安値
					line = br.readLine(); //一行下へ
					tKabuData.kabuClose = Long.parseLong(tag.word(line, 1).replace(",", ""));  //終値
					line = br.readLine(); //一行下へ
					tKabuData.kabuVolume = Long.parseLong(tag.word(line,1).replace(",", ""));  //出来高

					tKabuData.kabuCode = args[0];
					tKabuData.kabuName = args[1];
					tKabuData.Display();
		            tKabuDataService.insertKabuData(tKabuData);
				}
			}

			//後始末
			br.close();
			urlConn.disconnect();

			System.out.println("END\n");

			//１秒待つ
			Thread.sleep( 1000 ) ;

		} catch(Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}

	/**
	 * 日付データを空で無ければ、タイムスタンプ型に変換する。
	 * @param value String型 日付データ
	 * @return タイプスタンプ型
	 * @throws ParseException 日付フォーマットに関する例外
	 */
	private Timestamp convertDate(String value) throws ParseException {
		 if ( value == null || value.length() == 0 ) {
		        return null;
		    } else {
		        return new Timestamp(new SimpleDateFormat("yyyy/MM/dd").parse(value).getTime());
		    }
	}


}
