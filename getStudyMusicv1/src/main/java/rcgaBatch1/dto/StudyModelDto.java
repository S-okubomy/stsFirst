package rcgaBatch1.dto;

import java.io.Serializable;

public class StudyModelDto implements Serializable {

	public StudyModelDto() {
	}

	/** 学習No */
	private String studyNo;

	/** 判定情報 正解:T */
	private String hanteiJoho;

	/** 学習対象 */
	private String studyTaisho;

	/** 学習内容 */
	private String studyNaiyo;

	public void setStudyNo(String studyNo){
        this.studyNo = studyNo;
    }

	public void setHanteiJoho(String hanteiJoho){
        this.hanteiJoho = hanteiJoho;
    }

	public void setStudyTaisho(String studyTaisho){
        this.studyTaisho = studyTaisho;
    }

	public void setStudyNaiyo(String studyNaiyo){
        this.studyNaiyo = studyNaiyo;
    }

	public String getStudyNo() {
		return this.studyNo;
	}

	public String getHanteiJoho() {
		return this.hanteiJoho;
	}

	public String getStudyTaisho() {
		return this.studyTaisho;
	}

	public String getStudyNaiyo() {
		return this.studyNaiyo;
	}

}
