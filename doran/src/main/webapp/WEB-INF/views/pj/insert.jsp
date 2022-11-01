<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/WEB-INF/views/template/header2.jsp">
	<jsp:param value="${sellerdto.sellerMemNo} 프로젝트 올리기" name="title" />
</jsp:include>

<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/lightpick@1.6.2/css/lightpick.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
<style>
	.error-message {
		display:none;
	}
	.error-message.on {
		display:inline;
	}
</style>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/locale/ko.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/lightpick@1.6.2/lightpick.js"></script>
<script type="text/javascript">
</script>

<div class="container-800 mt-4 mb-4" id="pj-insert-app">
<form action="insert" method="post" enctype="multipart/form-data" @submit.prevent="sendForm">
	<div class="row center">
		<h1>신규 프로젝트 생성</h1>
	</div>
	
	<div class="row"><div class="col">
		<h2>프로젝트 정보&nbsp;<i class="fa-solid fa-asterisk text-danger"></i></h2>
	</div></div>
	
	<div class="row"><div class="col">
		<select class="form-control form-control-lg" name="pjCategory" v-model="data.pj_category">
			<option value="">카테고리 선택</option>
			<option>패션/잡화</option>
			<option>뷰티</option>
			<option>푸드</option>
			<option>홈/리빙</option>
			<option>테크/가전</option>
			<option>기타</option>
		</select>
		<span class="ms-2 text-danger error-message" :class="{on:!data.pj_category_valid}">필수 입력 항목입니다</span>
	</div></div>
	<div class="row"><div class="col">
		<div class="form-floating">
			<input name="pjName" type="text" class="form-control" placeholder="프로젝트명" v-model="data.pj_name">
			<label class="form-label text-secondary">프로젝트명</label>
			<span class="ms-2 text-danger error-message" :class="{on:!data.pj_name_valid}">필수 입력 항목입니다</span>
		</div>
	</div></div>
	<div class="row"><div class="col">
		<textarea class="form-control" name="pjSummary" placeholder="프로젝트 요약 설명" rows="5" v-model="data.pj_summary"></textarea>
		<span class="ms-2 text-danger error-message" :class="{on:!data.pj_summary_valid}">필수 입력 항목입니다</span>
	</div></div>
	<div class="row"><div class="col">
		<div class="form-floating">
			<input name="pjTargetMoney" type="text" class="form-control" placeholder="목표 금액" v-model.number="data.pj_target_money">
			<label class="form-label text-secondary">목표 금액(50만원 이상)</label>
			<span class="ms-2 text-danger error-message" :class="{on:!data.pj_target_money_valid}">목표금액은 500,000보다 커야 합니다</span>
		</div>
	</div></div>
	<div class="row">
		<div class="col">
			<div class="form-floating">
				<input name="pjFundingStartDate" type="text" class="form-control" placeholder="펀딩 시작일" @change="data.pj_funding_start_date = e.target.value">
				<label class="form-label text-secondary">펀딩 시작일(YYYY-MM-DD)</label>
				<span class="ms-2 text-danger error-message" :class="{on:!data.pj_funding_start_date_valid}">필수 입력 항목입니다</span>
			</div>
		</div>
		<div class="col">
			<div class="form-floating">
				<input name="pjFundingEndDate" type="text" class="form-control" placeholder="펀딩 종료일" @change="data.pj_funding_end_date = e.target.value">
				<label class="form-label text-secondary">펀딩 종료일(YYYY-MM-DD)</label>
				<span class="ms-2 text-danger error-message" :class="{on:!data.pj_funding_end_date_valid}">필수 입력 항목입니다</span>
			</div>
		</div>
	</div>
	<div class="row"><div class="col-6">
		<div class="form-floating">
			<input name="pjEndDate" type="text" class="form-control" placeholder="프로젝트 마감일" v-model="data.pj_end_date">
			<label class="form-label text-secondary">프로젝트 마감일(YYYY-MM-DD)</label>
			<span class="ms-2 text-danger error-message" :class="{on:!data.pj_end_date_valid}">필수 입력 항목입니다</span>
		</div>
	</div></div>
	
	<hr>
	
	<div class="row"><div class="col">
		<h2>옵션 정보</h2>
	</div></div>
	
	<div class="row" v-for="(option, index) in data.options" :key="index"><div class="col">
		<div class="row"><div class="col">
			<h3>
				옵션 {{index + 1}}&nbsp;
				<i class="fa-solid fa-asterisk text-danger" v-if="index == 0"></i>
				<button v-if="index > 0" type="button" class="btn btn-danger" @click="deleteOption(index)">
					<i class="fa-solid fa-minus"></i>제거
				</button>
			</h3>
		</div></div>
		
		<div class="row"><div class="col">
			<div class="form-floating">
				<input :name="'options['+index+'].optionsName'" type="text" class="form-control" placeholder="옵션이름" v-model="data.options[index].options_name">
				<label class="form-label text-secondary">옵션 이름</label>
				<span class="ms-2 text-danger error-message" :class="{on:!data.options[index].options_name_valid}">필수 입력 항목입니다</span>
			</div>
		</div></div>
		
		<div class="row"><div class="col">
			<div class="form-floating">
				<input :name="'options['+index+'].optionsPrice'" type="text" class="form-control" placeholder="옵션가격" v-model.number="data.options[index].options_price">
				<label class="form-label text-secondary">옵션 가격(원)</label>
				<span class="ms-2 text-danger error-message" :class="{on:!data.options[index].options_price_valid}">필수 입력 항목입니다</span>
			</div>
		</div></div>
		
		<div class="row"><div class="col">
			<div class="form-floating">
				<input :name="'options['+index+'].optionsStock'" type="text" class="form-control" placeholder="옵션재고" v-model.number="data.options[index].options_stock">
				<label class="form-label text-secondary">옵션 재고</label>
				<span class="ms-2 text-danger error-message" :class="{on:!data.options[index].options_stock_valid}">필수 입력 항목입니다</span>
			</div>
		</div></div>
		
		<div class="row"><div class="col">
			<select :name="'options['+index+'].optionsDeliveryPrice'" class="form-control form-control-lg" v-model.number="data.options[index].options_delivery_price">
				<option value="0">배송비 없음(+0원)</option>
				<option value="2500">배송비 추가(+2500원)</option>
			</select>
			<span class="ms-2 text-danger error-message" :class="{on:!data.options[index].options_delivery_price_valid}">필수 입력 항목입니다</span>
		</div></div>
	</div></div>
	
	<div class="row">
		<div class="col">
			<button class="btn btn-primary btn-lg w-100" type="button" @click="addOption">
				<i class="fa-solid fa-plus"></i>&nbsp;&nbsp;옵션 추가
			</button>
		</div>
	</div>
	
	<hr>
	
	<div class="row mt-4"><div class="col">
		<h2>첨부 이미지</h2>
	</div></div>
	
	<div class="row mt-4"><div class="col">
		<h3>
			배너&nbsp;<i class="fa-solid fa-asterisk text-danger"></i>
			<span class="ms-2 text-danger error-message" :class="{on:!data.pj_file_banner.valid}">배너 이미지는 필수 항목입니다</span>
		</h3>
	</div></div>
	<div class="row"><div class="col">
		<input type="file" name="pjFileBanner" class="form-control" @change="setPjFileBanner">
		
	</div></div>
	<div class="row"><div class="col">
		<img :src="data.pj_file_banner.src" width="120" height="120">
	</div></div>
	
	<div class="row mt-4"><div class="col">
		<h3>
			소개&nbsp;<i class="fa-solid fa-asterisk text-danger"></i>
			<span class="ms-2 text-danger error-message" :class="{on:!data.pj_file_intro.valid}">소개 이미지는 필수 항목입니다</span>
		</h3>
	</div></div>
	<div class="row"><div class="col">
		<input type="file" name="pjFileIntro" class="form-control" @change="setPjFileIntro">
		
	</div></div>
	<div class="row"><div class="col">
		<img :src="data.pj_file_intro.src" width="120" height="120">
	</div></div>
	
	<div class="row mt-4"><div class="col">
		<h3>
			대표&nbsp;<i class="fa-solid fa-asterisk text-danger"></i>
			<span class="ms-2 text-danger error-message" :class="{on:!data.pj_file_mains_valid}">대표 이미지는 한 개 이상 필요합니다</span>
		</h3>
	</div></div>
	<div class="row"><div class="col">
		<input type="file" name="pjFileMains" class="form-control" multiple @change="setPjFileMains">
	</div></div>
	<div class="row"><div class="col-2 p-2" v-for="(file, index) in this.data.pj_file_mains" :key="index">
		<img :src="file.src" width="120" height="120">
	</div></div>
	
	<hr>
	<div class="row"><div class="col right">
		<button type="submit" class="btn btn-success btn-lg">프로젝트 생성</button>
	</div></div>
	
