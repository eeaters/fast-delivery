<template>
  <div class="content">
    <div>
      <el-form :model="storeForm">
        <el-form-item prop="storeName" label="门店名称">
          <el-input placeholder="请输入门店名称" v-model="storeForm.storeName"></el-input>
        </el-form-item>
        <el-form-item prop="storeCode" label="门店编码">
          <el-input placeholder="请输入门店编码" v-model="storeForm.storeCode"></el-input>
        </el-form-item>
        <el-form-item label="省-市-区">
          <el-cascader :options="options" v-model="cityVal" @change="handleChange" change-on-select/>
        </el-form-item>
        <el-form-item prop="address" label="详细地址">
          <el-input placeholder="请输入门店地址" v-model="storeForm.address"></el-input>
        </el-form-item>
        <el-form-item prop="storeContract" label="联系人">
          <el-input placeholder="请输入门店联系人" v-model="storeForm.storeContract"></el-input>
        </el-form-item>
        <el-form-item prop="storeContactPhone" label="联系电话">
          <el-input placeholder="请输入门店联系人电话" v-model="storeForm.storeContractPhone"></el-input>
        </el-form-item>

        <el-form-item label="门店经度">
          <el-input placeholder="请输入门店经度" v-model="storeForm.latitude"></el-input>
        </el-form-item>
        <el-form-item label="门店纬度">
          <el-input placeholder="请输入门店纬度" v-model="storeForm.longitude"></el-input>
        </el-form-item>
        <el-form-item label="配送方案">
          <el-select v-model="storeForm.planId" placeholder="选择一个配送方案" clearable>
            <el-option
                v-for="item in planOptions"
                :key="item.id"
                :label="item.planName"
                :value="item.id"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <el-button type="primary" @click="save">保存</el-button>
      <el-button type="primary" @click="back">返回</el-button>
    </div>
  </div>
</template>

<script setup lang="ts">
import {onMounted, reactive, ref} from "vue";
import region_store from "@/store/modules/region.ts";
import {store_page_add} from "@/api/store";
import {plan_all} from "@/api/plan";
import userStore from "@/store/modules/user.ts";

let useRegion = region_store();
let useUser = userStore();
let planOptions = ref<PlanBaseInfo[]>();

let options = ref([]);
let $emit = defineEmits(['saveOrCancel']);


let storeForm = reactive<StoreAddReq>({
  storeContractPhone: "",
  storeContract: "",
  operateUser: {userName: useUser.userInfo.nickName},
  id: 0,
  address: "",
  provinceId: undefined,
  cityId: undefined,
  districtId: undefined,
  latitude: "",
  longitude: "",
  storeCode: "",
  storeName: "",
  planId: undefined
});

let cityVal = reactive(["", "", ""]);

const handleChange = (val) => {
  storeForm.provinceId = val[0];
  storeForm.cityId = val[1];
  storeForm.districtId = val[2];
};


const save = async () => {
  await store_page_add(storeForm);
  $emit('saveOrCancel', 'save');
};
const back = () => {
  $emit('saveOrCancel', 'cancel');
};

const init = async (store: StoreInfo) => {
  if (store == null || 'undefined' == store) {
    storeForm.id = undefined;
    storeForm.address = '';
    storeForm.provinceId = undefined;
    storeForm.cityId = undefined;
    storeForm.districtId = undefined;
    storeForm.latitude = "";
    storeForm.longitude = "";
    storeForm.storeCode = "";
    storeForm.storeName = "";
    storeForm.storeContract = "";
    storeForm.storeContractPhone = "";
    cityVal[0] = "";
    cityVal[1] = "";
    cityVal[2] = "";
  } else {
    Object.assign(storeForm, store);
    cityVal[0] = "" + store.provinceId;
    cityVal[1] = "" + store.cityId;
    cityVal[2] = "" + store.districtId;
  }
  planOptions.value = await plan_all();
}

onMounted(async () => {
  options.value = await useRegion.getRegion();
});

defineExpose({init});
</script>

<style scoped>

</style>
