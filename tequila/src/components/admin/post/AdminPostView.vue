<template>
  <div>
    <div class="title">文章列表</div>
    <a-table :columns="columns" :dataSource="data" bordered>
      <template slot="operation" slot-scope="text, record">
        <div class="editable-row-operations">
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
    title: "文章ID",
    dataIndex: "id",
    width: "10%",
    scopedSlots: { customRender: "id" }
  },
  {
    title: "标题",
    dataIndex: "title",
    width: "35%",
    scopedSlots: { customRender: "title" }
  },
  {
    title: "发布时间",
    dataIndex: "createTime",
    width: "40%",
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
        .get(api.Post)
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
    handleChange(value, key, column) {
      const newData = [...this.data];
      const target = newData.filter(item => key === item.key)[0];
      if (target) {
        target[column] = value;
        this.data = newData;
      }
    },
    success() {
      this.$message.success("删除成功", 2);
    },
    del(key) {
      axios.delete(api.Post + "/" + key).then(response => {
        if (response.status == 204) {
          this.success();
          this.fetchList();
        }
      });
    }
  }
};
</script>
<style scoped>
.editable-row-operations a {
  margin-right: 8px;
}
</style>
