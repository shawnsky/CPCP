<template>
  <div>
    <a-list bordered :dataSource="data" style="height: 632px">
      <a-list-item slot="renderItem" slot-scope="item">{{item}}</a-list-item>

    </a-list>
  </div>
</template>
<script>
const data = [
    '5835007@qq.com',
    '5835007@qq.com',
    '5835007@qq.com',
    '5835007@qq.com',
    '5835007@qq.com',
  ];
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
      data
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
