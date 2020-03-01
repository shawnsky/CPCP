<template>
  <div>
    <a-menu
      @click="handleClick"
      style="width: 256px; height: 600px"
      :defaultSelectedKeys="[skey]"
      mode="inline"
    >
      <a-menu-item key="1"
        ><router-link to="/user"> 基本设置</router-link></a-menu-item
      >
      <a-menu-item key="2"
        ><router-link to="/user/security"> 安全设置</router-link></a-menu-item
      >
      <a-menu-item key="3"
        ><router-link to="/user/message"> 消息中心</router-link></a-menu-item
      >
    </a-menu>
  </div>
</template>
<script>
export default {
  props: {
    skey: {
      type: String,
      default: "1",
      required: true
    }
  },
  data() {
    return {
      
    }
  },
  methods: {
    handleClick(e) {
      console.log("click", e);
    },
    titleClick(e) {
      console.log("titleClick", e);
    }
  }
};
</script>
