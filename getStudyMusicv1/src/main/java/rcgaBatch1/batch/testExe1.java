package rcgaBatch1.batch;

import rcgaBatch1.dto.ResultGeneDto;

import static util.FmtUtil.dblToStr;

public class testExe1 {

    public static void main(String[] args) {

        MainRcga mainRcga = new MainRcga();
        ResultGeneDto result = mainRcga.calGene("evalutePack.SvmEvaClass");

        System.out.println("---------------------最適化結果--------------------");
        System.out.println("実値: " + dblToStr(result.getTrueVal1()) +  "  係数： "
                + dblToStr(result.getAc1()[0]) + "  " + dblToStr(result.getAc1()[1]) + "  "
                + dblToStr(result.getAc1()[2]) + "  " + dblToStr(result.getAc1()[3]));
        
    }
    
    
    

}
