<template>
  <div class="main-content" :style='{"width":"100%","padding":"30px","margin":"0","overflow-y":"auto","background":"#fff","height":"auto"}'>
    <!-- 列表页 -->
    <template v-if="!showFlag">
      <el-form :style='{"margin":"0 0 20px"}' :inline="true" :model="searchForm" class="center-form-pv">
        <el-row :style='{"display":"block"}'>
			<div :style='{"margin":"0 10px 0 0","display":"inline-block"}'>
			  <label :style='{"margin":"0 10px 0 0","color":"#666","display":"none","lineHeight":"40px","fontSize":"14px","fontWeight":"500","height":"40px"}' class="item-label">在线测试</label>
			  <el-input v-model="searchForm.papername" placeholder="在线测试名称" clearable></el-input>
			</div>
			<div :style='{"margin":"0 10px 0 0","display":"inline-block"}'>
			  <label :style='{"margin":"0 10px 0 0","color":"#666","display":"none","lineHeight":"40px","fontSize":"14px","fontWeight":"500","height":"40px"}' class="item-label">测试试题</label>
			  <el-input v-model="searchForm.questionname" placeholder="测试试题名称" clearable></el-input>
			</div>
			<el-button class="search" :style='{"border":"2px solid #3388cb","cursor":"pointer","padding":"0 24px","outline":"none","color":"#fff","borderRadius":"4px","background":"#3388cb","width":"auto","fontSize":"16px","height":"40px"}' type="success" @click="search()">
				<span class="icon iconfont icon-chakan12" :style='{"margin":"0 2px","fontSize":"16px","color":"#fff","height":"40px"}'></span>
				查询
			</el-button>
		</el-row>
      </el-form>
	<div :style='{"width":"100%","padding":"0","fontSize":"12px"}'>
        <el-table
		  :stripe='false'
		  :style='{"width":"100%","padding":"0","borderColor":"#eee","borderStyle":"solid","borderWidth":"1px 0 0 1px","background":"#fff"}'
          :data="dataList"
          :border='true'
          v-loading="dataListLoading"
          @selection-change="selectionChangeHandler"
          style="width: 100%;"
        >
          <el-table-column :resizable='true' type="selection" header-align="center" align="center" width="50"></el-table-column>
          <el-table-column :resizable='true' :sortable='false' prop="username" header-align="center" align="center" label="姓名"></el-table-column>
          <el-table-column
		    :resizable='true' :sortable='false'
            prop="papername"
            header-align="center"
            align="center"
            label="在线测试"
          ></el-table-column>
          <el-table-column
		    :resizable='true' :sortable='false'
            prop="questionname"
            header-align="center"
            align="center"
            label="测试试题名称"
          ></el-table-column>
		  <el-table-column
			:resizable='true' :sortable='false'
		    prop="type"
		    header-align="center"
		    align="center"
		    label="测试试题类型"
		  >
		    <template slot-scope="scope">
		      <el-tag type="success" v-if="scope.row.type==0">单选题</el-tag>
		      <el-tag type="warning" v-if="scope.row.type==1">多选题</el-tag>
		      <el-tag type="info" v-if="scope.row.type==2">判断题</el-tag>
		      <el-tag type="primary" v-if="scope.row.type==3">填空题</el-tag>
		      <el-tag type="danger" v-if="scope.row.type==4">主观题</el-tag>
		    </template>
		  </el-table-column>
          <el-table-column :resizable='true' :sortable='false' prop="score" header-align="center" align="center" label="分值"></el-table-column>
          <el-table-column :resizable='true' :sortable='false' prop="right" header-align="center" align="center" label="正确答案"></el-table-column>
          <el-table-column
		    :resizable='true' :sortable='false'
            prop="myanswer"
            header-align="center"
            align="center"
            label="考生答案"
          ></el-table-column>
          <el-table-column
		    :resizable='true' :sortable='false'
            prop="analysis"
            header-align="center"
            align="center"
            label="测试试题分析"
          ></el-table-column>
          <el-table-column
		    :resizable='true' :sortable='false'
            prop="addtime"
            header-align="center"
            align="center"
            width="170"
            label="测试试卷时间"
          ></el-table-column>
        </el-table>
	</div>

        <el-pagination
          @size-change="sizeChangeHandle"
          @current-change="currentChangeHandle"
          :current-page="pageIndex"
          :page-sizes="[10, 50, 100, 200]"
          :page-size="pageSize"
          :total="totalPage"
          :layout="layouts.join()"
          prev-text="<"
          next-text=">"
          :hide-on-single-page="true"
          :style='{"width":"100%","padding":"0","margin":"20px 0 0","whiteSpace":"nowrap","color":"#333","fontWeight":"500"}'
        ></el-pagination>
    </template>
  </div>
