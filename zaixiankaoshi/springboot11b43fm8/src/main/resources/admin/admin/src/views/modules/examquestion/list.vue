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
        <el-row class="actions" :style='{"flexWrap":"wrap","margin":"20px 0","display":"flex"}'>
		  <el-button class="add" :style='{"border":"2px solid #d4d8df","cursor":"pointer","padding":"0 10px","margin":"4px","outline":"none","color":"#8e9db5","borderRadius":"4px","background":"#fff","width":"auto","fontSize":"14px","height":"40px"}' type="success" @click="addOrUpdateHandler()">
		  	<span class="icon iconfont icon-tianjia14" :style='{"margin":"0 2px","fontSize":"14px","color":"#8a99b2","height":"40px"}'></span>
		  	添加
		  </el-button>
		  <el-button class="del" :disabled="dataListSelections.length <= 0" :style='{"border":"1px solid #d4d8df","cursor":"pointer","padding":"0 10px","margin":"4px","outline":"none","color":"#8e9db5","borderRadius":"4px","background":"#fff","width":"auto","fontSize":"14px","height":"40px"}' type="danger" @click="deleteHandler()">
		  	<span class="icon iconfont icon-shanchu8" :style='{"margin":"0 2px","fontSize":"14px","color":"#8e9db5","height":"40px"}'></span>
		  	删除
		  </el-button>
          <download-excel v-if="isAuth('examquestion','导出')" class = "export-excel-wrapper" :data = "dataList" :fields = "json_fields" name = "试卷题目.xls">
            <!-- 导出excel -->
			<el-button class="btn18" type="success">
				<span class="icon iconfont icon-daochu1" :style='{"margin":"0 2px","fontSize":"14px","color":"#8b9ab3","height":"40px"}'></span>
				导出
			</el-button>
          </download-excel>
		  <el-button class="btn18" v-if="isAuth('examquestion','打印')" type="success" @click="printJson">
		  	<span class="icon iconfont icon-dayin2" :style='{"margin":"0 2px","fontSize":"14px","color":"#8b9ab3","height":"40px"}'></span>
		  	打印
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
          <el-table-column
		    :resizable='true' :sortable='false'
            width="300"
            prop="papername"
            header-align="center"
            align="center"
            sortable
            label="在线测试"
          ></el-table-column>
          <el-table-column
		    :resizable='true' :sortable='false'
            width="300"
            prop="questionname"
            header-align="center"
            align="center"
            sortable
            label="测试试题名称"
          ></el-table-column>
          <el-table-column :resizable='true' :sortable='false' prop="score" header-align="center" align="center" sortable label="分值"></el-table-column>
          <el-table-column :resizable='true' :sortable='false' prop="answer" header-align="center" align="center" sortable label="答案"></el-table-column>
          <el-table-column :resizable='true' :sortable='false' prop="type" header-align="center" align="center" sortable label="类型">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.type==0">单选题</el-tag>
              <el-tag v-if="scope.row.type==1" type="info">多选题</el-tag>
              <el-tag v-if="scope.row.type==2" type="success">判断题</el-tag>
              <el-tag v-if="scope.row.type==3" type="warning">填空题</el-tag>
              <el-tag v-if="scope.row.type==4" type="danger">主观题</el-tag>
            </template>
          </el-table-column>
          <el-table-column
            header-align="center"
            align="center"
            width="150"
            label="操作"
          >
            <template slot-scope="scope">
			  <el-button class="edit" :style='{"border":"2px solid rgb(212, 216, 223)","cursor":"pointer","padding":"0 5px","margin":"0 5px 5px 0","outline":"none","color":"rgb(139, 154, 179)","borderRadius":"4px","background":"rgb(255, 255, 255)","width":"auto","fontSize":"13px","height":"32px"}' type="success" @click="addOrUpdateHandler(scope.row.id)">
			  	<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"rgb(139, 154, 179)","display":"none","height":"40px"}'></span>
			  	修改
			  </el-button>
			  <el-button class="del" :style='{"border":"2px solid rgb(212, 216, 223)","cursor":"pointer","padding":"0 5px","margin":"0 5px 5px 0","outline":"none","color":"rgb(139, 154, 179)","borderRadius":"4px","background":"rgb(255, 255, 255)","width":"auto","fontSize":"13px","height":"32px"}' type="primary" @click="deleteHandler(scope.row.id)">
			  	<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"rgb(139, 154, 179)","display":"none","height":"40px"}'></span>
			  	删除
			  </el-button>
            </template>
          </el-table-column>
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
    <!-- 添加/修改页面  将父组件的search方法传递给子组件-->
    <add-or-update v-else :parent="this" ref="addOrUpdate"></add-or-update>
  </div>
