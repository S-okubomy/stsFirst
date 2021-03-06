package rcgaBatch1.batch;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import net.java.sen.SenFactory;
import net.java.sen.StringTagger;
import net.java.sen.dictionary.Token;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import rcgaBatch1.dto.AnsModelDto;
import rcgaBatch1.dto.InitDto;
import rcgaBatch1.dto.StudyModelDto;
import util.GetNetInfoUtil;
import util.ReadFileUtil;
import util.SelectWordUtil;

/**
 * GAの学習結果より、ネットから情報を収集する
 * @author Administrator
 *
 */
public class GetJitsuDateForAns {

	public static final String SEIKAI = "T";
	public static final String FUSEIKAI = "F";

	public static void main(String[] args) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		GetJitsuDateForAns mainGetJitsuDate = new GetJitsuDateForAns();

		//
		String[] args1 = new String[2];
		mainGetJitsuDate.getJitsuDate(args1);
	}

	public List<AnsModelDto> getJitsuDate(String[] args) throws Exception {

		//学習データ取得先 URL指定
	    String reqUrl = "https://search.goo.ne.jp/web.jsp?MT=";
        
        args[0] = "プレステの発売日はいつ？";
        String reqUrlAll = reqUrl + args[0] + "&mode=0&sbd=goo001&IE=UTF-8&OE=UTF-8";
        //学習先のHTMLリスト
        List<String> studyHtmlList = new ArrayList<String>();
        studyHtmlList = GetNetInfoUtil.getStudyHtmlList(reqUrlAll);

		System.out.println("以下、検索元URL");
		System.out.println(reqUrlAll);
		System.out.println("以下、URLリスト（検索結果）");
		for (String htmlUrl : studyHtmlList) {
			System.out.println(htmlUrl);
		}
		
        // Projectのトップディレクトリパス取得
        String folderName = System.getProperty("user.dir");
        // トップディレクトリパス以降を設定
        folderName = folderName + "\\src\\main\\java\\rcgaBatch1\\batch\\";

		//重み係数の読み込み
		String csvWeightValueFileInput = folderName + "ans_outWGVal.csv";
		LinkedHashMap<String,String[]> weightValueMap = ReadFileUtil.readCsvCom(csvWeightValueFileInput);

		//GA学習結果の読み取り
		String csvStudyResultInput = folderName + "getSVMParam.csv";
		LinkedHashMap<String,String[]> studyResultMap = ReadFileUtil.readCsvCom(csvStudyResultInput);
		String[] gaResultArray = studyResultMap.get("2").clone();

		//素性ベクトル作成用
		String soseiVecterSakusei = folderName + "ans_studyInput.txt";
		LinkedHashMap<String,String[]> soseiVecterSakuseiMap = ReadFileUtil.readCsvCom(soseiVecterSakusei);

		//出力ファイル
		String strOutputFile = folderName + "outputJitsuForAns.txt";
		BufferedWriter newFileStream = new BufferedWriter(new FileWriter(strOutputFile));

		Pattern pUrl = Pattern.compile("(.pdf)");
		Matcher matcherUrl;
		String[] sujoVector;
		
		// 回答結果のList格納用
		List<AnsModelDto> ansModelList = new ArrayList<AnsModelDto>();

		try{
			for(String studyHtml : studyHtmlList) {
				matcherUrl = pUrl.matcher(studyHtml);
				if (!matcherUrl.find()) {
					//URLにアクセス
					Document document = Jsoup.connect(studyHtml).get();
					//ネット情報を分割して配列に
					Pattern p = Pattern.compile("[。.]+");
					String[] rsltNetInfo = p.split(document.text());
						for (int iCount =0; iCount < rsltNetInfo.length; iCount++) {
						    // 正規表現でフィルター（文章の前後にスペースを含む行を除く    "^\\x01-\\x7E"で1バイト文字以外を探す）
						    // 上記以外の文章を対象にする。
						    if (!rsltNetInfo[iCount]
						            .matches(".*([a-zA-Z0-9]|[^\\x01-\\x7E]).*\\ ([a-zA-Z0-9]|[^\\x01-\\x7E]).*")) {
	                            sujoVector = getSujoVector(soseiVecterSakuseiMap, rsltNetInfo[iCount]);
	                            // 振り分け結果を出力
	                            outFuriwakeResult(sujoVector, weightValueMap, gaResultArray
	                                    , rsltNetInfo[iCount], newFileStream, ansModelList);
						    }
						}
				}
			}
		} catch (IOException e){
			e.printStackTrace();
			System.out.println("ファイル書き込み失敗");
		} finally {
			try {
				// ストリームは必ず finally で close
				if (newFileStream != null) {
				    newFileStream.close();
				    
		            Collections.sort(ansModelList, new SortAnsModelList());
		            System.out.println("並び替え後");
		            // 正規表現でフィルター（文章の前後にスペースを含む行を除く    "^\\x01-\\x7E"で1バイト文字以外を探す）
		            ansModelList.stream()
		                        .filter(ansModel -> !ansModel.getAnsSentence()
		                                .matches(".*([a-zA-Z0-9]|[^\\x01-\\x7E]).*\\ ([a-zA-Z0-9]|[^\\x01-\\x7E]).*"))
		                        .forEach(ansModel -> System.out.println("回答分類: " + ansModel.getAnsBunrui() 
		                                    + " fx= " + ansModel.getFxValue() 
		                                    + " 文章: " + ansModel.getAnsSentence()));
					System.out.println("出力完了");
					
					return ansModelList;
				}
			} catch (IOException e) {
			}
		}
		
		return ansModelList;
	}
	
	/**
	 * 振り分け結果を出力する。
	 * @param sujoVector
	 * @param weightValueMap
	 * @param gaResultArray
	 * @param rsltSentence
	 * @param newFileStream
	 * @throws IOException
	 */
	private void outFuriwakeResult(String[] sujoVector
	        , LinkedHashMap<String,String[]> weightValueMap, String[] gaResultArray
	        , String rsltSentence, BufferedWriter newFileStream
	        , List<AnsModelDto> ansModelList) throws IOException {
        // 振り分け結果を出力
        for (String key : weightValueMap.keySet()) {
             //配列を作りなおし
           String[] weightValueMapArray = new String[weightValueMap.get(key).length -1];
           for(int ii = 0; ii < weightValueMap.get(key).length -1; ii++) {
               weightValueMapArray[ii] = weightValueMap.get(key)[ii + 1];
           }
           
           StudyModelDto studyModelDto = isFuriwake(sujoVector, weightValueMapArray
                                           , Double.parseDouble(gaResultArray[3]));
           
           if (SEIKAI.equals(studyModelDto.getHanteiJoho()) 
                   && studyModelDto.getFxValue() > 100.0) {
               //ファイルへの書き込み
               System.out.println("該当あり    回答分類: " + weightValueMap.get(key)[0] + " fx= " + studyModelDto.getFxValue() 
                                   + " 文章: " + rsltSentence);
               //ファイルへの書き込み
               newFileStream.write(rsltSentence);
               newFileStream.newLine();
               newFileStream.flush();
               
               // 回答結果をListに格納
               AnsModelDto ansModelDto = new AnsModelDto();
               ansModelDto.setHanteiJoho(studyModelDto.getHanteiJoho());
               ansModelDto.setAnsBunrui(weightValueMap.get(key)[0]);
               ansModelDto.setFxValue(studyModelDto.getFxValue());
               ansModelDto.setAnsSentence(rsltSentence);
               ansModelList.add(ansModelDto);
               
           } else {
//               System.out.println("該当 なし   回答分類: " + weightValueMap.get(key)[0] + " fx= " + studyModelDto.getFxValue()
//                                   + " 文章: " + rsltSentence);
           }
       }
	}
	
	

	/**
	 * 素性ベクトルを返す
	 * @param soseiVecterSakuseiMap
	 * @param strNetInfo
	 * @return
	 * @throws Exception
	 */
	private String[] getSujoVector(LinkedHashMap<String,String[]> soseiVecterSakuseiMap, String strNetInfo) throws Exception {


        // この3行で解析できる
        StringTagger tagger = SenFactory.getStringTagger(null);
        List<Token> tokens = new ArrayList<Token>();

        // 1-gram
        StringBuilder oneGramTitle = new StringBuilder();
        for (String key : soseiVecterSakuseiMap.keySet()) {
            String studyLine = soseiVecterSakuseiMap.get(key)[3];
            tagger.analyze(studyLine, tokens);
            for (Token token : tokens) {
                oneGramTitle.append(token.getSurface() + ",");
            }
        }

        // 2-gram（単語）
        StringBuilder twoGramTitle = new StringBuilder();
        for (String key : soseiVecterSakuseiMap.keySet()) {
            String studyLine = soseiVecterSakuseiMap.get(key)[3];
            tagger.analyze(studyLine, tokens);
            for (int i = 0; i < tokens.size() -1; i++) {
                twoGramTitle.append(tokens.get(i).getSurface()); // 1単語目の出力
                twoGramTitle.append(tokens.get(i + 1).getSurface() + ","); // 連結 2単語目の出力
            }
        }

        // 2-gram（単語/品詞）
        StringBuilder tangoHinshi = new StringBuilder();
        for (String key : soseiVecterSakuseiMap.keySet()) {
            String studyLine = soseiVecterSakuseiMap.get(key)[3];
            tagger.analyze(studyLine, tokens);
            for (int i = 0; i < tokens.size() -1; i++) {
                tangoHinshi.append(tokens.get(i).getSurface());  // 1単語目の出力
                tangoHinshi.append("/");
                tangoHinshi.append(SelectWordUtil.selectWord(tokens.get(i+1).getMorpheme().getPartOfSpeech(), "", "-") + ",");  // 連結 2単語目の出力
            }
        }

        // 以下、素性ベクトル作成
        String[] tmpOneGramTitle = new String(oneGramTitle).split(",");
        String[] tmpTwoGramTitle = new String(twoGramTitle).split(",");
        String[] tmpTangoHinshiGramTitle = new String(tangoHinshi).split(",");
        StringBuilder tmpRenketsu;

        String studyLine = strNetInfo;
        tagger.analyze(studyLine, tokens);

        boolean isVectorFlag;
        //1gram チェック
        StringBuilder oneGramSujoVector = new StringBuilder();
        for (String oneGram : tmpOneGramTitle) {
            isVectorFlag = false;
            for (Token token : tokens) {
                if (oneGram.equals(token.getSurface())) {
                    oneGramSujoVector.append("1,");
                    isVectorFlag = true;
                    break;
                }
            }
            if (!isVectorFlag) {
                oneGramSujoVector.append("0,");
            }
        }

        //2gram チェック
        StringBuilder twoGramSujoVector = new StringBuilder();
        for (String twoGram : tmpTwoGramTitle) {
            isVectorFlag = false;
            for (int i = 0; i < tokens.size() -1; i++) {
                tmpRenketsu = new StringBuilder();
                tmpRenketsu.append(tokens.get(i).getSurface()); // 1単語目の出力
                tmpRenketsu.append(tokens.get(i + 1).getSurface()); // 連結 2単語目の出力
                if (twoGram.equals(new String(tmpRenketsu))) {
                    twoGramSujoVector.append("1,");
                    isVectorFlag = true;
                    break;
                }
            }
            if (!isVectorFlag) {
                twoGramSujoVector.append("0,");
            }
        }

        //2-gram（単語/品詞）
        StringBuilder tangoHinshiSujoVector = new StringBuilder();
        for (String TangoHinshiGram : tmpTangoHinshiGramTitle) {
            isVectorFlag = false;
            for (int i = 0; i < tokens.size() -1; i++) {
                tmpRenketsu = new StringBuilder();
                tmpRenketsu.append(tokens.get(i).getSurface()); // 1単語目の出力
                tmpRenketsu.append("/");
                tmpRenketsu.append(SelectWordUtil.selectWord(tokens.get(i+1).getMorpheme().getPartOfSpeech(), "", "-"));  // 連結 2単語目の出力
                if (TangoHinshiGram.equals(new String(tmpRenketsu))) {
                    tangoHinshiSujoVector.append("1,");
                    isVectorFlag = true;
                    break;
                }
            }
            if (!isVectorFlag) {
                tangoHinshiSujoVector.append("0,");
            }
        }

        // ベクトルを全てまとめる
        StringBuilder allSujoVector = new StringBuilder();
        allSujoVector.append(oneGramSujoVector);
        allSujoVector.append(twoGramSujoVector);
        allSujoVector.append(twoGramSujoVector);

        return new String(allSujoVector).split(",");
	}


    /**
     * 正解データかどうか振り分ける
     * @param gaParameterKotai（GAが文章を述語と振り分けるために算出した係数）
     * @param getHinshiTmp
     * @return　述語に振り分けたらTrue（正解データと判定）, それ以外はFalse
     */
    private StudyModelDto isFuriwake(String[] allSujoVector, String[] weightParam, double gaParameter) {

        int[] intSujoVector = new int[allSujoVector.length];
        int[] intWeightParam = new int[allSujoVector.length];

        //配列をint型へ変換
        for(int ii = 0; ii < allSujoVector.length -1; ii++) {
            intSujoVector[ii] = Integer.valueOf(allSujoVector[ii]);
        }

        //配列をint型へ変換
        for(int ii = 0; ii < allSujoVector.length -1; ii++) {
            intWeightParam[ii] = Integer.valueOf(weightParam[ii]);
        }

        double fxValue = (double)getNaiseki(intWeightParam, intSujoVector)
                            + gaParameter;
        
        StudyModelDto studyModelDto = new StudyModelDto();
        if (fxValue >= 0) {
            studyModelDto.setFxValue(fxValue);
            studyModelDto.setHanteiJoho(SEIKAI);
        } else { // fx が負の場合
           studyModelDto.setFxValue(fxValue);
           studyModelDto.setHanteiJoho(FUSEIKAI);
        }
        
        return studyModelDto;
    }

	/**
	 * 内積を計算する
	 * @param vectorX1
	 * @param vectorX2
	 * @return 内積
	 */
	private int getNaiseki(int[] vectorX1, int[] vectorX2) {
		//内積
		int naisekiValue = 0;
		for(int ii = 0; ii < vectorX1.length; ii++) {
			naisekiValue = naisekiValue + vectorX1[ii] * vectorX2[ii];
    	}

		return naisekiValue;
	}
	
	/**
	 * ソート用クラス
	 * @author Administrator
	 *
	 */
	private class SortAnsModelList implements Comparator<AnsModelDto> {
        public static final int ASC = 1;   //昇順 (1.2.3....)
        public static final int DESC = -1; //降順 (3.2.1....)
        
        @Override
        public int compare(AnsModelDto ansModel1, AnsModelDto ansModel2) {
            
            // compareメソッド : 引数1=引数2→0、引数1<引数2→-1、引数1>引数2→1
            
            // 降順
            return DESC * Double.compare(ansModel1.getFxValue(), ansModel2.getFxValue());
        }
	}



}
