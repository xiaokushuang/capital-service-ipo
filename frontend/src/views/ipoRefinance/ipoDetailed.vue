<template>
    <div class="container">
        <!-- 标题 -->
        <el-row :gutter="20" class="no-margin-tb">
            <el-col :span="14">
                <h3 class="no-margin" style="line-height:32px;padding-left:0px !important;">IPO在审项目数据查询</h3>
            </el-col>
            <el-col :span="6">
            </el-col>
        </el-row>
        <el-row :gutter="20">
            <el-col :span="6">
                <el-select ref="selectCheckbox1" v-model="selectValue.classly" placeholder="请选择板块" size='small full'>
                    <el-option :label="selectValue.classly" :value="selectValue.classly">
                        <el-tree :data="getIpoQuery.belongsPlateList" show-checkbox node-key="value" ref="tree1" highlight-current  @check-change="handleNodeClick1" :props="defaultPropsClassly"></el-tree>
                    </el-option>
                    <el-col :span="24" class='selectFull'>
                        <el-button  size="mini" @click="clear">清空</el-button>
                        <el-button  size="mini">全选</el-button>
                        <el-button type="primary" size="mini" @click="sure">确定</el-button>
                    </el-col>
                </el-select>
            </el-col>
            <el-col :span="6">
                <el-select ref="selectCheckbox2" v-model="selectValue.areaList" placeholder="请选择注册地" size='small full'>
                    <el-option :label="selectValue.areaList" :value="selectValue.areaList">
                        <el-tree :data="getIpoQuery.areaList" show-checkbox node-key="value" ref="tree2"  highlight-current  @check-change="handleNodeClick2" :props="defaultPropsArea"></el-tree>
                    </el-option>
                    <el-col :span="24" class='selectFull'>
                        <el-button  size="mini" @click="clear">清空</el-button>
                        <el-button  size="mini">全选</el-button>
                        <el-button type="primary" size="mini" @click="sure">确定</el-button>
                    </el-col>
                </el-select>
            </el-col>
            <el-col :span="2" style="float:right">
                <el-button size="mini" @click="clearAll">清空条件</el-button>
            </el-col>
        </el-row>
        <el-row>
            <el-table id="ipoDetail" :data="getIpoAreaData" style="width:100%" border sortable="custom" size="medium" :row-class-name="tableRowClassName">
                <el-table-column label="序号" align="center"  type="index" width="65px" style="border-right:1px solid #fff"></el-table-column>
                <el-table-column label="注册地" align="center" width="105px">
                    <template slot-scope="scope">
                        <span v-if="scope.row.registAddr == ''">合计</span>
                        <span v-else>{{scope.row.registAddr}}</span>
                    </template>
                </el-table-column>
                <el-table-column label="在审情况" align="center"  prop="registAddr" width="945px">
                    <el-table-column align="center" style="height:54px;" prop="applied" border="true" label="已受理" sortable width="130px"></el-table-column>
                    <el-table-column align="center" prop="reviewed" label="已反馈" sortable width="130px"></el-table-column>
                    <el-table-column align="center" prop="preUpdate" label="预先披露更新" sortable width="130px"></el-table-column>
                    <el-table-column align="center" prop="endYet" label="中止查询" sortable width="130px"></el-table-column>
                    <el-table-column align="center" prop="processing" label="已提交发审会讨论，暂缓表决" sortable width="168px"></el-table-column>
                    <el-table-column align="center" prop="passed" label="已通过发审会" sortable width="130px"></el-table-column>
                    <el-table-column align="center" prop="areaCount" label="合计" sortable width="130px" ></el-table-column>
                </el-table-column>
                <el-table-column label="终止审查" align="center" prop="registAddr" width="180px">
                    <el-table-column align="center" prop="weekStopYet" label="最近一周" sortable width="90px"></el-table-column>
                    <el-table-column align="center" prop="stopYet" label="2018年" sortable width="90px"></el-table-column>
                </el-table-column>
            </el-table>
        </el-row>
    </div>
</template>

