<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>


<c:set var="login" value="${loginId != null}"></c:set>
<c:set var="admin" value="${mg == '관리자'}"></c:set>

<html>
	<head>
		<title>
			<c:choose>
				<c:when test="${param.title != null}">
					${param.title}
				</c:when>
				<c:otherwise>
					홈페이지
				</c:otherwise>
			</c:choose>
		</title>
		
            <link rel="preconnect" href="https://fonts.googleapis.com">
            <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
            <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
        
            <link rel="stylesheet" type="text/css" href="/css/reset.css">
            <link rel="stylesheet" type="text/css" href="/css/commons.css">
            
        
            <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"/>
        
            <!-- <link rel="stylesheet" type="text/css" href="/css/test.css"> -->
        
            <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>  
            

            <style>
                header{
                    width: 100%;                
                    position: relative;
                    border-bottom: 1px;
                }


                #top_menu {
                    float: right;
                    position: relative;
                    right: 10px;
                    top: 20px;  
                    padding: 10px;                  
                }

                nav {
                    border: none;
                }
                
                
                nav li{
                    float: left;
                    line-height: 3em;             
                    padding-left: 20px;
                    list-style: none;
                }
                
                nav li a{
                    color: black;
                    display: block;
                    text-align: center;
                    text-decoration: none;
                }
                
                nav li a:hover{
                    
                    color: #0072b2;
                }
            
                #logo_img {
                    margin: 10px;
                }

                #logo {
                    display: inline-block;
                    height: 70px;
                }
                
                #top_menu > a {
               		color: black;
                    display: inline-block;
                    text-align: center;
                    text-decoration: none;
                }
                
                #login_menu {
                	border: 1px;
                	}
                	
               .ghost {
                align-items: center;
                display: inline-block;
                padding: 25px 40px;
                margin:  320px 70px;
                border: 3px solid #d9cbb3;
                color: black;
                text-align: center; 
				
            }
            .ghost:hover {
                background-color:#d9cbb3;
                color:black;
            }

                
            </style>
        </head>
        <body>
            
            <!-- main은 영역의 시작과 끝을 의미 -->
            
                <!-- 상단 헤더 -->
                <header>
                    <div class="container-1400">
                        <div id="logo">
                        	<a href="/">
                            	<img src="/img/DoranMini.png" id="logo_img" width="170px" height="70px">
                            </a>
                        </div>
            
                        <div id="top_menu">
                        
                        	<c:choose>
						        <c:when test = "${login}">
						        	<button type="submit" class= "ghost" onclick="location.href='/seller/sellerjoin'">판매자 신청</button>
								</c:when>
							</c:choose>               
                        
                      
					          	<c:choose>
					         		<c:when test = "${login}">
							           <a class="dropdown-item" href="/mem/logout">로그아웃</a>							     
						          </c:when>
						          <c:otherwise>
						          <button class="btn btn-neutral">
			                            <a href="/mem/login">로그인</a> |
			                            <a href="/mem/join">가입</a>		
		                           </button>				          	  
						          </c:otherwise>
					      		</c:choose>
					      		
                      
					</div>					
                 </div>                 
              </header>
                        
                    <div class="container-1400">
                        <nav>
                            <ul>                                
                                <li><a href="#"><i class="fa-solid fa-bars"></i>&nbsp;전체 프로젝트</a></li>
                                <li><a href="http://localhost:8888/pj/list?sort=pj_likes_number">인기</a></li>
                                <li><a href="http://localhost:8888/pj/list?sort=pj_no">신규</a></li>
                                <li><a href="http://localhost:8888/pj/list?sort=pj_funding_end_date-sysdate">마감임박</a></li>
                                <li class="dropdown"><a class="dropdown-toggle" href="#">커뮤니티</a>
                                	<ul class="dropdown-menu">
								  	<li><a class="dropdown-item" href="/notice/list">공지사항</a></li>
								    <li><a class="dropdown-item" href="/board/list">도란도란</a></li>
								 </ul>
                                </li>
                                <li class="dropdown"><a class="dropdown-toggle" href="#">고객센터</a>
                                	<ul class="dropdown-menu">
								      <li><a class="dropdown-item" href="#">FAQ</a></li>
								      <li><a class="dropdown-item" href="#">1:1 문의</a></li>
								    </ul>
                                </li>
                            </ul>
                            
							     <!-- 검색창 -->
                                <div class="row right">
							        <form action = "list" method = "get">
							            <select name="type">
							                <option value="pj_name" <c:if test="${pjListSearchVo.type=='pj_name'}">selected</c:if>>프로젝트 이름</option>
							                <option value="pj_category" <c:if test="${pjListSearchVo.type=='pj_category'}">selected</c:if>>프로젝트 카테고리</option>
							            </select>
							                
							            <input type="search" name="keyword" placeholder="검색어" required value="${pjListSearchVo.keyword}">
							            <button type="submit">검색</button>
							        </form>
  								</div>    
                        </nav>
                        
                    </div>
                    <hr style="border:1px color= silver;" width="1400px">
                    <body>
                        
                    
    
                    