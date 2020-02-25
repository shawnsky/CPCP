<template>
  <div>
    <h3>在此编辑系统公告</h3>
    <a-textarea placeholder="系统公告..." :rows="6" v-model="data" />
    <a-button type="primary" style="margin: 32px 0 0 0; float: right"
      >提交</a-button
    >
  </div>
</template>

<script>
export default {
  data() {
    return {
      data: ""
    };
  }
};
</script>
