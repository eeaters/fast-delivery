<template>
  <div class="top">
    <div class="left">
        <img src="../../../assets/icon.svg" alt="">
        <span>私域流量成本精准把控</span>
    </div>

    <div class="right">
      <el-dropdown>
          <span class="el-dropdown-link">
            欢迎 , {{ useUserStore.userInfo.nickName }}
            <el-icon class="el-icon--right">
              <arrow-down/>
            </el-icon>
          </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>

  </div>
</template>

<script setup lang="ts">
import userStore from '@/store/modules/user.ts'
import {useRouter} from "vue-router";

let $router = useRouter();
let useUserStore = userStore();

const logout = () => {
  useUserStore.logout();
  $router.push({path: "/login"});
};

</script>

<style scoped lang="scss">
.top {
  display: flex;
  justify-content: space-between;
  background-color: #f6f8fa;
  .left {
    margin-left: 6px;
    display: flex;
    align-items: center;
    font-family: 'Cinzel Decorative', cursive;
    img {
      width: $top-menu-height;
      margin: 0 60px;
    }
  }

  .right {
    display: flex;
    align-items: center;
    margin-right: 50px;
  }
}
</style>