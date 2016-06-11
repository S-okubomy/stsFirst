package kabuAuto.batch;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Spd {

	String date ;
	String volume ;
	String open ;
	String high ;
	String low ;
	String close ;

	void Display(){
		System.out.println("--------- SPD ----------") ;
		System.out.println("日付："+date ) ;
		System.out.println("出来高："+volume) ;
		System.out.println("始値："+open ) ;
		System.out.println("高値："+high ) ;
		System.out.println("安値："+low ) ;
		System.out.println("終値："+close ) ;
		System.out.println("------------------------") ;
	}

	void FileWrite(String code){

		try{

			int i, j ;
			String[] data = new String[1024] ; //データ格納領域

			//既存の株価データファイルを読み込む
			FileInputStream fis = new FileInputStream( "C://pleiades/workspace/kabuAuto/src/main/java/kabuAuto/batch/" + code + ".csv" );
			InputStreamReader ir = new InputStreamReader(fis , "MS932");
			BufferedReader br = new BufferedReader(ir);

			//読み込み
			for(i=0 ;; i++){
				//System.out.println("i="+i);
				data[i] = br.readLine();
				//ファイル終了みたい
				if(data[i] == null){
					break ;
				}
			}

			//ファイルクローズ
			br.close();
			ir.close();
			fis.close();

			//出力用ファイルのオープン
			FileOutputStream fos = new FileOutputStream( "C://pleiades/workspace/kabuAuto/src/main/java/kabuAuto/batch/" + code + ".csv");
			OutputStreamWriter osw = new OutputStreamWriter(fos , "MS932");
			BufferedWriter bw = new BufferedWriter(osw);

			for(j=0 ;; j++){
				//更新対象ファイルが256行以上だったら古いデータをとばす（削除）
				if(j == 3 && i >= 256){
					j ++ ;
				}

				//過去のデータ書き込み
				if(data[j] != null){
					bw.write(data[j]) ;
					bw.write("\r\n") ;
				} else {  //最新のデータ書き込み
					String msg = date ;
					bw.write(msg);
					msg = "," ;
					bw.write(msg);

					msg = "\"" ;
					bw.write(msg);
					msg = volume ;
					bw.write(msg);
					msg = "\"," ;
					bw.write(msg);

					msg = "\"" ;
					bw.write(msg);
					msg = open ;
					bw.write(msg);
					msg = "\"," ;
					bw.write(msg);

					msg = "\"" ;
					bw.write(msg);
					msg = high ;
					bw.write(msg);
					msg = "\"," ;
					bw.write(msg);

					msg = "\"" ;
					bw.write(msg);
					msg = low ;
					bw.write(msg);
					msg = "\"," ;
					bw.write(msg);

					msg = "\"" ;
					bw.write(msg);
					msg = close ;
					bw.write(msg);
					msg = "\"\r\n" ;
					bw.write(msg);

					//ファイルクローズ
					bw.close();
					osw.close();
					fos.close();
					break ;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