</template>
<script>
	export default {
		data() {
			return {
				layouts: ["total","prev","pager","next","sizes","jumper"],
				searchForm: {
					key: ""
				},
				dataList: [],
				pageIndex: 1,
				pageSize: 10,
				totalPage: 0,
				dataListLoading: false,
				dataListSelections: [],
				showFlag: false,
				user: {}
			};
		},
		mounted() {
			this.$http({
				url: `${this.$storage.get("sessionTable")}/session`,
				method: "get"
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.user = data.data;
				} else {
					this.$message.error(data.msg);
				}
			});
			this.init();
			this.getDataList();
		},
		components: {},
		methods: {
			init() {},
			search() {
				this.pageIndex = 1;
				this.getDataList();
			},
			// 获取数据列表
			getDataList() {
				this.dataListLoading = true;
				var params = {
					page: this.pageIndex,
					limit: this.pageSize,
					sort: "id",
					myscore: 0,
					userid: this.id,
					ismark: 1
				};
				if (this.searchForm.papername) {
					params["papername"] = `%${this.searchForm.papername}%`;
				}
				if (this.searchForm.questionname) {
					params["questionname"] = `%${this.searchForm.questionname}%`;
				}
				this.$http({
					url: this.$api.examrecordpage,
					method: "get",
					params: params
				}).then(({ data }) => {
					if (data && data.code === 0) {
						let arr = []
						for(let x in data.data.list){
							if(data.data.list[x].type==0||data.data.list[x].type==2){
								arr = JSON.parse(data.data.list[x].options)
								for(let i in arr){
									if(data.data.list[x].answer == arr[i].code){
										data.data.list[x].right = arr[i].text
									}
								}
							}else if(data.data.list[x].type==1){
								arr = JSON.parse(data.data.list[x].options)
								data.data.list[x].answer.split(',').forEach(item=>{
									for(let i in arr){
										if (item == arr[i].code) {
											if (data.data.list[x].right) {
												data.data.list[x].right += ','
												data.data.list[x].right = data.data.list[x].right + arr[i].text
											}else{
												data.data.list[x].right = arr[i].text
											}
										}
									}
								})
							}else if(data.data.list[x].type==3){
								data.data.list[x].right = data.data.list[x].answer
							}else if(data.data.list[x].type==4){
								data.data.list[x].right = '略'
							}
						}
						this.dataList = data.data.list;
						this.totalPage = data.data.total;
					} else {
						this.dataList = [];
						this.totalPage = 0;
					}
					this.dataListLoading = false;
				});
			},
			// 每页数
			sizeChangeHandle(val) {
				this.pageSize = val;
				this.pageIndex = 1;
				this.getDataList();
			},
			// 当前页
			currentChangeHandle(val) {
				this.pageIndex = val;
				this.getDataList();
			},
			// 多选
			selectionChangeHandler(val) {
				this.dataListSelections = val;
			}
		}
	};