</template>
<script>
import AddOrUpdate from "./question-add-or-update";
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
      //导出excel
        json_fields: {
        "试卷名称": "papername",    //常规字段
        "题目名称": "questionname",    //常规字段
        "题目类型": {
                        field: 'type',
                        callback: value => {
                          let str = ''
                          switch (value) {
                            case 0:
                              str = '单选题'
                              break
                            case 1:
                              str = '多选题'
                              break
                            case 2:
                              str = '判断题'
                              break
                            case 3:
                              str = '填空题'
                              break
                            case 4:
                              str = '主观题'
                              break
                          }
                          return str
                        }
                    },
        "选项": "options",    //常规字段
        "分值": "score",    //常规字段
        "正确答案": "answer",    //常规字段
        "答案解析": "analysis",    //常规字段
        },
        json_meta: [
          [
            {
              " key ": " charset ",
              " value ": " utf- 8 "
            }
          ]
        ]
    };
  },
  mounted() {
    this.init();
    this.getDataList();
  },
  components: {
    AddOrUpdate
  },
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
        sort: "id"
      };
      if(this.searchForm.papername){
        params['papername'] = `%${this.searchForm.papername}%`
      }
      if(this.searchForm.questionname){
        params['questionname'] = `%${this.searchForm.questionname}%`
      }
      this.$http({
        url: this.$api.examquestionpage,
        method: "get",
        params: params
      }).then(({ data }) => {
        if (data && data.code === 0) {
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
    },
    // 添加/修改
    addOrUpdateHandler(id) {
      this.showFlag = true;
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id);
      });
    },
    // 删除
    deleteHandler(id) {
      var ids = id
        ? [Number(id)]
        : this.dataListSelections.map(item => {
            return Number(item.id);
          });
      this.$confirm(`确定进行[${id ? "删除" : "批量删除"}]操作?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$http({
          url: this.$api.examquestiondelete,
          method: "post",
          data: ids
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.$message({
              message: "操作成功",
              type: "success",
              duration: 1500,
              onClose: () => {
                this.search();
              }
            });
          } else {
            this.$message.error(data.msg);
          }
        });
      });
    },
    async printJson() {
      //通过getdata调用后台接口获取数据封装到res
      this.list = this.dataList;
      let data = []
      for (let i=0; i < this.list.length; i++) {
          let typeName = '';
          if(this.list[i].type==0) {
              typeName = '单选题'
          } else if(this.list[i].type==1) {
              typeName = '多选题'
          } else if(this.list[i].type==2) {
              typeName = '判断题'
          } else if(this.list[i].type==3) {
              typeName = '填空题'
          } else if(this.list[i].type==4) {
              typeName = '主观题'
          }
          data.push({
          papername: this.list[i].papername,
          questionname: this.list[i].questionname,
          type: typeName,
          options: this.list[i].options,
          score: this.list[i].score,
          answer: this.list[i].answer,
          analysis: this.list[i].analysis,
        })
      }
      printJS({
        printable: data,
        properties: [
      {
        field: 'papername',
        displayName: '试卷名称',
        columnSize: 1
      },
      {
        field: 'questionname',
        displayName: '题目名称',
        columnSize: 1
      },
      {
        field: 'type',
        displayName: '题目类型',
        columnSize: 1
      },
      {
        field: 'options',
        displayName: '选项',
        columnSize: 1
      },
      {
        field: 'score',
        displayName: '分值',
        columnSize: 1
      },
      {
        field: 'answer',
        displayName: '正确答案',
        columnSize: 1
      },
      {
        field: 'analysis',
        displayName: '答案解析',
        columnSize: 1
      },
        ],
        type: 'json',
        header: '试卷题目',
        // 样式设置
        gridStyle: 'border: 2px solid #3971A5;',
        gridHeaderStyle: 'color: red;  border: 2px solid #3971A5;'
      })
    },
  }
};
</script>
<style lang="scss" scoped>
    //导出excel
      .export-excel-wrapper{
        display: inline-block;
      }
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
	
	.center-form-pv .actions .add {
				border: 2px solid #d4d8df;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 10px;
				margin: 4px;
				outline: none;
				color: #8e9db5;
				background: #fff;
				width: auto;
				font-size: 14px;
				height: 40px;
			}
	
	.center-form-pv .actions .add:hover {
				color: #3388cb;
				border-color: #3388cb;
			}
	
	.center-form-pv .actions .del {
				border: 1px solid #d4d8df;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 10px;
				margin: 4px;
				outline: none;
				color: #8e9db5;
				background: #fff;
				width: auto;
				font-size: 14px;
				height: 40px;
			}
	
	.center-form-pv .actions .del:hover {
				border: 1px solid #3388cb;
				color: #3388cb;
			}
	
	.center-form-pv .actions .btn18 {
				border: 2px solid #d4d8df;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 10px;
				margin: 4px;
				outline: none;
				color: #8b9ab3;
				background: #fff;
				width: auto;
				font-size: 14px;
				height: 40px;
			}
	
	.center-form-pv .actions .btn18:hover {
				border: 1px solid #3388cb;
				color: #3388cb;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .edit {
				border: 2px solid rgb(212, 216, 223);
				cursor: pointer;
				border-radius: 4px;
				padding: 0 5px;
				margin: 0 5px 5px 0;
				outline: none;
				color: rgb(139, 154, 179);
				background: rgb(255, 255, 255);
				width: auto;
				font-size: 13px;
				height: 32px;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .edit:hover {
				border: 2px solid #409eff;
				color: #409eff;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .del {
				border: 2px solid rgb(212, 216, 223);
				cursor: pointer;
				border-radius: 4px;
				padding: 0 5px;
				margin: 0 5px 5px 0;
				outline: none;
				color: rgb(139, 154, 179);
				background: rgb(255, 255, 255);
				width: auto;
				font-size: 13px;
				height: 32px;
			}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .del:hover {
				border: 2px solid #409eff;
				color:  #409eff;
			}
	
	.one .list1-edit {
				border: 1px solid rgb(212, 216, 223)  ;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 15px;
				margin: 0;
				outline: none;
				color:  rgb(142, 157, 181);
				background: #fff;
				width: auto;
				font-size: 14px;
				height: 40px;
			}
	
	.one .list1-edit:hover {
				border: 1px solid #2b68b5;
				color: #2b68b5;
			}
	
	.one .list1-del {
				border: 1px solid rgb(212, 216, 223)  ;
				cursor: pointer;
				border-radius: 4px;
				padding: 0 15px;
				margin: 0;
				outline: none;
				color: rgb(142, 157, 181);
				background: #fff;
				width: auto;
				font-size: 14px;
				height: 40px;
			}
	
	.one .list1-del:hover {
				border: 1px solid #2b68b5;
				color:  #2b68b5;
			}
</style>
