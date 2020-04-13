<template>
	<el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
		<el-row>
			<el-col :span="8">
				<el-form-item label="名称">
					<el-date-picker
				      v-model="value2"
				      align="right"
				      type="date"
				      placeholder="选择日期"
				      :picker-options="pickerOptions1">
				    </el-date-picker>
				</el-form-item>
			</el-col>
			<el-col :span="8">
				<el-form-item label="多日期">
					<el-date-picker
				      v-model="value7"
				      type="daterange"
				      align="right"
				      unlink-panels
				      range-separator="至"
				      start-placeholder="开始日期"
				      end-placeholder="结束日期"
				      :picker-options="pickerOptions2">
				    </el-date-picker>
				</el-form-item>
			</el-col>
			<el-col :span="8">
				<el-form-item label="下拉多选">
					<el-select ref="selectTree" v-model="value" placeholder="请选择" size="100%">
						<el-option :value='value'>
							<el-tree
							  node-key="id"
							  class="filter-tree"
							  :data="data2"
							  :props="defaultProps"
							  show-checkbox
							  :filter-node-method="filterNode"
							  @check-change="handleCheckChange"
							  ref="tree2">
							</el-tree>
							<el-form-item class='text-center'>
								<el-button type="primary" @click.prevent="clear">清空全部</el-button>
								<el-button type="primary" @click.prevent="enter">查询</el-button>
							</el-form-item>
						</el-option>
					</el-select>
					
				</el-form-item>
			</el-col>
			<el-col :span="8">
				<el-form-item label="掉行">
					<el-input v-model="formLabelAlign.pre"></el-input>
				</el-form-item>
			</el-col>
			<el-col :span="16">
				<el-form-item class='text-right'>
					<el-button type="primary" @click="onSubmit">清空全部</el-button>
					<el-button type="primary" @click="onSubmit">查询</el-button>
				</el-form-item>
			</el-col>
		</el-row>
		<div class="calendar-list-container">
		    <div class="filter-container">
		      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" placeholder="标题" v-model="listQuery.title">
		      </el-input>
		      <el-select clearable style="width: 90px" class="filter-item" v-model="listQuery.importance" placeholder="重要性">
		        <el-option v-for="item in importanceOptions" :key="item" :label="item" :value="item">
		        </el-option>
		      </el-select>
		      <el-select clearable class="filter-item" style="width: 130px" v-model="listQuery.type" placeholder="类型">
		        <el-option v-for="item in  calendarTypeOptions" :key="item.key" :label="item.display_name+'('+item.key+')'" :value="item.key">
		        </el-option>
		      </el-select>
		      <el-select @change='handleFilter' style="width: 140px" class="filter-item" v-model="listQuery.sort">
		        <el-option v-for="item in sortOptions" :key="item.key" :label="item.label" :value="item.key">
		        </el-option>
		      </el-select>
		      <el-button class="filter-item" type="primary" v-waves icon="el-icon-search" @click="handleFilter">搜索</el-button>
		      <el-button class="filter-item" style="margin-left: 10px;" @click="handleCreate" type="primary" icon="el-icon-edit">添加</el-button>
		      <el-button class="filter-item" type="primary" :loading="downloadLoading" v-waves icon="el-icon-download" @click="handleDownload">导出</el-button>
		      <el-checkbox class="filter-item" style='margin-left:15px;' @change='tableKey=tableKey+1' v-model="showReviewer">审核人</el-checkbox>
		    </div>

		    <el-table :key='tableKey' :data="list" v-loading="listLoading" element-loading-text="给我一点时间" border fit highlight-current-row
		      style="width: 100%">
		      <el-table-column align="center" sortable label="序号" width="65">
		        <template slot-scope="scope">
		          <span>{{scope.row.id}}</span>
		        </template>
		      </el-table-column>
		      <el-table-column width="150px" sortable align="center" label="时间">
		        <template slot-scope="scope">
		          <span>{{scope.row.timestamp | parseTime('{y}-{m}-{d} {h}:{i}')}}</span>
		        </template>
		      </el-table-column>
		      <el-table-column min-width="150px" sortable label="标题">
		        <template slot-scope="scope">
		          <span class="link-type" @click="handleUpdate(scope.row)">{{scope.row.title}}</span>
		          <el-tag>{{scope.row.type | typeFilter}}</el-tag>
		        </template>
		      </el-table-column>
		      <el-table-column width="110px" sortable align="center" label="作者">
		        <template slot-scope="scope">
		          <span>{{scope.row.author}}</span>
		        </template>
		      </el-table-column>
		      <el-table-column width="110px" v-if='showReviewer' sortable align="center" label="重要性">
		        <template slot-scope="scope">
		          <span style='color:red;'>{{scope.row.reviewer}}</span>
		        </template>
		      </el-table-column>
		      <el-table-column width="80px" sortable label="阅读数">
		        <template slot-scope="scope">
		          <svg-icon v-for="n in +scope.row.importance" icon-class="star" class="meta-item__icon" :key="n"></svg-icon>
		        </template>
		      </el-table-column>
		      <el-table-column align="center" sortable label="状态" width="95">
		        <template slot-scope="scope">
		          <span v-if="scope.row.pageviews" class="link-type" @click='handleFetchPv(scope.row.pageviews)'>{{scope.row.pageviews}}</span>
		          <span v-else>0</span>
		        </template>
		      </el-table-column>
		      <el-table-column class-name="status-col" sortable label="操作" width="100">
		        <template slot-scope="scope">
		          <el-tag :type="scope.row.status | statusFilter">{{scope.row.status}}</el-tag>
		        </template>
		      </el-table-column>
		      <el-table-column align="center" label="操作" sortable width="230" class-name="small-padding fixed-width">
		        <template slot-scope="scope">
		          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
		          <el-button v-if="scope.row.status!='published'" size="mini" type="success" @click="handleModifyStatus(scope.row,'published')">发布
		          </el-button>
		          <el-button v-if="scope.row.status!='draft'" size="mini" @click="handleModifyStatus(scope.row,'draft')">草稿</el-button>
		          <el-button v-if="scope.row.status!='deleted'" size="mini" type="danger" @click="handleModifyStatus(scope.row,'deleted')">删除</el-button>
		        </template>
		      </el-table-column>
		    </el-table>

		    <div class="pagination-container">
		      <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="listQuery.page"
		        :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total">
		      </el-pagination>
		    </div>

		    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false">
		      <el-form :rules="rules" ref="dataForm" :model="temp" label-position="left" label-width="70px" style='width: 400px; margin-left:50px;'>
		        <el-form-item :label="$t('table.type')" prop="type">
		          <el-select class="filter-item" v-model="temp.type" placeholder="Please select">
		            <el-option v-for="item in  calendarTypeOptions" :key="item.key" :label="item.display_name" :value="item.key">
		            </el-option>
		          </el-select>
		        </el-form-item>
		        <el-form-item :label="$t('table.date')" prop="timestamp">
		          <el-date-picker v-model="temp.timestamp" type="datetime" placeholder="Please pick a date">
		          </el-date-picker>
		        </el-form-item>
		        <el-form-item :label="$t('table.title')" prop="title">
		          <el-input v-model="temp.title"></el-input>
		        </el-form-item>
		        <el-form-item :label="$t('table.status')">
		          <el-select class="filter-item" v-model="temp.status" placeholder="Please select">
		            <el-option v-for="item in  statusOptions" :key="item" :label="item" :value="item">
		            </el-option>
		          </el-select>
		        </el-form-item>
		        <el-form-item :label="$t('table.importance')">
		          <el-rate style="margin-top:8px;" v-model="temp.importance" :colors="['#99A9BF', '#F7BA2A', '#FF9900']" :max='3'></el-rate>
		        </el-form-item>
		        <el-form-item :label="$t('table.remark')">
		          <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" placeholder="Please input" v-model="temp.remark">
		          </el-input>
		        </el-form-item>
		      </el-form>
		      <div slot="footer" class="dialog-footer">
		        <el-button @click="dialogFormVisible = false">取消</el-button>
		        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">确定</el-button>
		        <el-button v-else type="primary" @click="updateData">确定</el-button>
		      </div>
		    </el-dialog>

		    <el-dialog title="Reading statistics" :visible.sync="dialogPvVisible" :close-on-click-modal="false">
		      <el-table :data="pvData" border fit highlight-current-row style="width: 100%">
		        <el-table-column prop="key" label="Channel"> </el-table-column>
		        <el-table-column prop="pv" label="Pv"> </el-table-column>
		      </el-table>
		      <span slot="footer" class="dialog-footer">
		        <el-button type="primary" @click="dialogPvVisible = false">{{$t('table.confirm')}}</el-button>
		      </span>
		    </el-dialog>
			
		</div>
	</el-form>
