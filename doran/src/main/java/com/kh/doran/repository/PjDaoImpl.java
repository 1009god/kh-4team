package com.kh.doran.repository;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.doran.entity.PjDto;
import com.kh.doran.vo.OrderCountVO;
import com.kh.doran.vo.OrdersCalVO;
import com.kh.doran.vo.PjFileVO;
import com.kh.doran.vo.PjListSearchVO;


@Repository
public class PjDaoImpl implements PjDao {
	
	@Autowired//주입
	private JdbcTemplate jdbcTemplate;
	
//	@Autowired
//	private PjDto pjDto; 하면...안됩니다...아예 지우면 나중에 또 까먹고 걸칠까봐 주석처리함
	
	
	private RowMapper<PjDto> mapper=new RowMapper<PjDto>(){

		@Override
		public PjDto mapRow(ResultSet rs, int rowNum) throws SQLException {
			return PjDto.builder()
					.pjNo(rs.getInt("PJ_NO"))
					.pjSellerMemNo(rs.getInt("PJ_SELLER_MEM_NO"))
					.pjCategory(rs.getString("PJ_CATEGORY"))
					.pjName(rs.getString("PJ_NAME"))
					.pjSummary(rs.getString("PJ_SUMMARY"))
					.pjTargetMoney(rs.getInt("PJ_TARGET_MONEY"))
					.pjFundingStartDate(rs.getDate("PJ_FUNDING_START_DATE"))
					.pjFundingEndDate(rs.getDate("PJ_FUNDING_END_DATE"))
					.pjEndDate(rs.getDate("PJ_END_DATE"))
					.pjLikesNumber(rs.getInt("PJ_LIKES_NUMBER"))
					.build();
		}
	
	};
	
	
	private ResultSetExtractor<PjDto> extractor=new ResultSetExtractor<PjDto>() {

		@Override
		public PjDto extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(rs.next()) {
				return PjDto.builder()
						.pjNo(rs.getInt("PJ_NO"))
						.pjSellerMemNo(rs.getInt("PJ_SELLER_MEM_NO"))
						.pjCategory(rs.getString("PJ_CATEGORY"))
						.pjName(rs.getString("PJ_NAME"))
						.pjSummary(rs.getString("PJ_SUMMARY"))
						.pjTargetMoney(rs.getInt("PJ_TARGET_MONEY"))
						.pjFundingStartDate(rs.getDate("PJ_FUNDING_START_DATE"))
						.pjFundingEndDate(rs.getDate("PJ_FUNDING_END_DATE"))
						.pjEndDate(rs.getDate("PJ_END_DATE"))
						.pjLikesNumber(rs.getInt("PJ_LIKES_NUMBER"))
						.build();
			}
			else {
				return null;
			}
		}
	};


	@Override
	public PjDto selectOne(int pjNo) {
		String sql="select*from pj where pj_no=?";
		Object[] param= {pjNo};
		return jdbcTemplate.query(sql,extractor,param);
	}
	
	
	@Override
	public List<PjListSearchVO> selectList(PjListSearchVO vo) {
		if(vo.isSearch()) {//검색
			return search(vo);
		}
		else if(vo.isPopular()) {//인기순
			return popular(vo);
		}
		else if(vo.isImminent()) {//마감임박순
			return imminent(vo);
		}
		else if(vo.isLatest()) {//최신순
			return latest(vo);
		}
		else if(vo.isPrelaunching()) {//펀딩예정
			return prelaunching(vo);
		}
		else if(vo.isOngoing()) {//펀딩예정
			return ongoing(vo);
		}
		else if(vo.isFinishing()) {//펀딩마감
			return finishing(vo);
		}
		else if(vo.isCategory()) {//카테고리별
			return category(vo);
		}

		else {//목록
			return list(vo);
		}
	}
	
	//검색
	@Override
	public List<PjListSearchVO> search(PjListSearchVO vo) {
			String sql ="select * from ( "  
			        + " select rownum rn, TMP.* from( "
			               + "select " 
			       + " PJ.*, "
			       + " nvl(ACH.total, 0), "
			      + " nvl(ACH.total, 0) / PJ.pj_target_money *100 achievement_rate, "
			      + "IMG.pj_file_no "
			   + " from pj PJ "
			      + " left outer join ( "
			          + " select " 
			             + " OPT.options_pj_no, " 
			              + " sum(OPT.options_price) total "
			           +" from orders ORD inner join options OPT on ORD.orders_options_no = OPT.options_no "
			          + " group by OPT.options_pj_no "
			      + " ) ACH on PJ.pj_no = ACH.options_pj_no "
			   + "            left outer join ("
			      + "             select B.pj_file_no ,B.pj_file_pj_no"
			      + "                    from"
			      + "                    pj_file B inner join files A"
			      + "                    on B.pj_file_no = A.files_no"
			      + "                where pj_file_classify = '대표'"
			      + "            ) IMG on PJ.pj_no = IMG.pj_file_pj_no"
			      	+ "where instr(#1, ?)>0 order by pj_no desc"
								+")TMP "
								+ "where  pj_funding_start_date < sysdate "
								+ "and sysdate-pj_funding_end_date<=0 " 
						+	" ) where rn between ? and ? " ;
			
	sql=sql.replace("#1", vo.getType());
	Object[]param = {
			vo.getKeyword(), vo.startRow(),vo.endRow()
		};
	return jdbcTemplate.query(sql,pjListMapper,param);
	}
	
	//목록
	@Override
	public List<PjListSearchVO> list(PjListSearchVO vo) {
		String sql ="    select * from ( "
				+ "        select rownum rn, TMP.* from( "
				+ "                select "
				+ "        PJ.*,"
				+ "        nvl(ACH.total, 0),"
				+ "        nvl(ACH.total, 0) / PJ.pj_target_money *100 achievement_rate, "
			    + "		   IMG.pj_file_no "				
				+ "    from pj PJ"
				+ "        left outer join ("
				+ "            select "
				+ "                OPT.options_pj_no, "
				+ "                sum(OPT.options_price) total"
				+ "            from orders ORD inner join options OPT on ORD.orders_options_no = OPT.options_no"
				+ "            group by OPT.options_pj_no"
				+ "        ) ACH on PJ.pj_no = ACH.options_pj_no "
				+ "                    left outer join ("
				+ "             select B.pj_file_no ,B.pj_file_pj_no"
				+ "                    from"
				+ "                    pj_file B inner join files A"
				+ "                    on B.pj_file_no = A.files_no"
				+ "                where pj_file_classify = '대표'"
				+ "            ) IMG on PJ.pj_no = IMG.pj_file_pj_no "
				+ "			order by pj_no desc"
				+ "					)TMP "
				+ "					where  pj_funding_start_date < sysdate "
				+ "					and sysdate-pj_funding_end_date<=0 "
				+ "				) where rn between ? and ?  ";

		Object[]param = {vo.startRow(), vo.endRow()};
		return jdbcTemplate.query(sql,pjListMapper,param);
	}
	
	// 좋아요순
	@Override
	public List<PjListSearchVO> popular (PjListSearchVO vo) {
		String sql ="select * from ( "  
		        + " select rownum rn, TMP.* from( "
		               + "select " 
		       + " PJ.*, "
		       + " nvl(ACH.total, 0), "
		      + " nvl(ACH.total, 0) / PJ.pj_target_money *100 achievement_rate "
		   + " from pj PJ "
		      + " left outer join ( "
		          + " select " 
		             + " OPT.options_pj_no, " 
		              + " sum(OPT.options_price) total "
		           +" from orders ORD inner join options OPT on ORD.orders_options_no = OPT.options_no "
		          + " group by OPT.options_pj_no "
		      + " ) ACH on PJ.pj_no = ACH.options_pj_no order by #1 desc"
							+")TMP "
							+ "where pj_funding_start_date < sysdate "
							+ "and sysdate-pj_funding_end_date<=0  "
							+ "order by #1 desc" 
					+	" ) where rn between ? and ? " ;
		sql=sql.replace("#1", vo.getSort());
		Object[] param = {vo.startRow(), vo.endRow()};
		return jdbcTemplate.query(sql,pjListMapper,param);
	}
	
	//마감임박순
	@Override
	public List<PjListSearchVO> imminent(PjListSearchVO vo) {
		String sql ="select * from ( "  
		        + " select rownum rn, TMP.* from( "
		               + "select " 
		       + " PJ.*, "
		       + " nvl(ACH.total, 0), "
		      + " nvl(ACH.total, 0) / PJ.pj_target_money *100 achievement_rate "
		   + " from pj PJ "
		      + " left outer join ( "
		          + " select " 
		             + " OPT.options_pj_no, " 
		              + " sum(OPT.options_price) total "
		           +" from orders ORD inner join options OPT on ORD.orders_options_no = OPT.options_no "
		          + " group by OPT.options_pj_no "
		      + " ) ACH on PJ.pj_no = ACH.options_pj_no order by #1 asc"
							+")TMP "
							+ "where pj_funding_start_date < sysdate "
							+ "and sysdate-pj_funding_end_date<=0 "
							+ "order by #1 asc" 
					+	" ) where rn between ? and ? " ;
		sql=sql.replace("#1", vo.getSort());
		Object[] param = {vo.startRow(), vo.endRow()};
		return jdbcTemplate.query(sql,pjListMapper,param);
	}
	
	//최신순
	@Override
	public List<PjListSearchVO> latest(PjListSearchVO vo) {
		String sql ="select * from ( "  
		        + " select rownum rn, TMP.* from( "
		               + "select " 
		       + " PJ.*, "
		       + " nvl(ACH.total, 0), "
		      + " nvl(ACH.total, 0) / PJ.pj_target_money *100 achievement_rate "
		   + " from pj PJ "
		      + " left outer join ( "
		          + " select " 
		             + " OPT.options_pj_no, " 
		              + " sum(OPT.options_price) total "
		           +" from orders ORD inner join options OPT on ORD.orders_options_no = OPT.options_no "
		          + " group by OPT.options_pj_no "
		      + " ) ACH on PJ.pj_no = ACH.options_pj_no order by pj_no desc"
							+")TMP "
							+ "where pj_funding_start_date < sysdate "
							+ "and sysdate-pj_funding_end_date<=0 "
							+ "order by #1 desc" 
					+	" ) where rn between ? and ? " ;
			sql=sql.replace("#1", vo.getSort());
			Object[] param = {vo.startRow(), vo.endRow()};
			return jdbcTemplate.query(sql,pjListMapper,param);
	}
	
	// 카테고리별 정렬순
	@Override
	public List<PjListSearchVO> category(PjListSearchVO vo) {
		String sql ="select * from ( "  
		        + " select rownum rn, TMP.* from( "
		               + "select " 
		       + " PJ.*, "
		       + " nvl(ACH.total, 0), "
		      + " nvl(ACH.total, 0) / PJ.pj_target_money *100 achievement_rate "
		   + " from pj PJ "
		      + " left outer join ( "
		          + " select " 
		             + " OPT.options_pj_no, " 
		              + " sum(OPT.options_price) total "
		           +" from orders ORD inner join options OPT on ORD.orders_options_no = OPT.options_no "
		          + " group by OPT.options_pj_no "
		      + " ) ACH on PJ.pj_no = ACH.options_pj_no order by pj_no desc"
							+")TMP "
							+ "where pj_category in ? "
							+ "and pj_funding_start_date < sysdate "
							+ "and sysdate-pj_funding_end_date<=0" 
					+	" ) where rn between ? and ? " ;
		Object[] param = {vo.getCategory(), vo.startRow(), vo.endRow()};
		return jdbcTemplate.query(sql,pjListMapper,param);
	}
	
	//펀딩예정
	@Override
	public List<PjListSearchVO> prelaunching(PjListSearchVO vo) {
		String sql = "    select #1.* from ( ("
				+ "        select rownum rn, TMP.* from( "
				+ "                select "
				+ "        PJ.*,"
				+ "        nvl(ACH.total, 0),"
				+ "        nvl(ACH.total, 0) / PJ.pj_target_money *100 achievement_rate, "
			    + "		   IMG.pj_file_no "	
				+ "    from pj PJ"
				+ "        left outer join ("
				+ "            select "
				+ "                OPT.options_pj_no, "
				+ "                sum(OPT.options_price) total"
				+ "            from orders ORD inner join options OPT on ORD.orders_options_no = OPT.options_no"
				+ "            group by OPT.options_pj_no"
				+ "        ) ACH on PJ.pj_no = ACH.options_pj_no "
				+ "                    left outer join ("
				+ "             select B.pj_file_no ,B.pj_file_pj_no"
				+ "                    from"
				+ "                    pj_file B inner join files A"
				+ "                    on B.pj_file_no = A.files_no"
				+ "                where pj_file_classify = '대표'"
				+ "            ) IMG on PJ.pj_no = IMG.pj_file_pj_no "
				+ "				order by pj_no desc"
				+ "					)TMP where (sysdate-pj_funding_start_date<0) "
				+ "						order by sysdate-pj_funding_start_date desc ) #1"
				+ "				) where rn between ? and ?  ";
		sql=sql.replace("#1", vo.getSort());
		Object[] param = {vo.startRow(), vo.endRow()};
		return jdbcTemplate.query(sql,pjListMapper,param);
	}
	
	//펀딩중
	@Override
	public List<PjListSearchVO> ongoing(PjListSearchVO vo) {
		String sql = "    select #1.* from ( ("
				+ "        select rownum rn, TMP.* from( "
				+ "                select "
				+ "			PJ.*,"
				+ "        nvl(ACH.total, 0),"
				+ "        nvl(ACH.total, 0) / PJ.pj_target_money *100 achievement_rate"
				+ "    from pj PJ"
				+ "        left outer join ("
				+ "            select "
				+ "                OPT.options_pj_no, "
				+ "                sum(OPT.options_price) total"
				+ "            from orders ORD inner join options OPT on ORD.orders_options_no = OPT.options_no"
				+ "            group by OPT.options_pj_no"
				+ "        ) ACH on PJ.pj_no = ACH.options_pj_no order by pj_no desc"
				+ "					)TMP "
				+ "					where pj_funding_start_date < sysdate "
				+ "					and sysdate-pj_funding_end_date<=0 "
				+ "                 order by sysdate-pj_funding_end_date desc ) #1"
				+ "				) where rn between ? and ?  ";
		
		sql=sql.replace("#1", vo.getSort());
		Object[] param = {vo.startRow(), vo.endRow()};
		return jdbcTemplate.query(sql,pjListMapper,param);
	}
	
	//펀딩마감
	@Override
	public List<PjListSearchVO> finishing(PjListSearchVO vo) {
		String sql = "        select #1.* from ( ("
				+ "        select rownum rn, TMP.* from( "
				+ "                select "
				+ "        PJ.*,"
				+ "        nvl(ACH.total, 0),"
				+ "        nvl(ACH.total, 0) / PJ.pj_target_money *100 achievement_rate"
				+ "    from pj PJ"
				+ "        left outer join ("
				+ "            select "
				+ "                OPT.options_pj_no, "
				+ "                sum(OPT.options_price) total"
				+ "            from orders ORD inner join options OPT on ORD.orders_options_no = OPT.options_no"
				+ "            group by OPT.options_pj_no"
				+ "        ) ACH on PJ.pj_no = ACH.options_pj_no order by pj_no desc"
				+ "					)TMP "
				+ "					where sysdate-pj_funding_end_date>0"
				+ "                 order by sysdate-pj_funding_end_date desc ) #1"
				+ "				) where rn between ? and ?  ";
				
		sql=sql.replace("#1", vo.getSort());
		Object[] param = {vo.startRow(), vo.endRow()};
		return jdbcTemplate.query(sql,pjListMapper,param);
	}

	@Override
	public int count(PjListSearchVO vo) {
		if(vo.isSearch()) {
			return searchCount(vo);
		}
		else if(vo.isImminent()) {
			return listCount(vo);
		}
		else if(vo.isCategory()) {
			return categoryCount(vo);
		}
		else if(vo.isPrelaunching()) {
			return prelaunchingCount(vo);
		}
		else if(vo.isOngoing()) {
			return ongoingCount(vo);
		}
		else if(vo.isFinishing()) {
			return finishingCount(vo);
		}
		else {
			return listCount(vo);
		}
	}
	
	// 전체 데이터 갯수
	@Override
	public int listCount(PjListSearchVO vo) {
		String sql = "select count(*) from pj "
				+ "where pj_funding_start_date < sysdate "
				+ "and sysdate-pj_funding_end_date<=0";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	
	// 검색 데이터 갯수
	@Override
	public int searchCount(PjListSearchVO vo) {
		String sql = "select count(*) from pj "
				+ "where instr(#1,?)>0 "
				+ "and pj_funding_start_date < sysdate "
				+ "and sysdate-pj_funding_end_date<=0";
		sql = sql.replace("#1", vo.getType());
		Object[] param = {vo.getKeyword()};
		return jdbcTemplate.queryForObject(sql, int.class,param);
	}
	
	// 카테고리별 데이터 갯수
	@Override
	public int categoryCount(PjListSearchVO vo) {
		String sql = "select count(*) from pj "
				+ "where pj_category in ? "
				+ "and pj_funding_start_date < sysdate "
				+ "and sysdate-pj_funding_end_date<=0";
		Object[] param = {vo.getCategory()};
		return jdbcTemplate.queryForObject(sql, int.class,param);
	}
	
	// 펀딩 예정 데이터 갯수
	@Override
	public int prelaunchingCount(PjListSearchVO vo) {
		String sql = "select count(*) from pj where (sysdate-pj_funding_start_date<0) ";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	
	// 펀딩중 데이터 갯수
	@Override
	public int ongoingCount(PjListSearchVO vo) {
		String sql = " select count(*) from pj "
				+ "			where"
				+ "      (pj_funding_start_date < sysdate and "
				+ "        sysdate-pj_funding_end_date<=0 )";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	
	// 펀딩 마감 데이터 갯수
	@Override
	public int finishingCount(PjListSearchVO vo) {
		String sql = "select count(*) from pj where sysdate-pj_funding_end_date>0";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	


//	@Override
//	public void insert(PjDto pjDto) {//	1      2				3			4			5			6				7						8					9				1 2 3 4 5 6 7 8 9
//		String sql ="insert into Pj(PJ_NO, PJ_SELLER_MEM_NO, PJ_CATEGORY, PJ_NAME, PJ_SUMMARY, PJ_TARGET_MONEY, PJ_FUNDING_START_DATE, PJ_FUNDING_END_DATE, PJ_END_DATE) values(?,?,?,?,?,?,?,?,?)";
//		Object[] param = {pjDto.getPjNo(), pjDto.getPjSellerMemNo(),pjDto.getPjCategory(),pjDto.getPjName(),pjDto.getPjSummary(),pjDto.getPjTargetMoney(),pjDto.getPjFundingStartDate(),pjDto.getPjFundingEndDate(),pjDto.getPjEndDate()};
//		//							1					2						3				4 					5							6							7							8					9
//		jdbcTemplate.update(sql, param);
		
//	}
	@Override
	public void insert(PjDto pjDto) {
		String sql ="insert into Pj("
					+ "PJ_NO, "
					+ "PJ_SELLER_MEM_NO, "
					+ "PJ_CATEGORY, "
					+ "PJ_NAME, PJ_SUMMARY, "
					+ "PJ_TARGET_MONEY, "
					+ "PJ_FUNDING_START_DATE, "
					+ "PJ_FUNDING_END_DATE, "
					+ "PJ_END_DATE"
				+ ") values(?,?,?,?,?,?,?,?,?)";
		Object[] param = {
				pjDto.getPjNo(), 
				pjDto.getPjSellerMemNo(),
				pjDto.getPjCategory(),
				pjDto.getPjName(),
				pjDto.getPjSummary(),
				pjDto.getPjTargetMoney(),
				pjDto.getPjFundingStartDate(),
				pjDto.getPjFundingEndDate(),
				pjDto.getPjEndDate()
		};
		jdbcTemplate.update(sql, param);
		
	}
	
	private RowMapper<PjListSearchVO> pjListMapper = new RowMapper<>() {

		@Override
		public PjListSearchVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			return PjListSearchVO.builder()
					.pjNo(rs.getInt("pj_no"))
					.pjSellerMemNo(rs.getInt("pj_seller_mem_no"))
					.pjCategory(rs.getString("pj_category"))
					.pjName(rs.getString("pj_name"))
					.pjSummary(rs.getString("pj_summary"))
					.pjTargetMoney(rs.getInt("pj_target_money"))
					.pjFundingStartDate(rs.getDate("pj_funding_start_date"))
					.pjFundingEndDate(rs.getDate("pj_funding_end_date"))
					.pjEndDate(rs.getDate("pj_end_date"))
					.pjLikesNumber(rs.getInt("pj_likes_number"))
					.nvl(rs.getInt("nvl(ACH.total,0)"))
					.achievementRate(rs.getInt("achievement_rate"))
					.pjFileNo(rs.getInt("pj_file_no"))
				.build();
		}
	};
	
	//단일프로젝트 달성도추출용 extractor 필요
	private ResultSetExtractor<OrdersCalVO> calExtractor=new ResultSetExtractor<OrdersCalVO>() {

		@Override
		public OrdersCalVO extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(rs.next()) {
				return OrdersCalVO.builder()
						.optionsPjNo(rs.getInt("options_pj_no"))
						.pjNo(rs.getInt("pj_no"))
						.priceTotal(rs.getInt("price_total"))
						.pjTargetMoney(rs.getInt("pj_target_money"))
						.achievementRate(rs.getInt("achievement_rate"))
					.build();
			}
			
			else {
				return null;
			}
		}
		
		
	};

	//프로젝트번호로 검색하면 그 프로젝트의 결제총액, 달성율을 뽑아주는 메소드
	//취소날짜가 들어간 주문은 빼고 검색
	@Override
	public OrdersCalVO calVo(int pjNo) {
		String sql="select op.options_pj_no, sum(options_price) price_total, "
				+ "sum(options_price)/pj_target_money*100 achievement_rate, "
				+ "pj_target_money, pj_no from options op inner join "
				+ "orders ord on op.options_no=ord.orders_options_no inner join "
				+ "pj on options_pj_no = pj_no where pj_no=? "
				+ "and orders_cancel_date is null "
				+ "group by op.options_pj_no, pj_target_money,pj_no";
		Object[] param= {pjNo};
		return jdbcTemplate.query(sql, calExtractor, param);
	}
	
//	//support맵퍼
//	private RowMapper<SupportPjVO> supportMapper = new RowMapper<SupportPjVO>() {
//		
//		@Override
//		public SupportPjVO mapRow(ResultSet rs, int rowNum) throws SQLException {			
//			return SupportPjVO.builder().memNo(rs.getInt("mem_no"))
//					.optionsPjNo(rs.getInt("options_pj_no"))
//					.pjCategory(rs.getString(""));
//		}
//	};
	

//	@Override
//	public List<SupportPjVO> supportList() {
//		String sql ="select options_pj_no, mem.mem_no from options inner join orders on options_no=orders_options_no "
//				+ "inner join mem on mem.mem_no = orders.orders_mem_no";
//				
//		return jdbcTemplate.query(sql,supportMapper);
//	}
	
	//구매여부확인용
	private RowMapper<OrderCountVO> orderCountMapper=new RowMapper<OrderCountVO>() {

		@Override
		public OrderCountVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			return OrderCountVO.builder()
											.optionsPjNo(rs.getInt("options_pj_no"))
											.ordersMemNo(rs.getInt("orders_mem_no"))
											.build();
		}
		
	};
	
	private ResultSetExtractor<OrderCountVO> orderCountExtractor=new ResultSetExtractor<OrderCountVO>() {

		@Override
		public OrderCountVO extractData(ResultSet rs) throws SQLException, DataAccessException {
			if(rs.next()) {
				return OrderCountVO.builder()
						.optionsPjNo(rs.getInt("options_pj_no"))
						.ordersMemNo(rs.getInt("orders_mem_no"))
						.build();
			}
			else {
				return null;
			}
		}
	};
	
	//이 프로젝트를 구매한 회원이 몇명인가
	@Override
	public int orderCountAll(int pjNo) {
		String sql="select count(*) from(select orders.orders_no, orders.orders_mem_no, orders.orders_options_no, orders.orders_cancel_date, options.options_no, options.options_pj_no from orders left outer join options on orders.orders_options_no=options.options_no) where options_pj_no=? and orders_cancel_date is null";
		Object[] param= {pjNo};
		return jdbcTemplate.queryForObject(sql, int.class,param);
	}

	//구매여부확인(int 1일 경우 이력있음, 0일 경우 없음)
	//취소상태인 주문은 빼고 검색
@Override
public int orderCount(OrderCountVO vo) {
	String sql="select count(*) from (select options.options_no, "
			+ "options.options_pj_no, orders.orders_options_no, "
			+ "orders.orders_mem_no, orders.orders_cancel_date from options "
			+ "left outer join orders on options.options_no=orders.orders_options_no) "
			+ "where options_pj_no=? and orders_mem_no=? "
			+ "and orders_cancel_date is null";
	Object[] param={vo.getOptionsPjNo(), vo.getOrdersMemNo()};
	return jdbcTemplate.queryForObject(sql, int.class,param);
}


	@Override
	public int sequence() {
	String sql="select PJ_SEQ.nextval from dual";
	int pjSeqNo = jdbcTemplate.queryForObject(sql, int.class);
	return pjSeqNo;
}
	
//오늘부터 프로젝트 마감일까지 며칠남았는지(결제가능기간)
@Override
public float dateCount(int pjNo) {
	String sql="select pj_funding_end_date-sysdate from pj where pj_no=?";
	Object[] param= {pjNo};
	return jdbcTemplate.queryForObject(sql, float.class, param);
}

//특정 판매자가 개설한 모든 프로젝트
@Override
public List<PjDto> selectSeller(int pjSellerMemNo) {
	String sql="select*from pj where pj_seller_mem_no=?";
	Object[] param= {pjSellerMemNo};
	return jdbcTemplate.query(sql, mapper, param);
}






}