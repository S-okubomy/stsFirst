package kabuAuto.batch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;




public class CopyOfMainKabu1 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		String line ;
		//String reqUrl = "http://www5f.biglobe.ne.jp/~uzy/smis/p1301.htm";
		String reqUrl = "http://minkabu.jp/stock/";
		String vol ;

		//クラスの定義
		Tag tag = new Tag() ;
		Spd spd = new Spd() ;

		try{

			//URLクラスのインスタンスを生成
			//java.net.URL urlObj = new java.net.URL(reqUrl);
			//java.net.URL urlObj = new java.net.URL( reqUrl+args[0]+".T" );
			java.net.URL urlObj = new java.net.URL( reqUrl+args[0] );
			//１秒待つ
			Thread.sleep( 1000 ) ;

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
				if(line.indexOf("現在値<br") != -1){
					String lineAfter  = br.readLine();
					vol = tag.word(lineAfter, 2) ;
					spd.close = vol ;
					//System.out.println("終値:"+vol) ;
				}
				if(line.indexOf("<th>出来高</th>") != -1){
					vol = tag.word(line, 4) ;
					spd.volume = vol.replace("株", "") ;
					//System.out.println("出来高:"+vol) ;
				}
				if(line.indexOf("<th>始値</th>") != -1){
					vol = tag.word(line, 4) ;
					spd.open = vol.replace("円", "") ;
					//System.out.println("始値:"+vol) ;
				}
				if(line.indexOf("<th>高値</th>") != -1){
					vol = tag.word(line, 4) ;
					spd.high = vol.replace("円", "") ;
					//System.out.println("高値:"+vol) ;
				}
				if(line.indexOf("<th>安値</th>") != -1){
					vol = tag.word(line, 4) ;
					spd.low = vol.replace("円", "") ;
					//System.out.println("安値:"+vol) ;
				}
				//表示
				System.out.println(line);
			}
			//日付セット
			SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy/MM/dd", Locale.US ) ;
			dateFormat.setTimeZone( TimeZone.getDefault() );
			spd.date = new String(dateFormat.format( new Date()) );
            spd.Display();
			spd.FileWrite(args[0]);

			//後始末
			br.close();
			urlConn.disconnect();

			System.out.println("END\n");
		} catch(Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}



}
