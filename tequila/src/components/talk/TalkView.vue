<template>
  <div>
    <!-- <a-list
    :grid="{ gutter: 16, column: 4}"
      :dataSource="data"
    >
      <a-list-item slot="renderItem" slot-scope="item">
        <a-card :title="item.user" style="width: 300px">
         
          <p>{{item.content}}</p>
        
        </a-card>
      </a-list-item>
    </a-list> -->

    <h3>对本站的意见或一些相关的信息</h3>
    <a-textarea placeholder="请输入留言" :rows="6" v-model="data" />
    <a-button type="primary" style="margin: 32px 0 0 0; float: right">提交</a-button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      data: ""
      // data: [
      //     {
      //         user: "苏同学",
      //         content: "Hi"
      //     },
      //      {
      //         user: "Bob",
      //         content: "Hi"
      //     },
      //      {
      //         user: "Bob",
      //         content: "Hi"
      //     },
      //      {
      //         user: "Bob",
      //         content: "Hi"
      //     },
      //      {
      //         user: "Bob",
      //         content: "Hi"
      //     },
      //      {
      //         user: "Bob",
      //         content: "Hi"
      //     }
      // ]
    };
  }
};
</script>
