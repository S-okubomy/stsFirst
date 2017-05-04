package com.svm.service;

import com.svm.dto.ResultGeneDto;
import static com.svm.util.FmtUtil.dblToStr;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import com.svm.evalutePack.EvaCalMax1;
import com.svm.unit.GetNGramUnit;
import com.svm.unit.MainRcgaUnit;

public class GetWeightValService {

    public static void main(String[] args) throws Exception {
        
        GetWeightValService getWeightValService = new GetWeightValService();
        
        // ================= 質問解析用の重み係数を作成する  =================
        String modeName = "que";
        getWeightValService.getWeightVal(modeName);
        System.out.println("質問解析 完了");
        
        // ================= 応答解析用の重み係数を作成する  =================
        getWeightValService = new GetWeightValService();
        modeName = "ans";
        getWeightValService.getWeightVal(modeName);
        System.out.println("応答解析 完了");
    }
    
    private void getWeightVal(String modeName) throws Exception {
        
        // 最適化する評価関数名（クラス名）
        String evalMethodName = "com.svm.evalutePack.SvmEvaClass"; // 質問パターンの重み係数算出用
        
        // Projectのトップディレクトリパス取得
        String folderName = System.getProperty("user.dir");
        String copySrcInput = folderName + "\\src\\main\\resources\\inputFile\\";     // コピー元
        String copySrcOutput = folderName + "\\src\\main\\resources\\outputFile\\";     // コピー元
        String copyDest = folderName + "\\src\\main\\resources\\resultSvmFile\\"; //コピー先
        FileSystem fs = FileSystems.getDefault();
        
        // ================= NGramを作成する  =================
        GetNGramUnit getNGramUnit = new GetNGramUnit();
        getNGramUnit.getNGram(modeName);
        //0.5秒待つ
        Thread.sleep( 500 ) ;
        
        // ================= 重み係数を作成する  =================
        MainRcgaUnit mainRcga = new MainRcgaUnit();
        ResultGeneDto result = mainRcga.calGene(evalMethodName, modeName);
        System.out.println("--------------最大値の重み係数結果を再計算 （" + modeName + "）------------------");
        EvaCalMax1 evaCalMax1 = new EvaCalMax1();
        evaCalMax1.evaCalMax(evalMethodName, result.getAc1(), modeName);
        
        System.out.println("---------------------最適化結果（" + modeName + "）------------------");
        String strPrMaxAll = "実値: " + dblToStr(result.getTrueVal1()) +  "  係数： ";
        for (int i = 0; i < result.getAc1().length; i++) {
            strPrMaxAll = strPrMaxAll + dblToStr(result.getAc1()[i])  + "  ";
        }
        
        //0.5秒待つ
        Thread.sleep( 500 ) ;
        
        // ================= ファイルをコピーする  =================
        // que_studyInput.txtをコピー（質問解析用）
        Files.copy(fs.getPath(copySrcInput + modeName + "_studyInput.txt")
                , fs.getPath(copyDest + modeName + "_studyInput.txt"), StandardCopyOption.REPLACE_EXISTING);
        // que_SVMParam.csvをコピー（質問解析用）
        Files.copy(fs.getPath(copySrcOutput + "getStudyManModelTestHist.csv")
                , fs.getPath(copyDest + modeName + "_SVMParam.csv"), StandardCopyOption.REPLACE_EXISTING);
        System.out.println(strPrMaxAll);
        
        //0.5秒待つ
        Thread.sleep( 500 ) ;
    }

}
