package kr.or.ddit.basic;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class PagingController implements Initializable {

	@FXML
	private TableView<MemberVO> tv;
	@FXML
	private TableColumn<MemberVO, String> id;
	@FXML
	private TableColumn<MemberVO, String> name;
	@FXML
	private TableColumn<MemberVO, String> addr;
	@FXML
	private Pagination pn;

	private int from, to, itemsForPage;

	private ObservableList<MemberVO> allTableData, currentPageData;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		addr.setCellValueFactory(new PropertyValueFactory<>("addr"));

		// 테스트용 데이트 만들기
		allTableData = FXCollections.observableArrayList();

		allTableData.add(new MemberVO("1", "홍길동1", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("2", "홍길동2", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("3", "홍길동3", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("4", "홍길동4", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("5", "홍길동5", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("6", "홍길동6", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("7", "홍길동7", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("8", "홍길동8", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("9", "홍길동9", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("10", "홍길동10", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("11", "홍길동11", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("12", "홍길동12", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("13", "홍길동13", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("14", "홍길동14", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("15", "홍길동15", "대전시 중구 대흥동 대덕인재개발원"));
		allTableData.add(new MemberVO("16", "홍길동16", "대전시 중구 대흥동 대덕인재개발원"));

//		tv.setItems(allTableData);
		
		itemsForPage = 5; //한페이지에 보여줄 항목 수 설정
		int totalDataCnt = allTableData.size();
		int totalPageCnt = totalDataCnt % itemsForPage == 0? totalDataCnt / itemsForPage : totalDataCnt / itemsForPage + 1;
		
		pn.setPageCount(totalPageCnt); //전체 페이지수 설정
		
		//방법1 Callback타입의 익명 객체 생성
		pn.setPageFactory(new Callback<Integer, Node>() {
			
			@Override
			public Node call(Integer pageIndex) {
				from = pageIndex * itemsForPage;
				to = from + itemsForPage - 1;
				tv.setItems(getTableViewDate(from, to));
				return tv;
			}

			private ObservableList<MemberVO> getTableViewDate(int from, int to) {
				currentPageData = FXCollections.observableArrayList();
				int totSize = allTableData.size();
				for(int i = 0; i <= to && i <= totSize; i++) {
					currentPageData.add(allTableData.get(i));
				}
				
				return currentPageData;
			}
		});
	}

}
