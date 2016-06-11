package kabuAuto.batch;

public class Tag {
	String word( String line, int tags ){

		int i, j ;
		int cnt ;

		for(i=0,cnt=0; i<line.length(); i++){
			if(line.charAt(i) == '>'){
				cnt ++ ;
				if(cnt == tags){
					break ;
				}
			}
		}
		for(j=i; j<line.length(); j++){
			if(line.charAt(j) == '<'){
				break ;
			}
		}
		//System.out.println(line.substring(i+1,j));
		return line.substring(i+1,j) ;
	}
}
