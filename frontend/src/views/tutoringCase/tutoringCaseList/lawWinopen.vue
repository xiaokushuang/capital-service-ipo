<template>
  <div style="font-size: 13px;color: #333333">
    <div style="display: inline-block;width: 164px;height: 599px;background-color: rgba(245, 247, 250, 1);padding: 20px">
      <div :class="issueDialogSel == '1' ? 'issueSel':''" style="cursor: pointer;padding-bottom: 20px" @click="issueDialogClick('1')">科创板上市条件</div>
      <div :class="issueDialogSel == '2' ? 'issueSel':''" style="cursor: pointer;padding-bottom: 20px" @click="issueDialogClick('2')">精选层上市条件</div>
    </div>
    <div style="display: inline-block;vertical-align: top;width: 650px;padding-top: 20px;padding-left: 20px" class="lawContent">
      <div v-if="issueDialogSel == '1'">
        <div>
          <a style="color: #1990FE;margin-left: -5px" @click="openNewRule()">《上海证券交易所科创板股票上市规则》：</a>
        </div>
        <div style="margin-top: 5px">2.1.2</div>
        <div>发行人申请在本所科创板上市，市值及财务指标应当至少符合下列标准中的一项：</div>
        <div>
          （一）预计市值不低于人民币10亿元，最近两年净利润均为正且累计净利润不低于人民币5000万元，或者预计市值不低于人民币10亿元，最近一年净利润为正且营业收入不低于人民币1亿元；
        </div>
        <div>（二）预计市值不低于人民币15亿元，最近一年营业收入不低于人民币2亿元，且最近三年累计研发投入占最近三年累计营业收入的比例不低于15%；</div>
        <div>（三）预计市值不低于人民币20亿元，最近一年营业收入不低于人民币3亿元，且最近三年经营活动产生的现金流量净额累计不低于人民币1亿元；</div>
        <div>（四）预计市值不低于人民币30亿元，且最近一年营业收入不低于人民币3亿元；</div>
        <div>
          （五）预计市值不低于人民币40亿元，主要业务或产品需经国家有关部门批准，市场空间大，目前已取得阶段性成果。医药行业企业需至少有一项核心产品获准开展二期临床试验，其他符合科创板定位的企业需具备明显的技术优势并满足相应条件。
        </div>
        <div>
          本条所称净利润以扣除非经常性损益前后的孰低者为准，所称净利润、营业收入、经营活动产生的现金流量净额均指经审计的数值。
        </div>
        <div style="margin-top: 5px">2.1.3</div>
        <div>
          符合《国务院办公厅转发证监会关于开展创新企业境内发行股票或存托凭证试点若干意见的通知》（国办发﹝2018﹞21号）相关规定的红筹企业，可以申请发行股票或存托凭证并在科创板上市。
        </div>
        <div>
          营业收入快速增长，拥有自主研发、国际领先技术，同行业竞争中处于相对优势地位的尚未在境外上市红筹企业，申请在科创板上市的，市值及财务指标应当至少符合下列标准之一：
        </div>
        <div>（一）预计市值不低于人民币100亿元；</div>
        <div>（二）预计市值不低于人民币50亿元，且最近一年营业收入不低于人民币5亿元。</div>
        <div style="margin-top: 5px">2.1.4</div>
        <div>
          发行人具有表决权差异安排的，市值及财务指标应当至少符合下列标准中的一项：
        </div>
        <div>
          （一）预计市值不低于人民币100亿元；
        </div>
        <div>
          （二）预计市值不低于人民币50亿元，且最近一年营业收入不低于人民币5亿元。
        </div>
        <div>
          发行人特别表决权股份的持有人资格、公司章程关于表决权差异安排的具体规定，应当符合本规则第四章第五节的规定。
        </div>
        <div>
          本规则所称表决权差异安排，是指发行人依照《公司法》第一百三十一条的规定，在一般规定的普通股份之外，发行拥有特别表决权的股份（以下简称特别表决权股份）。每一特别表决权股份拥有的表决权数量大于每一普通股份拥有的表决权数量，其他股东权利与普通股份相同。
        </div>
      </div>
      <div v-else>
        <div>
          <a style="color: #1990FE;margin-left: -5px" @click="openNewRuleJxc()">《全国中小企业股份转让系统分层管理办法》：</a>
        </div>
        <div style="margin-top: 5px">第十五条</div>
        <div>在全国股转系统连续挂牌满12个月的创新层挂牌公司，可以申请公开发行并进入精选层。</div>
        <div>挂牌公司申请公开发行并进入精选层时，应当符合下列条件之一：</div>
        <div>
          （一）市值不低于2亿元，最近两年净利润均不低于1500万元且加权平均净资产收益率平均不低于8%，或者最近一年净利润不低于2500万元且加权平均净资产收益率不低于8%；
        </div>
        <div>（二）市值不低于4亿元，最近两年营业收入平均不低于1亿元，且最近一年营业收入增长率不低于30%，最近一年经营活动产生的现金流量净额为正；</div>
        <div>（三）市值不低于8亿元，最近一年营业收入不低于2亿元，最近两年研发投入合计占最近两年营业收入合计比例不低于8%；</div>
        <div>（四）市值不低于15亿元，最近两年研发投入合计不低于5000万元。</div>
        <div>前款所称市值是指以挂牌公司向不特定合格投资者公开发行（以下简称公开发行）价格计算的股票市值。</div>
        <div style="margin-top: 5px">第十六条</div>
        <div>挂牌公司完成公开发行并进入精选层时，除应当符合本办法第十五条规定条件外，还应当符合下列条件：</div>
        <div>（一）最近一年期末净资产不低于5000万元；</div>
        <div>（二）公开发行的股份不少于100万股，发行对象不少于100人；</div>
        <div>（三）公开发行后，公司股本总额不少于3000万元；</div>
        <div>（四）公开发行后，公司股东人数不少于200人，公众股东持股比例不低于公司股本总额的25%；公司股本总额超过4亿元的，公众股东持股比例不低于公司股本总额的10%；</div>
        <div>（五）中国证监会和全国股转公司规定的其他条件。</div>
        <div>公众股东是指除以下股东之外的挂牌公司股东：</div>
        <div>（一）持有公司10%以上股份的股东及其一致行动人；</div>
        <div>（二）公司董事、监事、高级管理人员及其关系密切的家庭成员，公司董事、监事、高级管理人员直接或间接控制的法人或者其他组织。关系密切的家庭成员，包括配偶、子女及其配偶、父母及配偶的父母、兄弟姐妹及其配偶、配偶的兄弟姐妹、子女配偶的父母。</div>
      </div>
    </div>
  </div>
</template>

<script>
  import { iframeDoMessage } from "@/utils/auth";
  export default {
    data() {
      return {
        issueDialogSel:'1',
      }
    },
    created() {
    },
    methods: {
      issueDialogClick(val){
        this.issueDialogSel = val
      },
      openNewRule() {
        const _self = this;
        debugger;
        alert(_self.$store.state.app.info)
        const href = window.location.origin + '/ui/laws/laws/lawsDetail?lawId=' + _self.$route.query.issueLawId + '&access_token=' +
          self.$store.state.app.token + '&tenant_info=' + _self.$store.state.app.info;
        window.open(href, '_blank');
      },
      openNewRuleJxc() {
        const _self = this;
        const href = window.location.origin + '/ui/laws/laws/lawsDetail?lawId=' + _self.$route.query.issueLawJxcId + '&access_token=' +
          self.$store.state.app.token + '&tenant_info=' + _self.$store.state.app.info;
        window.open(href, '_blank');
      }
    },
    components: {}
  }
</script>
<style>
  .issueSel{
    color:rgb(25, 144, 254)
  }
  .lawContent div{
    line-height: 18px;
  }
</style>
