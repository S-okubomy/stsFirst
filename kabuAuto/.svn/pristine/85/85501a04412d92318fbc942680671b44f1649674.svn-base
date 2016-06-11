package kabuAuto.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import kabuAuto.annotation.Auth;
import kabuAuto.dto.CalListDto;
import kabuAuto.dto.SaveUserDto;
import kabuAuto.form.KabuCalListForm;
import kabuAuto.service.TKabuDataService;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;


public class CopyOfKabuCalListAction {

	/*
	 * 計算一覧画面のパラメータ
	 */
	@ActionForm
	@Resource
	protected KabuCalListForm kabuCalListForm;

	/*
	 * 投稿マスターテーブルにアクセスする
	 */
	@Resource
	protected TKabuDataService tKabuDataService;

	/*
	 * ユーザ情報を保存して表示するため
	 */
	@Resource
	protected SaveUserDto saveUserDto;

	/*
	 * 計算のための、DTO
	 */
	@Resource
	protected CalListDto calListDto;

	/*
	 * 検索結果有無の情報を受注一覧画面に渡すためのフラグ
	 */
	public boolean hasSerch;


	/**
	 * 株価計算一覧画面への遷移
	 * @return 遷移パス
	 */
	@Auth
	@Execute(validator = false)
	public String index() {

		List<CalListDto> calList = new ArrayList<CalListDto>();
		List<CalListDto> kabuList = this.tKabuDataService.findKabuCodeList();

		for(CalListDto kabuInfo : kabuList) {
			this.calListDto = this.tKabuDataService.calbol(kabuInfo.kabuCode);
			this.calListDto.kabuCode = kabuInfo.kabuCode;
			this.calListDto.kabuName = kabuInfo.kabuName;
			this.calListDto.bolCalLow = this.calListDto.transAve20 - 2.0d * this.calListDto.sigma20;
			this.calListDto.bolCalUp = this.calListDto.transAve20 + 2.0d * this.calListDto.sigma20;
			this.calListDto.kairi = (this.calListDto.kabuClose - this.calListDto.transAve20) / this.calListDto.transAve20 * 100.0d;

			calList.add(this.calListDto);
		}

		this.kabuCalListForm.calList = calList;
		this.kabuCalListForm.saveUserName = saveUserDto.userName;

		return "kabuCalList.jsp";
	}


	/**
	 * ボリンジャ該当一覧画面への遷移
	 * @return 遷移パス
	 */
	@Auth
	@Execute(validator = false)
	public String bolHit() {

		List<CalListDto> calLowList = new ArrayList<CalListDto>();
		List<CalListDto> calUpList = new ArrayList<CalListDto>();
		List<CalListDto> kabuList = this.tKabuDataService.findKabuCodeList();

		for(CalListDto kabuInfo : kabuList) {
			this.calListDto = this.tKabuDataService.calbol(kabuInfo.kabuCode);
			this.calListDto.kabuCode = kabuInfo.kabuCode;
			this.calListDto.kabuName = kabuInfo.kabuName;
			this.calListDto.bolCalLow = this.calListDto.transAve20 - 2.0d * this.calListDto.sigma20;
			this.calListDto.bolCalUp = this.calListDto.transAve20 + 2.0d * this.calListDto.sigma20;
			this.calListDto.kairi = (this.calListDto.kabuClose - this.calListDto.transAve20) / this.calListDto.transAve20 * 100.0d;

			if (this.calListDto.bolCalLow > this.calListDto.kabuClose ) {   //下部バンドより下（買いかも）
				calLowList.add(this.calListDto);
			}

			if (this.calListDto.bolCalUp < this.calListDto.kabuClose ) {   //上部バンドより上（売りかも）
				calUpList.add(this.calListDto);
			}

		}

		if (calLowList != null) {
			this.kabuCalListForm.calLowList = calLowList;

		}

		if (calUpList != null) {
			this.kabuCalListForm.calUpList = calUpList;
		}

		this.kabuCalListForm.saveUserName = saveUserDto.userName;

		return "bolHitList.jsp";
	}


