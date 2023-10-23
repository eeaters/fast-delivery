<template>
  <div class="query">
    <el-card class="box-card">
      <el-form :inline="true" label-width="120px" class="demo-form-inline">
        <el-form-item label="订单编号">
          <el-input placeholder="请选择查询的运单编号" v-model="orderCode"/>
        </el-form-item>
        <el-form-item>
        <el-button type="primary" @click="query">查询</el-button>
        <el-button type="primary" @click="back">去列表</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>

  <div class="delivery-info">
    <el-form :inline="true" label-width="120px" class="demo-form-inline">
      <el-card class="box-card" style="margin-top: 20px">
        <template #header>
          <div class="card-header">
            <span>运单基本信息</span>
          </div>
        </template>
        <el-form-item label="订单编号">
          <el-input placeholder="无" disabled v-model="orderInfo.orderInfo.orderCode"/>
        </el-form-item>
        <el-form-item label="发货门店编号">
          <el-input placeholder="无" disabled v-model="orderInfo.orderInfo.storeCode"/>
        </el-form-item>
        <el-form-item label="收货人">
          <el-input placeholder="无" disabled v-model="orderInfo.orderInfo.receiveUserName"/>
        </el-form-item>
        <el-form-item label="收货人地址">
          <el-input placeholder="无" disabled v-model="orderInfo.orderInfo.receiveUserAddress"/>
        </el-form-item>
        <el-form-item label="收货人电话">
          <el-input placeholder="无" disabled v-model="orderInfo.orderInfo.receiveUserPhone"/>
        </el-form-item>

        <el-form-item label="配送策略">
          <el-popover placement="right" :width="400" trigger="click">
            <template #reference>
              <el-button style="margin-right: 16px">查看配送策略</el-button>
            </template>
            配送策略: {{ typeConvert(weightInfo.type) }}
            <el-table :data="weightInfo.infoList" style="width: 100%">
              <el-table-column label="渠道">
                <template #="{row, $index}">
                  {{ channelConvert(row.channel) }}
                </template>
              </el-table-column>
              <el-table-column prop="sort" label="优先级"/>
              <el-table-column prop="price" label="价格"/>
              <el-table-column prop="distance" label="距离"/>
            </el-table>
          </el-popover>
        </el-form-item>


        <el-table :data="products" style="width: 100%">
          <el-table-column prop="productName" label="商品名称"/>
          <el-table-column prop="productNum" label="商品数量"/>
          <el-table-column prop="productRemark" label="商品描述"/>
        </el-table>
      </el-card>

      <el-card class="box-card" style="margin-top: 20px">
        <template #header>
          <div class="card-header">
            <span>三方配送信息</span>
          </div>
        </template>

        <el-form-item label="推送渠道">
          <el-input placeholder="无" disabled :value="channelConvert(deliveryInfo.channel)"> </el-input>
        </el-form-item>
        <el-form-item label="渠道运单号">
          <el-input placeholder="无" v-model="deliveryInfo.channelOrderId" disabled/>
        </el-form-item>
        <el-form-item label="渠道费用">
          <el-input placeholder="无" v-model="deliveryInfo.channelPrice" disabled/>
        </el-form-item>
        <el-form-item label="预计送达时间">
          <el-input placeholder="无" disabled  v-model="deliveryInfo.exceptTime"/>
        </el-form-item>

        <el-form-item label="配送骑手">
          <el-input placeholder="无" v-model="deliveryInfo.riderName" disabled/>
        </el-form-item>
        <el-form-item label="骑手电话">
          <el-input placeholder="无" v-model="deliveryInfo.riderPhone" disabled/>
        </el-form-item>

        <el-divider/>
        <el-form-item label="推送三方时间">
          <el-input placeholder="无" v-model="deliveryInfo.pushTime" disabled/>
        </el-form-item>
        <el-form-item label="三方接单时间">
          <el-input placeholder="无" disabled/>
        </el-form-item>
        <el-form-item label="骑手接单时间">
          <el-input placeholder="无" v-model="deliveryInfo.riderReceiveTime" disabled/>
        </el-form-item>
        <el-form-item label="骑手取货时间">
          <el-input placeholder="无" v-model="deliveryInfo.arriveStoreTime" disabled/>
        </el-form-item>
        <el-form-item label="骑手配送时间">
          <el-input placeholder="无" v-model="deliveryInfo.deliveringTime" disabled/>
        </el-form-item>
        <el-form-item label="配送完成时间">
          <el-input placeholder="无" v-model="deliveryInfo.completeTime" disabled/>
        </el-form-item>
        <el-form-item label="配送异常时间">
          <el-input placeholder="无" v-model="deliveryInfo.errorTime" disabled/>
        </el-form-item>

        <el-form-item label="配送取消时间">
          <el-input placeholder="无" v-model="deliveryInfo.cancelTime" disabled/>
        </el-form-item>

        <el-divider/>

        <el-form-item label="配送异常原因">
          <el-input placeholder="无" disabled/>
        </el-form-item>
        <el-form-item label="配送取消原因">
          <el-input placeholder="无" disabled/>
        </el-form-item>
      </el-card>

    </el-form>

  </div>
