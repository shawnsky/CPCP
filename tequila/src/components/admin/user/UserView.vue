<template>
  <div>
    <div class="title">用户列表</div>
    <a-table :columns="columns" :dataSource="data" bordered>
      <template
        v-for="col in ['username', 'nickname']"
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
      <template slot="gender" slot-scope="record">
        <div v-if="!record">未知</div>
        {{ record }}
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
  </div>
</template>
<script>
import api from "@/api/index";
import axios from "axios";
const columns = [
  {
    title: "用户名/邮箱",
    dataIndex: "username",
    width: "25%",
    scopedSlots: { customRender: "username" }
  },
  {
    title: "昵称",
    dataIndex: "nickname",
    width: "30%",
    scopedSlots: { customRender: "nickname" }
  },
  {
    title: "年龄",
    dataIndex: "age",
    width: "10%",
    scopedSlots: { customRender: "age" }
  },
  {
    title: "性别",
    dataIndex: "gender",
    width: "10%",
    scopedSlots: { customRender: "gender" }
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
    this.fetchData();
  },
  methods: {
    fetchData() {
      axios.get(api.User).then(response => {
        this.data = response.data.data;
        this.data.map(ele => (ele.key = ele.id));
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
      const target = newData.filter(item => key === item.key)[0];
      if (target) {
        delete target.editable;
        this.data = newData;
        this.updateUser(target);
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
      axios.delete(api.User + "/" + key).then(response => {
        if (response.status == 204) {
          this.$message.success("用户删除成功");
          this.fetchData();
        } else {
          this.$message.error("用户删除失败");
        }
      });
    },
    updateUser(user) {
      axios
        .put(api.User + "/" + user.id, {
          username: user.username,
          nickname: user.nickname
        })
        .then(response => {
          if (response.data.code == 0) {
            this.$message.error("用户数据更新失败，请检查输入");
          } else if (response.data.code == 1) {
            this.$message.success("用户数据更新成功");
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
