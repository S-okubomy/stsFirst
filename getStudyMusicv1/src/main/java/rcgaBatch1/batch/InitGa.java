package rcgaBatch1.batch;

import java.util.Random;

import rcgaBatch1.dto.InitDto;


/**
 * 初期遺伝子をつくるクラス
 * @author Administrator
 *
 */
public class InitGa {

	//ランダムーサーチをして，ある程度制約を満たした解を
	//初期個体とする．
	public InitDto initGa(){

		//Randomクラスのインスタンス化
        Random rnd = new Random();
        InitDto initDto = new InitDto();

    	/** 各個体の適応度 初期化 */
		double[][] x1 = new double[InitDto.NN][InitDto.nn];
		double[][] ac1 = new double[InitDto.NN][InitDto.nn];
		double[][] ba1 = new double[InitDto.NN][InitDto.nn];

		for(int i1 =0; i1 < InitDto.NN; i1++){
			x1[i1][0]=(InitDto.maxA0-InitDto.minA0)*(rnd.nextDouble()) + InitDto.minA0;    /*乱数を使って実数*/
			x1[i1][1]=(InitDto.maxA1-InitDto.minA1)*(rnd.nextDouble()) + InitDto.minA1;    /*乱数を使って実数*/
			x1[i1][2]=(InitDto.maxA2-InitDto.minA2)*(rnd.nextDouble()) + InitDto.minA2;    /*乱数を使って実数*/
			x1[i1][3]=(InitDto.maxA3-InitDto.minA3)*(rnd.nextDouble()) + InitDto.minA3;    /*乱数を使って実数*/

			ba1[i1][0]=x1[i1][0];    /*バックアップをとる*/
			ac1[i1][0]=ba1[i1][0];

			ba1[i1][1]=x1[i1][1];    /*バックアップをとる*/
			ac1[i1][1]=ba1[i1][1];

			ba1[i1][2]=x1[i1][2];    /*バックアップをとる*/
			ac1[i1][2]=ba1[i1][2];

			ba1[i1][3]=x1[i1][3];    /*バックアップをとる*/
			ac1[i1][3]=ba1[i1][3];
		}

		initDto.setAc1(ac1);
		initDto.setX1(x1);
		initDto.setBa1(ba1);

		return initDto;

	}

}
