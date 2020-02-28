<template>
  <a-table :columns="columns" :dataSource="data" bordered>
    <template slot="value" slot-scope="record">
      {{ record }}
      <a-tag v-if="record >= 50" color="#f50" style="margin-left: 8px"
        >建议沟通</a-tag
      >
      <a-tag
        v-if="record >= 30 && record < 50"
        color="#2db7f5"
        style="margin-left: 8px"
        >需要关注</a-tag
      >
      <a-tag v-if="record < 30" color="#87d068" style="margin-left: 8px"
        >正常</a-tag
      >
    </template>
    <template slot="result" slot-scope="record">
      <div style="letter-spacing: 8px">{{ record }}</div>
    </template>
    <template slot="operation" slot-scope="text, record">
      <div class="editable-row-operations">
        <span>
          <a @click="() => getContact(record.userId)">联系他</a>
        </span>
      </div>
    </template>
  </a-table>
</template>
<script>
import api from "@/api/index";
import axios from "axios";
const columns = [
  {
    title: "测试ID",
    dataIndex: "id",
    width: "10%",
    scopedSlots: { customRender: "id" }
  },
  {
    title: "用户ID",
    dataIndex: "userId",
    width: "10%",
    scopedSlots: { customRender: "userId" }
  },
  {
    title: "分数",
    dataIndex: "value",
    width: "20%",
    scopedSlots: { customRender: "value" }
  },
  {
    title: "答案",
    dataIndex: "result",
    width: "30%",
    scopedSlots: { customRender: "result" }
  },
  {
    title: "答题时间",
    dataIndex: "createTime",
    width: "20%",
    scopedSlots: { customRender: "createTime" }
  },
  {
    title: "操作",
    dataIndex: "operation",
    scopedSlots: { customRender: "operation" }
  }
];

export default {
  data() {
    return {
      data: [],
      columns
    };
  },
  mounted() {
    this.fetchList();
  },
  methods: {
    fetchList() {
      axios
        .get(api.TestResult)
        .then(response => {
          var rawList = response.data.data;
          rawList.map(element => {
            element.key = element.id;
            element.createTime = new Date(parseInt(element.createTime))
              .toLocaleString()
              .replace(/:\d{1,2}$/, " ");
          });
          this.data = rawList;
        })
        .catch(error => {
          console.log(error);
        });
    },
    getContact(userId) {
      console.log(userId);
    }
  }
};
</script>
<style scoped>
.editable-row-operations a {
  margin-right: 8px;
}
</style>
