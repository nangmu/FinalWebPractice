package main.java.VO;

public class Paging {
	public static final int USER_DEFAULT_PAGE_SIZE = 5;
	public static final int USER_DEFAULT_GROUP_SIZE = 3;
	public static final int BOARD_DEFAULT_PAGE_SIZE = 5;
	public static final int BOARD_DEFAULT_GROUP_SIZE = 3;
	
	private int pageSize;
	private int groupSize;
	private int curPage;
	private int totalRecords;
	
	private int totalPage;
	private int curGroupNum;
	private int startPageNum;
	private int endPageNum;
	
	//View에서 전체 레코드를 가져올 경우, 아래를 변수를 참고하여 curPage에 표시될 적절한 레코드를 선택할 수 있음
	private int startingRecordIdx;
	private int endingRecordIdx;

	public Paging(){}
	public Paging(int pageSize, int groupSize,int totalrecords,int curPage){
		this.pageSize=pageSize; this.groupSize=groupSize;
		this.totalRecords=totalrecords; this.curPage=curPage;
		init();
	}
	private void init(){
		totalPage = totalRecords / pageSize;
		if(totalRecords%pageSize!=0) totalPage++;
		
		curGroupNum = curPage/groupSize;
		if(curPage%groupSize!=0) curGroupNum++;
		
		// 1p 0~4 2p 5~9 3p 10~14 np (curPage-1)*pageSize ~ curPage*pageSize-1
		startingRecordIdx= (curPage-1)*pageSize;
		endingRecordIdx = curPage*pageSize-1;
		if(endingRecordIdx>totalRecords-1){
			endingRecordIdx = totalRecords-1;
		}
		
		startPageNum = (curGroupNum-1)*groupSize+1;
		endPageNum = curGroupNum*groupSize;
		if(endPageNum > totalPage) endPageNum = totalPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public int getGroupSize() {
		return groupSize;
	}
	public int getCurPage() {
		return curPage;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public int getCurGroupNum() {
		return curGroupNum;
	}
	public int getStartPageNum() {
		return startPageNum;
	}
	public int getEndPageNum() {
		return endPageNum;
	}
	public int getStartingRecordIdx() {
		return startingRecordIdx;
	}
	public int getEndingRecordIdx() {
		return endingRecordIdx;
	}

	@Override
	public String toString() {
		return "Paging [startPageNum=" + startPageNum + ", endPageNum=" + endPageNum + ", startingRecordNum="
				+ startingRecordIdx + ", endingRecordNum=" + endingRecordIdx + "]";
	}
	
	
	
}
