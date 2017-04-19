package rcgaBatch1.batch;

import rcgaBatch1.dto.ResultGeneDto;
import static util.FmtUtil.dblToStr;
import evalutePack.EvaCalMax1;

public class testExe1 {

    public static void main(String[] args) throws Exception {
        // 最適化する評価関数名（クラス名）
//        String evalMethodName = "evalutePack.SvmEvaForAns"; // 回答パターンの重み係数算出用
        String evalMethodName = "evalutePack.SvmEvaClass"; // 質問パターンの重み係数算出用
//        String evalMethodName = "evalutePack.testEvaClass1";
        
        MainRcga mainRcga = new MainRcga();
        ResultGeneDto result = mainRcga.calGene(evalMethodName);

        // 最大値の結果を再計算
        System.out.println("--------------最大値の結果を再計算------------------");
        EvaCalMax1 evaCalMax1 = new EvaCalMax1();
        evaCalMax1.evaCalMax(evalMethodName, result.getAc1());
        
        System.out.println("---------------------最適化結果--------------------");
        String strPrMaxAll = "実値: " + dblToStr(result.getTrueVal1()) +  "  係数： ";
        for (int i = 0; i < result.getAc1().length; i++) {
            strPrMaxAll = strPrMaxAll + dblToStr(result.getAc1()[i])  + "  ";
        }
        
        System.out.println(strPrMaxAll);
    }

}
