<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <Top></Top>
      </el-header>
      <el-container>
        <el-aside>
          <div class="menu">
            <el-menu
                background-color="#f6f8fa"
                :default-active="$router.path"
                class="el-menu-vertical-demo"
                router="true"
            >
              <el-menu-item index="/home">
                <el-icon><House /></el-icon>
                <span>首页</span>
              </el-menu-item>
              <el-menu-item index="/plan">
                <el-icon><Setting /></el-icon>
                <span>方案</span>
              </el-menu-item>
              <el-menu-item index="/store">
                <el-icon><OfficeBuilding/></el-icon>
                <span>门店</span>
              </el-menu-item>
              <el-menu-item index="/delivery">
                <el-icon><List /></el-icon>
                <span>运单</span>
              </el-menu-item>
            </el-menu>
          </div>
        </el-aside>
        <el-container>
          <el-main>
            <router-view></router-view>
          </el-main>
          <el-footer>
            <Bottom></Bottom>
          </el-footer>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>

<script setup lang="ts">

import Top from './component/top.vue'
import Bottom from './component/bottom.vue'
import {useRoute} from "vue-router";

let $router = useRoute();
</script>

<style scoped lang="scss">
.common-layout .el-header {
  padding: 0;
  height: $top-menu-height;
}

.common-layout .el-aside {
  width: 180px;
  --text-color: #303133;
  height: calc(100vh - $top-menu-height);
  //代替el-menu的边框
  border-right: solid 1px var(--el-menu-border-color);
  background-color: #f6f8fa
}
::v-deep(.el-menu){
  border-right: 0px;
}
</style>