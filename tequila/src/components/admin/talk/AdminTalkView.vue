<template>
  <div>
    <div class="title">留言板管理</div>
    <a-table :columns="columns" :dataSource="data" bordered>
      <template slot="operation" slot-scope="text, record">
        <div class="editable-row-operations">
          <span>
            <a @click="() => reply(record.key)">回复</a>
          </span>
          <span>
            <a @click="() => del(record.key)">删除</a>
          </span>
        </div>
      </template>
    </a-table>
  </div>
</template>
<script>
import api from "@/api/index";
import axios from "axios";
const columns = [
  {
    title: "留言ID",
    dataIndex: "id",
    width: "10%",
    scopedSlots: { customRender: "id" }
  },
  {
    title: "用户",
    dataIndex: "username",
    width: "15%",
    scopedSlots: { customRender: "user" }
  },
  {
    title: "留言内容",
    dataIndex: "content",
    width: "35%",
    scopedSlots: { customRender: "content" }
  },
  {
    title: "留言时间",
    dataIndex: "createTime",
    width: "30%",
    scopedSlots: { customRender: "content" }
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
    this.fetchList()
  },
  methods: {
    handleChange(value, key, column) {
      const newData = [...this.data];
      const target = newData.filter(item => key === item.key)[0];
      if (target) {
        target[column] = value;
        this.data = newData;
      }
    },
    fetchList() {
      axios
      .get(api.Talk, {
        headers: { Authorization: localStorage.token }
      })
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
        this.$router.push({ path: "/login" });
        console.log(error);
      });
    },
    del(key) {
      axios
      .delete(api.Talk + '/' + key, {
        headers: { Authorization: localStorage.token }
      })
      .then(response => {
        if (response.status == 204) {
          this.$message.success('删除成功')
          this.fetchList()
        } else {
          this.$message.error('删除失败')
        }
      })
    },
    reply(key) {
      console.log(key)
    }

  }
};
</script>
<style scoped>
.editable-row-operations a {
  margin-right: 8px;
}
</style>
