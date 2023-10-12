<template>
  <div class="list" v-show="scene=='list'">
    <el-card class="box-top">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="门店编码">
          <el-input placeholder="请输入门店编码" v-model="storeCode" clearable/>
        </el-form-item>

        <el-form-item label="订单编号">
          <el-input placeholder="请输入订单编号" v-model="orderCode" clearable/>
        </el-form-item>


        <el-form-item label="下单时间范围">
          <el-date-picker
              v-model="orderTime"
              type="daterange"
              unlink-panels
              range-separator="至"
              start-placeholder="下单开始时间"
              end-placeholder="下单终止时间"
              :shortcuts="shortcuts"
              :clearable="false"
          />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" :icon="Search" @click="searchStore">查询</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card class="box-card">

      <el-button type="primary" :icon="Plus" style="margin-bottom: 15px" @click="mockDelivery">模拟运单</el-button>


      <el-table :data="orderInfo" border style="width: 100%">
        <el-table-column prop="orderCode" label="订单号"/>
        <el-table-column prop="storeCode" label="门店Code"/>
        <el-table-column prop="receiveUserName" label="收货人姓名"/>
        <el-table-column prop="receiveUserPhone" label="收货人电话"/>
        <el-table-column prop="receiveUserAddress" label="收货人地址"/>
        <el-table-column prop="totalPrice" label="订单价格">
          <template #="{row, $index}">
            {{ row.totalPrice ? filterMoney(row.totalPrice) + "元" : '无' }}
          </template>
        </el-table-column>
        <el-table-column prop="orderTime" label="下单时间"/>
        <el-table-column label="订单种类">
          <template #="{row, $index}">
            {{ row.isMock ? 'Mock订单' : '常规订单' }}
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template #="{row,$index}">
            <el-button size="small" @click="toDetail(row)">查看详情</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="page">
        <el-pagination layout="prev, pager, next, jumper , -> ,total, sizes"
                       v-model:page-size="pageSize"
                       v-model:current-page="pageNum"
                       :page-sizes="[10,20,50,100]"
                       :total="total"
                       @size-change="handleSizeChange"
                       @current-change="handlePageChange"
        />
      </div>
    </el-card>


    <el-drawer
        v-model="drawer"
        title="模拟运单推送!"
    >
      <MockDelivery @manualClose="manualClose" ref="drawerRef"/>
    </el-drawer>
  </div>

  <div v-show="scene=='detail'">
    <DeliveryDetail
        ref="deliveryRef"
        @back="back"
    ></DeliveryDetail>
  </div>

</template>

<script setup lang="ts">

import {Plus, Search} from "@element-plus/icons-vue";
import {onMounted, ref} from "vue";
import MockDelivery from './mockDelivery.vue'
import DeliveryDetail from "./DeliveryDetail.vue";
import {order_page} from "@/api/delivery";
import {format} from "date-fns";
import {ElMessage} from "element-plus";

let scene = ref<string>('list');

const drawer = ref(false)

let storeCode = ref<string>();
let orderCode = ref<string>();
let total = ref<number>();
let pageSize = ref<number>(10);
let pageNum = ref<number>(1);
let deliveryRef = ref();

let drawerRef = ref<any>();

let orderInfo = ref<OrderInfo[]>();


const defaultTimePicker = () => {
  const end = new Date()
  end.setTime(end.getTime() + 3600 * 1000 * 24);
  const start = new Date()
  start.setTime(start.getTime() - 3600 * 1000 * 24 * 29)
  return [start, end]
}

const orderTime = ref<[Date, Date]>(defaultTimePicker());

const query = async () => {
  if (orderTime == null) {
    ElMessage({
      type: "error",
      message: '时间区间必选!'
    })
  }
  let value: OrderListReq = {
    endTime: format(new Date(orderTime.value[1]), 'yyyy-MM-dd'),
    orderCode: orderCode.value,
    pageQuery: {
      pageNum: pageNum.value,
      pageSize: pageSize.value
    },
    startTime: format(new Date(orderTime.value[0]), 'yyyy-MM-dd'),
    storeNo: storeCode.value
  };
  let result = await order_page(value);
  total.value = result.total;
  orderInfo.value = result.list;
}

onMounted(async () => {
  await query();
})
const back = async () => {
  scene.value = 'list';
  await query();
}

const handleSizeChange = async () => {
  await query();
}
const handlePageChange = async () => {
  await query();
}
const searchStore = async () => {
  await query();
}

const toDetail = async (info: OrderInfo) => {
  scene.value = 'detail';
  deliveryRef.value.init(info.orderCode);
};
const mockDelivery = async () => {
  drawer.value = true;
  drawerRef.value.init();
}
const manualClose = async (obj: string) => {
  drawer.value = false;
  if (obj == 'create') {
    await query();
  }
};

const filterMoney = (num: string) => {
  return (num / 100).toFixed(2);
}

const shortcuts = [
  {
    text: '最近一周',
    value: () => {
      const end = new Date()
      end.setTime(end.getTime() + 3600 * 1000 * 24);
      const start = new Date()
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 6)
      return [start, end]
    },
  },
  {
    text: '最近一月',
    value: () => {
      const end = new Date()
      end.setTime(end.getTime() + 3600 * 1000 * 24);
      const start = new Date()
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 29)
      return [start, end]
    },
  },
  {
    text: '最近三月',
    value: () => {
      const end = new Date()
      end.setTime(end.getTime() + 3600 * 1000 * 24);
      const start = new Date()
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 89)
      return [start, end]
    },
  },
]

</script>

<style scoped lang="scss">
.box-top {
  margin-bottom: 20px;
}

.page {
  margin-top: 10px;
}
</style>