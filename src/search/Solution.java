package search;

import java.util.ArrayList;

public class Solution extends ArrayList<String> {

    public char getChar(int i, int j) {
        return this.get(i).charAt(j);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < size(); i++) {
            stringBuilder.append(get(i));
            if(i != size()-1) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }



}
