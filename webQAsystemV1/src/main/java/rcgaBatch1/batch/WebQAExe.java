package rcgaBatch1.batch;

import java.util.ArrayList;
import java.util.List;

import rcgaBatch1.dto.AnsModelDto;

public class WebQAExe {

    /**
     * 上限の件数
     */
    public static final int resultKensu = 5;
    
    public static void main(String[] args) throws Exception {
        
        WebQAExe webQAExe = new WebQAExe();
        webQAExe.getWebQA();
    }
    
    public void getWebQA() throws Exception {
        // ================= 質問タイプの判定 =================
        String[] question = new String[1];
        question[0] = "宮崎駅はどこにありますか？";
        QueGetJitsuDate queGetJitsuDate = new QueGetJitsuDate();
        // 質問タイプ判定後の結果を取得（判定結果の降順に格納）
        List<AnsModelDto> queTypeList = queGetJitsuDate.getJitsuDate(question);
        // 質問タイプ判定後の結果を上限件数分、格納
        List<AnsModelDto> resultQueTypeList = getResultQueList(queTypeList);

        // ================= ネット検索して回答作成  =================
        AnsGetJitsuDate ansGetJitsuDate = new AnsGetJitsuDate();
        // 回答結果を取得（判定結果の降順に格納）
        List<AnsModelDto> ansList = ansGetJitsuDate.getJitsuDate(question);
        // 回答結果を上限件数分、格納
        List<AnsModelDto> resultAnsList = getResultAnsList(resultQueTypeList, ansList);

        // ================= 画面に表示 =================
        
        System.out.println("============ 質問タイプ 並び替え後 ============");
        // 正規表現でフィルター（文章の前後にスペースを含む行を除く    "^\\x01-\\x7E"で1バイト文字以外を探す）
        resultQueTypeList.stream()
                    .forEach(ansModel -> System.out.println("質問分類: " + ansModel.getAnsBunrui() 
                                + " fx= " + ansModel.getFxValue() 
                                + " 文章: " + ansModel.getAnsSentence()));
        System.out.println("出力完了");
        
        System.out.println("============ 回答並び替え後 ============");
        // 正規表現でフィルター（文章の前後にスペースを含む行を除く    "^\\x01-\\x7E"で1バイト文字以外を探す）
        resultAnsList.stream()
                    .forEach(ansModel -> System.out.println("回答分類: " + ansModel.getAnsBunrui() 
                                + " fx= " + ansModel.getFxValue() 
                                + " 文章: " + ansModel.getAnsSentence()));
        System.out.println("出力完了");
    }
    
    /**
     * 結果を上限件数分、格納
     * @param beforeList
     * @return
     */
    private List<AnsModelDto> getResultQueList(List<AnsModelDto> beforeList) {
        List<AnsModelDto> resultList = new ArrayList<AnsModelDto>();
        int cntAns = 0;
        for (AnsModelDto queModel : beforeList) {
            resultList.add(queModel);
            // 上限件数 以上 格納したらbreak
            if (resultKensu <= cntAns) {
                break;
            }
        }
        return resultList;
    }
    
    /**
     * 回答結果を上限件数分、格納
     * @param resultQueTypeList
     * @param ansList
     * @return
     */
    private List<AnsModelDto> getResultAnsList(List<AnsModelDto> resultQueTypeList
            , List<AnsModelDto> ansList) {
        List<AnsModelDto> resultAnsList = new ArrayList<AnsModelDto>();
        for (AnsModelDto resultQueType : resultQueTypeList) {
            int cntAns = 0;
            for (AnsModelDto ansModel : ansList) {
                // 質問タイプと一致する回答結果をセットする。
                if (resultQueType.getAnsBunrui().equals(ansModel.getAnsBunrui())) {
                    resultAnsList.add(ansModel);
                    cntAns++;
                }
                // 上限件数 以上 格納したらbreak
                if (resultKensu <= cntAns) {
                    break;
                }
            }
        }
        return resultAnsList;
    }
    
    

}