</script>
<style lang="scss" scoped>
.form-content {
	background: transparent;
}
.table-content {
	background: transparent;
}

	.center-form-pv {
		.el-input {
		  width: auto;
		}
	  .el-date-editor.el-input {
	    width: auto;
	  }
	}
	
	// form
	.center-form-pv .el-input /deep/ .el-input__inner {
				border: 2px solid #c9d0dc;
				border-radius: 20px;
				padding: 0 12px;
				color: #8696b0;
				width: 170px;
				font-size: 14px;
				height: 40px;
			}
	
	.center-form-pv .el-select /deep/ .el-input__inner {
				border: 2px solid #c9d0dc;
				border-radius: 20px;
				padding: 0 10px;
				outline: none;
				color: #8696b0;
				width: 170px;
				font-size: 14px;
				height: 40px;
			}
	
	.center-form-pv .el-date-editor /deep/ .el-input__inner {
				border: 2px solid #c9d0dc;
				border-radius: 20px;
				padding: 0 10px 0 30px;
				outline: none;
				color: #8696b0;
				width: 170px;
				font-size: 14px;
				height: 40px;
			}
	
	// table
	.el-table /deep/ .el-table__header-wrapper thead {
				color: #999;
				background: red;
				font-weight: 500;
				width: 100%;
			}
	
	.el-table /deep/ .el-table__header-wrapper thead tr {
				background: #eef3f7;
			}
	
	.el-table /deep/ .el-table__header-wrapper thead tr th {
				padding: 6px 0;
				color: #808080;
				background: #eef3f7;
				font-weight: normal;
				border-color: #ddd;
				border-width: 1px 1px 0 0;
				border-style: solid;
				text-align: left;
			}
	
	.el-table /deep/ .el-table__header-wrapper thead tr th .cell {
				padding: 0 2px;
				word-wrap: normal;
				word-break: break-all;
				white-space: normal;
				font-weight: bold;
				display: inline-block;
				vertical-align: middle;
				width: 100%;
				font-size: 12px;
				line-height: 24px;
				position: relative;
				text-overflow: ellipsis;
			}
	
	
	.el-table /deep/ .el-table__body-wrapper tbody {
				width: 100%;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr {
				background: #fff;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
				padding: 4px 0;
				color: #666;
				background: #fff;
				border-color: #eee;
				border-width: 1px 1px 0 0;
				border-style: solid;
				text-align: left;
			}
	
		
	.el-table /deep/ .el-table__body-wrapper tbody tr:hover td {
				padding: 4px 0;
				color: #444;
				background: #f6f6f6;
				border-color: #eee;
				border-width: 1px 1px 0 0;
				border-style: solid;
				text-align: left;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
				padding: 4px 0;
				color: #666;
				background: #fff;
				border-color: #eee;
				border-width: 1px 1px 0 0;
				border-style: solid;
				text-align: left;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .cell {
				padding: 0 2px;
				overflow: hidden;
				word-break: break-all;
				white-space: normal;
				font-size: 12px;
				line-height: 24px;
				text-overflow: ellipsis;
			}
	
	// pagination
	.main-content .el-pagination /deep/ .el-pagination__total {
				margin: 0 10px 0 0;
				color: #666;
				font-weight: 400;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-prev {
				border: none;
				border-radius: 2px;
				padding: 0;
				margin: 0 5px;
				color: #666;
				background: #f4f4f5;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				min-width: 35px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-next {
				border: none;
				border-radius: 2px;
				padding: 0;
				margin: 0 5px;
				color: #666;
				background: #f4f4f5;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				min-width: 35px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-prev:disabled {
				border: none;
				cursor: not-allowed;
				border-radius: 2px;
				padding: 0;
				margin: 0 5px;
				color: #C0C4CC;
				background: #f4f4f5;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .btn-next:disabled {
				border: none;
				cursor: not-allowed;
				border-radius: 2px;
				padding: 0;
				margin: 0 5px;
				color: #C0C4CC;
				background: #f4f4f5;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pager {
				padding: 0;
				margin: 0;
				display: inline-block;
				vertical-align: top;
			}
	
	.main-content .el-pagination /deep/ .el-pager .number {
				cursor: pointer;
				padding: 0 4px;
				margin: 0 5px;
				color: #666;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				border-radius: 2px;
				background: #f4f4f5;
				text-align: center;
				min-width: 30px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pager .number:hover {
				cursor: pointer;
				padding: 0 4px;
				margin: 0 5px;
				color: #409EFF;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				border-radius: 2px;
				background: #f4f4f5;
				text-align: center;
				min-width: 30px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pager .number.active {
				cursor: default;
				padding: 0 4px;
				margin: 0 5px;
				color: #FFF;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				border-radius: 2px;
				background: #2964af;
				text-align: center;
				min-width: 30px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes {
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input {
				margin: 0 5px;
				width: 100px;
				position: relative;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__inner {
				border: 1px solid #DCDFE6;
				cursor: pointer;
				padding: 0 25px 0 8px;
				color: #606266;
				display: inline-block;
				font-size: 13px;
				line-height: 28px;
				border-radius: 3px;
				outline: 0;
				background: #FFF;
				width: 100%;
				text-align: center;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input span.el-input__suffix {
				top: 0;
				position: absolute;
				right: 0;
				height: 100%;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__suffix .el-select__caret {
				cursor: pointer;
				color: #C0C4CC;
				width: 25px;
				font-size: 14px;
				line-height: 28px;
				text-align: center;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump {
				margin: 0 0 0 24px;
				color: #606266;
				display: inline-block;
				vertical-align: top;
				font-size: 13px;
				line-height: 28px;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input {
				border-radius: 3px;
				padding: 0 2px;
				margin: 0 2px;
				display: inline-block;
				width: 50px;
				font-size: 14px;
				line-height: 18px;
				position: relative;
				text-align: center;
				height: 28px;
			}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input .el-input__inner {
				border: 1px solid #DCDFE6;
				cursor: pointer;
				padding: 0 3px;
				color: #606266;
				display: inline-block;
				font-size: 14px;
				line-height: 28px;
				border-radius: 3px;
				outline: 0;
				background: #FFF;
				width: 100%;
				text-align: center;
				height: 28px;
			}
	
	.center-form-pv .search {
				border: 2px solid #3388cb;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 24px;
				outline: none;
				color: #fff;
				background: #3388cb;
				width: auto;
				font-size: 16px;
				height: 40px;
			}
	
	.center-form-pv .search:hover {
				opacity: 0.8;
			}
</style>
