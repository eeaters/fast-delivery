<template>
  <div>
    <el-form :model="form" label-width="120px">

      <el-form-item label="门店编号">
        <el-select v-model="form.storeCode" placeholder="选一个门店">
          <el-option
              v-for="item in storeOptions"
              :key="item.storeCode"
              :label="item.storeName"
              :value="item.storeCode"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="订单号">
        <el-input v-model="form.orderCode"/>
      </el-form-item>
      <el-form-item label="收货人地址">
        <el-input v-model="form.receiveUserAddress"/>
      </el-form-item>
      <el-form-item label="收货人经度">
        <el-input v-model="form.receiveUserLongitude"/>
      </el-form-item>
      <el-form-item label="收货人纬度">
        <el-input v-model="form.receiveUserLatitude"/>
      </el-form-item>
      <el-form-item label="收货人姓名">
        <el-input v-model="form.receiveUserName"/>
      </el-form-item>
      <el-form-item label="收货人电话">
        <el-input v-model="form.receiveUserPhone"/>
      </el-form-item>

      <el-form-item label="下单时间">
        <el-input v-model="form.orderTime" disabled/>
      </el-form-item>
      <el-form-item label="订单总金额">
        <el-input v-model="form.totalPrice"/>
      </el-form-item>

      <el-form-item label="订单总重量">
        <el-input v-model="form.weightGram"/>
      </el-form-item>

      <el-form-item label="商品信息">
        <el-table :data="products" style="width: 100%">
          <el-table-column prop="productName" label="商品名称" width="80"/>
          <el-table-column prop="productNum" label="商品数量" width="80"/>
          <el-table-column prop="productRemark" label="商品描述"/>
        </el-table>
      </el-form-item>

    </el-form>

    <div class="btn">
      <el-button type="primary" @click="createDelivery">创建运单</el-button>
      <el-button type="primary" @click="cancel">取消创建</el-button>
    </div>
  </div>
</template>

<script setup lang="ts">
import {onMounted, reactive, ref} from "vue";
import {store_list_all} from "@/api/store";
import {mock_delivery, mock_delivery_create} from "@/api/delivery/mock-api.ts";
import { format } from 'date-fns';


let $emit = defineEmits(['manualClose']);
let products: ProductInfo [] = reactive([{
  productName: "SKⅡ神仙水",
  productNum: "1",
  productRemark: "女神节必备"
}]);


let form :DeliveryCreateReq = reactive({
  callbackUrl: '',
  orderCode: '',
  orderTime: format(new Date(), 'yyyy-MM-dd HH:mm:ss'),
  receiveUserAddress: '',
  receiveUserLongitude: '',
  receiveUserLatitude: '',
  receiveUserName: '',
  receiveUserPhone: '',
  storeCode: '',
  totalPrice: '',
  weightGram: '',
  detailInfos: products
});

let storeOptions = ref([]);


onMounted(async () => {
  storeOptions.value = await store_list_all();
  await init();
})

const init = async () => {
  let result = await mock_delivery();
  form.receiveUserLongitude = result.receiveUserLongitude;
  form.receiveUserLatitude = result.receiveUserLatitude;
  form.callbackUrl = result.callbackUrl;
  form.orderCode = result.orderCode;
  form.receiveUserAddress = result.receiveUserAddress;
  form.receiveUserName = result.receiveUserName;
  form.receiveUserPhone = result.receiveUserPhone;
  form.totalPrice = result.totalPrice;
  form.weightGram = result.weightGram;
};

const createDelivery = async ()=>{
  await mock_delivery_create(form);
  $emit('manualClose','create');
}
const cancel =  ()=>{
  $emit('manualClose','cancel');
}

defineExpose({init});

</script>

<style scoped lang="scss">
.btn {
  display: flex;
  margin-top: 40px;
  justify-content: center;
}
</style>