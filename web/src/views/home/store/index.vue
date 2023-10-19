<template>
  <div v-show="scene=='list'">
    <el-card class="box-top">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="门店名称">
          <el-input placeholder="请输入门店名称" v-model="queryParam.storeName" clearable/>
        </el-form-item>
        <el-form-item label="门店编码">
          <el-input placeholder="请输入门店编码" v-model="queryParam.storeCode" clearable/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" :icon="Search" @click="searchStore">查询</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card class="box-body">
      <el-button class="add_store" type="primary" :icon="Plus" @click="addStore">添加门店</el-button>
      <el-table border style="width: 100%" :data="storeList" max-height="500">
        <el-table-column prop="id" label="ID" width="100"/>
        <el-table-column prop="storeName" label="门店名称"/>
        <el-table-column prop="storeCode" label="门店编码"/>
        <el-table-column prop="storeContract" label="门店联系人"/>
        <el-table-column prop="storeContractPhone" label="门店联系电话"/>
        <el-table-column label="省-市-区">
          <template #="{row, $index}">
            {{ row.provinceName }} - {{ row.cityName }} - {{ row.districtName }}
          </template>
        </el-table-column>
        <el-table-column prop="address" label="详细地址"/>
        <el-table-column prop="longitude" label="经纬度">
          <template #="{row, $index}">
            {{ row.longitude }} , {{ row.latitude }}
          </template>
        </el-table-column>
        <el-table-column prop="planName" label="绑定方案">
          <template #="{row, $index}">
            {{ row.planName ? row.planName : '暂未绑定 --' }}
          </template>
        </el-table-column>
        <el-table-column label="编辑">
          <template #="{row,$index}">
            <el-button size="small" @click="editStore(row)">Edit</el-button>
            <!--            <el-button size="small" type="danger">Delete</el-button>-->
          </template>
        </el-table-column>
      </el-table>

      <div class="page">
        <el-pagination
            layout="prev, pager, next, jumper , -> ,total, sizes"
            v-model:page-size="pageSize"
            v-model:current-page="pageNum"
            :page-sizes="[10,20,50,100]"
            @size-change="handleSizeChange"
            @current-change="handlePageChange"
            :total="total"/>
      </div>
    </el-card>
  </div>
  <StoreDetail
      ref="store"
      v-show="scene=='detail'"
      @saveOrCancel="saveOrCancel"
  ></StoreDetail>

</template>

<script setup lang="ts">
import {Plus, Search} from "@element-plus/icons-vue";
import StoreDetail from './StoreDetail.vue'
import {onMounted, reactive, ref} from "vue";
import {store_page} from "@/api/store";
import region_store from "@/store/modules/region.ts";

let scene = ref<string>("list");

let store = ref<any>();
let pageNum = ref<number>(1);
let pageSize = ref<number>(10);
let total = ref<number>(0);
let queryParam = reactive<StorePageReq>({
  storeName: '',
  storeCode: '',
  pageQuery: {
    pageSize: 0,
    pageNum: 0,
  }
});
let storeList = ref<StoreInfo[]>();


onMounted(() => {
  searchStore();
})

const searchStore = async () => {
  await getStoreList();
};


const getStoreList = async () => {
  queryParam.pageQuery.pageSize = pageSize.value;
  queryParam.pageQuery.pageNum = pageNum.value;
  let response = await store_page(queryParam);
  total.value = response.total;
  storeList.value = response.list;
};


const addStore = () => {
  scene.value = 'detail';
  store.value.init();
}
const editStore = (row: StoreInfo) => {
  scene.value = 'detail';
  store.value.init(row);
};

const saveOrCancel = (obj: string) => {
  scene.value = 'list';
  if (obj == 'save') {
    pageNum.value = 1;
    getStoreList();
  }
};

const handleSizeChange = (val: number) => {
  pageSize.value = val;
  getStoreList();
}
const handlePageChange = (val: number) => {
  pageNum.value = val;
  getStoreList();
}

</script>

<style scoped lang="scss">
.box-top, .add_store {
  margin-bottom: 20px;
}

.page {
  margin-top: 10px;
}
</style>