</template>

<script setup lang="ts">
import {onMounted, reactive, ref} from "vue";
import {delivery_detail, order_info} from "@/api/delivery";
import {channelConvert} from "@/util/channel.ts";
import {ElMessage} from "element-plus";

let orderCode = ref<string>();


let orderInitial = reactive<OrderInfo>({
  isMock: undefined,
  orderCode: "",
  orderTime: "",
  receiveUserAddress: "",
  receiveUserName: "",
  receiveUserPhone: "",
  storeCode: "",
  totalPrice: 0,
  weightInfo: undefined
})
let deliveryInitial = reactive < DeliveryDetailInfo>({
  arriveStoreTime: "",
  cancelCode: "",
  cancelMessage: "",
  cancelTime: "",
  channelOrderId: "",
  channelPrice: 0,
  completeTime: "",
  deliveringTime: "",
  errorCode: "",
  errorMessage: "",
  errorTime: "",
  orderCode: "",
  orderId: 0,
  pushTime: "",
  riderName: "",
  riderPhone: "",
  riderReceiveTime: "",
  status: 0
});
let deliveryInfo = reactive<DeliveryDetailInfo>({...deliveryInitial});


let orderInfo = reactive<OrderDetailInfo>({
  orderInfo: {...orderInitial},
  productInfos: [],
});



let weightInfo = reactive<WeightInfo>({
  infoList: [], type: 0
});


const typeConvert = (type: number) => {
  if (type == 1) {
    return '默认排序';
  }
  if (type == 2) {
    return '价格优先'
  }
  if (type == 3) {
    return '距离优先';
  }
}

const query = async () => {
  if (orderCode.value == null || orderCode.value == '') {
    ElMessage({
      type:"error",
      message:"请输入订单号"
    })
  }else{
    await search();
  }
};
const init = async (code: string) => {
  orderCode.value = code;
  orderInfo.orderInfo = {...orderInitial};
  Object.assign(deliveryInfo, deliveryInitial);
  await search();
};
const search = async () => {
  let result = await delivery_detail(orderCode.value);
  let detailInfo = await order_info(orderCode.value);

  Object.assign(weightInfo, JSON.parse(detailInfo.orderInfo.weightInfo))
  Object.assign(orderInfo, detailInfo);
  Object.assign(deliveryInfo, result);
};

let products: ProductInfo [] = reactive([{
  productName: "SKⅡ神仙水",
  productNum: "1",
  productRemark: "女神节必备"
}]);

let $emit = defineEmits(['back']);
const back = ()=>{
  $emit('back');
}
defineExpose({init});
</script>

<style scoped lang="scss">
.demo-form-inline .el-input {
  --el-input-width: 250px;
}
</style>