<template>
  <div v-show="scene=='list'">
    <!-- 搜索区域 -->
    <el-card class="box-top">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="方案名称">
          <el-input placeholder="请输入方案名称" v-model="planName" clearable/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" :icon="Search" @click="searchPlan">查询</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <!--  列表  -->
    <el-card class="box-body">
      <el-button class="add_Plan" type="primary" :icon="Plus" @click="addPlan" style="margin-bottom:20px">添加方案
      </el-button>
      <el-table border style="width: 100%" :data="planList" max-height="400">
        <el-table-column type="expand">
          <template #default="props">
            <div style="width: 500px;margin-left: 50px">
              <el-table :data="props.row.mappingList">
                <el-table-column label="渠道名称" prop="channelName"/>
                <el-table-column label="渠道简称" prop="channel"/>
                <el-table-column label="排序值" prop="sort"/>
              </el-table>
            </div>
          </template>
        </el-table-column>

        <el-table-column prop="id" label="ID"/>
        <el-table-column prop="planName" label="方案名称"/>
        <el-table-column prop="timePeriod" label="超时未接单间隔"/>
        <el-table-column prop="desc" label="详情描述"/>
        <el-table-column label="操作">
          <template #="{ row, $index }">
            <el-button @click="updatePlan(row)">修改</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="page">
        <el-pagination
            layout="prev, pager, next, jumper , -> ,total, sizes"
            v-model:page-size="pageSize"
            v-model:current-page="pageNum"
            @size-change="handleSizeChange"
            @current-change="handlePageChange"
            :page-sizes="[10,20,50,100]"
            :total="total">
        </el-pagination>
      </div>
    </el-card>

  </div>

  <PlanDetail
      v-show="scene=='detail'"
      ref="plan"
      @saveOrCancel="saveOrCancel"
  ></PlanDetail>
</template>

<script setup lang="ts">
import {Plus, Search} from "@element-plus/icons-vue";
import PlanDetail from "@/views/home/plan/PlanDetail.vue";
import {onMounted, ref} from "vue";
import {plan_page} from "@/api/plan";
import StoreDetail from "@/views/home/store/StoreDetail.vue";

let scene = ref<string>('list');
let plan = ref<any>();

let pageNum = ref<number>(1);
let pageSize = ref<number>(10);
let total = ref<number>(0);
let planName = ref<string>();
let planList = ref<PlanInfo[]>();


const addPlan = () => {
  scene.value = 'detail';
  plan.value.init();
};
const updatePlan = (item: PlanInfo) => {
  scene.value = 'detail';
  plan.value.init(item);
}

const searchPlan = async () => {
  await page();
}
const handleSizeChange = async () => {
  await page();
}
const handlePageChange = async () => {
  await page();
}
onMounted(async () => {
  await page();
});

const page = async () => {
  let queryParam = {
    planName: planName.value,
    pageQuery:
        {
          pageSize: pageSize.value,
          pageNum: pageNum.value,
        }
  };
  let planPage = await plan_page(queryParam);
  total.value = planPage.total;
  planList.value = planPage.list;
};
const saveOrCancel = (obj: string) => {
  scene.value = 'list';
  if (obj == 'save') {
    pageNum.value = 1;
    page();
  }
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