</template>
<script>
	import { fetchList, fetchPv, createArticle, updateArticle } from '@/api/article'
	import waves from '@/directive/waves' // 水波纹指令
	import { parseTime } from '@/utils'
	//import Bts from './tab2'
	const calendarTypeOptions = [
	  { key: 'CN', display_name: 'China' },
	  { key: 'US', display_name: 'USA' },
	  { key: 'JP', display_name: 'Japan' },
	  { key: 'EU', display_name: 'Eurozone' }
	]

	// arr to obj ,such as { CN : "China", US : "USA" }
	const calendarTypeKeyValue = calendarTypeOptions.reduce((acc, cur) => {
	  acc[cur.key] = cur.display_name
	  return acc
	}, {})

	export default {
		data(){
			return{
				of:0,
				labelPosition: 'right',
		        formLabelAlign: {
		          name: 'abc',
		          region: '',
		          type: '',
		          pre:''
		        },
		        //table字段
		        tableKey: 0,
				list: null,
				total: null,
				listLoading: true,
				listQuery: {
					page: 1,
					limit: 10,
					importance: undefined,
					title: undefined,
					type: undefined,
					sort: '+id'
				},
				importanceOptions: [1, 2, 3],
				calendarTypeOptions,
				sortOptions: [{ label: 'ID Ascending', key: '+id' }, { label: 'ID Descending', key: '-id' }],
				statusOptions: ['published', 'draft', 'deleted'],
				showReviewer: false,
				temp: {
					id: undefined,
					importance: 1,
					remark: '',
					timestamp: new Date(),
					title: '',
					type: '',
					status: 'published'
				},
				dialogFormVisible: false,
				dialogStatus: '',
				textMap: {
					update: 'Edit',
					create: 'Create'
				},
				dialogPvVisible: false,
				pvData: [],
				rules: {
					type: [{ required: true, message: 'type is required', trigger: 'change' }],
					timestamp: [{ type: 'date', required: true, message: 'timestamp is required', trigger: 'change' }],
					title: [{ required: true, message: 'title is required', trigger: 'blur' }]
				},
				downloadLoading: false,
				//双日历字段
				pickerOptions2: {
		          shortcuts: [{
		            text: '最近一周',
		            onClick(picker) {
		              const end = new Date();
		              const start = new Date();
		              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
		              picker.$emit('pick', [start, end]);
		            }
		          }, {
		            text: '最近一个月',
		            onClick(picker) {
		              const end = new Date();
		              const start = new Date();
		              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
		              picker.$emit('pick', [start, end]);
		            }
		          }, {
		            text: '最近三个月',
		            onClick(picker) {
		              const end = new Date();
		              const start = new Date();
		              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
		              picker.$emit('pick', [start, end]);
		            }
		          }]
		        },
		        value7: '',
		        //单日历
		        pickerOptions1: {
		          disabledDate(time) {
		            return time.getTime() > Date.now();
		          },
		          shortcuts: [{
		            text: '今天',
		            onClick(picker) {
		              picker.$emit('pick', new Date());
		            }
		          }, {
		            text: '昨天',
		            onClick(picker) {
		              const date = new Date();
		              date.setTime(date.getTime() - 3600 * 1000 * 24);
		              picker.$emit('pick', date);
		            }
		          }, {
		            text: '一周前',
		            onClick(picker) {
		              const date = new Date();
		              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
		              picker.$emit('pick', date);
		            }
		          }]
		        },
		        value2: '',
		        //下拉多选假数据
		        filterText: '',
		        data2: [
		        	{id: 1,label: '一级 1',
		        		children: [{id: 4,label: '二级 1-1',
		            		children: [{id: 9,label: '三级 1-1-1'}, 
		            					{id: 10,label: '三级 1-1-2'}
		            				]
		          				}]
		        	}, 
		        	{id: 2, label: '一级 2',
		          		children: [{id: 5,label: '二级 2-1'}, 
		          					{id: 6,label: '二级 2-2'}
		          		]
		        	}, 
		        	{id: 3,label: '一级 3',
		          		children: [{id: 7,label: '二级 3-1'}, 
		          					{id: 8,label: '二级 3-2'}
		          		]
		        	}
		        ],
		        defaultProps: {
		          children: 'children',
		          label: 'label'
		        },
		        value:[],
			}
		},
		directives: {
		    waves
	  	},
		name: 'tab1',
		components:{},
		methods:{
			onSubmit(){

			},
			getList() {
		      this.listLoading = true
		      fetchList(this.listQuery).then(response => {
		        this.list = response.data.items
		        this.total = response.data.total
		        this.listLoading = false
		      })
		    },
		    handleFilter() {
		      this.listQuery.page = 1
		      this.getList()
		    },
		    handleSizeChange(val) {
		      this.listQuery.limit = val
		      this.getList()
		    },
		    handleCurrentChange(val) {
		      this.listQuery.page = val
		      this.getList()
		    },
		    handleModifyStatus(row, status) {
		      this.$message({
		        message: '操作成功',
		        type: 'success'
		      })
		      row.status = status
		    },
		    resetTemp() {
		      this.temp = {
		        id: undefined,
		        importance: 1,
		        remark: '',
		        timestamp: new Date(),
		        title: '',
		        status: 'published',
		        type: ''
		      }
		    },
		    handleCreate() {
		      this.resetTemp()
		      this.dialogStatus = 'create'
		      this.dialogFormVisible = true
		      this.$nextTick(() => {
		        this.$refs['dataForm'].clearValidate()
		      })
		    },
		    createData() {
		      this.$refs['dataForm'].validate((valid) => {
		        if (valid) {
		          this.temp.id = parseInt(Math.random() * 100) + 1024 // mock a id
		          this.temp.author = 'vue-element-admin'
		          createArticle(this.temp).then(() => {
		            this.list.unshift(this.temp)
		            this.dialogFormVisible = false
		            this.$notify({
		              title: '成功',
		              message: '创建成功',
		              type: 'success',
		              duration: 2000
		            })
		          })
		        }
		      })
		    },
		    handleUpdate(row) {
		      this.temp = Object.assign({}, row) // copy obj
		      this.temp.timestamp = new Date(this.temp.timestamp)
		      this.dialogStatus = 'update'
		      this.dialogFormVisible = true
		      this.$nextTick(() => {
		        this.$refs['dataForm'].clearValidate()
		      })
		    },
		    updateData() {
		      this.$refs['dataForm'].validate((valid) => {
		        if (valid) {
		          const tempData = Object.assign({}, this.temp)
		          tempData.timestamp = +new Date(tempData.timestamp) // change Thu Nov 30 2017 16:41:05 GMT+0800 (CST) to 1512031311464
		          updateArticle(tempData).then(() => {
		            for (const v of this.list) {
		              if (v.id === this.temp.id) {
		                const index = this.list.indexOf(v)
		                this.list.splice(index, 1, this.temp)
		                break
		              }
		            }
		            this.dialogFormVisible = false
		            this.$notify({
		              title: '成功',
		              message: '更新成功',
		              type: 'success',
		              duration: 2000
		            })
		          })
		        }
		      })
		    },
		    handleDelete(row) {
		      this.$notify({
		        title: '成功',
		        message: '删除成功',
		        type: 'success',
		        duration: 2000
		      })
		      const index = this.list.indexOf(row)
		      this.list.splice(index, 1)
		    },
		    handleFetchPv(pv) {
		      fetchPv(pv).then(response => {
		        this.pvData = response.data.pvData
		        this.dialogPvVisible = true
		      })
		    },
		    handleDownload() {
		      this.downloadLoading = true
		      import('@/vendor/Export2Excel').then(excel => {
		        const tHeader = ['timestamp', 'title', 'type', 'importance', 'status']
		        const filterVal = ['timestamp', 'title', 'type', 'importance', 'status']
		        const data = this.formatJson(filterVal, this.list)
		        excel.export_json_to_excel(tHeader, data, 'table-list')
		        this.downloadLoading = false
		      })
		    },
		    formatJson(filterVal, jsonData) {
		      return jsonData.map(v => filterVal.map(j => {
		        if (j === 'timestamp') {
		          return parseTime(v[j])
		        } else {
		          return v[j]
		        }
		      }))
		    },
		    //下拉多选搜索方法
			filterNode(value, data) {
				if (!value) return true;
				return data.label.indexOf(value) !== -1;
			},
			selectOptionClick(){
				// console.log('取消select点击')
			},
			//tree方法
			handleCheckChange(data, checked, indeterminate) {
				//console.log(data, checked, indeterminate);
				//console.log(data)
				let self = this;
				let arr = self.$refs.tree2.getCheckedNodes(true);
				let str = '';
				arr.map((obj,idx)=>{
					str+=obj.label+','
				})
				// console.log(arr)
				self.value = str
			},
			clear(){
				this.$refs.tree2.setCheckedNodes([]);
				self.value = '';
			},
			enter(){
				let self = this;
			},
		},
		activated(){//缓存组件更新数据钩子
			this.of++;
			// console.log(1)
		},
		updated(){
			// console.log`更新了`
		},
	    mounted(){
	      // console.log`编译了`
	    },
	    filters: {
			statusFilter(status) {
			  const statusMap = {
			    published: 'success',
			    draft: 'info',
			    deleted: 'danger'
			  }
			  return statusMap[status]
			},
			typeFilter(type) {
			  return calendarTypeKeyValue[type]
			}
		},
		created() {
			this.getList();
			//console.log(this)
		},
		watch: {
      		filterText(val) {
      			//console.log(val)
		        this.$refs.tree2.filter(val);
	      	}
	    },
	}
</script>