<script>
import datepicker from "@/mixins/datepicker";
import { mapGetters } from "vuex";
import { GetDateDiff } from "@/utils";
import ipoDetailed from "./ipoDetailed";
export default {
  name: "ipoDetailed",
  mixins: [datepicker],
  data() {
    return {
      selectValue: {
        areaList: "",
        classly: ""
      },
      defaultPropsClassly: {
        label: "label",
        value: "value"
      },
      defaultPropsArea: {
         label: "label", 
         value: "value"
      },
      param: {
        ipoOrder: "",
        belongsPlate: "",
        areaList: "",
        classly: ""
      }
    };
  },
  mounted() {
    //页面加载完成时刷新echart图表
    this.ipoDataPort(true);
  },
  computed: {
    ...mapGetters(["getIpoAreaData", "getIpoQuery"])
  },
  methods: {
    sure() {
      this.$refs.selectCheckbox1.handleClose(); //关闭下拉框
      this.$refs.selectCheckbox2.handleClose(); //关闭下拉框
      this.param = {
        ipoOrder: "",
        belongsPlate: this.selectValue.classly,
        registAddr: this.selectValue.areaList
      }
      this.$store.dispatch("ipoAreaDataGet", this.param);
    },
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex === this.getIpoAreaData.length - 1) {
        return "warning-row"
      }
    },
    ipoDataPort() {
      this.param = {
        ipoOrder: "",
        belongsPlate: "",
        areaList: "",
      }
      this.$store.dispatch("ipoAreaDataGet", this.param)
      this.$store.dispatch("ipoQueryGet")
    },
    // 下拉菜单市场板块
    handleNodeClick1(data, node, component) {
      //共三个参数，依次为：传递给 data 属性的数组中该节点所对应的对象、节点本身是否被选中、节点的子树中是否有被选中的节点
      const nodeCheck = this.$refs.tree1.getCheckedNodes(true); //通过 node 获取(光子节点)
      let middle = "";
      nodeCheck.map((obj, idx) => {
        //拼接字符串
        middle += `,${obj.value}`;
      });
      this.selectValue.classly = middle.substr(1); //设置input里显示的文字，可扩展
    },
    handleNodeClick2(data, node, component) {
      //共三个参数，依次为：传递给 data 属性的数组中该节点所对应的对象、节点本身是否被选中、节点的子树中是否有被选中的节点
      const nodeCheck = this.$refs.tree2.getCheckedNodes(true); //通过 node 获取(光子节点)
      let middle = "";
      nodeCheck.map((obj, idx) => {
        //拼接字符串
        middle += `,${obj.label}`;
      });
      this.selectValue.areaList = middle.substr(1); //设置input里显示的文字，可扩展
    },
    clear() {
      //下拉清空
      this.$refs.tree1.setCheckedKeys([]); //清空选中
      this.$refs.tree2.setCheckedKeys([]); //清空选中
    },
    clearAll() {
      this.$refs.tree1.setCheckedKeys([]); //清空选中
      this.$refs.tree2.setCheckedKeys([]); //清空选中
      this.param = {
        ipoOrder: "",
        belongsPlate: "",
        areaList: "",
      }
      this.$store.dispatch("ipoAreaDataGet", this.param).then(() =>{
        this.loading = false
      }).catch(err => {
        this.loading = false
      })
    }
  }
};
</script>

<style>
.no-margin {
  padding-left: 20px;
}
.h100 {
  height: 100%;
  min-height: 500px;
}
.h100 > div.chart {
  height: 100%;
  min-height: 500px;
}
.fullDiv_border {
  width: 100%;
  height: 100%;
  height: 500px;
}
.list {
  line-height: 38px;
}
.list a {
  padding: 5px 20px;
  font-size: 14px;
}
.list a.active {
  color: #0285ac;
}
.el-table thead.is-group th{
  background: #a0adb5 !important;
  padding: 1px;
  border-right: 1px solid #fff;
}
.el-table thead {
  height: 87px;
}
.el-table--border td {
  height: 30px;
  line-height: 30px;
  border-right: 1px solid #ddd;
}
.el-table .cell,
.el-table th div,
.el-table--border td:first-child .cell,
.el-table--border th:first-child .cell {
  padding: 0px;
}
.el-table .warning-row {
  background: #e8e8e8 !important;
}
.table thead th{padding:6px !important;}
</style>
