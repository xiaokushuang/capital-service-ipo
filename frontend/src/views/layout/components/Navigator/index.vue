<style rel="stylesheet/scss" lang="scss" scoped>
  @import 'navigator.scss';
</style>
<template>
  <el-menu class="navigator" mode="horizontal">
    <!--Navigator sidebar hamburger container -->
    <el-col :span="4">
      <hamburger class="hamburger-container" :toggleClick="toggleSideBar" :isActive="sidebar.opened"></hamburger>
      <div class="application-title">
        <div v-text="$t('navbar.application')"></div>
      </div>

      <!--Navigator link breadcrumb container -->
      <!--<breadcrumb class="breadcrumb-container"></breadcrumb>-->
    </el-col>

    <!--Navigator menu container -->
    <el-col :span="12" class="menu-container">
      <a class="menu-bar"><i class="fa fa-handshake-o" aria-hidden="true"></i>关联方</a>
      <a class="menu-bar"><i class="fa fa-ils" aria-hidden="true"></i>关联交易</a>
      <a class="menu-bar"><i class="fa fa-gavel" aria-hidden="true"></i>担保</a>
      <a class="menu-bar"><i class="fa fa-pie-chart" aria-hidden="true"></i>股权</a>
      <a class="menu-bar"><i class="fa fa-file-text" aria-hidden="true"></i>独立声明书</a>
      <a class="menu-bar"><i class="fa fa-exchange" aria-hidden="true"></i>流程管理</a>
      <div class="menu-pop-container">
        <el-col :span="24">
          <el-col :span="4">
            <div class="group">
              <div class="title">关联方</div>
              <ul class="list">
                <fieldset>
                  <legend>申报</legend>
                  <li><a>关联自然人申报</a></li>
                  <li><a>关联法人申报</a></li>
                </fieldset>
                <fieldset>
                  <legend>清单</legend>
                  <li>
                    <router-link :to="'/relatePersons'">关联自然人清单</router-link>
                  </li>
                  <li>
                    <router-link :to="'/relateLegalPersons'">关联法人清单</router-link>
                  </li>
                </fieldset>
                <fieldset>
                  <legend>管理</legend>
                  <li>
                    <router-link :to="'/transactionType/transactionWay'">从属主体身份管理</router-link>
                  </li>
                  <li>
                    <router-link :to="'/relatedWayConfig'">关联方信息配置</router-link>
                  </li>
                </fieldset>
              </ul>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="group">
              <div class="title">关联交易</div>
              <ul class="list">
                <fieldset>
                  <legend>申报</legend>
                  <li><a>事前关联交易申报</a></li>
                  <li><a>事后关联申报</a></li>
                  <li><a>日常关联交易预计</a></li>
                </fieldset>
                <fieldset>
                  <legend>清单</legend>
                  <li>
                    <router-link :to="'/Trade/relatedTransaction'">关联交易清单</router-link>
                  </li>
                  <li>
                    <router-link :to="'/money'">日常关联交易预计清单</router-link>
                  </li>
                </fieldset>
                <fieldset>
                  <legend>管理</legend>
                  <li>
                    <router-link :to="'/transactionType/transactionTrade'">自定义关联交易类型管理</router-link>
                  </li>
                  <li>
                    <router-link :to="'/transactionType/transactionTrade'">关联交易类型管理</router-link>
                  </li>
                  <li>
                    <router-link :to="'/transactionType/transactionEvent'">标的事件管理</router-link>
                  </li>
                </fieldset>
              </ul>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="group">
              <div class="title">担保</div>
              <ul class="list">
                <fieldset>
                  <legend>申报</legend>
                  <li><a>关联担保申报</a></li>
                  <li><a>非关联担保申报</a></li>
                </fieldset>
                <fieldset>
                  <legend>清单</legend>
                  <li>
                    <router-link :to="'/guaranteesManage'">担保清单</router-link>
                  </li>
                </fieldset>
              </ul>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="group">
              <div class="title">股权</div>
              <ul class="list">
                <fieldset>
                  <legend>申报</legend>
                  <li><a>附属公司申报</a></li>
                  <li><a>附属公司董监高申报</a></li>
                </fieldset>
                <fieldset>
                  <legend>清单</legend>
                  <li>
                    <router-link :to="'/companyManagement/companyManagement'">附属公司清单</router-link>
                  </li>
                  <li>
                    <router-link :to="'/companyManagement/seniorPersonInfo'">附属公司董监高清单</router-link>
                  </li>
                  <li>
                    <router-link :to="'/companyManagement/orgChart'">股权架构图</router-link>
                  </li>
                </fieldset>
              </ul>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="group">
              <div class="title">独立声明书</div>
              <ul class="list">
                <fieldset>
                  <legend>通知</legend>
                  <li>
                    <router-link :to="'/statement/book/letterMgr'">独立声明书</router-link>
                  </li>
                </fieldset>
              </ul>
            </div>
          </el-col>
          <el-col :span="4">
            <div class="group">
              <div class="title">流程管理</div>
              <ul class="list">
                <fieldset>
                  <legend>流程</legend>
                  <li><a>关联方申报流程</a></li>
                  <li><a>关联交易申报流程</a></li>
                  <li><a>日常关联交易预计流程</a></li>
                  <li><a>担保申报流程</a></li>
                  <li><a>附属公司申报流程</a></li>
                  <li><a>附属公司董监高申报流程</a></li>
                </fieldset>
              </ul>
            </div>
          </el-col>
        </el-col>
      </div>
    </el-col>

    <!--Navigator menu container -->
    <el-col :span="8" class="sys-menu-container">
      <!--<error-log class="errLog-container"></error-log>-->

      <!-- 关联交易申报情况查询 -->
      <el-tooltip effect="light" :content="$t('navbar.report')" placement="bottom">
        <el-row>
          <el-button size="small" type="primary" icon="el-icon-search" @click="relatedTransactionViewHandler"></el-button>
        </el-row>
      </el-tooltip>

      <!-- 站内通知 -->
      <el-popover ref="msgPopover" placement="bottom" width="350" trigger="hover" v-if="msgDatasUncompleteSize()">
        <div>
          <div style="margin-top: 5px;" v-for="item in messageDatasUnComplete" :key="item.id">
                <span style="cursor: pointer;" @click="titleContent(item)">
                  <a href="javascript:void(0)">{{ item.rTitle }}</a></span>
          </div>
          <div style="margin-top:10px;text-align:right;">
            <span><a href="javascript:void(0)" @click="allComplete()">全部标记完成</a></span>
          </div>
        </div>
      </el-popover>
      <el-tooltip effect="light" :content="$t('navbar.notice')" placement="bottom">
        <el-button size="small" type="primary" icon="el-icon-message" v-popover:msgPopover @click="emailButton" v-if="msgDatasUncompleteSize()">
          {{messageInfoCount1}}
        </el-button>
        <el-button size="small" type="primary" icon="el-icon-message" @click="emailButton" v-else></el-button>
      </el-tooltip>

      <!-- 系统管理 -->
      <el-tooltip effect="light" :content="$t('navbar.system')" placement="right">
        <el-row class="system-menu-bar">
          <el-button size="small" type="primary" icon="el-icon-setting"></el-button>
          <div class="system-menu-pop-container">
            <el-col class="group">
              <div class="title">系统设置</div>
              <ul class="list">
                <li>
                  <router-link v-bind:to="'/system/systemSetting'">系统参数</router-link>
                </li>
                <li>
                  <router-link v-bind:to="'/messageSendInfo'">消息推送</router-link>
                </li>
              </ul>
            </el-col>
            <el-col class="group">
              <div class="title">组织机构</div>
              <ul class="list">
                <li>
                  <router-link :to="'/system/company'">公司信息</router-link>
                </li>
                <li>
                  <router-link :to="'/system/roleManage'">角色管理</router-link>
                </li>
                <li>
                  <router-link :to="'/system/userManage'">用户管理</router-link>
                </li>
              </ul>
            </el-col>
          </div>
        </el-row>
      </el-tooltip>

      <!-- Theme switch-->
      <el-tooltip effect="light" :content="$t('navbar.theme')" placement="bottom">
        <theme-picker class="theme-switch"></theme-picker>
      </el-tooltip>

      <!-- Screen-full switch-->
      <el-tooltip effect="light" :content="$t('navbar.screenFull')" placement="bottom">
        <screenfull class="screen-full"></screenfull>
      </el-tooltip>

      <!-- Language switch-->
      <el-tooltip effect="light" :content="$t('navbar.language')" placement="bottom">
        <lang-select class="international"></lang-select>
      </el-tooltip>

      <el-tooltip effect="light" :content="'用户'" placement="bottom">
        <el-dropdown class="avatar-container" trigger="click">
          <div class="avatar-wrapper">
            <!--<img class="user-avatar" :src="avatar+'?imageView2/1/w/80/h/80'">-->
            <img class="user-avatar" src="../../../../assets/avatar1.png">
            <span><i class="el-icon-caret-bottom"></i></span>
          </div>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <span @click="logout">{{$t('navbar.logOut')}}</span>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-tooltip>

    </el-col>

    <el-dialog :title="dialogTitle" :close-on-click-modal="false" style="text-align: left;" :visible.sync="navMessageInfoDialog">
      <div v-text="dialogContent"></div>
      <span slot="footer" class="dialog-footer">
        <el-button align="center" type="primary" @click="navMessageInfoDialog = false">确 定</el-button>
      </span>
    </el-dialog>

    <!-- liulin demo -->
    <el-dialog title="关联交易申报情况" :close-on-click-modal="false" :visible.sync="dialogFormVisible" width="70%" style="text-align:left;">
      <el-form :inline="true" :rules="temParamRules" class="demo-form-inline" ref="dataForm" :model="temp" label-position="left" label-width="70px" style='margin-left:50px margin-top:500px'>
        <el-row>
          <el-col style="width:110px;">
            <el-form-item prop="year">
              <!-- <div class="block"> -->
              <el-date-picker v-model="temp.year" align="right" type="year" size="small" placeholder="选择年" value-format="yyyy">
              </el-date-picker>
              <!-- </div> -->
            </el-form-item>
          </el-col>
          <el-col style="width:25px;">
            <label size="small full" style="line-height:37px">年</label>
            <!-- <p size="small full">年</p> -->
          </el-col>
          <el-col style="width:110px;">
            <el-form-item prop="mont">
              <!-- <div class="block"> -->
              <el-date-picker v-model="temp.mont" type="month" size="small" placeholder="选择月" format="MM" value-format="MM">
              </el-date-picker>
              <!-- </div> -->
            </el-form-item>
          </el-col>
          <el-col style="width:25px;">
            <label size="small full" style="line-height:37px">月</label>
          </el-col>
          <el-col :span="10" style="float: right;text-align: right;">
            <el-form-item class='text-right'>
              <el-button size="small" @click="onReset">清空</el-button>
              <el-button type="primary" size="small" @click="onSubmit">查询</el-button>
            </el-form-item>
          </el-col>
        </el-row>
        <div>
          <el-table :key='tableKey' :data="relatedTransactionrecordsDataList" v-loading="listLoading" element-loading-text="给我一点时间" border fit highlight-current-row
                    style="width: 100%">
            <el-table-column align="center" label="部门名称">
              <template slot-scope="scope">
                <span>{{scope.row.departmentName}}</span>
              </template>
            </el-table-column>
            <el-table-column align="center" label="状态">
              <template slot-scope="scope">
                <span>{{scope.row.deSate | deSateFilter}}</span>
              </template>
            </el-table-column>
            <el-table-column align="center" label="申报时间">
              <template slot-scope="scope">
                <span>{{scope.row.fiDate}}</span>
              </template>
            </el-table-column>
            <el-table-column align="center" label="申报人">
              <template slot-scope="scope">
                <span>{{scope.row.reporter}}</span>
              </template>
            </el-table-column>
            <el-table-column align="center" label="操作">
              <template slot-scope="scope">
							<span v-if=" scope.row.deSate == null">
		          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">发送提醒</el-button>
							</span>
              </template>
            </el-table-column>
          </el-table>
          <div class="pagination-container">
            <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="listQuery.page"
                           :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="relatedTransactionrecordsDataCount">
            </el-pagination>
          </div>
        </div>
      </el-form>
    </el-dialog>

    <!-- <el-dialog title="关联交易申报情况" :visible.sync="dialogFormVisible" width="70%">
      <associateTransaction :param1='xxx'></associateTransaction>
    </el-dialog> -->
  </el-menu>
