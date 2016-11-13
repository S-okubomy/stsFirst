package evalutePack;


import interfaceEva.BaseEvaVal;


/**
 * @author Administrator
 *
 */
public class prod1Eva1 implements BaseEvaVal{

    
    /* (非 Javadoc)
     * @see interfaceEva.BaseEvaVal#execute(double)
     */
    public double execute(double[] gaParameter){
        
        String [] gp1 = new String[gaParameter.length];
        
        if (gaParameter[0] > 0.5) {
            gp1 [0] = "雨";
        }
        if (gaParameter[1] > 0.5) {
            gp1 [1] = "傘有";
        }
        
        if (gaParameter[2] > 0.5) {
            gp1 [2] = "満員電車";
        }
        
        if (gaParameter[3] > 0.5) {
            gp1 [3] = "大雨";
        }
        
        if (gaParameter[4] > 0.5) {
            gp1 [4] = "小雨";
        }
        
        if (gaParameter[5] > 0.5) {
            gp1 [5] = "適温";
        }
        
        if (gaParameter[6] > 0.5) {
            gp1 [6] = "寒い";
        }
        
        if (gaParameter[7] > 0.5) {
            gp1 [7] = "熱い";
        }
        
        
        double evaValue = 0;
        
        if ("雨".equals(gp1[0])) {
            evaValue = evaValue - 2.0;
            if ("傘有".equals(gp1[1])) {
                evaValue = evaValue + 1.0;
                if ("適温".equals(gp1[5])) {
                    evaValue = evaValue + 2.0;
                    if ("満員電車".equals(gp1[2])) {
                        evaValue = evaValue - 3.0;
                    }
                } else if ("寒い".equals(gp1[6])) {
                    evaValue = evaValue - 2.0;
                    if ("満員電車".equals(gp1[2])) {
                        evaValue = evaValue - 1.0;
                    }
                } else if ("熱い".equals(gp1[7])) {
                    evaValue = evaValue - 4.0;
                    if ("満員電車".equals(gp1[2])) {
                        evaValue = evaValue - 5.0;
                    }
                }
            } else {
                evaValue = evaValue - 5.0;
                if ("大雨".equals(gp1[3])) {
                    evaValue = evaValue - 5.0;
                } else if ("小雨".equals(gp1[4])) {
                    evaValue = evaValue - 1.0;
                }
            }
        } else {
            evaValue = evaValue + 1.0;
            if ("適温".equals(gp1[5])) {
                evaValue = evaValue + 2.0;
                if ("満員電車".equals(gp1[2])) {
                    evaValue = evaValue - 3.0;
                }
            } else if ("寒い".equals(gp1[6])) {
                evaValue = evaValue - 2.0;
                if ("満員電車".equals(gp1[2])) {
                    evaValue = evaValue - 1.0;
                }
            } else if ("熱い".equals(gp1[7])) {
                evaValue = evaValue - 4.0;
                if ("満員電車".equals(gp1[2])) {
                    evaValue = evaValue - 5.0;
                }
            }
        }

        return evaValue;
    }
    
    
}
