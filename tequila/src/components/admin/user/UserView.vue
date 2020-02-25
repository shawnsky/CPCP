<template>
  <a-table :columns="columns" :dataSource="data" bordered>
    <template
      v-for="col in ['name', 'age', 'address']"
      :slot="col"
      slot-scope="text, record"
    >
      <div :key="col">
        <a-input
          v-if="record.editable"
          style="margin: -5px 0"
          :value="text"
          @change="e => handleChange(e.target.value, record.key, col)"
        />
        <template v-else>{{ text }}</template>
      </div>
    </template>
    <template slot="operation" slot-scope="text, record">
      <div class="editable-row-operations">
        <span v-if="record.editable">
          <a @click="() => save(record.key)">保存</a>
          <a-popconfirm
            title="确定取消编辑吗？"
            @confirm="() => cancel(record.key)"
          >
            <a>取消</a>
          </a-popconfirm>
        </span>
        <span v-else>
          <a @click="() => edit(record.key)">编辑</a>
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
    title: "用户名",
    dataIndex: "name",
    width: "25%",
    scopedSlots: { customRender: "name" }
  },
  {
    title: "年龄",
    dataIndex: "age",
    width: "15%",
    scopedSlots: { customRender: "age" }
  },
  {
    title: "邮箱",
    dataIndex: "address",
    width: "40%",
    scopedSlots: { customRender: "address" }
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
    name: `Edrward ${i}`,
    age: 32,
    address: `London Park no. ${i}`
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
    edit(key) {
      const newData = [...this.data];
      const target = newData.filter(item => key === item.key)[0];
      if (target) {
        target.editable = true;
        this.data = newData;
      }
    },
    save(key) {
      const newData = [...this.data];
      const newCacheData = [...this.cacheData];
      const target = newData.filter(item => key === item.key)[0];
      const targetCache = newCacheData.filter(item => key === item.key)[0];
      if (target && targetCache) {
        delete target.editable;
        this.data = newData;
        Object.assign(targetCache, target);
        this.cacheData = newCacheData;
      }
    },
    cancel(key) {
      const newData = [...this.data];
      const target = newData.filter(item => key === item.key)[0];
      if (target) {
        Object.assign(
          target,
          this.cacheData.filter(item => key === item.key)[0]
        );
        delete target.editable;
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