</form>
</div>

<script src="https://unpkg.com/vue@3.2.36/dist/vue.global.js"></script>
<script>
	Vue.createApp({
		data(){
			return {
				picker1:null,
				picker2:null,
				optionTemplate:{
					options_name:"",
					options_name_valid:true,
					options_price:0,
					options_price_valid:true,
					options_stock:0,
					options_stock_valid:true,
					options_delivery_price:0,
					options_delivery_price_valid:true,
				},
				data:{
					pj_category:"",
					pj_category_valid:true,
					pj_name:"",
					pj_name_valid:true,
					pj_summary:"",
					pj_summary_valid:true,
					pj_target_money:0,//50만이상
					pj_target_money_valid:true,
					pj_funding_start_date:"",
					pj_funding_start_date_valid:true,
					pj_funding_end_date:"",
					pj_funding_end_date_valid:true,
					pj_end_date:"",
					pj_end_date_valid:true,
					options:[
						{
							options_name:"",
							options_name_valid:true,
							options_price:0,//0이상
							options_price_valid:true,
							options_stock:0,//0이상
							options_stock_valid:true,
							options_delivery_price:0,//0아니면 2500
							options_delivery_price_valid:true,
						}
					],
					pj_file_banner:{
						file:null,
						src:"https://via.placeholder.com/200?text=IMAGE",
						valid:true,
					},
					pj_file_intro:{
						file:null,
						src:"https://via.placeholder.com/200?text=IMAGE",
						valid:true,
					},
					pj_file_mains:[],
					pj_file_mains_valid:true,
				},
				defaultImageURL:"https://via.placeholder.com/300?text=IMAGE",
			};
		},
		computed:{},
		methods:{
			addOption(){
				this.data.options.push(Object.assign({}, this.optionTemplate));
			},
			deleteOption(index){
				this.data.options.splice(index, 1);
			},
			setPjFileBanner(e){
				const file = e.target.files[0];
				this.data.pj_file_banner.file = file;
				this.data.pj_file_banner.src = file ? URL.createObjectURL(file) : this.defaultImageURL;
			},
			setPjFileIntro(e){
				const file = e.target.files[0];
				this.data.pj_file_intro.file = file;
				this.data.pj_file_intro.src = file ? URL.createObjectURL(file) : this.defaultImageURL;
			},
			setPjFileMains(e){
				var files = e.target.files;
				this.data.pj_file_mains = [];
				for(var i=0; i < files.length; i++){
					this.data.pj_file_mains.push({
						file:files[i],
						src:URL.createObjectURL(files[i]),
					});
				}
			},
			emptyInputCheck(value){
				return value.trim().length > 0;
			},
			validDateCheck(value) {
				var datePattern = /(19|20)[0-9]{2}-((02-(0[1-9]|[12][0-9]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))/;
				var valid = datePattern.test(value);
				console.log(value, valid);
				return valid;
			},
			numberBetween(value, begin, end){
				var valid = value >= begin;
				if(end) {
					valid &&= value <= end;
				}
				return valid;
			},
			emptyFileCheck(value){
				return !value.file;
			},
			emptyFilesCheck(values){ 
				return values.length == 0;
			},
			sendForm(e){
				var isAllValid = true;
				this.data.pj_category_valid = this.emptyInputCheck(this.data.pj_category);
				isAllValid = isAllValid && this.data.pj_category_valid;
				this.data.pj_name_valid = this.emptyInputCheck(this.data.pj_name);
				isAllValid = isAllValid && this.data.pj_name_valid;
				this.data.pj_summary_valid = this.emptyInputCheck(this.data.pj_summary);
				isAllValid = isAllValid && this.data.pj_summary_valid;
				this.data.pj_target_money_valid = this.numberBetween(this.data.pj_target_money, 500000);
				isAllValid = isAllValid && this.data.pj_target_money;
				this.data.pj_funding_start_date_valid = this.validDateCheck(this.data.pj_funding_start_date);
				isAllValid = isAllValid && this.data.pj_funding_start_date;
				this.data.pj_funding_end_date_valid = this.validDateCheck(this.data.pj_funding_end_date);
				isAllValid = isAllValid && this.data.pj_funding_end_date_valid;
				this.data.pj_end_date_valid = this.validDateCheck(this.data.pj_end_date);
				isAllValid = isAllValid && this.data.pj_end_date_valid;
				for(var i=0; i < this.data.options.length; i++){
					this.data.options[i].options_name_valid = this.emptyInputCheck(this.data.options[i].options_name);
					isAllValid = isAllValid && this.data.options[i].options_name_valid;
					this.data.options[i].options_price_valid = this.numberBetween(this.data.options[i].options_price, 0);
					isAllValid = isAllValid && this.data.options[i].options_price_valid;
					this.data.options[i].options_stock_valid = this.numberBetween(this.data.options[i].options_stock, 0);
					isAllValid = isAllValid && this.data.options[i].options_stock_valid;
					this.data.options[i].options_delivery_price_valid = this.data.options[i].options_delivery_price == 0 
																										|| this.data.options[i].options_delivery_price == 2500;
					isAllValid = isAllValid && this.data.options[i].options_delivery_price_valid;
				}
				this.data.pj_file_banner.valid = !!this.data.pj_file_banner.file;
				isAllValid = isAllValid && this.data.pj_file_banner.valid;
				this.data.pj_file_intro.valid = !!this.data.pj_file_intro.file;
				isAllValid = isAllValid && this.data.pj_file_intro.valid;
				this.data.pj_file_mains_valid = this.data.pj_file_mains.length > 0;
				isAllValid = isAllValid && this.data.pj_file_mains_valid;
				
				if(isAllValid){
					e.target.submit();
				}
			}
		},
		created(){
			
		},
		mounted(){
			var app = this;
			this.picker1 = new Lightpick({
				field:document.querySelector("[name=pjFundingStartDate]"),
				secondField:document.querySelector("[name=pjFundingEndDate]"),
				format:"YYYY-MM-DD",
				minDate:moment(),
				numberOfMonths:2,
				onSelect:function(start, end){
					if(!start || !end){
						app.data.pj_funding_start_date = "";
						app.data.pj_funding_end_date = "";
					}
					else {
						app.data.pj_funding_start_date = start.format("YYYY-MM-DD");
						app.data.pj_funding_end_date = end.format("YYYY-MM-DD");
						app.data.pj_end_date = end.format("YYYY-MM-DD");
						app.picker2.setDate(end);
						app.picker2.reloadOptions({
							minDate:end,
						});
					}
				},
			});
			this.picker2 = new Lightpick({
				field:document.querySelector("[name=pjEndDate]"),
				format:"YYYY-MM-DD",
				minDate:moment(),
				numberOfMonths:2,
				onSelect:function(day){
					if(!day){
						app.data.pj_end_date = "";
					}
					else {
						app.data.pj_end_date = day.format("YYYY-MM-DD");
					}
				}
			});
		},
	}).mount("#pj-insert-app");
</script>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
