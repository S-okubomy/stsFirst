package rcgaBatch1.batch;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;

import rcgaBatch1.dto.CalFitDto;
import rcgaBatch1.dto.CalProbaDto;
import rcgaBatch1.dto.InitDto;
import util.GetHinshiUtil;

/**
 * RCGAのメインクラス
 * @author Administrator
 *
 */
public class MainRcga {

	private static final int mSedai = InitDto.calSedai; //繰り返し世代数


	public void calGene(String evaluationClsName) {

		try{
			// Projectのトップディレクトリパス取得
			String folderName = System.getProperty("user.dir");
			// トップディレクトリパス以降を設定
			folderName = folderName + "\\src\\main\\java\\rcgaBatch1\\batch\\";
			// String folderName = "C://pleiades/workspace/getStudyManModelTest/src/main/java/rcgaBatch1/batch/";
			// Linux用
			// String folderName ="/opt/";

			String fname1 ="getStudyManModelTestHistLog";
			String fnameUse ="getStudyManModelTestHist";

			//データ保存用 変数
			int writeL = 0;
			int writeFitMaxNum = 0;
			double writeFitMax = 0;
			String AC0 = " ";
			String AC1 = " ";
			String AC2 = " ";
			String AC3 = " ";

			//出力用ファイルのオープン
			FileOutputStream fos = new FileOutputStream( folderName + fname1 + ".csv");
			OutputStreamWriter osw = new OutputStreamWriter(fos , "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);

			//フォーマット
			DecimalFormat df1 = new DecimalFormat("0.00");

			/* 初期遺伝子をつくる */
			InitGa initGa = new InitGa();
			InitDto initDto = new InitDto();

			/* 適応度を計算する */
			CalFitDto calFitDto = new CalFitDto();
			CalFit calFit = new CalFit();

			/* 選択確率を計算する */
			CalProbaDto calProbaDto = new CalProbaDto();
			ProbaSelecct probaSelecct = new ProbaSelecct();

			/* ルーレット選択を行う */
			SelectR selectR = new SelectR();

			/* ブレンド交叉（BLX)をする */
			CrossBLX crossBLX = new CrossBLX();

			/* 突然変異（一様突然変異）をする */
			Mutation mutation = new Mutation();

			/*染色体の遺伝子の値を関数の係数にする*/
			Convert1 convert1 = new Convert1();

			System.out.println("個体No: " + " 適応度 " + " 係数0 " +  " 係数1 "  + " 係数2 " + " 係数3 ");
			bw.write("世代," + "個体No," + "実値," + "適応度," + "係数0," +  "係数1,"  + "係数2," + "係数3" + "\r\n");

			/* 初期遺伝子をつくる */
			initDto = initGa.initGa();

			/* 適応度を計算する */
			calFitDto = calFit.calFit(initDto, evaluationClsName);

			/* 全世代 最大の個体を取得*/
			double[] acGenMax1 = new double[InitDto.nn];
			double fitGenMax1 = 0;
			double truvalGenMax1 = 0;
			int LGenMax1 = 0;
			int fitMaxNumGen1 = 0;

			for(int L = 0; L < mSedai; L++){

				/* 選択確率を計算する */
				calProbaDto = probaSelecct.probaSelecct(calFitDto);

				/* ルーレット選択を行う */
				initDto = selectR.selectR(calProbaDto,calFitDto);

				/* ブレンド交叉（BLX)をする */
				initDto = crossBLX.crossBLX(initDto);

				/* 突然変異（一様突然変異）をする */
				initDto = mutation.mutation(initDto);

				/*染色体の遺伝子の値を関数の係数にする*/
				initDto = convert1.convert1(initDto);

				/* 適応度を計算する */
				calFitDto = calFit.calFit(initDto, evaluationClsName);

				double[] trueVal = new double[InitDto.NN];
				trueVal = calFitDto.getTrueVal();

				double[] fit1 = new double[InitDto.NN];
				fit1 = calFitDto.getFit1();

				/** x1[][]:染色体 */
				double[][] ac1 = new double[InitDto.NN][InitDto.nn];
				ac1 = initDto.getAc1();

				System.out.println("世代： " + L);
				double fitMax = fit1[0];
				int fitMaxNum = 0;

				System.out.println("実値: " + trueVal[0] +  " ,適応度： " + fit1[0] + " ,個体 "+ 0  + " ,ac1[0][0] " +df1.format(ac1[0][0])
			            + " ,ac1[0][1] " + df1.format(ac1[0][1])  + " ,ac1[0][2] " + df1.format(ac1[0][2]) + " ,ac1[0][3] " + df1.format(ac1[0][3]));

				for(int i =1; i < fit1.length ; i++) {
					System.out.println("実値: " + trueVal[i] +  " ,適応度： " + fit1[i] + " ,個体 "+ i  + " ,ac1[i][0] " + df1.format(ac1[i][0])
							            + " ,ac1[i][1] " + df1.format(ac1[i][1])  + " ,ac1[i][2] " + df1.format(ac1[i][2]) + " ,ac1[i][3] " + df1.format(ac1[i][3]));

					if (fit1[i] > fitMax) {
						fitMax = fit1[i];
						fitMaxNum = i;
					}
				}
				System.out.println("個体No: " + fitMaxNum + " ,実値: " + trueVal[fitMaxNum]  + " ,適応度： " + fit1[fitMaxNum] + "  " + df1.format(ac1[fitMaxNum][0])
			              + "  " + df1.format(ac1[fitMaxNum][1]) + "  " + df1.format(ac1[fitMaxNum][2]) + "  " + df1.format(ac1[fitMaxNum][3]));
				//CSVに書き込み
				bw.write(L + "," + fitMaxNum + "," + trueVal[fitMaxNum] + "," + fit1[fitMaxNum] + "," + df1.format(ac1[fitMaxNum][0])
			            + "," + df1.format(ac1[fitMaxNum][1]) + "," + df1.format(ac1[fitMaxNum][2]) + "," + df1.format(ac1[fitMaxNum][3]) + "\r\n" );

				//全世代で適応度が最大のものを保存
				if (fit1[fitMaxNum] > fitGenMax1) {
					fitGenMax1 = fit1[fitMaxNum];
					fitMaxNumGen1 = fitMaxNum;
					LGenMax1 = L;
					truvalGenMax1 = trueVal[fitMaxNum];
					for(int i =0; i < InitDto.nn ; i++) {
						acGenMax1[i] = ac1[fitMaxNum][i];
					}
				}

			}

			//ファイルクローズ
			bw.close();

			writeL = LGenMax1;
			writeFitMaxNum = fitMaxNumGen1;
			writeFitMax = fitGenMax1;
			AC0 = df1.format(acGenMax1[0]);
			AC1 = df1.format(acGenMax1[1]);
			AC2 = df1.format(acGenMax1[2]);
			AC3 = df1.format(acGenMax1[3]);

			System.out.println("最大世代: " + writeL +  " ,個体No: " + writeFitMaxNum + " ,実値: " + truvalGenMax1  + " ,適応度： " + writeFitMax + "  "
					+ AC0 + "  " + AC1 + "  "
					+ AC2 + "  " + AC3);
			System.out.println("最大世代: " + writeL +  " ,個体No: " + writeFitMaxNum + " ,実値: " + truvalGenMax1  + " ,適応度： " + writeFitMax + "  "
					+ GetHinshiUtil.getHinshiNum(acGenMax1[0]) + "  " + GetHinshiUtil.getHinshiNum(acGenMax1[1]) + "  "
					+ GetHinshiUtil.getHinshiNum(acGenMax1[2]) + "  " + GetHinshiUtil.getHinshiNum(acGenMax1[3]));


			//最終結果をCSVへ書き込み
			FileOutputStream fosUse = new FileOutputStream( folderName + fnameUse + ".csv");
			OutputStreamWriter oswUse = new OutputStreamWriter(fosUse , "UTF-8");
			BufferedWriter bwUse = new BufferedWriter(oswUse);
			bwUse.write("世代," + "個体No," + "適応度," + "係数0," +  "係数1,"  + "係数2," + "係数3" + "\r\n");
			bwUse.write(writeL + "," + writeFitMaxNum + "," + writeFitMax + ","
					+ acGenMax1[0] + "," + GetHinshiUtil.getHinshiNum(acGenMax1[1]) + ","
					+ GetHinshiUtil.getHinshiNum(acGenMax1[2]) + "," + GetHinshiUtil.getHinshiNum(acGenMax1[3]) + "\r\n" );

			//ファイルクローズ
			bwUse.close();
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}

	}

}
