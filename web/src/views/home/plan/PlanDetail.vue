<template>
  <div class="planInfo">
    <el-form :model="planForm" label-width="120px">
      <el-form-item label="方案名称">
        <el-input v-model="planForm.planName"/>
      </el-form-item>
      <el-form-item label="超时取消间隔">
        <el-select v-model="planForm.timePeriod">
          <el-option label="15 分钟" value="15"/>
          <el-option label="30 分钟" value="30"/>
          <el-option label="45 分钟" value="45"/>
        </el-select>
      </el-form-item>

      <el-form-item label="方案类型">
        <el-select v-model="planForm.type">
          <el-option label="默认排序" value="1"/>
          <el-option label="价格优先" value="2"/>
          <el-option label="距离优先" value="3"/>
        </el-select>
      </el-form-item>

      <el-form-item label="方案描述">
        <el-input type="textarea" v-model="planForm.desc"/>
      </el-form-item>

      <el-form-item label="配送渠道">
        <el-select v-model="channel" placeholder="请选择配送渠道" @change="channelSelected">
          <el-option
              v-for="item in channelOptions"
              :key="item.channel"
              :label="item.channelName"
              :value="item.channel"
              :disabled="item.disabled"
          />
        </el-select>
      </el-form-item>


      <el-table :data="channelData" style="padding-left: 80px">
        <el-table-column type="index" label="优先级" width="80"/>
        <el-table-column prop="channelName" label="配送渠道" width="180"/>
        <el-table-column prop="channel" label="渠道简称" width="180"/>
        <el-table-column prop="intro" label="渠道简介"/>
        <el-table-column label="操作" width="180">
          <template #="{ row, $index }">
            <el-button @click="deleteChannel(row,$index)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-form>

    <div class="btn_container">
      <el-button type="primary" @click="save">保存</el-button>
      <el-button type="primary" @click="back">返回</el-button>
    </div>
  </div>
</template>

<script setup lang="ts">
import {nextTick, reactive, ref} from "vue";
import {plan_add, plan_update} from "@/api/plan";
import userStore from "@/store/modules/user.ts";

let $emit = defineEmits(['saveOrCancel']);
let planForm = reactive({});
let useUser = userStore();
const channel = ref('')
const channelOptions = ref([
  {
    channel: 'meituan',
    channelName: '美团',
    intro: '全平台配送'
  },
  {
    channel: 'fengniao',
    channelName: '蜂鸟',
    intro: '全平台配送'
  },
  {
    channel: 'dada',
    channelName: '达达',
    intro: '商超这块,俺是专业滴'
  }
]);
const channelData = ref([]);


const channelSelected = (value) => {
  for (let ele of channelOptions.value) {
    if (value == ele.channel) {
      ele.disabled = true;
      channelData.value.push(ele);
    }
  }
}
const deleteChannel = (channel,index) =>{
  channelData.value.splice(index, 1);

  for (let ele of channelOptions.value) {
    if (channel.value == ele.value) {
      ele.disabled = false;
    }
  }
}

const save = async ()=>{
  let channels: PlanChannelMapping [] = [];
  for (let i = 0; i < channelData.value.length; i++) {
    let channel:PlanChannelMapping = {
      channel: channelData.value[i].channel,
      channelName: channelData.value[i].channelName,
      id: undefined,
      sort: i + 1,
    }
    channels.push(channel);
  }
  let planInfo: PlanAdd = {
    id: planForm.id,
    desc: planForm.desc,
    type: planForm.type,
    mappingList: channels,
    planName: planForm.planName,
    timePeriod: planForm.timePeriod,
    operateUser: {
      userName: useUser.userInfo.nickName
    },
  };
  if (planForm.id == null) {
    await plan_add(planInfo);
  }else{
    await plan_update(planInfo);
  }
  $emit('saveOrCancel', 'save');
}
const init = (plan: PlanInfo) => {
  channelData.value = [];
  if (plan != null ) {
    planForm.id = plan.id;
    planForm.desc = plan.desc;
    planForm.planName = plan.planName;
    planForm.type = plan.type;
    planForm.timePeriod = plan.timePeriod;
    planForm.mappingList = plan.mappingList;
  }else{
    planForm.id = undefined;
    planForm.desc = '';
    planForm.planName = '';
    planForm.type = '';
    planForm.timePeriod = 15;
    planForm.mappingList = [];
  }

  initChannel();
};
const initChannel = ()=>{
  channel.value = '';
  let channelShortNames: string[] = [];
  if (planForm != null && planForm.mappingList != null) {
    for (const item of planForm.mappingList) {
      channelShortNames.push(item.channel);
    }
  }

  for (let ele of channelOptions.value) {
    ele.disabled = false;
    if (channelShortNames.includes(ele.channel)) {
      ele.disabled = true;
      channelData.value.push(ele);
    }
  }
}
const back = ()=>{
  $emit('saveOrCancel', 'update');
}
defineExpose({init});
</script>

<style scoped>
.planInfo {
  margin-top: 25px;
  width: 70%;
}

.btn_container {
  width: 70%;
  display: flex;
  margin-top: 40px;
  justify-content: center;
}
</style>