<template>
  <a-table :columns="columns" :dataSource="data" bordered>
    <template slot="operation" slot-scope="text, record">
      <div class="editable-row-operations">
        <span>
            <a @click="() => del(record.key)">回复</a>
        </span>
        <span>
            <a @click="() => del(record.key)">删除</a>
        </span>
      </div>
    </template>
  </a-table>
</template>
<script>
const columns = [
  {
    title: "留言ID",
    dataIndex: "id",
    width: "10%",
    scopedSlots: { customRender: "id" }
  },
  {
    title: "用户",
    dataIndex: "user",
    width: "15%",
    scopedSlots: { customRender: "user" }
  },
  {
    title: "留言内容",
    dataIndex: "content",
    width: "65%",
    scopedSlots: { customRender: "content" }
  },
  {
    title: "操作",
    dataIndex: "operation",
    scopedSlots: { customRender: "operation" }
  }
];

const data = [];
for (let i = 0; i < 100; i++) {
  data.push({
    key: i.toString(),
    id: `${i}`,
    user: "Jeff",
    content: "我心情很不好"
  });
}
export default {
  data() {
    this.cacheData = data.map(item => ({ ...item }));
    return {
      data,
      columns
    };
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
    del(key) {
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
