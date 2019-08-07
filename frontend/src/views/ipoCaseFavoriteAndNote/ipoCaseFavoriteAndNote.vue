<template>
<div>
    <el-tabs v-model="editableTabsValue" @tab-click="tos">
        <el-tab-pane :key="item.name" v-for="item in editableTabs" :label="item.title" :name="item.name" :to="item.to">
        </el-tab-pane>
        <keep-alive>
            <router-view></router-view>
        </keep-alive>
    </el-tabs>
</div>
</template>

<script>
import {
    mapGetters
} from 'vuex'
export default {
    name: "ipoCaseFavoriteAndNote",
    components: {},
    data() {
        return {
            editableTabsValue: this.get_routename,
            editableTabs: [{
                    title: '收藏',
                    name: 'ipoCaseFavorite',
                    content: '静态可忽略',
                    to: '/ipoCaseFavoriteAndNote/ipoCaseFavorite'
                },
                {
                    title: '笔记',
                    name: 'ipoCaseNote',
                    content: '静态可忽略',
                    to: '/ipoCaseFavoriteAndNote/ipoCaseNote'
                }
            ],
        }
    },
    mounted() {
        //试用用户部分功能不可用
        this.ifSignStatus();
    },
    methods: {
        getValue(val) {
            if (val == null || val == "null" || val == undefined || val == "undefined") {
                val = "";
            }
            return val
        },
        destroyOther(ob) { //点击tab销毁另一个页面
            if (this.getValue(ob.name) != '') { //不是第一次进入时
                if (ob.name == 'ipoCaseFavorite') { //点击收藏，销毁笔记
                    this.$store.commit('ipoCase/SET_IPO_DESTROY_NOTE', true);
                } else { //点击笔记，销毁收藏
                    this.$store.commit('ipoCase/SET_IPO_DESTROY_FAVORITE', true);
                }
            }
        },
        ifSignStatus() {
            var param = {};
            this.tos({
                'index': 0
            });
        },
        tos(ob) {
            let url = window.location.href;
            let urlArr = url.split(this.$route.path);
            url = this.editableTabs[ob.index].to + urlArr[1];
            this.destroyOther(ob);
            this.$router.push(url);
        },
    },
    computed: {
        ...mapGetters([
            'get_routename'
        ]),
    },
    watch: {
        get_routename(n, o) {
            this.editableTabsValue = n
        },
    },
    created() {
        this.editableTabsValue = this.get_routename;
        let param = {
            client_type: 'pc', //手机或pc
            recordType: 'menu', //跳转页面方式:
            recordModule: 'IPO', //跳转模块
            recordTab: '收藏与笔记', //跳转tab
            recordTabChild: null, //跳转子集tab
            popTitle: null //弹窗title
        }
        this.$store.commit('CREATE_MESSAGE', param)
    },
}
</script>

<style>
.el-tabs__item{
    width:100%;
    text-align: center;
    padding-left: 0!important;
    padding-right: 0!important;
    font-size: 16px;
    color: #777!important;
    height:38px;
    margin-top:-2px;
}
.el-tabs__nav {
    width: 50%;
}
.el-tabs__header {
    width: 26%;
    padding: 0;
    position: relative;
    margin: 0;
    padding-left: 25px;
    margin-top: 18px;
}
.el-tabs__active-bar {
    width: 100%!important;
}
.el-tabs__item.is-active {
    color: #555!important;
}
.el-tabs__item.is-active:hover {
    cursor: default;
    color: #555!important;
}
.el-tabs__item:hover {
    color: #555;
    cursor: pointer;
}

</style>
