package evalutePack;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import interfaceEva.BaseEvaVal;

/**
 * 最大値の結果を再計算
 * @author Administrator
 *
 */
public class EvaCalMax1 {

    /**
     * 最大値の結果を再計算
     * @param evaluationClsName
     * @param gaParameter
     * @return
     * @throws Exception
     */
    public double evaCalMax (String evaluationClsName, double[] gaParameter) throws Exception {
        
        double evaValMax = getEvaValue(evaluationClsName, gaParameter);
        
        return evaValMax;
    }
    
    /**
     * クラス名からメソッドを実行する。
     * @param evaluationClsName
     * @param gaParameter
     * @return
     * @throws Exception
     */
    private double getEvaValue(String evaluationClsName, double[] gaParameter) throws Exception {
        
        double evaValue = 0;
        try {
            // クラス名からクラスのインスタンスを取得する。
            Class<? extends BaseEvaVal> evaluationCls 
                = Class.forName(evaluationClsName).asSubclass(BaseEvaVal.class);
            Method method = evaluationCls.getMethod("execute", double[].class);
            evaValue = (double)method.invoke(evaluationCls.newInstance(), gaParameter);
        } catch (ClassCastException e) {
            throw new Exception("評価クラスにはBaseEvaValを実装したクラスを指定する必要があります。", e);
        } catch (ClassNotFoundException e) {
            throw new Exception("評価クラスが見つかりません。");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        
        return evaValue;
    }
    
}
