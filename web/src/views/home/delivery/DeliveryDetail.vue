<template>
  <div class ="base">
    <el-form :inline="true" label-width="120px">
      <el-form-item label="运单号">
        <el-input  placeholder="Approved by" clearable />
      </el-form-item>
      <el-form-item label="推送渠道">
        <el-input  placeholder="Approved by" clearable />
      </el-form-item>
      <el-form-item label="预计送达时间">
        <el-input  placeholder="Approved by" clearable />
      </el-form-item>
      <el-form-item label="三方费用">
        <el-input  placeholder="Approved by" clearable />
      </el-form-item>

      <el-form-item label="推送三方时间">
        <el-input  placeholder="Approved by" clearable />
      </el-form-item>
      <el-form-item label="骑手接单时间">
        <el-input  placeholder="Approved by" clearable />
      </el-form-item>
      <el-form-item label="骑手到店时间">
        <el-input  placeholder="Approved by" clearable />
      </el-form-item>
      <el-form-item label="骑手开始配送时间">
        <el-input  placeholder="Approved by" clearable />
      </el-form-item>
      <el-form-item label="骑手送达时间">
        <el-input  placeholder="Approved by" clearable />
      </el-form-item>

  </div>
</template>

<script setup lang="ts">

</script>

<style scoped lang="scss">
.demo-form-inline .el-input {
  --el-input-width: 220px;
}
</style>