package main.java.VO;

public class Paging {
	public static final int USER_DEFAULT_PAGE_SIZE = 5;
	public static final int USER_DEFAULT_GROUP_SIZE = 3;
	public static final int BOARD_DEFAULT_PAGE_SIZE = 20;
	public static final int BOARD_DEFAULT_GROUP_SIZE = 3;
	
	private int pageSize;
	private int groupSize;
	private int curPage;
	private int totalRecords;
	
	private int totalPage;
	private int curGroupNum;
	private int startPageNum;
	private int endPageNum;
	
	//DB에서 전체 VO 인스턴스를 가져와 페이징처리를 하는 경우 보여줄 레코드의 시작과 끝 인덱스.
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
