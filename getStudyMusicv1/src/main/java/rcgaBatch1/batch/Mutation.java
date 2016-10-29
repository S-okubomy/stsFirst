package rcgaBatch1.batch;

import java.util.Random;

import rcgaBatch1.dto.InitDto;

/**
 * 突然変異をするクラス
 * @author Administrator
 *
 */
public class Mutation {

	public InitDto mutation(InitDto initDto) {

		/*突然変異*/
		Random r = new Random();
        int to = r.nextInt(1000);      //0～ 1000の乱数を取得する
        Random rSe = new Random();
        Random rnd = new Random();

		double[][] x1 = new double[InitDto.NN][InitDto.nn];
		x1 = initDto.getX1();
		double[][] ba1 = new double[InitDto.NN][InitDto.nn];
		ba1 = initDto.getBa1();

	    /* 個体のうち 10% 突然変異を実行する*/
		for(int i=0; i< InitDto.NN /7; i++){
			int se1 = rSe.nextInt(InitDto.NN -1) + 1;   /* 0番目はエリート遺伝子用  個体を決定*/
			x1[se1][0]=(InitDto.maxA0-InitDto.minA0)*(rnd.nextDouble()) + InitDto.minA0;    /*乱数を使って実数*/
			x1[se1][1]=(InitDto.maxA1-InitDto.minA1)*(rnd.nextDouble()) + InitDto.minA1;    /*乱数を使って実数*/
			x1[se1][2]=(InitDto.maxA2-InitDto.minA2)*(rnd.nextDouble()) + InitDto.minA2;    /*乱数を使って実数*/
			x1[se1][3]=(InitDto.maxA3-InitDto.minA3)*(rnd.nextDouble()) + InitDto.minA3;    /*乱数を使って実数*/

			ba1[se1][0] = x1[se1][0];
			ba1[se1][1] = x1[se1][1];
			ba1[se1][2] = x1[se1][2];
			ba1[se1][3] = x1[se1][3];
		}

		initDto.setX1(x1);
		initDto.setBa1(ba1);

		return initDto;
	}

}