</template>

<script>
  import {mapGetters} from 'vuex'
  import Breadcrumb from '@/components/Breadcrumb'
  import Hamburger from '@/components/Hamburger'
  import ErrorLog from '@/components/ErrorLog'
  import Screenfull from '@/components/Screenfull'
  import LangSelect from '@/components/LangSelect'
  import ThemePicker from '@/components/ThemePicker'
  // import associateTransaction from './associateTransaction/associateTransaction'
  export default {
    data() {
      return {
        dialogFormVisible: false,
        a: 1,
        b: '保存',
        page: 1,
        limit: 10,
        //table字段
        tableKey: 0,
        listLoading: true,
        temp: {
          year: null,
          mont: null
        },
        listQuery: {
          page: 1,
          limit: 10,

          sort: '+id'
        },
        temParamRules: {//修改创建页面校验规则
          year: [
            {required: true, message: '请选择您需要的年份！', trigger: 'blur'}
          ],
          mont: [
            {required: true, message: '请选择您需要的月份！', trigger: 'blur'}
          ],
        },
        // xxx:'',
        dialogFormVisible: false,
        navMessageInfoDialog: false,
        dialogContent: '',
        dialogTitle: '',
        //默认分页条件
        listQuery: {
          page: 1,
          limit: 10,
          importance: undefined,
          title: undefined,
          type: undefined,
          sort: "+id"
        },
        //消息中心页面参数
        formPara: {},
      }
    },
    components: {
      Breadcrumb,
      Hamburger,
      ErrorLog,
      Screenfull,
      LangSelect,
      ThemePicker,
      // associateTransaction
    },
    computed: {
      ...mapGetters([
        'sidebar',
        'name',
        'avatar',
        //未完成数量
        'messageInfoCount1',
        //未完成数据
        'messageDatasUnComplete',
        'relatedTransactionrecordsDatanowyear',
        'relatedTransactionrecordsDatanowmonth',
        'relatedTransactionrecordsDataList',
        'relatedTransactionrecordsDataCount'

      ])
    },

    methods: {
      handleCurrentChange(val) {
        this.listQuery.page = val
        this.getList1()
      },
      getList1() {
        listLoading: true;
        let param = {
          startRow: this.listQuery.page - 1,
          pageSize: this.listQuery.limit,
          condition: {
            year: this.temp.year,
            modeonth: this.temp.mont
          }
        }
        console.log(param)
        this.$store.dispatch('getRelatedTransactionRecordsl', param).then((data) => {
          this.listLoading = false;

        }).catch(err => {
        })
      },
      handleSizeChange(val) {
        this.listQuery.limit = val
        this.getList1()
      },
      onSubmit() {
        this.$refs['dataForm'].validate((valid) => {//修改/增加表单验证
          if (valid) {
            listLoading: true;
            let param = {
              startRow: 0,
              pageSize: this.listQuery.limit,
              condition: {
                year: this.temp.year,
                modeonth: this.temp.mont
              }

            }
            console.log(param)
            this.$store.dispatch('getRelatedTransactionRecordsl', param).then((data) => {
              this.listLoading = false;

            }).catch(err => {
            })
          }
        });
      },
      relatedTransactionViewHandler() {
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
          this.temp.year = '',
              this.temp.mont = '',
              this.listQuery.page = 1,
              this.listQuery.limit = 10
        })
        listLoading: true;
        let param = {
          startRow: 0,
          pageSize: this.listQuery.limit,
          condition: {
            year: '',
            modeonth: ''
          }

        }
        this.$store.dispatch('getRelatedTransactionRecordsl', param).then((data) => {
          this.listLoading = false;
          this.temp.year = this.relatedTransactionrecordsDatanowyear
          this.temp.mont = this.relatedTransactionrecordsDatanowmonth
          console.log(this.temp.year)
          console.log(this.temp.mont)
        }).catch(err => {
        })

      },
      onReset() {
        this.formReset()
        this.getList1()
      },
      formReset() {
        this.temp.year = '',
            this.temp.mont = ''
      },
      handleUpdate(dates) {
        let param = {
          condition: {
            dId: dates.dId,
            departmentName: dates.departmentName,
          }
        }
        this.$store.dispatch('sendMessageToDemartment', param).then((data) => {
          console.log(data)
          this.listLoading = false;
          if (data == true) {
            this.$message({
              showClose: true,
              message: '恭喜你，提醒发送成功！',
              type: 'success'
            });
          } else {
            this.$message({
              message: '对不起，提醒发送失败！',
              type: 'success'
            });
          }
        }).catch(err => {
        })
      },
      //定时刷新方法
      timingRefresh() {
        //导航栏获取消息
        let param = {}
        this.$store.dispatch("getMessageInfoCount", param).then(data => {
        }).catch(err => {
        });
        //消息中心获取
        this.getList();
      },
      //全部标记完成
      allComplete() {
        let param = {}
        this.$store.dispatch('completeMessageInfoAll', param).then((data) => {
          //获取列表
          this.getList();
        })
      },
      msgDatasUncompleteSize() {
        return this.messageDatasUnComplete.length
      },
      //消息中心初期化获取值
      getList() {
        let param = {
          startRow: this.listQuery.page - 1,
          pageSize: this.listQuery.limit,
          condition: this.formPara
        };
        this.$store.dispatch("getMessageInfoData", param).then(data => {
        })
            .catch(err => {
            });
      },
      toggleSideBar() {
        this.$store.dispatch('toggleSideBar')
      },
      logout() {
        this.$store.dispatch('LogOut').then(
            (data) => {
              location.reload()// In order to re-instantiate the vue-router object to avoid bugs
            })
            .catch((error) => {
              if (error && error.response && error.response.status == 401) {
                // session expire
                location.reload();
              } else {
                console.log(error);
              }
            });
      },
      //消息中心
      emailButton() {
        //跳转路由
        this.$router.push({path: '/messageInfo'});
      },
      //消息内容
      titleContent(data) {
        this.navMessageInfoDialog = true,
            this.dialogTitle = data.rTitle,
            this.dialogContent = data.rContent
        let param = {id: data.id, viewstatus: "0"}
        this.$store.dispatch('completeMessageInfo', param).then((data) => {
          this.getList();
        })
      },

    },
    filters: {
      deSateFilter: function (value) {
        if (value == 1) {
          return "已申报";
        }
        if (value == null) {
          return "未申报";
        }
        if (value == 2) {
          return "本期无关联交易";
        }
      },
    },
    //初期化加载方法
    created: function () {
      let param = {}
      this.$store.dispatch("getMessageInfoCount", param).then(data => {
      }).catch(err => {
      });
    },
    beforeMount() {
      //定时刷新 （方法名，毫秒数）每隔1分钟刷新一次
      setInterval(interval, 60000)
      var self = this;

      function interval() {
        self.timingRefresh();
      }
    }
  }
</script>