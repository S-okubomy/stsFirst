package evalutePack;

import static util.FmtUtil.dblToStr;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import interfaceEva.BaseEvaVal;
import util.ReadFileUtil;


/**
 * @author Administrator
 *
 */
public class SvmEvaClass implements BaseEvaVal{

    public static final String SEIKAI = "T";
    public static final String FUSEIKAI = "F";
    
    /* (非 Javadoc)
     * @see interfaceEva.BaseEvaVal#execute(double)
     */
    public double execute(double[] gaParameter){
        
        // Projectのトップディレクトリパス取得
        String folderName = System.getProperty("user.dir");
        // トップディレクトリパス以降を設定
        folderName = folderName + "\\src\\main\\java\\rcgaBatch1\\batch\\";
        
        //学習データの読み込み
        String csvFileInput = folderName + "nGramOutput.csv";
        LinkedHashMap<String,String[]> studyMap = null;
        try {
            studyMap = ReadFileUtil.readCsvFile(csvFileInput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //初期化
        int vectorSu = studyMap.get("データNo").length - 3; //ベクトル数
        int[] vectorX1 = new int[vectorSu];
        int[] weightParam = new int[vectorSu];
        for(int i = 0; i < vectorSu; i++) { //ベクトルの数
            weightParam[i] = 1;
        }
        
        Map<String, int[]> weightParamMap = new HashMap<String, int[]>();
        for (String key : studyMap.keySet()) {
            if (!"データNo".equals(studyMap.get(key)[0])) {
                if (!weightParamMap.containsKey(studyMap.get(key)[2])) {
                    weightParamMap.put(studyMap.get(key)[2], weightParam.clone());
                }
            }
        }
        
        double evaValue = 0;
        double fxValue = 0;
        int countAll = 0;
        // 重み係数のマップを分類ごとにループし、修正する。
        for (String keyWeightParam : weightParamMap.keySet()) {
            // 学習データを一行ずつ判断する。
            boolean isSeikai = false;
            boolean isFuseikai = false;
            for (int i = 0; i < 10000; i++) { // 重み係数を1回だけではなく繰り返し修正することで正解率上げる
                for (String key : studyMap.keySet()) {
                    if (!"データNo".equals(studyMap.get(key)[0])) {
                        countAll++;
                        //マップの配列を入れ替え
                        for(int ii = 0; ii < vectorSu; ii++) {
                            vectorX1[ii] = Integer.valueOf(studyMap.get(key)[ii + 3]);
                        }
                        // 決定関数を計算（判断基準）
                        fxValue = (double)getNaiseki(weightParamMap.get(keyWeightParam), vectorX1)
                                + gaParameter[0];
                        if (fxValue >= 0) { // 正しいと判断した場合
                            
                            // 不正解ラベル & 分類に一致→重み係数を修正
                            if (FUSEIKAI.equals(studyMap.get(key)[1]) 
                                    && studyMap.get(key)[2].equals(keyWeightParam)) {
                                for(int ii = 0; ii < vectorSu; ii++) {
                                    weightParam[ii] = weightParamMap.get(keyWeightParam)[ii] 
                                            + ((-1) * Integer.valueOf(studyMap.get(key)[ii + 3]));
                                }
                                weightParamMap.put(keyWeightParam, weightParam.clone());
                                // 画面に学習結果（判断）を表示
                                System.out.println("判断NG  No" + studyMap.get(key)[0] + " fx " + fxValue + " 分類  " + keyWeightParam);
         
                                // 正解ラベル & 分類に一致→重み係数そのまま
                            } else if (SEIKAI.equals(studyMap.get(key)[1])
                                    && studyMap.get(key)[2].equals(keyWeightParam)) {
                                evaValue = evaValue + 1;
                                System.out.println("判断OK  No" + studyMap.get(key)[0] + " fx " + fxValue + " 分類 " + keyWeightParam);
                            
                                isSeikai = true;
                                
                            // 正解ラベル & 分類に不一致→重み係数を修正
                            } else if (SEIKAI.equals(studyMap.get(key)[1])
                                    && !studyMap.get(key)[2].equals(keyWeightParam)) {
                                for(int ii = 0; ii < vectorSu; ii++) {
                                    weightParam[ii] = weightParamMap.get(keyWeightParam)[ii] 
                                            + ((-1) * Integer.valueOf(studyMap.get(key)[ii + 3]));
                                }
                                weightParamMap.put(keyWeightParam, weightParam.clone());
                                // 画面に学習結果（判断）を表示
                                System.out.println("判断NG  No" + studyMap.get(key)[0] + " fx " + fxValue + " 分類  " + keyWeightParam);
                            }
                        } else { // 誤りと判断した場合
                            // 不正解ラベル & 分類に一致→重み係数そのまま
                            if (FUSEIKAI.equals(studyMap.get(key)[1]) 
                                    && studyMap.get(key)[2].equals(keyWeightParam)) {
                                evaValue = evaValue + 1;
                                System.out.println("判断OK  No" + studyMap.get(key)[0] + " fx " + fxValue + " 分類 " + keyWeightParam);
                                
                                isFuseikai = true;
                                
                            // 正解ラベル & 分類に一致→重み係数を修正
                            } else if (SEIKAI.equals(studyMap.get(key)[1])
                                    && studyMap.get(key)[2].equals(keyWeightParam)) {
                                for(int ii = 0; ii < vectorSu; ii++) {
                                    weightParam[ii] = weightParamMap.get(keyWeightParam)[ii] 
                                            + ((+1) * Integer.valueOf(studyMap.get(key)[ii + 3]));
                                }
                                weightParamMap.put(keyWeightParam, weightParam.clone());
                                // 画面に学習結果（判断）を表示
                                System.out.println("判断NG  No" + studyMap.get(key)[0] + " fx " + fxValue + " 分類 " + keyWeightParam);
                                
                            // 正解ラベル & 分類に不一致→重み係数そのまま
                            } else if (SEIKAI.equals(studyMap.get(key)[1])
                                    && !studyMap.get(key)[2].equals(keyWeightParam)) {
                                evaValue = evaValue + 1;
                                System.out.println("判断OK  No" + studyMap.get(key)[0] + " fx " + fxValue + " 分類 " + keyWeightParam);
                            }
                        }
                    }
                }
                
                if (isSeikai && isFuseikai) {
                    System.out.println("Break 正解&不正解" + " 分類 " + keyWeightParam);
                    break;
                }
            }
        }
        
        System.out.println("個体の実値 " + evaValue + " 正解率" + dblToStr((evaValue/countAll) * 100) + "%");
        // 重み係数をCSVに出力する。
        outPutWeightValue(folderName, weightParamMap);

        return evaValue;
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
     * 絶対値を計算する
     * @param vectorX
     * @return
     */
    private double getZetaichi(int[] vectorX) {
        //絶対値計算
        double sowaValue = 0;
        for(int ii = 0; ii < vectorX.length; ii++) {
            sowaValue = sowaValue + Math.pow((double)vectorX[ii], 2);
        }

        return Math.sqrt(sowaValue);
    }
    
    private void outPutWeightValue(String folderName, Map<String, int[]> weightParamMap) {
        
        //重み係数をCSVへ書き込み
        String fnameWeight ="outWeightValue";
        StringBuilder weightParamOut = new StringBuilder();
        for (String weightParamKey : weightParamMap.keySet()) {
            weightParamOut.append(weightParamKey + ",");
            for (int i =0; i < weightParamMap.get(weightParamKey).length; i++) {
                weightParamOut.append(Integer.toString(weightParamMap.get(weightParamKey)[i]) + ",");
            }
            weightParamOut.append("\r\n");
        }
        try {
            FileOutputStream fosWeight = new FileOutputStream( folderName + fnameWeight + ".csv");
            OutputStreamWriter oswWeight = new OutputStreamWriter(fosWeight , "UTF-8");
            BufferedWriter bwWeight = new BufferedWriter(oswWeight);
            bwWeight.write(new String(weightParamOut));
            //ファイルクローズ
            bwWeight.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}
