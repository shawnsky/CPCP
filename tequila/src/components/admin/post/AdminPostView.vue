<template>
  <a-table :columns="columns" :dataSource="data" bordered>
    <template slot="operation" slot-scope="text, record">
      <div class="editable-row-operations">
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
    dataIndex: "time",
    width: "40%",
    scopedSlots: { customRender: "time" }
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
    title: "自信很重要",
    time: "2020/02/20 18:30"
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
