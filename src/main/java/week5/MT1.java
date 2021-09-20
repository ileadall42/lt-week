package week5;

public class MT1 {
    public boolean makeEqual(String[] words) {
        //1. 统计每个字符出现次数
        int n = words.length;
        int[] charDict = new int[26];
        for (int i = 0; i < n; i++){

            for (char cc : words[i].toCharArray()){
                int idx = cc - 'a';
                charDict[idx]++;
            }
        }
        //2. 若每个字符不是n的倍数，则一定不能成
        for (int rs = 0; rs < charDict.length; rs++){
            if (charDict[rs] % n != 0){
                return false;
            }
        }
        return true;
    }
}
