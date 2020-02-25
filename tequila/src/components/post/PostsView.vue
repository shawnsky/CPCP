<template>
  <div>
      <div class="title"><a-icon type="read" /> 推荐文章</div>
    <a-list
      itemLayout="vertical"
      size="large"
      :pagination="pagination"
      :dataSource="listData"
    >
      <a-list-item slot="renderItem" slot-scope="item" key="item.title">
        <img
          slot="extra"
          width="272"
          alt="logo"
          src="https://ossimg.xinli001.com/20200221/2fe9a2e7e659d487a8aa22b38a709fb8.jpeg?x-oss-process=image/quality,Q_80"
        />
        <a-list-item-meta :description="item.description">
          <a slot="title" :href="item.href">{{ item.title }}</a>
        </a-list-item-meta>
        {{ item.content }}
      </a-list-item>
    </a-list>
  </div>
</template>
<script>
const listData = [];
for (let i = 0; i < 10; i++) {
  listData.push({
    href: "/post/5",
    title: `自信对一个人有多重要？`,
    description:
      "发布时间 2020/02/20 18:30",
    content:
      "自信是一个人幸福生活的重要元素，有自信的人往往生活的更加愉快，会让我们的生活和工作更加顺利。但自信并非“从天而降”，要建立起自信，就需要我们理解自信是什么？有什么提高我们自信的方法？然后接纳自己的优点和不足，善待自己，爱自己。"
  });
}

export default {
  data() {
    return {
      listData,
      pagination: {
        onChange: page => {
          console.log(page);
        },
        pageSize: 3
      }
    };
  }
};
</script>
<style></style>