	/**
	 * 移動平均乖離率 該当一覧画面への遷移
	 * @return 遷移パス
	 */
	@Auth
	@Execute(validator = false)
	public String aveKairiHit() {

		List<CalListDto> calLowList = new ArrayList<CalListDto>();
		List<CalListDto> calUpList = new ArrayList<CalListDto>();
		List<CalListDto> kabuList = this.tKabuDataService.findKabuCodeList();

		for(CalListDto kabuInfo : kabuList) {
			this.calListDto = this.tKabuDataService.calbol(kabuInfo.kabuCode);
			this.calListDto.kabuCode = kabuInfo.kabuCode;
			this.calListDto.kabuName = kabuInfo.kabuName;
			this.calListDto.bolCalLow = this.calListDto.transAve20 - 2.0d * this.calListDto.sigma20;
			this.calListDto.bolCalUp = this.calListDto.transAve20 + 2.0d * this.calListDto.sigma20;
			this.calListDto.kairi = (this.calListDto.kabuClose - this.calListDto.transAve20) / this.calListDto.transAve20 * 100.0d;

			if (this.calListDto.kairi <= -10.0d ) {   //移動平均乖離率がマイナス１０％以下（買いかも）
				calLowList.add(this.calListDto);
			}

			if (this.calListDto.kairi >= 10.0d ) {   //移動平均乖離率がプラス１０％以上（売りかも）
				calUpList.add(this.calListDto);
			}
		}

		if (calLowList != null) {
			this.kabuCalListForm.calLowList = calLowList;

		}

		if (calUpList != null) {
			this.kabuCalListForm.calUpList = calUpList;
		}

		this.kabuCalListForm.saveUserName = saveUserDto.userName;

		return "aveKairiHit.jsp";
	}


	/**
	 * 検索画面への遷移
	 * @return 遷移パス
	 */
	@Auth
	@Execute(validator = false)
	public String serchDisp() {

		this.kabuCalListForm.saveUserName = saveUserDto.userName;

		return "kabuSerch.jsp";
	}


	/**
	 * 検索 画面への遷移
	 * @return 遷移パス
	 */
	@Auth
	@Execute(validator = false)
	public String kabuSerch() {

		List<CalListDto> calLowList = new ArrayList<CalListDto>();
		List<CalListDto> calUpList = new ArrayList<CalListDto>();
		List<CalListDto> kabuList = this.tKabuDataService.findKabuCodeList();

		for(CalListDto kabuInfo : kabuList) {
			this.calListDto = this.tKabuDataService.calbol(kabuInfo.kabuCode);
			this.calListDto.kabuCode = kabuInfo.kabuCode;
			this.calListDto.kabuName = kabuInfo.kabuName;

				if (convertSelectId(this.kabuCalListForm.sigmaSerch) !=null) {
					this.calListDto.bolCalLow = this.calListDto.transAve20 - Double.parseDouble(this.kabuCalListForm.sigmaSerch) * this.calListDto.sigma20;
					this.calListDto.bolCalUp = this.calListDto.transAve20 + Double.parseDouble(this.kabuCalListForm.sigmaSerch) * this.calListDto.sigma20;
					this.calListDto.kairi = (this.calListDto.kabuClose - this.calListDto.transAve20) / this.calListDto.transAve20 * 100.0d;
					if (this.calListDto.bolCalLow > this.calListDto.kabuClose ) {   //下部バンドより下（買いかも）
						calLowList.add(this.calListDto);
					}
					if (this.calListDto.bolCalUp < this.calListDto.kabuClose ) {   //上部バンドより上（売りかも）
						calUpList.add(this.calListDto);
					}

				} else if (convertSelectId(this.kabuCalListForm.kairiSerch) !=null ) {   //移動平均乖離率がマイナス X％以下（買いかも）
					this.calListDto.kairi = (this.calListDto.kabuClose - this.calListDto.transAve20) / this.calListDto.transAve20 * 100.0d;
					this.calListDto.bolCalLow = this.calListDto.transAve20 - 2.0d * this.calListDto.sigma20;
					this.calListDto.bolCalUp = this.calListDto.transAve20 + 2.0d * this.calListDto.sigma20;
					if (this.calListDto.kairi <= - Double.parseDouble(convertSelectId(this.kabuCalListForm.kairiSerch))) {   //移動平均乖離率がマイナス X％以下（買いかも）
						calLowList.add(this.calListDto);
					}
					if (this.calListDto.kairi >= Double.parseDouble(convertSelectId(this.kabuCalListForm.kairiSerch))) {   //移動平均乖離率がプラス X％以上（売りかも）
						calUpList.add(this.calListDto);
					}
				}
		}

		if (calLowList != null) {
			this.kabuCalListForm.hasSerch = true;
			this.kabuCalListForm.calLowList = calLowList;
		}

		if (calUpList != null) {
			this.kabuCalListForm.hasSerch = true;
			this.kabuCalListForm.calUpList = calUpList;
		}

		this.kabuCalListForm.serchFlag = true;

		this.kabuCalListForm.saveUserName = saveUserDto.userName;

		return "kabuSerch.jsp";

	}


	/**
	 * セレクトボックスのIDを確認し、未選択の場合、nullを返す。
	 * @param value ステータスID
	 * @return ステータスIDが未選択の場合はnull、それ以外は、ステータスID値を返す
	 */
	private String convertSelectId(String value) {
	    if ( value == null || value.length() == 0 || value.equals("未選択")) {
	        return null;
	    } else {
	        return value;
	    }
	}







}
