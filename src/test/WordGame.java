package test;

import java.util.List;

public class WordGame {
	
	private String currentState;
	private String goal;
	private String start;
	
	public WordGame(String start, String goal) {
		currentState=new String(start);
		this.goal=goal;
		this.start=start;
	}
	
	public String getCurrentState() {
		return currentState;
	}
	public boolean isGoal(){
		return currentState.equals(goal);
	}	
	public String getStart() {
		return start;
	}
	
	public int switchLetters(int i, int j){
		if(i>=0 && i<currentState.length() && j>=0 && j<currentState.length()){
			char[] work=currentState.toCharArray();
			char t=work[i];
			work[i]=work[j];		
			work[j]=t;
			currentState=new String(work);
			return Math.abs(i-j);
		}
		return -1;
	}
	
	public boolean equals(Object o){
		WordGame w=(WordGame)o;
		return w.currentState.equals(currentState);
	}

	public void applyActions(List<String> actions) {
		actions.forEach(s->{
			int i=Integer.parseInt(s.split(",")[0]);
			int j=Integer.parseInt(s.split(",")[1]);
			switchLetters(i, j);
		});
	}
}
